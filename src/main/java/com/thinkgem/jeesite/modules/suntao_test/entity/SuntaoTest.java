/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.suntao_test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试主子表Entity
 * @author suntao
 * @version 2020-05-02
 */
public class SuntaoTest extends DataEntity<SuntaoTest> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private List<SuntaoTestChild> suntaoTestChildList = Lists.newArrayList();		// 子表列表
	
	public SuntaoTest() {
		super();
	}

	public SuntaoTest(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<SuntaoTestChild> getSuntaoTestChildList() {
		return suntaoTestChildList;
	}

	public void setSuntaoTestChildList(List<SuntaoTestChild> suntaoTestChildList) {
		this.suntaoTestChildList = suntaoTestChildList;
	}
}