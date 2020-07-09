/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.contract.SwContract;
import com.thinkgem.jeesite.modules.business.dao.contract.SwContractDao;

/**
 * 合同审批Service
 * @author suntao
 * @version 2020-03-20
 */
@Service
@Transactional(readOnly = true)
public class SwContractService extends CrudService<SwContractDao, SwContract> {

	public SwContract get(String id) {
		return super.get(id);
	}
	
	public List<SwContract> findList(SwContract swContract) {
		return super.findList(swContract);
	}
	
	public Page<SwContract> findPage(Page<SwContract> page, SwContract swContract) {
		return super.findPage(page, swContract);
	}
	
	@Transactional(readOnly = false)
	public void save(SwContract swContract) {
		super.save(swContract);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwContract swContract) {
		super.delete(swContract);
	}
	
}