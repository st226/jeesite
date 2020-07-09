/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.pay;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementPay;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPayYs;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;
import com.thinkgem.jeesite.modules.business.service.agreement.SwAgreementService;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.purchase.SwPurchaseService;
import com.thinkgem.jeesite.modules.business.web.agreement.ChineseNumber;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 请款单Controller
 * @author suntao
 * @version 2020-03-20
 */
@Controller
@RequestMapping(value = "${adminPath}/business/pay/swPay")
public class SwPayController extends BaseController {

	@Autowired
	private SwPayService swPayService;
	
	@Autowired
	private SwAgreementService swAgreementService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwPurchaseService swPurchaseService ;
	
	@Autowired
	private SwNegotiateService swNegotiateService ;
	
	
	@ModelAttribute
	public SwPay get(@RequestParam(required=false) String id) {
		SwPay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swPayService.get(id);
		}
		if (entity == null){
			entity = new SwPay();
		}
		return entity;
	}
	
	@RequiresPermissions("business:pay:swPay:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwPay swPay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwPay> page = swPayService.findPage(new Page<SwPay>(request, response), swPay); 
		model.addAttribute("page", page);
		return "modules/business/pay/swPayList";
	}
	
	@RequiresPermissions("business:pay:swPay:view")
	@RequestMapping(value = {"listPay"})
	public String listPay(SwPay swPay, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("completionTime", swPay.getCompletionTime()) ;
		dataMap.put("expectedCompletionTime", swPay.getExpectedCompletionTime()) ;
		Page<Map> page = swPayService.findMapPage(new Page<Map>(request, response), dataMap); 
		model.addAttribute("page", page);
		return "modules/business/pay/swPayYsList";
	}
	
	@RequiresPermissions("business:pay:swPay:view")
	@RequestMapping(value = {"listPayNew"})
	public String listPayNew(SwPay swPay, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("completionTime", swPay.getCompletionTime()) ;
		dataMap.put("expectedCompletionTime", swPay.getExpectedCompletionTime()) ;
		Page<Map> page = swPayService.findMapPage2(new Page<Map>(request, response), dataMap); 
	    System.out.println(page.getPageNo()+"LLLLLLLLLLLLLLLLLLLl");
		model.addAttribute("page", page);
		return "modules/business/pay/swPayYsListNew";
	}

	@RequiresPermissions("business:pay:swPay:view")
	@RequestMapping(value = "form")
	public String form(SwPay swPay, Model model) {
		if(swPay.getField1()==null || "".equals(swPay.getField1())){
			swPay.setField1(UserUtils.getUser().getName());
			swPay.setPhone(UserUtils.getUser().getPhone());
		}
		//查询未付款信息
		SwPay tp = new SwPay();
		tp.setOrderId(swPay.getOrderId());
		tp.setState("0");
		List<SwPay> payListwf = swPayService.findList(tp);
		
		double contrateTreat = 0 ;
		
		
		//查询未付款信息
		if(swPay.getCollectId()!=null && !"".equals(swPay.getCollectId())){
			SwPay tpp = new SwPay();
			tpp.setOrderId(swPay.getOrderId());
			tpp.setCollectId(swPay.getCollectId());
			List<SwPay> payListyx = swPayService.findList(tpp);
			for (SwPay swPay2 : payListwf) {
				for (SwPay swPay3 : payListyx) {
					if(swPay3.getId().equals(swPay2.getId())){
						contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
						swPay2.setField4("1");
					}
				}
			}
			
			swPay.setContrateTreat(contrateTreat+"");
		}else{
			for (SwPay swPay2 : payListwf) {
				if(swPay.getId().equals(swPay2.getId())){
					swPay.setField5(swPay.getId());
					swPay2.setField4("1");
				}
			}
			
		}
		

	
			
		
		
		
			
			
		//查询已付款选择
		tp.setState("1");
		List<SwPay> payListyf = swPayService.findList(tp);
		
		contrateTreat = 0 ;
		for (SwPay swPay2 : payListyf) {
			contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
		}
		
		swPay.setContratePaid(contrateTreat+"");
		
		/*
		SwAgreementPay temp = null;
		SwAgreement  swAgreement = new SwAgreement();
		if(swPay.getId()==null || "".equals(swPay.getId())){
			SwOrder swOrder = swOrderService.get(swPay.getOrderId());
			
			swPay.setContratePrice(swOrder.getAmountYs()+"");
			swPay.setContratePaid("0");
			swPay.setContrateTreat(swOrder.getAmountYs()+"");
			
			
			//如果填写的是合同
			if(swPay.getContractId()!=null && !"".equals(swPay.getContractId())){
				swAgreement = swAgreementService.get(swPay.getContractId()) ;
				List<SwAgreementPay> swAgreementPayList = swAgreement.getSwAgreementPayList();
				double price =  Double.valueOf(swAgreement.getAmount()) ;
				double paid = 0;
				double treat =  0;
				NumberFormat nf=NumberFormat.getPercentInstance();
			   for(int i =0 ;i<swAgreementPayList.size();i++){
				   SwAgreementPay swAgreementPay = swAgreementPayList.get(i);
					if(swAgreementPay.getState()==null || "".equals(swAgreementPay.getState())){
						SwPay sp = new SwPay();
						sp.setPayId(swAgreementPay.getId());
						SwPay pay = swPayService.getByPayId(sp);
						if(pay!=null && pay.getId()!=null && !"".equals(pay.getId())){
							return "redirect:"+Global.getAdminPath()+"/business/pay/swPay/form?id="+pay.getId();
						}
						temp = swAgreementPay ;
						try {
							System.out.println(temp.getPayRate());
							treat = nf.parse(temp.getPayRate()).doubleValue()*price ;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break ;
					}else{
						try {
							paid+= nf.parse(swAgreementPay.getPayRate()).doubleValue()*price ;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				swPay.setPayId(temp.getId());
				swPay.setContrateName(swAgreement.getAgreementSecond());
				swPay.setContractId(swAgreement.getId());
				swPay.setContratePrice(swAgreement.getAmount());
				swPay.setContractCode(swAgreement.getAgreementNo());
				swPay.setSupplierName(swAgreement.getSecondName());
				swPay.setSupplierTel(swAgreement.getSecondNamePhone());
				swPay.setSupplierBankName(swAgreement.getSecondName());
				swPay.setSupplierBankNumber(swAgreement.getSecondBankNo());
				swPay.setSupplierBank(swAgreement.getSecondBank());
				swPay.setContratePrice(price+"");
				swPay.setContratePaid(paid+"");
				swPay.setContrateTreat(treat+"");
				
				
			}else{
				
				if(payList!=null && payList.size()>0){
					swPay = payList.get(0);
				}
				
			}
			
			
			//如果填写的是申购单
			if(swPay.getPurchaseId()!=null && !"".equals(swPay.getPurchaseId())){
				
				SwPurchase swPurchase = swPurchaseService.get(swPay.getPurchaseId());
				swPay.setContrateName(swPurchase.getName());
				if(swOrder.getField3Id()!=null && !"".equals(swOrder.getField3Id())){
					SwNegotiate swNegotiate = swNegotiateService.get(swOrder.getField3Id()) ;
					swPay.setContratePrice(swNegotiate.getNegotiatePrice());
					swPay.setContratePaid("0");
					swPay.setContrateTreat(swNegotiate.getNegotiatePrice());
				}
			}
			
			
			
			
			
			
		}*/
		model.addAttribute("swPay", swPay);
		model.addAttribute("payList", payListyf);
		model.addAttribute("payListwf", payListwf);
		return "modules/business/pay/swPayForm";
	}
	
	@RequiresPermissions("business:pay:swPay:view")
	@RequestMapping(value = "edit")
	public String edit(SwPay swPay, Model model) {
		if(swPay.getField1()==null || "".equals(swPay.getField1())){
			swPay.setField1(UserUtils.getUser().getName());
			swPay.setPhone(UserUtils.getUser().getPhone());
		}
		//查询未付款信息
		SwPay tp = new SwPay();
		tp.setOrderId(swPay.getOrderId());
		tp.setState("0");
		List<SwPay> payListwf = swPayService.findList(tp);
		
			
		//查询已付款选择
		tp.setState("1");
		List<SwPay> payListyf = swPayService.findList(tp);
		
		
		model.addAttribute("swPay", swPay);
		model.addAttribute("payList", payListyf);
		model.addAttribute("payListwf", payListwf);
		return "modules/business/pay/swPaySee";
	}
	
	
	@RequestMapping(value = "view")
	public String view(SwPay swPay, Model model) {
		if(swPay.getField1()==null || "".equals(swPay.getField1())){
			swPay.setField1(UserUtils.getUser().getName());
			swPay.setPhone(UserUtils.getUser().getPhone());
		}
		//查询未付款信息
		SwPay tp = new SwPay();
		tp.setOrderId(swPay.getOrderId());
		tp.setState("0");
		List<SwPay> payListwf = swPayService.findList(tp);
		
		double contrateTreat = 0 ;
		
		
		//查询未付款信息
		if(swPay.getCollectId()!=null && !"".equals(swPay.getCollectId())){
			SwPay tpp = new SwPay();
			tpp.setOrderId(swPay.getOrderId());
			tpp.setCollectId(swPay.getCollectId());
			List<SwPay> payListyx = swPayService.findList(tpp);
			for (SwPay swPay2 : payListwf) {
				for (SwPay swPay3 : payListyx) {
					if(swPay3.getId().equals(swPay2.getId())){
						contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
						swPay2.setField4("1");
					}
				}
			}
			
			swPay.setContrateTreat(contrateTreat+"");
		}else{
			for (SwPay swPay2 : payListwf) {
				if(swPay.getId().equals(swPay2.getId())){
					swPay.setField5(swPay.getId());
					swPay2.setField4("1");
				}
			}
			
		}
		
		//查询已付款选择
				tp.setState("1");
				List<SwPay> payListyf = swPayService.findList(tp);
				
				contrateTreat = 0 ;
				for (SwPay swPay2 : payListyf) {
					contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
				}
				
				swPay.setContratePaid(contrateTreat+"");
				swPay.setSupplierAccount(swPay.getContrateName()+swPay.getSupplierAccount());
		
		model.addAttribute("swPay", swPay);
		return "modules/business/pay/swPayView";
	}
	
	
	@RequestMapping(value = "view2")
	public String view2(SwPay swPay, Model model) {
		if(swPay.getField1()==null || "".equals(swPay.getField1())){
			swPay.setField1(UserUtils.getUser().getName());
			swPay.setPhone(UserUtils.getUser().getPhone());
		}
		//查询未付款信息
		SwPay tp = new SwPay();
		tp.setOrderId(swPay.getOrderId());
		tp.setState("0");
		List<SwPay> payListwf = swPayService.findList(tp);
		
		double contrateTreat = 0 ;
		
		
		//查询未付款信息
		if(swPay.getCollectId()!=null && !"".equals(swPay.getCollectId())){
			SwPay tpp = new SwPay();
			tpp.setOrderId(swPay.getOrderId());
			tpp.setCollectId(swPay.getCollectId());
			List<SwPay> payListyx = swPayService.findList(tpp);
			for (SwPay swPay2 : payListwf) {
				for (SwPay swPay3 : payListyx) {
					if(swPay3.getId().equals(swPay2.getId())){
						contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
						swPay2.setField4("1");
					}
				}
			}
			
			swPay.setContrateTreat(contrateTreat+"");
		}else{
			for (SwPay swPay2 : payListwf) {
				if(swPay.getId().equals(swPay2.getId())){
					swPay.setField5(swPay.getId());
					swPay2.setField4("1");
				}
			}
			
		}
		
		//查询已付款选择
				tp.setState("1");
				List<SwPay> payListyf = swPayService.findList(tp);
				
				contrateTreat = 0 ;
				for (SwPay swPay2 : payListyf) {
					contrateTreat = contrateTreat+ Double.parseDouble(swPay2.getContrateTreat());
				}
				
		swPay.setContratePaid(contrateTreat+"");
		swPay.setField1(UserUtils.getUser().getName());
		swPay.setField2(UserUtils.getUser().getOffice().getName());
		swPay.setField3(UserUtils.getUser().getPhone());
	
		swPay.setContrateTreat(ChineseNumber.getChineseNumber(swPay.getContrateTreat()));
		swPay.setSupplierAccount(swPay.getContrateName()+swPay.getSupplierAccount());
		model.addAttribute("swPay", swPay);
		return "modules/business/pay/swPayView2";
	}
	
	
    //原本保存
	/*
	@RequiresPermissions("business:pay:swPay:edit")
	@RequestMapping(value = "save")
	public String save(SwPay swPay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swPay)){
			return form(swPay, model);
		}
		swPayService.save(swPay);
		
		
		
		
		addMessage(redirectAttributes, "保存请款单成功");

        if("1".equals(swPay.getState())){
        	SwOrder swOrder = swOrderService.get(swPay.getOrderId());
        	if(swPay.getContractId()==null || "".equals(swPay.getContractId())){
    			swOrder.setField6State("3");
    			swOrder.setField6Id(swPay.getId());
    			swOrder.setField6Text("<font color='green'>借款完成</font><i style='color: green;' class='icon-ok-sign'></i>");
    			swOrder.setField7State("2");
    			swOrderService.save(swOrder);
    			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
        	}else{
        		SwAgreementPay swAgreementPay = swAgreementService.getPay(swPay.getPayId());
        		swAgreementPay.setState("1");
        		swAgreementPay.setPayThis(swPay.getContrateTreat());
        		swAgreementService.savePay(swAgreementPay);
        		List list = swAgreementService.getSwAgreementPay(swAgreementPay);

        		if(list==null || list.size()==0){
       
        			swOrder.setField6State("3");
        			swOrder.setField6Text("<font color='green'>借款完成</font><i style='color: green;' class='icon-ok-sign'></i>");
        			swOrder.setField6Id(swPay.getId());
        			
        		}
        		swOrder.setField7State("2");
        		swOrderService.save(swOrder);
    			
    			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
        	}
        	
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/pay/swPay/form?id="+swPay.getId();
		}
	}
	
	*/
	//修改后保存
	@RequiresPermissions("business:pay:swPay:edit")
	@RequestMapping(value = "save")
	public String save(SwPay swPay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swPay)){
			return form(swPay, model);
		}
		SwOrder swOrder = null ;
		if(swPay.getOrderId()!=null && !"".equals(swPay.getOrderId())){
			swOrder = swOrderService.get(swPay.getOrderId());
		}
		System.out.println(swPay);
		//未付款的清空付款信息
		SwPay tp = new SwPay();
		tp.setOrderId(swPay.getOrderId());
		tp.setState("0");
		List<SwPay> payListwf = swPayService.findList(tp);
		for (SwPay swPay2 : payListwf) {
			swPay2.setCollectId(null);
			swPay2.setSupplierAccount(null);
			swPayService.save(swPay2);
		}

		String[] collectIds =  swPay.getField5().split(",");
		for (String collectId : collectIds) {
			SwPay swPayTemp = swPayService.get(collectId);
			swPayTemp.setPayType(swPay.getPayType());
			swPayTemp.setContratePaid(swPay.getContratePaid());
			swPayTemp.setContrateTreat(swPayTemp.getContrateTreat());
			swPayTemp.setSupplierAccount(swPay.getSupplierAccount());
			swPayTemp.setCollectId(swPay.getId());
			swPayTemp.setField1(swPay.getField1());
			swPayTemp.setPhone(swPay.getPhone());
			swPayTemp.setField5(swPay.getField5());
			swPayTemp.setAppendix(swPay.getAppendix());
			 if("1".equals(swPay.getState())){
			    swPayTemp.setState("1");
			    swPayTemp.setCompletionTime(new Date());
			 }
        	swPayService.save(swPayTemp);
		}
		
		
		
		addMessage(redirectAttributes, "保存请款单成功");

        if("1".equals(swPay.getState())){
        	if(swOrder!=null && swOrder.getState().equals("6")){
        		swOrder.setState("7");
        		swOrderService.save(swOrder);
        	}
        	
    		return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
   
        	
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/pay/swPay/form?id="+swPay.getId();
		}
	}
	
	@RequiresPermissions("business:pay:swPay:edit")
	@RequestMapping(value = "delete")
	public String delete(SwPay swPay, RedirectAttributes redirectAttributes) {
		swPayService.delete(swPay);
		addMessage(redirectAttributes, "删除请款单成功");
		return "redirect:"+Global.getAdminPath()+"/business/pay/swPay/?repage";
	}
	
	@RequestMapping(value = "updateProjectClass")
    @ResponseBody
	public void updateProjectClass(String orderId,String type,String project_class, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		System.out.println(orderId+"--"+type+"--"+project_class);
		if("1".equals(type)){
			SwOrder swOrder = swOrderService.get(orderId);
			if(swOrder!=null){
				swOrder.setProjectClass(project_class);
				swOrderService.save(swOrder);
			}
		}
        if("2".equals(type)){
			SwPay swPay = swPayService.get(orderId);
			if(swPay!=null){
				swPay.setProjectClass(project_class);
				swPayService.save(swPay);
			}
		}
		addMessage(redirectAttributes, "操作成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	

	
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SwPay swPay, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
            String fileName = "预算信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            DataMap dataMap = new DataMap() ;
    		dataMap.put("completionTime", swPay.getCompletionTime()) ;
    		dataMap.put("expectedCompletionTime", swPay.getExpectedCompletionTime()) ;
    		List<SwPayYs> page = swPayService.findListYs( dataMap); 
    		
    		new ExportExcel("预算信息", SwPayYs.class).setDataList(page).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出预算失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/business/pay/swPayYsListNew?repage";
    }

}