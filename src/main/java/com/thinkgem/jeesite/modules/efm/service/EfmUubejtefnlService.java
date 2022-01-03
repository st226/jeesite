/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmUubejtefnl;
import com.thinkgem.jeesite.modules.efm.dao.EfmUubejtefnlDao;

/**
 * 涉密载体销毁回收管理Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmUubejtefnlService extends CrudService<EfmUubejtefnlDao, EfmUubejtefnl> {

	public EfmUubejtefnl get(String id) {
		return super.get(id);
	}
	
	public List<EfmUubejtefnl> findList(EfmUubejtefnl efmUubejtefnl) {
		return super.findList(efmUubejtefnl);
	}
	
	public Page<EfmUubejtefnl> findPage(Page<EfmUubejtefnl> page, EfmUubejtefnl efmUubejtefnl) {
		return super.findPage(page, efmUubejtefnl);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmUubejtefnl efmUubejtefnl) {
		super.save(efmUubejtefnl);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmUubejtefnl efmUubejtefnl) {
		super.delete(efmUubejtefnl);
	}
	
}