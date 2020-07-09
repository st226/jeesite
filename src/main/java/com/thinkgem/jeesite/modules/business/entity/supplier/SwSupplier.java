/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.supplier;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * @author suntao
 * @version 2020-03-19
 */
public class SwSupplier extends DataEntity<SwSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 供应商名称
	private String legal;		// 法人
	private String agent;		// 代理
	private String code;		// 供应商编号
	private String phone;		// 供应商电话
	private String local;		// 公司地址
	private String dutyno;		// 供应商税号
	private String supplierType;		// 供应商类型
	private String supplierUser;		// 联系人
	private String userPhone;		// 联系人电话
	private String bankName;		// 开户行
	private String bankNumber;		// 账户
	private String bankLocal;		// 开户行地址
	private String supplierFax;
	private String supplierZip;
	private String supplierTel;
	private String appendix;		// 附件
	private String filed1;		// filed1
	private String filed2;		// filed2
	private String filed3;		// filed3
	private String filed4;		// filed4
	private String filed5;		// filed5
	
	public SwSupplier() {
		super();
	}

	public SwSupplier(String id){
		super(id);
	}

	@Length(min=1, max=255, message="供应商名称长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="供应商编号长度必须介于 1 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=255, message="供应商电话长度必须介于 1 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="公司地址长度必须介于 1 和 255 之间")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=0, max=255, message="供应商税号长度必须介于 1 和 255 之间")
	public String getDutyno() {
		return dutyno;
	}

	public void setDutyno(String dutyno) {
		this.dutyno = dutyno;
	}
	
	@Length(min=0, max=255, message="供应商类型长度必须介于 1 和 255 之间")
	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	
	@Length(min=1, max=255, message="联系人长度必须介于 1 和 255 之间")
	public String getSupplierUser() {
		return supplierUser;
	}

	public void setSupplierUser(String supplierUser) {
		this.supplierUser = supplierUser;
	}
	
	@Length(min=1, max=255, message="联系人电话长度必须介于 1 和 255 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Length(min=0, max=255, message="开户行长度必须介于 0 和 255 之间")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=0, max=255, message="账户长度必须介于 0 和 255 之间")
	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	@Length(min=0, max=255, message="开户行地址长度必须介于 0 和 255 之间")
	public String getBankLocal() {
		return bankLocal;
	}

	public void setBankLocal(String bankLocal) {
		this.bankLocal = bankLocal;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
	
	@Length(min=1, max=255, message="filed1长度必须介于 1 和 255 之间")
	public String getFiled1() {
		return filed1;
	}

	public void setFiled1(String filed1) {
		this.filed1 = filed1;
	}
	
	@Length(min=1, max=255, message="filed2长度必须介于 1 和 255 之间")
	public String getFiled2() {
		return filed2;
	}

	public void setFiled2(String filed2) {
		this.filed2 = filed2;
	}
	
	@Length(min=1, max=255, message="filed3长度必须介于 1 和 255 之间")
	public String getFiled3() {
		return filed3;
	}

	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}
	
	@Length(min=1, max=255, message="filed4长度必须介于 1 和 255 之间")
	public String getFiled4() {
		return filed4;
	}

	public void setFiled4(String filed4) {
		this.filed4 = filed4;
	}
	
	@Length(min=1, max=255, message="filed5长度必须介于 1 和 255 之间")
	public String getFiled5() {
		return filed5;
	}

	public void setFiled5(String filed5) {
		this.filed5 = filed5;
	}

	public String getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}

	public String getSupplierZip() {
		return supplierZip;
	}

	public void setSupplierZip(String supplierZip) {
		this.supplierZip = supplierZip;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
}