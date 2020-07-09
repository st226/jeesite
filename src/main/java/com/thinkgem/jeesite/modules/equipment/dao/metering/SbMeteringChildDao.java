/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.metering;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMeteringChild;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdModel;

/**
 * 计量管理DAO接口
 * @author suntao
 * @version 2020-01-01
 */
@MyBatisDao
public interface SbMeteringChildDao extends CrudDao<SbMeteringChild> {
	
	public List<SbMeteringChild> getMeteringChildList(SbMeteringChild sbMeteringChild);
	
}