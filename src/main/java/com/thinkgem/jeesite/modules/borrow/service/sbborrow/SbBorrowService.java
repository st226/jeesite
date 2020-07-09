/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.service.sbborrow;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.dao.sbborrow.SbBorrowDao;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.equipment.dao.sbcache.SbCacheDao;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.archives.entity.Model;
import com.thinkgem.jeesite.modules.borrow.dao.sbborrow.SbBorrowChildDao;

/**
 * 设备借用Service
 * @author suntao
 * @version 2019-12-26
 */
@Service
@Transactional(readOnly = true)
public class SbBorrowService extends CrudService<SbBorrowDao, SbBorrow> {

	@Autowired
	private SbBorrowChildDao sbBorrowChildDao;
	
	@Autowired
	private SbBorrowDao sbBorrowDao;
	
	@Autowired
	private SbCacheDao sbCacheDao;
	
	public SbBorrow get(String id) {
		SbBorrow sbBorrow = super.get(id);
		sbBorrow.setSbBorrowChildList(sbBorrowChildDao.findList(new SbBorrowChild(sbBorrow)));
		return sbBorrow;
	}
	
	public SbBorrowChild getChild(String childid) {
		return sbBorrowChildDao.get(childid);
	}
	
	public SbBorrowChild getSbBorrowChild(SbBorrowChild sbBorrowChild) {
		return sbBorrowChildDao.getSbBorrowChild(sbBorrowChild);
	}
	
	
	@Transactional(readOnly = false)
	public int updateChild(SbBorrowChild sbBorrowChild) {
		return sbBorrowChildDao.update(sbBorrowChild) ;
	}
	
	public SbBorrow getByProcessInstID(long processInstID) {
		SbBorrow sbBorrow = sbBorrowDao.getByProcessInstID(processInstID);
		sbBorrow.setSbBorrowChildList(sbBorrowChildDao.findList(new SbBorrowChild(sbBorrow)));
		return sbBorrow;
	}
	
	public List<SbBorrow> findList(SbBorrow sbBorrow) {
		return super.findList(sbBorrow);
	}
	
	public List<SbBorrowChild> findChildList(SbBorrowChild sbBorrowChild) {
		return sbBorrowChildDao.findList(sbBorrowChild);
	}
	
	public Page<SbBorrow> findPage(Page<SbBorrow> page, SbBorrow sbBorrow) {
		return super.findPage(page, sbBorrow);
	}
	public Page<SbBorrowChild> findChildPage(Page<SbBorrowChild> page, SbBorrowChild sbBorrowChild) {
		sbBorrowChild.setPage(page);
		page.setList(sbBorrowChildDao.findList(sbBorrowChild));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void saveCache(SbCache sbCache) {
		sbCache.setId(UUID.randomUUID().toString());
		sbCacheDao.insert(sbCache) ;
	}
	
	public List<SbCache> findCacheList(SbCache sbCache) {
		return sbCacheDao.findList(sbCache);
	}
	
	
	@Transactional(readOnly = false)
	public void save(SbBorrow sbBorrow) {
		super.save(sbBorrow);
		for (SbBorrowChild sbBorrowChild : sbBorrow.getSbBorrowChildList()){
			if (sbBorrowChild.getId() == null){
				continue;
			}
			if (SbBorrowChild.DEL_FLAG_NORMAL.equals(sbBorrowChild.getDelFlag())){
				if (StringUtils.isBlank(sbBorrowChild.getId())){
					sbBorrowChild.setBorrowId(sbBorrow);
					sbBorrowChild.preInsert();
					sbBorrowChildDao.insert(sbBorrowChild);
				}else{
					sbBorrowChild.preUpdate();
					sbBorrowChildDao.update(sbBorrowChild);
				}
			}else{
				sbBorrowChildDao.delete(sbBorrowChild);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void saveChild(SbBorrowChild sbBorrowChild) {
		sbBorrowChildDao.update(sbBorrowChild);
	}
	
	@Transactional(readOnly = false)
	public void insertChild(SbBorrowChild sbBorrowChild) {
		sbBorrowChild.setId(UUID.randomUUID().toString());
		sbBorrowChildDao.insert(sbBorrowChild);
	}
	
	@Transactional(readOnly = false)
	public void delete(SbBorrow sbBorrow) {
		super.delete(sbBorrow);
		sbBorrowChildDao.delete(new SbBorrowChild(sbBorrow));
	}
	
	@Transactional(readOnly = false)
	public void deleteBysbId(String sbId) {
		sbCacheDao.deleteBysbId(sbId);
	}
	
	@Transactional(readOnly = false)
	public void deleteByuserId(Map map) {
		sbCacheDao.deleteByuserId(map);
	}
	
}