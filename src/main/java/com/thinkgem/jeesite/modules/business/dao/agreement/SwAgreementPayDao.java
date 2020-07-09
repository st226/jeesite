/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.agreement;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementPay;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;

/**
 * 合同录入DAO接口
 * @author suntao
 * @version 2020-03-24
 */
@MyBatisDao
public interface SwAgreementPayDao extends CrudDao<SwAgreementPay> {
	
	public List<SwAgreementPay> getSwAgreementPay(SwAgreementPay  swAgreementPay);
	
}