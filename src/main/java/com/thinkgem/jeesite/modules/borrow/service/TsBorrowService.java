/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.borrow.entity.TsBorrow;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.borrow.dao.TsBorrowDao;

/**
 * 资源流通Service
 * @author suntao
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class TsBorrowService extends CrudService<TsBorrowDao, TsBorrow> {
	@Autowired
	private TsBorrowDao tsBorrowDao;

	public TsBorrow get(String id) {
		return super.get(id);
	}
	
	public List<TsBorrow> findList(TsBorrow tsBorrow) {
		return super.findList(tsBorrow);
	}
	
	public Map findListMap(Map map){
		return tsBorrowDao.findListMap(map) ;
	}
	
	
	
	public Page<TsBorrow> findPage(Page<TsBorrow> page, TsBorrow tsBorrow) {
		return super.findPage(page, tsBorrow);
	}
	
	@Transactional(readOnly = false)
	public void save(TsBorrow tsBorrow) {
		super.save(tsBorrow);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsBorrow tsBorrow) {
		super.delete(tsBorrow);
	}
	
}