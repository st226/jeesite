/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.purchase;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementProduct;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仪器申购单Entity
 * @author suntao
 * @version 2020-03-21
 */
public class SwPurchase extends DataEntity<SwPurchase> {
	
	private static final long serialVersionUID = 1L;
	private String processinstid;		// 流程号
	private String state;		// 状态
	private String code ;
	private String orderId;	
	private Office office;		// 申请单位
	private String officeName;		// 申请单位
	private User user;		// user_id
	private String userName;		// 申请人
	private String name;		// 名称
	private String amount;		// 数量
	private String specifications;		// 型号规格、技术条件
	private String made;		// 生产单位（国别）
	private String unitPrice;		// 单价
	private String funds;		// 资金来源
	private String file;		// 资金来源
	private String reason;		// 申请理由及用途
	private String applicant;		// 申请人
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private List<SwAgreementProduct> swAgreementProductList = Lists.newArrayList();		
	
	public SwPurchase() {
		super();
	}

	public SwPurchase(String id){
		super(id);
	}

	@Length(min=0, max=255, message="流程号长度必须介于 0 和 255 之间")
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="申请单位长度必须介于 0 和 255 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=255, message="型号规格、技术条件长度必须介于 0 和 255 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=0, max=255, message="生产单位（国别）长度必须介于 0 和 255 之间")
	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Length(min=0, max=255, message="资金来源长度必须介于 0 和 255 之间")
	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds;
	}
	
	@Length(min=0, max=255, message="申请理由及用途长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
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

	public List<SwAgreementProduct> getSwAgreementProductList() {
		return swAgreementProductList;
	}

	public void setSwAgreementProductList(List<SwAgreementProduct> swAgreementProductList) {
		this.swAgreementProductList = swAgreementProductList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}