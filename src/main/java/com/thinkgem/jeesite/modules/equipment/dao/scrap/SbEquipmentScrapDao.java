/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.scrap;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.scrap.SbEquipmentScrap;

/**
 * 设备仪器报废（闲置）申请DAO接口
 * @author suntao
 * @version 2020-03-23
 */
@MyBatisDao
public interface SbEquipmentScrapDao extends CrudDao<SbEquipmentScrap> {
	
}