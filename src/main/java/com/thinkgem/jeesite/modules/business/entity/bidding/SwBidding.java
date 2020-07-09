/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.bidding;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 内部招投标Entity
 * @author suntao
 * @version 2020-04-10
 */
public class SwBidding extends DataEntity<SwBidding> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// order_id
	private String goodsName;		// 货物名称
	private String goodsAmount;		// 数量
	private String goodsBrand;		// 品牌
	private String address;		// 到货地址
	private Date closingDate;		// 截止日期
	private String projectName;		// 项目名称
	private String goodsComposition;		// 货物组成
	private String technical;		// 技术参数
	private String budget;		// 项目预算
	private String state;		// state
	private String field1;		// field1
	private String completeBy;		// complete_by
	private String field2;		// field2
	private Date completeDate;		// complete_date
	private String field3;		// field3
	private String file;		// file
	private String field4;		// field4
	private String field5;		// field5
	private String field6;		// field6
	private String field7;		// field7
	private String field8;		// field8
	private String field9;		// field9
	private String field10;		// field10
	private Date openingTime;
	private Date winningTime;
	private String signFile;		
	private String notice;		
	private List<SwBiddingSupplier> swBiddingSupplierList = Lists.newArrayList();		// 子表列表
	
	public SwBidding() {
		super();
	}

	public SwBidding(String id){
		super(id);
	}

	@Length(min=0, max=255, message="order_id长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=255, message="货物名称长度必须介于 0 和 255 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	
	@Length(min=0, max=255, message="品牌长度必须介于 0 和 255 之间")
	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	
	@Length(min=0, max=255, message="到货地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	
	@Length(min=0, max=255, message="项目名称长度必须介于 0 和 255 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=2550, message="货物组成长度必须介于 0 和 2550 之间")
	public String getGoodsComposition() {
		return goodsComposition;
	}

	public void setGoodsComposition(String goodsComposition) {
		this.goodsComposition = goodsComposition;
	}
	
	@Length(min=0, max=2550, message="技术参数长度必须介于 0 和 2550 之间")
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="field1长度必须介于 0 和 255 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=255, message="complete_by长度必须介于 0 和 255 之间")
	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}
	
	@Length(min=0, max=255, message="field2长度必须介于 0 和 255 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
	@Length(min=0, max=255, message="field3长度必须介于 0 和 255 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=2000, message="file长度必须介于 0 和 2000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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
	
	@Length(min=0, max=255, message="field6长度必须介于 0 和 255 之间")
	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}
	
	@Length(min=0, max=255, message="field7长度必须介于 0 和 255 之间")
	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}
	
	@Length(min=0, max=255, message="field8长度必须介于 0 和 255 之间")
	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}
	
	@Length(min=0, max=255, message="field9长度必须介于 0 和 255 之间")
	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}
	
	@Length(min=0, max=255, message="field10长度必须介于 0 和 255 之间")
	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}
	
	public List<SwBiddingSupplier> getSwBiddingSupplierList() {
		return swBiddingSupplierList;
	}

	public void setSwBiddingSupplierList(List<SwBiddingSupplier> swBiddingSupplierList) {
		this.swBiddingSupplierList = swBiddingSupplierList;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getWinningTime() {
		return winningTime;
	}

	public void setWinningTime(Date winningTime) {
		this.winningTime = winningTime;
	}

	public String getSignFile() {
		return signFile;
	}

	public void setSignFile(String signFile) {
		this.signFile = signFile;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}