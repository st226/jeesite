/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.qmsupplier;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.eos.workflow.data.WFWorkItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 供应商管理Entity
 * @author suntao
 * @version 2020-04-18
 */
public class QmSupplier extends DataEntity<QmSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String state;		// state
	private String processinstid;		// processinstid
	private String supplierType;		// supplier_type
	private String supplierName;		// 供应商名称
	private String supplierNameSimple;		// 供应商简称
	private String supplierNature;		// 供应商性质
	private Double registeredCapital;		// 注册资金
	private String contacts;		// 联系人
	private String contactNumber;		// 联系电话
	private String mobilePhone;		// 手机
	private String fax;		// 传真
	private String mailbox;		// 邮箱
	private String address;		// 地址
	private String isImportant;		// 是否为关重件
	private String product;		// 产品名称
	private String assist;		// 外协军种
	private String expansion;		// 新增/扩项
	private String reasons;		// 添加原因
	private String applicantId;		// 申请人
	private String applicantName;		// 申请人名称
	private String legalName;		// 法人
	private String legalTel;		// 法人电话
	private String legalPhone;		// 移动电话
	private String employees;		// 员工总数
	private Date registrationTime;		// 注册时间
	private String registrationCapital;		// 注册地点
	private String mailingAddress;		// 通信地址
	private String postalCode;		// 邮编
	private String customer;		// 航天主要客户
	private String wbType;		// 外包产品应用型号类型
	private String wbName;		// 外包产品名称
	private String productScope;		// 外包产品范围
	private String bankName;		// 开户名称
	private String bankNumber;		// 开户账号
	private String bankLocal;		// 开户地址
	private String dutyno;		// 工商注册号
	private String file;		// file
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
	private String model;
	private String importance;
	private String evaluationLevel;
	private String scope;
	private String productType;
	private String productTypeName;
	private List<QmSupplierAdmittance> qmSupplierAdmittanceList = Lists.newArrayList();		// 子表列表
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public QmSupplier() {
		super();
	}

	public QmSupplier(String id){
		super(id);
	}

	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="processinstid长度必须介于 0 和 255 之间")
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=20, message="supplier_type长度必须介于 0 和 20 之间")
	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	
	@Length(min=0, max=255, message="供应商名称长度必须介于 0 和 255 之间")
	@ExcelField(title="供应商名称", align=2, sort=10)
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=255, message="供应商简称长度必须介于 0 和 255 之间")
	@ExcelField(title="供应商简称", align=2, sort=20)
	public String getSupplierNameSimple() {
		return supplierNameSimple;
	}

	public void setSupplierNameSimple(String supplierNameSimple) {
		this.supplierNameSimple = supplierNameSimple;
	}
	
	@Length(min=0, max=255, message="供应商性质长度必须介于 0 和 255 之间")
	@ExcelField(title="单位性质", align=2, sort=30,dictType="supplier_nature")
	public String getSupplierNature() {
		return supplierNature;
	}

	public void setSupplierNature(String supplierNature) {
		this.supplierNature = supplierNature;
	}
	
	@ExcelField(title="注册资金", align=2, sort=40)
	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	
	@Length(min=0, max=255, message="联系人长度必须介于 0 和 255 之间")
	@ExcelField(title="联系人", align=2, sort=50)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	@ExcelField(title="联系电话", align=2, sort=60)
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Length(min=0, max=255, message="手机长度必须介于 0 和 255 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Length(min=0, max=255, message="传真长度必须介于 0 和 255 之间")
	@ExcelField(title="传真", align=2, sort=80)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Length(min=0, max=255, message="邮箱长度必须介于 0 和 255 之间")
	@ExcelField(title="邮箱", align=2, sort=90)
	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	@ExcelField(title="地址", align=2, sort=100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="是否为关重件长度必须介于 0 和 255 之间")
	@ExcelField(title="是否关重件", align=2, sort=110,dictType="is_leaf")
	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}
	
	@Length(min=0, max=255, message="产品名称长度必须介于 0 和 255 之间")
	@ExcelField(title="产品名称", align=2, sort=22)
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	@Length(min=0, max=255, message="外协军种长度必须介于 0 和 255 之间")
	@ExcelField(title="外协军种", align=2, sort=130)
	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}
	
	@Length(min=0, max=255, message="类别长度必须介于 0 和 255 之间")
	@ExcelField(title="类别", align=2, sort=34,dictType="expansion")
	public String getExpansion() {
		return expansion;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}
	
	@Length(min=0, max=255, message="添加原因长度必须介于 0 和 255 之间")
	@ExcelField(title="添加原因", align=2, sort=150)
	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	
	@Length(min=0, max=255, message="申请人名称长度必须介于 0 和 255 之间")
	@ExcelField(title="申请人", align=2, sort=160)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	@Length(min=0, max=255, message="法人长度必须介于 0 和 255 之间")
	@ExcelField(title="法人", align=2, sort=170)
	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	@Length(min=0, max=255, message="法人电话长度必须介于 0 和 255 之间")
	@ExcelField(title="法人电话", align=2, sort=180)
	public String getLegalTel() {
		return legalTel;
	}

	public void setLegalTel(String legalTel) {
		this.legalTel = legalTel;
	}
	
	@Length(min=0, max=255, message="移动电话长度必须介于 0 和 255 之间")
	@ExcelField(title="法人移动电话", align=2, sort=190)
	public String getLegalPhone() {
		return legalPhone;
	}

	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}
	
	@Length(min=0, max=11, message="员工总数长度必须介于 0 和 11 之间")
	@ExcelField(title="员工总人数", align=2, sort=200)
	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="注册时间", align=2, sort=210)
	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	
	@ExcelField(title="注册地点", align=2, sort=220)
	public String getRegistrationCapital() {
		return registrationCapital;
	}

	public void setRegistrationCapital(String registrationCapital) {
		this.registrationCapital = registrationCapital;
	}
	
	@Length(min=0, max=255, message="通信地址长度必须介于 0 和 255 之间")
	@ExcelField(title="通信地址", align=2, sort=230)
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	@Length(min=0, max=255, message="邮编长度必须介于 0 和 255 之间")
	@ExcelField(title="邮编", align=2, sort=240)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Length(min=0, max=255, message="航天主要客户长度必须介于 0 和 255 之间")
	@ExcelField(title="航天主要客户", align=2, sort=250)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@Length(min=0, max=255, message="外包产品应用型号类型长度必须介于 0 和 255 之间")
	@ExcelField(title="外包产品应用型号类型", align=2, sort=260)
	public String getWbType() {
		return wbType;
	}

	public void setWbType(String wbType) {
		this.wbType = wbType;
	}
	
	@Length(min=0, max=255, message="外包产品名称长度必须介于 0 和 255 之间")
	@ExcelField(title="外包产品名称", align=2, sort=270)
	public String getWbName() {
		return wbName;
	}

	public void setWbName(String wbName) {
		this.wbName = wbName;
	}
	
	@Length(min=0, max=255, message="外包产品范围长度必须介于 0 和 255 之间")
	@ExcelField(title="外包产品范围长度", align=2, sort=280)
	public String getProductScope() {
		return productScope;
	}

	public void setProductScope(String productScope) {
		this.productScope = productScope;
	}
	
	@Length(min=0, max=255, message="开户名称长度必须介于 0 和 255 之间")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=0, max=255, message="开户账号长度必须介于 0 和 255 之间")
	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	@Length(min=0, max=255, message="开户地址长度必须介于 0 和 255 之间")
	public String getBankLocal() {
		return bankLocal;
	}

	public void setBankLocal(String bankLocal) {
		this.bankLocal = bankLocal;
	}
	
	@Length(min=0, max=255, message="工商注册号长度必须介于 0 和 255 之间")
	public String getDutyno() {
		return dutyno;
	}

	public void setDutyno(String dutyno) {
		this.dutyno = dutyno;
	}
	
	@Length(min=0, max=255, message="file长度必须介于 0 和 255 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	@ExcelField(title="备注", align=2, sort=36)
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="field2长度必须介于 0 和 255 之间")
	@ExcelField(title="排序", align=2, sort=38)
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=1000, message="field3长度必须介于 0 和 255 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=1000, message="field4长度必须介于 0 和 255 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=1000, message="field5长度必须介于 0 和 255 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	@Length(min=0, max=1000, message="field6长度必须介于 0 和 255 之间")
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
	
	public List<QmSupplierAdmittance> getQmSupplierAdmittanceList() {
		return qmSupplierAdmittanceList;
	}

	public void setQmSupplierAdmittanceList(List<QmSupplierAdmittance> qmSupplierAdmittanceList) {
		this.qmSupplierAdmittanceList = qmSupplierAdmittanceList;
	}

	 
	@ExcelField(title="规格型号", align=2, sort=22)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
    
	
	@ExcelField(title="重要程度", align=2, sort=24,dictType="supplier_importance")
	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	
	@ExcelField(title="评价等级", align=2, sort=26,dictType="evaluation_level")
	public String getEvaluationLevel() {
		return evaluationLevel;
	}

	public void setEvaluationLevel(String evaluationLevel) {
		this.evaluationLevel = evaluationLevel;
	}

	
	@ExcelField(title="业务范围考核", align=2, sort=32)
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@ExcelField(title="产品类别", align=2, sort=38)
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public List<Examine> getExamineList() {
		return examineList;
	}

	public void setExamineList(List<Examine> examineList) {
		this.examineList = examineList;
	}

	public WFWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WFWorkItem workItem) {
		this.workItem = workItem;
	}
}