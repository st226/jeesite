/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purchase.entity.TsPurchase;
import com.thinkgem.jeesite.modules.purchase.dao.TsPurchaseDao;

/**
 * 征订信息维护Service
 * @author suntao
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class TsPurchaseService extends CrudService<TsPurchaseDao, TsPurchase> {
	
	
	@Autowired
	private TsPurchaseDao tsResourceBusDao;

	public TsPurchase get(String id) {
		return super.get(id);
	}
	
	public List<TsPurchase> findList(TsPurchase tsPurchase) {
		return super.findList(tsPurchase);
	}
	
	public List<TsPurchase> getTsPurchaseByOrder(TsPurchase tsPurchase) {
		return tsResourceBusDao.getTsPurchaseByOrder(tsPurchase);
	}
	
	public Page<TsPurchase> findPage(Page<TsPurchase> page, TsPurchase tsPurchase) {
		return super.findPage(page, tsPurchase);
	}
	
	@Transactional(readOnly = false)
	public void save(TsPurchase tsPurchase) {
		super.save(tsPurchase);
	}
	
	@Transactional(readOnly = false)
	public void saveTsPurchaseOrder(List<TsPurchase>  list , String orderId) {
		for (TsPurchase tsPurchase : list) {
            if (tsPurchase.getIsList().equals("1")) {
            	tsPurchase = super.get(tsPurchase.getId()) ;
            	tsPurchase.setOrderId(orderId);
            	super.save(tsPurchase);
            }

        }
		
	}
	
	@Transactional(readOnly = false)
	public void delete(TsPurchase tsPurchase) {
		super.delete(tsPurchase);
	}
	
}