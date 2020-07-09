/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.paycollect;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.paycollect.SwPayCollect;
import com.thinkgem.jeesite.modules.business.dao.paycollect.SwPayCollectDao;

/**
 * 付款信息汇总Service
 * @author suntao
 * @version 2020-05-04
 */
@Service
@Transactional(readOnly = true)
public class SwPayCollectService extends CrudService<SwPayCollectDao, SwPayCollect> {

	public SwPayCollect get(String id) {
		return super.get(id);
	}
	
	public List<SwPayCollect> findList(SwPayCollect swPayCollect) {
		return super.findList(swPayCollect);
	}
	
	public Page<SwPayCollect> findPage(Page<SwPayCollect> page, SwPayCollect swPayCollect) {
		return super.findPage(page, swPayCollect);
	}
	
	@Transactional(readOnly = false)
	public void save(SwPayCollect swPayCollect) {
		super.save(swPayCollect);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwPayCollect swPayCollect) {
		super.delete(swPayCollect);
	}
	
}