/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.externalunit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.externalunit.entity.SysExternalUnit;
import com.thinkgem.jeesite.modules.externalunit.dao.SysExternalUnitDao;

/**
 * 外单位信息维护Service
 * @author 孙涛
 * @version 2020-04-26
 */
@Service
@Transactional(readOnly = true)
public class SysExternalUnitService extends CrudService<SysExternalUnitDao, SysExternalUnit> {

	public SysExternalUnit get(String id) {
		return super.get(id);
	}
	
	public List<SysExternalUnit> findList(SysExternalUnit sysExternalUnit) {
		return super.findList(sysExternalUnit);
	}
	
	public Page<SysExternalUnit> findPage(Page<SysExternalUnit> page, SysExternalUnit sysExternalUnit) {
		return super.findPage(page, sysExternalUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(SysExternalUnit sysExternalUnit) {
		super.save(sysExternalUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysExternalUnit sysExternalUnit) {
		super.delete(sysExternalUnit);
	}
	
}