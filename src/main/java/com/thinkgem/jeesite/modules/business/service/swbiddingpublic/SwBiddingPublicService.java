/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.swbiddingpublic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBidding;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBiddingSupplier;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceive;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;
import com.thinkgem.jeesite.modules.business.entity.swbiddingpublic.SwBiddingPublic;
import com.thinkgem.jeesite.modules.business.dao.bidding.SwBiddingSupplierDao;
import com.thinkgem.jeesite.modules.business.dao.swbiddingpublic.SwBiddingPublicDao;

/**
 * 公开招投标Service
 * @author suntao
 * @version 2020-04-21
 */
@Service
@Transactional(readOnly = true)
public class SwBiddingPublicService extends CrudService<SwBiddingPublicDao, SwBiddingPublic> {
	
	@Autowired
	private SwBiddingSupplierDao swBiddingSupplierDao;

	public SwBiddingPublic get(String id) {
		SwBiddingPublic swBiddingPublic = new SwBiddingPublic();
		swBiddingPublic =  super.get(id);
		SwBidding swBidding = new SwBidding() ;
		swBidding.setId(id);
		SwBiddingSupplier swBiddingSupplier = new SwBiddingSupplier();
		swBiddingSupplier.setBiddingId(swBidding);
		swBiddingPublic.setSwBiddingSupplierList(swBiddingSupplierDao.findList(swBiddingSupplier));

		return swBiddingPublic;
	}
	
	public List<SwBiddingPublic> findList(SwBiddingPublic swBiddingPublic) {
		return super.findList(swBiddingPublic);
	}
	
	public Page<SwBiddingPublic> findPage(Page<SwBiddingPublic> page, SwBiddingPublic swBiddingPublic) {
		return super.findPage(page, swBiddingPublic);
	}
	
	@Transactional(readOnly = false)
	public void save(SwBiddingPublic swBiddingPublic) {
		
		super.save(swBiddingPublic);
		for (SwBiddingSupplier swBiddingSupplier : swBiddingPublic.getSwBiddingSupplierList()){
			if (swBiddingSupplier.getId() == null){
				continue;
			}
			if (SwBiddingSupplier.DEL_FLAG_NORMAL.equals(swBiddingSupplier.getDelFlag())){
				if (StringUtils.isBlank(swBiddingSupplier.getId())){
					SwBidding swBidding = new SwBidding();
					swBidding.setId(swBiddingPublic.getId());
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
	public void delete(SwBiddingPublic swBiddingPublic) {
		super.delete(swBiddingPublic);
	}
	
}