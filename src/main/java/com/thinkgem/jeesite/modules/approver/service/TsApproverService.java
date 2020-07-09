/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.approver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.approver.entity.TsApprover;
import com.thinkgem.jeesite.modules.archives.dao.ApplicationDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.approver.dao.TsApproverDao;

/**
 * 现行文件申请Service
 * @author suntao
 * @version 2019-01-27
 */
@Service
@Transactional(readOnly = true)
public class TsApproverService extends CrudService<TsApproverDao, TsApprover> {
	
	@Autowired
	private TsApproverDao tsApproverDao ;

	public TsApprover get(String id) {
		return super.get(id);
	}
	
	public List<TsApprover> findList(TsApprover tsApprover) {
		return super.findList(tsApprover);
	}
	public TsApprover getByProcessInstID(long processInstID) {
		
		return tsApproverDao.getByProcessInstID(processInstID);
	}
	
	public Page<TsApprover> findPage(Page<TsApprover> page, TsApprover tsApprover) {
		return super.findPage(page, tsApprover);
	}
	
	@Transactional(readOnly = false)
	public void save(TsApprover tsApprover) {
		super.save(tsApprover);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsApprover tsApprover) {
		super.delete(tsApprover);
	}
	
}