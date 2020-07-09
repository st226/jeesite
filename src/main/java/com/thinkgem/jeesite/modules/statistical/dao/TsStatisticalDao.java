/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.statistical.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.statistical.entity.TsStatistical;

/**
 * 统计分析DAO接口
 * @author suntao
 * @version 2018-02-04
 */
@MyBatisDao
public interface TsStatisticalDao extends CrudDao<TsStatistical> {
	
}