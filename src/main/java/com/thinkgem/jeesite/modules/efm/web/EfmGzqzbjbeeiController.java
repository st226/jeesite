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
import com.thinkgem.jeesite.modules.efm.entity.EfmGzqzbjbeei;
import com.thinkgem.jeesite.modules.efm.service.EfmGzqzbjbeeiService;

/**
 * 涉密载体送外印制申请Controller
 * @author l
 * @version 2021-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmGzqzbjbeei")
public class EfmGzqzbjbeeiController extends BaseController {

	@Autowired
	private EfmGzqzbjbeeiService efmGzqzbjbeeiService;
	
	@ModelAttribute
	public EfmGzqzbjbeei get(@RequestParam(required=false) String id) {
		EfmGzqzbjbeei entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmGzqzbjbeeiService.get(id);
		}
		if (entity == null){
			entity = new EfmGzqzbjbeei();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmGzqzbjbeei:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmGzqzbjbeei efmGzqzbjbeei, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmGzqzbjbeei> page = efmGzqzbjbeeiService.findPage(new Page<EfmGzqzbjbeei>(request, response), efmGzqzbjbeei); 
		model.addAttribute("page", page);
		return "modules/efm/efmGzqzbjbeeiList";
	}

	@RequiresPermissions("efm:efmGzqzbjbeei:view")
	@RequestMapping(value = "form")
	public String form(EfmGzqzbjbeei efmGzqzbjbeei, Model model) {
		model.addAttribute("efmGzqzbjbeei", efmGzqzbjbeei);
		return "modules/efm/efmGzqzbjbeeiForm";
	}

	@RequiresPermissions("efm:efmGzqzbjbeei:edit")
	@RequestMapping(value = "save")
	public String save(EfmGzqzbjbeei efmGzqzbjbeei, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmGzqzbjbeei)){
			return form(efmGzqzbjbeei, model);
		}
		efmGzqzbjbeeiService.save(efmGzqzbjbeei);
		addMessage(redirectAttributes, "保存涉密载体送外印制申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmGzqzbjbeei/?repage";
	}
	
	@RequiresPermissions("efm:efmGzqzbjbeei:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmGzqzbjbeei efmGzqzbjbeei, RedirectAttributes redirectAttributes) {
		efmGzqzbjbeeiService.delete(efmGzqzbjbeei);
		addMessage(redirectAttributes, "删除涉密载体送外印制申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmGzqzbjbeei/?repage";
	}

}