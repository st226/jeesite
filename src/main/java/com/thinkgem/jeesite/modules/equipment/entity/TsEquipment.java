/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 试验数据Entity
 * @author suntao
 * @version 2018-05-22
 */
public class TsEquipment extends DataEntity<TsEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String local;		// 试验地
	private String number;		// 试验编号
	private String name;		// 试验名称
	private String busType;		// 试验类型
	private String equipmentid;		// 实体设备ID
	private String taskid;		// 任务ID
	private String classification;		// 试验分类
	private String keyCode;		// 试验号
	private String resourceCode;		// 试验人
	private String author;		// 试验品相
	private String press;		// 试验数据
	private String isbn;		// 试验名称
	private String price;		// 试验单价
	private String publicationDate;		// 试验日期
	private String keyword;		// 试验介绍
	private String zyAbstract;		// 实验内容
	private String bmState;		// 试验状态
	private String storageTime;		// 采集时间
	private String sort;		// 排序
	private String rollCode;		// 其他
	private String files;		// 上传附件
	
	public TsEquipment() {
		super();
	}

	public TsEquipment(String id){
		super(id);
	}

	@Length(min=0, max=50, message="试验地长度必须介于 0 和 50 之间")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=0, max=50, message="试验编号长度必须介于 0 和 50 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=50, message="试验名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="试验类型长度必须介于 0 和 100 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@Length(min=0, max=50, message="实体设备ID长度必须介于 0 和 50 之间")
	public String getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	
	@Length(min=0, max=50, message="任务ID长度必须介于 0 和 50 之间")
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	@Length(min=0, max=50, message="试验分类长度必须介于 0 和 50 之间")
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	@Length(min=0, max=50, message="试验号长度必须介于 0 和 50 之间")
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	@Length(min=0, max=50, message="试验人长度必须介于 0 和 50 之间")
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@Length(min=0, max=50, message="试验品相长度必须介于 0 和 50 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=50, message="试验数据长度必须介于 0 和 50 之间")
	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}
	
	@Length(min=0, max=50, message="试验名称长度必须介于 0 和 50 之间")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=50, message="试验日期长度必须介于 0 和 50 之间")
	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@Length(min=0, max=500, message="试验介绍长度必须介于 0 和 500 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=4000, message="实验内容长度必须介于 0 和 4000 之间")
	public String getZyAbstract() {
		return zyAbstract;
	}

	public void setZyAbstract(String zyAbstract) {
		this.zyAbstract = zyAbstract;
	}
	
	@Length(min=0, max=50, message="试验状态长度必须介于 0 和 50 之间")
	public String getBmState() {
		return bmState;
	}

	public void setBmState(String bmState) {
		this.bmState = bmState;
	}
	
	@Length(min=0, max=50, message="采集时间长度必须介于 0 和 50 之间")
	public String getStorageTime() {
		return storageTime;
	}

	public void setStorageTime(String storageTime) {
		this.storageTime = storageTime;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=50, message="其他长度必须介于 0 和 50 之间")
	public String getRollCode() {
		return rollCode;
	}

	public void setRollCode(String rollCode) {
		this.rollCode = rollCode;
	}
	
	@Length(min=0, max=100, message="上传附件长度必须介于 0 和 100 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
}