/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;

/**
 * 现行文件管理DAO接口
 * @author suntao
 * @version 2019-11-10
 */
@MyBatisDao
public interface ApplicationDao extends CrudDao<Application> {
	
	public Application getByProcessInstID(long processInstID);
	
	public String getCode(String code);
	
}