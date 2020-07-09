/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.statistical.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 统计分析Entity
 * @author suntao
 * @version 2018-02-04
 */
public class TsStatistical extends DataEntity<TsStatistical> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String url;		// 地址
	private String titleid;		// 标题
	private String titlename;		// 标题名称
	
	public TsStatistical() {
		super();
	}

	public TsStatistical(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="地址长度必须介于 0 和 50 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=50, message="标题长度必须介于 0 和 50 之间")
	public String getTitleid() {
		return titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}
	
	@Length(min=0, max=50, message="标题名称长度必须介于 0 和 50 之间")
	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	
}