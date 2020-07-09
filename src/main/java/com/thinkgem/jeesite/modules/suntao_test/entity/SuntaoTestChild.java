/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.suntao_test.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试主子表Entity
 * @author suntao
 * @version 2020-05-02
 */
public class SuntaoTestChild extends DataEntity<SuntaoTestChild> {
	
	private static final long serialVersionUID = 1L;
	private SuntaoTest sunId;		// sun_id 父类
	private String name;		// name
	private String test;		// test
	
	public SuntaoTestChild() {
		super();
	}

	public SuntaoTestChild(String id){
		super(id);
	}

	public SuntaoTestChild(SuntaoTest sunId){
		this.sunId = sunId;
	}

	@Length(min=0, max=255, message="sun_id长度必须介于 0 和 255 之间")
	public SuntaoTest getSunId() {
		return sunId;
	}

	public void setSunId(SuntaoTest sunId) {
		this.sunId = sunId;
	}
	
	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="test长度必须介于 0 和 255 之间")
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}