/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.receive;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仪器设备开箱验收Entity
 * @author suntao
 * @version 2020-04-11
 */
public class SwReceive extends DataEntity<SwReceive> {
	
	private static final long serialVersionUID = 1L;
	private String state;		// state
	private String orderId;		// order_id
	private String voucherNo;		// 凭证号
	private String planNumber;		// 计划编号
	private String items;		// 所属项目
	private String assets;		// 资产归属
	private String sbName;		// 设备名称
	private String contractId;		// 合同ID
	private String contractCode;		// 合同编号
	private Office office;		// office_id
	private String officeName;		// 申请部门
	private User user;		// user_id
	private String userName;		// 申请人
	private String made;		// 制造商
	private Date productionDate;		// 出厂日期
	private Date arrivalDate;		// 到货日期
	private String contactInformation;		// 联系方式
	private String location;		// 开箱地点
	private String supplier;		// 供应商
	private String supplierUser;		// 供应商联系人
	private String supplierPhone;		// 供应商联系方式
	private String isAgreement;		// 装箱货物是否与合同（技术协议）一致
	private String isAppearance;		// 货物外观完整性检查
	private String isNorms;		// 规格是否与合同（技术协议）一致
	private String isModel;		// 型号是否合同（技术协议）一致
	private String factoryNumber;		// 出厂编号
	private String acceptor;		// 验收人
	private Date acceptorDate;		// 验收日期
	private String archives;		// 档案室
	private Date archivesDate;		// 档案接收日期
	private String sbModel;		// 型号
	private String sbNorms;		// 规格
	private String sbOriginalValue;		// 原值
	private String sbPower;		// 功率
	private String leader;		// 单位领导
	private String file;		// file
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String field6;		// field6
	private String field7;		// field7
	private String field8;		// field8
	private List<SwReceiveData> swReceiveDataList = Lists.newArrayList();		// 子表列表
	private List<SwReceiveEquipment> swReceiveEquipmentList = Lists.newArrayList();		// 子表列表
	
	public SwReceive() {
		super();
	}

	public SwReceive(String id){
		super(id);
	}

	@Length(min=0, max=255, message="state长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="order_id长度必须介于 0 和 255 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=255, message="凭证号长度必须介于 0 和 255 之间")
	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	@Length(min=0, max=255, message="计划编号长度必须介于 0 和 255 之间")
	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	
	@Length(min=0, max=255, message="所属项目长度必须介于 0 和 255 之间")
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
	@Length(min=0, max=255, message="资产归属长度必须介于 0 和 255 之间")
	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}
	
	@Length(min=0, max=255, message="设备名称长度必须介于 0 和 255 之间")
	public String getSbName() {
		return sbName;
	}

	public void setSbName(String sbName) {
		this.sbName = sbName;
	}
	
	@Length(min=0, max=255, message="合同ID长度必须介于 0 和 255 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=255, message="合同编号长度必须介于 0 和 255 之间")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="申请部门长度必须介于 0 和 255 之间")
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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="制造商长度必须介于 0 和 255 之间")
	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Length(min=0, max=255, message="联系方式长度必须介于 0 和 255 之间")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	@Length(min=0, max=255, message="开箱地点长度必须介于 0 和 255 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=255, message="供应商长度必须介于 0 和 255 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=255, message="供应商联系人长度必须介于 0 和 255 之间")
	public String getSupplierUser() {
		return supplierUser;
	}

	public void setSupplierUser(String supplierUser) {
		this.supplierUser = supplierUser;
	}
	
	@Length(min=0, max=255, message="供应商联系方式长度必须介于 0 和 255 之间")
	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	
	@Length(min=0, max=255, message="装箱货物是否与合同（技术协议）一致长度必须介于 0 和 255 之间")
	public String getIsAgreement() {
		return isAgreement;
	}

	public void setIsAgreement(String isAgreement) {
		this.isAgreement = isAgreement;
	}
	
	@Length(min=0, max=255, message="货物外观完整性检查长度必须介于 0 和 255 之间")
	public String getIsAppearance() {
		return isAppearance;
	}

	public void setIsAppearance(String isAppearance) {
		this.isAppearance = isAppearance;
	}
	
	@Length(min=0, max=255, message="规格是否与合同（技术协议）一致长度必须介于 0 和 255 之间")
	public String getIsNorms() {
		return isNorms;
	}

	public void setIsNorms(String isNorms) {
		this.isNorms = isNorms;
	}
	
	@Length(min=0, max=255, message="型号是否合同（技术协议）一致长度必须介于 0 和 255 之间")
	public String getIsModel() {
		return isModel;
	}

	public void setIsModel(String isModel) {
		this.isModel = isModel;
	}
	
	@Length(min=0, max=255, message="出厂编号长度必须介于 0 和 255 之间")
	public String getFactoryNumber() {
		return factoryNumber;
	}

	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}
	
	@Length(min=0, max=255, message="验收人长度必须介于 0 和 255 之间")
	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcceptorDate() {
		return acceptorDate;
	}

	public void setAcceptorDate(Date acceptorDate) {
		this.acceptorDate = acceptorDate;
	}
	
	@Length(min=0, max=255, message="档案室长度必须介于 0 和 255 之间")
	public String getArchives() {
		return archives;
	}

	public void setArchives(String archives) {
		this.archives = archives;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArchivesDate() {
		return archivesDate;
	}

	public void setArchivesDate(Date archivesDate) {
		this.archivesDate = archivesDate;
	}
	
	@Length(min=0, max=255, message="型号长度必须介于 0 和 255 之间")
	public String getSbModel() {
		return sbModel;
	}

	public void setSbModel(String sbModel) {
		this.sbModel = sbModel;
	}
	
	@Length(min=0, max=255, message="规格长度必须介于 0 和 255 之间")
	public String getSbNorms() {
		return sbNorms;
	}

	public void setSbNorms(String sbNorms) {
		this.sbNorms = sbNorms;
	}
	
	@Length(min=0, max=255, message="原值长度必须介于 0 和 255 之间")
	public String getSbOriginalValue() {
		return sbOriginalValue;
	}

	public void setSbOriginalValue(String sbOriginalValue) {
		this.sbOriginalValue = sbOriginalValue;
	}
	
	@Length(min=0, max=255, message="功率长度必须介于 0 和 255 之间")
	public String getSbPower() {
		return sbPower;
	}

	public void setSbPower(String sbPower) {
		this.sbPower = sbPower;
	}
	
	@Length(min=0, max=255, message="单位领导长度必须介于 0 和 255 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=2000, message="file长度必须介于 0 和 2000 之间")
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
	
	public List<SwReceiveData> getSwReceiveDataList() {
		return swReceiveDataList;
	}

	public void setSwReceiveDataList(List<SwReceiveData> swReceiveDataList) {
		this.swReceiveDataList = swReceiveDataList;
	}
	public List<SwReceiveEquipment> getSwReceiveEquipmentList() {
		return swReceiveEquipmentList;
	}

	public void setSwReceiveEquipmentList(List<SwReceiveEquipment> swReceiveEquipmentList) {
		this.swReceiveEquipmentList = swReceiveEquipmentList;
	}
}