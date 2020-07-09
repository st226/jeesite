/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.equipmenttype;

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
import com.thinkgem.jeesite.modules.equipment.entity.equipmenttype.SbEquipmentType;
import com.thinkgem.jeesite.modules.equipment.service.equipmenttype.SbEquipmentTypeService;

/**
 * 仪器设备分类Controller
 * @author suntao
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/equipmenttype/sbEquipmentType")
public class SbEquipmentTypeController extends BaseController {

	@Autowired
	private SbEquipmentTypeService sbEquipmentTypeService;
	
	@ModelAttribute
	public SbEquipmentType get(@RequestParam(required=false) String id) {
		SbEquipmentType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEquipmentTypeService.get(id);
		}
		if (entity == null){
			entity = new SbEquipmentType();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:equipmenttype:sbEquipmentType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEquipmentType sbEquipmentType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SbEquipmentType> list = sbEquipmentTypeService.findList(sbEquipmentType); 
		model.addAttribute("list", list);
		return "modules/equipment/equipmenttype/sbEquipmentTypeList";
	}

	@RequiresPermissions("equipment:equipmenttype:sbEquipmentType:view")
	@RequestMapping(value = "form")
	public String form(SbEquipmentType sbEquipmentType, Model model) {
		if (sbEquipmentType.getParent()!=null && StringUtils.isNotBlank(sbEquipmentType.getParent().getId())){
			sbEquipmentType.setParent(sbEquipmentTypeService.get(sbEquipmentType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sbEquipmentType.getId())){
				SbEquipmentType sbEquipmentTypeChild = new SbEquipmentType();
				sbEquipmentTypeChild.setParent(new SbEquipmentType(sbEquipmentType.getParent().getId()));
				List<SbEquipmentType> list = sbEquipmentTypeService.findList(sbEquipmentType); 
				if (list.size() > 0){
					sbEquipmentType.setSort(list.get(list.size()-1).getSort());
					if (sbEquipmentType.getSort() != null){
						sbEquipmentType.setSort(sbEquipmentType.getSort() + 30);
					}
				}
			}
		}
		if (sbEquipmentType.getSort() == null){
			sbEquipmentType.setSort(30);
		}
		model.addAttribute("sbEquipmentType", sbEquipmentType);
		return "modules/equipment/equipmenttype/sbEquipmentTypeForm";
	}

	@RequiresPermissions("equipment:equipmenttype:sbEquipmentType:edit")
	@RequestMapping(value = "save")
	public String save(SbEquipmentType sbEquipmentType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEquipmentType)){
			return form(sbEquipmentType, model);
		}
		sbEquipmentTypeService.save(sbEquipmentType);
		addMessage(redirectAttributes, "保存仪器设备分类成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmenttype/sbEquipmentType/?repage";
	}
	
	@RequiresPermissions("equipment:equipmenttype:sbEquipmentType:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEquipmentType sbEquipmentType, RedirectAttributes redirectAttributes) {
		sbEquipmentTypeService.delete(sbEquipmentType);
		addMessage(redirectAttributes, "删除仪器设备分类成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmenttype/sbEquipmentType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SbEquipmentType> list = sbEquipmentTypeService.findList(new SbEquipmentType());
		for (int i=0; i<list.size(); i++){
			SbEquipmentType e = list.get(i);
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