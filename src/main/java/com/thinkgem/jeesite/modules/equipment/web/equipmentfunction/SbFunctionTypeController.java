/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.equipmentfunction;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentfunction.SbFunctionType;
import com.thinkgem.jeesite.modules.equipment.service.equipmentfunction.SbFunctionTypeService;

/**
 * 功能类别Controller
 * @author suntao
 * @version 2019-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/equipmentfunction/sbFunctionType")
public class SbFunctionTypeController extends BaseController {

	@Autowired
	private SbFunctionTypeService sbFunctionTypeService;
	
	@ModelAttribute
	public SbFunctionType get(@RequestParam(required=false) String id) {
		SbFunctionType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbFunctionTypeService.get(id);
		}
		if (entity == null){
			entity = new SbFunctionType();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:equipmentfunction:sbFunctionType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbFunctionType sbFunctionType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SbFunctionType> list = sbFunctionTypeService.findList(sbFunctionType); 
		model.addAttribute("list", list);
		return "modules/equipment/equipmentfunction/sbFunctionTypeList";
	}

	@RequiresPermissions("equipment:equipmentfunction:sbFunctionType:view")
	@RequestMapping(value = "form")
	public String form(SbFunctionType sbFunctionType, Model model) {
		if (sbFunctionType.getParent()!=null && StringUtils.isNotBlank(sbFunctionType.getParent().getId())){
			sbFunctionType.setParent(sbFunctionTypeService.get(sbFunctionType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sbFunctionType.getId())){
				SbFunctionType sbFunctionTypeChild = new SbFunctionType();
				sbFunctionTypeChild.setParent(new SbFunctionType(sbFunctionType.getParent().getId()));
				List<SbFunctionType> list = sbFunctionTypeService.findList(sbFunctionType); 
				if (list.size() > 0){
					sbFunctionType.setSort(list.get(list.size()-1).getSort());
					if (sbFunctionType.getSort() != null){
						sbFunctionType.setSort(sbFunctionType.getSort() + 30);
					}
				}
			}
		}
		if (sbFunctionType.getSort() == null){
			sbFunctionType.setSort(30);
		}
		model.addAttribute("sbFunctionType", sbFunctionType);
		return "modules/equipment/equipmentfunction/sbFunctionTypeForm";
	}

	@RequiresPermissions("equipment:equipmentfunction:sbFunctionType:edit")
	@RequestMapping(value = "save")
	public String save(SbFunctionType sbFunctionType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbFunctionType)){
			return form(sbFunctionType, model);
		}
		sbFunctionTypeService.save(sbFunctionType);
		addMessage(redirectAttributes, "保存功能类别成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmentfunction/sbFunctionType/?repage";
	}
	
	@RequiresPermissions("equipment:equipmentfunction:sbFunctionType:edit")
	@RequestMapping(value = "delete")
	public String delete(SbFunctionType sbFunctionType, RedirectAttributes redirectAttributes) {
		sbFunctionTypeService.delete(sbFunctionType);
		addMessage(redirectAttributes, "删除功能类别成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmentfunction/sbFunctionType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SbFunctionType> list = sbFunctionTypeService.findList(new SbFunctionType());
		for (int i=0; i<list.size(); i++){
			SbFunctionType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}