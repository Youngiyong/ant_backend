package com.ant.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages="com.ant.mapper.indicators",sqlSessionFactoryRef="db2SqlSessionFactory")/*멀티DB사용시 mapper클래스파일 스켄용 basePackages를 DB별로 따로설정*/
@EnableTransactionManagement
public class DBConfig_2 {
	
	@Bean(name="db2DataSource")
	@ConfigurationProperties(prefix="spring.db2.datasource") //appliction.properties 참고.
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
		
	}
	
	@Bean(name="db2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource, ApplicationContext applicationContext) throws Exception{
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(db2DataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis-mapper-indicators/*.xml")); //쿼리작성용 mapper.xml위치 설정.
		return sessionFactory.getObject();
	}
	
	@Bean(name="db2SqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db2sqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(db2sqlSessionFactory);
	}
	
    @Bean(name = "db2transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("db2DataSource") DataSource db2DataSource) {
        return new DataSourceTransactionManager(db2DataSource);
    }
}
