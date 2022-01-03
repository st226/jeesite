/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmJueltygvaw;
import com.thinkgem.jeesite.modules.efm.dao.EfmJueltygvawDao;

/**
 * 单表生成Service
 * @author liang
 * @version 2021-11-02
 */
@Service
@Transactional(readOnly = true)
public class EfmJueltygvawService extends CrudService<EfmJueltygvawDao, EfmJueltygvaw> {

	public EfmJueltygvaw get(String id) {
		return super.get(id);
	}
	
	public List<EfmJueltygvaw> findList(EfmJueltygvaw efmJueltygvaw) {
		return super.findList(efmJueltygvaw);
	}
	
	public Page<EfmJueltygvaw> findPage(Page<EfmJueltygvaw> page, EfmJueltygvaw efmJueltygvaw) {
		return super.findPage(page, efmJueltygvaw);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmJueltygvaw efmJueltygvaw) {
		super.save(efmJueltygvaw);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmJueltygvaw efmJueltygvaw) {
		super.delete(efmJueltygvaw);
	}
	
}