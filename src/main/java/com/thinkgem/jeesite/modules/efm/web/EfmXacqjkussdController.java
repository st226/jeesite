/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.efm.web;

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
import com.thinkgem.jeesite.modules.efm.entity.EfmXacqjkussd;
import com.thinkgem.jeesite.modules.efm.service.EfmXacqjkussdService;

/**
 * 涉密载体对外移交审批表Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmXacqjkussd")
public class EfmXacqjkussdController extends BaseController {

	@Autowired
	private EfmXacqjkussdService efmXacqjkussdService;
	
	@ModelAttribute
	public EfmXacqjkussd get(@RequestParam(required=false) String id) {
		EfmXacqjkussd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmXacqjkussdService.get(id);
		}
		if (entity == null){
			entity = new EfmXacqjkussd();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmXacqjkussd:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmXacqjkussd efmXacqjkussd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmXacqjkussd> page = efmXacqjkussdService.findPage(new Page<EfmXacqjkussd>(request, response), efmXacqjkussd); 
		model.addAttribute("page", page);
		return "modules/efm/efmXacqjkussdList";
	}

	@RequiresPermissions("efm:efmXacqjkussd:view")
	@RequestMapping(value = "form")
	public String form(EfmXacqjkussd efmXacqjkussd, Model model) {
		model.addAttribute("efmXacqjkussd", efmXacqjkussd);
		return "modules/efm/efmXacqjkussdForm";
	}

	@RequiresPermissions("efm:efmXacqjkussd:edit")
	@RequestMapping(value = "save")
	public String save(EfmXacqjkussd efmXacqjkussd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmXacqjkussd)){
			return form(efmXacqjkussd, model);
		}
		efmXacqjkussdService.save(efmXacqjkussd);
		addMessage(redirectAttributes, "保存涉密载体对外移交审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmXacqjkussd/?repage";
	}
	
	@RequiresPermissions("efm:efmXacqjkussd:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmXacqjkussd efmXacqjkussd, RedirectAttributes redirectAttributes) {
		efmXacqjkussdService.delete(efmXacqjkussd);
		addMessage(redirectAttributes, "删除涉密载体对外移交审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmXacqjkussd/?repage";
	}

}