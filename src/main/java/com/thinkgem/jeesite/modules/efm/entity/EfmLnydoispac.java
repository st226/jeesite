/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 涉密载体归档管理Entity
 * @author l
 * @version 2021-11-27
 */
public class EfmLnydoispac extends DataEntity<EfmLnydoispac> {
	
	private static final long serialVersionUID = 1L;
	private String xuhao;		// 序号
	private String zaitimingcheng;		// 载体名称
	private String zaitibianhao;		// 载体编号
	private String miji;		// 密级
	private String fenshu;		// 份数
	private String yeshu;		// 页数
	private String formId;		// 表单ID
	private String procSno;		// 流程编号
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String fcId;		// 载体id
	private String fkId;		// 外键
	private String zaitizhuangtai;		// 载体状态
	
	public EfmLnydoispac() {
		super();
	}

	public EfmLnydoispac(String id){
		super(id);
	}

	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	
	@Length(min=0, max=100, message="载体名称长度必须介于 0 和 100 之间")
	public String getZaitimingcheng() {
		return zaitimingcheng;
	}

	public void setZaitimingcheng(String zaitimingcheng) {
		this.zaitimingcheng = zaitimingcheng;
	}
	
	@Length(min=0, max=50, message="载体编号长度必须介于 0 和 50 之间")
	public String getZaitibianhao() {
		return zaitibianhao;
	}

	public void setZaitibianhao(String zaitibianhao) {
		this.zaitibianhao = zaitibianhao;
	}
	
	@Length(min=0, max=4, message="密级长度必须介于 0 和 4 之间")
	public String getMiji() {
		return miji;
	}

	public void setMiji(String miji) {
		this.miji = miji;
	}
	
	@Length(min=0, max=50, message="份数长度必须介于 0 和 50 之间")
	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}
	
	public String getYeshu() {
		return yeshu;
	}

	public void setYeshu(String yeshu) {
		this.yeshu = yeshu;
	}
	
	@Length(min=0, max=64, message="表单ID长度必须介于 0 和 64 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=50, message="流程编号长度必须介于 0 和 50 之间")
	public String getProcSno() {
		return procSno;
	}

	public void setProcSno(String procSno) {
		this.procSno = procSno;
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
	
	@Length(min=0, max=100, message="载体id长度必须介于 0 和 100 之间")
	public String getFcId() {
		return fcId;
	}

	public void setFcId(String fcId) {
		this.fcId = fcId;
	}
	
	@Length(min=0, max=100, message="外键长度必须介于 0 和 100 之间")
	public String getFkId() {
		return fkId;
	}

	public void setFkId(String fkId) {
		this.fkId = fkId;
	}
	
	@Length(min=0, max=50, message="载体状态长度必须介于 0 和 50 之间")
	public String getZaitizhuangtai() {
		return zaitizhuangtai;
	}

	public void setZaitizhuangtai(String zaitizhuangtai) {
		this.zaitizhuangtai = zaitizhuangtai;
	}
	
}