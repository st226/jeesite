/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 列表Entity
 * @author liang
 * @version 2021-11-02
 */
public class EfmIyuuy5h4h9 extends DataEntity<EfmIyuuy5h4h9> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 流程编号
	private String chengbanren;		// 承办人
	private String chengbanbumen;		// 承办部门
	private String sxmc;		// 事项名称
	private String miji;		// 密级
	private String miji2;		// 变更后密级
	private String baomiqixian;		// 保密期限
	private Date baomiqixian2;		// 变更后保密期限
	private String bgqzxfw;		// 变更前知悉范围
	private String bghzxfw;		// 变更后知悉范围
	private String biangengliyou;		// 变更理由
	private String beizhu;		// 备注
	private String chengbanrenid;		// 承办人id
	private String chengbanbumenid;		// 承办部门id
	private String sxmcid;		// 事项名称id
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	
	public EfmIyuuy5h4h9() {
		super();
	}

	public EfmIyuuy5h4h9(String id){
		super(id);
	}

	@Length(min=0, max=255, message="流程编号长度必须介于 0 和 255 之间")
	public String getProcSno() {
		return procSno;
	}

	public void setProcSno(String procSno) {
		this.procSno = procSno;
	}
	
	@Length(min=0, max=255, message="承办人长度必须介于 0 和 255 之间")
	public String getChengbanren() {
		return chengbanren;
	}

	public void setChengbanren(String chengbanren) {
		this.chengbanren = chengbanren;
	}
	
	@Length(min=0, max=255, message="承办部门长度必须介于 0 和 255 之间")
	public String getChengbanbumen() {
		return chengbanbumen;
	}

	public void setChengbanbumen(String chengbanbumen) {
		this.chengbanbumen = chengbanbumen;
	}
	
	@Length(min=0, max=255, message="事项名称长度必须介于 0 和 255 之间")
	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	
	@Length(min=0, max=255, message="密级长度必须介于 0 和 255 之间")
	public String getMiji() {
		return miji;
	}

	public void setMiji(String miji) {
		this.miji = miji;
	}
	
	@Length(min=0, max=255, message="变更后密级长度必须介于 0 和 255 之间")
	public String getMiji2() {
		return miji2;
	}

	public void setMiji2(String miji2) {
		this.miji2 = miji2;
	}
	
	@Length(min=0, max=255, message="保密期限长度必须介于 0 和 255 之间")
	public String getBaomiqixian() {
		return baomiqixian;
	}

	public void setBaomiqixian(String baomiqixian) {
		this.baomiqixian = baomiqixian;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBaomiqixian2() {
		return baomiqixian2;
	}

	public void setBaomiqixian2(Date baomiqixian2) {
		this.baomiqixian2 = baomiqixian2;
	}
	
	@Length(min=0, max=255, message="变更前知悉范围长度必须介于 0 和 255 之间")
	public String getBgqzxfw() {
		return bgqzxfw;
	}

	public void setBgqzxfw(String bgqzxfw) {
		this.bgqzxfw = bgqzxfw;
	}
	
	@Length(min=0, max=255, message="变更后知悉范围长度必须介于 0 和 255 之间")
	public String getBghzxfw() {
		return bghzxfw;
	}

	public void setBghzxfw(String bghzxfw) {
		this.bghzxfw = bghzxfw;
	}
	
	@Length(min=0, max=255, message="变更理由长度必须介于 0 和 255 之间")
	public String getBiangengliyou() {
		return biangengliyou;
	}

	public void setBiangengliyou(String biangengliyou) {
		this.biangengliyou = biangengliyou;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Length(min=0, max=255, message="承办人id长度必须介于 0 和 255 之间")
	public String getChengbanrenid() {
		return chengbanrenid;
	}

	public void setChengbanrenid(String chengbanrenid) {
		this.chengbanrenid = chengbanrenid;
	}
	
	@Length(min=0, max=255, message="承办部门id长度必须介于 0 和 255 之间")
	public String getChengbanbumenid() {
		return chengbanbumenid;
	}

	public void setChengbanbumenid(String chengbanbumenid) {
		this.chengbanbumenid = chengbanbumenid;
	}
	
	@Length(min=0, max=255, message="事项名称id长度必须介于 0 和 255 之间")
	public String getSxmcid() {
		return sxmcid;
	}

	public void setSxmcid(String sxmcid) {
		this.sxmcid = sxmcid;
	}
	
	@Length(min=0, max=255, message="表单ID长度必须介于 0 和 255 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=255, message="数据密级长度必须介于 0 和 255 之间")
	public String getDataSecretLevel() {
		return dataSecretLevel;
	}

	public void setDataSecretLevel(String dataSecretLevel) {
		this.dataSecretLevel = dataSecretLevel;
	}
	
	@Length(min=0, max=255, message="版本长度必须介于 0 和 255 之间")
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
	
}