/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.supplier.entity.TsSupplier;

/**
 * 供应商管理DAO接口
 * @author suntao
 * @version 2018-01-22
 */
@MyBatisDao
public interface TsSupplierDao extends CrudDao<TsSupplier> {
	
}