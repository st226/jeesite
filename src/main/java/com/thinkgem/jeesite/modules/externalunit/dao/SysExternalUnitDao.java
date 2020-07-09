/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.externalunit.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.externalunit.entity.SysExternalUnit;

/**
 * 外单位信息维护DAO接口
 * @author 孙涛
 * @version 2020-04-26
 */
@MyBatisDao
public interface SysExternalUnitDao extends CrudDao<SysExternalUnit> {
	
}