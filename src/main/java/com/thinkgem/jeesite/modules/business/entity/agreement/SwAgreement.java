/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.agreement;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 合同录入Entity
 * @author suntao
 * @version 2020-03-24
 */
public class SwAgreement extends DataEntity<SwAgreement> {
	
	private static final long serialVersionUID = 1L;
	private String processinstid;		// processinstid
	private String state;		// state
	private String file ;
	private String orderId;		// state
	private String agreementNo;		// 合同编号
	private String agreementName;		// 合同名称
	private String agreementType;		// 合同类型
	private String handledBy;		// 经办人
	private Date handledDate;		// 经办时间
	private String agreementFirst;		// 甲方
	private String agreementSecond;		// 乙方
	private Date signingTime;		// 签订时间
	private String signingPlace;		// 签订地点
	private String amount;		// 总价
	private String amountup;		// 总价大写
	private String solveDay;		// 解决日期
	private String warranty;		// 质保期
	private String deliveryTime;		// 交货时间
	private String deliveryMethod;		// 交货方式
	private String objectionDay;		// 异议天数
	private String objectionDayHf;		// 异议回复天数
	private String invoiceDay;		// 发票交付天数
	private String falsify;		// 违约金
	private String firstName;		// first_name
	private String firstPlace;		// first_place
	private String firstLegal;		// first_legal
	private String firstAgent;		// first_agent
	private String firstNameLiaison;		// first_name_liaison
	private String firstNamePhone;		// first_name_phone
	private String firstBank;		// first_bank
	private String firstBankNo;		// first_bank_no
	private String firstDuty;		// first_duty
	private String firstZip;		// first_zip
	private String firstTel;		// first_tel
	private String firstFax;		// first_fax
	private String secondName;		// 乙方单位名称
	private String secondPlace;		// 单位地址
	private String secondLegal;		// 法人代表
	private String secondAgent;		// 委托代理
	private String secondNameLiaison;		// 联系人
	private String secondNamePhone;		// 联系电话
	private String secondBank;		// 开户银行
	private String secondBankNo;		// 账户
	private String secondDuty;		// 税号
	private String secondZip;		// 邮编
	private String secondTel;		// 电话
	private String secondFax;		// 传真
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String field6;		// field6
	private String field7;		// field7
	private String field8;		// field8
	private String field9;		// field9
	private String field10;		// field10
	private List<SwAgreementPay> swAgreementPayList = Lists.newArrayList();		// 子表列表
	private List<SwAgreementProduct> swAgreementProductList = Lists.newArrayList();		// 子表列表
	
	public SwAgreement() {
		super();
	}

	public SwAgreement(String id){
		super(id);
	}

