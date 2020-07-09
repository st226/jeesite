/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.entity.qmresource;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 技术文件管理Entity
 * @author suntao
 * @version 2020-05-08
 */
public class QmResource extends DataEntity<QmResource> {
	
	private static final long serialVersionUID = 1L;
	private String fileType;		// 文件类型
	private String fileTypeName;		// 文件类型
	private String qmType;		// 文件分类
	private String qmTypeName;		// 文件分类
	private String name;		// 标准模板文件名称
	private String basis;		// 编制依据文件/标准
	private String file;		// 文件
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public QmResource() {
		super();
	}

	public QmResource(String id){
		super(id);
	}

	@Length(min=0, max=255, message="文件类型长度必须介于 0 和 255 之间")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=255, message="文件类型长度必须介于 0 和 255 之间")
	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	
	@Length(min=0, max=255, message="文件分类长度必须介于 0 和 255 之间")
	public String getQmType() {
		return qmType;
	}

	public void setQmType(String qmType) {
		this.qmType = qmType;
	}
	
	@Length(min=0, max=255, message="文件分类长度必须介于 0 和 255 之间")
	public String getQmTypeName() {
		return qmTypeName;
	}

	public void setQmTypeName(String qmTypeName) {
		this.qmTypeName = qmTypeName;
	}
	
	@Length(min=0, max=255, message="标准模板文件名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="编制依据文件/标准长度必须介于 0 和 255 之间")
	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}
	
	@Length(min=0, max=2000, message="文件长度必须介于 0 和 2000 之间")
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