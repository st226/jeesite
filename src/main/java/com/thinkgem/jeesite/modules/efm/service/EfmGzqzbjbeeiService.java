/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmGzqzbjbeei;
import com.thinkgem.jeesite.modules.efm.dao.EfmGzqzbjbeeiDao;

/**
 * 涉密载体送外印制申请Service
 * @author l
 * @version 2021-11-22
 */
@Service
@Transactional(readOnly = true)
public class EfmGzqzbjbeeiService extends CrudService<EfmGzqzbjbeeiDao, EfmGzqzbjbeei> {

	public EfmGzqzbjbeei get(String id) {
		return super.get(id);
	}
	
	public List<EfmGzqzbjbeei> findList(EfmGzqzbjbeei efmGzqzbjbeei) {
		return super.findList(efmGzqzbjbeei);
	}
	
	public Page<EfmGzqzbjbeei> findPage(Page<EfmGzqzbjbeei> page, EfmGzqzbjbeei efmGzqzbjbeei) {
		return super.findPage(page, efmGzqzbjbeei);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmGzqzbjbeei efmGzqzbjbeei) {
		super.save(efmGzqzbjbeei);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmGzqzbjbeei efmGzqzbjbeei) {
		super.delete(efmGzqzbjbeei);
	}
	
}