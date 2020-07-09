/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.swbiddingpublic;

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
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementProduct;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.entity.swbiddingpublic.SwBiddingPublic;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.survey.SwSurveyService;
import com.thinkgem.jeesite.modules.business.service.swbiddingpublic.SwBiddingPublicService;

/**
 * 公开招投标Controller
 * @author suntao
 * @version 2020-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/business/swbiddingpublic/swBiddingPublic")
public class SwBiddingPublicController extends BaseController {

	@Autowired
	private SwBiddingPublicService swBiddingPublicService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwProductService swProductService;
	
	@Autowired
	private SwSurveyService swSurveyService;
	
	@ModelAttribute
	public SwBiddingPublic get(@RequestParam(required=false) String id) {
		SwBiddingPublic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swBiddingPublicService.get(id);
		}
		if (entity == null){
			entity = new SwBiddingPublic();
		}
		return entity;
	}
	
	@RequiresPermissions("business:swbiddingpublic:swBiddingPublic:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwBiddingPublic swBiddingPublic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwBiddingPublic> page = swBiddingPublicService.findPage(new Page<SwBiddingPublic>(request, response), swBiddingPublic); 
		model.addAttribute("page", page);
		return "modules/business/swbiddingpublic/swBiddingPublicList";
	}

	@RequiresPermissions("business:swbiddingpublic:swBiddingPublic:view")
	@RequestMapping(value = "form")
	public String form(SwBiddingPublic swBiddingPublic, Model model) {
		if(swBiddingPublic.getId()==null || "".equals(swBiddingPublic.getId())){
			SwOrder swOrder = swOrderService.get(swBiddingPublic.getOrderId());
		    String name = "" ;
		    double amount = 0 ; 
		    SwProduct swProduct = new SwProduct();
			swProduct.setOrderId(swOrder.getId());
			List<SwProduct> swProductList = swProductService.findList(swProduct) ;
			for (SwProduct swProduct2 : swProductList) {
				amount += swProduct2.getProductAmount() ;
				if("".equals(name)){
					name = swProduct2.getProductName();
				}else{
					name = name+ ","+swProduct2.getProductName();
				}
			}
			swBiddingPublic.setGoodsName(name);
			swBiddingPublic.setGoodsAmount(amount+"");
			swBiddingPublic.setPort("中国天津新港");
			swBiddingPublic.setState("0");
			
			if(swOrder.getField1Id()!=null && !"".equals(swOrder.getField1Id())){
				SwSurvey swSurvey = swSurveyService.get(swOrder.getField1Id());
				swBiddingPublic.setPuse(swSurvey.getMainUses());
				swBiddingPublic.setParameter(swSurvey.getTechnical());
			}
			
		}
		model.addAttribute("swBiddingPublic", swBiddingPublic);
		return "modules/business/swbiddingpublic/swBiddingPublicForm";
	}
	
	@RequiresPermissions("business:swbiddingpublic:swBiddingPublic:view")
	@RequestMapping(value = "look")
	public String look(SwBiddingPublic swBiddingPublic, Model model) {
		
		model.addAttribute("swBiddingPublic", swBiddingPublic);
		return "modules/business/swbiddingpublic/swBiddingPublicLook";
	
	}

	@RequiresPermissions("business:swbiddingpublic:swBiddingPublic:edit")
	@RequestMapping(value = "save")
	public String save(SwBiddingPublic swBiddingPublic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swBiddingPublic)){
			return form(swBiddingPublic, model);
		}
	
		swBiddingPublicService.save(swBiddingPublic);
		SwOrder swOrder = swOrderService.get(swBiddingPublic.getOrderId());
		swOrder.setField2Id(swBiddingPublic.getId());
		
		if("1".equals(swBiddingPublic.getState())){
			swOrder.setField2State("3");
			swOrder.setField2Text("<font color='green'>公开招投标</font><i style='color: green;' class='icon-ok-sign'></i>");
			swOrder.setField2DateEnd(new Date());
		    swOrder.setField3State("2");
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		
		addMessage(redirectAttributes, "保存公开招投标成功");
		
		 if("1".equals(swBiddingPublic.getState())){
				
				return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
			}else{
				return "redirect:"+Global.getAdminPath()+"/business/swbiddingpublic/swBiddingPublic/form?id="+swBiddingPublic.getId();
			}
		
		
	}
	
	@RequiresPermissions("business:swbiddingpublic:swBiddingPublic:edit")
	@RequestMapping(value = "delete")
	public String delete(SwBiddingPublic swBiddingPublic, RedirectAttributes redirectAttributes) {
		swBiddingPublicService.delete(swBiddingPublic);
		addMessage(redirectAttributes, "删除公开招投标成功");
		return "redirect:"+Global.getAdminPath()+"/business/swbiddingpublic/swBiddingPublic/?repage";
	}

}