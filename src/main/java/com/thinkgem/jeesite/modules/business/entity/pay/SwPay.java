/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.pay;

import org.hibernate.validator.constraints.Length;

import java.util.Date;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 请款单Entity
 * @author suntao
 * @version 2020-03-20
 */
public class SwPay extends DataEntity<SwPay> {
	
	private static final long serialVersionUID = 1L;
	private String contractId;		// 合同ID
	private String purchaseId;
	private String payId;		// 合同ID
	private String payIndex;		// 合同ID
	private String orderId;		// 合同ID
	private Long processinstid;		// 流程号
	private String state;		// 合同ID
	private String contractCode;		// 合同编号
	private String contrateName;		// 合同名称
	private String payType;		// 付款方式
	private String supplierId;		// 供应商ID
	private String supplierName;		// 供应商名称
	private String supplierTel;		// 供应商电话
	private String contratePrice;		// 合同总额
	private String contratePaid;		// 已付款额
	private String contrateTreat;		// 本次付款额
	private String supplierBank;		// 开户行
	private String supplierBankName;		// 账号名称
	private String supplierBankNumber;		// 账户
	private String supplierAccount;		// 付款原因
	private String appendix;		// 附件
	private String phone;		// 联系电话
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String collectId;		// field5
	private Date projectDate ;
	private String projectClass ;
	private String payCondition ;
	private Date completionTime ;
	private Date expectedCompletionTime ;
	private String identification ;
	
	public SwPay() {
		super();
	}

	public SwPay(String id){
		super(id);
	}

	@Length(min=0, max=255, message="合同ID长度必须介于 0 和 255 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="合同编号长度必须介于 0 和 255 之间")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@Length(min=0, max=255, message="合同名称长度必须介于 0 和 255 之间")
	public String getContrateName() {
		return contrateName;
	}

	public void setContrateName(String contrateName) {
		this.contrateName = contrateName;
	}
	
	@Length(min=0, max=255, message="付款方式长度必须介于 0 和 255 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=255, message="供应商ID长度必须介于 0 和 255 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=255, message="供应商名称长度必须介于 0 和 255 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=255, message="供应商电话长度必须介于 0 和 255 之间")
	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	
	public String getContratePrice() {
		return contratePrice;
	}

	public void setContratePrice(String contratePrice) {
		this.contratePrice = contratePrice;
	}
	
	public String getContratePaid() {
		return contratePaid;
	}

	public void setContratePaid(String contratePaid) {
		this.contratePaid = contratePaid;
	}
	
	public String getContrateTreat() {
		return contrateTreat;
	}

	public void setContrateTreat(String contrateTreat) {
		this.contrateTreat = contrateTreat;
	}
	
	@Length(min=0, max=255, message="开户行长度必须介于 0 和 255 之间")
	public String getSupplierBank() {
		return supplierBank;
	}

	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}
	
	@Length(min=0, max=255, message="账号名称长度必须介于 0 和 255 之间")
	public String getSupplierBankName() {
		return supplierBankName;
	}

	public void setSupplierBankName(String supplierBankName) {
		this.supplierBankName = supplierBankName;
	}
	
	@Length(min=0, max=255, message="账户长度必须介于 0 和 255 之间")
	public String getSupplierBankNumber() {
		return supplierBankNumber;
	}

	public void setSupplierBankNumber(String supplierBankNumber) {
		this.supplierBankNumber = supplierBankNumber;
	}
	
	@Length(min=0, max=255, message="付款原因长度必须介于 0 和 255 之间")
	public String getSupplierAccount() {
		return supplierAccount;
	}

	public void setSupplierAccount(String supplierAccount) {
		this.supplierAccount = supplierAccount;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=255, message="field1长度必须介于 1 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=1, max=255, message="field2长度必须介于 1 和 255 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=1, max=255, message="field3长度必须介于 1 和 255 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=1, max=255, message="field4长度必须介于 1 和 255 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=255, message="field5长度必须介于 1 和 255 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
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

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getPayIndex() {
		return payIndex;
	}

	public void setPayIndex(String payIndex) {
		this.payIndex = payIndex;
	}

	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public Date getExpectedCompletionTime() {
		return expectedCompletionTime;
	}

	public void setExpectedCompletionTime(Date expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
	}

	public Date getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}

	public String getProjectClass() {
		return projectClass;
	}

	public void setProjectClass(String projectClass) {
		this.projectClass = projectClass;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}


	
}