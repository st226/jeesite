/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author liang
 * @version 2021-11-02
 */
public class EfmJueltygvaw extends DataEntity<EfmJueltygvaw> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 流程编号
	private String chengbanren;		// 承办人
	private String chengbanbumen;		// 承办部门
	private String sxmc;		// 事项名称
	private String miji;		// 原定密级
	private String ydbmqx;		// 原定保密期限
	private String zhixifanwei;		// 知悉范围
	private String tuomichuliliyou;		// 脱密处理理由
	private String tmclyj;		// 脱密处理意见
	private String beizhu;		// 备注
	private String chengbanrenid;		// 承办人id
	private String chengbanbumenid;		// 承办部门id
	private String sxmcid;		// 事项名称id
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	
	public EfmJueltygvaw() {
		super();
	}

	public EfmJueltygvaw(String id){
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
	
	@Length(min=0, max=255, message="原定密级长度必须介于 0 和 255 之间")
	public String getMiji() {
		return miji;
	}

	public void setMiji(String miji) {
		this.miji = miji;
	}
	
	@Length(min=0, max=255, message="原定保密期限长度必须介于 0 和 255 之间")
	public String getYdbmqx() {
		return ydbmqx;
	}

	public void setYdbmqx(String ydbmqx) {
		this.ydbmqx = ydbmqx;
	}
	
	@Length(min=0, max=255, message="知悉范围长度必须介于 0 和 255 之间")
	public String getZhixifanwei() {
		return zhixifanwei;
	}

	public void setZhixifanwei(String zhixifanwei) {
		this.zhixifanwei = zhixifanwei;
	}
	
	@Length(min=0, max=255, message="脱密处理理由长度必须介于 0 和 255 之间")
	public String getTuomichuliliyou() {
		return tuomichuliliyou;
	}

	public void setTuomichuliliyou(String tuomichuliliyou) {
		this.tuomichuliliyou = tuomichuliliyou;
	}
	
	@Length(min=0, max=255, message="脱密处理意见长度必须介于 0 和 255 之间")
	public String getTmclyj() {
		return tmclyj;
	}

	public void setTmclyj(String tmclyj) {
		this.tmclyj = tmclyj;
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