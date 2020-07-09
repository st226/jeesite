/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maxtable.web;

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
import com.thinkgem.jeesite.modules.maxtable.entity.TsMaxTable;
import com.thinkgem.jeesite.modules.maxtable.service.TsMaxTableService;

/**
 * 流水号Controller
 * @author suntao
 * @version 2018-01-26
 */
@Controller
@RequestMapping(value = "${adminPath}/maxtable/tsMaxTable")
public class TsMaxTableController extends BaseController {

	@Autowired
	private TsMaxTableService tsMaxTableService;
	
	@ModelAttribute
	public TsMaxTable get(@RequestParam(required=false) String id) {
		TsMaxTable entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsMaxTableService.get(id);
		}
		if (entity == null){
			entity = new TsMaxTable();
		}
		return entity;
	}
	
	@RequiresPermissions("maxtable:tsMaxTable:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsMaxTable tsMaxTable, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsMaxTable> page = tsMaxTableService.findPage(new Page<TsMaxTable>(request, response), tsMaxTable); 
		model.addAttribute("page", page);
		return "modules/maxtable/tsMaxTableList";
	}

	@RequiresPermissions("maxtable:tsMaxTable:view")
	@RequestMapping(value = "form")
	public String form(TsMaxTable tsMaxTable, Model model) {
		model.addAttribute("tsMaxTable", tsMaxTable);
		return "modules/maxtable/tsMaxTableForm";
	}

	@RequiresPermissions("maxtable:tsMaxTable:edit")
	@RequestMapping(value = "save")
	public String save(TsMaxTable tsMaxTable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsMaxTable)){
			return form(tsMaxTable, model);
		}
		tsMaxTableService.save(tsMaxTable);
		addMessage(redirectAttributes, "保存流水号成功");
		return "redirect:"+Global.getAdminPath()+"/maxtable/tsMaxTable/?repage";
	}
	
	@RequiresPermissions("maxtable:tsMaxTable:edit")
	@RequestMapping(value = "delete")
	public String delete(TsMaxTable tsMaxTable, RedirectAttributes redirectAttributes) {
		tsMaxTableService.delete(tsMaxTable);
		addMessage(redirectAttributes, "删除流水号成功");
		return "redirect:"+Global.getAdminPath()+"/maxtable/tsMaxTable/?repage";
	}

}