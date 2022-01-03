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
import com.thinkgem.jeesite.modules.efm.entity.EfmJueltygvaw;
import com.thinkgem.jeesite.modules.efm.service.EfmJueltygvawService;

/**
 * 单表生成Controller
 * @author liang
 * @version 2021-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmJueltygvaw")
public class EfmJueltygvawController extends BaseController {

	@Autowired
	private EfmJueltygvawService efmJueltygvawService;
	
	@ModelAttribute
	public EfmJueltygvaw get(@RequestParam(required=false) String id) {
		EfmJueltygvaw entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmJueltygvawService.get(id);
		}
		if (entity == null){
			entity = new EfmJueltygvaw();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmJueltygvaw:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmJueltygvaw efmJueltygvaw, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmJueltygvaw> page = efmJueltygvawService.findPage(new Page<EfmJueltygvaw>(request, response), efmJueltygvaw); 
		model.addAttribute("page", page);
		return "modules/efm/efmJueltygvawList";
	}

	@RequiresPermissions("efm:efmJueltygvaw:view")
	@RequestMapping(value = "form")
	public String form(EfmJueltygvaw efmJueltygvaw, Model model) {
		model.addAttribute("efmJueltygvaw", efmJueltygvaw);
		return "modules/efm/efmJueltygvawForm";
	}

	@RequiresPermissions("efm:efmJueltygvaw:edit")
	@RequestMapping(value = "save")
	public String save(EfmJueltygvaw efmJueltygvaw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmJueltygvaw)){
			return form(efmJueltygvaw, model);
		}
		efmJueltygvawService.save(efmJueltygvaw);
		addMessage(redirectAttributes, "保存国家秘密事项脱密处理审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmJueltygvaw/?repage";
	}
	
	@RequiresPermissions("efm:efmJueltygvaw:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmJueltygvaw efmJueltygvaw, RedirectAttributes redirectAttributes) {
		efmJueltygvawService.delete(efmJueltygvaw);
		addMessage(redirectAttributes, "删除国家秘密事项脱密处理审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmJueltygvaw/?repage";
	}

}