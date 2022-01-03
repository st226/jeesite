/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 涉密载体流转审批表Entity
 * @author l
 * @version 2021-11-27
 */
public class EfmSmycmp3wjm extends DataEntity<EfmSmycmp3wjm> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 编号
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String shenqingren;		// 申请人
	private String shenqingbumen;		// 申请部门
	private String jieshouren;		// 接收人
	private String jieshoubumen;		// 接收部门
	private String shenqingrenmiji;		// 申请人密级
	private String jieshourenmiji;		// 接收人密级
	private String sqrlxfs;		// 申请人联系方式
	private Date shenqingshijian;		// 申请时间
	private String shenqingrenid;		// 申请人id
	private String shenqingbumenid;		// 申请部门id
	private String jieshourenid;		// 接收人id
	private String jieshoubumenid;		// 接收部门id
	private String sqyhid;		// 申请用户id
	private String jieshouyonghuid;		// 接收用户id
	
	public EfmSmycmp3wjm() {
		super();
	}

	public EfmSmycmp3wjm(String id){
		super(id);
	}

	@Length(min=0, max=50, message="编号长度必须介于 0 和 50 之间")
	public String getProcSno() {
		return procSno;
	}

	public void setProcSno(String procSno) {
		this.procSno = procSno;
	}
	
	@Length(min=0, max=64, message="表单ID长度必须介于 0 和 64 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=20, message="数据密级长度必须介于 0 和 20 之间")
	public String getDataSecretLevel() {
		return dataSecretLevel;
	}

	public void setDataSecretLevel(String dataSecretLevel) {
		this.dataSecretLevel = dataSecretLevel;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public User getCreateByDept() {
		return createByDept;
	}

	public void setCreateByDept(User createByDept) {
		this.createByDept = createByDept;
	}
	
	@Length(min=0, max=100, message="申请人长度必须介于 0 和 100 之间")
	public String getShenqingren() {
		return shenqingren;
	}

	public void setShenqingren(String shenqingren) {
		this.shenqingren = shenqingren;
	}
	
	@Length(min=0, max=100, message="申请部门长度必须介于 0 和 100 之间")
	public String getShenqingbumen() {
		return shenqingbumen;
	}

	public void setShenqingbumen(String shenqingbumen) {
		this.shenqingbumen = shenqingbumen;
	}
	
	@Length(min=0, max=100, message="接收人长度必须介于 0 和 100 之间")
	public String getJieshouren() {
		return jieshouren;
	}

	public void setJieshouren(String jieshouren) {
		this.jieshouren = jieshouren;
	}
	
	@Length(min=0, max=100, message="接收部门长度必须介于 0 和 100 之间")
	public String getJieshoubumen() {
		return jieshoubumen;
	}

	public void setJieshoubumen(String jieshoubumen) {
		this.jieshoubumen = jieshoubumen;
	}
	
	@Length(min=0, max=4, message="申请人密级长度必须介于 0 和 4 之间")
	public String getShenqingrenmiji() {
		return shenqingrenmiji;
	}

	public void setShenqingrenmiji(String shenqingrenmiji) {
		this.shenqingrenmiji = shenqingrenmiji;
	}
	
	@Length(min=0, max=4, message="接收人密级长度必须介于 0 和 4 之间")
	public String getJieshourenmiji() {
		return jieshourenmiji;
	}

	public void setJieshourenmiji(String jieshourenmiji) {
		this.jieshourenmiji = jieshourenmiji;
	}
	
	@Length(min=0, max=20, message="申请人联系方式长度必须介于 0 和 20 之间")
	public String getSqrlxfs() {
		return sqrlxfs;
	}

	public void setSqrlxfs(String sqrlxfs) {
		this.sqrlxfs = sqrlxfs;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShenqingshijian() {
		return shenqingshijian;
	}

	public void setShenqingshijian(Date shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	
	@Length(min=0, max=100, message="申请人id长度必须介于 0 和 100 之间")
	public String getShenqingrenid() {
		return shenqingrenid;
	}

	public void setShenqingrenid(String shenqingrenid) {
		this.shenqingrenid = shenqingrenid;
	}
	
	@Length(min=0, max=100, message="申请部门id长度必须介于 0 和 100 之间")
	public String getShenqingbumenid() {
		return shenqingbumenid;
	}

	public void setShenqingbumenid(String shenqingbumenid) {
		this.shenqingbumenid = shenqingbumenid;
	}
	
	@Length(min=0, max=100, message="接收人id长度必须介于 0 和 100 之间")
	public String getJieshourenid() {
		return jieshourenid;
	}

	public void setJieshourenid(String jieshourenid) {
		this.jieshourenid = jieshourenid;
	}
	
	@Length(min=0, max=100, message="接收部门id长度必须介于 0 和 100 之间")
	public String getJieshoubumenid() {
		return jieshoubumenid;
	}

	public void setJieshoubumenid(String jieshoubumenid) {
		this.jieshoubumenid = jieshoubumenid;
	}
	
	@Length(min=0, max=100, message="申请用户id长度必须介于 0 和 100 之间")
	public String getSqyhid() {
		return sqyhid;
	}

	public void setSqyhid(String sqyhid) {
		this.sqyhid = sqyhid;
	}
	
	@Length(min=0, max=100, message="接收用户id长度必须介于 0 和 100 之间")
	public String getJieshouyonghuid() {
		return jieshouyonghuid;
	}

	public void setJieshouyonghuid(String jieshouyonghuid) {
		this.jieshouyonghuid = jieshouyonghuid;
	}
	
}