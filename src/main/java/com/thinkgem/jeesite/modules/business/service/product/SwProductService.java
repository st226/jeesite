/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.dao.product.SwProductDao;

/**
 * 采购设备清单Service
 * @author suntao
 * @version 2020-03-27
 */
@Service
@Transactional(readOnly = true)
public class SwProductService extends CrudService<SwProductDao, SwProduct> {

	public SwProduct get(String id) {
		return super.get(id);
	}
	
	public List<SwProduct> findList(SwProduct swProduct) {
		return super.findList(swProduct);
	}
	
	public Page<SwProduct> findPage(Page<SwProduct> page, SwProduct swProduct) {
		return super.findPage(page, swProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(SwProduct swProduct) {
		super.save(swProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwProduct swProduct) {
		super.delete(swProduct);
	}
	
}