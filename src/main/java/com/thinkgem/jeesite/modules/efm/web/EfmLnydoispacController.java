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
import com.thinkgem.jeesite.modules.efm.entity.EfmLnydoispac;
import com.thinkgem.jeesite.modules.efm.service.EfmLnydoispacService;

/**
 * 涉密载体归档管理Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmLnydoispac")
public class EfmLnydoispacController extends BaseController {

	@Autowired
	private EfmLnydoispacService efmLnydoispacService;
	
	@ModelAttribute
	public EfmLnydoispac get(@RequestParam(required=false) String id) {
		EfmLnydoispac entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmLnydoispacService.get(id);
		}
		if (entity == null){
			entity = new EfmLnydoispac();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmLnydoispac:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmLnydoispac efmLnydoispac, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmLnydoispac> page = efmLnydoispacService.findPage(new Page<EfmLnydoispac>(request, response), efmLnydoispac); 
		model.addAttribute("page", page);
		return "modules/efm/efmLnydoispacList";
	}

	@RequiresPermissions("efm:efmLnydoispac:view")
	@RequestMapping(value = "form")
	public String form(EfmLnydoispac efmLnydoispac, Model model) {
		model.addAttribute("efmLnydoispac", efmLnydoispac);
		return "modules/efm/efmLnydoispacForm";
	}

	@RequiresPermissions("efm:efmLnydoispac:edit")
	@RequestMapping(value = "save")
	public String save(EfmLnydoispac efmLnydoispac, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmLnydoispac)){
			return form(efmLnydoispac, model);
		}
		efmLnydoispacService.save(efmLnydoispac);
		addMessage(redirectAttributes, "保存涉密载体归档管理成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmLnydoispac/?repage";
	}
	
	@RequiresPermissions("efm:efmLnydoispac:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmLnydoispac efmLnydoispac, RedirectAttributes redirectAttributes) {
		efmLnydoispacService.delete(efmLnydoispac);
		addMessage(redirectAttributes, "删除涉密载体归档管理成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmLnydoispac/?repage";
	}

}