/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sbdeploy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sbdeploy.entity.SbDeploy;
import com.thinkgem.jeesite.modules.borrow.dao.sbborrow.SbBorrowDao;
import com.thinkgem.jeesite.modules.sbdeploy.dao.SbDeployDao;

/**
 * 标签配置Service
 * @author suntao
 * @version 2020-03-17
 */
@Service
@Transactional(readOnly = true)
public class SbDeployService extends CrudService<SbDeployDao, SbDeploy> {
	
	@Autowired
	private SbDeployDao sbDeployDao;

	public SbDeploy get(String id) {
		return super.get(id);
	}
	
	public List<SbDeploy> findList(SbDeploy sbDeploy) {
		return super.findList(sbDeploy);
	}
	
	public SbDeploy getByUserId(SbDeploy sbDeploy){
		return sbDeployDao.getByUserId(sbDeploy);
	}
	
	public Page<SbDeploy> findPage(Page<SbDeploy> page, SbDeploy sbDeploy) {
		return super.findPage(page, sbDeploy);
	}
	
	@Transactional(readOnly = false)
	public void save(SbDeploy sbDeploy) {
		super.save(sbDeploy);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbDeploy sbDeploy) {
		super.delete(sbDeploy);
	}
	
}