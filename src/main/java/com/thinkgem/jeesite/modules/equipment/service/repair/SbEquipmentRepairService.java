/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.repair;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.repair.SbEquipmentRepair;
import com.thinkgem.jeesite.modules.equipment.dao.repair.SbEquipmentRepairDao;

/**
 * 设备维修申请Service
 * @author suntao
 * @version 2020-03-23
 */
@Service
@Transactional(readOnly = true)
public class SbEquipmentRepairService extends CrudService<SbEquipmentRepairDao, SbEquipmentRepair> {

	public SbEquipmentRepair get(String id) {
		return super.get(id);
	}
	
	public List<SbEquipmentRepair> findList(SbEquipmentRepair sbEquipmentRepair) {
		return super.findList(sbEquipmentRepair);
	}
	
	public Page<SbEquipmentRepair> findPage(Page<SbEquipmentRepair> page, SbEquipmentRepair sbEquipmentRepair) {
		return super.findPage(page, sbEquipmentRepair);
	}
	
	@Transactional(readOnly = false)
	public void save(SbEquipmentRepair sbEquipmentRepair) {
		super.save(sbEquipmentRepair);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEquipmentRepair sbEquipmentRepair) {
		super.delete(sbEquipmentRepair);
	}
	
}