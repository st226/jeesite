/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.negotiate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiateSupplier;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurveyCompany;
import com.thinkgem.jeesite.modules.business.entity.swbiddingpublic.SwBiddingPublic;
import com.thinkgem.jeesite.modules.business.service.bidding.SwBiddingService;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.special.SwSpecialService;
import com.thinkgem.jeesite.modules.business.service.supplier.SwSupplierService;
import com.thinkgem.jeesite.modules.business.service.survey.SwSurveyService;
import com.thinkgem.jeesite.modules.business.service.swbiddingpublic.SwBiddingPublicService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 商务谈判Controller
 * @author 孙涛
 * @version 2020-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/business/negotiate/swNegotiate")
public class SwNegotiateController extends BaseController {

	@Autowired
	private SwNegotiateService swNegotiateService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwSpecialService swSpecialService;
	
	@Autowired
	private SwSurveyService swSurveyService;
	
	@Autowired
	private SwBiddingService swBiddingService;
	
	@Autowired
	private SwBiddingPublicService swBiddingPublicService;
	
	
	
	@ModelAttribute
	public SwNegotiate get(@RequestParam(required=false) String id) {
		SwNegotiate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swNegotiateService.get(id);
		}
		if (entity == null){
			entity = new SwNegotiate();
		}
		return entity;
	}
	
	@RequiresPermissions("business:negotiate:swNegotiate:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwNegotiate swNegotiate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwNegotiate> page = swNegotiateService.findPage(new Page<SwNegotiate>(request, response), swNegotiate); 
		model.addAttribute("page", page);
		return "modules/business/negotiate/swNegotiateList";
	}

	@RequiresPermissions("business:negotiate:swNegotiate:view")
	@RequestMapping(value = "form")
	public String form(SwNegotiate swNegotiate, Model model) {
		SwOrder swOrder = new SwOrder();
		SwNegotiateSupplier swNegotiateSupplier = new SwNegotiateSupplier();
		List<SwNegotiateSupplier> list = new ArrayList<SwNegotiateSupplier>();
		if(swNegotiate.getId()==null || "".equals(swNegotiate.getId())){
			swNegotiate.setState("0");
			swNegotiate.setNegotiateUser(UserUtils.getUser().getName());
			if(swNegotiate.getOrderId()!=null && !"".equals(swNegotiate.getOrderId())){
				swOrder = swOrderService.get(swNegotiate.getOrderId()) ;
				swNegotiate.setProjectName(swOrder.getName());
				
				
				//如果是特殊项目或者航天电子超市则查询特殊项目申请表
				if(("1".equals(swOrder.getType()) || "2".equals(swOrder.getType())) && swOrder.getField1Id()!=null){
					SwSpecial  swSpecial =	swSpecialService.get(swOrder.getField1Id());
					swNegotiateSupplier.setSupplierId(swSpecial.getSupplierId());
					swNegotiateSupplier.setSupplierName(swSpecial.getSupplierName());
					swNegotiateSupplier.setSupplierUser(swSpecial.getSupplierUser());
					swNegotiateSupplier.setPhone(swSpecial.getSupplierTel());
					list.add(swNegotiateSupplier);
					swNegotiate.setSwNegotiateSupplierList(list);
					swNegotiate.setProjectContent(swSpecial.getContext());
					swNegotiate.setNegotiateNotes("客户报价总额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元，经过谈判客户减去&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元，最终谈判价");
					
	
				}
				
				//非特殊项目查询调研表
				if("0".equals(swOrder.getType()) && swOrder.getField1Id()!=null){
					SwSurvey swSurvey = swSurveyService.get(swOrder.getField1Id());
					List<SwSurveyCompany> swSurveyCompanyList = swSurvey.getSwSurveyCompanyList();
					for (SwSurveyCompany swSurveyCompany : swSurveyCompanyList) {
						swNegotiateSupplier.setSupplierId(swSurveyCompany.getField1());
						swNegotiateSupplier.setSupplierName(swSurveyCompany.getName());
						swNegotiateSupplier.setSupplierUser(swSurveyCompany.getContacts());
						swNegotiateSupplier.setPhone(swSurveyCompany.getTelephone());
						list.add(swNegotiateSupplier);
						swNegotiateSupplier = new SwNegotiateSupplier();
					}
					swNegotiate.setProjectContent(swSurvey.getMainUses());
					swNegotiate.setSwNegotiateSupplierList(list);
					
				
					if(swOrder.getField2Id()!=null && !"".equals(swOrder.getField2Id()) && swOrder.getAmountYs()<2000000){
						list = new ArrayList<>();
						SwBidding swBidding = swBiddingService.get(swOrder.getField2Id()) ;
						swNegotiate.setProcedures("1");
						swNegotiate.setSupplier("1");
						
						List<SwBiddingSupplier> swBiddingSupplierList = swBidding.getSwBiddingSupplierList() ;
						for (SwBiddingSupplier swBiddingSupplier : swBiddingSupplierList) {
							if("1".equals(swBiddingSupplier.getIsBid())){
								
								swNegotiateSupplier.setSupplierId(swBiddingSupplier.getSupplierId());
								swNegotiateSupplier.setSupplierName(swBiddingSupplier.getSupplierName());
								swNegotiateSupplier.setSupplierUser(swBiddingSupplier.getSupplierUser());
								swNegotiateSupplier.setPhone(swBiddingSupplier.getPhone());
								
								list.add(swNegotiateSupplier);
								swNegotiateSupplier = new SwNegotiateSupplier();
								if(swBiddingSupplier.getNegotiatePrice()!=null && !"".equals(swBiddingSupplier.getNegotiatePrice())){
									double price = Double.valueOf(swBiddingSupplier.getNegotiatePrice()) ;
									 DecimalFormat df = new DecimalFormat("#.00");
									swNegotiate.setTotalPrice(df.format(price*10000)+"");
									swNegotiate.setQuotedPrice(df.format(price*10000)+"");
								}
								
								
								
							}
							
							
						}
						swNegotiate.setSwNegotiateSupplierList(list);
						
					}
					
					if(swOrder.getField2Id()!=null && !"".equals(swOrder.getField2Id()) && swOrder.getAmountYs()>=2000000){
						list = new ArrayList<>();
						SwBiddingPublic swBiddingPublic = swBiddingPublicService.get(swOrder.getField2Id()) ;
						swNegotiate.setProcedures("1");
						swNegotiate.setSupplier("1");
						
						List<SwBiddingSupplier> swBiddingSupplierList = swBiddingPublic.getSwBiddingSupplierList() ;
						for (SwBiddingSupplier swBiddingSupplier : swBiddingSupplierList) {
							if("1".equals(swBiddingSupplier.getIsBid())){
								
								swNegotiateSupplier.setSupplierId(swBiddingSupplier.getSupplierId());
								swNegotiateSupplier.setSupplierName(swBiddingSupplier.getSupplierName());
								swNegotiateSupplier.setSupplierUser(swBiddingSupplier.getSupplierUser());
								swNegotiateSupplier.setPhone(swBiddingSupplier.getPhone());
								
								list.add(swNegotiateSupplier);
								swNegotiateSupplier = new SwNegotiateSupplier();
								if(swBiddingSupplier.getNegotiatePrice()!=null && !"".equals(swBiddingSupplier.getNegotiatePrice())){
									double price = Double.valueOf(swBiddingSupplier.getNegotiatePrice()) ;
									 DecimalFormat df = new DecimalFormat("#.00");
									swNegotiate.setTotalPrice(df.format(price*10000)+"");
									swNegotiate.setQuotedPrice(df.format(price*10000)+"");
								}
								
								
								
							}
							
							
						}
						swNegotiate.setSwNegotiateSupplierList(list);
						
					}
				
				
				} 
				
			}
		}
		if(swNegotiate.getOrderId()!=null && !"".equals(swNegotiate.getOrderId())){
			swOrder = swOrderService.get(swNegotiate.getOrderId()) ;
		}
		model.addAttribute("type", swOrder.getTypeOrder());
		model.addAttribute("swNegotiate", swNegotiate);
		return "modules/business/negotiate/swNegotiateForm";
	}
	
	@RequiresPermissions("business:negotiate:swNegotiate:view")
	@RequestMapping(value = "view")
	public String view(SwNegotiate swNegotiate, Model model) {
		if(swNegotiate.getField2()!=null && !"".equals(swNegotiate.getField2())){
			swNegotiate.setNegotiateNotes("本次采购已经完成航天电子采购平台询比价，询价场次号为："+swNegotiate.getField2()+"。"+swNegotiate.getNegotiateNotes());
		}
		model.addAttribute("swNegotiate", swNegotiate);
		return "modules/business/negotiate/swNegotiateView";
	}
	@RequiresPermissions("business:negotiate:swNegotiate:view")
	@RequestMapping(value = "look")
	public String look(SwNegotiate swNegotiate, Model model) {
		
		model.addAttribute("swNegotiate", swNegotiate);
		return "modules/business/negotiate/swNegotiateLook";
	}

	@RequiresPermissions("business:negotiate:swNegotiate:edit")
	@RequestMapping(value = "save")
	public String save(SwNegotiate swNegotiate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swNegotiate)){
			return form(swNegotiate, model);
		}
		swNegotiateService.save(swNegotiate);
		
		SwOrder swOrder = swOrderService.get(swNegotiate.getOrderId());
		swOrder.setField3Id(swNegotiate.getId());
		
		if("1".equals(swNegotiate.getState())){
			swOrder.setField3State("3");
			swOrder.setField3DateEnd(new Date());
			swOrder.setField3Text("<font color='green'>谈判</font><i style='color: green;' class='icon-ok-sign'></i>");
		   
		    if(swOrder.getAmountYs()<20000){
		    	 swOrder.setField5State("2");
		    	 swOrder.setState("5");
		    }else{
		    	 swOrder.setField5State("2");
		    	 swOrder.setState("5");
		    }
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		addMessage(redirectAttributes, "保存商务谈判成功");

      if("1".equals(swNegotiate.getState())){
			
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?identification="+swOrder.getIdentification();
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/negotiate/swNegotiate/form?id="+swNegotiate.getId();
		}
	}
	
	@RequiresPermissions("business:negotiate:swNegotiate:edit")
	@RequestMapping(value = "delete")
	public String delete(SwNegotiate swNegotiate, RedirectAttributes redirectAttributes) {
		swNegotiateService.delete(swNegotiate);
		addMessage(redirectAttributes, "删除商务谈判成功");
		return "redirect:"+Global.getAdminPath()+"/business/negotiate/swNegotiate/?repage";
	}

}