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
import com.thinkgem.jeesite.modules.efm.entity.EfmHpby2oimag;
import com.thinkgem.jeesite.modules.efm.service.EfmHpby2oimagService;

/**
 * 涉密载体回收审批表Controller
 * @author l
 * @version 2021-11-27
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmHpby2oimag")
public class EfmHpby2oimagController extends BaseController {

	@Autowired
	private EfmHpby2oimagService efmHpby2oimagService;
	
	@ModelAttribute
	public EfmHpby2oimag get(@RequestParam(required=false) String id) {
		EfmHpby2oimag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmHpby2oimagService.get(id);
		}
		if (entity == null){
			entity = new EfmHpby2oimag();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmHpby2oimag:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmHpby2oimag efmHpby2oimag, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmHpby2oimag> page = efmHpby2oimagService.findPage(new Page<EfmHpby2oimag>(request, response), efmHpby2oimag); 
		model.addAttribute("page", page);
		return "modules/efm/efmHpby2oimagList";
	}

	@RequiresPermissions("efm:efmHpby2oimag:view")
	@RequestMapping(value = "form")
	public String form(EfmHpby2oimag efmHpby2oimag, Model model) {
		model.addAttribute("efmHpby2oimag", efmHpby2oimag);
		return "modules/efm/efmHpby2oimagForm";
	}

	@RequiresPermissions("efm:efmHpby2oimag:edit")
	@RequestMapping(value = "save")
	public String save(EfmHpby2oimag efmHpby2oimag, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmHpby2oimag)){
			return form(efmHpby2oimag, model);
		}
		efmHpby2oimagService.save(efmHpby2oimag);
		addMessage(redirectAttributes, "保存涉密载体回收审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmHpby2oimag/?repage";
	}
	
	@RequiresPermissions("efm:efmHpby2oimag:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmHpby2oimag efmHpby2oimag, RedirectAttributes redirectAttributes) {
		efmHpby2oimagService.delete(efmHpby2oimag);
		addMessage(redirectAttributes, "删除涉密载体回收审批表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmHpby2oimag/?repage";
	}

}