/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebook.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图册资源维护Entity
 * @author suntao
 * @version 2018-01-31
 */
public class TsResourceBook extends DataEntity<TsResourceBook> {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;		// 资源ID
	private String keyCode;		// 索书号
	private String resourceCode;		// 资源号
	private String bookCode;		// 册号
	private String bookState;		// 册状态
	private String staff;		// 借阅人
	private String local;		// 位置信息
	private String borrowState;		// 借阅状态
	
	public TsResourceBook() {
		super();
	}

	public TsResourceBook(String id){
		super(id);
	}

	@Length(min=0, max=100, message="资源ID长度必须介于 0 和 100 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@Length(min=0, max=50, message="索书号长度必须介于 0 和 50 之间")
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	@Length(min=0, max=50, message="资源号长度必须介于 0 和 50 之间")
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@Length(min=0, max=50, message="册号长度必须介于 0 和 50 之间")
	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	
	@Length(min=0, max=100, message="册状态长度必须介于 0 和 100 之间")
	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}
	
	@Length(min=0, max=50, message="借阅人长度必须介于 0 和 50 之间")
	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}
	
	@Length(min=0, max=50, message="位置信息长度必须介于 0 和 50 之间")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=0, max=50, message="借阅状态长度必须介于 0 和 50 之间")
	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}
	
}