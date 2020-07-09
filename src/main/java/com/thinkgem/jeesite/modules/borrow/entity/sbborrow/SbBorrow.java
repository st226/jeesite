/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.entity.sbborrow;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;

import com.eos.workflow.data.WFWorkItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 设备借用Entity
 * @author suntao
 * @version 2019-12-26
 */
public class SbBorrow extends DataEntity<SbBorrow> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;		// processinstid
	private String state;		// 状态
	private Office office;		// 借用部门
	private User user;		// 借用人
	private String useLocal;		// 借用地方
	private Date borrowTo;		// 借用到
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private List<SbBorrowChild> sbBorrowChildList = Lists.newArrayList();		// 子表列表
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public SbBorrow() {
		super();
	}

	public SbBorrow(String id){
		super(id);
	}

	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=20, message="状态长度必须介于 0 和 20 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=200, message="借用地方长度必须介于 0 和 200 之间")
	public String getUseLocal() {
		return useLocal;
	}

	public void setUseLocal(String useLocal) {
		this.useLocal = useLocal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBorrowTo() {
		return borrowTo;
	}

	public void setBorrowTo(Date borrowTo) {
		this.borrowTo = borrowTo;
	}
	
	@Length(min=0, max=200, message="field1长度必须介于 0 和 200 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=200, message="field2长度必须介于 0 和 200 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=200, message="field3长度必须介于 0 和 200 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=200, message="field4长度必须介于 0 和 200 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=200, message="field5长度必须介于 0 和 200 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	public List<SbBorrowChild> getSbBorrowChildList() {
		return sbBorrowChildList;
	}

	public void setSbBorrowChildList(List<SbBorrowChild> sbBorrowChildList) {
		this.sbBorrowChildList = sbBorrowChildList;
	}

	public List<Examine> getExamineList() {
		return examineList;
	}

	public void setExamineList(List<Examine> examineList) {
		this.examineList = examineList;
	}

	public WFWorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WFWorkItem workItem) {
		this.workItem = workItem;
	}
}