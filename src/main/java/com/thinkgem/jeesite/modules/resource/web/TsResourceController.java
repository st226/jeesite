/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resource.service.TsResourceService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 图书馆资源管理Controller
 * @author suntao
 * @version 2018-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/tsResource")
public class TsResourceController extends BaseController {

	@Autowired
	private TsResourceService tsResourceService;
	
	@ModelAttribute
	public TsResource get(@RequestParam(required=false) String id) {
		TsResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsResourceService.get(id);
		}
		if (entity == null){
			entity = new TsResource();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:tsResource:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/resource/tsResourceIndex";
	}
	
	@RequiresPermissions("resource:tsResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsResource tsResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		TsResourceBus tsResourceBus = new TsResourceBus();
		tsResourceBus.setBusType(tsResource.getBusType());
		model.addAttribute("busType", tsResource.getBusType());
		List<TsResourceBus> list = tsResourceService.getBus(tsResourceBus) ;
		String code = "" ;
		for(int i = 0 ;i<list.size() ; i++){
			tsResourceBus = list.get(i) ;
			if(code ==""){
				code = tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
		}
		if(code != null && !"".equals(code)){
		tsResource.setBusType(code);
		DataMap dataMap = new DataMap() ;
		dataMap.put("busType", code) ;
	//	Page<Map> page = tsResourceService.findMapPage(new Page<Map>(request, response), dataMap); 
	//	model.addAttribute("page", page);
		}
		
		model.addAttribute("TsResourceBus", list);
		return "modules/resource/tsResourceList";
	}

	@RequiresPermissions("resource:tsResource:view")
	@RequestMapping(value = "form")
	public String form(TsResource tsResource, Model model) {
		
		
		
		model.addAttribute("tsResource", tsResource);
		return "modules/resource/tsResourceForm";
	}

	@RequiresPermissions("resource:tsResource:edit")
	@RequestMapping(value = "save")
	public String save(TsResource tsResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsResource)){
			return form(tsResource, model);
		}
		tsResourceService.save(tsResource);
		addMessage(redirectAttributes, "保存图书馆资源管理成功"+tsResource.getBusType());
		model.addAttribute("busType", tsResource.getBusType());
		return "redirect:"+Global.getAdminPath()+"/resource/tsResource/list?busType="+tsResource.getBusType();
	}
	
	@RequiresPermissions("resource:tsResource:edit")
	@RequestMapping(value = "delete")
	public String delete(TsResource tsResource, Model model, RedirectAttributes redirectAttributes) {
		tsResourceService.delete(tsResource);
		addMessage(redirectAttributes, "删除图书馆资源管理成功");
		model.addAttribute("busType", tsResource.getBusType());
		return "redirect:"+Global.getAdminPath()+"/resource/tsResource/list?busType="+tsResource.getBusType();
	}

}