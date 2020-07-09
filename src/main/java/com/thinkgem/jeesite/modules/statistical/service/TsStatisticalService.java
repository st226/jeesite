/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.statistical.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.statistical.entity.TsStatistical;
import com.thinkgem.jeesite.modules.statistical.dao.TsStatisticalDao;

/**
 * 统计分析Service
 * @author suntao
 * @version 2018-02-04
 */
@Service
@Transactional(readOnly = true)
public class TsStatisticalService extends CrudService<TsStatisticalDao, TsStatistical> {

	public TsStatistical get(String id) {
		return super.get(id);
	}
	
	public List<TsStatistical> findList(TsStatistical tsStatistical) {
		return super.findList(tsStatistical);
	}
	
	public Page<TsStatistical> findPage(Page<TsStatistical> page, TsStatistical tsStatistical) {
		return super.findPage(page, tsStatistical);
	}
	
	@Transactional(readOnly = false)
	public void save(TsStatistical tsStatistical) {
		super.save(tsStatistical);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsStatistical tsStatistical) {
		super.delete(tsStatistical);
	}
	
}