/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.dao.survey.SwSurveyDao;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurveyCompany;
import com.thinkgem.jeesite.modules.business.dao.survey.SwSurveyCompanyDao;

/**
 * 调研报告Service
 * @author suntao
 * @version 2020-04-05
 */
@Service
@Transactional(readOnly = true)
public class SwSurveyService extends CrudService<SwSurveyDao, SwSurvey> {

	@Autowired
	private SwSurveyCompanyDao swSurveyCompanyDao;
	
	public SwSurvey get(String id) {
		SwSurvey swSurvey = super.get(id);
		swSurvey.setSwSurveyCompanyList(swSurveyCompanyDao.findList(new SwSurveyCompany(swSurvey)));
		return swSurvey;
	}
	
	public List<SwSurvey> findList(SwSurvey swSurvey) {
		return super.findList(swSurvey);
	}
	
	public Page<SwSurvey> findPage(Page<SwSurvey> page, SwSurvey swSurvey) {
		return super.findPage(page, swSurvey);
	}
	
	@Transactional(readOnly = false)
	public void save(SwSurvey swSurvey) {
		super.save(swSurvey);
		for (SwSurveyCompany swSurveyCompany : swSurvey.getSwSurveyCompanyList()){
			if (swSurveyCompany.getId() == null){
				continue;
			}
			if (SwSurveyCompany.DEL_FLAG_NORMAL.equals(swSurveyCompany.getDelFlag())){
				if (StringUtils.isBlank(swSurveyCompany.getId())){
					swSurveyCompany.setSurveyId(swSurvey);
					swSurveyCompany.preInsert();
					swSurveyCompanyDao.insert(swSurveyCompany);
				}else{
					swSurveyCompany.preUpdate();
					swSurveyCompanyDao.update(swSurveyCompany);
				}
			}else{
				swSurveyCompanyDao.delete(swSurveyCompany);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SwSurvey swSurvey) {
		super.delete(swSurvey);
		swSurveyCompanyDao.delete(new SwSurveyCompany(swSurvey));
	}
	
}