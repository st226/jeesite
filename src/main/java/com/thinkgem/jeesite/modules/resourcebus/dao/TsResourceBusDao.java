/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebus.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.dozer.Mapping;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;

/**
 * 著录项管理DAO接口
 * @author suntao
 * @version 2018-01-15
 */
@MyBatisDao
public interface TsResourceBusDao extends CrudDao<TsResourceBus> {
	
	public List<TsResourceBus> getBus(TsResourceBus tsResourceBus);
	public List<TsResourceBus> getBusst(TsResourceBus tsResourceBus);
	public List<TsResourceBus> getBuszd(TsResourceBus tsResourceBus);
	public List<TsResourceBus> getBusById(@Param("ids")String[] ids);
	public List<Map> findListMap(Map map);
	public List<Map> queryCensus(Map map);
	public List<Map> gesbData(Map map);
	public List<Map> queryCensus2(Map map);
	public List<Map> queryCensusa(Map map);
	public List<Map> queryCensusb(Map map);
	public List<Map> queryCensusc(Map map);
	public List<Map> findListMapzd(Map map);
	public List<Map> findLocalListMap(Map map);
	public int updateState(TsResource tsResource);
	public List<TsResource> getTsResourceList(TsResource tsResource) ;
	public List<GenTableColumn> getGenTableColumn(GenTableColumn genTableColumn);
	
	
	
}