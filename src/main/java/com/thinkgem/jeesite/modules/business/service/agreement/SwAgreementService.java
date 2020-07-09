/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.agreement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.dao.agreement.SwAgreementDao;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementPay;
import com.thinkgem.jeesite.modules.business.dao.agreement.SwAgreementPayDao;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementProduct;
import com.thinkgem.jeesite.modules.business.dao.agreement.SwAgreementProductDao;

/**
 * 合同录入Service
 * @author suntao
 * @version 2020-03-24
 */
@Service
@Transactional(readOnly = true)
public class SwAgreementService extends CrudService<SwAgreementDao, SwAgreement> {

	@Autowired
	private SwAgreementPayDao swAgreementPayDao;
	@Autowired
	private SwAgreementProductDao swAgreementProductDao;
	
	public SwAgreement get(String id) {
		SwAgreement swAgreement = super.get(id);
		swAgreement.setSwAgreementPayList(swAgreementPayDao.findList(new SwAgreementPay(swAgreement)));
		swAgreement.setSwAgreementProductList(swAgreementProductDao.findList(new SwAgreementProduct(swAgreement)));
		return swAgreement;
	}
	
	public SwAgreementPay getPay(String id) {
	
		return swAgreementPayDao.get(id);
	}
	
	public List<SwAgreement> findList(SwAgreement swAgreement) {
		return super.findList(swAgreement);
	}
	
	public Page<SwAgreement> findPage(Page<SwAgreement> page, SwAgreement swAgreement) {
		return super.findPage(page, swAgreement);
	}
	
	@Transactional(readOnly = false)
	public void save(SwAgreement swAgreement) {
		super.save(swAgreement);
		for (SwAgreementPay swAgreementPay : swAgreement.getSwAgreementPayList()){
			if (swAgreementPay.getId() == null){
				continue;
			}
			if (SwAgreementPay.DEL_FLAG_NORMAL.equals(swAgreementPay.getDelFlag())){
				if (StringUtils.isBlank(swAgreementPay.getId())){
					swAgreementPay.setAgreementId(swAgreement);
					swAgreementPay.preInsert();
					swAgreementPayDao.insert(swAgreementPay);
				}else{
					swAgreementPay.preUpdate();
					swAgreementPayDao.update(swAgreementPay);
				}
			}else{
				swAgreementPayDao.delete(swAgreementPay);
			}
		}
		for (SwAgreementProduct swAgreementProduct : swAgreement.getSwAgreementProductList()){
			if (swAgreementProduct.getId() == null){
				continue;
			}
			if (SwAgreementProduct.DEL_FLAG_NORMAL.equals(swAgreementProduct.getDelFlag())){
				if (StringUtils.isBlank(swAgreementProduct.getId())){
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
	public void savePay(SwAgreementPay swAgreementPay) {
		swAgreementPayDao.update(swAgreementPay);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(SwAgreement swAgreement) {
		super.delete(swAgreement);
		swAgreementPayDao.delete(new SwAgreementPay(swAgreement));
		swAgreementProductDao.delete(new SwAgreementProduct(swAgreement));
	}
	
	public List<SwAgreementPay> getSwAgreementPay(SwAgreementPay  swAgreementPay){
		return swAgreementPayDao.getSwAgreementPay(swAgreementPay);
	}
	
}