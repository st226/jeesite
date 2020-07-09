/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.agreement;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementPay;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreementProduct;
import com.thinkgem.jeesite.modules.business.entity.contract.SwContract;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiateSupplier;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.service.agreement.SwAgreementService;
import com.thinkgem.jeesite.modules.business.service.contract.SwContractService;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.supplier.SwSupplierService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 合同录入Controller
 * @author suntao
 * @version 2020-03-24
 */
@Controller
@RequestMapping(value = "${adminPath}/business/agreement/swAgreement")
public class SwAgreementController extends BaseController {

	@Autowired
	private SwAgreementService swAgreementService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwContractService swContractService;
	
	@Autowired
	private SwSupplierService swSupplierService;
	
	@Autowired
	private SwProductService swProductService;
	
	@Autowired
	private SwNegotiateService swNegotiateService;
	
	@Autowired
	private SwPayService swPayService;
	
	
	@ModelAttribute
	public SwAgreement get(@RequestParam(required=false) String id) {
		SwAgreement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swAgreementService.get(id);
		}
		if (entity == null){
			entity = new SwAgreement();
		}
		return entity;
	}
	
	@RequiresPermissions("business:agreement:swAgreement:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwAgreement swAgreement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwAgreement> page = swAgreementService.findPage(new Page<SwAgreement>(request, response), swAgreement); 
		model.addAttribute("page", page);
		return "modules/business/agreement/swAgreementList";
	}
	
	public static String convert(int money) {
		      char[] data = new char[] { '零', '壹', '贰', '叁', '肆','伍', '陆', '柒', '捌', '玖' };
			  char[] units = new char[] { '元', '拾', '佰', '仟', '万','拾', '佰', '仟', '亿' };
              StringBuffer sbf = new StringBuffer();
		      int unit = 0;
	          while (money != 0) {
		           sbf.insert(0, units[unit++]);
		           int number = money % 10;
		           sbf.insert(0, data[number]);
		           money /= 10;
	             }
		     return sbf.toString();
		}


	@RequiresPermissions("business:agreement:swAgreement:view")
	@RequestMapping(value = "form")
	public String form(SwAgreement swAgreement, Model model) {
		SwAgreementProduct swAgreementProduct = new SwAgreementProduct() ;
		List<SwAgreementProduct> list = new ArrayList<SwAgreementProduct>();
		if(swAgreement.getId()==null || "".equals(swAgreement.getId())){
			swAgreement.setState("0");
			swAgreement.setHandledBy(UserUtils.getUser().getName());
			swAgreement.setField1(UserUtils.getUser().getMobile());
			if(swAgreement.getOrderId() !=null && !"".equals(swAgreement.getOrderId())){
				SwOrder swOrder = swOrderService.get(swAgreement.getOrderId()) ;
				swAgreement.setAgreementName(swOrder.getName());
				
				//如果合同申请ID不为空，补齐首页可以填写的值
				if(swOrder.getField3Id()!=null && !"".equals(swOrder.getField3Id())){
					SwNegotiate  swNegotiate = swNegotiateService.get(swOrder.getField3Id());
					List<SwNegotiateSupplier>  negotiateSupplierList = swNegotiate.getSwNegotiateSupplierList();
					SwNegotiateSupplier negotiateSupplier = new SwNegotiateSupplier() ;
		
					if(negotiateSupplierList!=null && negotiateSupplierList.size()>0){
						negotiateSupplier = negotiateSupplierList.get(0);
					}
			//		swAgreement.setAgreementNo(swContract.getContractCode());
					swAgreement.setSecondName(negotiateSupplier.getSupplierName());
					swAgreement.setAmount(swNegotiate.getNegotiatePrice());
	
					swAgreement.setAmountup(ChineseNumber.getChineseNumber(swNegotiate.getNegotiatePrice()));
					
					//供应商ID查询补齐
					if(negotiateSupplier.getSupplierId()!=null && !"".equals(negotiateSupplier.getSupplierId())){
						
						SwSupplier swSupplier = swSupplierService.get(negotiateSupplier.getSupplierId());
						swAgreement.setField5(negotiateSupplier.getSupplierId());
	                	swAgreement.setAgreementSecond(swSupplier.getName());
	                	swAgreement.setSecondName(swSupplier.getName());
	                	swAgreement.setSecondPlace(swSupplier.getLocal());
	                	swAgreement.setSecondNameLiaison(swSupplier.getSupplierUser());
	                	swAgreement.setSecondNamePhone(swSupplier.getUserPhone());
	                	swAgreement.setSecondBank(swSupplier.getBankName());
	                	swAgreement.setSecondBankNo(swSupplier.getBankNumber());
	                	swAgreement.setSecondDuty(swSupplier.getDutyno());
	                	swAgreement.setSecondZip(swSupplier.getSupplierZip());
	                	swAgreement.setSecondTel(swSupplier.getSupplierTel());
	                	swAgreement.setSecondFax(swSupplier.getSupplierFax());
	                	swAgreement.setSecondLegal(swSupplier.getLegal());
	                	swAgreement.setSecondAgent(swSupplier.getAgent());
	                	
	                	//提前录入
	                	swAgreement.setSigningPlace("北京市");
	                	swAgreement.setSigningTime(new Date());
	                	swAgreement.setSolveDay("5");
	                	swAgreement.setWarranty("1");
	                	swAgreement.setDeliveryTime("60");
	                	swAgreement.setDeliveryMethod("乙方");
	                	swAgreement.setObjectionDay("30");
	                	swAgreement.setObjectionDayHf("5");
	                	swAgreement.setInvoiceDay("5");
	                	swAgreement.setFalsify("10%");
	                	
	                	
						
					}
					
				}
				
				
				SwProduct swProduct = new SwProduct();
				swProduct.setOrderId(swOrder.getId());
				List<SwProduct> swProductList = swProductService.findList(swProduct) ;
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
				swAgreement.setSwAgreementProductList(list);
				
			}
		}
		
		
		model.addAttribute("swAgreement", swAgreement);
		return "modules/business/agreement/swAgreementForm";
	}
	
	@RequestMapping(value = "view")
	public String view(SwAgreement swAgreement, Model model) {
		List<SwAgreementPay> agreementPayList = swAgreement.getSwAgreementPayList();
		String pay = "" ;
		for (SwAgreementPay swAgreementPay : agreementPayList) {
			String condition = DictUtils.getDictLabel(swAgreementPay.getPayCondition(), "pay_state", "") ;
			if("".equals(pay)){
				
				
				pay = condition+",甲方向乙方支付合同总额价款的  <u>"+swAgreementPay.getPayRate()+"</u>";
			}else{
				
				pay = pay+";"+condition+",甲方向乙方支付合同总额价款的  <u>"+swAgreementPay.getPayRate()+"</u>";
			}
			
		}
		pay=pay+"。";
		if(swAgreement.getField3()!=null && !"".equals(swAgreement.getField3())){
			swAgreement.setAgreementNo(swAgreement.getField3());
		}
		swAgreement.setField9(pay);
		
		double price = Double.valueOf(swAgreement.getAmount()) ;
		DecimalFormat df = new DecimalFormat("#.00");
		swAgreement.setAmount(df.format(price)+"");
        SwSupplier swSupplier = swSupplierService.get("jggs");
        List<SwAgreementProduct> productList = swAgreement.getSwAgreementProductList();
        for (SwAgreementProduct swAgreementProduct : productList) {
        	price = Double.valueOf(swAgreementProduct.getTotalPrice()) ;
        	swAgreementProduct.setTotalPrice(df.format(price)+"");
		}
        swAgreement.setSwAgreementProductList(productList);
        model.addAttribute("swSupplier",swSupplier);
		model.addAttribute("swAgreement", swAgreement);
		return "modules/business/agreement/swAgreementView";
	}
	
	@RequestMapping(value = "look")
	public String look(SwAgreement swAgreement, Model model) {
		
		model.addAttribute("swAgreement", swAgreement);
		return "modules/business/agreement/swAgreementLook";
	}
	
	
	@RequestMapping(value = "viewContract")
	public String viewContract(SwAgreement swAgreement, Model model) {
		if(swAgreement.getAmount()!=null && !"".equals(swAgreement.getAmount())){
			double price = Double.valueOf(swAgreement.getAmount()) ;
			DecimalFormat df = new DecimalFormat("#.00");
			swAgreement.setAmount(df.format(price)+"");
		}
		swAgreement.setHandledDate(new Date());
		model.addAttribute("swAgreement", swAgreement);
		return "modules/business/contract/swContractView";
	}
	

	@RequiresPermissions("business:agreement:swAgreement:edit")
	@RequestMapping(value = "save")
	public String save(SwAgreement swAgreement, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		if (!beanValidator(model, swAgreement)){
			return form(swAgreement, model);
		}
		
		
		//生成合同编号
		if(swAgreement.getAgreementNo()==null ||"".equals(swAgreement.getAgreementNo())){
			String code = swOrderService.getGyCode("", 3, false, false, "", "sw_agreement", "field2");
			swAgreement.setField2(code);
			String codes[] = code.split("-");
			if(swAgreement.getAgreementType().equals("JG13")){
				swAgreement.setAgreementNo(swAgreement.getAgreementType()+codes[0]+"PC"+codes[1]);
			}else{
				swAgreement.setAgreementNo(swAgreement.getAgreementType()+codes[0]+"02"+codes[1]);
			}
				
			
		}
		
		swAgreementService.save(swAgreement);
		
		SwOrder swOrder = swOrderService.get(swAgreement.getOrderId());
		swOrder.setField5Id(swAgreement.getId());
		
		if("1".equals(swAgreement.getState())){
			swOrder.setField5DateEnd(new Date());
			swOrder.setField5State("3");
			swOrder.setField5Text("<font color='green'>合同签订</font><i style='color: green;' class='icon-ok-sign'></i>");
		    swOrder.setField6State("2");
		    swOrder.setField7State("2");
		    
		    List<SwAgreementPay> list = swAgreement.getSwAgreementPayList();
		    double price = Double.valueOf(swAgreement.getAmount()) ;

			double treat =  0;
			NumberFormat nf=NumberFormat.getPercentInstance();
		    for (SwAgreementPay swAgreementPay : list) {
		    	
		    	SwPay swPay = new SwPay();
		    	swPay.setIdentification(swOrder.getIdentification());
		    	swPay.setPayIndex(swAgreementPay.getPayIndex());
		    	swPay.setOrderId(swAgreement.getOrderId());
		    	swPay.setContractId(swAgreement.getId());
		    	swPay.setContractCode(swAgreement.getAgreementNo());
		    	swPay.setState("0");
		    	swPay.setContratePrice(price+"" );
		    	swPay.setContratePaid(treat+"");
		    	treat = treat + (nf.parse(swAgreementPay.getPayRate()).doubleValue())*price ;
		    	swPay.setContrateTreat((nf.parse(swAgreementPay.getPayRate()).doubleValue()*price) +"");
		    	swPay.setContrateName(swAgreement.getAgreementName());
		    	
		    	swPay.setPayCondition(swAgreementPay.getPayCondition());
		    	if("1".equals(swAgreementPay.getPayCondition())){
		    		
		    		Calendar cal = Calendar.getInstance();
		    		cal.add(Calendar.DATE, 15);
		    		Date date = cal.getTime();
		    		swPay.setProjectDate(date);
		    	}
               if("2".equals(swAgreementPay.getPayCondition()) && swAgreement.getDeliveryTime()!=null){
            	   int a = Integer.parseInt(swAgreement.getDeliveryTime());
		    		Calendar cal = Calendar.getInstance();
		    		cal.add(Calendar.DATE, a );
		    		Date date = cal.getTime();
		    		swPay.setProjectDate(date);
		    	}
               
               if("3".equals(swAgreementPay.getPayCondition())  && swAgreement.getDeliveryTime()!=null){
            	   int a = Integer.parseInt(swAgreement.getDeliveryTime());
		    		Calendar cal = Calendar.getInstance();
		    		cal.add(Calendar.DATE, a+365 );
		    		Date date = cal.getTime();
		    		swPay.setProjectDate(date);
		    	}
               
               if( "4".equals(swAgreementPay.getPayCondition()) && swAgreement.getDeliveryTime()!=null){
            	   int a = Integer.parseInt(swAgreement.getDeliveryTime());
		    		Calendar cal = Calendar.getInstance();
		    		cal.add(Calendar.DATE, a+60 );
		    		Date date = cal.getTime();
		    		swPay.setProjectDate(date);
		    	}

		    	swPay.setSupplierId(swAgreement.getField5());
		    	swPay.setSupplierName(swAgreement.getSecondName());
		    	swPay.setSupplierTel(swAgreement.getSecondTel());
		    	swPay.setSupplierBank(swAgreement.getSecondBank());
		    	swPay.setSupplierBankName(swAgreement.getSecondName());
		    	swPay.setSupplierBankNumber(swAgreement.getSecondBankNo());
		    	swPayService.save(swPay);
				
			}
		    swOrder.setState("6");
		    if(swAgreement.getDeliveryTime()!=null && !"".equals(swAgreement.getDeliveryTime())){
		    	int a = Integer.parseInt(swAgreement.getDeliveryTime());
			    Calendar cal = Calendar.getInstance();
	    		cal.add(Calendar.DATE, a);
	    		Date date = cal.getTime();
			    swOrder.setField7Date(date);
		    }
		    
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		addMessage(redirectAttributes, "保存合同录入成功");

		  if("1".equals(swAgreement.getState())){
				
				return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
			}else{
				return "redirect:"+Global.getAdminPath()+"/business/agreement/swAgreement/form?id="+swAgreement.getId();
			}
	}
	
	@RequiresPermissions("business:agreement:swAgreement:edit")
	@RequestMapping(value = "delete")
	public String delete(SwAgreement swAgreement, RedirectAttributes redirectAttributes) {
		swAgreementService.delete(swAgreement);
		addMessage(redirectAttributes, "删除合同录入成功");
		return "redirect:"+Global.getAdminPath()+"/business/agreement/swAgreement/?repage";
	}

}