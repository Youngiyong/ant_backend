package com.ant.controller;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@CrossOrigin(origins = "*", maxAge = 10000)
@RestController
@RequestMapping("/news")
public class ElasticSearchController {

	@Autowired
	RestHighLevelClient client;
	
	
	//id값을 1씩 증가시키기 위해 static으로 선언
		static int count;
		UUID uuidran = UUID.randomUUID();
		//RestHighLevelClient사용후 반드 Close를 해야된다고 필히 명시가 되어 있었고
		//그에 따른 Spring에서 Bean 어노테이션에 destroymethod 속성을 사용해서 자원을 해제 할 수 있음
		
		@Bean(destroyMethod = "close")
		@Scope("prototype")//prototype : 어플리케이션에서 요청시 (getBean()) 마다 스프링이 새 인스턴스를 생성
		public RestHighLevelClient restHighLevelClient(){
		      return new RestHighLevelClient(RestClient.builder(new HttpHost("0.0.0.0",9200,"http")));
		}
		
		
		//index 생성시 mapping의 설정한 형식으로 생성됨
		@GetMapping(value = "/create")
		public String ping() throws IOException {
			CreateIndexRequest request = new CreateIndexRequest("news_keyword");
			request.settings(Settings.builder()
					.put("index.number_of_shards", 1)
					.put("index.number_of_replicas", 2)
					);
			request.mapping( 	
			        "{\n" +
			                "  \"properties\": {\n" +
			                "    \"word\": {\n" +
			                "      \"type\": \"text\",\n" +
			                "        \"fields\": {\n"+
			                "        	\"keyword\": {\n"+
			                "			\"type\": \"keyword\",\n"+
			                "			 \"ignore_above\": 256 \n"+
			                "    }\n" +
			                "  }\n" +
			                "  }\n" +
			                "  }\n" +
			                "}", 
			        XContentType.JSON);
			
			
			CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
			return "created";
		}
		
		
		//index에 키워드를 쌓음
		@GetMapping(value = "/upsert")
		public String upsert(String id) throws IOException {
			Map<String, Object> jsonMap = new HashMap<>();
			JSONObject obj = new JSONObject(id);
			jsonMap.put("word", obj.get("search"));
			System.out.println(obj.get("search"));
			IndexRequest request = new IndexRequest("news_keyword-2021.02.23").id(uuidran.toString()).source(jsonMap);
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
			return "success";
		}
		
		
		/*
		GET news_keyword-2021.02.21/_search
		{
	  		"size":0,
	  		"aggs": {
	    	"group_by_word": {
	      		"terms": {
	        "field": "word.keyword"
	      	}
	    	}
	  	}
		}
		 */
		
		
		@GetMapping(value = "searchcount")
	    public ResponseEntity searchcount() throws IOException {
			SearchRequest searchRequest = new SearchRequest("news_keyword-2021.02.23");
			System.out.println("searchcount");
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.size(0);		
			TermsAggregationBuilder aggregation = AggregationBuilders.terms("group_by_word").field("word.keyword").size(10);
			searchSourceBuilder.aggregation(aggregation);
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		
		
		
		//fuzzyQuery 유사 키워드 
		//검색어와 필드 값이 조금 차이가 나더라도 매치가 되도록 하고 싶을 수 있다. fuzziness 옵션을 지정하면 이것이 가능하다.
		//GetMapping은 RequestMapping(value="", method = RequestMethod.Get)을 대신 하는 코드 스프링 4.3버전 이후로 나왔다고함
		@GetMapping(value = "searchfuzzy")
	    public ResponseEntity searchfuzzy(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.fuzzyQuery("news_elastic", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(50);
	        SearchRequest searchRequest = new SearchRequest("news");
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }

		
		@GetMapping(value = "searchprefix")
	    public ResponseEntity searchprefix(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.prefixQuery("news_elastic", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        System.out.println(searchResponse);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeat(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }


		//match_pharse 쿼리
		//match 쿼리는 용어 사이에 띄어쓰기를 하면 bool-should 쿼리로 처리된다. 
		//띄어쓰기까지 모두 포함해 정확한 구(phrase)를 검색하고 싶다면 match_phrase 쿼리를 사용한다.
		//예) 모든 단어가 정확한 위치에 있어야 매치된다.
		@GetMapping(value = "searchmatchphrase")
	    public ResponseEntity searchmatchphrase(String id) throws IOException {
			System.out.println(id);
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_elastic", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
//	        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
//          sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));  
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeat(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }

	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		
		//검색 결과 없을시 띄어쓰기 제외후 쿼리
	    public SearchResponse searchRepeat(String id) throws IOException {
	    	String id2 = id.replaceAll(" ", "");
	    	System.out.println("searchRepeat"+id2);
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_elastic", id2);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10); 
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        System.out.println("searchRepeat" +searchResponse.getHits().getTotalHits());
	        return searchResponse;
	    }
		
	    public SearchResponse searchRepeatSort(String id) throws IOException {
	    	String id2 = id.replaceAll(" ", "");
	    	System.out.println("searchRepeat"+id2);
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_elastic", id2);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
	        sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        System.out.println("searchRepeat" +searchResponse.getHits().getTotalHits());
	        return searchResponse;
	    }

	    
		//최신순
		@GetMapping(value = "searchmatchphrasesort")
	    public ResponseEntity searchmatchphrasesort(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_elastic", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
            sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));  
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeatSort(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
		}
		
		//오늘의 뉴스 최신 9개
	    @GetMapping(value = "searchtodaynews")
	    public ResponseEntity searchtodaynews() throws IOException {
	        Calendar cal = Calendar.getInstance();
	        Date date = cal.getTime();
	        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchAllQuery();
	        System.out.println(dateString);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(9);
	        sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));
	        System.out.println(sourceBuilder);
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        JSONObject json = new JSONObject(searchResponse.toString());
	        System.out.println(json);
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		
		
		@GetMapping(value = "searchgroupmatchphrase")
	    public ResponseEntity searchgroupmatchphrase(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_group", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
	        sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeat(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		
		//주, 월 기간별 검색 쿼리
		@GetMapping(value = "searchmatchphrasedate")
	    public ResponseEntity searchmatchphrasedate(String id, String id2) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("news_elastic", id);
	        Calendar cal = Calendar.getInstance();
	        Date date = cal.getTime();
	        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        int num = Integer.parseInt(id2);
	        switch(num) {
	        case 7 : 
	        	cal.add(Calendar.DATE, -7);
	        	date = cal.getTime();
	        	dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        	break;
	        	
	        case 30:
	        	cal.add(Calendar.MONTH, -1);
	        	date = cal.getTime();
	        	dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        	break;
	        	
	        case 90:
	        	cal.add(Calendar.MONTH, -3);
	        	date = cal.getTime();
	        	dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        	break;
	        	
	        case 180:
	        	cal.add(Calendar.MONTH, -6);
	        	date = cal.getTime();
	        	dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        	break;
	        	
	        case 365:
	        	cal.add(Calendar.YEAR, -1);
	        	date = cal.getTime();
	        	dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        	break;
	        }
	        
	        SearchRequest searchRequest = new SearchRequest("news");
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
            sourceBuilder.postFilter(QueryBuilders.rangeQuery("news_date").from(dateString).to(now)); //gt 24
            sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeatSort(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
		}
		
		
		@GetMapping(value = "searchworkdkeyword")
	    public ResponseEntity searchworkdkeyword(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.prefixQuery("word", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10); 
	        SearchRequest searchRequest = new SearchRequest("news");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
	        	SearchResponse searchResponse02 = searchRepeat(id);
		        JSONObject json = new JSONObject(searchResponse02.toString());
		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	        }
	        JSONObject json = new JSONObject(searchResponse.toString());
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		
		//대량의 document를 추가/수정/삭제할때는 BulkAPI를 사용한다함
		@GetMapping(value = "bulkrequest")
	    public BulkResponse bulkrequest() throws IOException {
			BulkRequest request = new BulkRequest(); 
			request.add(new IndexRequest("news_keyword").id(uuidran.toString().replace("-", ""))
			        .source(XContentType.JSON,"word", "금융감독원"));
//			request.add(new IndexRequest("news").id("2")  
//			        .source(XContentType.JSON,"word", "금융결제원"));
//			request.add(new IndexRequest("news").id("3")  
//			        .source(XContentType.JSON,"word", "금융조합"));
//			request.add(new IndexRequest("news").id("4")  
//			        .source(XContentType.JSON,"word", "금융소비자원"));
//			request.add(new IndexRequest("news").id("5")  
//			        .source(XContentType.JSON,"word", "금융산업"));
//			request.add(new IndexRequest("news").id("6")  
//			        .source(XContentType.JSON,"word", "금융 자격증"));
//			request.add(new IndexRequest("news").id("7")  
//			        .source(XContentType.JSON,"word", "금융 데이터"));
//			request.add(new IndexRequest("news").id("8")  
//			        .source(XContentType.JSON,"word", "금융이란"));
//			request.add(new IndexRequest("news").id("9")  
//			        .source(XContentType.JSON,"word", "금융위기"));
//			request.add(new IndexRequest("news").id("10")  
//			        .source(XContentType.JSON,"word", "부자"));
//			request.add(new IndexRequest("news").id("11")  
//			        .source(XContentType.JSON,"word", "부동산"));
//			request.add(new IndexRequest("news").id("12")  
//			        .source(XContentType.JSON,"word", "부양"));
//			request.add(new IndexRequest("news").id("13")  
//			        .source(XContentType.JSON,"word", "삼성"));
//			request.add(new IndexRequest("news").id("14")  
//			        .source(XContentType.JSON,"word", "삼성전자"));
//			request.add(new IndexRequest("news").id("15")  
//			        .source(XContentType.JSON,"word", "삼성에스원"));
//			request.add(new IndexRequest("news").id("16")  
//			        .source(XContentType.JSON,"word", "삼성전기"));
//			request.add(new IndexRequest("news").id("17")  
//			        .source(XContentType.JSON,"word", "경제뉴스"));
//			request.add(new IndexRequest("news").id("18")  
//			        .source(XContentType.JSON,"word", "경제신문"));
//			request.add(new IndexRequest("news").id("19")  
//			        .source(XContentType.JSON,"word", "경제위기"));
//			request.add(new IndexRequest("news").id("20")  
//			        .source(XContentType.JSON,"word", "경제란"));
			BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
			return bulkResponse;
		}
		
		@GetMapping(value = "searchboardmatchpharse")
	    public ResponseEntity searchboardmatchpharse(String id) throws IOException {
	        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("board_search_content", id);
	        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	        sourceBuilder.query(matchQueryBuilder);
	        sourceBuilder.from(0);
	        sourceBuilder.size(10);
//	        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
//          sourceBuilder.sort(new FieldSortBuilder("news_date").order(SortOrder.DESC));  
	        SearchRequest searchRequest = new SearchRequest("boards");
	        searchRequest.source(sourceBuilder);
	        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
//	        if(searchResponse.getHits().getTotalHits().toString().equals("0 hits")) {
//	        	SearchResponse searchResponse02 = searchRepeat(id);
//		        JSONObject json = new JSONObject(searchResponse02.toString());
//		        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
//	        }

	        JSONObject json = new JSONObject(searchResponse.toString());
//	        System.out.println(searchRequest);
//	        System.out.println(sourceBuilder);
//	        System.out.println(searchResponse);
	        return new ResponseEntity<>(json.toMap(), HttpStatus.OK);
	    }
		

}
