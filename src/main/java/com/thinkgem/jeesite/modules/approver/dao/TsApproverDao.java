/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.approver.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.approver.entity.TsApprover;
import com.thinkgem.jeesite.modules.archives.entity.Application;

/**
 * 现行文件申请DAO接口
 * @author suntao
 * @version 2019-01-27
 */
@MyBatisDao
public interface TsApproverDao extends CrudDao<TsApprover> {
	
	public TsApprover getByProcessInstID(long processInstID);
	
}