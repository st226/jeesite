/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmHpby2oimag;
import com.thinkgem.jeesite.modules.efm.dao.EfmHpby2oimagDao;

/**
 * 涉密载体回收审批表Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmHpby2oimagService extends CrudService<EfmHpby2oimagDao, EfmHpby2oimag> {

	public EfmHpby2oimag get(String id) {
		return super.get(id);
	}
	
	public List<EfmHpby2oimag> findList(EfmHpby2oimag efmHpby2oimag) {
		return super.findList(efmHpby2oimag);
	}
	
	public Page<EfmHpby2oimag> findPage(Page<EfmHpby2oimag> page, EfmHpby2oimag efmHpby2oimag) {
		return super.findPage(page, efmHpby2oimag);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmHpby2oimag efmHpby2oimag) {
		super.save(efmHpby2oimag);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmHpby2oimag efmHpby2oimag) {
		super.delete(efmHpby2oimag);
	}
	
}