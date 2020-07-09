/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.order;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBidding;
import com.thinkgem.jeesite.modules.business.entity.bidding.SwBiddingSupplier;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.purchase.SwPurchase;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceive;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurvey;
import com.thinkgem.jeesite.modules.business.entity.survey.SwSurveyCompany;
import com.thinkgem.jeesite.modules.business.entity.swbiddingpublic.SwBiddingPublic;
import com.thinkgem.jeesite.modules.business.service.agreement.SwAgreementService;
import com.thinkgem.jeesite.modules.business.service.bidding.SwBiddingService;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.purchase.SwPurchaseService;
import com.thinkgem.jeesite.modules.business.service.receive.SwReceiveService;
import com.thinkgem.jeesite.modules.business.service.special.SwSpecialService;
import com.thinkgem.jeesite.modules.business.service.survey.SwSurveyService;
import com.thinkgem.jeesite.modules.business.service.swbiddingpublic.SwBiddingPublicService;
import com.thinkgem.jeesite.modules.equipment.entity.repair.SbEquipmentRepair;
import com.thinkgem.jeesite.modules.equipment.service.repair.SbEquipmentRepairService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 采购任务Controller
 * @author suntao
 * @version 2020-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/business/order/swOrder")
public class SwOrderController extends BaseController {

	@Autowired
	private SwOrderService swOrderService;
	
	@Autowired
	private SwProductService swProductService;
	
	@Autowired
	private SwSurveyService swSurveyService;
	
	
	@Autowired
	private SwSpecialService swSpecialService;
	
	@Autowired
	private SwBiddingService swBiddingService;
	
	@Autowired
	private SwBiddingPublicService swBiddingPublicService;
	
	@Autowired
	private SwNegotiateService swNegotiateService ;
	
	@Autowired
	private SwAgreementService swAgreementService ;
	
	@Autowired
	private SwPurchaseService swPurchaseService ;
	
	@Autowired
	private SwPayService swPayService;
	
	@Autowired
	private SwReceiveService swReceiveService;
	
