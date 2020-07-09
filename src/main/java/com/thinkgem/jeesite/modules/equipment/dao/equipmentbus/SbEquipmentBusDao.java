/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.dao.equipmentbus;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentbus.SbEquipmentBus;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;

/**
 * 仪器设备配置DAO接口
 * @author suntao
 * @version 2019-12-07
 */
@MyBatisDao
public interface SbEquipmentBusDao extends CrudDao<SbEquipmentBus> {
	
	public List<Map> findListMap(Map map);
	public Map getMap(Map map);
	public int updateEquipment(SbEquipment equipment);
	public int updateBstate(SbEquipment equipment);
	public List<Map> queryEquipmentInfo(Map map);
	public List<Map> findMeteringListMap(Map map);
	public List<Map> findMeteringYMListMap(Map map);
	public List<Map> queryStateInfo(Map map);
	public List<Map> queryOfficeInfo(Map map);
	public List<Map> queryFsTypeInfo(Map map);
	public List<Map> querySbTypeInfo(Map map);
	public List<Map> queryRateInfo(Map map);
	
	
	public List<Map> queryIndexInfo(Map map);
	
	public String getCode(Map map);
	
}