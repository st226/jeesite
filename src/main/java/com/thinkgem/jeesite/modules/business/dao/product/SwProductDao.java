/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.product;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;

/**
 * 采购设备清单DAO接口
 * @author suntao
 * @version 2020-04-13
 */
@MyBatisDao
public interface SwProductDao extends CrudDao<SwProduct> {
	
}