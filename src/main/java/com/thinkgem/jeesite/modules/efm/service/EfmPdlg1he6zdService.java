/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.efm.entity.EfmPdlg1he6zd;
import com.thinkgem.jeesite.modules.efm.dao.EfmPdlg1he6zdDao;

/**
 * 外来载体入账登记申请Service
 * @author l
 * @version 2021-11-19
 */
@Service
@Transactional(readOnly = true)
public class EfmPdlg1he6zdService extends CrudService<EfmPdlg1he6zdDao, EfmPdlg1he6zd> {

	public EfmPdlg1he6zd get(String id) {
		return super.get(id);
	}
	
	public List<EfmPdlg1he6zd> findList(EfmPdlg1he6zd efmPdlg1he6zd) {
		return super.findList(efmPdlg1he6zd);
	}
	
	public Page<EfmPdlg1he6zd> findPage(Page<EfmPdlg1he6zd> page, EfmPdlg1he6zd efmPdlg1he6zd) {
		return super.findPage(page, efmPdlg1he6zd);
	}
	
	@Transactional(readOnly = false)
	public void save(EfmPdlg1he6zd efmPdlg1he6zd) {
		super.save(efmPdlg1he6zd);
	}
	
	@Transactional(readOnly = false)
	public void delete(EfmPdlg1he6zd efmPdlg1he6zd) {
		super.delete(efmPdlg1he6zd);
	}
	
}