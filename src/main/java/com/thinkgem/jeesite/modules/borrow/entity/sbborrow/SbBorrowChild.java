/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.entity.sbborrow;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 设备借用Entity
 * @author suntao
 * @version 2019-12-26
 */
public class SbBorrowChild extends DataEntity<SbBorrowChild> {
	
	private static final long serialVersionUID = 1L;
	private SbBorrow borrowId;		// 主表 父类
	private String equipmentId;		// 设备ID
	private String equipmentName;		// 设备名称
	private String equipmentType;		// 规格型号
	private String equipmentSbcode;		// 设备编号
	private String equipmentCccode;		// 出厂编号
	private String borrowState;		// 借用状态
	private String borrowType;		// 借用类型
	private String ismeasurement;		// 是否计量
	private Date measurement;		// 计量有效期
	private String enclosure;		// 附件
	private String frequency;		// 借用次数
	private Date statrDate;		// 借用时间
	private Date endDate;		// 归还时间
	private Office office;		// 借用部门
	private User user;		// 借用人
	private String isGood;		// 是否正常
	private String reason;		// reason
	private String endIsGood;		// 结束是否正常
	private String endReason;		// end_reason
	private String manage;		// 开始管理员
	private String endManage;		// 结束管理员
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SbBorrowChild() {
		super();
	}

	public SbBorrowChild(String id){
		super(id);
	}

	public SbBorrowChild(SbBorrow borrowId){
		this.borrowId = borrowId;
	}

	@Length(min=0, max=100, message="主表长度必须介于 0 和 100 之间")
	public SbBorrow getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(SbBorrow borrowId) {
		this.borrowId = borrowId;
	}
	
	@Length(min=0, max=199, message="设备ID长度必须介于 0 和 199 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Length(min=0, max=199, message="设备名称长度必须介于 0 和 199 之间")
	@ExcelField(title="设备名称", align=2, sort=10)
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=199, message="规格型号长度必须介于 0 和 199 之间")
	@ExcelField(title="规格型号", align=2, sort=30)
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	@Length(min=0, max=199, message="设备编号长度必须介于 0 和 199 之间")
	@ExcelField(title="设备编号", align=2, sort=20)
	public String getEquipmentSbcode() {
		return equipmentSbcode;
	}

	public void setEquipmentSbcode(String equipmentSbcode) {
		this.equipmentSbcode = equipmentSbcode;
	}
	
	@Length(min=0, max=19, message="出厂编号长度必须介于 0 和 19 之间")
	@ExcelField(title="出厂编号", align=2, sort=40)
	public String getEquipmentCccode() {
		return equipmentCccode;
	}

	public void setEquipmentCccode(String equipmentCccode) {
		this.equipmentCccode = equipmentCccode;
	}
	
	@Length(min=0, max=10, message="借用状态长度必须介于 0 和 10 之间")
	@ExcelField(title="借用状态", align=2, sort=70, dictType="bstate")
	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}
	
	@Length(min=0, max=19, message="借用类型长度必须介于 0 和 19 之间")
	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	
	@Length(min=0, max=10, message="是否计量长度必须介于 0 和 10 之间")
	@ExcelField(title="是否计量", align=2, sort=80)
	public String getIsmeasurement() {
		return ismeasurement;
	}

	public void setIsmeasurement(String ismeasurement) {
		this.ismeasurement = ismeasurement;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计量时间", align=2, sort=90)
	public Date getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Date measurement) {
		this.measurement = measurement;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	
	@Length(min=0, max=11, message="借用次数长度必须介于 0 和 11 之间")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="借用日期", align=2, sort=100)
	public Date getStatrDate() {
		return statrDate;
	}

	public void setStatrDate(Date statrDate) {
		this.statrDate = statrDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="归还日期", align=2, sort=100)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@ExcelField(title="借用部门", align=2, sort=50)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@ExcelField(title="借用人", align=2, sort=60,value="user")
	public User getUser() {
		return user;
	}
	

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=200, message="是否正常长度必须介于 0 和 200 之间")
	public String getIsGood() {
		return isGood;
	}

	public void setIsGood(String isGood) {
		this.isGood = isGood;
	}
	
	@Length(min=0, max=200, message="reason长度必须介于 0 和 200 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=200, message="结束是否正常长度必须介于 0 和 200 之间")
	public String getEndIsGood() {
		return endIsGood;
	}

	public void setEndIsGood(String endIsGood) {
		this.endIsGood = endIsGood;
	}
	
	@Length(min=0, max=200, message="end_reason长度必须介于 0 和 200 之间")
	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}
	
	@Length(min=0, max=200, message="开始管理员长度必须介于 0 和 200 之间")
	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}
	
	@Length(min=0, max=200, message="结束管理员长度必须介于 0 和 200 之间")
	public String getEndManage() {
		return endManage;
	}

	public void setEndManage(String endManage) {
		this.endManage = endManage;
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
	
}