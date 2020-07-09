/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.resource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.quality.entity.resource.QmResourceType;
import com.thinkgem.jeesite.modules.quality.dao.resource.QmResourceTypeDao;

/**
 * 技术文件模板Service
 * @author suntao
 * @version 2020-05-08
 */
@Service
@Transactional(readOnly = true)
public class QmResourceTypeService extends TreeService<QmResourceTypeDao, QmResourceType> {

	public QmResourceType get(String id) {
		return super.get(id);
	}
	
	public List<QmResourceType> findList(QmResourceType qmResourceType) {
		if (StringUtils.isNotBlank(qmResourceType.getParentIds())){
			qmResourceType.setParentIds(","+qmResourceType.getParentIds()+",");
		}
		return super.findList(qmResourceType);
	}
	
	@Transactional(readOnly = false)
	public void save(QmResourceType qmResourceType) {
		super.save(qmResourceType);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmResourceType qmResourceType) {
		super.delete(qmResourceType);
	}
	
}