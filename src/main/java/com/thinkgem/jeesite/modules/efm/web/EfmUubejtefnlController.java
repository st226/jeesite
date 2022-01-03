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
import com.thinkgem.jeesite.modules.efm.entity.EfmUubejtefnl;
import com.thinkgem.jeesite.modules.efm.service.EfmUubejtefnlService;

/**
 * 涉密载体销毁回收管理Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmUubejtefnl")
public class EfmUubejtefnlController extends BaseController {

	@Autowired
	private EfmUubejtefnlService efmUubejtefnlService;
	
	@ModelAttribute
	public EfmUubejtefnl get(@RequestParam(required=false) String id) {
		EfmUubejtefnl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmUubejtefnlService.get(id);
		}
		if (entity == null){
			entity = new EfmUubejtefnl();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmUubejtefnl:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmUubejtefnl efmUubejtefnl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmUubejtefnl> page = efmUubejtefnlService.findPage(new Page<EfmUubejtefnl>(request, response), efmUubejtefnl); 
		model.addAttribute("page", page);
		return "modules/efm/efmUubejtefnlList";
	}

	@RequiresPermissions("efm:efmUubejtefnl:view")
	@RequestMapping(value = "form")
	public String form(EfmUubejtefnl efmUubejtefnl, Model model) {
		model.addAttribute("efmUubejtefnl", efmUubejtefnl);
		return "modules/efm/efmUubejtefnlForm";
	}

	@RequiresPermissions("efm:efmUubejtefnl:edit")
	@RequestMapping(value = "save")
	public String save(EfmUubejtefnl efmUubejtefnl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmUubejtefnl)){
			return form(efmUubejtefnl, model);
		}
		efmUubejtefnlService.save(efmUubejtefnl);
		addMessage(redirectAttributes, "保存涉密载体销毁回收管理成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmUubejtefnl/?repage";
	}
	
	@RequiresPermissions("efm:efmUubejtefnl:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmUubejtefnl efmUubejtefnl, RedirectAttributes redirectAttributes) {
		efmUubejtefnlService.delete(efmUubejtefnl);
		addMessage(redirectAttributes, "删除涉密载体销毁回收管理成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmUubejtefnl/?repage";
	}

}