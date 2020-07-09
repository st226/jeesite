/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collection.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.collection.entity.TsCollection;
import com.thinkgem.jeesite.modules.collection.dao.TsCollectionDao;

/**
 * 馆藏信息Service
 * @author suntao
 * @version 2018-01-28
 */
@Service
@Transactional(readOnly = true)
public class TsCollectionService extends TreeService<TsCollectionDao, TsCollection> {

	public TsCollection get(String id) {
		return super.get(id);
	}
	
	public List<TsCollection> findList(TsCollection tsCollection) {
		if (StringUtils.isNotBlank(tsCollection.getParentIds())){
			tsCollection.setParentIds(","+tsCollection.getParentIds()+",");
		}
		return super.findList(tsCollection);
	}
	
	@Transactional(readOnly = false)
	public void save(TsCollection tsCollection) {
		super.save(tsCollection);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsCollection tsCollection) {
		super.delete(tsCollection);
	}
	
}