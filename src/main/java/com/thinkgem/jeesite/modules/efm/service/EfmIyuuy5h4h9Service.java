/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmIyuuy5h4h9;
import com.thinkgem.jeesite.modules.efm.dao.EfmIyuuy5h4h9Dao;

/**
 * 列表Service
 * @author liang
 * @version 2021-11-02
 */
@Service
@Transactional(readOnly = true)
public class EfmIyuuy5h4h9Service extends CrudService<EfmIyuuy5h4h9Dao, EfmIyuuy5h4h9> {

	public EfmIyuuy5h4h9 get(String id) {
		return super.get(id);
	}
	
	public List<EfmIyuuy5h4h9> findList(EfmIyuuy5h4h9 efmIyuuy5h4h9) {
		return super.findList(efmIyuuy5h4h9);
	}
	
	public Page<EfmIyuuy5h4h9> findPage(Page<EfmIyuuy5h4h9> page, EfmIyuuy5h4h9 efmIyuuy5h4h9) {
		return super.findPage(page, efmIyuuy5h4h9);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmIyuuy5h4h9 efmIyuuy5h4h9) {
		super.save(efmIyuuy5h4h9);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmIyuuy5h4h9 efmIyuuy5h4h9) {
		super.delete(efmIyuuy5h4h9);
	}
	
}