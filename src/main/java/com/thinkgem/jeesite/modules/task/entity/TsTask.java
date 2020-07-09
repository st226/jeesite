/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 采集任务管理Entity
 * @author suntao
 * @version 2018-05-21
 */
public class TsTask extends DataEntity<TsTask> {
	
	private static final long serialVersionUID = 1L;
	private String equipmentname;		// 设备编号
	private String equipmentid;		// 设备编号
	private String officeid;		// 采集部门
	private String userid;		// 采集人
	private String lasttime;		// 上次采集时间
	private String acquisitiontime;		// 任务时间
	private String amount;		// 数据包数
	private String state;		// 采集状态
	private String busType;		// bus_type
	
	public TsTask() {
		super();
	}

	public TsTask(String id){
		super(id);
	}

	@Length(min=0, max=50, message="设备编号长度必须介于 0 和 50 之间")
	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	
	@Length(min=0, max=50, message="设备编号长度必须介于 0 和 50 之间")
	public String getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	
	@Length(min=0, max=50, message="采集部门长度必须介于 0 和 50 之间")
	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	
	@Length(min=0, max=50, message="采集人长度必须介于 0 和 50 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=50, message="上次采集时间长度必须介于 0 和 50 之间")
	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	
	@Length(min=0, max=50, message="任务时间长度必须介于 0 和 50 之间")
	public String getAcquisitiontime() {
		return acquisitiontime;
	}

	public void setAcquisitiontime(String acquisitiontime) {
		this.acquisitiontime = acquisitiontime;
	}
	
	@Length(min=0, max=50, message="数据包数长度必须介于 0 和 50 之间")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=50, message="采集状态长度必须介于 0 和 50 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=50, message="bus_type长度必须介于 0 和 50 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
}