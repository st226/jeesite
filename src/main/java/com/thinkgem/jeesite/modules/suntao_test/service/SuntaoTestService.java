/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.suntao_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.suntao_test.entity.SuntaoTest;
import com.thinkgem.jeesite.modules.suntao_test.dao.SuntaoTestDao;
import com.thinkgem.jeesite.modules.suntao_test.entity.SuntaoTestChild;
import com.thinkgem.jeesite.modules.suntao_test.dao.SuntaoTestChildDao;

/**
 * 测试主子表Service
 * @author suntao
 * @version 2020-05-02
 */
@Service
@Transactional(readOnly = true)
public class SuntaoTestService extends CrudService<SuntaoTestDao, SuntaoTest> {

	@Autowired
	private SuntaoTestChildDao suntaoTestChildDao;
	
	public SuntaoTest get(String id) {
		SuntaoTest suntaoTest = super.get(id);
		suntaoTest.setSuntaoTestChildList(suntaoTestChildDao.findList(new SuntaoTestChild(suntaoTest)));
		return suntaoTest;
	}
	
	public List<SuntaoTest> findList(SuntaoTest suntaoTest) {
		return super.findList(suntaoTest);
	}
	
	public Page<SuntaoTest> findPage(Page<SuntaoTest> page, SuntaoTest suntaoTest) {
		return super.findPage(page, suntaoTest);
	}
	
	@Transactional(readOnly = false)
	public void save(SuntaoTest suntaoTest) {
		super.save(suntaoTest);
		for (SuntaoTestChild suntaoTestChild : suntaoTest.getSuntaoTestChildList()){
			if (suntaoTestChild.getId() == null){
				continue;
			}
			if (SuntaoTestChild.DEL_FLAG_NORMAL.equals(suntaoTestChild.getDelFlag())){
				if (StringUtils.isBlank(suntaoTestChild.getId())){
					suntaoTestChild.setSunId(suntaoTest);
					suntaoTestChild.preInsert();
					suntaoTestChildDao.insert(suntaoTestChild);
				}else{
					suntaoTestChild.preUpdate();
					suntaoTestChildDao.update(suntaoTestChild);
				}
			}else{
				suntaoTestChildDao.delete(suntaoTestChild);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SuntaoTest suntaoTest) {
		super.delete(suntaoTest);
		suntaoTestChildDao.delete(new SuntaoTestChild(suntaoTest));
	}
	
}