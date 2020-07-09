/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.meteringlist;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计量查询管理Entity
 * @author suntao
 * @version 2020-03-26
 */
public class SbMeteringlist extends DataEntity<SbMeteringlist> {
	
	private static final long serialVersionUID = 1L;
	private String sbname;		// 设备名称
	private String yycode;		// 原有编号
	private String sbode;		// 设备编号
	private String type;		// 规格型号
	private String cccode;		// 出厂编号
	private Date djdate;		// 登记日期
	private String amount;		// 数量
	private String dept;		// 责任部门
	private String emp;		// 责任人
	private String local;		// 保管地点
	private String isjl;		// 是否计量
	private String jlfs;		// 计量方式
	private String jdzq;		// 检定周期
	private Date sndyxq;		// 上年度有效期
	private String jcrq;		// 检测日期
	private Date zxyxq;		// 最新有效期
	private String sbstate;		// 设备状态
	private String sbtype;		// 计量类型
	
	public SbMeteringlist() {
		super();
	}

	public SbMeteringlist(String id){
		super(id);
	}

	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getSbname() {
		return sbname;
	}

	public void setSbname(String sbname) {
		this.sbname = sbname;
	}
	
	@Length(min=0, max=255, message="原有编号长度必须介于 0 和 255 之间")
	public String getYycode() {
		return yycode;
	}

	public void setYycode(String yycode) {
		this.yycode = yycode;
	}
	
	@Length(min=0, max=255, message="设备编号长度必须介于 0 和 255 之间")
	public String getSbode() {
		return sbode;
	}

	public void setSbode(String sbode) {
		this.sbode = sbode;
	}
	
	@Length(min=0, max=255, message="规格型号长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="出厂编号长度必须介于 0 和 255 之间")
	public String getCccode() {
		return cccode;
	}

	public void setCccode(String cccode) {
		this.cccode = cccode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDjdate() {
		return djdate;
	}

	public void setDjdate(Date djdate) {
		this.djdate = djdate;
	}
	
	@Length(min=0, max=255, message="数量长度必须介于 0 和 255 之间")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=255, message="责任部门长度必须介于 0 和 255 之间")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@Length(min=0, max=255, message="责任人长度必须介于 0 和 255 之间")
	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}
	
	@Length(min=0, max=255, message="保管地点长度必须介于 0 和 255 之间")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=0, max=255, message="是否计量长度必须介于 0 和 255 之间")
	public String getIsjl() {
		return isjl;
	}

	public void setIsjl(String isjl) {
		this.isjl = isjl;
	}
	
	@Length(min=0, max=255, message="计量方式长度必须介于 0 和 255 之间")
	public String getJlfs() {
		return jlfs;
	}

	public void setJlfs(String jlfs) {
		this.jlfs = jlfs;
	}
	
	@Length(min=0, max=255, message="检定周期长度必须介于 0 和 255 之间")
	public String getJdzq() {
		return jdzq;
	}

	public void setJdzq(String jdzq) {
		this.jdzq = jdzq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSndyxq() {
		return sndyxq;
	}

	public void setSndyxq(Date sndyxq) {
		this.sndyxq = sndyxq;
	}
	
	@Length(min=0, max=255, message="检测日期长度必须介于 0 和 255 之间")
	public String getJcrq() {
		return jcrq;
	}

	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZxyxq() {
		return zxyxq;
	}

	public void setZxyxq(Date zxyxq) {
		this.zxyxq = zxyxq;
	}
	
	@Length(min=0, max=255, message="设备状态长度必须介于 0 和 255 之间")
	public String getSbstate() {
		return sbstate;
	}

	public void setSbstate(String sbstate) {
		this.sbstate = sbstate;
	}
	
	@Length(min=0, max=255, message="计量类型长度必须介于 0 和 255 之间")
	public String getSbtype() {
		return sbtype;
	}

	public void setSbtype(String sbtype) {
		this.sbtype = sbtype;
	}
	
}