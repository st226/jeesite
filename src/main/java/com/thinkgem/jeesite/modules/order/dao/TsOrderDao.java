/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.order.entity.TsOrder;

/**
 * 订单管理DAO接口
 * @author suntao
 * @version 2018-01-22
 */
@MyBatisDao
public interface TsOrderDao extends CrudDao<TsOrder> {
	
}