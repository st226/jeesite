/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.gen.dao.GenTableDao;
import com.thinkgem.jeesite.modules.resource.dao.TsResourceDao;

/**
 * 图书馆资源管理Service
 * @author suntao
 * @version 2018-01-14
 */
@Service
@Transactional(readOnly = true)
public class TsResourceService extends CrudService<TsResourceDao, TsResource> {
	
	@Autowired
	private TsResourceBusDao tsResourceBusDao;
	
	@Autowired
	private TsResourceDao tsResourceDao;

	public TsResource get(String id) {
		return super.get(id);
	}
	
	public List<TsResource> findList(TsResource tsResource) {
		return super.findList(tsResource);
	}
	
	public List<TsResource> findAllList(TsResource tsResource) {
		System.out.println("2");
		return tsResourceDao.findAllList(tsResource);
	}
	
	public List<TsResource> findAllListNoParam() {
		return tsResourceDao.findAllListNoParam();
	}
	
	public List<TsResourceBus> getBus(TsResourceBus tsResourceBus){
		return tsResourceBusDao.getBus(tsResourceBus) ;
	}
	
	public Page<TsResource> findPage(Page<TsResource> page, TsResource tsResource) {
		return super.findPage(page, tsResource);
	}
	
	@Transactional(readOnly = false)
	public void save(TsResource tsResource) {
		super.save(tsResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsResource tsResource) {
		super.delete(tsResource);
	}
	
}