/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.agreement;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同录入Entity
 * @author suntao
 * @version 2020-03-24
 */
public class SwAgreementProduct extends DataEntity<SwAgreementProduct> {
	
	private static final long serialVersionUID = 1L;
	private SwAgreement agreementId;		// agreement_id 父类
	private String productNo;		// 序号
	private String productName;		// 产品名称
	private String productType;		// 规格型号
	private String productMade;		// 生产厂商
	private String productAmount;		// 数量
	private String productShipment;		// 货期
	private String productCompany;		// 单位
	private String unitPrice;		// 单价
	private String totalPrice;		// 总价
	
	public SwAgreementProduct() {
		super();
	}

	public SwAgreementProduct(String id){
		super(id);
	}

	public SwAgreementProduct(SwAgreement agreementId){
		this.agreementId = agreementId;
	}

	@Length(min=0, max=255, message="agreement_id长度必须介于 0 和 255 之间")
	public SwAgreement getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(SwAgreement agreementId) {
		this.agreementId = agreementId;
	}
	
	@Length(min=0, max=255, message="序号长度必须介于 0 和 255 之间")
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Length(min=0, max=255, message="产品名称长度必须介于 0 和 255 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=255, message="规格型号长度必须介于 0 和 255 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=255, message="生产厂商长度必须介于 0 和 255 之间")
	public String getProductMade() {
		return productMade;
	}

	public void setProductMade(String productMade) {
		this.productMade = productMade;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}
	
	@Length(min=0, max=255, message="货期长度必须介于 0 和 255 之间")
	public String getProductShipment() {
		return productShipment;
	}

	public void setProductShipment(String productShipment) {
		this.productShipment = productShipment;
	}
	
	@Length(min=0, max=255, message="单位长度必须介于 0 和 255 之间")
	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}