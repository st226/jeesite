/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.swbiddingpublic;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBiddingSupplier;

/**
 * 公开招投标Entity
 * @author suntao
 * @version 2020-04-21
 */
public class SwBiddingPublic extends DataEntity<SwBiddingPublic> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// order_id
	private String biddingNo;		// 招标编号
	private String goodsName;		// 设备名称
	private String goodsAmount;		// 数量
	private String currency;		// 报价币种
	private String model;		// 报价方式
	private String deliveryDate;		// 交货日期
	private String puse;		// 设备用途及基本要求
	private String parameter;		// 设备要求及主要规格参数
	private String parts;		// 设备附件及零备件
	private String technical;		// 技术服务要求
	private String pcheck;		// 验收标准及验收程序
	private String packaging;		// 机床包装要求及运输方式
	private String port;		// 到货港口
	private String state;		// 状态
	private Date completeDate;		// complete_date
	private String completeBy;		// complete_by
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String file;		// field5
	private String technicalFile;		// field1
	private String reviewDate;		// field2
	private String signIn;		// field3
	private String bidOpening;		// field4
	private String bidWinning;		// field5
	private String agency;		// field1
	private String notice;		// field2
	private String special;		// field3
	private String specialFile;		// field4
	private String bid;		// field5
	private List<SwBiddingSupplier> swBiddingSupplierList = Lists.newArrayList();	
	
	public SwBiddingPublic() {
		super();
	}

	public SwBiddingPublic(String id){
		super(id);
	}

	@Length(min=0, max=255, message="order_id长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=255, message="招标编号长度必须介于 0 和 255 之间")
	public String getBiddingNo() {
		return biddingNo;
	}

	public void setBiddingNo(String biddingNo) {
		this.biddingNo = biddingNo;
	}
	
	@Length(min=0, max=2000, message="设备名称长度必须介于 0 和 2000 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=0, max=2000, message="数量长度必须介于 0 和 2000 之间")
	public String getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	
	@Length(min=0, max=255, message="报价币种长度必须介于 0 和 255 之间")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Length(min=0, max=255, message="报价方式长度必须介于 0 和 255 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=255, message="交货日期长度必须介于 0 和 255 之间")
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Length(min=0, max=2550, message="设备用途及基本要求长度必须介于 0 和 2550 之间")
	public String getPuse() {
		return puse;
	}

	public void setPuse(String puse) {
		this.puse = puse;
	}
	
	@Length(min=0, max=2550, message="设备要求及主要规格参数长度必须介于 0 和 2550 之间")
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	@Length(min=0, max=500, message="设备附件及零备件长度必须介于 0 和 500 之间")
	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}
	
	@Length(min=0, max=2550, message="技术服务要求长度必须介于 0 和 2550 之间")
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	@Length(min=0, max=2550, message="验收标准及验收程序长度必须介于 0 和 2550 之间")
	public String getPcheck() {
		return pcheck;
	}

	public void setPcheck(String pcheck) {
		this.pcheck = pcheck;
	}
	
	@Length(min=0, max=2550, message="机床包装要求及运输方式长度必须介于 0 和 2550 之间")
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	@Length(min=0, max=255, message="到货港口长度必须介于 0 和 255 之间")
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
	@Length(min=0, max=255, message="complete_by长度必须介于 0 和 255 之间")
	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<SwBiddingSupplier> getSwBiddingSupplierList() {
		return swBiddingSupplierList;
	}

	public void setSwBiddingSupplierList(List<SwBiddingSupplier> swBiddingSupplierList) {
		this.swBiddingSupplierList = swBiddingSupplierList;
	}

	public String getTechnicalFile() {
		return technicalFile;
	}

	public void setTechnicalFile(String technicalFile) {
		this.technicalFile = technicalFile;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getBidOpening() {
		return bidOpening;
	}

	public void setBidOpening(String bidOpening) {
		this.bidOpening = bidOpening;
	}

	public String getBidWinning() {
		return bidWinning;
	}

	public void setBidWinning(String bidWinning) {
		this.bidWinning = bidWinning;
	}

	public String getSignIn() {
		return signIn;
	}

	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getSpecialFile() {
		return specialFile;
	}

	public void setSpecialFile(String specialFile) {
		this.specialFile = specialFile;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
	
}