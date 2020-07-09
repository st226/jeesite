/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.dao.supplierupdate;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.quality.entity.purchase.QmSupplierPurchase;
import com.thinkgem.jeesite.modules.quality.entity.supplierupdate.QmSupplierUpdate;

/**
 * 供应商信息变更DAO接口
 * @author suntao
 * @version 2020-06-04
 */
@MyBatisDao
public interface QmSupplierUpdateDao extends CrudDao<QmSupplierUpdate> {
	
	public QmSupplierUpdate getByProcessInstID(long processInstID);
	
}