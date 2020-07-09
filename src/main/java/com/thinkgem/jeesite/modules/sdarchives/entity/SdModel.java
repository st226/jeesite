/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 三单维护Entity
 * @author suntao
 * @version 2019-11-14
 */
public class SdModel extends DataEntity<SdModel> {
	
	private static final long serialVersionUID = 1L;
	private SdApplication applicationId;		// application_id 父类
	private String on;		// 序号
	private String sdNumber;		// 三单单号
	private String wjNumber;		// 文件带号
	private String sdType;		// 三单类型
	private Office office;		// 发送单位
	private String officeName;		// office_name
	private User user;		// 接收人员
	private String userName;		// user_name
	private String plyy;		// 偏离原因
	private String bjjd;		// 标记阶段
	private String yxfw;		// 有效范围
	private String sjr;		// 设计
	private String sjrname;		// 设计人名称
	private String yxqx;		// 有效期限
	private String cbz;		// 承办者
	private String syffyj;		// 使用方法意见
	private String modelClass;		// 密级
	private String ggbj;		// 更改标记
	private String syx;		// 使用性
	private String ggyy;		// 更改原因
	private String clyj;		// 制品处理意见
	private String gdr;		// 归档人
	private String gdrname;		// gdrname
	private Date gdrq;		// 归档日期
	private Date pzrq;		// 批准日期
	private Date ssrq;		// 实施日期
	private String imageType;		// image_type
	private String imageNo;		// image_no
	private String imagePageno;		// image_pageno
	private Long imageNumber;		// 页数
	private Long imagePageNumber;		// image_page_number
	private String imageTypename;		// image_typename
    private String atlasStatus;
    private String files;
    private String modelId;
    private String modelName;
	
	public SdModel() {
		super();
	}

	public SdModel(String id){
		super(id);
	}

	public SdModel(SdApplication applicationId){
		this.applicationId = applicationId;
	}

	@Length(min=0, max=200, message="application_id长度必须介于 0 和 200 之间")
	public SdApplication getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(SdApplication applicationId) {
		this.applicationId = applicationId;
	}
	
	@Length(min=0, max=11, message="序号长度必须介于 0 和 11 之间")
	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}
	
	@Length(min=0, max=200, message="三单单号长度必须介于 0 和 200 之间")
	public String getSdNumber() {
		return sdNumber;
	}

	public void setSdNumber(String sdNumber) {
		this.sdNumber = sdNumber;
	}
	
	@Length(min=0, max=200, message="文件带号长度必须介于 0 和 200 之间")
	public String getWjNumber() {
		return wjNumber;
	}

	public void setWjNumber(String wjNumber) {
		this.wjNumber = wjNumber;
	}
	
	@Length(min=0, max=200, message="三单类型长度必须介于 0 和 200 之间")
	public String getSdType() {
		return sdType;
	}

	public void setSdType(String sdType) {
		this.sdType = sdType;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="office_name长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=255, message="user_name长度必须介于 0 和 255 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="偏离原因长度必须介于 0 和 255 之间")
	public String getPlyy() {
		return plyy;
	}

	public void setPlyy(String plyy) {
		this.plyy = plyy;
	}
	
	@Length(min=0, max=50, message="标记阶段长度必须介于 0 和 50 之间")
	public String getBjjd() {
		return bjjd;
	}

	public void setBjjd(String bjjd) {
		this.bjjd = bjjd;
	}
	
	@Length(min=0, max=500, message="有效范围长度必须介于 0 和 500 之间")
	public String getYxfw() {
		return yxfw;
	}

	public void setYxfw(String yxfw) {
		this.yxfw = yxfw;
	}
	
	@Length(min=0, max=50, message="设计长度必须介于 0 和 50 之间")
	public String getSjr() {
		return sjr;
	}

	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	
	@Length(min=0, max=50, message="设计人名称长度必须介于 0 和 50 之间")
	public String getSjrname() {
		return sjrname;
	}

	public void setSjrname(String sjrname) {
		this.sjrname = sjrname;
	}
	
	@Length(min=0, max=500, message="有效期限长度必须介于 0 和 500 之间")
	public String getYxqx() {
		return yxqx;
	}

	public void setYxqx(String yxqx) {
		this.yxqx = yxqx;
	}
	
	@Length(min=0, max=50, message="承办者长度必须介于 0 和 50 之间")
	public String getCbz() {
		return cbz;
	}

	public void setCbz(String cbz) {
		this.cbz = cbz;
	}
	
	@Length(min=0, max=500, message="使用方法意见长度必须介于 0 和 500 之间")
	public String getSyffyj() {
		return syffyj;
	}

	public void setSyffyj(String syffyj) {
		this.syffyj = syffyj;
	}
	
	@Length(min=0, max=55, message="密级长度必须介于 0 和 55 之间")
	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	
	@Length(min=0, max=50, message="更改标记长度必须介于 0 和 50 之间")
	public String getGgbj() {
		return ggbj;
	}

	public void setGgbj(String ggbj) {
		this.ggbj = ggbj;
	}
	
	@Length(min=0, max=50, message="使用性长度必须介于 0 和 50 之间")
	public String getSyx() {
		return syx;
	}

	public void setSyx(String syx) {
		this.syx = syx;
	}
	
	@Length(min=0, max=500, message="更改原因长度必须介于 0 和 500 之间")
	public String getGgyy() {
		return ggyy;
	}

	public void setGgyy(String ggyy) {
		this.ggyy = ggyy;
	}
	
	@Length(min=0, max=500, message="制品处理意见长度必须介于 0 和 500 之间")
	public String getClyj() {
		return clyj;
	}

	public void setClyj(String clyj) {
		this.clyj = clyj;
	}
	
	@Length(min=0, max=50, message="归档人长度必须介于 0 和 50 之间")
	public String getGdr() {
		return gdr;
	}

	public void setGdr(String gdr) {
		this.gdr = gdr;
	}
	
	@Length(min=0, max=50, message="gdrname长度必须介于 0 和 50 之间")
	public String getGdrname() {
		return gdrname;
	}

	public void setGdrname(String gdrname) {
		this.gdrname = gdrname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getGdrq() {
		return gdrq;
	}

	public void setGdrq(Date gdrq) {
		this.gdrq = gdrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPzrq() {
		return pzrq;
	}

	public void setPzrq(Date pzrq) {
		this.pzrq = pzrq;
	}
	
	@Length(min=0, max=200, message="image_type长度必须介于 0 和 200 之间")
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	@Length(min=0, max=50, message="image_no长度必须介于 0 和 50 之间")
	public String getImageNo() {
		return imageNo;
	}

	public void setImageNo(String imageNo) {
		this.imageNo = imageNo;
	}
	
	@Length(min=0, max=55, message="image_pageno长度必须介于 0 和 55 之间")
	public String getImagePageno() {
		return imagePageno;
	}

	public void setImagePageno(String imagePageno) {
		this.imagePageno = imagePageno;
	}
	
	public Long getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(Long imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	public Long getImagePageNumber() {
		return imagePageNumber;
	}

	public void setImagePageNumber(Long imagePageNumber) {
		this.imagePageNumber = imagePageNumber;
	}
	
	@Length(min=0, max=200, message="image_typename长度必须介于 0 和 200 之间")
	public String getImageTypename() {
		return imageTypename;
	}

	public void setImageTypename(String imageTypename) {
		this.imageTypename = imageTypename;
	}

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

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getSsrq() {
		return ssrq;
	}

	public void setSsrq(Date ssrq) {
		this.ssrq = ssrq;
	}
	
}