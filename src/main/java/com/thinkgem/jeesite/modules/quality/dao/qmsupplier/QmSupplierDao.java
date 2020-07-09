/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.dao.qmsupplier;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;

/**
 * 供应商管理DAO接口
 * @author suntao
 * @version 2020-04-18
 */
@MyBatisDao
public interface QmSupplierDao extends CrudDao<QmSupplier> {
	
	public QmSupplier getByProcessInstID(long processInstID);
	
}