/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.receive;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仪器设备开箱验收Entity
 * @author suntao
 * @version 2020-04-11
 */
public class SwReceiveEquipment extends DataEntity<SwReceiveEquipment> {
	
	private static final long serialVersionUID = 1L;
	private SwReceive receiveId;		// receive_id 父类
	private String equipmentName;		// 设备名称
	private String equipmentId;		// equipment_id
	private String equipmentModel;		// 型号
	private String equipmentNorms;		// 规格
	private String equipmentValue;		// 原值
	private String equipmentPower;		// 功率
	private String equipmentFactoryNumber;		// 出厂编号
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	private String  zccode;
	private int  amount;
	private double  unitprice;
	private double  price;
	private String  team;
	private String  teamname;
	private String  usepeople;
	private String  usepeoplename;
	private String  local;
	private String  isMetering;
	private Date  meteringDate;
	private String  meteringType;
	private String  meteringTime;
	private String  fundingsource;
	private String  fsType;
	private String  fsTypeName;
	private String  sbType;
	private String  sbTypeName;
	private String  state;
	private String  outtime;
	private String  cometime;
	private String  isAgreement;
	private String  isAppearance;
	private String  isNorms;
	private String  isModel;
	private String made ;
	private String rfiles ;
	private String cfiles ;

	
	public SwReceiveEquipment() {
		super();
	}

	public SwReceiveEquipment(String id){
		super(id);
	}

	public SwReceiveEquipment(SwReceive receiveId){
		this.receiveId = receiveId;
	}

	@Length(min=0, max=255, message="receive_id长度必须介于 0 和 255 之间")
	public SwReceive getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(SwReceive receiveId) {
		this.receiveId = receiveId;
	}
	
	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=255, message="equipment_id长度必须介于 0 和 255 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Length(min=0, max=255, message="型号长度必须介于 0 和 255 之间")
	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	
	@Length(min=0, max=255, message="规格长度必须介于 0 和 255 之间")
	public String getEquipmentNorms() {
		return equipmentNorms;
	}

	public void setEquipmentNorms(String equipmentNorms) {
		this.equipmentNorms = equipmentNorms;
	}
	
	@Length(min=0, max=255, message="原值长度必须介于 0 和 255 之间")
	public String getEquipmentValue() {
		return equipmentValue;
	}

	public void setEquipmentValue(String equipmentValue) {
		this.equipmentValue = equipmentValue;
	}
	
	@Length(min=0, max=255, message="功率长度必须介于 0 和 255 之间")
	public String getEquipmentPower() {
		return equipmentPower;
	}

	public void setEquipmentPower(String equipmentPower) {
		this.equipmentPower = equipmentPower;
	}
	
	@Length(min=0, max=255, message="出厂编号长度必须介于 0 和 255 之间")
	public String getEquipmentFactoryNumber() {
		return equipmentFactoryNumber;
	}

	public void setEquipmentFactoryNumber(String equipmentFactoryNumber) {
		this.equipmentFactoryNumber = equipmentFactoryNumber;
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

	public String getZccode() {
		return zccode;
	}

	public void setZccode(String zccode) {
		this.zccode = zccode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getUsepeople() {
		return usepeople;
	}

	public void setUsepeople(String usepeople) {
		this.usepeople = usepeople;
	}

	public String getUsepeoplename() {
		return usepeoplename;
	}

	public void setUsepeoplename(String usepeoplename) {
		this.usepeoplename = usepeoplename;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getIsMetering() {
		return isMetering;
	}

	public void setIsMetering(String isMetering) {
		this.isMetering = isMetering;
	}

	public String getMeteringType() {
		return meteringType;
	}

	public void setMeteringType(String meteringType) {
		this.meteringType = meteringType;
	}

	public Date getMeteringDate() {
		return meteringDate;
	}

	public void setMeteringDate(Date meteringDate) {
		this.meteringDate = meteringDate;
	}

	public String getMeteringTime() {
		return meteringTime;
	}

	public void setMeteringTime(String meteringTime) {
		this.meteringTime = meteringTime;
	}

	public String getFundingsource() {
		return fundingsource;
	}

	public void setFundingsource(String fundingsource) {
		this.fundingsource = fundingsource;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public String getFsTypeName() {
		return fsTypeName;
	}

	public void setFsTypeName(String fsTypeName) {
		this.fsTypeName = fsTypeName;
	}

	public String getSbType() {
		return sbType;
	}

	public void setSbType(String sbType) {
		this.sbType = sbType;
	}

	public String getSbTypeName() {
		return sbTypeName;
	}

	public void setSbTypeName(String sbTypeName) {
		this.sbTypeName = sbTypeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public String getIsAgreement() {
		return isAgreement;
	}

	public void setIsAgreement(String isAgreement) {
		this.isAgreement = isAgreement;
	}

	public String getIsAppearance() {
		return isAppearance;
	}

	public void setIsAppearance(String isAppearance) {
		this.isAppearance = isAppearance;
	}

	public String getIsNorms() {
		return isNorms;
	}

	public void setIsNorms(String isNorms) {
		this.isNorms = isNorms;
	}

	public String getIsModel() {
		return isModel;
	}

	public void setIsModel(String isModel) {
		this.isModel = isModel;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getCometime() {
		return cometime;
	}

	public void setCometime(String cometime) {
		this.cometime = cometime;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	/**
	 * @return the rfiles
	 */
	public String getRfiles() {
		return rfiles;
	}

	/**
	 * @param rfiles the rfiles to set
	 */
	public void setRfiles(String rfiles) {
		this.rfiles = rfiles;
	}

	/**
	 * @return the cfiles
	 */
	public String getCfiles() {
		return cfiles;
	}

	/**
	 * @param cfiles the cfiles to set
	 */
	public void setCfiles(String cfiles) {
		this.cfiles = cfiles;
	}
	
}