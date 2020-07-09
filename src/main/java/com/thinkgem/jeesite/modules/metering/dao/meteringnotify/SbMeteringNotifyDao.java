/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.dao.meteringnotify;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.metering.entity.meteringnotify.SbMeteringNotify;

/**
 * 测量数据追踪DAO接口
 * @author suntao
 * @version 2020-05-20
 */
@MyBatisDao
public interface SbMeteringNotifyDao extends CrudDao<SbMeteringNotify> {
	
	public SbMeteringNotify getByProcessInstID(long processInstID);
	
}