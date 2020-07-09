/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.TsEquipment;

/**
 * 试验数据DAO接口
 * @author suntao
 * @version 2018-05-22
 */
@MyBatisDao
public interface TsEquipmentDao extends CrudDao<TsEquipment> {
	
}