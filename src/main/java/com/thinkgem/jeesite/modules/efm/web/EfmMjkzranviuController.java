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
import com.thinkgem.jeesite.modules.efm.entity.EfmMjkzranviu;
import com.thinkgem.jeesite.modules.efm.service.EfmMjkzranviuService;

/**
 * 保密本领用申请Controller
 * @author l
 * @version 2021-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmMjkzranviu")
public class EfmMjkzranviuController extends BaseController {

	@Autowired
	private EfmMjkzranviuService efmMjkzranviuService;
	
	@ModelAttribute
	public EfmMjkzranviu get(@RequestParam(required=false) String id) {
		EfmMjkzranviu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmMjkzranviuService.get(id);
		}
		if (entity == null){
			entity = new EfmMjkzranviu();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmMjkzranviu:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmMjkzranviu efmMjkzranviu, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmMjkzranviu> page = efmMjkzranviuService.findPage(new Page<EfmMjkzranviu>(request, response), efmMjkzranviu); 
		model.addAttribute("page", page);
		return "modules/efm/efmMjkzranviuList";
	}

	@RequiresPermissions("efm:efmMjkzranviu:view")
	@RequestMapping(value = "form")
	public String form(EfmMjkzranviu efmMjkzranviu, Model model) {
		model.addAttribute("efmMjkzranviu", efmMjkzranviu);
		return "modules/efm/efmMjkzranviuForm";
	}

	@RequiresPermissions("efm:efmMjkzranviu:edit")
	@RequestMapping(value = "save")
	public String save(EfmMjkzranviu efmMjkzranviu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmMjkzranviu)){
			return form(efmMjkzranviu, model);
		}
		efmMjkzranviuService.save(efmMjkzranviu);
		addMessage(redirectAttributes, "保存保密本领用申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmMjkzranviu/?repage";
	}
	
	@RequiresPermissions("efm:efmMjkzranviu:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmMjkzranviu efmMjkzranviu, RedirectAttributes redirectAttributes) {
		efmMjkzranviuService.delete(efmMjkzranviu);
		addMessage(redirectAttributes, "删除保密本领用申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmMjkzranviu/?repage";
	}

}