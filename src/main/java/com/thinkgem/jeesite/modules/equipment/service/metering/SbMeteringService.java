/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.service.metering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMetering;
import com.thinkgem.jeesite.modules.equipment.dao.metering.SbMeteringDao;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMeteringChild;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdModel;
import com.thinkgem.jeesite.modules.borrow.dao.sbborrow.SbBorrowDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.equipment.dao.metering.SbMeteringChildDao;

/**
 * 计量管理Service
 * @author suntao
 * @version 2020-01-01
 */
@Service
@Transactional(readOnly = true)
public class SbMeteringService extends CrudService<SbMeteringDao, SbMetering> {

	@Autowired
	private SbMeteringChildDao sbMeteringChildDao;
	
	@Autowired
	private SbMeteringDao sbMeteringDao;
	
	public SbMetering get(String id) {
		SbMetering sbMetering = super.get(id);
		sbMetering.setSbMeteringChildList(sbMeteringChildDao.findList(new SbMeteringChild(sbMetering)));
		return sbMetering;
	}
	
	public SbMeteringChild getSbMeteringChild(String id) {
		SbMeteringChild sbMeteringChild = sbMeteringChildDao.get(id);
		return sbMeteringChild;
	}
	
	@Transactional(readOnly = false)
	public void saveSbMeteringChild(SbMeteringChild sbMeteringChild){
		sbMeteringChildDao.update(sbMeteringChild);
	}
	
	public SbMetering getByProcessInstID(long processInstID) {
		SbMetering sbMetering = sbMeteringDao.getByProcessInstID(processInstID);
		sbMetering.setSbMeteringChildList(sbMeteringChildDao.findList(new SbMeteringChild(sbMetering)));
		return sbMetering;
	}
	
	public List<SbMeteringChild> getMeteringChildList(SbMeteringChild sbMeteringChild){
		return sbMeteringChildDao.getMeteringChildList(sbMeteringChild);
	}
	
	public SbMetering getByField1(String field1) {
		SbMetering sbMetering = new SbMetering();
		SbMeteringChild sbMeteringChild = new SbMeteringChild();
		sbMeteringChild.setField1(field1);
		sbMeteringChildDao.getMeteringChildList(sbMeteringChild) ;
		sbMetering.setSbMeteringChildList(sbMeteringChildDao.findList(sbMeteringChild));
		return sbMetering;
	}
	
	public List<SbMetering> findList(SbMetering sbMetering) {
		return super.findList(sbMetering);
	}
	
	public Page<SbMetering> findPage(Page<SbMetering> page, SbMetering sbMetering) {
		return super.findPage(page, sbMetering);
	}
	
	public Page<SbMeteringChild> findSbMeteringChildPage(Page<SbMeteringChild> page, SbMeteringChild sbMeteringChild) {
		sbMeteringChild.setPage(page);
		page.setList(sbMeteringChildDao.getMeteringChildList(sbMeteringChild));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SbMetering sbMetering) {
		super.save(sbMetering);
		for (SbMeteringChild sbMeteringChild : sbMetering.getSbMeteringChildList()){
			if (sbMeteringChild.getId() == null){
				continue;
			}
			if (SbMeteringChild.DEL_FLAG_NORMAL.equals(sbMeteringChild.getDelFlag())){
				if (StringUtils.isBlank(sbMeteringChild.getId())){
					sbMeteringChild.setMeteringId(sbMetering);
					sbMeteringChild.preInsert();
					sbMeteringChildDao.insert(sbMeteringChild);
				}else{
					sbMeteringChild.preUpdate();
					sbMeteringChildDao.update(sbMeteringChild);
				}
			}else{
				sbMeteringChildDao.delete(sbMeteringChild);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SbMetering sbMetering) {
		super.delete(sbMetering);
		sbMeteringChildDao.delete(new SbMeteringChild(sbMetering));
	}
	
}