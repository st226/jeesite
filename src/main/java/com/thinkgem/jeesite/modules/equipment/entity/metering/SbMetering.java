/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.metering;

import org.hibernate.validator.constraints.Length;
import java.util.List;

import com.eos.workflow.data.WFWorkItem;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 计量管理Entity
 * @author suntao
 * @version 2020-01-01
 */
public class SbMetering extends DataEntity<SbMetering> {
	
	private static final long serialVersionUID = 1L;
	private Long processinstid;		// processinstid
	private String state;		// 状态
	private String code;		// 编号
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private int totle;		// field5
	private int sureTotle;		// field5
	private double rate;		// field5
	private List<SbMeteringChild> sbMeteringChildList = Lists.newArrayList();		// 子表列表
	private List<Examine> examineList ;		// 子表列表
	private WFWorkItem workItem;
	
	public SbMetering() {
		super();
	}

	public SbMetering(String id){
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
	
	@Length(min=0, max=20, message="编号长度必须介于 0 和 20 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	public List<SbMeteringChild> getSbMeteringChildList() {
		return sbMeteringChildList;
	}

	public void setSbMeteringChildList(List<SbMeteringChild> sbMeteringChildList) {
		this.sbMeteringChildList = sbMeteringChildList;
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

	/**
	 * @return the totle
	 */
	public int getTotle() {
		return totle;
	}

	/**
	 * @param totle the totle to set
	 */
	public void setTotle(int totle) {
		this.totle = totle;
	}

	/**
	 * @return the sureTotle
	 */
	public int getSureTotle() {
		return sureTotle;
	}

	/**
	 * @param sureTotle the sureTotle to set
	 */
	public void setSureTotle(int sureTotle) {
		this.sureTotle = sureTotle;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
}