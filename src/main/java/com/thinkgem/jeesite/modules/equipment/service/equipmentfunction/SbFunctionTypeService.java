/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.equipmentfunction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentfunction.SbFunctionType;
import com.thinkgem.jeesite.modules.equipment.dao.equipmentfunction.SbFunctionTypeDao;

/**
 * 功能类别Service
 * @author suntao
 * @version 2019-12-12
 */
@Service
@Transactional(readOnly = true)
public class SbFunctionTypeService extends TreeService<SbFunctionTypeDao, SbFunctionType> {

	public SbFunctionType get(String id) {
		return super.get(id);
	}
	
	public List<SbFunctionType> findList(SbFunctionType sbFunctionType) {
		if (StringUtils.isNotBlank(sbFunctionType.getParentIds())){
			sbFunctionType.setParentIds(","+sbFunctionType.getParentIds()+",");
		}
		return super.findList(sbFunctionType);
	}
	
	@Transactional(readOnly = false)
	public void save(SbFunctionType sbFunctionType) {
		super.save(sbFunctionType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbFunctionType sbFunctionType) {
		super.delete(sbFunctionType);
	}
	
}