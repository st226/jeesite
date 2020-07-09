/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maxtable.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 流水号Entity
 * @author suntao
 * @version 2018-01-26
 */
public class TsMaxTable extends DataEntity<TsMaxTable> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 字段名称
	private Integer code;		// 最大值
	
	public TsMaxTable() {
		super();
	}

	public TsMaxTable(String id){
		super(id);
	}

	@Length(min=0, max=20, message="字段名称长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}