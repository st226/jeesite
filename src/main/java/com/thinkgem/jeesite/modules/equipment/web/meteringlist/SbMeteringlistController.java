/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.meteringlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.thinkgem.jeesite.modules.equipment.entity.meteringlist.SbMeteringlist;
import com.thinkgem.jeesite.modules.equipment.service.meteringlist.SbMeteringlistService;

/**
 * 计量查询管理Controller
 * @author suntao
 * @version 2020-03-26
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/meteringlist/sbMeteringlist")
public class SbMeteringlistController extends BaseController {

	@Autowired
	private SbMeteringlistService sbMeteringlistService;
	
	@ModelAttribute
	public SbMeteringlist get(@RequestParam(required=false) String id) {
		SbMeteringlist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbMeteringlistService.get(id);
		}
		if (entity == null){
			entity = new SbMeteringlist();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:meteringlist:sbMeteringlist:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbMeteringlist sbMeteringlist, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(sbMeteringlist.getEmp()+"-----");
		Page<SbMeteringlist> page = sbMeteringlistService.findPage(new Page<SbMeteringlist>(request, response), sbMeteringlist); 
		model.addAttribute("page", page);
		return "modules/equipment/meteringlist/sbMeteringlistList";
	}

	@RequiresPermissions("equipment:meteringlist:sbMeteringlist:view")
	@RequestMapping(value = "form")
	public String form(SbMeteringlist sbMeteringlist, Model model) {
		model.addAttribute("sbMeteringlist", sbMeteringlist);
		return "modules/equipment/meteringlist/sbMeteringlistForm";
	}

	@RequiresPermissions("equipment:meteringlist:sbMeteringlist:edit")
	@RequestMapping(value = "save")
	public String save(SbMeteringlist sbMeteringlist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMeteringlist)){
			return form(sbMeteringlist, model);
		}
		sbMeteringlistService.save(sbMeteringlist);
		addMessage(redirectAttributes, "保存计量查询管理成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/meteringlist/sbMeteringlist/?repage";
	}
	
	@RequestMapping(value = "saveMetering")
    @ResponseBody
    public void saveMetering(SbMeteringlist sbMeteringlist,HttpServletResponse response){
	    
		sbMeteringlistService.save(sbMeteringlist);

        renderString(response, JsonMapper.toJsonString("sucess"),"text/html");

    }
	
	@RequiresPermissions("equipment:meteringlist:sbMeteringlist:edit")
	@RequestMapping(value = "delete")
	public String delete(SbMeteringlist sbMeteringlist, RedirectAttributes redirectAttributes) {
		sbMeteringlistService.delete(sbMeteringlist);
		addMessage(redirectAttributes, "删除计量查询管理成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/meteringlist/sbMeteringlist/?repage";
	}

}