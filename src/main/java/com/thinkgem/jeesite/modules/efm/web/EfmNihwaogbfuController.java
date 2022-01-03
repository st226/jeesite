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
import com.thinkgem.jeesite.modules.efm.entity.EfmNihwaogbfu;
import com.thinkgem.jeesite.modules.efm.service.EfmNihwaogbfuService;

/**
 * 涉密载体归档审批表Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmNihwaogbfu")
public class EfmNihwaogbfuController extends BaseController {

	@Autowired
	private EfmNihwaogbfuService efmNihwaogbfuService;
	
	@ModelAttribute
	public EfmNihwaogbfu get(@RequestParam(required=false) String id) {
		EfmNihwaogbfu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmNihwaogbfuService.get(id);
		}
		if (entity == null){
			entity = new EfmNihwaogbfu();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmNihwaogbfu:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmNihwaogbfu efmNihwaogbfu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmNihwaogbfu> page = efmNihwaogbfuService.findPage(new Page<EfmNihwaogbfu>(request, response), efmNihwaogbfu); 
		model.addAttribute("page", page);
		return "modules/efm/efmNihwaogbfuList";
	}

	@RequiresPermissions("efm:efmNihwaogbfu:view")
	@RequestMapping(value = "form")
	public String form(EfmNihwaogbfu efmNihwaogbfu, Model model) {
		model.addAttribute("efmNihwaogbfu", efmNihwaogbfu);
		return "modules/efm/efmNihwaogbfuForm";
	}

	@RequiresPermissions("efm:efmNihwaogbfu:edit")
	@RequestMapping(value = "save")
	public String save(EfmNihwaogbfu efmNihwaogbfu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmNihwaogbfu)){
			return form(efmNihwaogbfu, model);
		}
		efmNihwaogbfuService.save(efmNihwaogbfu);
		addMessage(redirectAttributes, "保存涉密载体归档审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmNihwaogbfu/?repage";
	}
	
	@RequiresPermissions("efm:efmNihwaogbfu:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmNihwaogbfu efmNihwaogbfu, RedirectAttributes redirectAttributes) {
		efmNihwaogbfuService.delete(efmNihwaogbfu);
		addMessage(redirectAttributes, "删除涉密载体归档审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmNihwaogbfu/?repage";
	}

}