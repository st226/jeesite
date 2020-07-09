/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collection.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.collection.entity.TsCollection;

/**
 * 馆藏信息DAO接口
 * @author suntao
 * @version 2018-01-28
 */
@MyBatisDao
public interface TsCollectionDao extends TreeDao<TsCollection> {
	
}