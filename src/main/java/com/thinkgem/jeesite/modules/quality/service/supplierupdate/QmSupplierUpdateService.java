/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.supplierupdate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.quality.entity.supplierupdate.QmSupplierUpdate;
import com.thinkgem.jeesite.modules.quality.dao.supplierupdate.QmSupplierUpdateDao;

/**
 * 供应商信息变更Service
 * @author suntao
 * @version 2020-06-04
 */
@Service
@Transactional(readOnly = true)
public class QmSupplierUpdateService extends CrudService<QmSupplierUpdateDao, QmSupplierUpdate> {
	
	@Autowired
	private  QmSupplierUpdateDao qmSupplierUpdateDao;

	public QmSupplierUpdate get(String id) {
		return super.get(id);
	}
	
	public QmSupplierUpdate getByProcessInstID(long processInstID) {
		QmSupplierUpdate qmSupplierUpdate = qmSupplierUpdateDao.getByProcessInstID(processInstID);
		return qmSupplierUpdate;
	}
	
	public List<QmSupplierUpdate> findList(QmSupplierUpdate qmSupplierUpdate) {
		return super.findList(qmSupplierUpdate);
	}
	
	public Page<QmSupplierUpdate> findPage(Page<QmSupplierUpdate> page, QmSupplierUpdate qmSupplierUpdate) {
		return super.findPage(page, qmSupplierUpdate);
	}
	
	@Transactional(readOnly = false)
	public void save(QmSupplierUpdate qmSupplierUpdate) {
		super.save(qmSupplierUpdate);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmSupplierUpdate qmSupplierUpdate) {
		super.delete(qmSupplierUpdate);
	}
	
}