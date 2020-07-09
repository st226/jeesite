/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.receive;

import java.text.SimpleDateFormat;
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
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.contract.SwContract;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceive;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.service.agreement.SwAgreementService;
import com.thinkgem.jeesite.modules.business.service.contract.SwContractService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.purchase.SwPurchaseService;
import com.thinkgem.jeesite.modules.business.service.receive.SwReceiveService;
import com.thinkgem.jeesite.modules.business.service.supplier.SwSupplierService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentbus.SbEquipmentBusService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentfunction.SbFunctionTypeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 仪器设备开箱验收Controller
 * @author suntao
 * @version 2020-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/business/receive/swReceive")
public class SwReceiveController extends BaseController {

	@Autowired
	private SwReceiveService swReceiveService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	
	@Autowired
	private SbFunctionTypeService sbFunctionTypeService;
	
	@Autowired
	private SwProductService swProductService;
	
	@Autowired
	private SbEquipmentService sbEquipmentService;
	
	@Autowired
	private SbEquipmentBusService sbEquipmentBusService ;
	
	@Autowired
	private SwSupplierService swSupplierService ;
	
	@Autowired
	private SwAgreementService swAgreementService;
	
	@Autowired
	private SwPurchaseService swPurchaseService ;
	
	
	@Autowired
	private  SwPayService swPayService ;
	
