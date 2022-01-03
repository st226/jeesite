/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmNihwaogbfu;
import com.thinkgem.jeesite.modules.efm.dao.EfmNihwaogbfuDao;

/**
 * 涉密载体归档审批表Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmNihwaogbfuService extends CrudService<EfmNihwaogbfuDao, EfmNihwaogbfu> {

	public EfmNihwaogbfu get(String id) {
		return super.get(id);
	}
	
	public List<EfmNihwaogbfu> findList(EfmNihwaogbfu efmNihwaogbfu) {
		return super.findList(efmNihwaogbfu);
	}
	
	public Page<EfmNihwaogbfu> findPage(Page<EfmNihwaogbfu> page, EfmNihwaogbfu efmNihwaogbfu) {
		return super.findPage(page, efmNihwaogbfu);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmNihwaogbfu efmNihwaogbfu) {
		super.save(efmNihwaogbfu);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmNihwaogbfu efmNihwaogbfu) {
		super.delete(efmNihwaogbfu);
	}
	
}