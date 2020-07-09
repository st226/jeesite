/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.externalunit.web;

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
import com.thinkgem.jeesite.modules.externalunit.entity.SysExternalUnit;
import com.thinkgem.jeesite.modules.externalunit.service.SysExternalUnitService;

/**
 * 外单位信息维护Controller
 * @author 孙涛
 * @version 2020-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/externalunit/sysExternalUnit")
public class SysExternalUnitController extends BaseController {

	@Autowired
	private SysExternalUnitService sysExternalUnitService;
	
	@ModelAttribute
	public SysExternalUnit get(@RequestParam(required=false) String id) {
		SysExternalUnit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysExternalUnitService.get(id);
		}
		if (entity == null){
			entity = new SysExternalUnit();
		}
		return entity;
	}
	
	@RequiresPermissions("externalunit:sysExternalUnit:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysExternalUnit sysExternalUnit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysExternalUnit> page = sysExternalUnitService.findPage(new Page<SysExternalUnit>(request, response), sysExternalUnit); 
		model.addAttribute("page", page);
		return "modules/externalunit/sysExternalUnitList";
	}

	@RequiresPermissions("externalunit:sysExternalUnit:view")
	@RequestMapping(value = "form")
	public String form(SysExternalUnit sysExternalUnit, Model model) {
		model.addAttribute("sysExternalUnit", sysExternalUnit);
		return "modules/externalunit/sysExternalUnitForm";
	}

	@RequiresPermissions("externalunit:sysExternalUnit:edit")
	@RequestMapping(value = "save")
	public String save(SysExternalUnit sysExternalUnit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysExternalUnit)){
			return form(sysExternalUnit, model);
		}
		sysExternalUnitService.save(sysExternalUnit);
		addMessage(redirectAttributes, "保存外单位信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/externalunit/sysExternalUnit/?repage";
	}
	
	@RequiresPermissions("externalunit:sysExternalUnit:edit")
	@RequestMapping(value = "delete")
	public String delete(SysExternalUnit sysExternalUnit, RedirectAttributes redirectAttributes) {
		sysExternalUnitService.delete(sysExternalUnit);
		addMessage(redirectAttributes, "删除外单位信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/externalunit/sysExternalUnit/?repage";
	}

}