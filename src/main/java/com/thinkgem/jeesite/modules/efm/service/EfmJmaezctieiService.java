/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmJmaezctiei;
import com.thinkgem.jeesite.modules.efm.dao.EfmJmaezctieiDao;

/**
 * 台账Service
 * @author liang
 * @version 2021-11-02
 */
@Service
@Transactional(readOnly = true)
public class EfmJmaezctieiService extends CrudService<EfmJmaezctieiDao, EfmJmaezctiei> {

	public EfmJmaezctiei get(String id) {
		return super.get(id);
	}
	
	public List<EfmJmaezctiei> findList(EfmJmaezctiei efmJmaezctiei) {
		return super.findList(efmJmaezctiei);
	}
	
	public Page<EfmJmaezctiei> findPage(Page<EfmJmaezctiei> page, EfmJmaezctiei efmJmaezctiei) {
		return super.findPage(page, efmJmaezctiei);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmJmaezctiei efmJmaezctiei) {
		super.save(efmJmaezctiei);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmJmaezctiei efmJmaezctiei) {
		super.delete(efmJmaezctiei);
	}
	
}