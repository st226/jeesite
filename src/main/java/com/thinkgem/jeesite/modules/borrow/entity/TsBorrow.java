/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资源流通Entity
 * @author suntao
 * @version 2018-01-29
 */
public class TsBorrow extends DataEntity<TsBorrow> {
	
	private static final long serialVersionUID = 1L;
	private String tsId;		// 资源ID
	private String tsName;		// 资源名称
	private String tsKeyCode;		// 资源号
	private String busType;		// 资源类型
	private Office office;		// 借阅部门
	private User user;		// 借阅人员
	private String userCode;		// 借阅人code
	private String borrowState;		// 借阅状态
	private String borrowType;		// 借阅类型
	private Integer frequency;		// 续借次数
	
	public TsBorrow() {
		super();
	}

	public TsBorrow(String id){
		super(id);
	}

	@Length(min=1, max=64, message="资源ID长度必须介于 1 和 64 之间")
	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId;
	}
	
	@Length(min=1, max=100, message="资源名称长度必须介于 1 和 100 之间")
	public String getTsName() {
		return tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}
	
	@Length(min=0, max=100, message="资源号长度必须介于 0 和 100 之间")
	public String getTsKeyCode() {
		return tsKeyCode;
	}

	public void setTsKeyCode(String tsKeyCode) {
		this.tsKeyCode = tsKeyCode;
	}
	
	@Length(min=1, max=100, message="资源类型长度必须介于 1 和 100 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@NotNull(message="借阅部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="借阅人员不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=100, message="借阅人code长度必须介于 1 和 100 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=1, max=100, message="借阅状态长度必须介于 1 和 100 之间")
	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}
	
	@Length(min=1, max=100, message="借阅类型长度必须介于 1 和 100 之间")
	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	
	@Length(min=0, max=11, message="续借次数长度必须介于 0 和 11 之间")
	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
}