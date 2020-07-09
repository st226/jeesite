/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purchase.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purchase.entity.TsPurchase;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;

/**
 * 征订信息维护DAO接口
 * @author suntao
 * @version 2018-01-23
 */
@MyBatisDao
public interface TsPurchaseDao extends CrudDao<TsPurchase> {
	
	public List<TsPurchase> getTsPurchaseByOrder(TsPurchase tsPurchase);
	
}