	@Length(min=0, max=255, message="processinstid长度必须介于 0 和 255 之间")
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="合同编号长度必须介于 0 和 255 之间")
	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	
	@Length(min=0, max=255, message="甲方长度必须介于 0 和 255 之间")
	public String getAgreementFirst() {
		return agreementFirst;
	}

	public void setAgreementFirst(String agreementFirst) {
		this.agreementFirst = agreementFirst;
	}
	
	@Length(min=0, max=255, message="乙方长度必须介于 0 和 255 之间")
	public String getAgreementSecond() {
		return agreementSecond;
	}

	public void setAgreementSecond(String agreementSecond) {
		this.agreementSecond = agreementSecond;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(Date signingTime) {
		this.signingTime = signingTime;
	}
	
	@Length(min=0, max=255, message="签订地点长度必须介于 0 和 255 之间")
	public String getSigningPlace() {
		return signingPlace;
	}

	public void setSigningPlace(String signingPlace) {
		this.signingPlace = signingPlace;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=255, message="总价大写长度必须介于 0 和 255 之间")
	public String getAmountup() {
		return amountup;
	}

	public void setAmountup(String amountup) {
		this.amountup = amountup;
	}
	
	@Length(min=0, max=255, message="解决日期长度必须介于 0 和 255 之间")
	public String getSolveDay() {
		return solveDay;
	}

	public void setSolveDay(String solveDay) {
		this.solveDay = solveDay;
	}
	
	@Length(min=0, max=255, message="质保期长度必须介于 0 和 255 之间")
	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	
	@Length(min=0, max=255, message="交货时间长度必须介于 0 和 255 之间")
	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	@Length(min=0, max=255, message="交货方式长度必须介于 0 和 255 之间")
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
	@Length(min=0, max=255, message="异议天数长度必须介于 0 和 255 之间")
	public String getObjectionDay() {
		return objectionDay;
	}

	public void setObjectionDay(String objectionDay) {
		this.objectionDay = objectionDay;
	}
	
	@Length(min=0, max=255, message="异议回复天数长度必须介于 0 和 255 之间")
	public String getObjectionDayHf() {
		return objectionDayHf;
	}

	public void setObjectionDayHf(String objectionDayHf) {
		this.objectionDayHf = objectionDayHf;
	}
	
	@Length(min=0, max=255, message="发票交付天数长度必须介于 0 和 255 之间")
	public String getInvoiceDay() {
		return invoiceDay;
	}

	public void setInvoiceDay(String invoiceDay) {
		this.invoiceDay = invoiceDay;
	}
	
	@Length(min=0, max=255, message="违约金长度必须介于 0 和 255 之间")
	public String getFalsify() {
		return falsify;
	}

	public void setFalsify(String falsify) {
		this.falsify = falsify;
	}
	
	@Length(min=0, max=255, message="first_name长度必须介于 0 和 255 之间")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Length(min=0, max=255, message="first_place长度必须介于 0 和 255 之间")
	public String getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(String firstPlace) {
		this.firstPlace = firstPlace;
	}
	
	@Length(min=0, max=255, message="first_legal长度必须介于 0 和 255 之间")
	public String getFirstLegal() {
		return firstLegal;
	}

	public void setFirstLegal(String firstLegal) {
		this.firstLegal = firstLegal;
	}
	
	@Length(min=0, max=255, message="first_agent长度必须介于 0 和 255 之间")
	public String getFirstAgent() {
		return firstAgent;
	}

	public void setFirstAgent(String firstAgent) {
		this.firstAgent = firstAgent;
	}
	
	@Length(min=0, max=255, message="first_name_liaison长度必须介于 0 和 255 之间")
	public String getFirstNameLiaison() {
		return firstNameLiaison;
	}

	public void setFirstNameLiaison(String firstNameLiaison) {
		this.firstNameLiaison = firstNameLiaison;
	}
	
	@Length(min=0, max=255, message="first_name_phone长度必须介于 0 和 255 之间")
	public String getFirstNamePhone() {
		return firstNamePhone;
	}

	public void setFirstNamePhone(String firstNamePhone) {
		this.firstNamePhone = firstNamePhone;
	}
	
	@Length(min=0, max=255, message="first_bank长度必须介于 0 和 255 之间")
	public String getFirstBank() {
		return firstBank;
	}

	public void setFirstBank(String firstBank) {
		this.firstBank = firstBank;
	}
	
	@Length(min=0, max=255, message="first_bank_no长度必须介于 0 和 255 之间")
	public String getFirstBankNo() {
		return firstBankNo;
	}

	public void setFirstBankNo(String firstBankNo) {
		this.firstBankNo = firstBankNo;
	}
	
	@Length(min=0, max=255, message="first_duty长度必须介于 0 和 255 之间")
	public String getFirstDuty() {
		return firstDuty;
	}

	public void setFirstDuty(String firstDuty) {
		this.firstDuty = firstDuty;
	}
	
	@Length(min=0, max=255, message="first_zip长度必须介于 0 和 255 之间")
	public String getFirstZip() {
		return firstZip;
	}

	public void setFirstZip(String firstZip) {
		this.firstZip = firstZip;
	}
	
	@Length(min=0, max=255, message="first_tel长度必须介于 0 和 255 之间")
	public String getFirstTel() {
		return firstTel;
	}

	public void setFirstTel(String firstTel) {
		this.firstTel = firstTel;
	}
	
	@Length(min=0, max=255, message="first_fax长度必须介于 0 和 255 之间")
	public String getFirstFax() {
		return firstFax;
	}

	public void setFirstFax(String firstFax) {
		this.firstFax = firstFax;
	}
	
	@Length(min=0, max=255, message="乙方单位名称长度必须介于 0 和 255 之间")
	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Length(min=0, max=255, message="单位地址长度必须介于 0 和 255 之间")
	public String getSecondPlace() {
		return secondPlace;
	}

	public void setSecondPlace(String secondPlace) {
		this.secondPlace = secondPlace;
	}
	
	@Length(min=0, max=255, message="法人代表长度必须介于 0 和 255 之间")
	public String getSecondLegal() {
		return secondLegal;
	}

	public void setSecondLegal(String secondLegal) {
		this.secondLegal = secondLegal;
	}
	
	@Length(min=0, max=255, message="委托代理长度必须介于 0 和 255 之间")
	public String getSecondAgent() {
		return secondAgent;
	}

	public void setSecondAgent(String secondAgent) {
		this.secondAgent = secondAgent;
	}
	
	@Length(min=0, max=255, message="联系人长度必须介于 0 和 255 之间")
	public String getSecondNameLiaison() {
		return secondNameLiaison;
	}

	public void setSecondNameLiaison(String secondNameLiaison) {
		this.secondNameLiaison = secondNameLiaison;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getSecondNamePhone() {
		return secondNamePhone;
	}

	public void setSecondNamePhone(String secondNamePhone) {
		this.secondNamePhone = secondNamePhone;
	}
	
	@Length(min=0, max=255, message="开户银行长度必须介于 0 和 255 之间")
	public String getSecondBank() {
		return secondBank;
	}

	public void setSecondBank(String secondBank) {
		this.secondBank = secondBank;
	}
	
	@Length(min=0, max=255, message="账户长度必须介于 0 和 255 之间")
	public String getSecondBankNo() {
		return secondBankNo;
	}

	public void setSecondBankNo(String secondBankNo) {
		this.secondBankNo = secondBankNo;
	}
	
	@Length(min=0, max=255, message="税号长度必须介于 0 和 255 之间")
	public String getSecondDuty() {
		return secondDuty;
	}

	public void setSecondDuty(String secondDuty) {
		this.secondDuty = secondDuty;
	}
	
	@Length(min=0, max=255, message="邮编长度必须介于 0 和 255 之间")
	public String getSecondZip() {
		return secondZip;
	}

	public void setSecondZip(String secondZip) {
		this.secondZip = secondZip;
	}
	
	@Length(min=0, max=255, message="电话长度必须介于 0 和 255 之间")
	public String getSecondTel() {
		return secondTel;
	}

	public void setSecondTel(String secondTel) {
		this.secondTel = secondTel;
	}
	
	@Length(min=0, max=255, message="传真长度必须介于 0 和 255 之间")
	public String getSecondFax() {
		return secondFax;
	}

	public void setSecondFax(String secondFax) {
		this.secondFax = secondFax;
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
	
	@Length(min=0, max=255, message="field6长度必须介于 0 和 255 之间")
	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}
	
	@Length(min=0, max=255, message="field7长度必须介于 0 和 255 之间")
	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}
	
	@Length(min=0, max=255, message="field8长度必须介于 0 和 255 之间")
	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}
	
	@Length(min=0, max=255, message="field9长度必须介于 0 和 255 之间")
	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}
	
	@Length(min=0, max=255, message="field10长度必须介于 0 和 255 之间")
	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}
	
	public List<SwAgreementPay> getSwAgreementPayList() {
		return swAgreementPayList;
	}

	public void setSwAgreementPayList(List<SwAgreementPay> swAgreementPayList) {
		this.swAgreementPayList = swAgreementPayList;
	}
	public List<SwAgreementProduct> getSwAgreementProductList() {
		return swAgreementProductList;
	}

	public void setSwAgreementProductList(List<SwAgreementProduct> swAgreementProductList) {
		this.swAgreementProductList = swAgreementProductList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public String getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}

	public Date getHandledDate() {
		return handledDate;
	}

	public void setHandledDate(Date handledDate) {
		this.handledDate = handledDate;
	}
}