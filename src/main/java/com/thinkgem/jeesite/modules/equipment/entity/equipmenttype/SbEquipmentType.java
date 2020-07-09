/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.equipmenttype;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 仪器设备分类Entity
 * @author suntao
 * @version 2019-12-07
 */
public class SbEquipmentType extends TreeEntity<SbEquipmentType> {
	
	private static final long serialVersionUID = 1L;
	private SbEquipmentType parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private int sort;		// 排序
	private String model;		// model
	private String manager;		// manager
	private String managername;		// managername
	
	public SbEquipmentType() {
		super();
	}

	public SbEquipmentType(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public SbEquipmentType getParent() {
		return parent;
	}

	public void setParent(SbEquipmentType parent) {
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=2000, message="model长度必须介于 0 和 2000 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=200, message="manager长度必须介于 0 和 200 之间")
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=200, message="managername长度必须介于 0 和 200 之间")
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