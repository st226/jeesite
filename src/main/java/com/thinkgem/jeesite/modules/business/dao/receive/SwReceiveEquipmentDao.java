/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.receive;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;

/**
 * 仪器设备开箱验收DAO接口
 * @author suntao
 * @version 2020-04-11
 */
@MyBatisDao
public interface SwReceiveEquipmentDao extends CrudDao<SwReceiveEquipment> {

	Page<SwReceiveEquipment> findPage(Page<SwReceiveEquipment> page, SwReceiveEquipment swReceiveEquipment);
	List<SwReceiveEquipment> findSwReceiveEquipmentList(SwReceiveEquipment swReceiveEquipment);
	
}