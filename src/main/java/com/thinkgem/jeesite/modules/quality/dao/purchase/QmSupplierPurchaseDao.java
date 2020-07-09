/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.dao.purchase;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.quality.entity.purchase.QmSupplierPurchase;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;

/**
 * 合格供方目录外采购申请表DAO接口
 * @author suntao
 * @version 2020-06-04
 */
@MyBatisDao
public interface QmSupplierPurchaseDao extends CrudDao<QmSupplierPurchase> {
	
	public QmSupplierPurchase getByProcessInstID(long processInstID);
	
}