	@Autowired
	private SbEquipmentRepairService sbEquipmentRepairService;
	
	
	@ModelAttribute
	public SwOrder get(@RequestParam(required=false) String id) {
		SwOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swOrderService.get(id);
		}
		if (entity == null){
			entity = new SwOrder();
		}
		return entity;
	}
	
	
	//设备采购
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwOrder swOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if("WX".equals(swOrder.getIdentification())){
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/listRepair?repage";
		}
		List<Role>  list = UserUtils.getUser().getRoleList();
		boolean bool = true ;
		for (Role role : list) {
			if("yqsbgly".equals(role.getEnname()) || "gsld".equals(role.getEnname()) ){
				bool = false ;
			}
		}
		if("1".equals(UserUtils.getUser().getId())){
			bool = false ;
		}
		if(bool){
			swOrder.setDyuserId(UserUtils.getUser().getId());
		}
		if(swOrder.getIdentification()==null || "".equals(swOrder.getIdentification())){
			swOrder.setIdentification("CG");
		}
		swOrder.setField1(UserUtils.getUser().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		model.addAttribute("time",new Date());
		
		Page<SwOrder> page = swOrderService.findPage(new Page<SwOrder>(request, response), swOrder); 
		model.addAttribute("page", page);
		model.addAttribute("userId", UserUtils.getUser().getId());
		
		return "modules/business/order/swOrderList";
	}
	
	//设备维修
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = {"listRepair"})
	public String listRepair(SwOrder swOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		
		swOrder.setIdentification("WX");
		swOrder.setField1(UserUtils.getUser().getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		model.addAttribute("time",new Date());
		Page<SwOrder> page = swOrderService.findPage(new Page<SwOrder>(request, response), swOrder); 
		model.addAttribute("page", page);
		model.addAttribute("userId", UserUtils.getUser().getId());
		return "modules/business/order/swOrderListRepair";
	}
	
	
	
	
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = {"listYear"})
	public String listYear(SwOrder swOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		swOrder.setField1(UserUtils.getUser().getId());
		Page<SwOrder> page = swOrderService.findListYear(new Page<SwOrder>(request, response), swOrder); 
		model.addAttribute("page", page);
		return "modules/business/order/swOrderListYear";
	}
	
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = {"listProductYear"})
	public String listProductYear(SwOrder swOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		swOrder.setField1(UserUtils.getUser().getId());
		Page<SwOrder> page = swOrderService.findProductListYear(new Page<SwOrder>(request, response), swOrder); 
		model.addAttribute("page", page);
		return "modules/business/order/swOrderProductListYear";
	}

	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = "form")
	public String form(SwOrder swOrder, Model model) {
		double amountYs = 0 ;
		User user = null ;
		String name = "" ;
		User zrUser = null ;
		String zichanType ="CG";
		
		String id[] = swOrder.getField2().split(",");
		for (String string : id) {
			SwProduct swProduct = swProductService.get(string);
			user = swProduct.getUser();
			if(swProduct.getZrUserId()!=null && !"".equals(swProduct.getZrUserId())){
				zrUser = UserUtils.get(swProduct.getZrUserId());
				swOrder.setUser(zrUser);
				swOrder.setUserName(zrUser.getName());
				
			}
			if(swProduct.getTotalPrice()!=null){
				amountYs+=(swProduct.getTotalPrice()*10000);

			}
			if("DH".equals(swProduct.getType())){
				zichanType="DH";
			}
			
			name = swProduct.getProductName()+"采购";
			swOrder.setField1(swProduct.getProjectType());
			swOrder.setProjectType(DictUtils.getDictLabel(swProduct.getProjectType(), "project_type", ""));
		}

		if(user!=null){
			
			swOrder.setDyuserId(user.getId());
			swOrder.setDyuserName(user.getName());
		}
		
		
		swOrder.setName(name);
		swOrder.setType("0");
		DecimalFormat df = new DecimalFormat("#.00");

		
		swOrder.setAmountYs(amountYs);
		swOrder.setAmount(amountYs);
		
		List<Dict>  list =DictUtils.getDictList("order_state") ;
		swOrder.setIdentification(zichanType);
		model.addAttribute("swOrder", swOrder);
		model.addAttribute("dictList", list);
		return "modules/business/order/swOrderForm";
	}
	
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = "edit")
	public String edit(SwOrder swOrder, Model model) {
		SwProduct swProduct = new SwProduct();
		swProduct.setOrderId(swOrder.getId());
		swProduct.setZrUserId(UserUtils.getUser().getId());
		List<SwProduct> swProductList = swProductService.findList(swProduct) ;
		swOrder.setSwProductList(swProductList);
		List<Dict>  list =DictUtils.getDictList("order_state") ;
		model.addAttribute("swOrder", swOrder);
		model.addAttribute("dictList", list);
		return "modules/business/order/swOrderEdit";
	}
	
	
	@RequiresPermissions("business:order:swOrder:view")
	@RequestMapping(value = "editRepair")
	public String editRepair(SwOrder swOrder, Model model) {
		SbEquipmentRepair sbEquipmentRepair = new SbEquipmentRepair();
		sbEquipmentRepair.setOrderId(swOrder.getId());
		
		
		List<SbEquipmentRepair> sbEquipmentRepairList = sbEquipmentRepairService.findList(sbEquipmentRepair) ;
		swOrder.setSbEquipmentRepairList(sbEquipmentRepairList);
		model.addAttribute("swOrder", swOrder);
		List<Dict>  list =DictUtils.getDictList("order_state") ;
		model.addAttribute("dictList", list);
		return "modules/business/order/swOrderEditRepair";
	}

	@RequiresPermissions("business:order:swOrder:edit")
	@RequestMapping(value = "save")
	public String save(SwOrder swOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swOrder)){
			return form(swOrder, model);
		}
		
		List<SwProduct> swProductList = swOrder.getSwProductList();
		if(swProductList!=null){
			for (SwProduct swProduct : swProductList) {
				if (SwProduct.DEL_FLAG_NORMAL.equals(swProduct.getDelFlag())){	
				}else{
					swProduct.setState("0");
					swProduct.setOrderId(null);
					swProductService.save(swProduct);
				}
			}
		}
		if(swOrder.getAmountYs()<200000 || "1".equals(swOrder.getType())){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField2Date(null);
		}
		
		if(swOrder.getAmountYs()<20000){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField4State("0");
			swOrder.setField4Text("");
			swOrder.setField2Date(null);
			swOrder.setField4Date(null);
		}
		if("2".equals(swOrder.getType())){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField3State("0");
			swOrder.setField3Text("");
			swOrder.setField4State("0");
			swOrder.setField4Text("");
			swOrder.setField2Date(null);
			swOrder.setField3Date(null);
			swOrder.setField4Date(null);
		}
       if("1".equals(swOrder.getType())){
    	   swOrder.setTypeOrder("1");
		}
		if("2".equals(swOrder.getType())){
		   swOrder.setTypeOrder("2");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()<200000){
	    	   swOrder.setTypeOrder("3");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()<2000000 && swOrder.getAmountYs()>=200000){
	    	   swOrder.setTypeOrder("4");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()>=2000000){
	    	   swOrder.setTypeOrder("5");
		}
		swOrderService.save(swOrder);
		addMessage(redirectAttributes, "保存采购任务成功");
		if("WX".equals(swOrder.getIdentification())){
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/listRepair?repage";
		}
		return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
	}
	
	@RequestMapping(value = "saveOrder")
    @ResponseBody
    public void saveOrder(SwOrder swOrder,String ids ,HttpServletResponse response){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		
		if(swOrder.getIsNewRecord()){
			swOrder.setField1State("2");
			swOrder.setField1Text("<font color='#FF0000'>调研</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField2State("1");
			swOrder.setField2Text("<font color='#FF0000'>招投标</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField3State("1");
			swOrder.setField3Text("<font color='#FF0000'>谈判</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField5State("1");
			swOrder.setField5Text("<font color='#FF0000'>合同签订</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField6State("1");
			swOrder.setField6Text("<font color='#FF0000'>借款</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField7State("1");
			swOrder.setField7Text("<font color='#FF0000'>到货/验收</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField8State("1");
			swOrder.setField8Text("<font color='#FF0000'>入库/分发</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField9State("1");
			swOrder.setField9Text("<font color='#FF0000'>固定资产卡片</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField10State("1");
			swOrder.setField10Text("<font color='#FF0000'>报销</font><i style='color: red;' class='icon-exclamation-sign'></i>");
			swOrder.setField11State("1");
		}
		
		if(swOrder.getAmountYs()<200000 || "1".equals(swOrder.getType())){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField2Date(null);
		}
		
		if(swOrder.getAmountYs()<20000){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField4State("0");
			swOrder.setField4Text("");
			swOrder.setField2Date(null);
			swOrder.setField4Date(null);
		}
		if("2".equals(swOrder.getType())){
			swOrder.setField2State("0");
			swOrder.setField2Text("");
			swOrder.setField3State("0");
			swOrder.setField3Text("");
			swOrder.setField4State("0");
			swOrder.setField4Text("");
			swOrder.setField2Date(null);
			swOrder.setField3Date(null);
			swOrder.setField4Date(null);
		}
       if("1".equals(swOrder.getType())){
    	   swOrder.setTypeOrder("1");
		}
		if("2".equals(swOrder.getType())){
		   swOrder.setTypeOrder("2");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()<200000){
	    	   swOrder.setTypeOrder("3");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()<2000000 && swOrder.getAmountYs()>=200000){
	    	   swOrder.setTypeOrder("4");
		}
		if("0".equals(swOrder.getType()) && swOrder.getAmountYs()>=2000000){
	    	   swOrder.setTypeOrder("5");
		}
		
    
		String year = currentDate.split("-")[0];// 年
		String code = swOrderService.getCode(year);
		swOrder.setState("1");
		swOrder.setCode(getCode(code));
		swOrderService.save(swOrder);
		
		swOrder = swOrderService.getOrderByCode(swOrder);
		String id[] = ids.split(",");
		for (String string : id) {
			SwProduct swProduct = swProductService.get(string);
			swProduct.setOrderId(swOrder.getId());
			swProduct.setField1(swOrder.getCode());
			swProduct.setState("1");
			swProductService.save(swProduct);
		}

        renderString(response, JsonMapper.toJsonString("sucess"),"text/html");

    }
	
	public String getCode(String code){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		

		String year = currentDate.split("-")[0];// 年
		
		String codex =year+"-";
		if(code!=null){
			String[] maxCodes = code.split("-");
			String maxCode = maxCodes[maxCodes.length - 1];
			int maxc = Integer.parseInt(maxCode);
			int nowc = maxc + 1;
			int len = String.valueOf(nowc).length();
			int siz = 3 - len;
			if (siz == 0)
				codex = codex + String.valueOf(nowc);
			if (siz == 1)
				codex = codex + "0" + String.valueOf(nowc);
			if (siz == 2)
				codex = codex + "00" + String.valueOf(nowc);
			if (siz == 3)
				codex = codex + "000" + String.valueOf(nowc);
		
			
		}else{
			
			codex = codex+"001";
		}
		
		return codex ;	
	}
	
	@RequiresPermissions("business:order:swOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SwOrder swOrder, RedirectAttributes redirectAttributes) {
		swOrderService.delete(swOrder);
		SwProduct swProduct = new SwProduct();
		swProduct.setOrderId(swOrder.getId());
		swProduct.setZrUserId(UserUtils.getUser().getId());
		List<SwProduct> swProductList = swProductService.findList(swProduct) ;
		for (SwProduct swProduct2 : swProductList) {
			swProduct2.setState("0");
			swProduct2.setField1(null);
			swProduct2.setOrderId(null);
			swProductService.save(swProduct2);
			
		}
		addMessage(redirectAttributes, "删除采购任务成功");
		return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
	}
	

	@RequestMapping(value = {"progress"})
	public String progress(SwOrder swOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<Map> sworderList = new ArrayList<Map>();
		Map map = new HashMap();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		
		//计划外
		if("2".equals(swOrder.getField1())){
			SwProduct swProduct = new SwProduct();
			swProduct.setOrderId(swOrder.getId());
			List<SwProduct> swProductList = swProductService.findList(swProduct);
			if(swProductList!=null && swProductList.size()>0){
				swProduct = swProductList.get(0);
			}
			map.put("name", "计划外采购");
			map.put("state", "3");
			map.put("year", formatter.format(swProduct.getCreateDate()).substring(0, 4));
        	map.put("day", formatter.format(swProduct.getCreateDate()).substring(5, 10));
        	map.put("html", 
        			"<tr><td style='text-align:center;width:25%'>设备名称:</td><td style='text-align:center;;width:25%'>"+swProduct.getProductName()+"</td>"
        			+ "<td style='text-align:center;width:25%'>预算(万):</td><td style='text-align:center;;width:25%'>"+swProduct.getProductAmount()+"</td></tr>"
        			+ "<tr><td style='text-align:center;width:25%'>采购申请人:</td><td style='text-align:center;width:25%'>"+swProduct.getUserName()+"</td>"
        					+ "<td style='text-align:center;width:25%'>设备类别:</td><td style='text-align:center;width:25%'>"+swProduct.getSbTypeName()+"</td></tr>"
        					+ "<tr><td style='text-align:center;width:25%'>申购原因:</td><td style='text-align:center;width:25%' colspan='3' >"+swProduct.getReason()+"</td>");
			map.put("file" ,swProduct.getFile());
			sworderList.add(map);
			map = new HashMap();
		}
		
		
		//调研
        
        if("1".equals(swOrder.getField1State()) || "2".equals(swOrder.getField1State())){
        	map.put("name", DictUtils.getDictLabel(swOrder.getTypeOrder(), "type_order", ""));
        	map.put("state", swOrder.getField1State());
        	if(swOrder.getField1Date()!=null ){
        		map.put("year", formatter.format(swOrder.getField1Date()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField1Date()).substring(5, 10));
        	}
        	
		}
	
        if("3".equals(swOrder.getField1State())){
        	
        	map.put("state", swOrder.getField1State());
        	map.put("year", formatter.format(swOrder.getField1DateEnd()).substring(0, 4));
        	map.put("day", formatter.format(swOrder.getField1DateEnd()).substring(5, 10));
        	map.put("id",  swOrder.getField1Id());
        	
        	
        	if("0".equals(swOrder.getType())){
        		map.put("name", "调研");
    			SwSurvey swSurvey = swSurveyService.get(swOrder.getField1Id());
    			map.put("url",  "/business/survey/swSurvey/form?id="+swOrder.getField1Id());
    			String html ="<tr><td style='text-align:center;width:25%'>询价场次号:</td><td style='text-align:center;;width:25%'>"+swSurvey.getField3()+"</td>"
            			+ "<td style='text-align:center;width:25%'>调研人:</td><td style='text-align:center;;width:25%'>"+swOrder.getDyuserName()+"</td></tr>" ;
    			
    			List<SwSurveyCompany> companyList = swSurvey.getSwSurveyCompanyList();
    			for (SwSurveyCompany swSurveyCompany : companyList) {
    				html = html+"<tr><td style='text-align:center;width:25%'>调研供应商:</td><td style='text-align:center;width:25%' colspan='3'>"+swSurveyCompany.getName()+"</td></tr>";
				}
    			
    			map.put("html", html);
    			map.put("file" ,swSurvey.getFile());
    		}
    		
    		//特殊项目
           if(!"0".equals(swOrder.getType())){
        	   map.put("name", "特殊项目申请");
    			SwSpecial swSpecial = swSpecialService.get(swOrder.getField1Id());
    			map.put("url",  "/business/survey/swSurvey/form?id="+swOrder.getField1Id());
    			String html ="<tr><td style='text-align:center;width:25%'>特殊申请类型:</td><td style='text-align:center;;width:25%'>"+DictUtils.getDictLabel(swSpecial.getSpecialType(), "special_type", "")+"</td>"
            			+ "<td style='text-align:center;width:25%'>预算金额:</td><td style='text-align:center;;width:25%'>"+swSpecial.getBudget()+"</td></tr>"
            					 +"<tr><td style='text-align:center;width:25%'>选择供应商:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swSpecial.getSupplierName()+"</td></tr>"
            	            			+ "<tr><td style='text-align:center;width:25%'>申请原因:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swSpecial.getReason()+"</td></tr>" ;
    			map.put("html", html);
    			map.put("file" ,swSpecial.getFile());
    		}
		}
        if(!"0".equals(swOrder.getField1State())){
        	sworderList.add(map);
		}
        
        //招投标
        map  = new HashMap();
        if("1".equals(swOrder.getField2State()) || "2".equals(swOrder.getField2State())){
        	map.put("name", DictUtils.getDictLabel(swOrder.getTypeOrder(), "type_order", ""));
        	map.put("state", swOrder.getField2State());
        	if(swOrder.getField2Date()!=null ){
        	  map.put("year", formatter.format(swOrder.getField2Date()).substring(0, 4));
        	  map.put("day", formatter.format(swOrder.getField2Date()).substring(5, 10));
        	}
		}
	
        if("3".equals(swOrder.getField2State())){
        	map.put("name", DictUtils.getDictLabel(swOrder.getTypeOrder(), "type_order", ""));
        	map.put("state", swOrder.getField2State());
        	map.put("year", formatter.format(swOrder.getField2DateEnd()).substring(0, 4));
        	map.put("day", formatter.format(swOrder.getField2DateEnd()).substring(5, 10));
        	map.put("id",  swOrder.getField2Id());
        	
        	map.put("url",  "/business/bidding/swBidding/form?id="+swOrder.getField2Id());
        	
        	if(swOrder.getAmountYs()<2000000){
        		SwBidding swBidding = swBiddingService.get(swOrder.getField2Id());
        		List<SwBiddingSupplier> swBiddingSupplierList = swBidding.getSwBiddingSupplierList();
        		SwBiddingSupplier  swBiddingSupplier = null;
        		for (SwBiddingSupplier temp : swBiddingSupplierList) {
        			if("1".equals(temp.getIsBid())){
        				swBiddingSupplier = temp ;
        				break;
        			}
				}
        		map.put("file" ,swBidding.getFile());
        		String html ="<tr><td style='text-align:center;width:25%'>询价场次号:</td><td style='text-align:center;;width:25%'>"+swBidding.getField2()+"</td>"
            			+ "<td style='text-align:center;width:25%'>开标时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swBidding.getOpeningTime())+"</td></tr>"
            					 +"<tr><td style='text-align:center;width:25%'> 中标单位:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swBiddingSupplier.getSupplierName()+"</td></tr>";
    			map.put("html", html);
        	}else{
        		SwBiddingPublic swBiddingPublic = swBiddingPublicService.get(swOrder.getField2Id());
        		SwBiddingSupplier  swBiddingSupplier = null;
        		List<SwBiddingSupplier> swBiddingSupplierList = swBiddingPublic.getSwBiddingSupplierList();
        		for (SwBiddingSupplier temp : swBiddingSupplierList) {
        			if("1".equals(temp.getIsBid())){
        				swBiddingSupplier = temp ;
        				break;
        			}
				}
        		map.put("file" ,swBiddingPublic.getFile());
        		String html ="<tr><td style='text-align:center;width:25%'>询价场次号:</td><td style='text-align:center;;width:25%'>"+swBiddingPublic.getField2()+"</td>"
            			+ "<td style='text-align:center;width:25%'>开标时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swBiddingPublic.getBidOpening())+"</td></tr>"
            					 +"<tr><td style='text-align:center;width:25%'> 中标单位:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swBiddingSupplier.getSupplierName()+"</td></tr>";
    			map.put("html", html);
        	}
		}
        if(!"0".equals(swOrder.getField2State())){
        	sworderList.add(map);
		}
        
        
        //商务谈判
        map  = new HashMap();
        if("1".equals(swOrder.getField3State()) || "2".equals(swOrder.getField3State())){
        	map.put("name", "商务谈判");
        	map.put("state", swOrder.getField3State());
        	if(swOrder.getField3Date()!=null ){
        	  map.put("year", formatter.format(swOrder.getField3Date()).substring(0, 4));
        	  map.put("day", formatter.format(swOrder.getField3Date()).substring(5, 10));
        	}
		}
	
        if("3".equals(swOrder.getField3State())){
        	map.put("name", "商务谈判");
        	map.put("state", swOrder.getField3State());
        	map.put("year", formatter.format(swOrder.getField3DateEnd()).substring(0, 4));
        	map.put("day", formatter.format(swOrder.getField3DateEnd()).substring(5, 10));
        	map.put("id",  swOrder.getField3Id());
        	
        	map.put("url",  "/business/negotiate/swNegotiate/form?id="+swOrder.getField3Id());
        	SwNegotiate swNegotiate = swNegotiateService.get(swOrder.getField3Id());
        	String html ="<tr><td style='text-align:center;width:25%'>谈判时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swNegotiate.getNegotiateTime())+"</td>"
        			+ "<td style='text-align:center;width:25%'>谈判地点:</td><td style='text-align:center;;width:25%'>"+swNegotiate.getNegotiateLocal()+"</td></tr>"
        				+	"<tr><td style='text-align:center;width:25%'>谈判价格:</td><td style='text-align:center;;width:25%'>"+swNegotiate.getNegotiatePrice()+"</td>"
                			+ "<td style='text-align:center;width:25%'>差额:</td><td style='text-align:center;;width:25%'>"+swNegotiate.getBalancePrice()+"</td></tr>"
        					 +"<tr><td style='text-align:center;width:25%'> 谈判记录:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swNegotiate.getNegotiateNotes()+"</td></tr>";
			map.put("html", html);
        	map.put("file" ,swNegotiate.getAppendix());
        	
		}
        if(!"0".equals(swOrder.getField3State())){
        	sworderList.add(map);
		}
        
         //合同、申购单
        map  = new HashMap();
        if("1".equals(swOrder.getField5State()) || "2".equals(swOrder.getField5State())){
        	map.put("name", "合同");
        	map.put("state", swOrder.getField5State());
        	if(swOrder.getField5Date()!=null ){
        	  map.put("year", formatter.format(swOrder.getField5Date()).substring(0, 4));
        	  map.put("day", formatter.format(swOrder.getField5Date()).substring(5, 10));
        	}
		}
	
        if("3".equals(swOrder.getField5State())){
        	
        	map.put("state", swOrder.getField5State());
        	map.put("year", formatter.format(swOrder.getField5DateEnd()).substring(0, 4));
        	map.put("day", formatter.format(swOrder.getField5DateEnd()).substring(5, 10));
        	map.put("id",  swOrder.getField5Id());
        	
        	if("2".equals(swOrder.getType()) || swOrder.getAmountYs()<20000){
        		map.put("name", "申购单");
        		SwPurchase swPurchase = swPurchaseService.get(swOrder.getField5Id());
    			map.put("url",  "/business/purchase/swPurchase/form?id="+swOrder.getField5Id());
    			String html ="<tr><td style='text-align:center;width:25%'>申请单位:</td><td style='text-align:center;;width:25%'>"+swPurchase.getOfficeName()+"</td>"
            			+ "<td style='text-align:center;width:25%'>填报日期:</td><td style='text-align:center;;width:25%'>"+formatter.format(swPurchase.getCreateDate())+"</td></tr>"
            				+	"<tr><td style='text-align:center;width:25%'>型号规格、技术条件:</td><td style='text-align:center;;width:25%'>"+swPurchase.getSpecifications()+"</td>"
                    			+ "<td style='text-align:center;width:25%'>资金来源:</td><td style='text-align:center;;width:25%'>"+swPurchase.getFunds()+"</td></tr>"
            					 +"<tr><td style='text-align:center;width:25%'> 申请理由及用途:</td><td style='text-align:center;;width:25%'  colspan='3'>"+swPurchase.getReason()+"</td></tr>";
    			map.put("html", html);
    			map.put("file" ,swPurchase.getFile());
    		}else{
    			map.put("name", "合同");
    			SwAgreement swAgreement = swAgreementService.get(swOrder.getField5Id());
    			String html ="<tr><td style='text-align:center;width:25%'>合同编号:</td><td style='text-align:center;;width:25%'>"+swAgreement.getAgreementNo()+"</td>"
            			+ "<td style='text-align:center;width:25%'>合同类型:</td><td style='text-align:center;;width:25%'>"+DictUtils.getDictLabel(swAgreement.getAgreementType(), "contract_type", "")+"</td></tr>"
            				+	"<tr><td style='text-align:center;width:25%'>合计金额:</td><td style='text-align:center;;width:25%'>"+swAgreement.getAmountup()+"</td>"
                    			+ "<td style='text-align:center;width:25%'>签订时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swAgreement.getSigningTime())+"</td></tr>" ;
            	map.put("html", html);
    			map.put("url",  "/business/agreement/swAgreement/form?id="+swOrder.getField5Id());
    			map.put("file" ,swAgreement.getFile());
    		}
    		
    	
		}
        if(!"0".equals(swOrder.getField1State())){
        	sworderList.add(map);
		}
        
        
        //首付款
        
        SwPay tpp = new SwPay();
        SwPay swPay = new SwPay();
		tpp.setOrderId(swOrder.getId());
		List<SwPay> payList = swPayService.findList(tpp);
		map  = new HashMap();
		if(payList!=null &&payList.size()>0){
			swPay = payList.get(0);
			 
			 if("0".equals(swPay.getState())){
				 map.put("name", "首付款");
		         map.put("state", "2");
		         if(swPay.getProjectDate()!=null ){
		        	 map.put("year", formatter.format(swPay.getProjectDate()).substring(0, 4));
			         map.put("day", formatter.format(swPay.getProjectDate()).substring(5, 10));
		         }
		         
		         sworderList.add(map);
			 }else{
				 map.put("name", "首付款");
		         map.put("state", "3");
		         if(swPay.getCompletionTime()!=null){
		        	 map.put("year", formatter.format(swPay.getCompletionTime()).substring(0, 4));
			         map.put("day", formatter.format(swPay.getCompletionTime()).substring(5, 10));
		         }
		         String html ="<tr><td style='text-align:center;width:25%'>合同总额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePrice()+"</td>"
	            			+ "<td style='text-align:center;width:25%'>本次付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContrateTreat()+"</td></tr>"
	            				+	"<tr><td style='text-align:center;width:25%'>已付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePaid()+"</td>"
	                    			+ "<td style='text-align:center;width:25%'>付款时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swPay.getCompletionTime())+"</td></tr>" ;
	            	map.put("html", html);
		         
		         map.put("file", swPay.getAppendix());
		         sworderList.add(map);
			 }
		}else{
			 map.put("name", "首付款(未付)");
	         map.put("state", "1");
	         if(swOrder.getField6Date()!= null ){
	        	 map.put("year", formatter.format(swOrder.getField6Date()).substring(0, 4));
		         map.put("day", formatter.format(swOrder.getField6Date()).substring(5, 10));
	         }
	         
	         sworderList.add(map);
		}
		
		
		
		 //到货验收
        map  = new HashMap();
        if("1".equals(swOrder.getField7State()) || "2".equals(swOrder.getField7State())){
        	map.put("name", "到货验收");
        	map.put("state", swOrder.getField7State());
        	if(swOrder.getField7Date()!=null){
        		map.put("year", formatter.format(swOrder.getField7Date()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField7Date()).substring(5, 10));
        	}
        	
		}
	
        if("3".equals(swOrder.getField7State())){
        	map.put("name", "到货验收");
        	map.put("state", swOrder.getField7State());
        	 if(swPay.getProjectDate()!=null){
             	map.put("year", formatter.format(swOrder.getField7DateEnd()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField7DateEnd()).substring(5, 10));
        	 }
        	map.put("id",  swOrder.getField7Id());
        	
        	
        	map.put("url",  "/business/receive/swReceive/form?id="+swOrder.getField7Id());
        	SwReceive swReceive = swReceiveService.get(swOrder.getField7Id());
        	String html ="<tr><td style='text-align:center;width:25%'>开箱地点:</td><td style='text-align:center;width:25%'>"+swReceive.getLocation()+"</td>"
        			+ "<td style='text-align:center;width:25%'>验收时间:</td><td style='text-align:center;width:25%'>"+formatter.format(swOrder.getField7DateEnd())+"</td></tr>" 
        			+ "<tr><td style='text-align:center;width:25%'>技术协议一致性:</td><td style='text-align:center;width:25%'>一致</td>"
        				+	"<td style='text-align:center;width:25%'>外观一致性:</td><td style='text-align:center;width:25%'>一致</td></tr>"
                			+ "<tr><td style='text-align:center;width:25%'>型号一致性:</td><td style='text-align:center;width:25%'>一致</td>" 
                			+	"<td style='text-align:center;width:25%'>规格一致性:</td><td style='text-align:center;width:25%'>一致</td></tr>" ;
                			
        	map.put("html", html);
        	map.put("file" ,swReceive.getFile());
        	
		}
        if(!"0".equals(swOrder.getField7State())){
        	sworderList.add(map);
		}
        
        //到货款
        map  = new HashMap();
        if(payList!=null &&payList.size()>1){
        	swPay = payList.get(1);
			 
			 if("0".equals(swPay.getState())){
				 map.put("name", "到货款");
		         map.put("state", "2");
		         if(swPay.getProjectDate()!=null){
		        	 map.put("year", formatter.format(swPay.getProjectDate()).substring(0, 4));
			         map.put("day", formatter.format(swPay.getProjectDate()).substring(5, 10));
		         }
		         sworderList.add(map);
			 }else{
				 map.put("name", "到货款");
		         map.put("state", "3");
		         map.put("year", formatter.format(swPay.getCompletionTime()).substring(0, 4));
		         map.put("day", formatter.format(swPay.getCompletionTime()).substring(5, 10));
		         String html ="<tr><td style='text-align:center;width:25%'>合同总额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePrice()+"</td>"
	            			+ "<td style='text-align:center;width:25%'>本次付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContrateTreat()+"</td></tr>"
	            				+	"<tr><td style='text-align:center;width:25%'>已付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePaid()+"</td>"
	                    			+ "<td style='text-align:center;width:25%'>付款时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swPay.getCompletionTime())+"</td></tr>" ;
	            	map.put("html", html);
		         map.put("file", swPay.getAppendix());
		         sworderList.add(map);
			 }
        }
        
      //入库分发
        map  = new HashMap();
        if("1".equals(swOrder.getField8State()) || "2".equals(swOrder.getField8State())){
        	map.put("name", "入库分发");
        	map.put("state", swOrder.getField8State());
        	if(swOrder.getField8Date()!=null){
        		map.put("year", formatter.format(swOrder.getField8Date()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField8Date()).substring(5, 10));
        	}	
		}
        if("3".equals(swOrder.getField8State())){
        	map.put("name", "入库分发");
        	map.put("state", swOrder.getField8State());
        	 if(swPay.getProjectDate()!=null){
             	map.put("year", formatter.format(swOrder.getField8DateEnd()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField8DateEnd()).substring(5, 10));
        	 }
        	map.put("id",  swOrder.getField8Id());
        	map.put("url",  "/business/receive/swReceive/form?id="+swOrder.getField8Id());
        	
        	
		}
        if(!"0".equals(swOrder.getField8State())){
        	sworderList.add(map);
		}
        //验收单
        map  = new HashMap();
        if("1".equals(swOrder.getField9State()) || "2".equals(swOrder.getField9State())){
        	map.put("name", "验收单");
        	map.put("state", swOrder.getField9State());
        	if(swOrder.getField9Date()!=null){
        		map.put("year", formatter.format(swOrder.getField9Date()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField9Date()).substring(5, 10));
        	}	
		}
        if("3".equals(swOrder.getField9State())){
        	map.put("name", "验收单");
        	map.put("state", swOrder.getField9State());
        	 if(swPay.getProjectDate()!=null){
             	map.put("year", formatter.format(swOrder.getField9DateEnd()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField9DateEnd()).substring(5, 10));
        	 }
        	map.put("id",  swOrder.getField9Id());
        	map.put("url",  "/business/receive/swReceive/form?id="+swOrder.getField9Id());
        	
        	
		}
        if(!"0".equals(swOrder.getField9State())){
        	sworderList.add(map);
		}
        
      //尾款
        map  = new HashMap();
        if(payList!=null &&payList.size()>2){
        	swPay = payList.get(2);
			 
			 if("0".equals(swPay.getState())){
				 map.put("name", "尾款");
		         map.put("state", "2");
		         if(swPay.getProjectDate()!=null){
		        	 map.put("year", formatter.format(swPay.getProjectDate()).substring(0, 4));
			         map.put("day", formatter.format(swPay.getProjectDate()).substring(5, 10));
		         }
		         sworderList.add(map);
			 }else{
				 map.put("name", "尾款");
		         map.put("state", "3");
		         map.put("year", formatter.format(swPay.getCompletionTime()).substring(0, 4));
		         map.put("day", formatter.format(swPay.getCompletionTime()).substring(5, 10));
		         String html ="<tr><td style='text-align:center;width:25%'>合同总额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePrice()+"</td>"
	            			+ "<td style='text-align:center;width:25%'>本次付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContrateTreat()+"</td></tr>"
	            				+	"<tr><td style='text-align:center;width:25%'>已付款金额:</td><td style='text-align:center;;width:25%'>"+swPay.getContratePaid()+"</td>"
	                    			+ "<td style='text-align:center;width:25%'>付款时间:</td><td style='text-align:center;;width:25%'>"+formatter.format(swPay.getCompletionTime())+"</td></tr>" ;
	            	map.put("html", html);
		         map.put("file", swPay.getAppendix());
		         sworderList.add(map);
			 }
        }
        
        //报销
        map  = new HashMap();
        if("1".equals(swOrder.getField10State()) || "2".equals(swOrder.getField10State())){
        	map.put("name", "报销");
        	map.put("state", swOrder.getField10State());
        	if(swOrder.getField10Date()!=null){
        		map.put("year", formatter.format(swOrder.getField10Date()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField10Date()).substring(5, 10));
        	}	
		}
        if("3".equals(swOrder.getField10State())){
        	map.put("name", "验收单");
        	map.put("state", swOrder.getField10State());
        	 if(swPay.getProjectDate()!=null){
             	map.put("year", formatter.format(swOrder.getField10DateEnd()).substring(0, 4));
            	map.put("day", formatter.format(swOrder.getField10DateEnd()).substring(5, 10));
        	 }

        	map.put("id",  swOrder.getField10Id());
        	map.put("url",  "/business/receive/swReceive/form?id="+swOrder.getField10Id());
        	
        	
		}
        if(!"0".equals(swOrder.getField10State())){
        	sworderList.add(map);
		}
        
        
        model.addAttribute("sworderList", sworderList);
	
		return "modules/business/order/swOrderProgress";
	}
	
	
	

}