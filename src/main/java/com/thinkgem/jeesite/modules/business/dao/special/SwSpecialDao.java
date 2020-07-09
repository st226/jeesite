/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.special;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;

/**
 * 特殊项目申请表DAO接口
 * @author suntao
 * @version 2020-03-21
 */
@MyBatisDao
public interface SwSpecialDao extends CrudDao<SwSpecial> {
	
}