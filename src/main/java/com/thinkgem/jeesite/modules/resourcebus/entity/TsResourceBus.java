/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebus.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 著录项管理Entity
 * @author suntao
 * @version 2018-01-18
 */
public class TsResourceBus extends DataEntity<TsResourceBus> {
	
	private static final long serialVersionUID = 1L;
	private String busType;		// 业务类型
	private String columnId;		// 表列字段ID
	private String tableId;		// 表ID
	private String local ;
	private String sort;		// 排序
	private String columnName;		// 列名称
	private String columnComments;		// 列描述
	private String columnJdbcType;		// 程序类型
	private String isInsert;		// is_insert
	private String tableComments;		// 表描述
	private String isNull;	
	private String isEdit;		// is_edit
	private String isList;		// is_list
	private String isQuery;		// is_query
	private String queryType;		// query_type
	private String showType;		// show_type
	private String dictType;		// dict_type
	private String tableName;
	private String width;
	
	public TsResourceBus() {
		super();
	}

	public TsResourceBus(String id){
		super(id);
	}

	@Length(min=0, max=50, message="业务类型长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=36, message="表ID长度必须介于 0 和 36 之间")
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}
	
	

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=50, message="列名称长度必须介于 0 和 50 之间")
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
	
	@Length(min=0, max=50, message="程序类型长度必须介于 0 和 50 之间")
	public String getColumnJdbcType() {
		return columnJdbcType;
	}

	public void setColumnJdbcType(String columnJdbcType) {
		this.columnJdbcType = columnJdbcType;
	}
	
	@Length(min=0, max=255, message="is_insert长度必须介于 0 和 255 之间")
	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}
	
	@Length(min=0, max=50, message="表描述长度必须介于 0 和 50 之间")
	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
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
	
	@Length(min=0, max=255, message="tableName长度必须介于 0 和 255 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	
}