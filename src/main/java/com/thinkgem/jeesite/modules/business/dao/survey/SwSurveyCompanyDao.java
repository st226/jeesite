/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.survey;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurveyCompany;

/**
 * 调研报告DAO接口
 * @author suntao
 * @version 2020-04-05
 */
@MyBatisDao
public interface SwSurveyCompanyDao extends CrudDao<SwSurveyCompany> {
	
}