	@ModelAttribute
	public SwReceive get(@RequestParam(required=false) String id) {
		SwReceive entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swReceiveService.get(id);
		}
		if (entity == null){
			entity = new SwReceive();
		}
		return entity;
	}
	
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwReceive swReceive, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwReceive> page = swReceiveService.findPage(new Page<SwReceive>(request, response), swReceive); 
		model.addAttribute("page", page);
		return "modules/business/receive/swReceiveList";
	}
	
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = {"listEquipment"})
	public String listEquipment(SwReceive swReceive, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(swReceive.getOrderId()!=null || !"".equals(swReceive.getOrderId())){
			SwOrder swOrder = swOrderService.get(swReceive.getOrderId()) ;
			swReceive = swReceiveService.get(swOrder.getField7Id());
		}
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		swReceiveEquipment.setReceiveId(swReceive);
		Page<SwReceiveEquipment> page = swReceiveService.findEquipmentPage(new Page<SwReceiveEquipment>(request, response), swReceiveEquipment); 
		model.addAttribute("orderId", swReceive.getOrderId());
		model.addAttribute("page", page);
		return "modules/business/receive/swReceiveEquipmentList";
	}
	
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = {"listEquipmentYs"})
	public String listEquipmentYs(SwReceive swReceive, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(swReceive.getOrderId()!=null || !"".equals(swReceive.getOrderId())){
			SwOrder swOrder = swOrderService.get(swReceive.getOrderId()) ;
			swReceive = swReceiveService.get(swOrder.getField7Id());
		}
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		swReceiveEquipment.setReceiveId(swReceive);
		Page<SwReceiveEquipment> page = swReceiveService.findEquipmentPage(new Page<SwReceiveEquipment>(request, response), swReceiveEquipment); 
		model.addAttribute("orderId", swReceive.getOrderId());
		model.addAttribute("page", page);
		return "modules/business/receive/swReceiveEquipmentListYs";
	}

	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = "form")
	public String form(SwReceive swReceive, Model model) {
		
		if(swReceive.getId()==null || "".equals(swReceive.getId())){
			List<SwReceiveEquipment> list = new ArrayList<SwReceiveEquipment>();
			SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
			SwOrder swOrder = swOrderService.get(swReceive.getOrderId()) ;
			swReceive.setPlanNumber(swOrder.getCode());
			swReceive.setItems(swOrder.getName());
			swReceive.setSbName(swOrder.getField3());
			if(swOrder.getField5Id()!=null && !"".equals(swOrder.getField5Id()) && swOrder.getAmountYs()>20000 && !"2".equals(swOrder.getType())){
				SwAgreement swAgreement = swAgreementService.get(swOrder.getField5Id());
				swReceive.setContractCode(swAgreement.getAgreementNo());
				swReceive.setItems(swAgreement.getAgreementName());
				swReceive.setField8(swAgreement.getAgreementName());
				swReceive.setContractId(swAgreement.getId());
				swReceive.setMade(swAgreement.getAgreementSecond());
				swReceive.setContactInformation(UserUtils.getUser().getPhone());
				swReceive.setSupplier(swAgreement.getSecondName());
				swReceive.setSupplierUser(swAgreement.getSecondNameLiaison());
				swReceive.setSupplierPhone(swAgreement.getSecondNamePhone());
			}else{
				SwPay swPay = new SwPay() ;
				SwPay swpay = new SwPay() ;
				swPay.setOrderId(swReceive.getOrderId()); 
				List<SwPay> swpayList = swPayService.findList(swPay);
				if(swpayList!=null && swpayList.size()>0){
					swpay = swpayList.get(0);
				}
				//改过
				if(swpay!=null){
					swReceive.setMade(swpay.getSupplierName());
					swReceive.setSupplier(swpay.getSupplierName());
					swReceive.setContactInformation(swpay.getSupplierTel());
					swReceive.setSupplierPhone(swpay.getSupplierTel());
					if(swpay.getSupplierId()!=null && !"".equals(swpay.getSupplierId())){
						SwSupplier supplier = swSupplierService.get(swpay.getSupplierId());
						swReceive.setSupplierUser(supplier.getSupplierUser());
					}
				}
				SwPurchase swPurchase = swPurchaseService.get(swOrder.getField5Id());
				swReceive.setContractCode(swPurchase.getCode());
				swReceive.setItems(swPurchase.getName());
				swReceive.setContractId(swPurchase.getId());
				
				
				
				
			}
			
			
			
			
			
			
			SwProduct temp = new SwProduct();
			temp.setOrderId(swReceive.getOrderId());
			List<SwProduct> swProductList= swProductService.findList(temp);
			SwProduct swProduct = swProductList.get(0);
			swReceive.setOffice(swProduct.getOffice());
			swReceive.setOfficeName(swProduct.getOffice().getName());
			swReceive.setUser(swProduct.getUser());
			swReceive.setUserName(swProduct.getUser().getName());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (SwProduct swProducttemp : swProductList) {
				
				double count = swProducttemp.getProductAmount();
				for(int i=0 ;i<count ;i++){
					swReceiveEquipment.setEquipmentName(swProducttemp.getProductName());
					swReceiveEquipment.setEquipmentModel(swProducttemp.getProductType());
					swReceiveEquipment.setEquipmentNorms(swProducttemp.getProductType());
					swReceiveEquipment.setIsAgreement("Y");
					swReceiveEquipment.setIsAppearance("1");
					swReceiveEquipment.setIsModel("1");
					swReceiveEquipment.setIsNorms("1");
					swReceiveEquipment.setCometime(format.format(new Date()));
					swReceiveEquipment.setAmount(1);
					list.add(swReceiveEquipment);
				}
				swReceiveEquipment = new SwReceiveEquipment();

			}
			
			
			swReceive.setSwReceiveEquipmentList(list);
			swReceive.setIsAgreement("Y");
			swReceive.setIsAppearance("1");
			swReceive.setIsModel("Y");
			swReceive.setIsNorms("Y");
		}
		
		
		
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveForm";
	}
	
	@RequestMapping(value = "formEquipment")
	public String formEquipment(SwReceive swReceive, Model model) {
		
		
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		return "modules/business/receive/swReceiveEquipmentForm";
		
		
	}
	
	
	@RequestMapping(value = "rkReceive")
	public String rkReceive(String ids, Model model) {

		String id[] = ids.split(",");
		List<SwReceiveEquipment> list = new ArrayList<SwReceiveEquipment>();
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		for (String string : id) {
			swReceiveEquipment = swReceiveService.getEquipment(string);
			list.add(swReceiveEquipment);
		}
		
		
		swReceiveEquipment.setEquipmentId(ids);
		model.addAttribute("list", list);
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		return "modules/business/receive/rkReceive";
		
		
	}
	@RequestMapping(value = "ckReceive")
	public String ckReceive(String ids, Model model) {
		String id[] = ids.split(",");
		List<SwReceiveEquipment> list = new ArrayList<SwReceiveEquipment>();
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		for (String string : id) {
			swReceiveEquipment = swReceiveService.getEquipment(string);
			list.add(swReceiveEquipment);
		}
		swReceiveEquipment.setEquipmentId(ids);
		model.addAttribute("list", list);
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		return "modules/business/receive/ckReceive";
	}
	
	
	@RequestMapping(value = "viewEquipment")
	public String viewEquipment(SwReceive swReceive,String ids, Model model) {
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		swReceiveEquipment = swReceiveService.getEquipment(ids);
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		return "modules/business/receive/swReceiveEquipmentView";
     }
	
	//固定资产卡片
	@RequestMapping(value = "viewAssets")
	public String viewAssets(SwReceive swReceive,String ids, Model model) {
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		swReceiveEquipment = swReceiveService.getEquipment(ids);
		SbEquipment sbEquipment=sbEquipmentService.get(swReceiveEquipment.getEquipmentId()) ;
		swReceive = swReceiveService.get(swReceiveEquipment.getReceiveId());
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		model.addAttribute("sbEquipment", sbEquipment);
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveAcceptanceView";
     }
	
	//验收单
	@RequestMapping(value = "viewAcceptance")
	public String viewAcceptance(SwReceive swReceive,String ids, Model model) {
		SwReceiveEquipment swReceiveEquipment = new SwReceiveEquipment();
		swReceiveEquipment = swReceiveService.getEquipment(ids);
		swReceive = swReceiveService.get(swReceiveEquipment.getReceiveId());
		model.addAttribute("swReceiveEquipment", swReceiveEquipment);
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveView2";
     }
	
	@RequestMapping(value = "wcEquipment")
 	public String wcEquipment(SwReceive swReceive,String ids, Model model) {
		if(swReceive.getOrderId()!=null || !"".equals(swReceive.getOrderId())){
			
		   SwOrder swOrder = swOrderService.get(swReceive.getOrderId()) ;
		   

			swReceive = swReceiveService.get(swOrder.getField7Id());
			
			List<SwReceiveEquipment> swReceiveEquipmentList = swReceive.getSwReceiveEquipmentList();
			for (SwReceiveEquipment swReceiveEquipment : swReceiveEquipmentList) {
				swReceiveEquipment.setState("3");
				swReceiveService.saveEquipment(swReceiveEquipment);
			}
		   
		   swOrder.setField8Text("<font color='green'>入库/分发</font><i style='color: green;' class='icon-ok-sign'></i>");
		   swOrder.setField8State("3");
		   swOrder.setField8DateEnd(new Date());
		   swOrder.setField9State("2");
		   swOrder.setState("9");
		   swOrderService.save(swOrder);
		}
		return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
      }
 	
 	@RequestMapping(value = "wcAssets")
   	public String wcAssets(SwReceive swReceive,String ids, Model model) {
  		if(swReceive.getOrderId()!=null || !"".equals(swReceive.getOrderId())){
  		   SwOrder swOrder = swOrderService.get(swReceive.getOrderId()) ;
  			swReceive = swReceiveService.get(swOrder.getField7Id());	
  			List<SwReceiveEquipment> swReceiveEquipmentList = swReceive.getSwReceiveEquipmentList();
  			for (SwReceiveEquipment swReceiveEquipment : swReceiveEquipmentList) {
  				swReceiveEquipment.setState("5");
  				swReceiveService.saveEquipment(swReceiveEquipment);
  			}
  		   
  		   swOrder.setField9Text("<font color='green'>验收/固定资产卡片</font><i style='color: green;' class='icon-ok-sign'></i>");
  		   swOrder.setField9State("3");
  		 swOrder.setField9DateEnd(new Date());
  		   swOrder.setField10State("2");
  		 swOrder.setState("10");
  		   swOrderService.save(swOrder);
  		}
  		return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
        }
	
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = "view")
	public String view(SwReceive swReceive, Model model) {
		
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveView";
	}
	
	
	//最终
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = "view3")
	public String view3(SwReceive swReceive, Model model) {
		
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveView3";
	}
	
	@RequiresPermissions("business:receive:swReceive:view")
	@RequestMapping(value = "view2")
	public String view2(SwReceive swReceive, Model model) {
		
		model.addAttribute("swReceive", swReceive);
		return "modules/business/receive/swReceiveView2";
	}
	
	

	@RequiresPermissions("business:receive:swReceive:edit")
	@RequestMapping(value = "save")
	public String save(SwReceive swReceive, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swReceive)){
			return form(swReceive, model);
		}
		swReceiveService.save(swReceive);
		
		SwOrder swOrder = swOrderService.get(swReceive.getOrderId());
		swOrder.setField7Id(swReceive.getId());
		
		if("1".equals(swReceive.getState())){
			swOrder.setField7State("3");
			swOrder.setState("8");
			swOrder.setField7Text("<font color='green'>验收</font><i style='color: green;' class='icon-ok-sign'></i>");
			swOrder.setField7DateEnd(new Date());
		    swOrder.setField8State("2");
		    
		    
		    //验收款
		    SwPay swPay = new SwPay();
		    swPay.setOrderId(swReceive.getOrderId());
		    swPay.setPayCondition("2");
		    List<SwPay> list = swPayService.findList(swPay);
		    if(list!=null && list.size()>0){
		    	swPay = list.get(0);
		    	Calendar cal = Calendar.getInstance();
	    		cal.add(Calendar.DATE, 15);
	    		Date date = cal.getTime();
		    	swPay.setProjectDate(date);
		    	swPayService.save(swPay);
		    }
		    //尾款1
		    swPay = new SwPay();
		    swPay.setOrderId(swReceive.getOrderId());
		    swPay.setPayCondition("3");
		    list = swPayService.findList(swPay);
		    if(list!=null && list.size()>0){
		    	swPay = list.get(0);
		    	Calendar cal = Calendar.getInstance();
	    		cal.add(Calendar.DATE, 60);
	    		Date date = cal.getTime();
		    	swPay.setProjectDate(date);
		    	swPayService.save(swPay);
		    }
		    
		    
		    //尾款2
		    swPay = new SwPay();
		    swPay.setOrderId(swReceive.getOrderId());
		    swPay.setPayCondition("4");
		    list = swPayService.findList(swPay);
		    if(list!=null && list.size()>0){
		    	swPay = list.get(0);
		    	Calendar cal = Calendar.getInstance();
	    		cal.add(Calendar.DATE, 365);
	    		Date date = cal.getTime();
		    	swPay.setProjectDate(date);
		    	swPayService.save(swPay);
		    }
		    
		    
			
		}
		
		swOrderService.save(swOrder);
		addMessage(redirectAttributes, "保存仪器设备开箱验收成功");
		if("1".equals(swReceive.getState())){
			
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/receive/swReceive/form?id="+swReceive.getId();
		}
		
	
	}
	
	@RequiresPermissions("business:receive:swReceive:edit")
	@RequestMapping(value = "rksave")
	public String rksave(SwReceiveEquipment swReceiveEquipment, Model model, RedirectAttributes redirectAttributes) {
		String ids[] = swReceiveEquipment.getEquipmentId().split(",");
		int i = 0 ;
		String swReceiveId = "" ;
		String orderId = "" ;
		for (String id : ids) {
			SwReceiveEquipment temp = swReceiveService.getEquipment(id);
			if(i==0){
				swReceiveId = temp.getReceiveId().getId();
				orderId = swReceiveService.get(swReceiveId).getOrderId();
			}
			
			
			System.out.println(temp.getReceiveId().getId());
			temp.setRfiles(swReceiveEquipment.getRfiles());
			swReceiveService.saveEquipment(temp);
		}
		if("1".equals(swReceiveEquipment.getState())){
			
			
			
			
			String id[] = swReceiveEquipment.getEquipmentId().split(",");
			SbEquipment sbEquipment = new SbEquipment();
			for (String idstring : id) {
				SwReceiveEquipment temp = swReceiveService.getEquipment(idstring);
				sbEquipment.setName(temp.getEquipmentName());
				sbEquipment.setType(temp.getEquipmentModel());
				sbEquipment.setField1(temp.getEquipmentFactoryNumber());
				sbEquipment.setZccode(temp.getZccode());
				sbEquipment.setField2(temp.getAmount()+"");
				sbEquipment.setUnitprice(temp.getUnitprice());
				sbEquipment.setPrice(temp.getPrice());
				sbEquipment.setTeam(temp.getTeam());
				sbEquipment.setTeamname(temp.getTeamname());	
				sbEquipment.setUsepeople(temp.getUsepeople());
		    	sbEquipment.setUsepeoplename(temp.getUsepeoplename());
				sbEquipment.setField4(temp.getLocal());
				sbEquipment.setField5(temp.getIsMetering());
				if(temp.getMeteringDate()!=null)
				sbEquipment.setField6(temp.getMeteringDate().toString());
				sbEquipment.setField10(temp.getMeteringType());
				sbEquipment.setField11(temp.getMeteringTime());
				sbEquipment.setFundingsource(temp.getFundingsource());
				sbEquipment.setFsType(temp.getFsType());
				sbEquipment.setFsTypeName(temp.getFsTypeName());
				sbEquipment.setSbType(temp.getSbType());
				sbEquipment.setSbTypeName(temp.getSbTypeName());		
				temp.setState("1");
				if((sbEquipment.getSbcode()==null||"".equals(sbEquipment.getSbcode()))&&(sbEquipment.getSbType()!=null&&!"".equals(sbEquipment.getSbType()))){
					String type = sbFunctionTypeService.get(sbEquipment.getSbType()).getCode();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
					String currentDate = df.format(new Date());//系统当前时间
					String year = currentDate.split("-")[0];// 年
					String code = sbEquipmentBusService.getCode("sb_equipment","sbcode",type+year);
					sbEquipment.setSbcode(getCode(type,code));
				}
				sbEquipment.setState("1");
				sbEquipment.setBstate("1");
				sbEquipmentService.save(sbEquipment);
				temp.setEquipmentId(sbEquipment.getId());
				swReceiveService.saveEquipment(temp);
				sbEquipment = new SbEquipment();
			}	
			
		}
		return "redirect:"+Global.getAdminPath()+"/business/receive/swReceive/listEquipment?id=&orderId="+orderId;
	}
	
	@RequiresPermissions("business:receive:swReceive:edit")
	@RequestMapping(value = "cksave")
	public String cksave(SwReceiveEquipment swReceiveEquipment, Model model, RedirectAttributes redirectAttributes) {
		String ids[] = swReceiveEquipment.getEquipmentId().split(",");
		int i = 0 ;
		String swReceiveId = "" ;
		String orderId = "" ;
		for (String id : ids) {
			SwReceiveEquipment temp = swReceiveService.getEquipment(id);
			if(i==0){
				swReceiveId = temp.getReceiveId().getId();
				orderId = swReceiveService.get(swReceiveId).getOrderId();
			}
			temp.setCfiles(swReceiveEquipment.getCfiles());
			swReceiveService.saveEquipment(temp);
		}
		if("1".equals(swReceiveEquipment.getState())){
			String id[] = swReceiveEquipment.getEquipmentId().split(",");
			SbEquipment sbEquipment = new SbEquipment();
			for (String idstring : id) {
				SwReceiveEquipment temp = swReceiveService.getEquipment(idstring);
				sbEquipment = sbEquipmentService.get(temp.getEquipmentId());
				sbEquipment.setName(temp.getEquipmentName());
				sbEquipment.setType(temp.getEquipmentModel());
				sbEquipment.setField1(temp.getEquipmentFactoryNumber());
				sbEquipment.setZccode(temp.getZccode());
				sbEquipment.setField2(temp.getAmount()+"");
				sbEquipment.setUnitprice(temp.getUnitprice());
				sbEquipment.setPrice(temp.getPrice());
				sbEquipment.setTeam(temp.getTeam());
				sbEquipment.setTeamname(temp.getTeamname());	
				sbEquipment.setUsepeople(temp.getUsepeople());
		    	sbEquipment.setUsepeoplename(temp.getUsepeoplename());
				sbEquipment.setField4(temp.getLocal());
				sbEquipment.setField5(temp.getIsMetering());
				if(temp.getMeteringDate()!=null)
				sbEquipment.setField6(temp.getMeteringDate().toString());
				sbEquipment.setField10(temp.getMeteringType());
				sbEquipment.setField11(temp.getMeteringTime());
				sbEquipment.setFundingsource(temp.getFundingsource());
				sbEquipment.setFsType(temp.getFsType());
				sbEquipment.setFsTypeName(temp.getFsTypeName());
				sbEquipment.setSbType(temp.getSbType());
				sbEquipment.setSbTypeName(temp.getSbTypeName());		
				temp.setState("2");
				if((sbEquipment.getSbcode()==null||"".equals(sbEquipment.getSbcode()))&&(sbEquipment.getSbType()!=null&&!"".equals(sbEquipment.getSbType()))){
					String type = sbFunctionTypeService.get(sbEquipment.getSbType()).getCode();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
					String currentDate = df.format(new Date());//系统当前时间
					String year = currentDate.split("-")[0];// 年
					String code = sbEquipmentBusService.getCode("sb_equipment","sbcode",type+year);
					sbEquipment.setSbcode(getCode(type,code));
				}
				sbEquipment.setState("1");
				sbEquipment.setBstate("1");
				sbEquipmentService.save(sbEquipment);
				swReceiveService.saveEquipment(temp);
			}	
			
		}
		return "redirect:"+Global.getAdminPath()+"/business/receive/swReceive/listEquipment?id=&orderId="+orderId;
	}
	
	
	@RequestMapping(value = "rkEquipment")
	public String rkEquipment(SwReceiveEquipment swReceiveEquipment,String ids ,HttpServletResponse response) {
		String id[] = ids.split(",");
		SbEquipment sbEquipment = new SbEquipment();
		for (String idstring : id) {
			SwReceiveEquipment temp = swReceiveService.getEquipment(idstring);
			sbEquipment.setName(temp.getEquipmentName());
			sbEquipment.setType(temp.getEquipmentModel());
			sbEquipment.setField1(temp.getEquipmentFactoryNumber());
			sbEquipment.setZccode(temp.getZccode());
			sbEquipment.setField2(temp.getAmount()+"");
			sbEquipment.setUnitprice(temp.getUnitprice());
			sbEquipment.setPrice(temp.getPrice());
			sbEquipment.setTeam(temp.getTeam());
			sbEquipment.setTeamname(temp.getTeamname());	
			sbEquipment.setUsepeople(temp.getUsepeople());
	    	sbEquipment.setUsepeoplename(temp.getUsepeoplename());
			sbEquipment.setField4(temp.getLocal());
			sbEquipment.setField5(temp.getIsMetering());
			if(temp.getMeteringDate()!=null)
			sbEquipment.setField6(temp.getMeteringDate().toString());
			sbEquipment.setField10(temp.getMeteringType());
			sbEquipment.setField11(temp.getMeteringTime());
			sbEquipment.setFundingsource(temp.getFundingsource());
			sbEquipment.setFsType(temp.getFsType());
			sbEquipment.setFsTypeName(temp.getFsTypeName());
			sbEquipment.setSbType(temp.getSbType());
			sbEquipment.setSbTypeName(temp.getSbTypeName());		
			temp.setState("1");
			if((sbEquipment.getSbcode()==null||"".equals(sbEquipment.getSbcode()))&&(sbEquipment.getSbType()!=null&&!"".equals(sbEquipment.getSbType()))){
				String type = sbFunctionTypeService.get(sbEquipment.getSbType()).getCode();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String currentDate = df.format(new Date());//系统当前时间
				String year = currentDate.split("-")[0];// 年
				String code = sbEquipmentBusService.getCode("sb_equipment","sbcode",type+year);
				sbEquipment.setSbcode(getCode(type,code));
			}
			sbEquipment.setState("1");
			sbEquipment.setBstate("1");
			sbEquipmentService.save(sbEquipment);
			temp.setEquipmentId(sbEquipment.getId());
			swReceiveService.saveEquipment(temp);
			sbEquipment = new SbEquipment();
		}	
		
		return  renderString(response, JsonMapper.toJsonString("sucess"),"text/html");
	}
	
	public String getCode(String type,String code){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		

		String year = currentDate.split("-")[0];// 年
		
		String codex =type+year+"-";
		if(code!=null){
			String[] maxCodes = code.split("-");
			String maxCode = maxCodes[maxCodes.length - 1];
			int maxc = Integer.parseInt(maxCode);
			int nowc = maxc + 1;
			int len = String.valueOf(nowc).length();
			int siz = 4 - len;
			if (siz == 0)
				codex = codex + String.valueOf(nowc);
			if (siz == 1)
				codex = codex + "0" + String.valueOf(nowc);
			if (siz == 2)
				codex = codex + "00" + String.valueOf(nowc);
			if (siz == 3)
				codex = codex + "000" + String.valueOf(nowc);
		
			
		}else{
			
			codex = codex+"0001";
		}
		
		return codex ;	
	}
	
	@RequestMapping(value = "saveEquipment")
	public String saveEquipment(SwReceiveEquipment swReceiveEquipment,String ids ,HttpServletResponse response) {
		String id[] = ids.split(",");
		for (String idstring : id) {
			SwReceiveEquipment temp = swReceiveService.getEquipment(idstring);
			if(swReceiveEquipment.getEquipmentModel()!=null && !"".equals(swReceiveEquipment.getEquipmentModel()))
			temp.setEquipmentModel(swReceiveEquipment.getEquipmentModel());
			if(swReceiveEquipment.getEquipmentNorms()!=null && !"".equals(swReceiveEquipment.getEquipmentNorms()))
			temp.setEquipmentNorms(swReceiveEquipment.getEquipmentNorms());
			if(swReceiveEquipment.getEquipmentValue()!=null && !"".equals(swReceiveEquipment.getEquipmentValue()))
			temp.setEquipmentValue(swReceiveEquipment.getEquipmentValue());
			if(swReceiveEquipment.getEquipmentPower()!=null && !"".equals(swReceiveEquipment.getEquipmentPower()))
			temp.setEquipmentPower(swReceiveEquipment.getEquipmentPower());
			if(swReceiveEquipment.getEquipmentFactoryNumber()!=null && !"".equals(swReceiveEquipment.getEquipmentFactoryNumber()))
			temp.setEquipmentFactoryNumber(swReceiveEquipment.getEquipmentFactoryNumber());
			if(swReceiveEquipment.getZccode()!=null && !"".equals(swReceiveEquipment.getZccode()))
			temp.setZccode(swReceiveEquipment.getZccode());
			
			if(swReceiveEquipment.getAmount()>0)
			temp.setAmount(swReceiveEquipment.getAmount());
			if(swReceiveEquipment.getUnitprice()>0)
			temp.setUnitprice(swReceiveEquipment.getUnitprice());
			if(swReceiveEquipment.getPrice()>0 )
			temp.setPrice(swReceiveEquipment.getPrice());
			if(swReceiveEquipment.getTeam()!=null && !"".equals(swReceiveEquipment.getTeam()))
			temp.setTeam(swReceiveEquipment.getTeam());
			if(swReceiveEquipment.getTeamname()!=null && !"".equals(swReceiveEquipment.getTeamname()))
			temp.setTeamname(swReceiveEquipment.getTeamname());
			if(swReceiveEquipment.getUsepeople()!=null && !"".equals(swReceiveEquipment.getUsepeople()))
			temp.setUsepeople(swReceiveEquipment.getUsepeople());
			if(swReceiveEquipment.getUsepeoplename()!=null && !"".equals(swReceiveEquipment.getUsepeoplename()))
			temp.setUsepeoplename(swReceiveEquipment.getUsepeoplename());
			if(swReceiveEquipment.getLocal()!=null && !"".equals(swReceiveEquipment.getLocal()))
			temp.setLocal(swReceiveEquipment.getLocal());
			if(swReceiveEquipment.getIsMetering()!=null && !"".equals(swReceiveEquipment.getIsMetering()))
			temp.setIsMetering(swReceiveEquipment.getIsMetering());
			if(swReceiveEquipment.getMeteringDate()!=null && !"".equals(swReceiveEquipment.getMeteringDate()))
			temp.setMeteringDate(swReceiveEquipment.getMeteringDate());
			if(swReceiveEquipment.getMeteringType()!=null && !"".equals(swReceiveEquipment.getMeteringType()))
			temp.setMeteringType(swReceiveEquipment.getMeteringType());
			if(swReceiveEquipment.getMeteringTime()!=null && !"".equals(swReceiveEquipment.getMeteringTime()))
			temp.setMeteringTime(swReceiveEquipment.getMeteringTime());
			if(swReceiveEquipment.getFundingsource()!=null && !"".equals(swReceiveEquipment.getFundingsource()))
			temp.setFundingsource(swReceiveEquipment.getFundingsource());
			if(swReceiveEquipment.getFsType()!=null && !"".equals(swReceiveEquipment.getFsType()))
			temp.setFsType(swReceiveEquipment.getFsType());
			if(swReceiveEquipment.getFsTypeName()!=null && !"".equals(swReceiveEquipment.getFsTypeName()))
			temp.setFsTypeName(swReceiveEquipment.getFsTypeName());
			if(swReceiveEquipment.getSbType()!=null && !"".equals(swReceiveEquipment.getSbType()))
			temp.setSbType(swReceiveEquipment.getSbType());
			if(swReceiveEquipment.getSbTypeName()!=null && !"".equals(swReceiveEquipment.getSbTypeName()))
			temp.setSbTypeName(swReceiveEquipment.getSbTypeName());
			
			
			swReceiveService.saveEquipment(temp);
			
		}
		
		
		 return  renderString(response, JsonMapper.toJsonString("sucess"),"text/html");
	}
	
	@RequiresPermissions("business:receive:swReceive:edit")
	@RequestMapping(value = "delete")
	public String delete(SwReceive swReceive, RedirectAttributes redirectAttributes) {
		swReceiveService.delete(swReceive);
		addMessage(redirectAttributes, "删除仪器设备开箱验收成功");
		return "redirect:"+Global.getAdminPath()+"/business/receive/swReceive/?repage";
	}

}