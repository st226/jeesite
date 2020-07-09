/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sbdeploy.web;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.service.equipmentbus.SbEquipmentBusService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.sbdeploy.entity.SbDeploy;
import com.thinkgem.jeesite.modules.sbdeploy.service.SbDeployService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 标签配置Controller
 * @author suntao
 * @version 2020-03-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sbdeploy/sbDeploy")
public class SbDeployController extends BaseController {

	@Autowired
	private SbDeployService sbDeployService;
	
	@Autowired
	private TsResourceBusService tsResourceBusService;
	
	@ModelAttribute
	public SbDeploy get(@RequestParam(required=false) String id) {
		SbDeploy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbDeployService.get(id);
		}
		if (entity == null){
			entity = new SbDeploy();
		}
		return entity;
	}
	
	@RequiresPermissions("sbdeploy:sbDeploy:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbDeploy sbDeploy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbDeploy> page = sbDeployService.findPage(new Page<SbDeploy>(request, response), sbDeploy); 
		model.addAttribute("page", page);
		return "modules/sbdeploy/sbDeployList";
	}

	@RequiresPermissions("sbdeploy:sbDeploy:view")
	@RequestMapping(value = "form")
	public String form(SbDeploy sbDeploy, Model model) {
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy);
		if(sbDeploy!=null && sbDeploy.getBusType()!=null){
		   List<String> list = Arrays.asList(sbDeploy.getBusType().split(","));
		   sbDeploy.setTsResourceBusIdList(list);
		}else{
			
			sbDeploy = new SbDeploy();
		}
		TsResourceBus tsResourceBus = new TsResourceBus();
		tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		tsResourceBus.setIsList("1");
		List<TsResourceBus> tsResourceBusList = tsResourceBusService.getBus(tsResourceBus) ;
		    

		
		model.addAttribute("sbDeploy", sbDeploy);
		model.addAttribute("tsResourceBusList", tsResourceBusList);
		
		return "modules/sbdeploy/sbDeployForm";
	}

	@RequiresPermissions("sbdeploy:sbDeploy:edit")
	@RequestMapping(value = "save")
	public String save(SbDeploy sbDeploy, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, sbDeploy)){
			return form(sbDeploy, model);
		}
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy.setTsResourceBusbusType(sbDeploy.getTsResourceBusList());
		sbDeployService.save(sbDeploy);
		addMessage(redirectAttributes, "保存标签配置成功");
		return "redirect:"+Global.getAdminPath()+"/sbdeploy/sbDeploy/?repage";
	}
	
	@RequiresPermissions("sbdeploy:sbDeploy:edit")
	@RequestMapping(value = "delete")
	public String delete(SbDeploy sbDeploy, RedirectAttributes redirectAttributes) {
		sbDeployService.delete(sbDeploy);
		addMessage(redirectAttributes, "删除标签配置成功");
		return "redirect:"+Global.getAdminPath()+"/sbdeploy/sbDeploy/?repage";
	}

}