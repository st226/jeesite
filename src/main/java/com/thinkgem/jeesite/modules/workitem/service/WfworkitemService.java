/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.workitem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.workitem.entity.Wfworkitem;
import com.thinkgem.jeesite.modules.workitem.dao.WfworkitemDao;

/**
 * 工作项维护Service
 * @author suntao
 * @version 2019-11-12
 */
@Service
@Transactional(readOnly = true)
public class WfworkitemService extends CrudService<WfworkitemDao, Wfworkitem> {
	
	@Autowired
	private WfworkitemDao wfworkitemDao;

	public Wfworkitem get(String id) {
		return super.get(id);
	}
	
	public com.eos.workflow.data.WFWorkItem getByProcessInstID(long processInstID) {
		return wfworkitemDao.getByProcessInstID(processInstID);
	}
	
	public List<Wfworkitem> findList(Wfworkitem wfworkitem) {
		return super.findList(wfworkitem);
	}
	
	public Page<Wfworkitem> findPage(Page<Wfworkitem> page, Wfworkitem wfworkitem) {
		System.out.println(wfworkitem.getParticipant()+"++++++");
		return super.findPage(page, wfworkitem);
	}
	
	@Transactional(readOnly = false)
	public void save(Wfworkitem wfworkitem) {
		super.save(wfworkitem);
	}
	
	@Transactional(readOnly = false)
	public void delete(Wfworkitem wfworkitem) {
		super.delete(wfworkitem);
	}
	
}