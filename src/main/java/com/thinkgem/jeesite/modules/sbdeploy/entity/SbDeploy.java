/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sbdeploy.entity;

import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 标签配置Entity
 * @author suntao
 * @version 2020-03-17
 */
public class SbDeploy extends DataEntity<SbDeploy> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 人员
	private String busType;		// 业务类型
	private String columnId;		// 表列字段ID
	private String columnName;		// column_name
	private String columnComments;		// 列描述
	private String tableId;		// 表ID
	private String columnJdbcType;		// column_jdbc_type
	private String tableComments;		// 表描述
	private String sort;		// 排序
	private String isNull;		// is_null
	private String isInsert;		// is_insert
	private String isEdit;		// is_edit
	private String isList;		// is_list
	private String isQuery;		// is_query
	private String queryType;		// query_type
	private String showType;		// show_type
	private String dictType;		// dict_type
	private String tableName;		// table_name
	private List<TsResourceBus> tsResourceBusList  = Lists.newArrayList();  ;
	
	public SbDeploy() {
		super();
	}

	public SbDeploy(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=5000, message="业务类型长度必须介于 0 和 5000 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@Length(min=0, max=36, message="表列字段ID长度必须介于 0 和 36 之间")
	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	
	@Length(min=0, max=50, message="column_name长度必须介于 0 和 50 之间")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	@Length(min=0, max=50, message="列描述长度必须介于 0 和 50 之间")
	public String getColumnComments() {
		return columnComments;
	}

	public void setColumnComments(String columnComments) {
		this.columnComments = columnComments;
	}
	
	@Length(min=0, max=36, message="表ID长度必须介于 0 和 36 之间")
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	@Length(min=0, max=50, message="column_jdbc_type长度必须介于 0 和 50 之间")
	public String getColumnJdbcType() {
		return columnJdbcType;
	}

	public void setColumnJdbcType(String columnJdbcType) {
		this.columnJdbcType = columnJdbcType;
	}
	
	@Length(min=0, max=50, message="表描述长度必须介于 0 和 50 之间")
	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=255, message="is_null长度必须介于 0 和 255 之间")
	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	
	@Length(min=0, max=255, message="is_insert长度必须介于 0 和 255 之间")
	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}
	
	@Length(min=0, max=255, message="is_edit长度必须介于 0 和 255 之间")
	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	
	@Length(min=0, max=255, message="is_list长度必须介于 0 和 255 之间")
	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}
	
	@Length(min=0, max=255, message="is_query长度必须介于 0 和 255 之间")
	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	
	@Length(min=0, max=255, message="query_type长度必须介于 0 和 255 之间")
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	@Length(min=0, max=255, message="show_type长度必须介于 0 和 255 之间")
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}
	
	@Length(min=0, max=255, message="dict_type长度必须介于 0 和 255 之间")
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	
	@Length(min=0, max=255, message="table_name长度必须介于 0 和 255 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<TsResourceBus> getTsResourceBusList() {
		return tsResourceBusList;
	}

	public void setTsResourceBusList(List<TsResourceBus> tsResourceBusList) {
		this.tsResourceBusList = tsResourceBusList;
	}
	
	@JsonIgnore
	public List<String> getTsResourceBusIdList() {
		List<String> TsResourceBusIdList = Lists.newArrayList();
		for (TsResourceBus tsResourceBus : tsResourceBusList) {
			TsResourceBusIdList.add(tsResourceBus.getId());
		}
		return TsResourceBusIdList;
	}

	public void setTsResourceBusIdList(List<String> TsResourceBusIdList) {
		tsResourceBusList = Lists.newArrayList();
		for (String tsResourceBusId : TsResourceBusIdList) {
			TsResourceBus tsResourceBus = new TsResourceBus();
			tsResourceBus.setId(tsResourceBusId);
			tsResourceBusList.add(tsResourceBus);
		}
	}
	
	public void setTsResourceBusbusType(List<TsResourceBus> tsResourceBusList) {
		String Type ="";
		for (TsResourceBus tsResourceBus : tsResourceBusList) {
			if("".equals(Type)){
				Type = tsResourceBus.getId();
			}else{
				Type = Type+","+tsResourceBus.getId();
			}
		}
		
		busType = Type ;
		
	}
	
	
	
}