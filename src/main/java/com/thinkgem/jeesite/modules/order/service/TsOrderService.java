/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.order.entity.TsOrder;
import com.thinkgem.jeesite.modules.order.dao.TsOrderDao;

/**
 * 订单管理Service
 * @author suntao
 * @version 2018-01-22
 */
@Service
@Transactional(readOnly = true)
public class TsOrderService extends CrudService<TsOrderDao, TsOrder> {

	public TsOrder get(String id) {
		return super.get(id);
	}
	
	public List<TsOrder> findList(TsOrder tsOrder) {
		return super.findList(tsOrder);
	}
	
	public Page<TsOrder> findPage(Page<TsOrder> page, TsOrder tsOrder) {
		return super.findPage(page, tsOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(TsOrder tsOrder) {
		super.save(tsOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsOrder tsOrder) {
		super.delete(tsOrder);
	}
	
}