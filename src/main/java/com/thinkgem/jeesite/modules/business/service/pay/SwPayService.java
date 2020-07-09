/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.pay;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPayYs;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resourcebus.dao.TsResourceBusDao;
import com.thinkgem.jeesite.modules.business.dao.pay.SwPayDao;

/**
 * 请款单Service
 * @author suntao
 * @version 2020-03-20
 */
@Service
@Transactional(readOnly = true)
public class SwPayService extends CrudService<SwPayDao, SwPay> {
	
	@Autowired
	private SwPayDao swPayDao;

	public SwPay get(String id) {
		return super.get(id);
	}
	public SwPay getByPayId(SwPay swPay){
		return swPayDao.getByPayId(swPay);
	}
	
	public List<SwPay> findList(SwPay swPay) {
		return super.findList(swPay);
	}
	
	public Page<SwPay> findPage(Page<SwPay> page, SwPay swPay) {
		return super.findPage(page, swPay);
	}
	
	@Transactional(readOnly = false)
	public void save(SwPay swPay) {
		super.save(swPay);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwPay swPay) {
		super.delete(swPay);
	}
	
	public Page<Map> findMapPage(Page<Map> page, DataMap dataMap) {
		dataMap.setPage(page);
		dataMap.put("page", page);
	    Map map = swPayDao.getMap(dataMap);
		page.setMessage(",合计金额 "+map.get("contrate_treat")+" 元,紧急支付 "+map.get("contrate_treat2").toString()+"元");
		page.setList(swPayDao.findListMap(dataMap));
		return page;
	}
	
	public Page<Map> findMapPage2(Page<Map> page, DataMap dataMap) {
		System.out.println(page.getPageNo()+"LLLLLLLLLLLLLLLLLLL2");
		dataMap.setPage(page);
		dataMap.put("page", page);
		page.setList(swPayDao.findListMap2(dataMap));
	   // Map map = swPayDao.getMap2(dataMap);
	   // if(map!=null)
		//page.setMessage(",合计金额 "+map.get("contrate_treat")+" 元。");
		
		return page;
	}
	
	public List<SwPayYs> findListYs( DataMap dataMap) {
		return swPayDao.findListYs(dataMap);
	}
	
}