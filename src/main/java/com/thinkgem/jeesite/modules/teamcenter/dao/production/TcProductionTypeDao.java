/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.dao.production;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.teamcenter.entity.production.TcProductionType;

/**
 * 生产bom树DAO接口
 * @author suntao
 * @version 2020-05-24
 */
@MyBatisDao
public interface TcProductionTypeDao extends TreeDao<TcProductionType> {
	
}