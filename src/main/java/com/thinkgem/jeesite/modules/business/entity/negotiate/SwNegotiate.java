/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.negotiate;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商务谈判Entity
 * @author 孙涛
 * @version 2020-03-19
 */
public class SwNegotiate extends DataEntity<SwNegotiate> {
	
	
	private static final long serialVersionUID = 1L;
	private String orderId ;
	private String state ;
	private Date negotiateTime;		// 谈判时间
	private String negotiateLocal;		// 谈判地点
	private String projectName;		// 项目名称
	private String procedures;		// 采用程序
	private String supplier;		// 供应商来源
	private String projectContent;		// 项目内容
	private String quotedPrice;		// 报价情况
	private String negotiateNotes;		// 谈判记录
	private String negotiateUser;		// 经办人
	private String totalPrice;		// 总价
	private String negotiatePrice;		// 谈判价
	private String balancePrice;		// 差额
	private String negotiateUsers;		// 本单位谈判人员
	private String tendersUser;		// 招标办
	private String appendix;		// 附件
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private List<SwNegotiateSupplier> swNegotiateSupplierList = Lists.newArrayList();		// 子表列表
	
	public SwNegotiate() {
		super();
	}

	public SwNegotiate(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNegotiateTime() {
		return negotiateTime;
	}

	public void setNegotiateTime(Date negotiateTime) {
		this.negotiateTime = negotiateTime;
	}
	
	@Length(min=0, max=100, message="谈判地点长度必须介于 1 和 50 之间")
	public String getNegotiateLocal() {
		return negotiateLocal;
	}

	public void setNegotiateLocal(String negotiateLocal) {
		this.negotiateLocal = negotiateLocal;
	}
	
	@Length(min=1, max=100, message="项目名称长度必须介于 1 和 100 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=200, message="采用程序长度必须介于 0 和 200 之间")
	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
	
	@Length(min=1, max=200, message="供应商来源长度必须介于 1 和 200 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=500, message="项目内容长度必须介于 0 和 500 之间")
	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	
	@Length(min=0, max=500, message="报价情况长度必须介于 0 和 500 之间")
	public String getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(String quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	
	@Length(min=0, max=2000, message="谈判记录长度必须介于 0 和 2000 之间")
	public String getNegotiateNotes() {
		return negotiateNotes;
	}

	public void setNegotiateNotes(String negotiateNotes) {
		this.negotiateNotes = negotiateNotes;
	}
	
	@Length(min=0, max=100, message="经办人长度必须介于 0 和 100 之间")
	public String getNegotiateUser() {
		return negotiateUser;
	}

	public void setNegotiateUser(String negotiateUser) {
		this.negotiateUser = negotiateUser;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getNegotiatePrice() {
		return negotiatePrice;
	}

	public void setNegotiatePrice(String negotiatePrice) {
		this.negotiatePrice = negotiatePrice;
	}
	
	public String getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(String balancePrice) {
		this.balancePrice = balancePrice;
	}
	
	@Length(min=0, max=500, message="本单位谈判人员长度必须介于 0 和 500 之间")
	public String getNegotiateUsers() {
		return negotiateUsers;
	}

	public void setNegotiateUsers(String negotiateUsers) {
		this.negotiateUsers = negotiateUsers;
	}
	
	@Length(min=0, max=200, message="招标办长度必须介于 0 和 200 之间")
	public String getTendersUser() {
		return tendersUser;
	}

	public void setTendersUser(String tendersUser) {
		this.tendersUser = tendersUser;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
	
	@Length(min=0, max=200, message="field1长度必须介于 0 和 200 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=200, message="field2长度必须介于 0 和 200 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=200, message="field3长度必须介于 0 和 200 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=200, message="field4长度必须介于 0和 200 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=200, message="field5长度必须介于 0 和 200 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	public List<SwNegotiateSupplier> getSwNegotiateSupplierList() {
		return swNegotiateSupplierList;
	}

	public void setSwNegotiateSupplierList(List<SwNegotiateSupplier> swNegotiateSupplierList) {
		this.swNegotiateSupplierList = swNegotiateSupplierList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}