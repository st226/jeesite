/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.equipment;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;

/**
 * 仪器设备Controller
 * @author suntao
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/equipment/sbEquipment")
public class SbEquipmentController extends BaseController {

	@Autowired
	private SbEquipmentService sbEquipmentService;
	
	@ModelAttribute
	public SbEquipment get(@RequestParam(required=false) String id) {
		SbEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEquipmentService.get(id);
		}
		if (entity == null){
			entity = new SbEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:equipment:sbEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbEquipment> page = sbEquipmentService.findPage(new Page<SbEquipment>(request, response), sbEquipment); 
		model.addAttribute("page", page);
		return "modules/equipment/equipment/sbEquipmentList";
	}

	@RequiresPermissions("equipment:equipment:sbEquipment:view")
	@RequestMapping(value = "form")
	public String form(SbEquipment sbEquipment, Model model) {
		model.addAttribute("sbEquipment", sbEquipment);
		return "modules/equipment/equipment/sbEquipmentForm";
	}

	@RequiresPermissions("equipment:equipment:sbEquipment:edit")
	@RequestMapping(value = "save")
	public String save(SbEquipment sbEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEquipment)){
			return form(sbEquipment, model);
		}
		sbEquipmentService.save(sbEquipment);
		addMessage(redirectAttributes, "保存仪器设备成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipment/sbEquipment/?repage";
	}
	
	@RequestMapping(value = "updateField20")
    @ResponseBody
	public void updateField20(SbEquipment sbEquipment, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SbEquipment temp = sbEquipmentService.get(sbEquipment.getId());
		temp.setField20(sbEquipment.getField20());
		sbEquipmentService.save(temp);
		addMessage(redirectAttributes, "操作成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequiresPermissions("equipment:equipment:sbEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEquipment sbEquipment, RedirectAttributes redirectAttributes) {
		sbEquipmentService.delete(sbEquipment);
		addMessage(redirectAttributes, "删除仪器设备成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipment/sbEquipment/?repage";
	}

}