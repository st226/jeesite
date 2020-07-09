/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.order.entity.TsOrder;
import com.thinkgem.jeesite.modules.order.service.TsOrderService;
import com.thinkgem.jeesite.modules.purchase.entity.TsPurchase;
import com.thinkgem.jeesite.modules.purchase.service.TsPurchaseService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.supplier.entity.TsSupplier;
import com.thinkgem.jeesite.modules.supplier.service.TsSupplierService;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 订单管理Controller
 * @author suntao
 * @version 2018-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/order/tsOrder")
public class TsOrderController extends BaseController {

	@Autowired
	private TsOrderService tsOrderService;
	
	@Autowired
	private TsPurchaseService tsPurchaseService ;
	
	
	@Autowired
	private TsSupplierService tsSupplierService;
	
	@ModelAttribute
	public TsOrder get(@RequestParam(required=false) String id) {
		TsOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsOrderService.get(id);
		}
		if (entity == null){
			entity = new TsOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:tsOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsOrder tsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsOrder> page = tsOrderService.findPage(new Page<TsOrder>(request, response), tsOrder); 
		model.addAttribute("page", page);
		return "modules/order/tsOrderList";
	}

	@RequiresPermissions("order:tsOrder:view")
	@RequestMapping(value = "form")
	public String form(TsOrder tsOrder, Model model) {
		model.addAttribute("tsOrder", tsOrder);
		TsSupplier tsSupplier = new TsSupplier();
		tsSupplier.setGysState("1");
		model.addAttribute("tsSupplier", tsSupplierService.findList(tsSupplier));  
		return "modules/order/tsOrderForm";
	}
	
	@RequestMapping(value = "deploy")
	public String deploy(TsOrder tsOrder,  HttpServletRequest request, HttpServletResponse response, Model model) { 
		tsOrder = tsOrderService.get(tsOrder.getId()) ;
		TsPurchase tsPurchase = new TsPurchase();
		tsPurchase.setZyState("1");
		List<TsPurchase> list = tsPurchaseService.findList(tsPurchase) ;
		tsOrder.setColumnList(list);
		model.addAttribute("tsOrder", tsOrder);
		model.addAttribute("orderId",tsOrder.getId());
        model.addAttribute("config", GenUtils.getConfig());
		return "modules/order/tsOrderDeploy";
	}
	
	
	 @RequestMapping(value = "getSelectColumnData")
	    @ResponseBody
	    public void getSelectColumnData(String orderId,HttpServletResponse response){
		    TsPurchase tsPurchase = new  TsPurchase();
		    tsPurchase.setOrderId(orderId);
	        List<TsPurchase> dataList= tsPurchaseService.getTsPurchaseByOrder(tsPurchase);
	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	
	
	@RequestMapping(value = "orderDeploySave")
    //@ResponseBody
    public String orderDeploySave(TsOrder tsOrder, RedirectAttributes redirectAttributes) {
		tsPurchaseService.saveTsPurchaseOrder(tsOrder.getColumnList(), tsOrder.getId());
		addMessage(redirectAttributes, "订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/tsOrder/?repage";
    }
	
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("order:tsOrder:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(TsOrder tsOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		TsPurchase tsPurchase = new  TsPurchase();
	    tsPurchase.setOrderId(tsOrder.getId());
	    tsOrder =  tsOrderService.get(tsOrder.getId()) ;
	    
        List<TsPurchase> dataList= tsPurchaseService.getTsPurchaseByOrder(tsPurchase);
		try {
            String fileName = "订单信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("订单信息("+tsOrder.getName()+")", TsPurchase.class).setDataList(dataList).write(response, fileName).dispose();
    		tsOrder.setOrderState("2");
    		tsOrderService.save(tsOrder);
    		for(int i=0 ;i<dataList.size();i++){
    			tsPurchase = dataList.get(i) ;
    			tsPurchase = tsPurchaseService.get(tsPurchase.getId());
    			tsPurchase.setZyState("2");
    			tsPurchaseService.save(tsPurchase);
    					
    		}
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出采购单失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/order/tsOrder/list?repage";
    }
	
	
	@RequiresPermissions("order:tsOrder:edit")
	@RequestMapping(value = "success")
    public String successFile(TsOrder tsOrder, Model model, RedirectAttributes redirectAttributes) {
		TsPurchase tsPurchase = new  TsPurchase();
	    tsPurchase.setOrderId(tsOrder.getId());
	    tsOrder =  tsOrderService.get(tsOrder.getId()) ;
        List<TsPurchase> dataList= tsPurchaseService.getTsPurchaseByOrder(tsPurchase);
		try {
             tsOrder.setOrderState("3");
    		tsOrderService.save(tsOrder);
    		for(int i=0 ;i<dataList.size();i++){
    			tsPurchase = dataList.get(i) ;
    			tsPurchase = tsPurchaseService.get(tsPurchase.getId());
    			tsPurchase.setZyState("3");
    			tsPurchaseService.save(tsPurchase);
    					
    		}
    	
		} catch (Exception e) {
			addMessage(redirectAttributes, "变更失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/order/tsOrder/?repage";
    }
	

	@RequiresPermissions("order:tsOrder:edit")
	@RequestMapping(value = "save")
	public String save(TsOrder tsOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsOrder)){
			return form(tsOrder, model);
		}
		tsOrderService.save(tsOrder);
		addMessage(redirectAttributes, "保存订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/tsOrder/?repage";
	}
	
	@RequiresPermissions("order:tsOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(TsOrder tsOrder, RedirectAttributes redirectAttributes) {
		tsOrderService.delete(tsOrder);
		addMessage(redirectAttributes, "删除订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/order/tsOrder/?repage";
	}

}