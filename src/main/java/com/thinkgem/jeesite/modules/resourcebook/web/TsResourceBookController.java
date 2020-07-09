/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebook.web;

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
import com.thinkgem.jeesite.modules.resourcebook.entity.TsResourceBook;
import com.thinkgem.jeesite.modules.resourcebook.service.TsResourceBookService;

/**
 * 图册资源维护Controller
 * @author suntao
 * @version 2018-01-31
 */
@Controller
@RequestMapping(value = "${adminPath}/resourcebook/tsResourceBook")
public class TsResourceBookController extends BaseController {

	@Autowired
	private TsResourceBookService tsResourceBookService;
	
	@ModelAttribute
	public TsResourceBook get(@RequestParam(required=false) String id) {
		TsResourceBook entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsResourceBookService.get(id);
		}
		if (entity == null){
			entity = new TsResourceBook();
		}
		return entity;
	}
	
	@RequiresPermissions("resourcebook:tsResourceBook:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsResourceBook tsResourceBook, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsResourceBook> page = tsResourceBookService.findPage(new Page<TsResourceBook>(request, response), tsResourceBook); 
		model.addAttribute("page", page);
		return "modules/resourcebook/tsResourceBookList";
	}

	@RequiresPermissions("resourcebook:tsResourceBook:view")
	@RequestMapping(value = "form")
	public String form(TsResourceBook tsResourceBook, Model model) {
		model.addAttribute("tsResourceBook", tsResourceBook);
		return "modules/resourcebook/tsResourceBookForm";
	}

	@RequiresPermissions("resourcebook:tsResourceBook:edit")
	@RequestMapping(value = "save")
	public String save(TsResourceBook tsResourceBook, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsResourceBook)){
			return form(tsResourceBook, model);
		}
		tsResourceBookService.save(tsResourceBook);
		addMessage(redirectAttributes, "保存图册资源维护成功");
		return "redirect:"+Global.getAdminPath()+"/resourcebook/tsResourceBook/?repage";
	}
	
	@RequiresPermissions("resourcebook:tsResourceBook:edit")
	@RequestMapping(value = "delete")
	public String delete(TsResourceBook tsResourceBook, RedirectAttributes redirectAttributes) {
		tsResourceBookService.delete(tsResourceBook);
		addMessage(redirectAttributes, "删除图册资源维护成功");
		return "redirect:"+Global.getAdminPath()+"/resourcebook/tsResourceBook/?repage";
	}

}