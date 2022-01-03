/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 外来载体入账登记申请Entity
 * @author l
 * @version 2021-11-19
 */
public class EfmPdlg1he6zd extends DataEntity<EfmPdlg1he6zd> {
	
	private static final long serialVersionUID = 1L;
	private Date ruzhangshijian;		// 入账时间
	private String beizhu;		// 备注
	private String formId;		// 表单ID
	private String procSno;		// 编号
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String bumen;		// 责任部门
	private String zerenren;		// 责任人
	private String zerenrenid;		// 责任人id
	private String yonghuid;		// 用户id
	private String zerenrenbumenid;		// 责任人部门id
	
	public EfmPdlg1he6zd() {
		super();
	}

	public EfmPdlg1he6zd(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRuzhangshijian() {
		return ruzhangshijian;
	}

	public void setRuzhangshijian(Date ruzhangshijian) {
		this.ruzhangshijian = ruzhangshijian;
	}
	
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	@Length(min=0, max=64, message="表单ID长度必须介于 0 和 64 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=50, message="编号长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=100, message="责任部门长度必须介于 0 和 100 之间")
	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	
	@Length(min=0, max=100, message="责任人长度必须介于 0 和 100 之间")
	public String getZerenren() {
		return zerenren;
	}

	public void setZerenren(String zerenren) {
		this.zerenren = zerenren;
	}
	
	@Length(min=0, max=100, message="责任人id长度必须介于 0 和 100 之间")
	public String getZerenrenid() {
		return zerenrenid;
	}

	public void setZerenrenid(String zerenrenid) {
		this.zerenrenid = zerenrenid;
	}
	
	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getYonghuid() {
		return yonghuid;
	}

	public void setYonghuid(String yonghuid) {
		this.yonghuid = yonghuid;
	}
	
	@Length(min=0, max=100, message="责任人部门id长度必须介于 0 和 100 之间")
	public String getZerenrenbumenid() {
		return zerenrenbumenid;
	}

	public void setZerenrenbumenid(String zerenrenbumenid) {
		this.zerenrenbumenid = zerenrenbumenid;
	}
	
}