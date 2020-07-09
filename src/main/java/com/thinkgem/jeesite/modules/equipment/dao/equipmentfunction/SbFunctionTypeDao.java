/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.equipmentfunction;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentfunction.SbFunctionType;

/**
 * 功能类别DAO接口
 * @author suntao
 * @version 2019-12-12
 */
@MyBatisDao
public interface SbFunctionTypeDao extends TreeDao<SbFunctionType> {
	
}