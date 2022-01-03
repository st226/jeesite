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
import com.thinkgem.jeesite.modules.efm.entity.EfmJmaezctiei;
import com.thinkgem.jeesite.modules.efm.service.EfmJmaezctieiService;

/**
 * 台账Controller
 * @author liang
 * @version 2021-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmJmaezctiei")
public class EfmJmaezctieiController extends BaseController {

	@Autowired
	private EfmJmaezctieiService efmJmaezctieiService;
	
	@ModelAttribute
	public EfmJmaezctiei get(@RequestParam(required=false) String id) {
		EfmJmaezctiei entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmJmaezctieiService.get(id);
		}
		if (entity == null){
			entity = new EfmJmaezctiei();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmJmaezctiei:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmJmaezctiei efmJmaezctiei, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmJmaezctiei> page = efmJmaezctieiService.findPage(new Page<EfmJmaezctiei>(request, response), efmJmaezctiei); 
		model.addAttribute("page", page);
		return "modules/efm/efmJmaezctieiList";
	}

	@RequiresPermissions("efm:efmJmaezctiei:view")
	@RequestMapping(value = "form")
	public String form(EfmJmaezctiei efmJmaezctiei, Model model) {
		model.addAttribute("efmJmaezctiei", efmJmaezctiei);
		return "modules/efm/efmJmaezctieiForm";
	}

	@RequiresPermissions("efm:efmJmaezctiei:edit")
	@RequestMapping(value = "save")
	public String save(EfmJmaezctiei efmJmaezctiei, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmJmaezctiei)){
			return form(efmJmaezctiei, model);
		}
		efmJmaezctieiService.save(efmJmaezctiei);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmJmaezctiei/?repage";
	}
	
	@RequiresPermissions("efm:efmJmaezctiei:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmJmaezctiei efmJmaezctiei, RedirectAttributes redirectAttributes) {
		efmJmaezctieiService.delete(efmJmaezctiei);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmJmaezctiei/?repage";
	}

}