/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdApplication;

/**
 * 三单维护DAO接口
 * @author suntao
 * @version 2019-11-14
 */
@MyBatisDao
public interface SdApplicationDao extends CrudDao<SdApplication> {
	
	
	public SdApplication getByProcessInstID(long processInstID);
	
}