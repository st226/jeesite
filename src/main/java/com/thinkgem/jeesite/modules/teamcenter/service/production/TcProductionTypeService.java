/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.service.production;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.teamcenter.entity.production.TcProductionType;
import com.thinkgem.jeesite.modules.teamcenter.dao.production.TcProductionTypeDao;

/**
 * 生产bom树Service
 * @author suntao
 * @version 2020-05-24
 */
@Service
@Transactional(readOnly = true)
public class TcProductionTypeService extends TreeService<TcProductionTypeDao, TcProductionType> {

	public TcProductionType get(String id) {
		return super.get(id);
	}
	
	public List<TcProductionType> findList(TcProductionType tcProductionType) {
		if (StringUtils.isNotBlank(tcProductionType.getParentIds())){
			tcProductionType.setParentIds(","+tcProductionType.getParentIds()+",");
		}
		return super.findList(tcProductionType);
	}
	
	@Transactional(readOnly = false)
	public void save(TcProductionType tcProductionType) {
		super.save(tcProductionType);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcProductionType tcProductionType) {
		super.delete(tcProductionType);
	}
	
}