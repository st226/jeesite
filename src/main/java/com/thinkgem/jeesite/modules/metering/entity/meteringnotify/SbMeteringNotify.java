/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.entity.meteringnotify;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import java.util.List;

import com.eos.workflow.data.WFWorkItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测量数据追踪Entity
 * @author suntao
 * @version 2020-05-20
 */
public class SbMeteringNotify extends DataEntity<SbMeteringNotify> {
	
	private static final long serialVersionUID = 1L;
	private String equipmentId;		// equipment_id
	private long processInstID ;
	private String equipmentName;		// 设备名称
	private String equipmentCode;		// 设备编号
	private Office office;		// office_id
	private String officeName;		// 部门名称
	private User user;		// user_id
	private String userName;		// 使用人
	private Date meteringTime;		// 计量时间
	private Date beginTime;		// 起始时间
	private Date endTime;		// 结束时间
	private String createOffice;		// 提出部门
	private String reasoncx;		// 超差内容描述
	private String resultcx;		// 鉴定结果
	private String reasonqk;		// 反馈情况
	private String uname;		// 使用人
	private String leader;		// 部门领导
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public SbMeteringNotify() {
		super();
	}

	public SbMeteringNotify(String id){
		super(id);
	}

	@Length(min=0, max=255, message="equipment_id长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=255, message="设备编号长度必须介于 0 和 255 之间")
	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="部门名称长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=255, message="使用人长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMeteringTime() {
		return meteringTime;
	}

	public void setMeteringTime(Date meteringTime) {
		this.meteringTime = meteringTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=255, message="提出部门长度必须介于 0 和 255 之间")
	public String getCreateOffice() {
		return createOffice;
	}

	public void setCreateOffice(String createOffice) {
		this.createOffice = createOffice;
	}
	
	@Length(min=0, max=255, message="超差内容描述长度必须介于 0 和 255 之间")
	public String getReasoncx() {
		return reasoncx;
	}

	public void setReasoncx(String reasoncx) {
		this.reasoncx = reasoncx;
	}
	
	@Length(min=0, max=255, message="鉴定结果长度必须介于 0 和 255 之间")
	public String getResultcx() {
		return resultcx;
	}

	public void setResultcx(String resultcx) {
		this.resultcx = resultcx;
	}
	
	@Length(min=0, max=255, message="反馈情况长度必须介于 0 和 255 之间")
	public String getReasonqk() {
		return reasonqk;
	}

	public void setReasonqk(String reasonqk) {
		this.reasonqk = reasonqk;
	}
	
	@Length(min=0, max=255, message="使用人长度必须介于 0 和 255 之间")
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	@Length(min=0, max=255, message="部门领导长度必须介于 0 和 255 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
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



	public long getProcessInstID() {
		return processInstID;
	}

	public void setProcessInstID(long processInstID) {
		this.processInstID = processInstID;
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