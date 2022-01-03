/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmSmycmp3wjm;
import com.thinkgem.jeesite.modules.efm.dao.EfmSmycmp3wjmDao;

/**
 * 涉密载体流转审批表Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmSmycmp3wjmService extends CrudService<EfmSmycmp3wjmDao, EfmSmycmp3wjm> {

	public EfmSmycmp3wjm get(String id) {
		return super.get(id);
	}
	
	public List<EfmSmycmp3wjm> findList(EfmSmycmp3wjm efmSmycmp3wjm) {
		return super.findList(efmSmycmp3wjm);
	}
	
	public Page<EfmSmycmp3wjm> findPage(Page<EfmSmycmp3wjm> page, EfmSmycmp3wjm efmSmycmp3wjm) {
		return super.findPage(page, efmSmycmp3wjm);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmSmycmp3wjm efmSmycmp3wjm) {
		super.save(efmSmycmp3wjm);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmSmycmp3wjm efmSmycmp3wjm) {
		super.delete(efmSmycmp3wjm);
	}
	
}