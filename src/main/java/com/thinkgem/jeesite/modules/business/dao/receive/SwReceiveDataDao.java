/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.receive;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveData;

/**
 * 仪器设备开箱验收DAO接口
 * @author suntao
 * @version 2020-04-11
 */
@MyBatisDao
public interface SwReceiveDataDao extends CrudDao<SwReceiveData> {
	
}