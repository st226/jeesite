/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.dao.qmresource;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.quality.entity.qmresource.QmResource;

/**
 * 技术文件管理DAO接口
 * @author suntao
 * @version 2020-05-08
 */
@MyBatisDao
public interface QmResourceDao extends CrudDao<QmResource> {
	
}