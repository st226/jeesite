/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.TsEquipment;
import com.thinkgem.jeesite.modules.equipment.dao.TsEquipmentDao;

/**
 * 试验数据Service
 * @author suntao
 * @version 2018-05-21
 */
@Service
@Transactional(readOnly = true)
public class TsEquipmentService extends CrudService<TsEquipmentDao, TsEquipment> {

	public TsEquipment get(String id) {
		return super.get(id);
	}
	
	public List<TsEquipment> findList(TsEquipment tsEquipment) {
		return super.findList(tsEquipment);
	}
	
	public Page<TsEquipment> findPage(Page<TsEquipment> page, TsEquipment tsEquipment) {
		return super.findPage(page, tsEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(TsEquipment tsEquipment) {
		super.save(tsEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsEquipment tsEquipment) {
		super.delete(tsEquipment);
	}
	
}