/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebook.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.resourcebook.entity.TsResourceBook;

/**
 * 图册资源维护DAO接口
 * @author suntao
 * @version 2018-01-31
 */
@MyBatisDao
public interface TsResourceBookDao extends CrudDao<TsResourceBook> {
	
}