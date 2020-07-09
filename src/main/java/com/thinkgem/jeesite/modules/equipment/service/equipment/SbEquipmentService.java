/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.dao.equipment.SbEquipmentDao;
import com.thinkgem.jeesite.modules.equipment.dao.equipmentbus.SbEquipmentBusDao;

/**
 * 仪器设备Service
 * @author suntao
 * @version 2019-12-07
 */
@Service
@Transactional(readOnly = true)
public class SbEquipmentService extends CrudService<SbEquipmentDao, SbEquipment> {
	
	@Autowired
	private SbEquipmentDao sbEquipmentDao;

	public SbEquipment get(String id) {
		return super.get(id);
	}
	
	public List<SbEquipment> findList(SbEquipment sbEquipment) {
		return super.findList(sbEquipment);
	}
	
	public Page<SbEquipment> findPage(Page<SbEquipment> page, SbEquipment sbEquipment) {
		return super.findPage(page, sbEquipment);
	}
	
	public List<SbEquipment> exportList(String userId){
		return sbEquipmentDao.exportList(userId);
	}
	
	@Transactional(readOnly = false)
	public void save(SbEquipment sbEquipment) {
		super.save(sbEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEquipment sbEquipment) {
		super.delete(sbEquipment);
	}

	
	
}