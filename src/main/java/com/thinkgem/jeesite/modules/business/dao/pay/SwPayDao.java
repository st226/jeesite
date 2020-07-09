/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.pay;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPayYs;

/**
 * 请款单DAO接口
 * @author suntao
 * @version 2020-03-20
 */
@MyBatisDao
public interface SwPayDao extends CrudDao<SwPay> {
	
	public SwPay getByPayId(SwPay swPay);
	
	public List<Map> findListMap(Map map);
	public Map getMap(Map map);
	
	
	public List<Map> findListMap2(Map map);
	public Map getMap2(Map map);
	
	public List<SwPayYs> findListYs(Map map);
	
}