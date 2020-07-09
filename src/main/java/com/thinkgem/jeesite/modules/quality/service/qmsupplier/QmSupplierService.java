/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.qmsupplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.quality.dao.qmsupplier.QmSupplierDao;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplierAdmittance;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.quality.dao.qmsupplier.QmSupplierAdmittanceDao;

/**
 * 供应商管理Service
 * @author suntao
 * @version 2020-04-18
 */
@Service
@Transactional(readOnly = true)
public class QmSupplierService extends CrudService<QmSupplierDao, QmSupplier> {

	@Autowired
	private QmSupplierAdmittanceDao qmSupplierAdmittanceDao;
	
	@Autowired
	private QmSupplierDao qmSupplierDao;
	
	public QmSupplier get(String id) {
		QmSupplier qmSupplier = super.get(id);
		qmSupplier.setQmSupplierAdmittanceList(qmSupplierAdmittanceDao.findList(new QmSupplierAdmittance(qmSupplier)));
		return qmSupplier;
	}
	
	
	public QmSupplier getByProcessInstID(long processInstID) {
		QmSupplier qmSupplier = qmSupplierDao.getByProcessInstID(processInstID);
		qmSupplier.setQmSupplierAdmittanceList(qmSupplierAdmittanceDao.findList(new QmSupplierAdmittance(qmSupplier)));
		return qmSupplier;
	}
	
	
	public List<QmSupplier> findList(QmSupplier qmSupplier) {
		return super.findList(qmSupplier);
	}
	
	public Page<QmSupplier> findPage(Page<QmSupplier> page, QmSupplier qmSupplier) {
		return super.findPage(page, qmSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(QmSupplier qmSupplier) {
		super.save(qmSupplier);
		for (QmSupplierAdmittance qmSupplierAdmittance : qmSupplier.getQmSupplierAdmittanceList()){
			if (qmSupplierAdmittance.getId() == null){
				continue;
			}
			if (QmSupplierAdmittance.DEL_FLAG_NORMAL.equals(qmSupplierAdmittance.getDelFlag())){
				if (StringUtils.isBlank(qmSupplierAdmittance.getId())){
					qmSupplierAdmittance.setSupplierId(qmSupplier);
					qmSupplierAdmittance.preInsert();
					qmSupplierAdmittanceDao.insert(qmSupplierAdmittance);
				}else{
					qmSupplierAdmittance.preUpdate();
					qmSupplierAdmittanceDao.update(qmSupplierAdmittance);
				}
			}else{
				qmSupplierAdmittanceDao.delete(qmSupplierAdmittance);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(QmSupplier qmSupplier) {
		super.delete(qmSupplier);
		qmSupplierAdmittanceDao.delete(new QmSupplierAdmittance(qmSupplier));
	}
	
}