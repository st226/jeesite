/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.scrap;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备仪器报废（闲置）申请Entity
 * @author suntao
 * @version 2020-03-23
 */
public class SbEquipmentScrap extends DataEntity<SbEquipmentScrap> {
	
	private static final long serialVersionUID = 1L;
	private String processinstid;		// 流程号
	private String state;		// 状态
	private Office office;		// 申请部门
	private String officeName;		// 申请部门
	private String equipmentId;		// 设备ID
	private String equipmentName;		// 设备仪器名称
	private String equipmentMade;		// 国别厂家
	private String equipmentCode;		// 设备仪器编号
	private Date equipmentOuttime;		// 购置年月
	private Date equipmentBuytime;		// 出厂年月
	private String equipmentYprice;		// 原值（万元）
	private String equipmentJprice;		// 净值（万元）
	private String equipmentType;		// 型号
	private String equipmentTeam;		// 使用单位
	private String equipmentTeamName;		// 使用单位
	private String equipmentNorms;		// 规格
	private Date equipmentStarttime;		// 启用年月
	private String equipmentYears;		// 已使用年限
	private String reason;		// 报废原因
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SbEquipmentScrap() {
		super();
	}

	public SbEquipmentScrap(String id){
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
	
	@Length(min=0, max=255, message="申请部门长度必须介于 0 和 255 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@Length(min=0, max=255, message="设备ID长度必须介于 0 和 255 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Length(min=0, max=255, message="设备仪器名称长度必须介于 0 和 255 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=255, message="国别厂家长度必须介于 0 和 255 之间")
	public String getEquipmentMade() {
		return equipmentMade;
	}

	public void setEquipmentMade(String equipmentMade) {
		this.equipmentMade = equipmentMade;
	}
	
	@Length(min=0, max=255, message="设备仪器编号长度必须介于 0 和 255 之间")
	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEquipmentOuttime() {
		return equipmentOuttime;
	}

	public void setEquipmentOuttime(Date equipmentOuttime) {
		this.equipmentOuttime = equipmentOuttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEquipmentBuytime() {
		return equipmentBuytime;
	}

	public void setEquipmentBuytime(Date equipmentBuytime) {
		this.equipmentBuytime = equipmentBuytime;
	}
	
	public String getEquipmentYprice() {
		return equipmentYprice;
	}

	public void setEquipmentYprice(String equipmentYprice) {
		this.equipmentYprice = equipmentYprice;
	}
	
	public String getEquipmentJprice() {
		return equipmentJprice;
	}

	public void setEquipmentJprice(String equipmentJprice) {
		this.equipmentJprice = equipmentJprice;
	}
	
	@Length(min=0, max=255, message="型号长度必须介于 0 和 255 之间")
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	@Length(min=0, max=255, message="使用单位长度必须介于 0 和 255 之间")
	public String getEquipmentTeam() {
		return equipmentTeam;
	}

	public void setEquipmentTeam(String equipmentTeam) {
		this.equipmentTeam = equipmentTeam;
	}
	
	@Length(min=0, max=255, message="使用单位长度必须介于 0 和 255 之间")
	public String getEquipmentTeamName() {
		return equipmentTeamName;
	}

	public void setEquipmentTeamName(String equipmentTeamName) {
		this.equipmentTeamName = equipmentTeamName;
	}
	
	@Length(min=0, max=255, message="规格长度必须介于 0 和 255 之间")
	public String getEquipmentNorms() {
		return equipmentNorms;
	}

	public void setEquipmentNorms(String equipmentNorms) {
		this.equipmentNorms = equipmentNorms;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEquipmentStarttime() {
		return equipmentStarttime;
	}

	public void setEquipmentStarttime(Date equipmentStarttime) {
		this.equipmentStarttime = equipmentStarttime;
	}
	
	@Length(min=0, max=255, message="已使用年限长度必须介于 0 和 255 之间")
	public String getEquipmentYears() {
		return equipmentYears;
	}

	public void setEquipmentYears(String equipmentYears) {
		this.equipmentYears = equipmentYears;
	}
	
	@Length(min=0, max=255, message="报废原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	
}