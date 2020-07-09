/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.dao.infomation;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.infomation.entity.infomation.SbInformation;

/**
 * 信息化设备DAO接口
 * @author suntao
 * @version 2020-01-16
 */
@MyBatisDao
public interface SbInformationDao extends CrudDao<SbInformation> {
	
	public List<Map> findListMap(Map map);
	
	public List<Map> queryInformationInfo(Map map);
	public List<Map> querymjInfo(Map map);
	public List<Map> queryTeamnameInfo(Map map);
	public List<Map> queryUseInfo(Map map);
	public List<Map> queryTypeInfo(Map map);
	public void deleteType(SbInformation sbInformation);
	
}