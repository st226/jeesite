/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 载体复印申请Entity
 * @author l
 * @version 2021-11-19
 */
public class EfmGgtdycieys extends DataEntity<EfmGgtdycieys> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 编号
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String zaitileibie;		// 载体类别
	private String zaitiyongtu;		// 载体用途
	private String huiyimingcheng;		// 会议名称
	private String shenqingren;		// 申请人
	private String shenqingbumen;		// 申请部门
	private String shenqingrenid;		// 申请人id
	private String shenqingbumenid;		// 申请部门id
	private String yonghuid;		// 用户id
	private String beizhu;		// 备注
	private String qitayongtu;		// 其他用途
	private String maxSec;		// 最高密级
	private String huiyishenqingid;		// 会议申请id
	
	public EfmGgtdycieys() {
		super();
	}

	public EfmGgtdycieys(String id){
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
	
	@Length(min=0, max=4, message="载体类别长度必须介于 0 和 4 之间")
	public String getZaitileibie() {
		return zaitileibie;
	}

	public void setZaitileibie(String zaitileibie) {
		this.zaitileibie = zaitileibie;
	}
	
	@Length(min=0, max=4, message="载体用途长度必须介于 0 和 4 之间")
	public String getZaitiyongtu() {
		return zaitiyongtu;
	}

	public void setZaitiyongtu(String zaitiyongtu) {
		this.zaitiyongtu = zaitiyongtu;
	}
	
	@Length(min=0, max=200, message="会议名称长度必须介于 0 和 200 之间")
	public String getHuiyimingcheng() {
		return huiyimingcheng;
	}

	public void setHuiyimingcheng(String huiyimingcheng) {
		this.huiyimingcheng = huiyimingcheng;
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
	
	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getYonghuid() {
		return yonghuid;
	}

	public void setYonghuid(String yonghuid) {
		this.yonghuid = yonghuid;
	}
	
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Length(min=0, max=100, message="其他用途长度必须介于 0 和 100 之间")
	public String getQitayongtu() {
		return qitayongtu;
	}

	public void setQitayongtu(String qitayongtu) {
		this.qitayongtu = qitayongtu;
	}
	
	@Length(min=0, max=20, message="最高密级长度必须介于 0 和 20 之间")
	public String getMaxSec() {
		return maxSec;
	}

	public void setMaxSec(String maxSec) {
		this.maxSec = maxSec;
	}
	
	@Length(min=0, max=100, message="会议申请id长度必须介于 0 和 100 之间")
	public String getHuiyishenqingid() {
		return huiyishenqingid;
	}

	public void setHuiyishenqingid(String huiyishenqingid) {
		this.huiyishenqingid = huiyishenqingid;
	}
	
}