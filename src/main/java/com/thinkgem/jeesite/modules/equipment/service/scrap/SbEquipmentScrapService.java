/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.scrap;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.scrap.SbEquipmentScrap;
import com.thinkgem.jeesite.modules.equipment.dao.scrap.SbEquipmentScrapDao;

/**
 * 设备仪器报废（闲置）申请Service
 * @author suntao
 * @version 2020-03-23
 */
@Service
@Transactional(readOnly = true)
public class SbEquipmentScrapService extends CrudService<SbEquipmentScrapDao, SbEquipmentScrap> {

	public SbEquipmentScrap get(String id) {
		return super.get(id);
	}
	
	public List<SbEquipmentScrap> findList(SbEquipmentScrap sbEquipmentScrap) {
		return super.findList(sbEquipmentScrap);
	}
	
	public Page<SbEquipmentScrap> findPage(Page<SbEquipmentScrap> page, SbEquipmentScrap sbEquipmentScrap) {
		return super.findPage(page, sbEquipmentScrap);
	}
	
	@Transactional(readOnly = false)
	public void save(SbEquipmentScrap sbEquipmentScrap) {
		super.save(sbEquipmentScrap);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEquipmentScrap sbEquipmentScrap) {
		super.delete(sbEquipmentScrap);
	}
	
}