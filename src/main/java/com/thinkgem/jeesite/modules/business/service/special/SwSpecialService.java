/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.special;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;
import com.thinkgem.jeesite.modules.business.dao.special.SwSpecialDao;

/**
 * 特殊项目申请表Service
 * @author suntao
 * @version 2020-03-21
 */
@Service
@Transactional(readOnly = true)
public class SwSpecialService extends CrudService<SwSpecialDao, SwSpecial> {

	public SwSpecial get(String id) {
		return super.get(id);
	}
	
	public List<SwSpecial> findList(SwSpecial swSpecial) {
		return super.findList(swSpecial);
	}
	
	public Page<SwSpecial> findPage(Page<SwSpecial> page, SwSpecial swSpecial) {
		return super.findPage(page, swSpecial);
	}
	
	@Transactional(readOnly = false)
	public void save(SwSpecial swSpecial) {
		super.save(swSpecial);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwSpecial swSpecial) {
		super.delete(swSpecial);
	}
	
}