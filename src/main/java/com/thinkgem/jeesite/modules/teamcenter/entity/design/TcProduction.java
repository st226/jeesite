/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.entity.design;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 生产图纸Entity
 * @author suntao
 * @version 2020-05-25
 */
public class TcProduction extends DataEntity<TcProduction> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;		// 父级编号
	private String name;		// 名称
	private String code;		// 编码
	private String drawingNo;		// 图号
	private String stage;		// 阶段
	private String edition;		// 版本
	private String file;		// 设计文件
	private String filepdf;		// PDF版本
	private String sort;		// 排序
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	
	public TcProduction() {
		super();
	}

	public TcProduction(String id){
		super(id);
	}

	@Length(min=1, max=64, message="父级编号长度必须介于 1 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=100, message="图号长度必须介于 0 和 100 之间")
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	
	@Length(min=0, max=100, message="阶段长度必须介于 0 和 100 之间")
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Length(min=0, max=100, message="版本长度必须介于 0 和 100 之间")
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	@Length(min=0, max=1000, message="设计文件长度必须介于 0 和 1000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=1000, message="PDF版本长度必须介于 0 和 1000 之间")
	public String getFilepdf() {
		return filepdf;
	}

	public void setFilepdf(String filepdf) {
		this.filepdf = filepdf;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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