/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmLnydoispac;
import com.thinkgem.jeesite.modules.efm.dao.EfmLnydoispacDao;

/**
 * 涉密载体归档管理Service
 * @author l
 * @version 2021-11-27
 */
@Service
@Transactional(readOnly = true)
public class EfmLnydoispacService extends CrudService<EfmLnydoispacDao, EfmLnydoispac> {

	public EfmLnydoispac get(String id) {
		return super.get(id);
	}
	
	public List<EfmLnydoispac> findList(EfmLnydoispac efmLnydoispac) {
		return super.findList(efmLnydoispac);
	}
	
	public Page<EfmLnydoispac> findPage(Page<EfmLnydoispac> page, EfmLnydoispac efmLnydoispac) {
		return super.findPage(page, efmLnydoispac);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmLnydoispac efmLnydoispac) {
		super.save(efmLnydoispac);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmLnydoispac efmLnydoispac) {
		super.delete(efmLnydoispac);
	}
	
}