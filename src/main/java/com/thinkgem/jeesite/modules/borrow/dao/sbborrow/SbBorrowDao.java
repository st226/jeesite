/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.dao.sbborrow;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;

/**
 * 设备借用DAO接口
 * @author suntao
 * @version 2019-12-26
 */
@MyBatisDao
public interface SbBorrowDao extends CrudDao<SbBorrow> {
	
	public SbBorrow getByProcessInstID(long processInstID);
	
}