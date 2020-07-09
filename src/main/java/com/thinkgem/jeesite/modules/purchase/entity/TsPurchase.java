/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purchase.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 征订信息维护Entity
 * @author suntao
 * @version 2018-01-23
 */
public class TsPurchase extends DataEntity<TsPurchase> {
	
	private static final long serialVersionUID = 1L;
	private String zyType;		// 资源类型
	private String name;		// 资源名称
	private String author;		// 作者
	private String press;		// 出版社
	private String cgType;		// 采购类型
	private String isbn;		// ISBN
	private Long pages;		// 页数
	private String price;		// 价格
	private String zyState;		// 状态
	private String isDouble;		// 是否重复
	private Long count;		// 数量
	private Office office;		// 申请部门
	private User user;		// 申请人
	private String userName ; // 申请人
	private String orderId;		// 订单
	private String isList ;
	
	
	
	

	public TsPurchase() {
		super();
	}

	public TsPurchase(String id){
		super(id);
	}

	@Length(min=1, max=100, message="资源类型长度必须介于 1 和 100 之间")
	@ExcelField(title="资源类型", align=2, sort=10)
	public String getZyType() {
		return zyType;
	}

	public void setZyType(String zyType) {
		this.zyType = zyType;
	}
	
	
	
	
	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	@Length(min=1, max=100, message="资源名称长度必须介于 1 和 100 之间")
	@ExcelField(title="资源名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="作者长度必须介于 0 和 100 之间")
	@ExcelField(title="作者", align=2, sort=30)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=100, message="出版社长度必须介于 0 和 100 之间")
	@ExcelField(title="出版社", align=2, sort=40)
	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}
	
	@Length(min=0, max=100, message="采购类型长度必须介于 0 和 100 之间")
	@ExcelField(title="采购类型", align=2, sort=50)
	public String getCgType() {
		return cgType;
	}

	public void setCgType(String cgType) {
		this.cgType = cgType;
	}
	
	@Length(min=0, max=64, message="ISBN长度必须介于 0 和 64 之间")
	@ExcelField(title="ISBN", align=2, sort=60)
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@ExcelField(title="页数", align=2, sort=70)
	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}
	@ExcelField(title="价格", align=2, sort=80)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	
	public String getZyState() {
		return zyState;
	}

	public void setZyState(String zyState) {
		this.zyState = zyState;
	}
	
	@Length(min=0, max=64, message="是否重复长度必须介于 0 和 64 之间")
	public String getIsDouble() {
		return isDouble;
	}

	public void setIsDouble(String isDouble) {
		this.isDouble = isDouble;
	}
	
	@ExcelField(title="数量", align=2, sort=90)
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	@ExcelField(title="申请部门", align=2, sort=90)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@ExcelField(title="申请人", align=2, sort=100)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="订单长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}