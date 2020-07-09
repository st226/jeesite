/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.qmsuppliertemp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.quality.entity.qmsuppliertemp.QmSupplierAdmittanceTemplate;
import com.thinkgem.jeesite.modules.quality.dao.qmsuppliertemp.QmSupplierAdmittanceTemplateDao;

/**
 * 供应商文件模板Service
 * @author suntao
 * @version 2020-04-18
 */
@Service
@Transactional(readOnly = true)
public class QmSupplierAdmittanceTemplateService extends CrudService<QmSupplierAdmittanceTemplateDao, QmSupplierAdmittanceTemplate> {

	public QmSupplierAdmittanceTemplate get(String id) {
		return super.get(id);
	}
	
	public List<QmSupplierAdmittanceTemplate> findList(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate) {
		return super.findList(qmSupplierAdmittanceTemplate);
	}
	
	public Page<QmSupplierAdmittanceTemplate> findPage(Page<QmSupplierAdmittanceTemplate> page, QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate) {
		return super.findPage(page, qmSupplierAdmittanceTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate) {
		super.save(qmSupplierAdmittanceTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate) {
		super.delete(qmSupplierAdmittanceTemplate);
	}
	
}