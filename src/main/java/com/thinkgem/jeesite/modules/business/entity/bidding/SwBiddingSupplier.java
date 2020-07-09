/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.bidding;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 内部招投标Entity
 * @author suntao
 * @version 2020-04-10
 */
public class SwBiddingSupplier extends DataEntity<SwBiddingSupplier> {
	
	private static final long serialVersionUID = 1L;
	private SwBidding biddingId;		// 谈判 父类
	private String supplierId;		// 供应商ID
	private String supplierName;		// 供应商名称
	private String supplierUser;		// 联系人
	private String phone;		// 联系电话
	private String tel;		// 座机
	private String email;		// 电子邮件
	private String totalPrice;		// 总价
	private String negotiatePrice;		// 谈判价
	private String balancePrice;		// 差额
	private String appendix;		// appendix
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String isBid ;
	
	public SwBiddingSupplier() {
		super();
	}

	public SwBiddingSupplier(String id){
		super(id);
	}

	public SwBiddingSupplier(SwBidding biddingId){
		this.biddingId = biddingId;
	}

	@Length(min=0, max=50, message="谈判长度必须介于 0 和 50 之间")
	public SwBidding getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(SwBidding biddingId) {
		this.biddingId = biddingId;
	}
	
	@Length(min=0, max=50, message="供应商ID长度必须介于 0 和 50 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=200, message="供应商名称长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="座机长度必须介于 0 和 100 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=100, message="电子邮件长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=255, message="总价长度必须介于 0 和 255 之间")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=255, message="谈判价长度必须介于 0 和 255 之间")
	public String getNegotiatePrice() {
		return negotiatePrice;
	}

	public void setNegotiatePrice(String negotiatePrice) {
		this.negotiatePrice = negotiatePrice;
	}
	
	@Length(min=0, max=255, message="差额长度必须介于 0 和 255 之间")
	public String getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(String balancePrice) {
		this.balancePrice = balancePrice;
	}
	
	@Length(min=0, max=2000, message="appendix长度必须介于 0 和 2000 之间")
	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
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

	public String getIsBid() {
		return isBid;
	}

	public void setIsBid(String isBid) {
		this.isBid = isBid;
	}
	
}