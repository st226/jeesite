/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maxtable.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.maxtable.entity.TsMaxTable;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.maxtable.dao.TsMaxTableDao;

/**
 * 流水号Service
 * @author suntao
 * @version 2018-01-26
 */
@Service
@Transactional(readOnly = true)
public class TsMaxTableService extends CrudService<TsMaxTableDao, TsMaxTable> {
	
	@Autowired
	private TsMaxTableDao tsMaxTableDao;
	
	public String findMaxCode (String tableName ,String formsn , String code){
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("formsn", formsn);
		map.put("code", code);
		return tsMaxTableDao.findMaxCode(map);
	}

	public TsMaxTable get(String id) {
		return super.get(id);
	}
	
	public List<TsMaxTable> findList(TsMaxTable tsMaxTable) {
		return super.findList(tsMaxTable);
	}
	
	public Page<TsMaxTable> findPage(Page<TsMaxTable> page, TsMaxTable tsMaxTable) {
		return super.findPage(page, tsMaxTable);
	}
	
	@Transactional(readOnly = false)
	public void save(TsMaxTable tsMaxTable) {
		super.save(tsMaxTable);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsMaxTable tsMaxTable) {
		super.delete(tsMaxTable);
	}
	
}