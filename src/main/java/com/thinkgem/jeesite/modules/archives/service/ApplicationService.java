/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.archives.dao.ApplicationDao;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.archives.dao.ModelDao;

/**
 * 现行文件管理Service
 * @author suntao
 * @version 2019-11-10
 */
@Service
@Transactional(readOnly = true)
public class ApplicationService extends CrudService<ApplicationDao, Application> {

	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private ApplicationDao applicationDao ;
	
	public Application get(String id) {
		Application application = super.get(id);
		application.setModelList(modelDao.findList(new Model(application)));
		return application;
	}
	
	public Model getModel(Model model){
		
		return modelDao.get(model) ;
		
	}
	
	@Transactional(readOnly = false)
    public void saveModel(Model model){
		
		modelDao.update(model) ;
		
	}
	
	public Application getByProcessInstID(long processInstID) {
		Application application = applicationDao.getByProcessInstID(processInstID);
		application.setModelList(modelDao.findList(new Model(application)));
		return application;
	}
	
	public List<Application> findList(Application application) {
		return super.findList(application);
	}
	
	public Page<Application> findPage(Page<Application> page, Application application) {
		return super.findPage(page, application);
	}
	
	public Page<Model> findModelPage(Page<Model> page, Model model) {
		model.setPage(page);
		page.setList(modelDao.getModelList(model));
		return page;
	}
	
	public String getCode(String code){
		return applicationDao.getCode(code);
	}
	
	@Transactional(readOnly = false)
	public void save(Application application) {
		super.save(application);
		for (Model model : application.getModelList()){
			if (model.getId() == null){
				continue;
			}
			if (Model.DEL_FLAG_NORMAL.equals(model.getDelFlag())){
				if (StringUtils.isBlank(model.getId())){
					model.setApplicationId(application);
					model.preInsert();
					modelDao.insert(model);
				}else{
					model.preUpdate();
					modelDao.update(model);
				}
			}else{
				modelDao.delete(model);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Application application) {
		super.delete(application);
		modelDao.delete(new Model(application));
	}
	
}