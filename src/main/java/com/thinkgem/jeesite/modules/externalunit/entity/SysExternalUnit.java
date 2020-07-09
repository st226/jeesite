/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.externalunit.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 外单位信息维护Entity
 * @author 孙涛
 * @version 2020-04-26
 */
public class SysExternalUnit extends DataEntity<SysExternalUnit> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 单位名称
	
	public SysExternalUnit() {
		super();
	}

	public SysExternalUnit(String id){
		super(id);
	}

	@Length(min=0, max=255, message="单位名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}