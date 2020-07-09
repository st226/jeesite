/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.supplierupdate;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.eos.workflow.data.WFWorkItem;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 供应商信息变更Entity
 * @author suntao
 * @version 2020-06-04
 */
public class QmSupplierUpdate extends DataEntity<QmSupplierUpdate> {
	
	private static final long serialVersionUID = 1L;
	private String processinstid;		// processinstid
	private String state;		// state
	private String type;		// type
	private String supplierId;		// 供应商名称
	private String name;		// 供应商名称
	private String productName;		// 货物名称
	private String helpArmy;		// 外协军种
	private String reason;		// 申请理由
	private String applicant;		// 申请人
	private String original;		// 变更前信息
	private String now;		// 变更后信息
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public QmSupplierUpdate() {
		super();
	}

	public QmSupplierUpdate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="processinstid长度必须介于 0 和 255 之间")
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="type长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="供应商名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="货物名称长度必须介于 0 和 255 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=255, message="外协军种长度必须介于 0 和 255 之间")
	public String getHelpArmy() {
		return helpArmy;
	}

	public void setHelpArmy(String helpArmy) {
		this.helpArmy = helpArmy;
	}
	
	@Length(min=0, max=255, message="申请理由长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	@Length(min=0, max=2000, message="变更前信息长度必须介于 0 和 2000 之间")
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
	
	@Length(min=0, max=2000, message="变更后信息长度必须介于 0 和 2000 之间")
	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="field2长度必须介于 0 和 255 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=255, message="field3长度必须介于 0 和 255 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=255, message="field4长度必须介于 0 和 255 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=255, message="field5长度必须介于 0 和 255 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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