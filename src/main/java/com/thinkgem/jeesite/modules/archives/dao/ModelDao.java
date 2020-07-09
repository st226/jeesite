/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;

/**
 * 现行文件管理DAO接口
 * @author suntao
 * @version 2019-11-10
 */
@MyBatisDao
public interface ModelDao extends CrudDao<Model> {
	
	public List<Model> getModelList(Model Model);
	
}