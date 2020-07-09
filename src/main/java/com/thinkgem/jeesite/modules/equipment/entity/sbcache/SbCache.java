/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.sbcache;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备缓存Entity
 * @author suntao
 * @version 2019-12-27
 */
public class SbCache extends DataEntity<SbCache> {
	
	private static final long serialVersionUID = 1L;
	private String sbId;		// 设备ID
	private String sbName;		// 设备名称
	private String sbCode;		// 设备编号
	private String type;		// 类型
	private String userid;		// 人员ID
	
	public SbCache() {
		super();
	}

	public SbCache(String id){
		super(id);
	}

	@Length(min=0, max=100, message="设备ID长度必须介于 0 和 100 之间")
	public String getSbId() {
		return sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}
	
	@Length(min=0, max=100, message="设备名称长度必须介于 0 和 100 之间")
	public String getSbName() {
		return sbName;
	}

	public void setSbName(String sbName) {
		this.sbName = sbName;
	}
	
	@Length(min=0, max=100, message="设备编号长度必须介于 0 和 100 之间")
	public String getSbCode() {
		return sbCode;
	}

	public void setSbCode(String sbCode) {
		this.sbCode = sbCode;
	}
	
	@Length(min=0, max=100, message="类型长度必须介于 0 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="人员ID长度必须介于 0 和 100 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}