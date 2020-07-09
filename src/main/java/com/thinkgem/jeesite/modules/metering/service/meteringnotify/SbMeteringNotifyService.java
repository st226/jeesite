/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.service.meteringnotify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.metering.entity.meteringnotify.SbMeteringNotify;
import com.thinkgem.jeesite.modules.metering.dao.meteringnotify.SbMeteringNotifyDao;

/**
 * 测量数据追踪Service
 * @author suntao
 * @version 2020-05-20
 */
@Service
@Transactional(readOnly = true)
public class SbMeteringNotifyService extends CrudService<SbMeteringNotifyDao, SbMeteringNotify> {
	
	@Autowired
	private SbMeteringNotifyDao sbMeteringNotifyDao ;

	public SbMeteringNotify get(String id) {
		return super.get(id);
	}
	
	public List<SbMeteringNotify> findList(SbMeteringNotify sbMeteringNotify) {
		return super.findList(sbMeteringNotify);
	}
	
	public Page<SbMeteringNotify> findPage(Page<SbMeteringNotify> page, SbMeteringNotify sbMeteringNotify) {
		return super.findPage(page, sbMeteringNotify);
	}
	
	@Transactional(readOnly = false)
	public void save(SbMeteringNotify sbMeteringNotify) {
		super.save(sbMeteringNotify);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbMeteringNotify sbMeteringNotify) {
		super.delete(sbMeteringNotify);
	}
	
	public SbMeteringNotify getByProcessInstID(long processInstID) {
		SbMeteringNotify sbMeteringNotify = sbMeteringNotifyDao.getByProcessInstID(processInstID);

		return sbMeteringNotify;
	}
	
}