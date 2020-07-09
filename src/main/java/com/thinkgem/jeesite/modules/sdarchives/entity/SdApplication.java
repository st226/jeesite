/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.eos.workflow.data.WFWorkItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 三单维护Entity
 * @author suntao
 * @version 2019-11-14
 */
public class SdApplication extends DataEntity<SdApplication> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;		// 流程号
	private String applyPersonsj;		// 设计人员
	private String code;		// 编号
	private String apllyUnit;		// aplly_unit
	private String apllyPerson;		// aplly_person
	private Date apllyDate;		// 申请时间
	private Long apllyStatus;		// 申请状态
	private String userName;		// user_name
	private Office office;		// 申请部门
	private User user;		// 申请人
	private String officeName;		// office_name
	private List<SdModel> sdModelList = Lists.newArrayList();		// 子表列表
	private String sdType ;
	private WFWorkItem workItem;
	
	public SdApplication() {
		super();
	}

	public SdApplication(String id){
		super(id);
	}

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=1, max=255, message="设计人员长度必须介于 1 和 255 之间")
	public String getApplyPersonsj() {
		return applyPersonsj;
	}

	public void setApplyPersonsj(String applyPersonsj) {
		this.applyPersonsj = applyPersonsj;
	}
	
	@Length(min=0, max=55, message="编号长度必须介于 0 和 55 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=55, message="aplly_unit长度必须介于 0 和 55 之间")
	public String getApllyUnit() {
		return apllyUnit;
	}

	public void setApllyUnit(String apllyUnit) {
		this.apllyUnit = apllyUnit;
	}
	
	@Length(min=0, max=50, message="aplly_person长度必须介于 0 和 50 之间")
	public String getApllyPerson() {
		return apllyPerson;
	}

	public void setApllyPerson(String apllyPerson) {
		this.apllyPerson = apllyPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApllyDate() {
		return apllyDate;
	}

	public void setApllyDate(Date apllyDate) {
		this.apllyDate = apllyDate;
	}
	
	public Long getApllyStatus() {
		return apllyStatus;
	}

	public void setApllyStatus(Long apllyStatus) {
		this.apllyStatus = apllyStatus;
	}
	
	@Length(min=0, max=200, message="user_name长度必须介于 0 和 200 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	@Length(min=0, max=200, message="office_name长度必须介于 0 和 200 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	public List<SdModel> getSdModelList() {
		return sdModelList;
	}

	public void setSdModelList(List<SdModel> sdModelList) {
		this.sdModelList = sdModelList;
	}

	public String getSdType() {
		return sdType;
	}

	public void setSdType(String sdType) {
		this.sdType = sdType;
	}

	public WFWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WFWorkItem workItem) {
		this.workItem = workItem;
	}
}