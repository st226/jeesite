/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.dao.sbborrow;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;

/**
 * 设备借用DAO接口
 * @author suntao
 * @version 2019-12-26
 */
@MyBatisDao
public interface SbBorrowChildDao extends CrudDao<SbBorrowChild> {
	public SbBorrowChild getSbBorrowChild(SbBorrowChild sbBorrowChild);
}