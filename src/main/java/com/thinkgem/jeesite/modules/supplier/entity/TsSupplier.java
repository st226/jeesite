/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * @author suntao
 * @version 2018-01-22
 */
public class TsSupplier extends DataEntity<TsSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 供应商名称
	private String code;		// 供应商编号
	private String contactMan;		// 联系人
	private String contactAdd;		// 联系地址
	private String contactNumber;		// 联系电话
	private String gysState;		// 供应商状态
	private String mailbox;		// 邮箱
	
	public TsSupplier() {
		super();
	}

	public TsSupplier(String id){
		super(id);
	}

	@Length(min=1, max=100, message="供应商名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="供应商编号长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=100, message="联系人长度必须介于 1 和 100 之间")
	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}
	
	@Length(min=0, max=100, message="联系地址长度必须介于 0 和 100 之间")
	public String getContactAdd() {
		return contactAdd;
	}

	public void setContactAdd(String contactAdd) {
		this.contactAdd = contactAdd;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Length(min=0, max=64, message="供应商状态长度必须介于 0 和 64 之间")
	public String getGysState() {
		return gysState;
	}

	public void setGysState(String gysState) {
		this.gysState = gysState;
	}
	
	@Length(min=0, max=64, message="邮箱长度必须介于 0 和 64 之间")
	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
}