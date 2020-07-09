/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.metering;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMetering;

/**
 * 计量管理DAO接口
 * @author suntao
 * @version 2020-01-01
 */
@MyBatisDao
public interface SbMeteringDao extends CrudDao<SbMetering> {
	public SbMetering getByProcessInstID(long processInstID);
}