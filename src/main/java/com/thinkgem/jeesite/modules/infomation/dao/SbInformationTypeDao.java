/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.infomation.entity.SbInformationType;

/**
 * 信息化设备DAO接口
 * @author suntao
 * @version 2020-01-16
 */
@MyBatisDao
public interface SbInformationTypeDao extends TreeDao<SbInformationType> {
	
}