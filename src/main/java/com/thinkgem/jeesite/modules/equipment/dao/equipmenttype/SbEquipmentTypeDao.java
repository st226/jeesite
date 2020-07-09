/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.equipmenttype;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.equipmenttype.SbEquipmentType;

/**
 * 仪器设备分类DAO接口
 * @author suntao
 * @version 2019-12-07
 */
@MyBatisDao
public interface SbEquipmentTypeDao extends TreeDao<SbEquipmentType> {
	
}