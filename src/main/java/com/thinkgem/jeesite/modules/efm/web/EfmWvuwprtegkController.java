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
import com.thinkgem.jeesite.modules.efm.entity.EfmWvuwprtegk;
import com.thinkgem.jeesite.modules.efm.service.EfmWvuwprtegkService;

/**
 * 涉密文件扫描审批备案表Controller
 * @author l
 * @version 2021-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/efm/efmWvuwprtegk")
public class EfmWvuwprtegkController extends BaseController {

	@Autowired
	private EfmWvuwprtegkService efmWvuwprtegkService;
	
	@ModelAttribute
	public EfmWvuwprtegk get(@RequestParam(required=false) String id) {
		EfmWvuwprtegk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = efmWvuwprtegkService.get(id);
		}
		if (entity == null){
			entity = new EfmWvuwprtegk();
		}
		return entity;
	}
	
	@RequiresPermissions("efm:efmWvuwprtegk:view")
	@RequestMapping(value = {"list", ""})
	public String list(EfmWvuwprtegk efmWvuwprtegk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EfmWvuwprtegk> page = efmWvuwprtegkService.findPage(new Page<EfmWvuwprtegk>(request, response), efmWvuwprtegk); 
		model.addAttribute("page", page);
		return "modules/efm/efmWvuwprtegkList";
	}

	@RequiresPermissions("efm:efmWvuwprtegk:view")
	@RequestMapping(value = "form")
	public String form(EfmWvuwprtegk efmWvuwprtegk, Model model) {
		model.addAttribute("efmWvuwprtegk", efmWvuwprtegk);
		return "modules/efm/efmWvuwprtegkForm";
	}

	@RequiresPermissions("efm:efmWvuwprtegk:edit")
	@RequestMapping(value = "save")
	public String save(EfmWvuwprtegk efmWvuwprtegk, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, efmWvuwprtegk)){
			return form(efmWvuwprtegk, model);
		}
		efmWvuwprtegkService.save(efmWvuwprtegk);
		addMessage(redirectAttributes, "保存涉密文件扫描审批备案表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmWvuwprtegk/?repage";
	}
	
	@RequiresPermissions("efm:efmWvuwprtegk:edit")
	@RequestMapping(value = "delete")
	public String delete(EfmWvuwprtegk efmWvuwprtegk, RedirectAttributes redirectAttributes) {
		efmWvuwprtegkService.delete(efmWvuwprtegk);
		addMessage(redirectAttributes, "删除涉密文件扫描审批备案表成功");
		return "redirect:"+Global.getAdminPath()+"/efm/efmWvuwprtegk/?repage";
	}

}