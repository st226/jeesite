/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdModel;

/**
 * 三单维护DAO接口
 * @author suntao
 * @version 2019-11-14
 */
@MyBatisDao
public interface SdModelDao extends CrudDao<SdModel> {
	
	public List<SdModel> getModelList(SdModel sdModel);
	
}