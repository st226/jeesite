/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.infomation.entity.SbInformationType;
import com.thinkgem.jeesite.modules.infomation.dao.SbInformationTypeDao;

/**
 * 信息化设备Service
 * @author suntao
 * @version 2020-01-16
 */
@Service
@Transactional(readOnly = true)
public class SbInformationTypeService extends TreeService<SbInformationTypeDao, SbInformationType> {

	public SbInformationType get(String id) {
		return super.get(id);
	}
	
	public List<SbInformationType> findList(SbInformationType sbInformationType) {
		if (StringUtils.isNotBlank(sbInformationType.getParentIds())){
			sbInformationType.setParentIds(","+sbInformationType.getParentIds()+",");
		}
		return super.findList(sbInformationType);
	}
	
	@Transactional(readOnly = false)
	public void save(SbInformationType sbInformationType) {
		super.save(sbInformationType);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbInformationType sbInformationType) {
		super.delete(sbInformationType);
	}
	
}