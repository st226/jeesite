/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.dao.resource;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.quality.entity.resource.QmResourceType;

/**
 * 技术文件模板DAO接口
 * @author suntao
 * @version 2020-05-08
 */
@MyBatisDao
public interface QmResourceTypeDao extends TreeDao<QmResourceType> {
	
}