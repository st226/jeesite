/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementPay;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementProduct;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;
import com.thinkgem.jeesite.modules.business.dao.agreement.SwAgreementProductDao;
import com.thinkgem.jeesite.modules.business.dao.purchase.SwPurchaseDao;

/**
 * 仪器申购单Service
 * @author suntao
 * @version 2020-03-21
 */
@Service
@Transactional(readOnly = true)
public class SwPurchaseService extends CrudService<SwPurchaseDao, SwPurchase> {
	
	@Autowired
	private SwAgreementProductDao swAgreementProductDao;

	public SwPurchase get(String id) {
		SwPurchase swPurchase = super.get(id);
		SwAgreement swAgreement = new SwAgreement() ;
		swAgreement.setId(swPurchase.getId());
		swPurchase.setSwAgreementProductList(swAgreementProductDao.findList(new SwAgreementProduct(swAgreement)));
		return swPurchase;
	}
	
	public List<SwPurchase> findList(SwPurchase swPurchase) {
		return super.findList(swPurchase);
	}
	
	public Page<SwPurchase> findPage(Page<SwPurchase> page, SwPurchase swPurchase) {
		return super.findPage(page, swPurchase);
	}
	
	@Transactional(readOnly = false)
	public void save(SwPurchase swPurchase) {
		super.save(swPurchase);
	
		for (SwAgreementProduct swAgreementProduct : swPurchase.getSwAgreementProductList()){
			if (swAgreementProduct.getId() == null){
				continue;
			}
			if (SwAgreementProduct.DEL_FLAG_NORMAL.equals(swAgreementProduct.getDelFlag())){
				if (StringUtils.isBlank(swAgreementProduct.getId())){
					SwAgreement swAgreement = new SwAgreement() ;
					swAgreement.setId(swPurchase.getId());
					swAgreementProduct.setAgreementId(swAgreement);
					swAgreementProduct.preInsert();
					swAgreementProductDao.insert(swAgreementProduct);
				}else{
					swAgreementProduct.preUpdate();
					swAgreementProductDao.update(swAgreementProduct);
				}
			}else{
				swAgreementProductDao.delete(swAgreementProduct);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SwPurchase swPurchase) {
		super.delete(swPurchase);
	}
	
}