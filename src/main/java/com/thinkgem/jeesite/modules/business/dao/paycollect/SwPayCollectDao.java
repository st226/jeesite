/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.paycollect;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.paycollect.SwPayCollect;

/**
 * 付款信息汇总DAO接口
 * @author suntao
 * @version 2020-05-04
 */
@MyBatisDao
public interface SwPayCollectDao extends CrudDao<SwPayCollect> {
	
}