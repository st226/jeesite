/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 涉密载体对外移交审批表Entity
 * @author l
 * @version 2021-11-27
 */
public class EfmXacqjkussd extends DataEntity<EfmXacqjkussd> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 编号
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String shenqingren;		// 申请人
	private String shenqingbumen;		// 申请部门
	private String yijiaoren;		// 移交人
	private String jieshouren;		// 接收人
	private String yijiaobumen;		// 移交部门
	private String jieshoudanwei;		// 接收单位
	private String zaitizuigaomiji;		// 载体最高密级
	private String zgsld;		// 主管所领导
	private String jieshoudanweiid;		// 接收单位id
	private String beizhu;		// 备注
	private String shenqingrenid;		// 申请人id
	private String shenqingbumenid;		// 申请部门id
	private String jieshourenid;		// 接收人id
	private String yijiaorenid;		// 移交人id
	private String zgsldid;		// 主管所领导id
	private String sqyhid;		// 申请用户id
	private String yjryhid;		// 移交人用户id
	private String yijiaobumenid;		// 移交部门id
	
	public EfmXacqjkussd() {
		super();
	}

	public EfmXacqjkussd(String id){
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
	
	@Length(min=0, max=100, message="移交人长度必须介于 0 和 100 之间")
	public String getYijiaoren() {
		return yijiaoren;
	}

	public void setYijiaoren(String yijiaoren) {
		this.yijiaoren = yijiaoren;
	}
	
	@Length(min=0, max=100, message="接收人长度必须介于 0 和 100 之间")
	public String getJieshouren() {
		return jieshouren;
	}

	public void setJieshouren(String jieshouren) {
		this.jieshouren = jieshouren;
	}
	
	@Length(min=0, max=100, message="移交部门长度必须介于 0 和 100 之间")
	public String getYijiaobumen() {
		return yijiaobumen;
	}

	public void setYijiaobumen(String yijiaobumen) {
		this.yijiaobumen = yijiaobumen;
	}
	
	@Length(min=0, max=100, message="接收单位长度必须介于 0 和 100 之间")
	public String getJieshoudanwei() {
		return jieshoudanwei;
	}

	public void setJieshoudanwei(String jieshoudanwei) {
		this.jieshoudanwei = jieshoudanwei;
	}
	
	@Length(min=0, max=4, message="载体最高密级长度必须介于 0 和 4 之间")
	public String getZaitizuigaomiji() {
		return zaitizuigaomiji;
	}

	public void setZaitizuigaomiji(String zaitizuigaomiji) {
		this.zaitizuigaomiji = zaitizuigaomiji;
	}
	
	@Length(min=0, max=100, message="主管所领导长度必须介于 0 和 100 之间")
	public String getZgsld() {
		return zgsld;
	}

	public void setZgsld(String zgsld) {
		this.zgsld = zgsld;
	}
	
	@Length(min=0, max=100, message="接收单位id长度必须介于 0 和 100 之间")
	public String getJieshoudanweiid() {
		return jieshoudanweiid;
	}

	public void setJieshoudanweiid(String jieshoudanweiid) {
		this.jieshoudanweiid = jieshoudanweiid;
	}
	
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
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
	
	@Length(min=0, max=100, message="移交人id长度必须介于 0 和 100 之间")
	public String getYijiaorenid() {
		return yijiaorenid;
	}

	public void setYijiaorenid(String yijiaorenid) {
		this.yijiaorenid = yijiaorenid;
	}
	
	@Length(min=0, max=100, message="主管所领导id长度必须介于 0 和 100 之间")
	public String getZgsldid() {
		return zgsldid;
	}

	public void setZgsldid(String zgsldid) {
		this.zgsldid = zgsldid;
	}
	
	@Length(min=0, max=100, message="申请用户id长度必须介于 0 和 100 之间")
	public String getSqyhid() {
		return sqyhid;
	}

	public void setSqyhid(String sqyhid) {
		this.sqyhid = sqyhid;
	}
	
	@Length(min=0, max=100, message="移交人用户id长度必须介于 0 和 100 之间")
	public String getYjryhid() {
		return yjryhid;
	}

	public void setYjryhid(String yjryhid) {
		this.yjryhid = yjryhid;
	}
	
	@Length(min=0, max=100, message="移交部门id长度必须介于 0 和 100 之间")
	public String getYijiaobumenid() {
		return yijiaobumenid;
	}

	public void setYijiaobumenid(String yijiaobumenid) {
		this.yijiaobumenid = yijiaobumenid;
	}
	
}