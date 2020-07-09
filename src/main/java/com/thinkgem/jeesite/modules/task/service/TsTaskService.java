/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.task.entity.TsTask;
import com.thinkgem.jeesite.modules.task.dao.TsTaskDao;

/**
 * 采集任务管理Service
 * @author suntao
 * @version 2018-05-21
 */
@Service
@Transactional(readOnly = true)
public class TsTaskService extends CrudService<TsTaskDao, TsTask> {

	public TsTask get(String id) {
		return super.get(id);
	}
	
	public List<TsTask> findList(TsTask tsTask) {
		return super.findList(tsTask);
	}
	
	public Page<TsTask> findPage(Page<TsTask> page, TsTask tsTask) {
		return super.findPage(page, tsTask);
	}
	
	@Transactional(readOnly = false)
	public void save(TsTask tsTask) {
		super.save(tsTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsTask tsTask) {
		super.delete(tsTask);
	}
	
}