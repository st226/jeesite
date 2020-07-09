/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.order;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.equipment.entity.repair.SbEquipmentRepair;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 采购任务Entity
 * @author suntao
 * @version 2020-04-02
 */
public class SwOrder extends DataEntity<SwOrder> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编号
	private String name;		// 名称
	private String state;		// 状态
	private String type;		// 类型
	private String typeOrder;		// 类型
	private String projectType;		// 类型
	private User user;		// 采购人
	private String userName;		// 采购人
	private String dyuserId;		// dyuser_id
	private String field1;		// field1
	private String dyuserName;		// dyuser_name
	private String field2;		// field2
	private String field3;		// field3
	private Double amountYs;		// amount_ys
	private Double amount;		// amount
	private Date gmDate;		// field1_date
	private String field4;		// field4
	private String field5;		// field5
	private String field1State;		// field1_state
	private String field1Id;		// field1_id
	private String field1Text;		// field1_text
	private Date field1Date;		// field1_date
	private Date field1DateEnd ;
	private String field2State;		// field2_state
	private String field2Id;		// field2_id
	private String field2Text;		// field2_text
	private Date field2Date;		// field2_date
	private Date field2DateEnd ;
	private String field3State;		// field3_state
	private String field3Id;		// field3_id
	private String field3Text;		// field3_text
	private Date field3Date;		// field3_date
	private Date field3DateEnd ;
	private String field4State;		// field4_state
	private String field4Id;		// field4_id
	private String field4Text;		// field4_text
	private Date field4Date;		// field4_date
	private Date field4DateEnd ;
	private String field5State;		// field5_state
	private String field5Id;		// field5_id
	private String field5Text;		// field5_text
	private Date field5Date;		// field5_date
	private Date field5DateEnd ;
	private String field6State;		// field6_state
	private String field6Id;		// field6_id
	private String field6Text;		// field6_text
	private Date field6Date;		// field6_date
	private Date field6DateEnd ;
	private String field7State;		// field7_state
	private String field7Id;		// field7_id
	private String field7Text;		// field7_text
	private Date field7Date;		// field7_date
	private Date field7DateEnd ;
	private String field8State;		// field8_state
	private String field8Id;		// field8_id
	private String field8Text;		// field8_text
	private Date field8Date;		// field8_date
	private Date field8DateEnd ;
	private String field9State;		// field9_state
	private String field9Id;		// field9_id
	private String field9Text;		// field9_text
	private Date field9Date;		// field9_date
	private Date field9DateEnd ;
	private String field10State;		// field10_state
	private String field10Id;		// field10_id
	private String field10Text;		// field10_text
	private Date field10Date;		// field10_date
	private Date field10DateEnd ;
	private String field11State;		// field11_state
	private String field11Id;		// field11_id
	private String field11Text;		// field11_text
	private Date field11Date;		// field11_date
	private Date field11DateEnd ;
	private String projectClass ;
	private int count ;
	private int countYj ;
	private String identification ;
	private List<SwPay> swPayList ;
	
	private List<SwProduct> swProductList ;
	private List<SbEquipmentRepair> sbEquipmentRepairList ;
	
	
	public SwOrder() {
		super();
	}

	public SwOrder(String id){
		super(id);
	}

	@Length(min=0, max=255, message="编号长度必须介于 0 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=255, message="采购人长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="dyuser_id长度必须介于 0 和 255 之间")
	public String getDyuserId() {
		return dyuserId;
	}

	public void setDyuserId(String dyuserId) {
		this.dyuserId = dyuserId;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="dyuser_name长度必须介于 0 和 255 之间")
	public String getDyuserName() {
		return dyuserName;
	}

	public void setDyuserName(String dyuserName) {
		this.dyuserName = dyuserName;
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
	
	public Double getAmountYs() {
		return amountYs;
	}

	public void setAmountYs(Double amountYs) {
		this.amountYs = amountYs;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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
	
	@Length(min=0, max=255, message="field1_state长度必须介于 0 和 255 之间")
	public String getField1State() {
		return field1State;
	}

	public void setField1State(String field1State) {
		this.field1State = field1State;
	}
	
	@Length(min=0, max=255, message="field1_id长度必须介于 0 和 255 之间")
	public String getField1Id() {
		return field1Id;
	}

	public void setField1Id(String field1Id) {
		this.field1Id = field1Id;
	}
	
	@Length(min=0, max=255, message="field1_text长度必须介于 0 和 255 之间")
	public String getField1Text() {
		return field1Text;
	}

	public void setField1Text(String field1Text) {
		this.field1Text = field1Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField1Date() {
		return field1Date;
	}

	public void setField1Date(Date field1Date) {
		this.field1Date = field1Date;
	}
	
	@Length(min=0, max=255, message="field2_state长度必须介于 0 和 255 之间")
	public String getField2State() {
		return field2State;
	}

	public void setField2State(String field2State) {
		this.field2State = field2State;
	}
	
	@Length(min=0, max=255, message="field2_id长度必须介于 0 和 255 之间")
	public String getField2Id() {
		return field2Id;
	}

	public void setField2Id(String field2Id) {
		this.field2Id = field2Id;
	}
	
	@Length(min=0, max=255, message="field2_text长度必须介于 0 和 255 之间")
	public String getField2Text() {
		return field2Text;
	}

	public void setField2Text(String field2Text) {
		this.field2Text = field2Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField2Date() {
		return field2Date;
	}

	public void setField2Date(Date field2Date) {
		this.field2Date = field2Date;
	}
	
	@Length(min=0, max=255, message="field3_state长度必须介于 0 和 255 之间")
	public String getField3State() {
		return field3State;
	}

	public void setField3State(String field3State) {
		this.field3State = field3State;
	}
	
	@Length(min=0, max=255, message="field3_id长度必须介于 0 和 255 之间")
	public String getField3Id() {
		return field3Id;
	}

	public void setField3Id(String field3Id) {
		this.field3Id = field3Id;
	}
	
	@Length(min=0, max=255, message="field3_text长度必须介于 0 和 255 之间")
	public String getField3Text() {
		return field3Text;
	}

	public void setField3Text(String field3Text) {
		this.field3Text = field3Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField3Date() {
		return field3Date;
	}

	public void setField3Date(Date field3Date) {
		this.field3Date = field3Date;
	}
	
	@Length(min=0, max=255, message="field4_state长度必须介于 0 和 255 之间")
	public String getField4State() {
		return field4State;
	}

	public void setField4State(String field4State) {
		this.field4State = field4State;
	}
	
	@Length(min=0, max=255, message="field4_id长度必须介于 0 和 255 之间")
	public String getField4Id() {
		return field4Id;
	}

	public void setField4Id(String field4Id) {
		this.field4Id = field4Id;
	}
	
	@Length(min=0, max=255, message="field4_text长度必须介于 0 和 255 之间")
	public String getField4Text() {
		return field4Text;
	}

	public void setField4Text(String field4Text) {
		this.field4Text = field4Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField4Date() {
		return field4Date;
	}

	public void setField4Date(Date field4Date) {
		this.field4Date = field4Date;
	}
	
	@Length(min=0, max=255, message="field5_state长度必须介于 0 和 255 之间")
	public String getField5State() {
		return field5State;
	}

	public void setField5State(String field5State) {
		this.field5State = field5State;
	}
	
	@Length(min=0, max=255, message="field5_id长度必须介于 0 和 255 之间")
	public String getField5Id() {
		return field5Id;
	}

	public void setField5Id(String field5Id) {
		this.field5Id = field5Id;
	}
	
	@Length(min=0, max=255, message="field5_text长度必须介于 0 和 255 之间")
	public String getField5Text() {
		return field5Text;
	}

	public void setField5Text(String field5Text) {
		this.field5Text = field5Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField5Date() {
		return field5Date;
	}

	public void setField5Date(Date field5Date) {
		this.field5Date = field5Date;
	}
	
	@Length(min=0, max=255, message="field6_state长度必须介于 0 和 255 之间")
	public String getField6State() {
		return field6State;
	}

	public void setField6State(String field6State) {
		this.field6State = field6State;
	}
	
	@Length(min=0, max=255, message="field6_id长度必须介于 0 和 255 之间")
	public String getField6Id() {
		return field6Id;
	}

	public void setField6Id(String field6Id) {
		this.field6Id = field6Id;
	}
	
	@Length(min=0, max=255, message="field6_text长度必须介于 0 和 255 之间")
	public String getField6Text() {
		return field6Text;
	}

	public void setField6Text(String field6Text) {
		this.field6Text = field6Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField6Date() {
		return field6Date;
	}

	public void setField6Date(Date field6Date) {
		this.field6Date = field6Date;
	}
	
	@Length(min=0, max=255, message="field7_state长度必须介于 0 和 255 之间")
	public String getField7State() {
		return field7State;
	}

	public void setField7State(String field7State) {
		this.field7State = field7State;
	}
	
	@Length(min=0, max=255, message="field7_id长度必须介于 0 和 255 之间")
	public String getField7Id() {
		return field7Id;
	}

	public void setField7Id(String field7Id) {
		this.field7Id = field7Id;
	}
	
	@Length(min=0, max=255, message="field7_text长度必须介于 0 和 255 之间")
	public String getField7Text() {
		return field7Text;
	}

	public void setField7Text(String field7Text) {
		this.field7Text = field7Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField7Date() {
		return field7Date;
	}

	public void setField7Date(Date field7Date) {
		this.field7Date = field7Date;
	}
	
	@Length(min=0, max=255, message="field8_state长度必须介于 0 和 255 之间")
	public String getField8State() {
		return field8State;
	}

	public void setField8State(String field8State) {
		this.field8State = field8State;
	}
	
	@Length(min=0, max=255, message="field8_id长度必须介于 0 和 255 之间")
	public String getField8Id() {
		return field8Id;
	}

	public void setField8Id(String field8Id) {
		this.field8Id = field8Id;
	}
	
	@Length(min=0, max=255, message="field8_text长度必须介于 0 和 255 之间")
	public String getField8Text() {
		return field8Text;
	}

	public void setField8Text(String field8Text) {
		this.field8Text = field8Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField8Date() {
		return field8Date;
	}

	public void setField8Date(Date field8Date) {
		this.field8Date = field8Date;
	}
	
	@Length(min=0, max=255, message="field9_state长度必须介于 0 和 255 之间")
	public String getField9State() {
		return field9State;
	}

	public void setField9State(String field9State) {
		this.field9State = field9State;
	}
	
	@Length(min=0, max=255, message="field9_id长度必须介于 0 和 255 之间")
	public String getField9Id() {
		return field9Id;
	}

	public void setField9Id(String field9Id) {
		this.field9Id = field9Id;
	}
	
	@Length(min=0, max=255, message="field9_text长度必须介于 0 和 255 之间")
	public String getField9Text() {
		return field9Text;
	}

	public void setField9Text(String field9Text) {
		this.field9Text = field9Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField9Date() {
		return field9Date;
	}

	public void setField9Date(Date field9Date) {
		this.field9Date = field9Date;
	}
	
	@Length(min=0, max=255, message="field10_state长度必须介于 0 和 255 之间")
	public String getField10State() {
		return field10State;
	}

	public void setField10State(String field10State) {
		this.field10State = field10State;
	}
	
	@Length(min=0, max=255, message="field10_id长度必须介于 0 和 255 之间")
	public String getField10Id() {
		return field10Id;
	}

	public void setField10Id(String field10Id) {
		this.field10Id = field10Id;
	}
	
	@Length(min=0, max=255, message="field10_text长度必须介于 0 和 255 之间")
	public String getField10Text() {
		return field10Text;
	}

	public void setField10Text(String field10Text) {
		this.field10Text = field10Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField10Date() {
		return field10Date;
	}

	public void setField10Date(Date field10Date) {
		this.field10Date = field10Date;
	}
	
	@Length(min=0, max=255, message="field11_state长度必须介于 0 和 255 之间")
	public String getField11State() {
		return field11State;
	}

	public void setField11State(String field11State) {
		this.field11State = field11State;
	}
	
	@Length(min=0, max=255, message="field11_id长度必须介于 0 和 255 之间")
	public String getField11Id() {
		return field11Id;
	}

	public void setField11Id(String field11Id) {
		this.field11Id = field11Id;
	}
	
	@Length(min=0, max=255, message="field11_text长度必须介于 0 和 255 之间")
	public String getField11Text() {
		return field11Text;
	}

	public void setField11Text(String field11Text) {
		this.field11Text = field11Text;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getField11Date() {
		return field11Date;
	}

	public void setField11Date(Date field11Date) {
		this.field11Date = field11Date;
	}

	public List<SwProduct> getSwProductList() {
		return swProductList;
	}

	public void setSwProductList(List<SwProduct> swProductList) {
		this.swProductList = swProductList;
	}

	public Date getGmDate() {
		return gmDate;
	}

	public void setGmDate(Date gmDate) {
		this.gmDate = gmDate;
	}

	public Date getField1DateEnd() {
		return field1DateEnd;
	}

	public void setField1DateEnd(Date field1DateEnd) {
		this.field1DateEnd = field1DateEnd;
	}

	public Date getField11DateEnd() {
		return field11DateEnd;
	}

	public void setField11DateEnd(Date field11DateEnd) {
		this.field11DateEnd = field11DateEnd;
	}

	public Date getField10DateEnd() {
		return field10DateEnd;
	}

	public void setField10DateEnd(Date field10DateEnd) {
		this.field10DateEnd = field10DateEnd;
	}

	public Date getField9DateEnd() {
		return field9DateEnd;
	}

	public void setField9DateEnd(Date field9DateEnd) {
		this.field9DateEnd = field9DateEnd;
	}

	public Date getField8DateEnd() {
		return field8DateEnd;
	}

	public void setField8DateEnd(Date field8DateEnd) {
		this.field8DateEnd = field8DateEnd;
	}

	public Date getField7DateEnd() {
		return field7DateEnd;
	}

	public void setField7DateEnd(Date field7DateEnd) {
		this.field7DateEnd = field7DateEnd;
	}

	public Date getField6DateEnd() {
		return field6DateEnd;
	}

	public void setField6DateEnd(Date field6DateEnd) {
		this.field6DateEnd = field6DateEnd;
	}

	public Date getField5DateEnd() {
		return field5DateEnd;
	}

	public void setField5DateEnd(Date field5DateEnd) {
		this.field5DateEnd = field5DateEnd;
	}

	public Date getField4DateEnd() {
		return field4DateEnd;
	}

	public void setField4DateEnd(Date field4DateEnd) {
		this.field4DateEnd = field4DateEnd;
	}

	public Date getField3DateEnd() {
		return field3DateEnd;
	}

	public void setField3DateEnd(Date field3DateEnd) {
		this.field3DateEnd = field3DateEnd;
	}

	public Date getField2DateEnd() {
		return field2DateEnd;
	}

	public void setField2DateEnd(Date field2DateEnd) {
		this.field2DateEnd = field2DateEnd;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountYj() {
		return countYj;
	}

	public void setCountYj(int countYj) {
		this.countYj = countYj;
	}

	public List<SwPay> getSwPayList() {
		return swPayList;
	}

	public void setSwPayList(List<SwPay> swPayList) {
		this.swPayList = swPayList;
	}

	public String getTypeOrder() {
		return typeOrder;
	}

	public void setTypeOrder(String typeOrder) {
		this.typeOrder = typeOrder;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectClass() {
		return projectClass;
	}

	public void setProjectClass(String projectClass) {
		this.projectClass = projectClass;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}

	/**
	 * @return the sbEquipmentRepairList
	 */
	public List<SbEquipmentRepair> getSbEquipmentRepairList() {
		return sbEquipmentRepairList;
	}

	/**
	 * @param sbEquipmentRepairList the sbEquipmentRepairList to set
	 */
	public void setSbEquipmentRepairList(List<SbEquipmentRepair> sbEquipmentRepairList) {
		this.sbEquipmentRepairList = sbEquipmentRepairList;
	}
	
}