package com.thinkgem.jeesite.modules.resourcebus.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TsResourceTemp extends DataEntity<TsResourceTemp> {
	
	
	private static final long serialVersionUID = 1L;
	private String isList;	
	private String id;	
	
	public TsResourceTemp() {
		super();
	}

	public TsResourceTemp(String id){
		super(id);
	}

	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
