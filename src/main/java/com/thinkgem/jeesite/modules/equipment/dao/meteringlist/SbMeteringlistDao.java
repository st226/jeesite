/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.meteringlist;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.meteringlist.SbMeteringlist;

/**
 * 计量查询管理DAO接口
 * @author suntao
 * @version 2020-03-26
 */
@MyBatisDao
public interface SbMeteringlistDao extends CrudDao<SbMeteringlist> {
	
}