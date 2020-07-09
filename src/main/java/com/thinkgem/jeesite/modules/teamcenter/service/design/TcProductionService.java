/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.service.design;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.teamcenter.entity.design.TcProduction;
import com.thinkgem.jeesite.modules.teamcenter.dao.design.TcProductionDao;

/**
 * 生产图纸Service
 * @author suntao
 * @version 2020-05-25
 */
@Service
@Transactional(readOnly = true)
public class TcProductionService extends CrudService<TcProductionDao, TcProduction> {

	public TcProduction get(String id) {
		return super.get(id);
	}
	
	public List<TcProduction> findList(TcProduction tcProduction) {
		return super.findList(tcProduction);
	}
	
	public Page<TcProduction> findPage(Page<TcProduction> page, TcProduction tcProduction) {
		return super.findPage(page, tcProduction);
	}
	
	@Transactional(readOnly = false)
	public void save(TcProduction tcProduction) {
		super.save(tcProduction);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcProduction tcProduction) {
		super.delete(tcProduction);
	}
	
}