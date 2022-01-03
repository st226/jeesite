/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmXacqjkussd;
import com.thinkgem.jeesite.modules.efm.dao.EfmXacqjkussdDao;

/**
 * 涉密载体对外移交审批表Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmXacqjkussdService extends CrudService<EfmXacqjkussdDao, EfmXacqjkussd> {

	public EfmXacqjkussd get(String id) {
		return super.get(id);
	}
	
	public List<EfmXacqjkussd> findList(EfmXacqjkussd efmXacqjkussd) {
		return super.findList(efmXacqjkussd);
	}
	
	public Page<EfmXacqjkussd> findPage(Page<EfmXacqjkussd> page, EfmXacqjkussd efmXacqjkussd) {
		return super.findPage(page, efmXacqjkussd);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmXacqjkussd efmXacqjkussd) {
		super.save(efmXacqjkussd);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmXacqjkussd efmXacqjkussd) {
		super.delete(efmXacqjkussd);
	}
	
}