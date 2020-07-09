/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同审批Entity
 * @author suntao
 * @version 2020-03-20
 */
public class SwContract extends DataEntity<SwContract> {
	
	private static final long serialVersionUID = 1L;
	private String orderId ;
	private String state ;
	private String contractId;
	private String contractCode;		// 合同编号
	private String contractName;		// 合同名称
	private String contractType;		// 合同类型
	private String contractPrice;		// 合同价款
	private String supplierId;		// 对方单位ID
	private String supplierName;		// 对方单位
	private String supplierUser;		// 联系人
	private String supplierPhone;		// 联系电话
	private String contractUser;		// 经办人
	private String contractPhone;		// 经办人电话
	private String appendix;		// 附件
	private String appendixLength;		// 页数
	private String negotiateId;		// 谈判
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SwContract() {
		super();
	}

	public SwContract(String id){
		super(id);
	}

	@Length(min=1, max=100, message="合同编号长度必须介于 1 和 100 之间")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@Length(min=1, max=100, message="合同名称长度必须介于 1 和 100 之间")
	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	
	@Length(min=1, max=100, message="合同类型长度必须介于 1 和 100 之间")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}
	
	@Length(min=0, max=100, message="对方单位ID长度必须介于 0 和 100 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=100, message="对方单位长度必须介于 0 和 100 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=100, message="联系人长度必须介于 0 和 100 之间")
	public String getSupplierUser() {
		return supplierUser;
	}

	public void setSupplierUser(String supplierUser) {
		this.supplierUser = supplierUser;
	}
	
	@Length(min=0, max=100, message="联系电话长度必须介于 0 和 100 之间")
	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	
	@Length(min=0, max=100, message="经办人长度必须介于 0 和 100 之间")
	public String getContractUser() {
		return contractUser;
	}

	public void setContractUser(String contractUser) {
		this.contractUser = contractUser;
	}
	
	@Length(min=0, max=255, message="经办人电话长度必须介于 0 和 255 之间")
	public String getContractPhone() {
		return contractPhone;
	}

	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
	
	@Length(min=0, max=11, message="页数长度必须介于 0 和 11 之间")
	public String getAppendixLength() {
		return appendixLength;
	}

	public void setAppendixLength(String appendixLength) {
		this.appendixLength = appendixLength;
	}
	
	@Length(min=0, max=200, message="谈判长度必须介于 0 和 200 之间")
	public String getNegotiateId() {
		return negotiateId;
	}

	public void setNegotiateId(String negotiateId) {
		this.negotiateId = negotiateId;
	}
	

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	

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
	
	@Length(min=1, max=255, message="field5长度必须介于 1 和 255 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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