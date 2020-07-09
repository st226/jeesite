/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.event;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.equipment.entity.event.SbEvent;

/**
 * 借用事件DAO接口
 * @author suntao
 * @version 2020-01-08
 */
@MyBatisDao
public interface SbEventDao extends CrudDao<SbEvent> {
	public SbEvent getByProcessInstID(long processInstID);
}