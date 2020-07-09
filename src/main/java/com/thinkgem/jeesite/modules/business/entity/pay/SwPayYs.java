/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.entity.pay;

import org.hibernate.validator.constraints.Length;

import java.util.Date;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 请款单Entity
 * @author suntao
 * @version 2020-03-20
 */
public class SwPayYs extends DataEntity<SwPay> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 合同ID
	private String productAmount;
	private String productType;
	private String productMade;
	private String officeName;
	
	private String type;
	private String userName;		// 合同ID
	private String field7DateEnd;		// 合同ID
	private String amount;		// 合同ID
	
	private String code;		// 合同ID
	private String treat1;		// 合同编号
	private String completionTime1;		// 合同名称
	private String treat2;		// 付款方式
	private String completionTime2;		// 供应商ID
	private String treat3;		// 供应商名称
	private String completionTime3;		// 供应商电话
	private String contratePaid;		// 合同总额
	private String treat;		// 已付款额
	private String projectDate;		// 本次付款额
	private String contrateTreat;		// 开户行
	private String projectType;
	
	
	public SwPayYs() {
		super();
	}

	public SwPayYs(String id){
		super(id);
	}

	/**
	 * @return the name
	 */
	@ExcelField(title="采购任务名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	@ExcelField(title="执行状态", align=2, sort=30,dictType="swPay_state")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the user_name
	 */
	@ExcelField(title="责任人", align=2, sort=210)
	public String getUserName() {
		return userName;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the field7_date_end
	 */
	@ExcelField(title="到货时间", align=2, sort=200)
	public String getField7DateEnd() {
		return field7DateEnd;
	}

	/**
	 * @param field7_date_end the field7_date_end to set
	 */
	public void setField7DateEnd(String field7DateEnd) {
		this.field7DateEnd = field7DateEnd;
	}

	/**
	 * @return the amount
	 */
	@ExcelField(title="金额", align=2, sort=40)
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the code
	 */
	@ExcelField(title="合同编号", align=2, sort=50)
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the treat1
	 */
	@ExcelField(title="首付款金额", align=2, sort=60)
	public String getTreat1() {
		return treat1;
	}

	/**
	 * @param treat1 the treat1 to set
	 */
	public void setTreat1(String treat1) {
		this.treat1 = treat1;
	}

	/**
	 * @return the completion_time1
	 */
	@ExcelField(title="首付款时间", align=2, sort=70)
	public String getCompletionTime1() {
		return completionTime1;
	}

	/**
	 * @param completionTime1 the completionTime1 to set
	 */
	public void setCompletionTime1(String completionTime1) {
		this.completionTime1 = completionTime1;
	}

	/**
	 * @return the treat2
	 */
	@ExcelField(title="到货款金额", align=2, sort=80)
	public String getTreat2() {
		return treat2;
	}

	/**
	 * @param treat2 the treat2 to set
	 */
	public void setTreat2(String treat2) {
		this.treat2 = treat2;
	}

	/**
	 * @return the completionTime2
	 */
	@ExcelField(title="到货款时间", align=2, sort=90)
	public String getCompletionTime2() {
		return completionTime2;
	}

	/**
	 * @param completionTime2 the completionTime2 to set
	 */
	public void setCompletionTime2(String completionTime2) {
		this.completionTime2 = completionTime2;
	}

	/**
	 * @return the completionTime3
	 */
	@ExcelField(title="尾款时间", align=2, sort=110)
	public String getCompletionTime3() {
		return completionTime3;
	}

	/**
	 * @param completionTime3 the completionTime3 to set
	 */
	public void setCompletionTime3(String completionTime3) {
		this.completionTime3 = completionTime3;
	}

	/**
	 * @return the contrate_paid
	 */
	@ExcelField(title="已付款金额", align=2, sort=120)
	public String getContratePaid() {
		return contratePaid;
	}

	/**
	 * @param contratePaid the contratePaid to set
	 */
	public void setContratePaid(String contratePaid) {
		this.contratePaid = contratePaid;
	}

	/**
	 * @return the treat3
	 */
	@ExcelField(title="尾款金额", align=2, sort=100)
	public String getTreat3() {
		return treat3;
	}

	/**
	 * @param treat3 the treat3 to set
	 */
	public void setTreat3(String treat3) {
		this.treat3 = treat3;
	}

	/**
	 * @return the treat
	 */
	@ExcelField(title="欠款", align=2, sort=130)
	public String getTreat() {
		return treat;
	}

	/**
	 * @param treat the treat to set
	 */
	public void setTreat(String treat) {
		this.treat = treat;
	}

	/**
	 * @return the project_date
	 */
	@ExcelField(title="计划付款时间", align=2, sort=150)
	public String getProjectDate() {
		return projectDate;
	}

	/**
	 * @param project_date the project_date to set
	 */
	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}

	/**
	 * @return the contrateTreat
	 */
	@ExcelField(title="计划付款金额", align=2, sort=140)
	public String getContrateTreat() {
		return contrateTreat;
	}

	/**
	 * @param contrateTreat the contrateTreat to set
	 */
	public void setContrateTreat(String contrateTreat) {
		this.contrateTreat = contrateTreat;
	}

	/**
	 * @return the projectType
	 */
	@ExcelField(title="计划类型", align=2, sort=230)
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * @return the productAmount
	 */
	@ExcelField(title="采购数量", align=2, sort=22)
	public String getProductAmount() {
		return productAmount;
	}

	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * @return the productType
	 */
	@ExcelField(title="型号规格", align=2, sort=24)
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productMade
	 */
	@ExcelField(title="生产厂商", align=2, sort=26)
	public String getProductMade() {
		return productMade;
	}

	/**
	 * @param productMade the productMade to set
	 */
	public void setProductMade(String productMade) {
		this.productMade = productMade;
	}

	/**
	 * @return the officeName
	 */
	@ExcelField(title="需求部门", align=2, sort=28)
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @param officeName the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}



	
}