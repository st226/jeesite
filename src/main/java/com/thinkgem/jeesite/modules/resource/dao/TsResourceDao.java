/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;

/**
 * 图书馆资源管理DAO接口
 * @author suntao
 * @version 2018-12-26
 */
@MyBatisDao
public interface TsResourceDao extends CrudDao<TsResource> {
	
	public List<TsResource> findAllListNoParam();
	
}