/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.service.meteringtype;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.metering.entity.meteringtype.SbMeteringType;
import com.thinkgem.jeesite.modules.metering.dao.meteringtype.SbMeteringTypeDao;

/**
 * 计量设备类型Service
 * @author suntao
 * @version 2020-04-16
 */
@Service
@Transactional(readOnly = true)
public class SbMeteringTypeService extends TreeService<SbMeteringTypeDao, SbMeteringType> {

	public SbMeteringType get(String id) {
		return super.get(id);
	}
	
	public List<SbMeteringType> findList(SbMeteringType sbMeteringType) {
		if (StringUtils.isNotBlank(sbMeteringType.getParentIds())){
			sbMeteringType.setParentIds(","+sbMeteringType.getParentIds()+",");
		}
		return super.findList(sbMeteringType);
	}
	
	@Transactional(readOnly = false)
	public void save(SbMeteringType sbMeteringType) {
		super.save(sbMeteringType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbMeteringType sbMeteringType) {
		super.delete(sbMeteringType);
	}
	
}