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
import com.thinkgem.jeesite.modules.efm.entity.EfmPdlg1he6zd;
import com.thinkgem.jeesite.modules.efm.service.EfmPdlg1he6zdService;

/**
 * 外来载体入账登记申请Controller
 * @author l
 * @version 2021-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmPdlg1he6zd")
public class EfmPdlg1he6zdController extends BaseController {

	@Autowired
	private EfmPdlg1he6zdService efmPdlg1he6zdService;
	
	@ModelAttribute
	public EfmPdlg1he6zd get(@RequestParam(required=false) String id) {
		EfmPdlg1he6zd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmPdlg1he6zdService.get(id);
		}
		if (entity == null){
			entity = new EfmPdlg1he6zd();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmPdlg1he6zd:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmPdlg1he6zd efmPdlg1he6zd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmPdlg1he6zd> page = efmPdlg1he6zdService.findPage(new Page<EfmPdlg1he6zd>(request, response), efmPdlg1he6zd); 
		model.addAttribute("page", page);
		return "modules/efm/efmPdlg1he6zdList";
	}

	@RequiresPermissions("efm:efmPdlg1he6zd:view")
	@RequestMapping(value = "form")
	public String form(EfmPdlg1he6zd efmPdlg1he6zd, Model model) {
		model.addAttribute("efmPdlg1he6zd", efmPdlg1he6zd);
		return "modules/efm/efmPdlg1he6zdForm";
	}

	@RequiresPermissions("efm:efmPdlg1he6zd:edit")
	@RequestMapping(value = "save")
	public String save(EfmPdlg1he6zd efmPdlg1he6zd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmPdlg1he6zd)){
			return form(efmPdlg1he6zd, model);
		}
		efmPdlg1he6zdService.save(efmPdlg1he6zd);
		addMessage(redirectAttributes, "保存外来载体入账登记申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmPdlg1he6zd/?repage";
	}
	
	@RequiresPermissions("efm:efmPdlg1he6zd:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmPdlg1he6zd efmPdlg1he6zd, RedirectAttributes redirectAttributes) {
		efmPdlg1he6zdService.delete(efmPdlg1he6zd);
		addMessage(redirectAttributes, "删除外来载体入账登记申请成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmPdlg1he6zd/?repage";
	}

}