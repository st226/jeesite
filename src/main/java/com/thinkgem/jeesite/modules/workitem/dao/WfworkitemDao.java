/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.workitem.dao;

import java.util.Map;

import com.eos.workflow.data.WFWorkItem;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.workitem.entity.Wfworkitem;

/**
 * 工作项维护DAO接口
 * @author suntao
 * @version 2019-11-12
 */
@MyBatisDao
public interface WfworkitemDao extends CrudDao<Wfworkitem> {
	
	public WFWorkItem getByProcessInstID(long processInstID);
	
}