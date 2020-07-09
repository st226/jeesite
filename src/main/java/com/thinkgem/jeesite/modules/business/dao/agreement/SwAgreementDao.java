/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.agreement;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;

/**
 * 合同录入DAO接口
 * @author suntao
 * @version 2020-03-24
 */
@MyBatisDao
public interface SwAgreementDao extends CrudDao<SwAgreement> {
	
}