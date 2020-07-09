/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.business.service.supplier.SwSupplierService;

/**
 * 供应商管理Controller
 * @author suntao
 * @version 2020-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/business/supplier/swSupplier")
public class SwSupplierController extends BaseController {

	@Autowired
	private SwSupplierService swSupplierService;
	
	@ModelAttribute
	public SwSupplier get(@RequestParam(required=false) String id) {
		SwSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swSupplierService.get(id);
		}
		if (entity == null){
			entity = new SwSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("business:supplier:swSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwSupplier swSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwSupplier> page = swSupplierService.findPage(new Page<SwSupplier>(request, response), swSupplier); 
		model.addAttribute("page", page);
		return "modules/business/supplier/swSupplierList";
	}
	
	@RequiresPermissions("business:supplier:swSupplier:view")
	@RequestMapping(value = {"qureyList"}) 
	public String qureyList(SwSupplier swSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwSupplier> page = swSupplierService.findPage(new Page<SwSupplier>(request, response), swSupplier); 
		model.addAttribute("page", page);
		return "modules/business/supplier/swSupplierQureyList";
	}

	@RequiresPermissions("business:supplier:swSupplier:view")
	@RequestMapping(value = "form")
	public String form(SwSupplier swSupplier, Model model) {
		model.addAttribute("swSupplier", swSupplier);
		return "modules/business/supplier/swSupplierForm";
	}

	@RequiresPermissions("business:supplier:swSupplier:edit")
	@RequestMapping(value = "save")
	public String save(SwSupplier swSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swSupplier)){
			return form(swSupplier, model);
		}
		swSupplierService.save(swSupplier);
		addMessage(redirectAttributes, "保存供应商成功");
		return "redirect:"+Global.getAdminPath()+"/business/supplier/swSupplier/?repage";
	}
	
	@RequiresPermissions("business:supplier:swSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(SwSupplier swSupplier, RedirectAttributes redirectAttributes) {
		swSupplierService.delete(swSupplier);
		addMessage(redirectAttributes, "删除供应商成功");
		return "redirect:"+Global.getAdminPath()+"/business/supplier/swSupplier/?repage";
	}
	
	@RequestMapping(value = "getSupplier")
    @ResponseBody
	public void getSupplier(SwSupplier swSupplier, RedirectAttributes redirectAttributes,HttpServletResponse response) {

		System.out.println(swSupplier.getName()+"-------");
		renderString(response, JsonMapper.toJsonString(swSupplier),"text/html");
	}

}