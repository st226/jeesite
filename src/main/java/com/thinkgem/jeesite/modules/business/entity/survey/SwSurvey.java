/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.survey;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 调研报告Entity
 * @author suntao
 * @version 2020-04-05
 */
public class SwSurvey extends DataEntity<SwSurvey> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单号
	private String mainUses;		// 主要用途
	private String situation;		// 目前情况
	private String technical;		// 技术指标
	private String budget;		// 预算来源
	private String supplier;		// 供应商
	private String result;		// 评定结构
	private String other;		// 其他
	private String state;		// state
	private String completeBy;		// complete_by
	private String file;		// complete_by
	private Date completeDate;		// complete_date
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;	
	private String resolutionOther;		// field5
	private String resolutionFile;		// field5
	private String resolution;		// field5
	private String resolutionResult;		// field5
	private Date resolutionDate;		// field5
	private List<SwSurveyCompany> swSurveyCompanyList = Lists.newArrayList();		// 子表列表
	
	public SwSurvey() {
		super();
	}

	public SwSurvey(String id){
		super(id);
	}

	@Length(min=0, max=255, message="订单号长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=2550, message="主要用途长度必须介于 0 和 2550 之间")
	public String getMainUses() {
		return mainUses;
	}

	public void setMainUses(String mainUses) {
		this.mainUses = mainUses;
	}
	
	@Length(min=0, max=2550, message="目前情况长度必须介于 0 和 2550 之间")
	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	@Length(min=0, max=2550, message="技术指标长度必须介于 0 和 2550 之间")
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	@Length(min=0, max=2550, message="预算来源长度必须介于 0 和 2550 之间")
	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	@Length(min=0, max=2550, message="供应商长度必须介于 0 和 2550 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=2550, message="评定结构长度必须介于 0 和 2550 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Length(min=0, max=2550, message="其他长度必须介于 0 和 2550 之间")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="complete_by长度必须介于 0 和 255 之间")
	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
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
	
	public List<SwSurveyCompany> getSwSurveyCompanyList() {
		return swSurveyCompanyList;
	}

	public void setSwSurveyCompanyList(List<SwSurveyCompany> swSurveyCompanyList) {
		this.swSurveyCompanyList = swSurveyCompanyList;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getResolutionOther() {
		return resolutionOther;
	}

	public void setResolutionOther(String resolutionOther) {
		this.resolutionOther = resolutionOther;
	}

	public String getResolutionFile() {
		return resolutionFile;
	}

	public void setResolutionFile(String resolutionFile) {
		this.resolutionFile = resolutionFile;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Date getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getResolutionResult() {
		return resolutionResult;
	}

	public void setResolutionResult(String resolutionResult) {
		this.resolutionResult = resolutionResult;
	}
}