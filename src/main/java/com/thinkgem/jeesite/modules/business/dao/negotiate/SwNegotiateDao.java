/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.negotiate;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;

/**
 * 商务谈判DAO接口
 * @author 孙涛
 * @version 2020-03-19
 */
@MyBatisDao
public interface SwNegotiateDao extends CrudDao<SwNegotiate> {
	
}