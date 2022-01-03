/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 台账Entity
 * @author liang
 * @version 2021-11-02
 */
public class EfmJmaezctiei extends DataEntity<EfmJmaezctiei> {
	
	private static final long serialVersionUID = 1L;
	private User createByDept;		// 创建者组织
	private String procSno;		// 流程编号
	private String tianbaobumen;		// 填报部门
	private Date tianbaoriqi;		// 填报日期
	private String chengbanren;		// 承办人
	private String lianxidianhua;		// 联系电话
	private String gjmmlx;		// 国家秘密类型
	private String sxmc;		// 事项名称
	private String nidingmiji;		// 拟定密级
	private String baomiqixian;		// 保密期限
	private String zhixifanwei;		// 知悉范围
	private String dingmiyiju;		// 定密依据
	private String chengbanrenid;		// 承办人id
	private String tianbaobumenid;		// 填报部门id
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	
	public EfmJmaezctiei() {
		super();
	}

	public EfmJmaezctiei(String id){
		super(id);
	}

	public User getCreateByDept() {
		return createByDept;
	}

	public void setCreateByDept(User createByDept) {
		this.createByDept = createByDept;
	}
	
	@Length(min=0, max=255, message="流程编号长度必须介于 0 和 255 之间")
	public String getProcSno() {
		return procSno;
	}

	public void setProcSno(String procSno) {
		this.procSno = procSno;
	}
	
	@Length(min=0, max=255, message="填报部门长度必须介于 0 和 255 之间")
	public String getTianbaobumen() {
		return tianbaobumen;
	}

	public void setTianbaobumen(String tianbaobumen) {
		this.tianbaobumen = tianbaobumen;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTianbaoriqi() {
		return tianbaoriqi;
	}

	public void setTianbaoriqi(Date tianbaoriqi) {
		this.tianbaoriqi = tianbaoriqi;
	}
	
	@Length(min=0, max=255, message="承办人长度必须介于 0 和 255 之间")
	public String getChengbanren() {
		return chengbanren;
	}

	public void setChengbanren(String chengbanren) {
		this.chengbanren = chengbanren;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}
	
	@Length(min=0, max=255, message="国家秘密类型长度必须介于 0 和 255 之间")
	public String getGjmmlx() {
		return gjmmlx;
	}

	public void setGjmmlx(String gjmmlx) {
		this.gjmmlx = gjmmlx;
	}
	
	@Length(min=0, max=255, message="事项名称长度必须介于 0 和 255 之间")
	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	
	@Length(min=0, max=255, message="拟定密级长度必须介于 0 和 255 之间")
	public String getNidingmiji() {
		return nidingmiji;
	}

	public void setNidingmiji(String nidingmiji) {
		this.nidingmiji = nidingmiji;
	}
	
	@Length(min=0, max=255, message="保密期限长度必须介于 0 和 255 之间")
	public String getBaomiqixian() {
		return baomiqixian;
	}

	public void setBaomiqixian(String baomiqixian) {
		this.baomiqixian = baomiqixian;
	}
	
	@Length(min=0, max=255, message="知悉范围长度必须介于 0 和 255 之间")
	public String getZhixifanwei() {
		return zhixifanwei;
	}

	public void setZhixifanwei(String zhixifanwei) {
		this.zhixifanwei = zhixifanwei;
	}
	
	@Length(min=0, max=255, message="定密依据长度必须介于 0 和 255 之间")
	public String getDingmiyiju() {
		return dingmiyiju;
	}

	public void setDingmiyiju(String dingmiyiju) {
		this.dingmiyiju = dingmiyiju;
	}
	
	@Length(min=0, max=255, message="承办人id长度必须介于 0 和 255 之间")
	public String getChengbanrenid() {
		return chengbanrenid;
	}

	public void setChengbanrenid(String chengbanrenid) {
		this.chengbanrenid = chengbanrenid;
	}
	
	@Length(min=0, max=255, message="填报部门id长度必须介于 0 和 255 之间")
	public String getTianbaobumenid() {
		return tianbaobumenid;
	}

	public void setTianbaobumenid(String tianbaobumenid) {
		this.tianbaobumenid = tianbaobumenid;
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
	
	@Length(min=0, max=50, message="版本长度必须介于 0 和 50 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}