/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.reimbursement;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.reimbursement.SwReimbursement;

/**
 * 报销单DAO接口
 * @author suntao
 * @version 2020-04-24
 */
@MyBatisDao
public interface SwReimbursementDao extends CrudDao<SwReimbursement> {
	
}