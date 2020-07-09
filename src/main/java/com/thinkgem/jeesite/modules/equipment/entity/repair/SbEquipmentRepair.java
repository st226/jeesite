/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.repair;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备维修申请Entity
 * @author suntao
 * @version 2020-03-23
 */
public class SbEquipmentRepair extends DataEntity<SbEquipmentRepair> {
	
	private static final long serialVersionUID = 1L;
	private String processinstid;		// 流程号
	private String state;		// 状态
	private String orderId;		// 状态
	private Office office;		// 申请部门
	private String officeName;		// 申请部门
	private String equipmentId;		// 设备ID
	private String equipmentName;		// 设备名称
	private String equipmentAmount;		// 设备数量
	private String equipmentType;		// 型号规格
	private String equipmentMade;		// 生产厂家
	private String equipmentBudgt;		// 维修预算费用
	private String equipmentFault;		// 设备故障描述
	private User user;		// user_id
	private String userName;		// 申请人
	private String userPhone;		// 联系电话
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public SbEquipmentRepair() {
		super();
	}

	public SbEquipmentRepair(String id){
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
	
	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=255, message="设备数量长度必须介于 0 和 255 之间")
	public String getEquipmentAmount() {
		return equipmentAmount;
	}

	public void setEquipmentAmount(String equipmentAmount) {
		this.equipmentAmount = equipmentAmount;
	}
	
	@Length(min=0, max=255, message="型号规格长度必须介于 0 和 255 之间")
	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	@Length(min=0, max=255, message="生产厂家长度必须介于 0 和 255 之间")
	public String getEquipmentMade() {
		return equipmentMade;
	}

	public void setEquipmentMade(String equipmentMade) {
		this.equipmentMade = equipmentMade;
	}
	
	@Length(min=0, max=255, message="维修预算费用长度必须介于 0 和 255 之间")
	public String getEquipmentBudgt() {
		return equipmentBudgt;
	}

	public void setEquipmentBudgt(String equipmentBudgt) {
		this.equipmentBudgt = equipmentBudgt;
	}
	
	@Length(min=0, max=255, message="设备故障描述长度必须介于 0 和 255 之间")
	public String getEquipmentFault() {
		return equipmentFault;
	}

	public void setEquipmentFault(String equipmentFault) {
		this.equipmentFault = equipmentFault;
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
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}