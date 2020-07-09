/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.service.infomation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.infomation.entity.infomation.SbInformation;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.equipment.dao.equipmentbus.SbEquipmentBusDao;
import com.thinkgem.jeesite.modules.infomation.dao.infomation.SbInformationDao;

/**
 * 信息化设备Service
 * @author suntao
 * @version 2020-01-16
 */
@Service
@Transactional(readOnly = true)
public class SbInformationService extends CrudService<SbInformationDao, SbInformation> {
	
	@Autowired
	private SbInformationDao sbInformationDao;

	public SbInformation get(String id) {
		return super.get(id);
	}
	
	public List<SbInformation> findList(SbInformation sbInformation) {
		return super.findList(sbInformation);
	}
	
	public Page<SbInformation> findPage(Page<SbInformation> page, SbInformation sbInformation) {
		return super.findPage(page, sbInformation);
	}
	
	public Page<Map> findMapPage(Page<Map> page, DataMap dataMap) {

		dataMap.setPage(page);
		dataMap.put("page", page);
		page.setList(sbInformationDao.findListMap(dataMap));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SbInformation sbInformation) {
		super.save(sbInformation);
	}
	
	
	
	@Transactional(readOnly = false)
	public void delete(SbInformation sbInformation) {
		super.delete(sbInformation);
	}
	@Transactional(readOnly = false)
	public void deleteType(SbInformation sbInformation) {
		sbInformationDao.deleteType(sbInformation);
	}
	
	public List<Map> queryInformationInfo(Map map){
		return sbInformationDao.queryInformationInfo(map);
	}
	
	public List<Map> querymjInfo(Map map){
		return sbInformationDao.querymjInfo(map);
	}
	
	public List<Map> queryTeamnameInfo(Map map){
		return sbInformationDao.queryTeamnameInfo(map);
	}
	
	public List<Map> queryUseInfo(Map map){
		return sbInformationDao.queryUseInfo(map);
	}
	
	public List<Map> queryTypeInfo(Map map){
		return sbInformationDao.queryTypeInfo(map);
	}
	
}