/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.survey;

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
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.survey.SwSurveyService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 调研报告Controller
 * @author suntao
 * @version 2020-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/business/survey/swSurvey")
public class SwSurveyController extends BaseController {

	@Autowired
	private SwSurveyService swSurveyService;
	
	@Autowired
	private SwOrderService swOrderService;
	
	@Autowired
	private SwProductService swProductService ;
	
	
	
	@ModelAttribute
	public SwSurvey get(@RequestParam(required=false) String id) {
		SwSurvey entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swSurveyService.get(id);
		}
		if (entity == null){
			entity = new SwSurvey();
		}
		return entity;
	}
	
	@RequiresPermissions("business:survey:swSurvey:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwSurvey swSurvey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwSurvey> page = swSurveyService.findPage(new Page<SwSurvey>(request, response), swSurvey); 
		model.addAttribute("page", page);
		return "modules/business/survey/swSurveyList";
	}

	@RequiresPermissions("business:survey:swSurvey:view")
	@RequestMapping(value = "form")
	public String form(SwSurvey swSurvey, Model model) {
		if(swSurvey.getId()==null || "".equals(swSurvey.getId())){
			swSurvey.setState("0");
			
			if(swSurvey.getOrderId()!=null && !"".equals(swSurvey.getOrderId())){
				SwOrder swOrder = swOrderService.get(swSurvey.getOrderId()) ;
				swSurvey.setField1(swOrder.getName());
				
				SwProduct swProduct = new SwProduct();
				swProduct.setOrderId(swOrder.getId());
				String swProductName = "" ;
				List<SwProduct> swProductList = swProductService.findList(swProduct) ;
				for (SwProduct swProduct2 : swProductList) {
					if("".equals(swProductName)){
						swProductName= swProduct2.getProductName();
					}else{
						swProductName= swProductName+","+swProduct2.getProductName();
					}
				}
				swSurvey.setMainUses("主要用途：");
				swSurvey.setSituation("目前情况：");
				swSurvey.setTechnical("本次需求的设备及技术指标如下：");
				
				swSurvey.setBudget("本次采购设备均为年度采购"+DictUtils.getDictLabel(swOrder.getField1(), "project_type","")+"设备总预算。预算金额为"+swOrder.getAmountYs()+"元。");
			    swSurvey.setSupplier("生产的"+swProductName+"设备，经过对比，XXX厂家的设备指标满足要求。");
			    swSurvey.setResult("通过市场调研，以上X家公司均可以提供满足技术要求的"+swProductName+"设备。在综合测试结果、性价比、供货周期进行对比后，XXX厂家的XXX设备价格低，供货快，且售后服务方便。故拟购置XXX厂家定制生产的XXX型XXX设备。");
			    if(swOrder.getAmountYs()>=500000){
			    	swSurvey.setField2("本次采购金额为"+swOrder.getAmountYs()/10000+"万元，拟内部招投标，特申请公司领导决策，开展内部招标工作。");
			    }
			    if(swOrder.getAmountYs()>=2000000){
			    	swSurvey.setField2("本次采购金额为"+swOrder.getAmountYs()/10000+"万元，拟委托中招国际招标公司进行公开招标，符合公司“三重一大”决策管理规定，特申请公司领导决策，开展公开招标工作。");
			    }
			}
		}
		if(swSurvey.getOrderId()!=null && !"".equals(swSurvey.getOrderId())){
			SwOrder swOrder = swOrderService.get(swSurvey.getOrderId()) ;
			model.addAttribute("amountYs", swOrder.getAmountYs());
		}
		
		
		
		model.addAttribute("swSurvey", swSurvey);
		return "modules/business/survey/swSurveyForm";
	}

	@RequestMapping(value = "view")
	public String view(SwSurvey swSurvey, Model model) {
		if(swSurvey.getOrderId()!=null && !"".equals(swSurvey.getOrderId())){
			SwOrder swOrder = swOrderService.get(swSurvey.getOrderId()) ;
			model.addAttribute("amountYs", swOrder.getAmountYs());
		}
		
		model.addAttribute("swSurvey", swSurvey);
		return "modules/business/survey/swSurveyView";
	}
	
	@RequestMapping(value = "look")
	public String look(SwSurvey swSurvey, Model model) {
		if(swSurvey.getOrderId()!=null && !"".equals(swSurvey.getOrderId())){
			SwOrder swOrder = swOrderService.get(swSurvey.getOrderId()) ;
			model.addAttribute("amountYs", swOrder.getAmountYs());
		}
		
		model.addAttribute("swSurvey", swSurvey);
		return "modules/business/survey/swSurveyLook";
	}
	
	@RequestMapping(value = "viewQs")
	public String viewQs(SwSurvey swSurvey, Model model) {
		model.addAttribute("swSurvey", swSurvey);
		return "modules/business/survey/swSurveyViewQs";
	}
	

	@RequiresPermissions("business:survey:swSurvey:edit")
	@RequestMapping(value = "save")
	public String save(SwSurvey swSurvey, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swSurvey)){
			return form(swSurvey, model);
		}
		
		swSurveyService.save(swSurvey);
		SwOrder swOrder = swOrderService.get(swSurvey.getOrderId());
		swOrder.setField1Id(swSurvey.getId());
		
		if("1".equals(swSurvey.getState())){
			swOrder.setField1State("3");
			
			swOrder.setField1Text("<font color='green'>调研</font><i style='color: green;' class='icon-ok-sign'></i>");
			swOrder.setField1DateEnd(new Date());
			if(swOrder.getAmountYs()<200000){
				swOrder.setField3State("2");
				swOrder.setState("3");
			}else{
				swOrder.setField2State("2");
				swOrder.setState("2");
			}
		}
		
		
		swOrderService.save(swOrder);
		addMessage(redirectAttributes, "保存调研报告成功");
		
		if("1".equals(swSurvey.getState())){
			
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?identification="+swOrder.getIdentification();
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/survey/swSurvey/form?id="+swSurvey.getId();
		}
		
		
	}
	
	@RequiresPermissions("business:survey:swSurvey:edit")
	@RequestMapping(value = "delete")
	public String delete(SwSurvey swSurvey, RedirectAttributes redirectAttributes) {
		swSurveyService.delete(swSurvey);
		addMessage(redirectAttributes, "删除调研报告成功");
		return "redirect:"+Global.getAdminPath()+"/business/survey/swSurvey/?repage";
	}

}