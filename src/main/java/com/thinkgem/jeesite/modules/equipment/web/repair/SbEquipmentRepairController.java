/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.repair;



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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.equipment.entity.repair.SbEquipmentRepair;
import com.thinkgem.jeesite.modules.equipment.service.repair.SbEquipmentRepairService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 设备维修申请Controller
 * @author suntao
 * @version 2020-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/repair/sbEquipmentRepair")
public class SbEquipmentRepairController extends BaseController {

	@Autowired
	private SbEquipmentRepairService sbEquipmentRepairService;
	
	@Autowired
	private SwOrderService swOrderService;
	
	@ModelAttribute
	public SbEquipmentRepair get(@RequestParam(required=false) String id) {
		SbEquipmentRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEquipmentRepairService.get(id);
		}
		if (entity == null){
			entity = new SbEquipmentRepair();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:repair:sbEquipmentRepair:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEquipmentRepair sbEquipmentRepair, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbEquipmentRepair> page = sbEquipmentRepairService.findPage(new Page<SbEquipmentRepair>(request, response), sbEquipmentRepair); 
		model.addAttribute("page", page);
		return "modules/equipment/repair/sbEquipmentRepairList";
	}

	@RequiresPermissions("equipment:repair:sbEquipmentRepair:view")
	@RequestMapping(value = "form")
	public String form(SbEquipmentRepair sbEquipmentRepair, Model model) {
		if(sbEquipmentRepair.getId()==null || "".equals(sbEquipmentRepair.getId())){
			sbEquipmentRepair.setState("0");
			sbEquipmentRepair.setUser(UserUtils.getUser());
			sbEquipmentRepair.setOffice(UserUtils.getUser().getOffice());
			sbEquipmentRepair.setOfficeName(UserUtils.getUser().getOffice().getName());
			sbEquipmentRepair.setUserName((UserUtils.getUser().getName()));
			sbEquipmentRepair.setUserPhone(UserUtils.getUser().getPhone());
			sbEquipmentRepair.setCreateDate(new Date());
		}
		model.addAttribute("sbEquipmentRepair", sbEquipmentRepair);
		return "modules/equipment/repair/sbEquipmentRepairForm";
	}
	

	@RequestMapping(value = "view")
	public String view(SbEquipmentRepair sbEquipmentRepair, Model model) {
		
		model.addAttribute("sbEquipmentRepair", sbEquipmentRepair);
		return "modules/equipment/repair/sbEquipmentRepairView";
	}

	@RequiresPermissions("equipment:repair:sbEquipmentRepair:edit")
	@RequestMapping(value = "save")
	public String save(SbEquipmentRepair sbEquipmentRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEquipmentRepair)){
			return form(sbEquipmentRepair, model);
		}
		if("1".equals(sbEquipmentRepair.getState())){
			SwOrder swOrder = new SwOrder();
			swOrder.setAmount(Double.parseDouble(sbEquipmentRepair.getEquipmentBudgt()));
			swOrder.setAmountYs(Double.parseDouble(sbEquipmentRepair.getEquipmentBudgt()));
			swOrder.setName(sbEquipmentRepair.getEquipmentName()+"维修");
			swOrder.setState("1");
			swOrder.setType("0");
			swOrder.setUser(UserUtils.getUser());
			swOrder.setUserName(UserUtils.getUser().getName());
			swOrder.setGmDate(new Date());
			swOrder.setIdentification("WX");
			swOrder.setField1State("2");
			swOrder.setField2State("1");
			swOrder.setField3State("1");
			swOrder.setField4State("1");
			swOrder.setField5State("1");
			swOrder.setField6State("1");
			swOrderService.save(swOrder);
			sbEquipmentRepair.setOrderId(swOrder.getId());
		}
		sbEquipmentRepairService.save(sbEquipmentRepair);
		addMessage(redirectAttributes, "保存设备维修申请成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/repair/sbEquipmentRepair/?repage";
	}
	
	@RequiresPermissions("equipment:repair:sbEquipmentRepair:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEquipmentRepair sbEquipmentRepair, RedirectAttributes redirectAttributes) {
		sbEquipmentRepairService.delete(sbEquipmentRepair);
		addMessage(redirectAttributes, "删除设备维修申请成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/repair/sbEquipmentRepair/?repage";
	}
	
	@RequestMapping(value = "getRepair")
    @ResponseBody
    public void getRepair(String orderId,HttpServletResponse response){
	 SbEquipmentRepair sbEquipmentRepair = new SbEquipmentRepair();
	 sbEquipmentRepair.setOrderId(orderId);
	 List<SbEquipmentRepair> list = sbEquipmentRepairService.findList(sbEquipmentRepair);
	 

     renderString(response, JsonMapper.toJsonString(list),"text/html");
    
    }

}