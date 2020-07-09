/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.sbcache;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;

/**
 * 设备缓存DAO接口
 * @author suntao
 * @version 2019-12-27
 */
@MyBatisDao
public interface SbCacheDao extends CrudDao<SbCache> {
	
	public void deleteBysbId(String sbId);
	public void deleteByuserId(Map map);
	
}