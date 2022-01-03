/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmWvuwprtegk;
import com.thinkgem.jeesite.modules.efm.dao.EfmWvuwprtegkDao;

/**
 * 涉密文件扫描审批备案表Service
 * @author l
 * @version 2021-11-19
 */
@Service
@Transactional(readOnly = true)
public class EfmWvuwprtegkService extends CrudService<EfmWvuwprtegkDao, EfmWvuwprtegk> {

	public EfmWvuwprtegk get(String id) {
		return super.get(id);
	}
	
	public List<EfmWvuwprtegk> findList(EfmWvuwprtegk efmWvuwprtegk) {
		return super.findList(efmWvuwprtegk);
	}
	
	public Page<EfmWvuwprtegk> findPage(Page<EfmWvuwprtegk> page, EfmWvuwprtegk efmWvuwprtegk) {
		return super.findPage(page, efmWvuwprtegk);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmWvuwprtegk efmWvuwprtegk) {
		super.save(efmWvuwprtegk);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmWvuwprtegk efmWvuwprtegk) {
		super.delete(efmWvuwprtegk);
	}
	
}