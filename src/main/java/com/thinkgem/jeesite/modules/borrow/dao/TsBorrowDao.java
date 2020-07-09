/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.borrow.entity.TsBorrow;

/**
 * 资源流通DAO接口
 * @author suntao
 * @version 2018-01-29
 */
@MyBatisDao
public interface TsBorrowDao extends CrudDao<TsBorrow> {
	
	public Map findListMap(Map map);
	
}