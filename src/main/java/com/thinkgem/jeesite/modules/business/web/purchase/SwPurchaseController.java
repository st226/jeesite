/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.purchase;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiateSupplier;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.purchase.SwPurchaseService;
import com.thinkgem.jeesite.modules.business.service.special.SwSpecialService;
import com.thinkgem.jeesite.modules.business.service.supplier.SwSupplierService;

/**
 * 仪器申购单Controller
 * @author suntao
 * @version 2020-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/business/purchase/swPurchase")
public class SwPurchaseController extends BaseController {

	@Autowired
	private SwPurchaseService swPurchaseService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwProductService swProductService ;
	
	@Autowired
	private SwNegotiateService swNegotiateService;
	
	@Autowired
	private SwSupplierService swSupplierService;
	
	@Autowired
	private SwSpecialService swSpecialService;
	
	@Autowired
	private SwPayService swPayService;
	
	@ModelAttribute
	public SwPurchase get(@RequestParam(required=false) String id) {
		SwPurchase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swPurchaseService.get(id);
		}
		if (entity == null){
			entity = new SwPurchase();
		}
		return entity;
	}
	
	@RequiresPermissions("business:purchase:swPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwPurchase swPurchase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwPurchase> page = swPurchaseService.findPage(new Page<SwPurchase>(request, response), swPurchase); 
		model.addAttribute("page", page);
		return "modules/business/purchase/swPurchaseList";
	}

	@RequiresPermissions("business:purchase:swPurchase:view")
	@RequestMapping(value = "form")
	public String form(SwPurchase swPurchase, Model model) {
		SwProduct swProduct = new SwProduct();
		
		if(swPurchase.getId()==null || "".equals(swPurchase.getId())){
			swPurchase.setState("0");
			
			List<SwAgreementProduct> list = new ArrayList<SwAgreementProduct>();
			SwAgreementProduct swAgreementProduct = new SwAgreementProduct();
			
			swPurchase.setCreateDate(new Date());
			if(swPurchase.getOrderId() !=null && !"".equals(swPurchase.getOrderId())){
				SwOrder swOrder = swOrderService.get(swPurchase.getOrderId()) ;
				swProduct.setOrderId(swOrder.getId());
				String reason = "" ;
				List<SwProduct> swProductList = swProductService.findList(swProduct) ;
				
				if(swProductList!=null && swProductList.size()>0){
					swProduct = swProductList.get(0) ;
					swPurchase.setMade(swProduct.getProductMade());
					swPurchase.setSpecifications(swProduct.getProductType());
					swPurchase.setOffice(swProduct.getOffice());
					swPurchase.setOfficeName(swProduct.getOfficeName());
					swPurchase.setUnitPrice(swProduct.getUnitPrice()+"");
					reason = reason + swProduct.getReason()+"。";
				
				}
				int i = 1 ;
				for (SwProduct swProduct2 : swProductList) {
					swAgreementProduct.setProductName(swProduct2.getProductName());
					swAgreementProduct.setProductAmount(swProduct2.getProductAmount()+"");
					swAgreementProduct.setProductType(swProduct2.getProductType());
					swAgreementProduct.setProductMade(swProduct2.getProductMade());
					swAgreementProduct.setProductNo(i+"");
					i++;
					list.add(swAgreementProduct) ;
					swAgreementProduct = new SwAgreementProduct();
					
				}
				swPurchase.setSwAgreementProductList(list);
				swPurchase.setName(swOrder.getName());
				swPurchase.setAmount(swOrder.getField4());
				swPurchase.setReason(reason);
				
				
				
			}
		}
		
		
		model.addAttribute("swPurchase", swPurchase);
		return "modules/business/purchase/swPurchaseForm";
	}
	
	@RequestMapping(value = "view")
	public String view(SwPurchase swPurchase, Model model) {
		model.addAttribute("swPurchase", swPurchase);
		return "modules/business/purchase/swPurchaseView2";
	}
	
	@RequestMapping(value = "look")
	public String look(SwPurchase swPurchase, Model model) {
		model.addAttribute("swPurchase", swPurchase);
		return "modules/business/purchase/swPurchaseLook";
	}

	@RequiresPermissions("business:purchase:swPurchase:edit")
	@RequestMapping(value = "save")
	public String save(SwPurchase swPurchase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swPurchase)){
			return form(swPurchase, model);
		}
		
		//生成申请单编号
		if(swPurchase.getCode()==null ||"".equals(swPurchase.getCode())){
			String code = swOrderService.getGyCode("", 3, false, false, "", "sw_purchase", "field2");
			swPurchase.setField2(code);
			String codes[] = code.split("-");
			swPurchase.setCode("JGSG"+codes[0]+"02"+codes[1]);
		}
		
		
		
		swPurchaseService.save(swPurchase);
		
		SwOrder swOrder = swOrderService.get(swPurchase.getOrderId());
		swOrder.setField5Id(swPurchase.getId());
		
		if("1".equals(swPurchase.getState())){
			swOrder.setField5State("3");
			swOrder.setField5DateEnd(new Date());
			swOrder.setField5Text("<font color='green'>申购单</font><i style='color: green;' class='icon-ok-sign'></i>");
		    swOrder.setField6State("2");
		    swOrder.setField7State("2");
		    
		    
		    //插入付款信息
		    //有谈判的前提下付款信息
		    //无谈判，航天电子超市，直接取订单金额
		    if(swOrder.getField3Id()!=null && !"".equals(swOrder.getField3Id())){
		    	//有谈判
		    	SwNegotiate swNegotiate = swNegotiateService.get(swOrder.getField3Id());
		    	SwPay swPay = new SwPay();

		    		
		    	Calendar cal = Calendar.getInstance();
		    	cal.add(Calendar.DATE, 15);
		    	Date date = cal.getTime();
		    	swPay.setProjectDate(date);
		  
		    	swPay.setPayIndex("3");
		    	swPay.setOrderId(swPurchase.getOrderId());
		    	swPay.setPurchaseId(swPurchase.getId());
		    	swPay.setState("0");
		    	swPay.setContratePrice(swNegotiate.getNegotiatePrice());
		    	swPay.setContratePaid("0");
		    	swPay.setContrateTreat(swNegotiate.getNegotiatePrice());
		    	swPay.setContrateName(swPurchase.getName());
		    	if(swNegotiate.getSwNegotiateSupplierList()!=null && swNegotiate.getSwNegotiateSupplierList().size()>0){
		    		SwNegotiateSupplier swNegotiateSupplier = swNegotiate.getSwNegotiateSupplierList().get(0);
		    		swPay.setSupplierId(swNegotiateSupplier.getSupplierId());
			    	swPay.setSupplierName(swNegotiateSupplier.getSupplierName());
			    	SwSupplier swSupplier  = swSupplierService.get(swNegotiateSupplier.getSupplierId()) ;
			    	swPay.setSupplierTel(swSupplier.getSupplierTel());
			    	swPay.setSupplierBank(swSupplier.getBankName());
			    	swPay.setSupplierBankName(swSupplier.getName());
			    	swPay.setSupplierBankNumber(swSupplier.getBankNumber());
		    	}
		    	swPayService.save(swPay);
 	
		    }else{
		    	//航天电子超市
		    	SwSpecial swSpecial = swSpecialService.get(swOrder.getField1Id());
		    	SwPay swPay = new SwPay();
		    	swPay.setPayIndex("3");
		    	swPay.setOrderId(swPurchase.getOrderId());
		    	swPay.setPurchaseId(swPurchase.getId());
		    	swPay.setState("0");
		    	swPay.setContratePrice(swSpecial.getBudget());
		    	swPay.setContratePaid("0");
		    	swPay.setContrateTreat(swSpecial.getBudget());
		    	swPay.setContrateName(swPurchase.getName());
		    	SwSupplier swSupplier  = swSupplierService.get("htdzcs") ;
		    	swPay.setSupplierId(swSupplier.getId());
		    	swPay.setSupplierName(swSupplier.getName());
		    	swPay.setSupplierTel(swSupplier.getSupplierTel());
		    	swPay.setSupplierBank(swSupplier.getBankName());
		    	swPay.setSupplierBankName(swSupplier.getName());
		    	swPay.setSupplierBankNumber(swSupplier.getBankNumber());
		    	swPayService.save(swPay);
		    }
		    
			
		}
		
		
		swOrderService.save(swOrder);
		
		addMessage(redirectAttributes, "保存仪器申购单成功");
          if("1".equals(swPurchase.getState())){
        	  return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?identification="+swOrder.getIdentification();
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/purchase/swPurchase/form?id="+swPurchase.getId();
		}
		
		
	}
	
	@RequiresPermissions("business:purchase:swPurchase:edit")
	@RequestMapping(value = "delete")
	public String delete(SwPurchase swPurchase, RedirectAttributes redirectAttributes) {
		swPurchaseService.delete(swPurchase);
		addMessage(redirectAttributes, "删除仪器申购单成功");
		return "redirect:"+Global.getAdminPath()+"/business/purchase/swPurchase/?repage";
	}

}