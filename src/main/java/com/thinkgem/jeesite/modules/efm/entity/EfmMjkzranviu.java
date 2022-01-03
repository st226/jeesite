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
 * 保密本领用申请Entity
 * @author l
 * @version 2021-11-19
 */
public class EfmMjkzranviu extends DataEntity<EfmMjkzranviu> {
	
	private static final long serialVersionUID = 1L;
	private String benzibianhao;		// 本子编号
	private String yonghuid;		// 用户id
	private String shenqingren;		// 申请人
	private String shenqingbumen;		// 申请部门
	private Date shenqingriqi;		// 申请日期
	private String baomibendaxiao;		// 保密本大小
	private String baomibenguige;		// 保密本规格
	private String procSno;		// 编号
	private String formId;		// 表单ID
	private String dataSecretLevel;		// 数据密级
	private String version;		// 版本
	private User createByDept;		// 创建者组织
	private String shenqingrenmiji;		// 申请人密级
	private String sqsl;		// 申请数量
	private String baomibenmiji;		// 保密本密级
	private String shenqingrenid;		// 申请人id
	private String shenqingbumenid;		// 申请部门id
	private String baomibenbianhao;		// 保密本编号
	private String yeshu;		// 页数
	
	public EfmMjkzranviu() {
		super();
	}

	public EfmMjkzranviu(String id){
		super(id);
	}

	@Length(min=0, max=100, message="本子编号长度必须介于 0 和 100 之间")
	public String getBenzibianhao() {
		return benzibianhao;
	}

	public void setBenzibianhao(String benzibianhao) {
		this.benzibianhao = benzibianhao;
	}
	
	@Length(min=0, max=100, message="用户id长度必须介于 0 和 100 之间")
	public String getYonghuid() {
		return yonghuid;
	}

	public void setYonghuid(String yonghuid) {
		this.yonghuid = yonghuid;
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
	
	@Length(min=0, max=100, message="保密本大小长度必须介于 0 和 100 之间")
	public String getBaomibendaxiao() {
		return baomibendaxiao;
	}

	public void setBaomibendaxiao(String baomibendaxiao) {
		this.baomibendaxiao = baomibendaxiao;
	}
	
	@Length(min=0, max=100, message="保密本规格长度必须介于 0 和 100 之间")
	public String getBaomibenguige() {
		return baomibenguige;
	}

	public void setBaomibenguige(String baomibenguige) {
		this.baomibenguige = baomibenguige;
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
	
	@Length(min=0, max=4, message="申请人密级长度必须介于 0 和 4 之间")
	public String getShenqingrenmiji() {
		return shenqingrenmiji;
	}

	public void setShenqingrenmiji(String shenqingrenmiji) {
		this.shenqingrenmiji = shenqingrenmiji;
	}
	
	public String getSqsl() {
		return sqsl;
	}

	public void setSqsl(String sqsl) {
		this.sqsl = sqsl;
	}
	
	@Length(min=0, max=4, message="保密本密级长度必须介于 0 和 4 之间")
	public String getBaomibenmiji() {
		return baomibenmiji;
	}

	public void setBaomibenmiji(String baomibenmiji) {
		this.baomibenmiji = baomibenmiji;
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
	
	@Length(min=0, max=100, message="保密本编号长度必须介于 0 和 100 之间")
	public String getBaomibenbianhao() {
		return baomibenbianhao;
	}

	public void setBaomibenbianhao(String baomibenbianhao) {
		this.baomibenbianhao = baomibenbianhao;
	}
	
	public String getYeshu() {
		return yeshu;
	}

	public void setYeshu(String yeshu) {
		this.yeshu = yeshu;
	}
	
}