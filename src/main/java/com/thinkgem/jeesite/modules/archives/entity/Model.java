/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 现行文件管理Entity
 * @author suntao
 * @version 2019-11-10
 */
public class Model extends DataEntity<Model> {
	
	private static final long serialVersionUID = 1L;
	private Application applicationId;		// 申请表编号 父类
	private String imageType;		// 图号
	private String imageNo;		// 图号
	private String imagePageno;		// 名称
	private String modelClass;		// 密级
	private Long imageNumber;		// 份数
	private Office office;		// 发往部门
	private String officeName;		// office_name
	private Long imagePageNumber;		// 页数
	private Long imagePagea4Number;		// 折合A4页数
	private String imageTypename;		// image_typename
	private Long imagePagea4Number2;		// image_pagea4_number2
	private User user;		// 接收人
	private String userName;		// user_name
	private Date atlasDate;		// atlas_date
	private String atlasPid;		// atlas_pid
	private String atlasStatus;		// atlas_status
	private String files ;
	private String syJdbj ;
	private String syIndex ;
	
	public Model() {
		super();
	}

	public Model(String id){
		super(id);
	}

	public Model(Application applicationId){
		this.applicationId = applicationId;
	}

	@Length(min=0, max=50, message="申请表编号长度必须介于 0 和 50 之间")
	public Application getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Application applicationId) {
		this.applicationId = applicationId;
	}
	
	@Length(min=0, max=200, message="图号长度必须介于 0 和 200 之间")
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	@Length(min=1, max=500, message="图号长度必须介于 1 和 500 之间")
	public String getImageNo() {
		return imageNo;
	}

	public void setImageNo(String imageNo) {
		this.imageNo = imageNo;
	}
	
	@Length(min=1, max=55, message="名称长度必须介于 1 和 55 之间")
	public String getImagePageno() {
		return imagePageno;
	}

	public void setImagePageno(String imagePageno) {
		this.imagePageno = imagePageno;
	}
	
	@Length(min=1, max=55, message="密级长度必须介于 1 和 55 之间")
	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	
	@NotNull(message="份数不能为空")
	public Long getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(Long imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	@NotNull(message="发往部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=200, message="office_name长度必须介于 0 和 200 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@NotNull(message="页数不能为空")
	public Long getImagePageNumber() {
		return imagePageNumber;
	}

	public void setImagePageNumber(Long imagePageNumber) {
		this.imagePageNumber = imagePageNumber;
	}
	
	@NotNull(message="折合A4页数不能为空")
	public Long getImagePagea4Number() {
		return imagePagea4Number;
	}

	public void setImagePagea4Number(Long imagePagea4Number) {
		this.imagePagea4Number = imagePagea4Number;
	}
	
	@Length(min=0, max=200, message="image_typename长度必须介于 0 和 200 之间")
	public String getImageTypename() {
		return imageTypename;
	}

	public void setImageTypename(String imageTypename) {
		this.imageTypename = imageTypename;
	}
	
	public Long getImagePagea4Number2() {
		return imagePagea4Number2;
	}

	public void setImagePagea4Number2(Long imagePagea4Number2) {
		this.imagePagea4Number2 = imagePagea4Number2;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=100, message="user_name长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAtlasDate() {
		return atlasDate;
	}

	public void setAtlasDate(Date atlasDate) {
		this.atlasDate = atlasDate;
	}
	
	@Length(min=0, max=100, message="atlas_pid长度必须介于 0 和 100 之间")
	public String getAtlasPid() {
		return atlasPid;
	}

	public void setAtlasPid(String atlasPid) {
		this.atlasPid = atlasPid;
	}
	
	@Length(min=0, max=100, message="atlas_status长度必须介于 0 和 100 之间")
	public String getAtlasStatus() {
		return atlasStatus;
	}

	public void setAtlasStatus(String atlasStatus) {
		this.atlasStatus = atlasStatus;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getSyJdbj() {
		return syJdbj;
	}

	public void setSyJdbj(String syJdbj) {
		this.syJdbj = syJdbj;
	}

	public String getSyIndex() {
		return syIndex;
	}

	public void setSyIndex(String syIndex) {
		this.syIndex = syIndex;
	}
	
}