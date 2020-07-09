/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.suntao_test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.suntao_test.entity.SuntaoTestChild;

/**
 * 测试主子表DAO接口
 * @author suntao
 * @version 2020-05-02
 */
@MyBatisDao
public interface SuntaoTestChildDao extends CrudDao<SuntaoTestChild> {
	
}