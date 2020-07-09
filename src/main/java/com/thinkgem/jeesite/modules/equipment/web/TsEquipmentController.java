/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web;

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
import com.thinkgem.jeesite.modules.equipment.entity.TsEquipment;
import com.thinkgem.jeesite.modules.equipment.service.TsEquipmentService;

/**
 * 试验数据Controller
 * @author suntao
 * @version 2018-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/tsEquipment")
public class TsEquipmentController extends BaseController {

	@Autowired
	private TsEquipmentService tsEquipmentService;
	
	@ModelAttribute
	public TsEquipment get(@RequestParam(required=false) String id) {
		TsEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsEquipmentService.get(id);
		}
		if (entity == null){
			entity = new TsEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:tsEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsEquipment tsEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsEquipment> page = tsEquipmentService.findPage(new Page<TsEquipment>(request, response), tsEquipment); 
		model.addAttribute("page", page);
		return "modules/equipment/tsEquipmentList";
	}

	@RequiresPermissions("equipment:tsEquipment:view")
	@RequestMapping(value = "form")
	public String form(TsEquipment tsEquipment, Model model) {
		model.addAttribute("tsEquipment", tsEquipment);
		return "modules/equipment/tsEquipmentForm";
	}

	@RequiresPermissions("equipment:tsEquipment:edit")
	@RequestMapping(value = "save")
	public String save(TsEquipment tsEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsEquipment)){
			return form(tsEquipment, model);
		}
		tsEquipmentService.save(tsEquipment);
		addMessage(redirectAttributes, "保存试验数据成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/tsEquipment/?repage";
	}
	
	@RequiresPermissions("equipment:tsEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(TsEquipment tsEquipment, RedirectAttributes redirectAttributes) {
		tsEquipmentService.delete(tsEquipment);
		addMessage(redirectAttributes, "删除试验数据成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/tsEquipment/?repage";
	}

}