/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmGgtdycieys;
import com.thinkgem.jeesite.modules.efm.dao.EfmGgtdycieysDao;

/**
 * 载体复印申请Service
 * @author l
 * @version 2021-11-19
 */
@Service
@Transactional(readOnly = true)
public class EfmGgtdycieysService extends CrudService<EfmGgtdycieysDao, EfmGgtdycieys> {

	public EfmGgtdycieys get(String id) {
		return super.get(id);
	}
	
	public List<EfmGgtdycieys> findList(EfmGgtdycieys efmGgtdycieys) {
		return super.findList(efmGgtdycieys);
	}
	
	public Page<EfmGgtdycieys> findPage(Page<EfmGgtdycieys> page, EfmGgtdycieys efmGgtdycieys) {
		return super.findPage(page, efmGgtdycieys);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmGgtdycieys efmGgtdycieys) {
		super.save(efmGgtdycieys);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmGgtdycieys efmGgtdycieys) {
		super.delete(efmGgtdycieys);
	}
	
}