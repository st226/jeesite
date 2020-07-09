/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.dao.meteringtype;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.metering.entity.meteringtype.SbMeteringType;

/**
 * 计量设备类型DAO接口
 * @author suntao
 * @version 2020-04-16
 */
@MyBatisDao
public interface SbMeteringTypeDao extends TreeDao<SbMeteringType> {
	
}