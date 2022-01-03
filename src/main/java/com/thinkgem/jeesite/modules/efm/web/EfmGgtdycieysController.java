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
import com.thinkgem.jeesite.modules.efm.entity.EfmGgtdycieys;
import com.thinkgem.jeesite.modules.efm.service.EfmGgtdycieysService;

/**
 * 载体复印申请Controller
 * @author l
 * @version 2021-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmGgtdycieys")
public class EfmGgtdycieysController extends BaseController {

	@Autowired
	private EfmGgtdycieysService efmGgtdycieysService;
	
	@ModelAttribute
	public EfmGgtdycieys get(@RequestParam(required=false) String id) {
		EfmGgtdycieys entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmGgtdycieysService.get(id);
		}
		if (entity == null){
			entity = new EfmGgtdycieys();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmGgtdycieys:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmGgtdycieys efmGgtdycieys, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmGgtdycieys> page = efmGgtdycieysService.findPage(new Page<EfmGgtdycieys>(request, response), efmGgtdycieys); 
		model.addAttribute("page", page);
		return "modules/efm/efmGgtdycieysList";
	}

	@RequiresPermissions("efm:efmGgtdycieys:view")
	@RequestMapping(value = "form")
	public String form(EfmGgtdycieys efmGgtdycieys, Model model) {
		model.addAttribute("efmGgtdycieys", efmGgtdycieys);
		return "modules/efm/efmGgtdycieysForm";
	}

	@RequiresPermissions("efm:efmGgtdycieys:edit")
	@RequestMapping(value = "save")
	public String save(EfmGgtdycieys efmGgtdycieys, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmGgtdycieys)){
			return form(efmGgtdycieys, model);
		}
		efmGgtdycieysService.save(efmGgtdycieys);
		addMessage(redirectAttributes, "保存载体复印申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmGgtdycieys/?repage";
	}
	
	@RequiresPermissions("efm:efmGgtdycieys:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmGgtdycieys efmGgtdycieys, RedirectAttributes redirectAttributes) {
		efmGgtdycieysService.delete(efmGgtdycieys);
		addMessage(redirectAttributes, "删除载体复印申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmGgtdycieys/?repage";
	}

}