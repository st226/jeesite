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
import com.thinkgem.jeesite.modules.efm.entity.EfmIyuuy5h4h9;
import com.thinkgem.jeesite.modules.efm.service.EfmIyuuy5h4h9Service;

/**
 * 列表Controller
 * @author liang
 * @version 2021-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmIyuuy5h4h9")
public class EfmIyuuy5h4h9Controller extends BaseController {

	@Autowired
	private EfmIyuuy5h4h9Service efmIyuuy5h4h9Service;
	
	@ModelAttribute
	public EfmIyuuy5h4h9 get(@RequestParam(required=false) String id) {
		EfmIyuuy5h4h9 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmIyuuy5h4h9Service.get(id);
		}
		if (entity == null){
			entity = new EfmIyuuy5h4h9();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmIyuuy5h4h9:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmIyuuy5h4h9 efmIyuuy5h4h9, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmIyuuy5h4h9> page = efmIyuuy5h4h9Service.findPage(new Page<EfmIyuuy5h4h9>(request, response), efmIyuuy5h4h9); 
		model.addAttribute("page", page);
		return "modules/efm/efmIyuuy5h4h9List";
	}

	@RequiresPermissions("efm:efmIyuuy5h4h9:view")
	@RequestMapping(value = "form")
	public String form(EfmIyuuy5h4h9 efmIyuuy5h4h9, Model model) {
		model.addAttribute("efmIyuuy5h4h9", efmIyuuy5h4h9);
		return "modules/efm/efmIyuuy5h4h9Form";
	}

	@RequiresPermissions("efm:efmIyuuy5h4h9:edit")
	@RequestMapping(value = "save")
	public String save(EfmIyuuy5h4h9 efmIyuuy5h4h9, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmIyuuy5h4h9)){
			return form(efmIyuuy5h4h9, model);
		}
		efmIyuuy5h4h9Service.save(efmIyuuy5h4h9);
		addMessage(redirectAttributes, "保存列表保存成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmIyuuy5h4h9/?repage";
	}
	
	@RequiresPermissions("efm:efmIyuuy5h4h9:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmIyuuy5h4h9 efmIyuuy5h4h9, RedirectAttributes redirectAttributes) {
		efmIyuuy5h4h9Service.delete(efmIyuuy5h4h9);
		addMessage(redirectAttributes, "删除列表保存成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmIyuuy5h4h9/?repage";
	}

}