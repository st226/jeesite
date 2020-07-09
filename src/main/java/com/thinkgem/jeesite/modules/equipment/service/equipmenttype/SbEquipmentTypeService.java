/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.equipmenttype;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.equipmenttype.SbEquipmentType;
import com.thinkgem.jeesite.modules.equipment.dao.equipmenttype.SbEquipmentTypeDao;

/**
 * 仪器设备分类Service
 * @author suntao
 * @version 2019-12-07
 */
@Service
@Transactional(readOnly = true)
public class SbEquipmentTypeService extends TreeService<SbEquipmentTypeDao, SbEquipmentType> {

	public SbEquipmentType get(String id) {
		return super.get(id);
	}
	
	public List<SbEquipmentType> findList(SbEquipmentType sbEquipmentType) {
		if (StringUtils.isNotBlank(sbEquipmentType.getParentIds())){
			sbEquipmentType.setParentIds(","+sbEquipmentType.getParentIds()+",");
		}
		return super.findList(sbEquipmentType);
	}
	
	@Transactional(readOnly = false)
	public void save(SbEquipmentType sbEquipmentType) {
		super.save(sbEquipmentType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEquipmentType sbEquipmentType) {
		super.delete(sbEquipmentType);
	}
	
}