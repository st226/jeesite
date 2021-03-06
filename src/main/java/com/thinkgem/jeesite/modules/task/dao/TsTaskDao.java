/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.task.entity.TsTask;

/**
 * 采集任务管理DAO接口
 * @author suntao
 * @version 2018-05-21
 */
@MyBatisDao
public interface TsTaskDao extends CrudDao<TsTask> {
	
}