/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.repair;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.repair.SbEquipmentRepair;

/**
 * 设备维修申请DAO接口
 * @author suntao
 * @version 2020-03-23
 */
@MyBatisDao
public interface SbEquipmentRepairDao extends CrudDao<SbEquipmentRepair> {
	
}