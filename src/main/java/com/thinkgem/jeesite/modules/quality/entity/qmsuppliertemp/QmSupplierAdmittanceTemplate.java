/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.qmsuppliertemp;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商文件模板Entity
 * @author suntao
 * @version 2020-04-18
 */
public class QmSupplierAdmittanceTemplate extends DataEntity<QmSupplierAdmittanceTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String qmIndex;		// qm_index
	private String name;		// 文件名称
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public QmSupplierAdmittanceTemplate() {
		super();
	}

	public QmSupplierAdmittanceTemplate(String id){
		super(id);
	}

	@Length(min=0, max=255, message="qm_index长度必须介于 0 和 255 之间")
	public String getQmIndex() {
		return qmIndex;
	}

	public void setQmIndex(String qmIndex) {
		this.qmIndex = qmIndex;
	}
	
	@Length(min=0, max=255, message="文件名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}