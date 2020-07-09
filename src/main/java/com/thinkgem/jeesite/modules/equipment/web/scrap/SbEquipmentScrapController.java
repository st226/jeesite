/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.scrap;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.scrap.SbEquipmentScrap;
import com.thinkgem.jeesite.modules.equipment.service.scrap.SbEquipmentScrapService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 设备仪器报废（闲置）申请Controller
 * @author suntao
 * @version 2020-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/scrap/sbEquipmentScrap")
public class SbEquipmentScrapController extends BaseController {

	@Autowired
	private SbEquipmentScrapService sbEquipmentScrapService;
	
	@ModelAttribute
	public SbEquipmentScrap get(@RequestParam(required=false) String id) {
		SbEquipmentScrap entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEquipmentScrapService.get(id);
		}
		if (entity == null){
			entity = new SbEquipmentScrap();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:scrap:sbEquipmentScrap:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEquipmentScrap sbEquipmentScrap, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbEquipmentScrap> page = sbEquipmentScrapService.findPage(new Page<SbEquipmentScrap>(request, response), sbEquipmentScrap); 
		model.addAttribute("page", page);
		return "modules/equipment/scrap/sbEquipmentScrapList";
	}

	@RequiresPermissions("equipment:scrap:sbEquipmentScrap:view")
	@RequestMapping(value = "form")
	public String form(SbEquipmentScrap sbEquipmentScrap, Model model) {
		if(sbEquipmentScrap.getId()==null || "".equals(sbEquipmentScrap.getId())){
			sbEquipmentScrap.setOffice(UserUtils.getUser().getOffice());
			sbEquipmentScrap.setOfficeName(UserUtils.getUser().getOffice().getName());
			sbEquipmentScrap.setCreateDate(new Date());
		}
		model.addAttribute("sbEquipmentScrap", sbEquipmentScrap);
		return "modules/equipment/scrap/sbEquipmentScrapForm";
	}
	

	@RequestMapping(value = "view")
	public String view(SbEquipmentScrap sbEquipmentScrap, Model model) {
		
		model.addAttribute("sbEquipmentScrap", sbEquipmentScrap);
		return "modules/equipment/scrap/sbEquipmentScrapView";
	}

	@RequiresPermissions("equipment:scrap:sbEquipmentScrap:edit")
	@RequestMapping(value = "save")
	public String save(SbEquipmentScrap sbEquipmentScrap, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEquipmentScrap)){
			return form(sbEquipmentScrap, model);
		}
		sbEquipmentScrapService.save(sbEquipmentScrap);
		addMessage(redirectAttributes, "保存设备仪器报废（闲置）申请成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/scrap/sbEquipmentScrap/?repage";
	}
	
	@RequiresPermissions("equipment:scrap:sbEquipmentScrap:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEquipmentScrap sbEquipmentScrap, RedirectAttributes redirectAttributes) {
		sbEquipmentScrapService.delete(sbEquipmentScrap);
		addMessage(redirectAttributes, "删除设备仪器报废（闲置）申请成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/scrap/sbEquipmentScrap/?repage";
	}

}