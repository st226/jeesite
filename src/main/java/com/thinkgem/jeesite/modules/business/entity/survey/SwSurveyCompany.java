/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.survey;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 调研报告Entity
 * @author suntao
 * @version 2020-04-05
 */
public class SwSurveyCompany extends DataEntity<SwSurveyCompany> {
	
	private static final long serialVersionUID = 1L;
	private SwSurvey surveyId;		// survey_id 父类
	private String name;		// 供应商名称
	private String region;		// 地域
	private String price;		// 总价
	private String mode;		// 调研方式
	private String contacts;		// 联系人
	private String telephone;		// 联系电话
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SwSurveyCompany() {
		super();
	}

	public SwSurveyCompany(String id){
		super(id);
	}

	public SwSurveyCompany(SwSurvey surveyId){
		this.surveyId = surveyId;
	}

	@Length(min=0, max=255, message="survey_id长度必须介于 0 和 255 之间")
	public SwSurvey getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(SwSurvey surveyId) {
		this.surveyId = surveyId;
	}
	
	@Length(min=0, max=255, message="供应商名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="地域长度必须介于 0 和 255 之间")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	@Length(min=0, max=255, message="总价长度必须介于 0 和 255 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=255, message="调研方式长度必须介于 0 和 255 之间")
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@Length(min=0, max=255, message="联系人长度必须介于 0 和 255 之间")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="field2长度必须介于 0 和 255 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=255, message="field3长度必须介于 0 和 255 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=255, message="field4长度必须介于 0 和 255 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=255, message="field5长度必须介于 0 和 255 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
}