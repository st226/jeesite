/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.service.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.archives.dao.ModelDao;
import com.thinkgem.jeesite.modules.business.dao.order.SwOrderDao;
import com.thinkgem.jeesite.modules.business.dao.pay.SwPayDao;


/**
 * 采购任务Service
 * @author suntao
 * @version 2020-03-28
 */
@Service
@Transactional(readOnly = true)
public class SwOrderService extends CrudService<SwOrderDao, SwOrder> {
	
	@Autowired
	private SwOrderDao swOrderDao;
	
	@Autowired
	private SwPayDao swPayDao;

	public SwOrder get(String id) {
		return super.get(id);
	}
	
	public List<SwOrder> findList(SwOrder swOrder) {
		return super.findList(swOrder);
	}
	
	public String getCode(String code){
		return swOrderDao.getCode(code);
	}
	public SwOrder getOrderByCode(SwOrder swOrder){
		return swOrderDao.getOrderByCode(swOrder);
	}
	
	public Page<SwOrder> findPage(Page<SwOrder> page, SwOrder swOrder) {
		swOrder.setPage(page);
		List<SwOrder> list = dao.findList(swOrder) ;
		for (SwOrder swOrder2 : list) {
			SwPay swPay = new SwPay();
			swPay.setOrderId(swOrder2.getId());
			List<SwPay> swPayList = swPayDao.findList(swPay) ;
			swOrder2.setSwPayList(swPayList);
			swPay = new SwPay();
			}
		page.setList(list);
		return page;

	}
	
	public Page<SwOrder> findListYear(Page<SwOrder> page, SwOrder swOrder) {
		swOrder.setPage(page);
		page.setList(dao.findListYear(swOrder));
		return page;
	}
	
	public Page<SwOrder> findProductListYear(Page<SwOrder> page, SwOrder swOrder) {
		swOrder.setPage(page);
		page.setList(dao.findProductListYear(swOrder));
		return page;
	}
	
	
	@Transactional(readOnly = false)
	public void save(SwOrder swOrder) {
		super.save(swOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SwOrder swOrder) {
		super.delete(swOrder);
	}
	
	
	public String getGyCode( String firstCode,
		 int billNumber, boolean isMonth, boolean isDay, String deptCode, String tableName, String formSn){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
		String currentDate = df.format(new Date());      //系统当前时间

		String year = currentDate.split("-")[0];// 年
		String month = currentDate.split("-")[1];// 月
		String day = currentDate.split("-")[2];// 日

		String code ="";
        if(firstCode!=null && !"".equals(firstCode)){
        	code =firstCode + "-";
        }
		if (deptCode!=null && !"".equals(deptCode)) {
			code = code + deptCode + "-";
		}
		code = code + year + "-";
		if (isMonth) {
			code = code + month + "-";
		}
		if (isDay) {
			code = code + day + "-";
		}
		List result = null;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("tablename", tableName);
		paramMap.put("formsn", formSn);
		paramMap.put("code", code);
		//result = DBUtil.queryNamedSql(sqlMapID, code); 
		
		
		//查询最大的结果


		String resultCode = swOrderDao.getGyCode(paramMap);
		
		
		//初始化单据号
		if (resultCode == null) {
			if (billNumber == 2)
				code = code + "01";
			if (billNumber == 3)
				code = code + "001";
			if (billNumber == 4)
				code = code + "0001";
			if (billNumber == 5)
				code = code + "00001";
			if (billNumber == 6)
				code = code + "000001";
		} else {
			String[] maxCodes = resultCode.split("-");
			String maxCode = maxCodes[maxCodes.length - 1];
			int maxc = Integer.parseInt(maxCode);
			int nowc = maxc + 1;
			int len = String.valueOf(nowc).length();
			int siz = billNumber - len;
			if (siz == 0)
				code = code + String.valueOf(nowc);
			if (siz == 1)
				code = code + "0" + String.valueOf(nowc);
			if (siz == 2)
				code = code + "00" + String.valueOf(nowc);
			if (siz == 3)
				code = code + "000" + String.valueOf(nowc);
			if (siz == 4)
				code = code + "0000" + String.valueOf(nowc);
			if (siz == 5)
				code = code + "00000" + String.valueOf(nowc);
		}

		System.out.println("***********************code=" + code);
		return code;

	}
	
	
	
}