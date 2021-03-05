package com.ant.vo;

// 지표 정렬 목적 객체
public class IndicatorTableVO {
	
	private String tableName;
	private float changedate;
	
	public IndicatorTableVO(String tableName, float changedate) {
		super();
		this.tableName = tableName;
		this.changedate = changedate;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public float getChangedate() {
		return changedate;
	}

	public void setChangedate(float changedate) {
		this.changedate = changedate;
	}
	
}
