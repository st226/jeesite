/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcetype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.gen.dao.GenTableDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcetype.dao.TsResourceTypeDao;

/**
 * 著录项管理Service
 * @author suntao
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class TsResourceTypeService extends TreeService<TsResourceTypeDao, TsResourceType> {
	
	
	@Autowired
	private TsResourceBusDao tsResourceBusDao ;
	
	
	@Autowired
	private TsResourceTypeDao tsResourceTypeDao ;

	public TsResourceType get(String id) {
		return super.get(id);
	}
	
	public List<TsResourceType> getTsResourceType(TsResourceType tsResourceType){
		return tsResourceTypeDao.getTsResourceTypeList(tsResourceType);
	}
	
	public List<TsResource> getTsResource(TsResourceType tsResourceType){
		return tsResourceTypeDao.getTsResourceList(tsResourceType);
	}
	
	public List<TsResource> getLdResource(TsResourceType tsResourceType){
		return tsResourceTypeDao.getLdResourceList(tsResourceType);
	}
	
	public List<TsResourceType> getTsResourceTypeBy(TsResourceType tsResourceType){
		return tsResourceTypeDao.getTsResourceTypeBy(tsResourceType);
		
	}
	
	public List<TsResourceType> queryList(TsResourceType tsResourceType){
		return tsResourceTypeDao.queryList(tsResourceType);
		
	}
	
	public List<TsResourceType> findList(TsResourceType tsResourceType) {
		if (StringUtils.isNotBlank(tsResourceType.getParentIds())){
			tsResourceType.setParentIds(","+tsResourceType.getParentIds()+",");
		}
		return super.findList(tsResourceType);
	}
	
	@Transactional(readOnly = false)
	public void save(TsResourceType tsResourceType) {
		super.save(tsResourceType);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsResourceType tsResourceType) {
		super.delete(tsResourceType);
	}
	
	@Transactional
	public void saveGemCoumn(String BusType){
		GenTableColumn genTableColumn = new GenTableColumn();
		System.out.println(BusType+"-----------------");
		List<GenTableColumn> list = tsResourceBusDao.getGenTableColumn(genTableColumn);
		System.out.println(list.size()+"-----------------");
		for (GenTableColumn temp : list) {
			TsResourceBus tsResourceBusF = new TsResourceBus();
			tsResourceBusF.setBusType(BusType);
            tsResourceBusF.setColumnId(temp.getId());
            tsResourceBusF.setColumnJdbcType(temp.getJdbcType());
            tsResourceBusF.setColumnName(temp.getName());
            tsResourceBusF.setTableId(temp.getJavaType());
            tsResourceBusDao.insert(tsResourceBusF);
		}
	}
	
	
	 @Transactional
	    public void saveBusTableData(GenTable genTable) {
	        //保存之前先删除之前的数据然后保存数据
	       
	        TsResourceBus tsResourceBus = new TsResourceBus();
	        tsResourceBus.setBusType(genTable.getParentTableFk());
	        System.out.println(genTable.getParentTableFk());
	        tsResourceBus.setTableId(genTable.getId());
	        System.out.println(genTable.getId());
	        tsResourceBusDao.delete(tsResourceBus);
	       
	        List<GenTableColumn> busTableList = new ArrayList<GenTableColumn>();
	        String tableId = genTable.getId();
	        for (GenTableColumn column : genTable.getColumnList()) {
	            if (column.getIsList().equals("1")) {
	                String columnId = column.getId();
	                TsResourceBus tsResourceBusF = new TsResourceBus();
	                tsResourceBusF.setBusType(genTable.getParentTableFk());
	                tsResourceBusF.setColumnId(column.getId());
	                tsResourceBusF.setColumnJdbcType(column.getJdbcType());
	                tsResourceBusF.setColumnName(column.getName());
	                tsResourceBusF.setTableId(column.getGenTable().getId());
	                tsResourceBusDao.insert(tsResourceBusF);
	            }

	        }
	        
	        
	        if("a6ff8bd35d8a424f9f66b3bc24334758".equals(genTable.getId())){
	        	TsResourceType tsResourceType = new TsResourceType();
	        	tsResourceType.setParentIds(genTable.getParentTableFk());		
	        	List<TsResourceType> tsResourceTypeList = tsResourceTypeDao.getTsResourceTypeBy(tsResourceType);
	        	for (TsResourceType tsResourceType2 : tsResourceTypeList) {
	        		
	        		
	        		tsResourceBus = new TsResourceBus();
	    	        tsResourceBus.setBusType(tsResourceType2.getId());
	    	    
	    	        tsResourceBus.setTableId(genTable.getId());
	    	 
	    	        tsResourceBusDao.delete(tsResourceBus);
	    	       
	    	        busTableList = new ArrayList<GenTableColumn>();
	    	        tableId = genTable.getId();
	    	        for (GenTableColumn column : genTable.getColumnList()) {
	    	            if (column.getIsList().equals("1")) {
	    
	    	                TsResourceBus tsResourceBusF = new TsResourceBus();
	    	                tsResourceBusF.setBusType(tsResourceType2.getId());
	    	                tsResourceBusF.setColumnId(column.getId());
	    	                tsResourceBusF.setColumnJdbcType(column.getJdbcType());
	    	                tsResourceBusF.setColumnName(column.getName());
	    	                tsResourceBusF.setTableId(column.getGenTable().getId());
	    	                tsResourceBusDao.insert(tsResourceBusF);
	    	            }

	    	        }
				}
	        	
	        }

	    }
	
	
	
}