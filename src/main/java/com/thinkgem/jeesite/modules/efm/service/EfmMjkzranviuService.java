/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmMjkzranviu;
import com.thinkgem.jeesite.modules.efm.dao.EfmMjkzranviuDao;

/**
 * 保密本领用申请Service
 * @author l
 * @version 2021-11-19
 */
@Service
@Transactional(readOnly = true)
public class EfmMjkzranviuService extends CrudService<EfmMjkzranviuDao, EfmMjkzranviu> {

	public EfmMjkzranviu get(String id) {
		return super.get(id);
	}
	
	public List<EfmMjkzranviu> findList(EfmMjkzranviu efmMjkzranviu) {
		return super.findList(efmMjkzranviu);
	}
	
	public Page<EfmMjkzranviu> findPage(Page<EfmMjkzranviu> page, EfmMjkzranviu efmMjkzranviu) {
		return super.findPage(page, efmMjkzranviu);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmMjkzranviu efmMjkzranviu) {
		super.save(efmMjkzranviu);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmMjkzranviu efmMjkzranviu) {
		super.delete(efmMjkzranviu);
	}
	
}