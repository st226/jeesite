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
 * 涉密载体回收审批表Entity
 * @author l
 * @version 2021-11-27
 */
public class EfmHpby2oimag extends DataEntity<EfmHpby2oimag> {
	
	private static final long serialVersionUID = 1L;
	private String procSno;		// 编号
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String shenqingren;		// 申请人
	private String shenqingbumen;		// 申请部门
	private Date shenqingriqi;		// 申请日期
	private String lianxidianhua;		// 联系电话
	private String zaitizuigaomiji;		// 载体最高密级
	private String xiaohuiyuanyin;		// 销毁原因
	private String xiaohuiren;		// 销毁人
	private Date xiaohuishijian;		// 销毁时间
	private String shenqingrenid;		// 申请人id
	private String shenqingbumenid;		// 申请部门id
	private String yonghuid;		// 用户id
	private String xiaohuirenid;		// 销毁人id
	private String beizhu;		// 备注
	
	public EfmHpby2oimag() {
		super();
	}

	public EfmHpby2oimag(String id){
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShenqingriqi() {
		return shenqingriqi;
	}

	public void setShenqingriqi(Date shenqingriqi) {
		this.shenqingriqi = shenqingriqi;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}
	
	@Length(min=0, max=4, message="载体最高密级长度必须介于 0 和 4 之间")
	public String getZaitizuigaomiji() {
		return zaitizuigaomiji;
	}

	public void setZaitizuigaomiji(String zaitizuigaomiji) {
		this.zaitizuigaomiji = zaitizuigaomiji;
	}
	
	public String getXiaohuiyuanyin() {
		return xiaohuiyuanyin;
	}

	public void setXiaohuiyuanyin(String xiaohuiyuanyin) {
		this.xiaohuiyuanyin = xiaohuiyuanyin;
	}
	
	@Length(min=0, max=100, message="销毁人长度必须介于 0 和 100 之间")
	public String getXiaohuiren() {
		return xiaohuiren;
	}

	public void setXiaohuiren(String xiaohuiren) {
		this.xiaohuiren = xiaohuiren;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getXiaohuishijian() {
		return xiaohuishijian;
	}

	public void setXiaohuishijian(Date xiaohuishijian) {
		this.xiaohuishijian = xiaohuishijian;
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
	
	@Length(min=0, max=100, message="销毁人id长度必须介于 0 和 100 之间")
	public String getXiaohuirenid() {
		return xiaohuirenid;
	}

	public void setXiaohuirenid(String xiaohuirenid) {
		this.xiaohuirenid = xiaohuirenid;
	}
	
	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
}