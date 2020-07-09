/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.agreement;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同录入Entity
 * @author suntao
 * @version 2020-03-24
 */
public class SwAgreementPay extends DataEntity<SwAgreementPay> {
	
	private static final long serialVersionUID = 1L;
	private String payIndex;		// pay_index
	private SwAgreement agreementId;		// agreement_id 父类
	private String payRate;		// 百分比
	private String payAmount;		// 总计
	private String payThis;		// 本次
	private String paySurplus;		// 剩余
	private String payCondition;		// 付款条件
	private Date payDate;		// pay_date
	private String state ;
	
	public SwAgreementPay() {
		super();
	}

	public SwAgreementPay(String id){
		super(id);
	}

	public SwAgreementPay(SwAgreement agreementId){
		this.agreementId = agreementId;
	}

	@Length(min=0, max=11, message="pay_index长度必须介于 0 和 11 之间")
	public String getPayIndex() {
		return payIndex;
	}

	public void setPayIndex(String payIndex) {
		this.payIndex = payIndex;
	}
	
	@Length(min=0, max=255, message="agreement_id长度必须介于 0 和 255 之间")
	public SwAgreement getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(SwAgreement agreementId) {
		this.agreementId = agreementId;
	}
	
	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	public String getPayThis() {
		return payThis;
	}

	public void setPayThis(String payThis) {
		this.payThis = payThis;
	}
	
	public String getPaySurplus() {
		return paySurplus;
	}

	public void setPaySurplus(String paySurplus) {
		this.paySurplus = paySurplus;
	}
	
	@Length(min=0, max=255, message="付款条件长度必须介于 0 和 255 之间")
	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}