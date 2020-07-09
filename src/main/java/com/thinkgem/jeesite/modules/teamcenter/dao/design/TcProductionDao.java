/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.dao.design;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.teamcenter.entity.design.TcProduction;

/**
 * 生产图纸DAO接口
 * @author suntao
 * @version 2020-05-25
 */
@MyBatisDao
public interface TcProductionDao extends CrudDao<TcProduction> {
	
}