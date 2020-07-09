/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.reimbursement;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.reimbursement.SwReimbursement;
import com.thinkgem.jeesite.modules.business.dao.reimbursement.SwReimbursementDao;

/**
 * 报销单Service
 * @author suntao
 * @version 2020-04-24
 */
@Service
@Transactional(readOnly = true)
public class SwReimbursementService extends CrudService<SwReimbursementDao, SwReimbursement> {

	public SwReimbursement get(String id) {
		return super.get(id);
	}
	
	public List<SwReimbursement> findList(SwReimbursement swReimbursement) {
		return super.findList(swReimbursement);
	}
	
	public Page<SwReimbursement> findPage(Page<SwReimbursement> page, SwReimbursement swReimbursement) {
		return super.findPage(page, swReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void save(SwReimbursement swReimbursement) {
		super.save(swReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwReimbursement swReimbursement) {
		super.delete(swReimbursement);
	}
	
}