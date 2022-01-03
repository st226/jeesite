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
import com.thinkgem.jeesite.modules.efm.entity.EfmSmycmp3wjm;
import com.thinkgem.jeesite.modules.efm.service.EfmSmycmp3wjmService;

/**
 * 涉密载体流转审批表Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmSmycmp3wjm")
public class EfmSmycmp3wjmController extends BaseController {

	@Autowired
	private EfmSmycmp3wjmService efmSmycmp3wjmService;
	
	@ModelAttribute
	public EfmSmycmp3wjm get(@RequestParam(required=false) String id) {
		EfmSmycmp3wjm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmSmycmp3wjmService.get(id);
		}
		if (entity == null){
			entity = new EfmSmycmp3wjm();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmSmycmp3wjm:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmSmycmp3wjm efmSmycmp3wjm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmSmycmp3wjm> page = efmSmycmp3wjmService.findPage(new Page<EfmSmycmp3wjm>(request, response), efmSmycmp3wjm); 
		model.addAttribute("page", page);
		return "modules/efm/efmSmycmp3wjmList";
	}

	@RequiresPermissions("efm:efmSmycmp3wjm:view")
	@RequestMapping(value = "form")
	public String form(EfmSmycmp3wjm efmSmycmp3wjm, Model model) {
		model.addAttribute("efmSmycmp3wjm", efmSmycmp3wjm);
		return "modules/efm/efmSmycmp3wjmForm";
	}

	@RequiresPermissions("efm:efmSmycmp3wjm:edit")
	@RequestMapping(value = "save")
	public String save(EfmSmycmp3wjm efmSmycmp3wjm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmSmycmp3wjm)){
			return form(efmSmycmp3wjm, model);
		}
		efmSmycmp3wjmService.save(efmSmycmp3wjm);
		addMessage(redirectAttributes, "保存涉密载体流转审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmSmycmp3wjm/?repage";
	}
	
	@RequiresPermissions("efm:efmSmycmp3wjm:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmSmycmp3wjm efmSmycmp3wjm, RedirectAttributes redirectAttributes) {
		efmSmycmp3wjmService.delete(efmSmycmp3wjm);
		addMessage(redirectAttributes, "删除涉密载体流转审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmSmycmp3wjm/?repage";
	}

}