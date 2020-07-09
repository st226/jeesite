/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.dao.bidding;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBidding;

/**
 * 内部招投标DAO接口
 * @author suntao
 * @version 2020-04-10
 */
@MyBatisDao
public interface SwBiddingDao extends CrudDao<SwBidding> {
	
}