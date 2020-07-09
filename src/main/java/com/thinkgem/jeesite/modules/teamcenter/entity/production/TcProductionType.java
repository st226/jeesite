/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.entity.production;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 生产bom树Entity
 * @author suntao
 * @version 2020-05-24
 */
public class TcProductionType extends TreeEntity<TcProductionType> {
	
	private static final long serialVersionUID = 1L;
	private TcProductionType parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String code;		// 编码
	private String drawingNo;		// 图号
	private String stage;		// 阶段
	private String edition;		// 版本
	private Integer sort;		// 排序
	private String model;		// model
	private String manager;		// manager
	private String managername;		// managername
	
	public TcProductionType() {
		super();
	}

	public TcProductionType(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public TcProductionType getParent() {
		return parent;
	}

	public void setParent(TcProductionType parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	@ExcelField(title="名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=100, message="编码长度必须介于 1 和 100 之间")
	@ExcelField(title="编号", align=3, sort=30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=100, message="图号长度必须介于 1 和 100 之间")
	@ExcelField(title="图号", align=4, sort=40)
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	
	@Length(min=1, max=100, message="阶段长度必须介于 1 和 100 之间")
	@ExcelField(title="阶段", align=5, sort=50)
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Length(min=1, max=100, message="版本长度必须介于 1 和 100 之间")
	@ExcelField(title="版本", align=1, sort=60)
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=255, message="model长度必须介于 0 和 255 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=255, message="manager长度必须介于 0 和 255 之间")
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=255, message="managername长度必须介于 0 和 255 之间")
	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}