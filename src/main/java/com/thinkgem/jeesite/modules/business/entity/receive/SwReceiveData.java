/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.receive;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仪器设备开箱验收Entity
 * @author suntao
 * @version 2020-04-11
 */
public class SwReceiveData extends DataEntity<SwReceiveData> {
	
	private static final long serialVersionUID = 1L;
	private SwReceive receiveId;		// receive_id 父类
	private String name;		// 资料名称
	private String copies;		// 份数
	private String pages;		// 页数
	private String special;		// 特殊介质
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String equipmentId;
	private String equipmentName;
	
	public SwReceiveData() {
		super();
	}

	public SwReceiveData(String id){
		super(id);
	}

	public SwReceiveData(SwReceive receiveId){
		this.receiveId = receiveId;
	}

	@Length(min=0, max=255, message="receive_id长度必须介于 0 和 255 之间")
	public SwReceive getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(SwReceive receiveId) {
		this.receiveId = receiveId;
	}
	
	@Length(min=0, max=255, message="资料名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="份数长度必须介于 0 和 255 之间")
	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}
	
	@Length(min=0, max=255, message="页数长度必须介于 0 和 255 之间")
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}
	
	@Length(min=0, max=255, message="特殊介质长度必须介于 0 和 255 之间")
	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
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

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
}