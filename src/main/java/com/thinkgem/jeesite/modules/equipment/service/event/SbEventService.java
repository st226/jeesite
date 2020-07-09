/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.event.SbEvent;
import com.thinkgem.jeesite.modules.equipment.dao.event.SbEventDao;
import com.thinkgem.jeesite.modules.equipment.entity.event.SbEventChild;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.equipment.dao.event.SbEventChildDao;

/**
 * 借用事件Service
 * @author suntao
 * @version 2020-01-08
 */
@Service
@Transactional(readOnly = true)
public class SbEventService extends CrudService<SbEventDao, SbEvent> {

	@Autowired
	private SbEventChildDao sbEventChildDao;
	
	@Autowired
	private SbEventDao sbEventDao;
	
	public SbEvent get(String id) {
		SbEvent sbEvent = super.get(id);
		sbEvent.setSbEventChildList(sbEventChildDao.findList(new SbEventChild(sbEvent)));
		return sbEvent;
	}
	
	public List<SbEvent> findList(SbEvent sbEvent) {
		return super.findList(sbEvent);
	}
	
	public Page<SbEvent> findPage(Page<SbEvent> page, SbEvent sbEvent) {
		return super.findPage(page, sbEvent);
	}
	
	public SbEvent getByProcessInstID(long processInstID) {
		SbEvent sbEvent = sbEventDao.getByProcessInstID(processInstID);
		sbEvent.setSbEventChildList(sbEventChildDao.findList(new SbEventChild(sbEvent)));
		return sbEvent;
	}
	
	@Transactional(readOnly = false)
	public void save(SbEvent sbEvent) {
		super.save(sbEvent);
		for (SbEventChild sbEventChild : sbEvent.getSbEventChildList()){
			if (sbEventChild.getId() == null){
				continue;
			}
			if (SbEventChild.DEL_FLAG_NORMAL.equals(sbEventChild.getDelFlag())){
				if (StringUtils.isBlank(sbEventChild.getId())){
					sbEventChild.setEventId(sbEvent);
					sbEventChild.preInsert();
					sbEventChildDao.insert(sbEventChild);
				}else{
					sbEventChild.preUpdate();
					sbEventChildDao.update(sbEventChild);
				}
			}else{
				sbEventChildDao.delete(sbEventChild);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEvent sbEvent) {
		super.delete(sbEvent);
		sbEventChildDao.delete(new SbEventChild(sbEvent));
	}
	
}