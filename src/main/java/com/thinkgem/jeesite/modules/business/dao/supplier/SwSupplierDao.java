/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.supplier;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;

/**
 * 供应商管理DAO接口
 * @author suntao
 * @version 2020-03-19
 */
@MyBatisDao
public interface SwSupplierDao extends CrudDao<SwSupplier> {
	
}