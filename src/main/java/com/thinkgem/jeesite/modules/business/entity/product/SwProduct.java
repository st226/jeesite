/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.product;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 采购设备清单Entity
 * @author suntao
 * @version 2020-04-13
 */
public class SwProduct extends DataEntity<SwProduct> {
	
	private static final long serialVersionUID = 1L;
	private String productName;		// 设备名称
	private String productType;		// 拟采购型号规格
	private String productMade;		// 拟采购厂家
	private Double unitPrice;		// 参考单价（万元）
	private Long productAmount;		// 数量
	private Double totalPrice;		// 总价（万元）
	private String reason;		// 申购原因
	private Office office;		// office_id
	private String officeName;		// 需求部门
	private User user;		// user_id
	private String userName;		// 申请人
	private String productUse;		// 生产用途
	private String remark;		// 备注
	private String orderId;		// 订单
	private String field1;		// field1
	private String state;		// state
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String productDate;		// 采购时间
	private String sbType;		// 设备类型
	private String sbTypeName;		// 设备类型名称
	private String zrUserId;		// 责任人
	private String zrUserName;		// 责任人名称
	private String projectType;		// 计划类型
	private String file;	
	private String type;	
	
	public SwProduct() {
		super();
	}

	public SwProduct(String id){
		super(id);
	}

	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	@ExcelField(title="设备名称", align=2, sort=20)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=255, message="拟采购型号规格长度必须介于 0 和 255 之间")
	@ExcelField(title="拟采购型号规格", align=2, sort=25)
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Length(min=0, max=255, message="拟采购厂家长度必须介于 0 和 255 之间")
	@ExcelField(title="拟采购厂家", align=2, sort=30)
	public String getProductMade() {
		return productMade;
	}

	public void setProductMade(String productMade) {
		this.productMade = productMade;
	}
	
	
	@ExcelField(title="单价", align=2, sort=35)
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	@ExcelField(title="数量", align=2, sort=40)
	public Long getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Long productAmount) {
		this.productAmount = productAmount;
	}
	
	
	@ExcelField(title="总价", align=2, sort=45)
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Length(min=0, max=255, message="申购原因长度必须介于 0 和 255 之间")
	@ExcelField(title="申购原因", align=2, sort=50)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="需求部门长度必须介于 0 和 255 之间")
	@ExcelField(title="需求部门", align=2, sort=55)
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=255, message="申请人长度必须介于 0 和 255 之间")
	@ExcelField(title="申请人", align=2, sort=60)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="生产用途长度必须介于 0 和 255 之间")
	@ExcelField(title="生产用途", align=2, sort=65)
	public String getProductUse() {
		return productUse;
	}

	public void setProductUse(String productUse) {
		this.productUse = productUse;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	@ExcelField(title="备注", align=2, sort=70)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=255, message="订单长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	
	@Length(min=0, max=255, message="采购时间长度必须介于 0 和 255 之间")
	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	
	@Length(min=0, max=255, message="设备类型长度必须介于 0 和 255 之间")
	public String getSbType() {
		return sbType;
	}

	public void setSbType(String sbType) {
		this.sbType = sbType;
	}
	
	@Length(min=0, max=255, message="设备类型名称长度必须介于 0 和 255 之间")
	public String getSbTypeName() {
		return sbTypeName;
	}

	public void setSbTypeName(String sbTypeName) {
		this.sbTypeName = sbTypeName;
	}
	
	@Length(min=0, max=255, message="责任人长度必须介于 0 和 255 之间")
	public String getZrUserId() {
		return zrUserId;
	}

	public void setZrUserId(String zrUserId) {
		this.zrUserId = zrUserId;
	}
	
	@Length(min=0, max=255, message="责任人名称长度必须介于 0 和 255 之间")
	public String getZrUserName() {
		return zrUserName;
	}

	public void setZrUserName(String zrUserName) {
		this.zrUserName = zrUserName;
	}
	
	@Length(min=0, max=255, message="计划类型长度必须介于 0 和 255 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the type
	 */
	@ExcelField(title="采购类型", align=2, sort=100,dictType="product_type")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}