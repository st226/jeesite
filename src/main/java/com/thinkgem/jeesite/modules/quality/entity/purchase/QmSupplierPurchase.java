/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.purchase;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.eos.workflow.data.WFWorkItem;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 合格供方目录外采购申请表Entity
 * @author suntao
 * @version 2020-06-04
 */
public class QmSupplierPurchase extends DataEntity<QmSupplierPurchase> {
	
	private static final long serialVersionUID = 1L;
	private String state;		// state
	private String processinstid;		// processinstid
	private String productName;		// 产品名称
	private String productType;		// 产品规格/技术标准/图纸/任务书
	private String productClass;		// 质量等级
	private String enterpriseName;		// 生产厂家
	private String enterpriseNature;		// 企业性质
	private String projectModel;		// 工程型号
	private String standName;		// 单机名称
	private String standDosage;		// 单机用量
	private String standAmount;		// 单技数量
	private String reason;		// 申请理由、风险识别控制
	private String applicant;		// 申请人
	private String statement;		// 廉洁书
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	
	public QmSupplierPurchase() {
		super();
	}

	public QmSupplierPurchase(String id){
		super(id);
	}

	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="processinstid长度必须介于 0 和 255 之间")
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="产品名称长度必须介于 0 和 255 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=255, message="产品规格/技术标准/图纸/任务书长度必须介于 0 和 255 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=255, message="质量等级长度必须介于 0 和 255 之间")
	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	
	@Length(min=0, max=255, message="生产厂家长度必须介于 0 和 255 之间")
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	@Length(min=0, max=255, message="企业性质长度必须介于 0 和 255 之间")
	public String getEnterpriseNature() {
		return enterpriseNature;
	}

	public void setEnterpriseNature(String enterpriseNature) {
		this.enterpriseNature = enterpriseNature;
	}
	
	@Length(min=0, max=255, message="工程型号长度必须介于 0 和 255 之间")
	public String getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(String projectModel) {
		this.projectModel = projectModel;
	}
	
	@Length(min=0, max=255, message="单机名称长度必须介于 0 和 255 之间")
	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}
	
	@Length(min=0, max=255, message="单机用量长度必须介于 0 和 255 之间")
	public String getStandDosage() {
		return standDosage;
	}

	public void setStandDosage(String standDosage) {
		this.standDosage = standDosage;
	}
	
	@Length(min=0, max=255, message="单技数量长度必须介于 0 和 255 之间")
	public String getStandAmount() {
		return standAmount;
	}

	public void setStandAmount(String standAmount) {
		this.standAmount = standAmount;
	}
	
	@Length(min=0, max=255, message="申请理由、风险识别控制长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	@Length(min=0, max=255, message="廉洁书长度必须介于 0 和 255 之间")
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
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

	public List<Examine> getExamineList() {
		return examineList;
	}

	public void setExamineList(List<Examine> examineList) {
		this.examineList = examineList;
	}

	public WFWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WFWorkItem workItem) {
		this.workItem = workItem;
	}
	
}