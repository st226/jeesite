/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.bidding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBidding;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBiddingSupplier;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurveyCompany;
import com.thinkgem.jeesite.modules.business.service.bidding.SwBiddingService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.survey.SwSurveyService;

/**
 * 内部招投标Controller
 * @author suntao
 * @version 2020-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/business/bidding/swBidding")
public class SwBiddingController extends BaseController {

	@Autowired
	private SwBiddingService swBiddingService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwSurveyService swSurveyService;
	
	
	@ModelAttribute
	public SwBidding get(@RequestParam(required=false) String id) {
		SwBidding entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swBiddingService.get(id);
		}
		if (entity == null){
			entity = new SwBidding();
		}
		return entity;
	}
	
	@RequiresPermissions("business:bidding:swBidding:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwBidding swBidding, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwBidding> page = swBiddingService.findPage(new Page<SwBidding>(request, response), swBidding); 
		model.addAttribute("page", page);
		return "modules/business/bidding/swBiddingList";
	}

	@RequiresPermissions("business:bidding:swBidding:view")
	@RequestMapping(value = "form")
	public String form(SwBidding swBidding, Model model) {
		List<SwBiddingSupplier> list = new ArrayList<SwBiddingSupplier>();
		SwBiddingSupplier swBiddingSupplier = new SwBiddingSupplier();
		if(swBidding.getId()==null || "".equals(swBidding.getId())){
			swBidding.setState("0");
			if(swBidding.getOrderId()!=null && !"".equals(swBidding.getOrderId())){
				SwOrder swOrder = swOrderService.get(swBidding.getOrderId()) ;
				swBidding.setProjectName(swOrder.getName());
				swBidding.setGoodsName(swOrder.getField3());
				swBidding.setGoodsAmount(swOrder.getField4());
				swBidding.setBudget(swOrder.getAmountYs().toString());
				swBidding.setAddress("北京市海淀区丰滢东路1号院内");
				
				if(swOrder.getField1Id()!=null && !"".equals(swOrder.getField1Id()) && "0".equals(swOrder.getType())){
					SwSurvey swSurvey = swSurveyService.get(swOrder.getField1Id());
					swBidding.setTechnical(swSurvey.getTechnical());
					for (SwSurveyCompany swSurveyCompany : swSurvey.getSwSurveyCompanyList()) {
						swBiddingSupplier.setSupplierId(swSurveyCompany.getField1());
						swBiddingSupplier.setNegotiatePrice(swSurveyCompany.getPrice());
						swBiddingSupplier.setSupplierName(swSurveyCompany.getName());
						swBiddingSupplier.setSupplierUser(swSurveyCompany.getContacts());
						swBiddingSupplier.setPhone(swSurveyCompany.getTelephone());
						list.add(swBiddingSupplier);
						swBiddingSupplier = new SwBiddingSupplier();
					}
					swBidding.setSwBiddingSupplierList(list);
				}
				
				
				
			}
		}
		
		
		model.addAttribute("swBidding", swBidding);
		return "modules/business/bidding/swBiddingForm";
	}

	@RequiresPermissions("business:bidding:swBidding:edit")
	@RequestMapping(value = "save")
	public String save(SwBidding swBidding, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swBidding)){
			return form(swBidding, model);
		}
		swBiddingService.save(swBidding);
		SwOrder swOrder = swOrderService.get(swBidding.getOrderId());
		swOrder.setField2Id(swBidding.getId());
		
		if("1".equals(swBidding.getState())){
			swOrder.setField2State("3");
			swOrder.setField2DateEnd(new Date());
			swOrder.setField2Text("<font color='green'>内部招投标</font><i style='color: green;' class='icon-ok-sign'></i>");
		    swOrder.setField3State("2");
		    swOrder.setState("3");
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		addMessage(redirectAttributes, "保存内部招投标成功");
		
       if("1".equals(swBidding.getState())){
			
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?identification="+swOrder.getIdentification();
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/bidding/swBidding/form?id="+swBidding.getId();
		}

	}
	
	@RequiresPermissions("business:bidding:swBidding:view")
	@RequestMapping(value = "view")
	public String view(SwBidding swBidding, Model model) {
		
		model.addAttribute("swBidding", swBidding);
		return "modules/business/bidding/swBiddingView";
	}
	
	@RequiresPermissions("business:bidding:swBidding:view")
	@RequestMapping(value = "look")
	public String look(SwBidding swBidding, Model model) {
		
		model.addAttribute("swBidding", swBidding);
		return "modules/business/bidding/swBiddingLook";
	}
	
	@RequiresPermissions("business:bidding:swBidding:edit")
	@RequestMapping(value = "delete")
	public String delete(SwBidding swBidding, RedirectAttributes redirectAttributes) {
		swBiddingService.delete(swBidding);
		addMessage(redirectAttributes, "删除内部招投标成功");
		return "redirect:"+Global.getAdminPath()+"/business/bidding/swBidding/?repage";
	}

}