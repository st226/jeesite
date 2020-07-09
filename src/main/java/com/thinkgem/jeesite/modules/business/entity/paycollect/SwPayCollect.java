/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.paycollect;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 付款信息汇总Entity
 * @author suntao
 * @version 2020-05-04
 */
public class SwPayCollect extends DataEntity<SwPayCollect> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;		// processinstid
	private String contractId;		// 合同ID
	private String purchaseId;		// purchase_id
	private String payId;		// pay_id
	private String orderId;		// order_id
	private String state;		// 付款状态
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
	private String appendix;		// appendix
	private String phone;		// phone
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private Date completionTime;		// completion_time
	private Date expectedCompletionTime;		// expected_completion_time
	
	public SwPayCollect() {
		super();
	}

	public SwPayCollect(String id){
		super(id);
	}

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="合同ID长度必须介于 0 和 255 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=255, message="purchase_id长度必须介于 0 和 255 之间")
	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	@Length(min=0, max=255, message="pay_id长度必须介于 0 和 255 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Length(min=0, max=255, message="order_id长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=255, message="付款状态长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	
	@Length(min=0, max=2000, message="appendix长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
	
	@Length(min=0, max=255, message="phone长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpectedCompletionTime() {
		return expectedCompletionTime;
	}

	public void setExpectedCompletionTime(Date expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
	}
	
}