/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.quality.entity.purchase.QmSupplierPurchase;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplierAdmittance;
import com.thinkgem.jeesite.modules.quality.dao.purchase.QmSupplierPurchaseDao;
import com.thinkgem.jeesite.modules.quality.dao.qmsupplier.QmSupplierAdmittanceDao;

/**
 * 合格供方目录外采购申请表Service
 * @author suntao
 * @version 2020-06-04
 */
@Service
@Transactional(readOnly = true)
public class QmSupplierPurchaseService extends CrudService<QmSupplierPurchaseDao, QmSupplierPurchase> {
	
	@Autowired
	private QmSupplierPurchaseDao qmSupplierPurchaseDao;
	

	public QmSupplierPurchase get(String id) {
		return super.get(id);
	}
	
	public QmSupplierPurchase getByProcessInstID(long processInstID) {
		QmSupplierPurchase qmSupplierPurchase = qmSupplierPurchaseDao.getByProcessInstID(processInstID);
		return qmSupplierPurchase;
	}
	
	public List<QmSupplierPurchase> findList(QmSupplierPurchase qmSupplierPurchase) {
		return super.findList(qmSupplierPurchase);
	}
	
	public Page<QmSupplierPurchase> findPage(Page<QmSupplierPurchase> page, QmSupplierPurchase qmSupplierPurchase) {
		return super.findPage(page, qmSupplierPurchase);
	}
	
	@Transactional(readOnly = false)
	public void save(QmSupplierPurchase qmSupplierPurchase) {
		super.save(qmSupplierPurchase);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmSupplierPurchase qmSupplierPurchase) {
		super.delete(qmSupplierPurchase);
	}
	
}