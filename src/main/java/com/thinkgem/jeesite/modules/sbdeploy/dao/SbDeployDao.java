/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sbdeploy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.sbdeploy.entity.SbDeploy;

/**
 * 标签配置DAO接口
 * @author suntao
 * @version 2020-03-17
 */
@MyBatisDao
public interface SbDeployDao extends CrudDao<SbDeploy> {
	public SbDeploy getByUserId(SbDeploy sbDeploy);
}