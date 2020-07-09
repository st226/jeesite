/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.qmsupplier;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * @author suntao
 * @version 2020-04-18
 */
public class QmSupplierAdmittance extends DataEntity<QmSupplierAdmittance> {
	
	private static final long serialVersionUID = 1L;
	private QmSupplier supplierId;		// supplier_id 父类
	private String admittanceIndex;		// admittance_index
	private String name;		// name
	private String isSubmit;		// is_submit
	private String file;		// file
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public QmSupplierAdmittance() {
		super();
	}

	public QmSupplierAdmittance(String id){
		super(id);
	}

	public QmSupplierAdmittance(QmSupplier supplierId){
		this.supplierId = supplierId;
	}

	@Length(min=0, max=255, message="supplier_id长度必须介于 0 和 255 之间")
	public QmSupplier getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(QmSupplier supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=255, message="admittance_index长度必须介于 0 和 255 之间")
	public String getAdmittanceIndex() {
		return admittanceIndex;
	}

	public void setAdmittanceIndex(String admittanceIndex) {
		this.admittanceIndex = admittanceIndex;
	}
	
	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="is_submit长度必须介于 0 和 255 之间")
	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	
	@Length(min=0, max=255, message="file长度必须介于 0 和 255 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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