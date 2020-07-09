/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.meteringlist;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.meteringlist.SbMeteringlist;
import com.thinkgem.jeesite.modules.equipment.dao.meteringlist.SbMeteringlistDao;

/**
 * 计量查询管理Service
 * @author suntao
 * @version 2020-03-26
 */
@Service
@Transactional(readOnly = true)
public class SbMeteringlistService extends CrudService<SbMeteringlistDao, SbMeteringlist> {

	public SbMeteringlist get(String id) {
		return super.get(id);
	}
	
	public List<SbMeteringlist> findList(SbMeteringlist sbMeteringlist) {
		return super.findList(sbMeteringlist);
	}
	
	public Page<SbMeteringlist> findPage(Page<SbMeteringlist> page, SbMeteringlist sbMeteringlist) {
		return super.findPage(page, sbMeteringlist);
	}
	
	@Transactional(readOnly = false)
	public void save(SbMeteringlist sbMeteringlist) {
		super.save(sbMeteringlist);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbMeteringlist sbMeteringlist) {
		super.delete(sbMeteringlist);
	}
	
}