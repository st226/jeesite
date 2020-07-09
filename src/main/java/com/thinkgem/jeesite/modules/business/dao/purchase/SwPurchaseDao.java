/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.purchase;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;

/**
 * 仪器申购单DAO接口
 * @author suntao
 * @version 2020-03-21
 */
@MyBatisDao
public interface SwPurchaseDao extends CrudDao<SwPurchase> {
	
}