/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.web;

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
import com.thinkgem.jeesite.modules.supplier.entity.TsSupplier;
import com.thinkgem.jeesite.modules.supplier.service.TsSupplierService;

/**
 * 供应商管理Controller
 * @author suntao
 * @version 2018-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/tsSupplier")
public class TsSupplierController extends BaseController {

	@Autowired
	private TsSupplierService tsSupplierService;
	
	@ModelAttribute
	public TsSupplier get(@RequestParam(required=false) String id) {
		TsSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsSupplierService.get(id);
		}
		if (entity == null){
			entity = new TsSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("supplier:tsSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsSupplier tsSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsSupplier> page = tsSupplierService.findPage(new Page<TsSupplier>(request, response), tsSupplier); 
		model.addAttribute("page", page);
		return "modules/supplier/tsSupplierList";
	}

	@RequiresPermissions("supplier:tsSupplier:view")
	@RequestMapping(value = "form")
	public String form(TsSupplier tsSupplier, Model model) {
		model.addAttribute("tsSupplier", tsSupplier);
		return "modules/supplier/tsSupplierForm";
	}

	@RequiresPermissions("supplier:tsSupplier:edit")
	@RequestMapping(value = "save")
	public String save(TsSupplier tsSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsSupplier)){
			return form(tsSupplier, model);
		}
		tsSupplierService.save(tsSupplier);
		addMessage(redirectAttributes, "保存供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/tsSupplier/?repage";
	}
	
	@RequiresPermissions("supplier:tsSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(TsSupplier tsSupplier, RedirectAttributes redirectAttributes) {
		tsSupplierService.delete(tsSupplier);
		addMessage(redirectAttributes, "删除供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/tsSupplier/?repage";
	}

}