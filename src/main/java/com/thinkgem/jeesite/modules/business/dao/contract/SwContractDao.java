/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.contract;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.contract.SwContract;

/**
 * 合同审批DAO接口
 * @author suntao
 * @version 2020-03-20
 */
@MyBatisDao
public interface SwContractDao extends CrudDao<SwContract> {
	
}