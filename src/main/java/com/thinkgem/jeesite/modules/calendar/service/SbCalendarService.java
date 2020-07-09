/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.calendar.entity.SbCalendar;
import com.thinkgem.jeesite.modules.calendar.dao.SbCalendarDao;

/**
 * 日程信息Service
 * @author suntao
 * @version 2020-03-06
 */
@Service
@Transactional(readOnly = true)
public class SbCalendarService extends CrudService<SbCalendarDao, SbCalendar> {

	public SbCalendar get(String id) {
		return super.get(id);
	}
	
	public List<SbCalendar> findList(SbCalendar sbCalendar) {
		return super.findList(sbCalendar);
	}
	
	public Page<SbCalendar> findPage(Page<SbCalendar> page, SbCalendar sbCalendar) {
		return super.findPage(page, sbCalendar);
	}
	
	@Transactional(readOnly = false)
	public void save(SbCalendar sbCalendar) {
		super.save(sbCalendar);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbCalendar sbCalendar) {
		super.delete(sbCalendar);
	}
	
}