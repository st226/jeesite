/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdApplication;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.sdarchives.dao.SdApplicationDao;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdModel;
import com.thinkgem.jeesite.modules.sdarchives.dao.SdModelDao;

/**
 * 三单维护Service
 * @author suntao
 * @version 2019-11-14
 */
@Service
@Transactional(readOnly = true)
public class SdApplicationService extends CrudService<SdApplicationDao, SdApplication> {

	@Autowired
	private SdModelDao sdModelDao;
	@Autowired
	private SdApplicationDao sdApplicationDao ;
	
	public SdApplication get(String id) {
		SdApplication sdApplication = super.get(id);
		sdApplication.setSdModelList(sdModelDao.findList(new SdModel(sdApplication)));
		return sdApplication;
	}
	
	public List<SdApplication> findList(SdApplication sdApplication) {
		return super.findList(sdApplication);
	}
	
	public Page<SdApplication> findPage(Page<SdApplication> page, SdApplication sdApplication) {
		return super.findPage(page, sdApplication);
	}
	
	public Page<SdModel> findChildPage(Page<SdModel> page, SdModel sdModel) {
		sdModel.setPage(page);
		page.setList(sdModelDao.findList(sdModel));
		return page;
	}
	
	public Page<SdModel> findModelPage(Page<SdModel> page, SdModel sdModel) {
		sdModel.setPage(page);
		page.setList(sdModelDao.getModelList(sdModel));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(SdApplication sdApplication) {
		super.save(sdApplication);
		for (SdModel sdModel : sdApplication.getSdModelList()){
			if (sdModel.getId() == null){
				continue;
			}
			if (SdModel.DEL_FLAG_NORMAL.equals(sdModel.getDelFlag())){
				if (StringUtils.isBlank(sdModel.getId())){
					sdModel.setApplicationId(sdApplication);
					sdModel.preInsert();
					sdModel.setSdType(sdApplication.getSdType());
					sdModelDao.insert(sdModel);
				}else{
					sdModel.preUpdate();
					sdModelDao.update(sdModel);
				}
			}else{
				sdModelDao.delete(sdModel);
			}
		}
	}
	
     public SdModel getModel(SdModel sdmodel){
		
		return sdModelDao.get(sdmodel) ;
		
	}
     
     @Transactional(readOnly = false)
     public void saveModel(SdModel sdmodel){
 		
    	 sdModelDao.update(sdmodel) ;
 		
 	}
	
	@Transactional(readOnly = false)
	public SdApplication getByProcessInstID(long processInstID){
		SdApplication sdApplication = sdApplicationDao.getByProcessInstID(processInstID);
		sdApplication.setSdModelList(sdModelDao.findList(new SdModel(sdApplication)));
		return sdApplication;
	}
	
	@Transactional(readOnly = false)
	public void delete(SdApplication sdApplication) {
		super.delete(sdApplication);
		sdModelDao.delete(new SdModel(sdApplication));
	}
	
	@Transactional(readOnly = false)
	public void deleteModel(SdModel sdModel) {
		sdModelDao.delete(sdModel);
	}
	
}