/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.paycollect;

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
import com.thinkgem.jeesite.modules.business.entity.paycollect.SwPayCollect;
import com.thinkgem.jeesite.modules.business.service.paycollect.SwPayCollectService;

/**
 * 付款信息汇总Controller
 * @author suntao
 * @version 2020-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/business/paycollect/swPayCollect")
public class SwPayCollectController extends BaseController {

	@Autowired
	private SwPayCollectService swPayCollectService;
	
	@ModelAttribute
	public SwPayCollect get(@RequestParam(required=false) String id) {
		SwPayCollect entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swPayCollectService.get(id);
		}
		if (entity == null){
			entity = new SwPayCollect();
		}
		return entity;
	}
	
	@RequiresPermissions("business:paycollect:swPayCollect:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwPayCollect swPayCollect, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwPayCollect> page = swPayCollectService.findPage(new Page<SwPayCollect>(request, response), swPayCollect); 
		model.addAttribute("page", page);
		return "modules/business/paycollect/swPayCollectList";
	}

	@RequiresPermissions("business:paycollect:swPayCollect:view")
	@RequestMapping(value = "form")
	public String form(SwPayCollect swPayCollect, Model model) {
		model.addAttribute("swPayCollect", swPayCollect);
		return "modules/business/paycollect/swPayCollectForm";
	}

	@RequiresPermissions("business:paycollect:swPayCollect:edit")
	@RequestMapping(value = "save")
	public String save(SwPayCollect swPayCollect, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swPayCollect)){
			return form(swPayCollect, model);
		}
		swPayCollectService.save(swPayCollect);
		addMessage(redirectAttributes, "保存付款信息汇总成功");
		return "redirect:"+Global.getAdminPath()+"/business/paycollect/swPayCollect/?repage";
	}
	
	@RequiresPermissions("business:paycollect:swPayCollect:edit")
	@RequestMapping(value = "delete")
	public String delete(SwPayCollect swPayCollect, RedirectAttributes redirectAttributes) {
		swPayCollectService.delete(swPayCollect);
		addMessage(redirectAttributes, "删除付款信息汇总成功");
		return "redirect:"+Global.getAdminPath()+"/business/paycollect/swPayCollect/?repage";
	}

}