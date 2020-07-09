/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.calendar.entity.SbCalendar;

/**
 * 日程信息DAO接口
 * @author suntao
 * @version 2020-03-06
 */
@MyBatisDao
public interface SbCalendarDao extends CrudDao<SbCalendar> {
	
}