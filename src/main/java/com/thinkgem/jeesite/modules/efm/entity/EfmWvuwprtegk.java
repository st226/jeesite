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
 * 涉密文件扫描审批备案表Entity
 * @author l
 * @version 2021-11-19
 */
public class EfmWvuwprtegk extends DataEntity<EfmWvuwprtegk> {
	
	private static final long serialVersionUID = 1L;
	private String bumen;		// 部门
	private String xingming;		// 姓名
	private String procSno;		// 编号
	private String wenjianleixing;		// 文件类型
	private Date shenqingriqi;		// 申请日期
	private String smwjzgmj;		// 扫描文件最高密级
	private String wjjsr;		// 文件接收人
	private String yongtu;		// 用途
	private String zgcsld;		// 主管厂所领导
	private String zgcsldId;		// 主管厂所领导(ID)
	private String formId;		// 表单ID
	private String dianhua;		// 电话
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String xingmingid;		// 姓名id
	private String bumenid;		// 部门id
	private String yonghuid;		// 用户id
	private String wjjsrid;		// 文件接收人id
	private String dzgcsldid;		// 主管厂所领导id
	
	public EfmWvuwprtegk() {
		super();
	}

	public EfmWvuwprtegk(String id){
		super(id);
	}

	@Length(min=0, max=100, message="部门长度必须介于 0 和 100 之间")
	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	
	@Length(min=0, max=100, message="姓名长度必须介于 0 和 100 之间")
	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	@Length(min=0, max=50, message="编号长度必须介于 0 和 50 之间")
	public String getProcSno() {
		return procSno;
	}

	public void setProcSno(String procSno) {
		this.procSno = procSno;
	}
	
	@Length(min=0, max=4, message="文件类型长度必须介于 0 和 4 之间")
	public String getWenjianleixing() {
		return wenjianleixing;
	}

	public void setWenjianleixing(String wenjianleixing) {
		this.wenjianleixing = wenjianleixing;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShenqingriqi() {
		return shenqingriqi;
	}

	public void setShenqingriqi(Date shenqingriqi) {
		this.shenqingriqi = shenqingriqi;
	}
	
	@Length(min=0, max=4, message="扫描文件最高密级长度必须介于 0 和 4 之间")
	public String getSmwjzgmj() {
		return smwjzgmj;
	}

	public void setSmwjzgmj(String smwjzgmj) {
		this.smwjzgmj = smwjzgmj;
	}
	
	@Length(min=0, max=100, message="文件接收人长度必须介于 0 和 100 之间")
	public String getWjjsr() {
		return wjjsr;
	}

	public void setWjjsr(String wjjsr) {
		this.wjjsr = wjjsr;
	}
	
	@Length(min=0, max=100, message="用途长度必须介于 0 和 100 之间")
	public String getYongtu() {
		return yongtu;
	}

	public void setYongtu(String yongtu) {
		this.yongtu = yongtu;
	}
	
	@Length(min=0, max=100, message="主管厂所领导长度必须介于 0 和 100 之间")
	public String getZgcsld() {
		return zgcsld;
	}

	public void setZgcsld(String zgcsld) {
		this.zgcsld = zgcsld;
	}
	
	@Length(min=0, max=64, message="主管厂所领导(ID)长度必须介于 0 和 64 之间")
	public String getZgcsldId() {
		return zgcsldId;
	}

	public void setZgcsldId(String zgcsldId) {
		this.zgcsldId = zgcsldId;
	}
	
	@Length(min=0, max=64, message="表单ID长度必须介于 0 和 64 之间")
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	@Length(min=0, max=100, message="电话长度必须介于 0 和 100 之间")
	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
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
	
	@Length(min=0, max=100, message="姓名id长度必须介于 0 和 100 之间")
	public String getXingmingid() {
		return xingmingid;
	}

	public void setXingmingid(String xingmingid) {
		this.xingmingid = xingmingid;
	}
	
	@Length(min=0, max=100, message="部门id长度必须介于 0 和 100 之间")
	public String getBumenid() {
		return bumenid;
	}

	public void setBumenid(String bumenid) {
		this.bumenid = bumenid;
	}
	
	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getYonghuid() {
		return yonghuid;
	}

	public void setYonghuid(String yonghuid) {
		this.yonghuid = yonghuid;
	}
	
	@Length(min=0, max=100, message="文件接收人id长度必须介于 0 和 100 之间")
	public String getWjjsrid() {
		return wjjsrid;
	}

	public void setWjjsrid(String wjjsrid) {
		this.wjjsrid = wjjsrid;
	}
	
	@Length(min=0, max=100, message="主管厂所领导id长度必须介于 0 和 100 之间")
	public String getDzgcsldid() {
		return dzgcsldid;
	}

	public void setDzgcsldid(String dzgcsldid) {
		this.dzgcsldid = dzgcsldid;
	}
	
}