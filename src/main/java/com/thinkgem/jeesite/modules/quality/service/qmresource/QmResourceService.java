/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.service.qmresource;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.quality.entity.qmresource.QmResource;
import com.thinkgem.jeesite.modules.quality.dao.qmresource.QmResourceDao;

/**
 * 技术文件管理Service
 * @author suntao
 * @version 2020-05-08
 */
@Service
@Transactional(readOnly = true)
public class QmResourceService extends CrudService<QmResourceDao, QmResource> {

	public QmResource get(String id) {
		return super.get(id);
	}
	
	public List<QmResource> findList(QmResource qmResource) {
		return super.findList(qmResource);
	}
	
	public Page<QmResource> findPage(Page<QmResource> page, QmResource qmResource) {
		return super.findPage(page, qmResource);
	}
	
	@Transactional(readOnly = false)
	public void save(QmResource qmResource) {
		super.save(qmResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmResource qmResource) {
		super.delete(qmResource);
	}
	
}