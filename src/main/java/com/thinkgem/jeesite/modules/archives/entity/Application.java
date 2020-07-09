/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;

import com.eos.workflow.data.WFWorkItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 现行文件管理Entity
 * @author suntao
 * @version 2019-11-10
 */
public class Application extends DataEntity<Application> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;		// 流程号
	private String busType;		// 型号
	private String busTypeName;		// 型号
	private String applyCour;		// 申请原因
	private String code;		// 计划号
	private String userName;		// user_name
	private User user;		// 申请人
	private String officeName;		// office_name
	private Office office;		// 申请单位
	private Date apllyDate;		// 申请日期
	private String apllyNo;		// 电话号
	private String apllyModel;		// 复印形式
	private Long apllyStatus;		// 状态
	private String apllySypc;		// 晒印批组
	private String apllySystatus;		// aplly_systatus
	private Long apllyTotal;		// aplly_total
	private Long apllySfsy;		// aplly_sfsy
	private Long apllySyss;		// aplly_syss
	private Date apllyYjwcrq;		// aplly_yjwcrq
	private Long apllyGjgzl;		// aplly_gjgzl
	private String status;		// status
	private Date apllySykssj;		// aplly_sykssj
	private Long apllySytepy;		// aplly_sytepy
	private Date apllyKyyqsj;		// aplly_kyyqsj
	private String applyTypename;		// apply_typename
	private List<Model> modelList = Lists.newArrayList();		// 子表列表
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public Application() {
		super();
	}

	public Application(String id){
		super(id);
	}

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=200, message="型号长度必须介于 0 和 200 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@Length(min=0, max=200, message="型号长度必须介于 0 和 200 之间")
	public String getBusTypeName() {
		return busTypeName;
	}

	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}
	
	@Length(min=1, max=255, message="申请原因长度必须介于 1 和 255 之间")
	public String getApplyCour() {
		return applyCour;
	}

	public void setApplyCour(String applyCour) {
		this.applyCour = applyCour;
	}
	
	@Length(min=1, max=55, message="计划号长度必须介于 1 和 55 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=122, message="user_name长度必须介于 0 和 122 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@NotNull(message="申请人不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=122, message="office_name长度必须介于 0 和 122 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@NotNull(message="申请单位不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="申请日期不能为空")
	public Date getApllyDate() {
		return apllyDate;
	}

	public void setApllyDate(Date apllyDate) {
		this.apllyDate = apllyDate;
	}
	
	@Length(min=1, max=255, message="电话号长度必须介于 1 和 255 之间")
	public String getApllyNo() {
		return apllyNo;
	}

	public void setApllyNo(String apllyNo) {
		this.apllyNo = apllyNo;
	}
	
	@Length(min=1, max=20, message="复印形式长度必须介于 1 和 20 之间")
	public String getApllyModel() {
		return apllyModel;
	}

	public void setApllyModel(String apllyModel) {
		this.apllyModel = apllyModel;
	}
	
	public Long getApllyStatus() {
		return apllyStatus;
	}

	public void setApllyStatus(Long apllyStatus) {
		this.apllyStatus = apllyStatus;
	}
	
	@Length(min=1, max=20, message="晒印批组长度必须介于 1 和 20 之间")
	public String getApllySypc() {
		return apllySypc;
	}

	public void setApllySypc(String apllySypc) {
		this.apllySypc = apllySypc;
	}
	
	@Length(min=0, max=20, message="aplly_systatus长度必须介于 0 和 20 之间")
	public String getApllySystatus() {
		return apllySystatus;
	}

	public void setApllySystatus(String apllySystatus) {
		this.apllySystatus = apllySystatus;
	}
	
	public Long getApllyTotal() {
		return apllyTotal;
	}

	public void setApllyTotal(Long apllyTotal) {
		this.apllyTotal = apllyTotal;
	}
	
	public Long getApllySfsy() {
		return apllySfsy;
	}

	public void setApllySfsy(Long apllySfsy) {
		this.apllySfsy = apllySfsy;
	}
	
	public Long getApllySyss() {
		return apllySyss;
	}

	public void setApllySyss(Long apllySyss) {
		this.apllySyss = apllySyss;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApllyYjwcrq() {
		return apllyYjwcrq;
	}

	public void setApllyYjwcrq(Date apllyYjwcrq) {
		this.apllyYjwcrq = apllyYjwcrq;
	}
	
	public Long getApllyGjgzl() {
		return apllyGjgzl;
	}

	public void setApllyGjgzl(Long apllyGjgzl) {
		this.apllyGjgzl = apllyGjgzl;
	}
	
	@Length(min=0, max=20, message="status长度必须介于 0 和 20 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApllySykssj() {
		return apllySykssj;
	}

	public void setApllySykssj(Date apllySykssj) {
		this.apllySykssj = apllySykssj;
	}
	
	public Long getApllySytepy() {
		return apllySytepy;
	}

	public void setApllySytepy(Long apllySytepy) {
		this.apllySytepy = apllySytepy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApllyKyyqsj() {
		return apllyKyyqsj;
	}

	public void setApllyKyyqsj(Date apllyKyyqsj) {
		this.apllyKyyqsj = apllyKyyqsj;
	}
	
	@Length(min=0, max=200, message="apply_typename长度必须介于 0 和 200 之间")
	public String getApplyTypename() {
		return applyTypename;
	}

	public void setApplyTypename(String applyTypename) {
		this.applyTypename = applyTypename;
	}
	
	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	public WFWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WFWorkItem workItem) {
		this.workItem = workItem;
	}

	public List<Examine> getExamineList() {
		return examineList;
	}

	public void setExamineList(List<Examine> examineList) {
		this.examineList = examineList;
	}
}