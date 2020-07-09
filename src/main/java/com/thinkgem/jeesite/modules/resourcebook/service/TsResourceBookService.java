/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resourcebook.entity.TsResourceBook;
import com.thinkgem.jeesite.modules.resourcebook.dao.TsResourceBookDao;

/**
 * 图册资源维护Service
 * @author suntao
 * @version 2018-01-31
 */
@Service
@Transactional(readOnly = true)
public class TsResourceBookService extends CrudService<TsResourceBookDao, TsResourceBook> {

	public TsResourceBook get(String id) {
		return super.get(id);
	}
	
	public List<TsResourceBook> findList(TsResourceBook tsResourceBook) {
		return super.findList(tsResourceBook);
	}
	
	public Page<TsResourceBook> findPage(Page<TsResourceBook> page, TsResourceBook tsResourceBook) {
		return super.findPage(page, tsResourceBook);
	}
	
	@Transactional(readOnly = false)
	public void save(TsResourceBook tsResourceBook) {
		super.save(tsResourceBook);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsResourceBook tsResourceBook) {
		super.delete(tsResourceBook);
	}
	
}