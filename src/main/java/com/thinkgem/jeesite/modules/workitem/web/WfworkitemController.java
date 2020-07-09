/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.workitem.web;

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
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.entity.Wfworkitem;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 工作项维护Controller
 * @author suntao
 * @version 2019-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/workitem/wfworkitem")
public class WfworkitemController extends BaseController {

	@Autowired
	private WfworkitemService wfworkitemService;
	
	@ModelAttribute
	public Wfworkitem get(@RequestParam(required=false) String id) {
		Wfworkitem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wfworkitemService.get(id);
		}
		if (entity == null){
			entity = new Wfworkitem();
		}
		return entity;
	}
	
	@RequiresPermissions("workitem:wfworkitem:view")
	@RequestMapping(value = {"list", ""})
	public String list(Wfworkitem wfworkitem, HttpServletRequest request, HttpServletResponse response, Model model) {
		wfworkitem.setParticipant(UserUtils.getUser().getId());
		Page<Wfworkitem> page = wfworkitemService.findPage(new Page<Wfworkitem>(request, response), wfworkitem); 
		model.addAttribute("page", page);
		return "modules/workitem/wfworkitemList";
	}

	@RequiresPermissions("workitem:wfworkitem:view")
	@RequestMapping(value = "form")
	public String form(Wfworkitem wfworkitem, Model model) {
		model.addAttribute("wfworkitem", wfworkitem);
		return "modules/workitem/wfworkitemForm";
	}

	@RequiresPermissions("workitem:wfworkitem:edit")
	@RequestMapping(value = "save")
	public String save(Wfworkitem wfworkitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wfworkitem)){
			return form(wfworkitem, model);
		}
		wfworkitemService.save(wfworkitem);
		addMessage(redirectAttributes, "保存工作项维护成功");
		return "redirect:"+Global.getAdminPath()+"/workitem/wfworkitem/?repage";
	}
	
	@RequiresPermissions("workitem:wfworkitem:edit")
	@RequestMapping(value = "delete")
	public String delete(Wfworkitem wfworkitem, RedirectAttributes redirectAttributes) {
		wfworkitemService.delete(wfworkitem);
		addMessage(redirectAttributes, "删除工作项维护成功");
		return "redirect:"+Global.getAdminPath()+"/workitem/wfworkitem/?repage";
	}

}