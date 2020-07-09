/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.equipment;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;

/**
 * 仪器设备DAO接口
 * @author suntao
 * @version 2019-12-07
 */
@MyBatisDao
public interface SbEquipmentDao extends CrudDao<SbEquipment> {
	
	public List<SbEquipment> exportList(String userId);
	
}