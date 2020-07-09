/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.order;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;

/**
 * 采购任务DAO接口
 * @author suntao
 * @version 2020-04-02
 */
@MyBatisDao
public interface SwOrderDao extends CrudDao<SwOrder> {
	
    public String getCode(String code);
	
	public SwOrder getOrderByCode(SwOrder swOrder);

	public String getGyCode(Map paramMap);
	
	public List<SwOrder> findListYear(SwOrder entity);
	public List<SwOrder> findProductListYear(SwOrder entity);
	
}