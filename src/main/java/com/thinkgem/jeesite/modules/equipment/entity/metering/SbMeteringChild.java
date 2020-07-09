/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.metering;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计量管理Entity
 * @author suntao
 * @version 2020-01-01
 */
public class SbMeteringChild extends DataEntity<SbMeteringChild> {
	
	private static final long serialVersionUID = 1L;
	private SbMetering meteringId;		// 主表 父类
	private String equipmentId;		// 设备ID
	private String equipmentName;		// 设备名称
	private String equipmentType;		// 规格型号
	private String equipmentSbcode;		// 设备编号
	private String equipmentCccode;		// 出厂编号
	private String meteringState;		// 计量状态
	private String meteringType;		// 计量类型
	private String meteringTime;
	private Date measurement;		// 计量有效期
	private String ismeasurement;		// 是否计量
	private String reason;		// 原因
	private String isGood;		// 是否合格
	private Date inspectionDate;		// 检验日期
	private Date effectiveDate;		// 有效日期
	private String enclosure;		// 附件
	private Office office;		// 计量部门
	private User user;		// 计量人
	private String person;		// 责任人
	private String department;		// 责任部门
	private String departmentid;
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SbMeteringChild() {
		super();
	}

	public SbMeteringChild(String id){
		super(id);
	}

	public SbMeteringChild(SbMetering meteringId){
		this.meteringId = meteringId;
	}

	@Length(min=0, max=100, message="主表长度必须介于 0 和 100 之间")
	public SbMetering getMeteringId() {
		return meteringId;
	}

	public void setMeteringId(SbMetering meteringId) {
		this.meteringId = meteringId;
	}
	
	@Length(min=0, max=199, message="设备ID长度必须介于 0 和 199 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Length(min=0, max=199, message="设备名称长度必须介于 0 和 199 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=199, message="规格型号长度必须介于 0 和 199 之间")
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	@Length(min=0, max=199, message="设备编号长度必须介于 0 和 199 之间")
	public String getEquipmentSbcode() {
		return equipmentSbcode;
	}

	public void setEquipmentSbcode(String equipmentSbcode) {
		this.equipmentSbcode = equipmentSbcode;
	}
	
	@Length(min=0, max=19, message="出厂编号长度必须介于 0 和 19 之间")
	public String getEquipmentCccode() {
		return equipmentCccode;
	}

	public void setEquipmentCccode(String equipmentCccode) {
		this.equipmentCccode = equipmentCccode;
	}
	
	@Length(min=0, max=10, message="计量状态长度必须介于 0 和 10 之间")
	public String getMeteringState() {
		return meteringState;
	}

	public void setMeteringState(String meteringState) {
		this.meteringState = meteringState;
	}
	
	@Length(min=0, max=19, message="计量类型长度必须介于 0 和 19 之间")
	public String getMeteringType() {
		return meteringType;
	}

	public void setMeteringType(String meteringType) {
		this.meteringType = meteringType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Date measurement) {
		this.measurement = measurement;
	}
	
	@Length(min=0, max=10, message="是否计量长度必须介于 0 和 10 之间")
	public String getIsmeasurement() {
		return ismeasurement;
	}

	public void setIsmeasurement(String ismeasurement) {
		this.ismeasurement = ismeasurement;
	}
	
	@Length(min=0, max=200, message="原因长度必须介于 0 和 200 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=200, message="是否合格长度必须介于 0 和 200 之间")
	public String getIsGood() {
		return isGood;
	}

	public void setIsGood(String isGood) {
		this.isGood = isGood;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=200, message="责任人长度必须介于 0 和 200 之间")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	@Length(min=0, max=200, message="责任部门长度必须介于 0 和 200 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=200, message="field1长度必须介于 0 和 200 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=200, message="field2长度必须介于 0 和 200 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=200, message="field3长度必须介于 0 和 200 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=200, message="field4长度必须介于 0 和 200 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=200, message="field5长度必须介于 0 和 200 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getMeteringTime() {
		return meteringTime;
	}

	public void setMeteringTime(String meteringTime) {
		this.meteringTime = meteringTime;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	
}