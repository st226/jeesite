/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcetype.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;

/**
 * 著录项管理DAO接口
 * @author suntao
 * @version 2018-01-12
 */
@MyBatisDao
public interface TsResourceTypeDao extends TreeDao<TsResourceType> {
	
	public List<TsResourceType> getTsResourceTypeList(TsResourceType tsResourceType);
	
	
	public List<TsResourceType> getTsResourceTypeBy(TsResourceType tsResourceType);
	

	public List<TsResource> getTsResourceList(TsResourceType tsResourceType);
	
	public List<TsResource> getLdResourceList(TsResourceType tsResourceType);
	
	public List<TsResourceType> queryList(TsResourceType tsResourceType);
	
}