/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.approver.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.eos.workflow.data.WFWorkItem;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 现行文件申请Entity
 * @author suntao
 * @version 2019-01-27
 */
public class TsApprover extends DataEntity<TsApprover> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;	
	private String name;		// 文件名称
	private String file;		// 附件
	private String username;		// 上传人
	private String userid;		// 上传人ID
	private String officename;		// 上传部门
	private String officeid;		// 上传部门ID
	private String typeName;		// 所属型号
	private String applicantId;		// 申请人ID
	private String applicantName;		// 申请人
	private String applicantDeptId;		// 申请部门ID
	private String applicantDeptName;		// 申请部门
	private String applicantDate;		// 申请时间
	private String state;		// 申请状态
	private String approverId;		// 审批人ID
	private String approverName;		// 审批人名称
	private String resourceId;		// 资源ID
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public TsApprover() {
		super();
	}

	public TsApprover(String id){
		super(id);
	}

	@Length(min=0, max=500, message="文件名称长度必须介于 0 和 500 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=100, message="上传人长度必须介于 0 和 100 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=100, message="上传人ID长度必须介于 0 和 100 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=100, message="上传部门长度必须介于 0 和 100 之间")
	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}
	
	@Length(min=0, max=100, message="上传部门ID长度必须介于 0 和 100 之间")
	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	
	@Length(min=0, max=100, message="所属型号长度必须介于 0 和 100 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Length(min=0, max=100, message="申请人ID长度必须介于 0 和 100 之间")
	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	
	@Length(min=0, max=100, message="申请人长度必须介于 0 和 100 之间")
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	@Length(min=0, max=100, message="申请部门ID长度必须介于 0 和 100 之间")
	public String getApplicantDeptId() {
		return applicantDeptId;
	}

	public void setApplicantDeptId(String applicantDeptId) {
		this.applicantDeptId = applicantDeptId;
	}
	
	@Length(min=0, max=100, message="申请部门长度必须介于 0 和 100 之间")
	public String getApplicantDeptName() {
		return applicantDeptName;
	}

	public void setApplicantDeptName(String applicantDeptName) {
		this.applicantDeptName = applicantDeptName;
	}
	
	@Length(min=0, max=100, message="申请时间长度必须介于 0 和 100 之间")
	public String getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(String applicantDate) {
		this.applicantDate = applicantDate;
	}
	
	@Length(min=0, max=100, message="申请状态长度必须介于 0 和 100 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=100, message="审批人ID长度必须介于 0 和 100 之间")
	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	
	@Length(min=0, max=100, message="审批人名称长度必须介于 0 和 100 之间")
	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	
	@Length(min=0, max=100, message="资源ID长度必须介于 0 和 100 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
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