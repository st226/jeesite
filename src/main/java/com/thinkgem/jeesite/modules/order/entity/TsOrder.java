/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.purchase.entity.TsPurchase;

/**
 * 订单管理Entity
 * @author suntao
 * @version 2018-01-22
 */
public class TsOrder extends DataEntity<TsOrder> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 订单名称
	private String orderNumber;		// 订单编号
	private String gysId;		// 供应商ID
	private String gysName;		// 供应商名称
	private String count;		// 总件数
	private String price;		// 总价格
	private String orderState;		// 状态
	
	private List<TsPurchase> columnList = Lists.newArrayList();	
	
	public TsOrder() {
		super();
	}

	public TsOrder(String id){
		super(id);
	}

	@Length(min=1, max=100, message="订单名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Length(min=0, max=100, message="供应商ID长度必须介于 0 和 100 之间")
	public String getGysId() {
		return gysId;
	}

	public void setGysId(String gysId) {
		this.gysId = gysId;
	}
	
	@Length(min=0, max=100, message="供应商名称长度必须介于 0 和 100 之间")
	public String getGysName() {
		return gysName;
	}

	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public List<TsPurchase> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<TsPurchase> columnList) {
		this.columnList = columnList;
	}
	
	
	
}