/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.negotiate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.dao.negotiate.SwNegotiateDao;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiateSupplier;
import com.thinkgem.jeesite.modules.business.dao.negotiate.SwNegotiateSupplierDao;

/**
 * 商务谈判Service
 * @author 孙涛
 * @version 2020-03-19
 */
@Service
@Transactional(readOnly = true)
public class SwNegotiateService extends CrudService<SwNegotiateDao, SwNegotiate> {

	@Autowired
	private SwNegotiateSupplierDao swNegotiateSupplierDao;
	
	public SwNegotiate get(String id) {
		SwNegotiate swNegotiate = super.get(id);
		swNegotiate.setSwNegotiateSupplierList(swNegotiateSupplierDao.findList(new SwNegotiateSupplier(swNegotiate)));
		return swNegotiate;
	}
	
	public List<SwNegotiate> findList(SwNegotiate swNegotiate) {
		return super.findList(swNegotiate);
	}
	
	public Page<SwNegotiate> findPage(Page<SwNegotiate> page, SwNegotiate swNegotiate) {
		return super.findPage(page, swNegotiate);
	}
	
	@Transactional(readOnly = false)
	public void save(SwNegotiate swNegotiate) {
		super.save(swNegotiate);
		for (SwNegotiateSupplier swNegotiateSupplier : swNegotiate.getSwNegotiateSupplierList()){
			if (swNegotiateSupplier.getId() == null){
				continue;
			}
			if (SwNegotiateSupplier.DEL_FLAG_NORMAL.equals(swNegotiateSupplier.getDelFlag())){
				if (StringUtils.isBlank(swNegotiateSupplier.getId())){
					swNegotiateSupplier.setNegotiateId(swNegotiate);
					swNegotiateSupplier.preInsert();
					swNegotiateSupplierDao.insert(swNegotiateSupplier);
				}else{
					swNegotiateSupplier.preUpdate();
					swNegotiateSupplierDao.update(swNegotiateSupplier);
				}
			}else{
				swNegotiateSupplierDao.delete(swNegotiateSupplier);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SwNegotiate swNegotiate) {
		super.delete(swNegotiate);
		swNegotiateSupplierDao.delete(new SwNegotiateSupplier(swNegotiate));
	}
	
}