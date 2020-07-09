/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.bidding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBidding;
import com.thinkgem.jeesite.modules.business.dao.bidding.SwBiddingDao;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBiddingSupplier;
import com.thinkgem.jeesite.modules.business.dao.bidding.SwBiddingSupplierDao;

/**
 * 内部招投标Service
 * @author suntao
 * @version 2020-04-10
 */
@Service
@Transactional(readOnly = true)
public class SwBiddingService extends CrudService<SwBiddingDao, SwBidding> {

	@Autowired
	private SwBiddingSupplierDao swBiddingSupplierDao;
	
	public SwBidding get(String id) {
		SwBidding swBidding = super.get(id);
		swBidding.setSwBiddingSupplierList(swBiddingSupplierDao.findList(new SwBiddingSupplier(swBidding)));
		return swBidding;
	}
	
	public List<SwBidding> findList(SwBidding swBidding) {
		return super.findList(swBidding);
	}
	
	public Page<SwBidding> findPage(Page<SwBidding> page, SwBidding swBidding) {
		return super.findPage(page, swBidding);
	}
	
	@Transactional(readOnly = false)
	public void save(SwBidding swBidding) {
		super.save(swBidding);
		for (SwBiddingSupplier swBiddingSupplier : swBidding.getSwBiddingSupplierList()){
			if (swBiddingSupplier.getId() == null){
				continue;
			}
			if (SwBiddingSupplier.DEL_FLAG_NORMAL.equals(swBiddingSupplier.getDelFlag())){
				if (StringUtils.isBlank(swBiddingSupplier.getId())){
					swBiddingSupplier.setBiddingId(swBidding);
					swBiddingSupplier.preInsert();
					swBiddingSupplierDao.insert(swBiddingSupplier);
				}else{
					swBiddingSupplier.preUpdate();
					swBiddingSupplierDao.update(swBiddingSupplier);
				}
			}else{
				swBiddingSupplierDao.delete(swBiddingSupplier);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SwBidding swBidding) {
		super.delete(swBidding);
		swBiddingSupplierDao.delete(new SwBiddingSupplier(swBidding));
	}
	
}