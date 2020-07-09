/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.supplier;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.dao.supplier.SwSupplierDao;

/**
 * 供应商管理Service
 * @author suntao
 * @version 2020-03-19
 */
@Service
@Transactional(readOnly = true)
public class SwSupplierService extends CrudService<SwSupplierDao, SwSupplier> {

	public SwSupplier get(String id) {
		return super.get(id);
	}
	
	public List<SwSupplier> findList(SwSupplier swSupplier) {
		return super.findList(swSupplier);
	}
	
	public Page<SwSupplier> findPage(Page<SwSupplier> page, SwSupplier swSupplier) {
		return super.findPage(page, swSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(SwSupplier swSupplier) {
		super.save(swSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwSupplier swSupplier) {
		super.delete(swSupplier);
	}
	
}