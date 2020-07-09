/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.examine.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.examine.entity.Examine;

/**
 * 审批意见DAO接口
 * @author suntao
 * @version 2019-11-13
 */
@MyBatisDao
public interface ExamineDao extends CrudDao<Examine> {
	
	public List<Examine> getByProcessInstID(long processInstID);
	
}