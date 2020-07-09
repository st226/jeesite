/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.examine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.archives.dao.ApplicationDao;
import com.thinkgem.jeesite.modules.examine.dao.ExamineDao;

/**
 * 审批意见Service
 * @author suntao
 * @version 2019-11-13
 */
@Service
@Transactional(readOnly = true)
public class ExamineService extends CrudService<ExamineDao, Examine> {
	
	@Autowired
	private ExamineDao examineDao ;

	public Examine get(String id) {
		return super.get(id);
	}
	
	public List<Examine> findList(Examine examine) {
		return super.findList(examine);
	}
	
	public Page<Examine> findPage(Page<Examine> page, Examine examine) {
		return super.findPage(page, examine);
	}
	
	public List<Examine> getByProcessInstID(long processInstID){
		
		return examineDao.getByProcessInstID(processInstID);
	}
	
	@Transactional(readOnly = false)
	public void save(Examine examine) {
		super.save(examine);
	}
	
	@Transactional(readOnly = false)
	public void delete(Examine examine) {
		super.delete(examine);
	}
	
}