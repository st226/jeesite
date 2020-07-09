/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;

/**
 * 著录项管理Service
 * @author suntao
 * @version 2018-01-15
 */
@Service
@Transactional(readOnly = true)
public class TsResourceBusService extends CrudService<TsResourceBusDao, TsResourceBus> {
	
	@Autowired
	private TsResourceBusDao tsResourceBusDao;

	public TsResourceBus get(String id) {
		return super.get(id);
	}
	
	public List<TsResource> getTsResourceList(TsResource tsResource){
		return tsResourceBusDao.getTsResourceList(tsResource);
	}
	
	public List<TsResourceBus> findList(TsResourceBus tsResourceBus) {
		return super.findList(tsResourceBus);
	}
	
	public List<TsResourceBus> getBus(TsResourceBus tsResourceBus){
		return tsResourceBusDao.getBus(tsResourceBus) ;
	}
	
	public List<TsResourceBus> getBusst(TsResourceBus tsResourceBus){
		return tsResourceBusDao.getBusst(tsResourceBus) ;
	}
	
	public List<TsResourceBus> getBuszd(TsResourceBus tsResourceBus){
		return tsResourceBusDao.getBuszd(tsResourceBus) ;
	}
	
	public List<TsResourceBus> getBusById(String[] ids){
		return tsResourceBusDao.getBusById(ids) ;
	}
	
	public Page<Map> findMapPage(Page<Map> page, DataMap dataMap) {
		dataMap.setPage(page);
		dataMap.put("page", page);
		page.setList(tsResourceBusDao.findListMap(dataMap));
		return page;
	}
	
	public List<Map> findMaps(DataMap dataMap) {
		return tsResourceBusDao.findListMap(dataMap);
	}
	
	public List<Map> findMapszd(DataMap dataMap) {
		return tsResourceBusDao.findListMapzd(dataMap);
	}
	
	public List<Map> queryCensus(Map map) {
		return tsResourceBusDao.queryCensus(map);
	}
	public List<Map> gesbData(Map map) {
		return tsResourceBusDao.gesbData(map);
	}
	
	
	public List<Map> queryCensus2(Map map) {
		return tsResourceBusDao.queryCensus2(map);
	}
	public List<Map> queryCensusa(Map map) {
		return tsResourceBusDao.queryCensusa(map);
	}
	public List<Map> queryCensusb(Map map) {
		return tsResourceBusDao.queryCensusb(map);
	}
	public List<Map> queryCensusc(Map map) {
		return tsResourceBusDao.queryCensusc(map);
	}
	
	public Page<Map> findLocalMapPage(Page<Map> page, DataMap dataMap) {
		dataMap.setPage(page);
		page.setList(tsResourceBusDao.findLocalListMap(dataMap));
		return page;
	}
	
	public Page<TsResourceBus> findPage(Page<TsResourceBus> page, TsResourceBus tsResourceBus) {
		return super.findPage(page, tsResourceBus);
	}
	
	@Transactional(readOnly = false)
	public void save(TsResourceBus tsResourceBus) {
		super.save(tsResourceBus);
	}
	@Transactional(readOnly = false)
	public int updateState(TsResource tsResource) {
		return tsResourceBusDao.updateState(tsResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsResourceBus tsResourceBus) {
		super.delete(tsResourceBus);
	}
	
}