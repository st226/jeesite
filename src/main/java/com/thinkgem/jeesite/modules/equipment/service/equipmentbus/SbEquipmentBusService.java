/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.equipmentbus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentbus.SbEquipmentBus;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.equipment.dao.equipment.SbEquipmentDao;
import com.thinkgem.jeesite.modules.equipment.dao.equipmentbus.SbEquipmentBusDao;

/**
 * 仪器设备配置Service
 * @author suntao
 * @version 2019-12-07
 */
@Service
@Transactional(readOnly = true)
public class SbEquipmentBusService extends CrudService<SbEquipmentBusDao, SbEquipmentBus> {
	
	@Autowired
	private SbEquipmentBusDao sbEquipmentBusDao;

	public SbEquipmentBus get(String id) {
		return super.get(id);
	}
	
	public List<SbEquipmentBus> findList(SbEquipmentBus sbEquipmentBus) {
		return super.findList(sbEquipmentBus);
	}
	
	public Page<SbEquipmentBus> findPage(Page<SbEquipmentBus> page, SbEquipmentBus sbEquipmentBus) {
		return super.findPage(page, sbEquipmentBus);
	}
	@Transactional(readOnly = false)
	public void updateEquipment(SbEquipment equipment){
		sbEquipmentBusDao.updateEquipment(equipment);
	}
	@Transactional(readOnly = false)
	public int updateBstate(SbEquipment equipment){
		return sbEquipmentBusDao.updateBstate(equipment);
	}
	
	@Transactional(readOnly = false)
	public void save(SbEquipmentBus sbEquipmentBus) {
		super.save(sbEquipmentBus);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbEquipmentBus sbEquipmentBus) {
		super.delete(sbEquipmentBus);
	}
	
	public Page<Map> findMapPage(Page<Map> page, DataMap dataMap,Map kmap) {

		dataMap.setPage(page);
		dataMap.put("page", page);
		Map map = sbEquipmentBusDao.getMap(kmap);
		if(map!=null && map.get("field2")!=null && map.get("price")!=null)
		page.setMessage(",设备数量 "+map.get("field2")+" 台,设备总价值 "+map.get("price").toString()+"万元");
		page.setList(sbEquipmentBusDao.findListMap(dataMap));
		return page;
	}
	
	public Page<Map> findMeteringPage(Page<Map> page, DataMap dataMap) {
		dataMap.setPage(page);
		dataMap.put("page", page);
		page.setOrderBy("field6");
		page.setList(sbEquipmentBusDao.findMeteringListMap(dataMap));
		return page;
	}
	
	public Page<Map> findMeteringYMPage(Page<Map> page, DataMap dataMap) {
		dataMap.setPage(page);
		dataMap.put("page", page);
		page.setOrderBy("field6");
		page.setList(sbEquipmentBusDao.findMeteringYMListMap(dataMap));
		return page;
	}
	
	public List<Map> queryEquipmentInfo(Map map){
		return sbEquipmentBusDao.queryEquipmentInfo(map);
	}
	
	public List<Map> queryIndexInfo(Map map){
		return sbEquipmentBusDao.queryIndexInfo(map);
	}
	
	public List<Map> queryStateInfo(Map map){
		return sbEquipmentBusDao.queryStateInfo(map);
	}
	
	public List<Map> queryOfficeInfo(Map map){
		return sbEquipmentBusDao.queryOfficeInfo(map);
	}
	
	public List<Map> queryFsTypeInfo(Map map){
		return sbEquipmentBusDao.queryFsTypeInfo(map);
	}
	
	public List<Map> querySbTypeInfo(Map map){
		return sbEquipmentBusDao.querySbTypeInfo(map);
	}
	
	public List<Map> queryRateInfo(Map map){
		return sbEquipmentBusDao.queryRateInfo(map);
	}

	public String getCode(String tableName,String formsn,String name) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("tableName", tableName);
		map.put("formsn", formsn);
		map.put("name", name);
		return sbEquipmentBusDao.getCode(map);
	}
	
}