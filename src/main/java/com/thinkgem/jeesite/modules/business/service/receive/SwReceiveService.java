/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.receive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceive;
import com.thinkgem.jeesite.modules.business.dao.receive.SwReceiveDao;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveData;
import com.thinkgem.jeesite.modules.business.dao.receive.SwReceiveDataDao;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;
import com.thinkgem.jeesite.modules.business.dao.receive.SwReceiveEquipmentDao;

/**
 * 仪器设备开箱验收Service
 * @author suntao
 * @version 2020-04-11
 */
@Service
@Transactional(readOnly = true)
public class SwReceiveService extends CrudService<SwReceiveDao, SwReceive> {

	@Autowired
	private SwReceiveDataDao swReceiveDataDao;
	@Autowired
	private SwReceiveEquipmentDao swReceiveEquipmentDao;
	
	public SwReceive get(String id) {
		SwReceive swReceive = super.get(id);
		swReceive.setSwReceiveDataList(swReceiveDataDao.findList(new SwReceiveData(swReceive)));
		swReceive.setSwReceiveEquipmentList(swReceiveEquipmentDao.findList(new SwReceiveEquipment(swReceive)));
		return swReceive;
	}
	
	public SwReceiveEquipment getEquipment(String id) {
		SwReceiveEquipment swReceiveEquipment = swReceiveEquipmentDao.get(id);
		
		return swReceiveEquipment;
	}
	
	
	public List<SwReceiveEquipment> findSwReceiveEquipmentList(SwReceiveEquipment swReceiveEquipment) {
		return swReceiveEquipmentDao.findSwReceiveEquipmentList(swReceiveEquipment);
	}
	
	
	public List<SwReceive> findList(SwReceive swReceive) {
		return super.findList(swReceive);
	}
	
	public Page<SwReceive> findPage(Page<SwReceive> page, SwReceive swReceive) {
		return super.findPage(page, swReceive);
	}
	
	public Page<SwReceiveEquipment> findEquipmentPage(Page<SwReceiveEquipment> page, SwReceiveEquipment swReceiveEquipment) {
		swReceiveEquipment.setPage(page);
		page.setList(swReceiveEquipmentDao.findList(swReceiveEquipment));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SwReceive swReceive) {
		super.save(swReceive);
		for (SwReceiveData swReceiveData : swReceive.getSwReceiveDataList()){
			if (swReceiveData.getId() == null){
				continue;
			}
			if (SwReceiveData.DEL_FLAG_NORMAL.equals(swReceiveData.getDelFlag())){
				if (StringUtils.isBlank(swReceiveData.getId())){
					swReceiveData.setReceiveId(swReceive);
					swReceiveData.preInsert();
					swReceiveDataDao.insert(swReceiveData);
				}else{
					swReceiveData.preUpdate();
					swReceiveDataDao.update(swReceiveData);
				}
			}else{
				swReceiveDataDao.delete(swReceiveData);
			}
		}
		for (SwReceiveEquipment swReceiveEquipment : swReceive.getSwReceiveEquipmentList()){
			if (swReceiveEquipment.getId() == null){
				continue;
			}
			if (SwReceiveEquipment.DEL_FLAG_NORMAL.equals(swReceiveEquipment.getDelFlag())){
				if (StringUtils.isBlank(swReceiveEquipment.getId())){
					swReceiveEquipment.setReceiveId(swReceive);
					swReceiveEquipment.setState("0");
					swReceiveEquipment.preInsert();
					swReceiveEquipmentDao.insert(swReceiveEquipment);
				}else{
					swReceiveEquipment.preUpdate();
					swReceiveEquipmentDao.update(swReceiveEquipment);
				}
			}else{
				swReceiveEquipmentDao.delete(swReceiveEquipment);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void saveEquipment(SwReceiveEquipment swReceiveEquipment) {
		
		swReceiveEquipmentDao.update(swReceiveEquipment);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(SwReceive swReceive) {
		super.delete(swReceive);
		swReceiveDataDao.delete(new SwReceiveData(swReceive));
		swReceiveEquipmentDao.delete(new SwReceiveEquipment(swReceive));
	}
	
}