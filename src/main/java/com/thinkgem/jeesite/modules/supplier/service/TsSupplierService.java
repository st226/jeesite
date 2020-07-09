/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.supplier.entity.TsSupplier;
import com.thinkgem.jeesite.modules.supplier.dao.TsSupplierDao;

/**
 * 供应商管理Service
 * @author suntao
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class TsSupplierService extends CrudService<TsSupplierDao, TsSupplier> {

	public TsSupplier get(String id) {
		return super.get(id);
	}
	
	public List<TsSupplier> findList(TsSupplier tsSupplier) {
		return super.findList(tsSupplier);
	}
	
	public Page<TsSupplier> findPage(Page<TsSupplier> page, TsSupplier tsSupplier) {
		return super.findPage(page, tsSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(TsSupplier tsSupplier) {
		super.save(tsSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsSupplier tsSupplier) {
		super.delete(tsSupplier);
	}
	
}