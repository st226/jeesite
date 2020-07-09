/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maxtable.dao;


import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.maxtable.entity.TsMaxTable;

/**
 * 流水号DAO接口
 * @author suntao
 * @version 2018-01-26
 */
@MyBatisDao
public interface TsMaxTableDao extends CrudDao<TsMaxTable> {
	
	public String findMaxCode (Map<String, String> map);
	
	
}