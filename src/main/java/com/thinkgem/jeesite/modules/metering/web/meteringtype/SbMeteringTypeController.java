/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.web.meteringtype;

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
import com.thinkgem.jeesite.modules.metering.entity.meteringtype.SbMeteringType;
import com.thinkgem.jeesite.modules.metering.service.meteringtype.SbMeteringTypeService;

/**
 * 计量设备类型Controller
 * @author suntao
 * @version 2020-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/metering/meteringtype/sbMeteringType")
public class SbMeteringTypeController extends BaseController {

	@Autowired
	private SbMeteringTypeService sbMeteringTypeService;
	
	@ModelAttribute
	public SbMeteringType get(@RequestParam(required=false) String id) {
		SbMeteringType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbMeteringTypeService.get(id);
		}
		if (entity == null){
			entity = new SbMeteringType();
		}
		return entity;
	}
	
	@RequiresPermissions("metering:meteringtype:sbMeteringType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbMeteringType sbMeteringType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SbMeteringType> list = sbMeteringTypeService.findList(sbMeteringType); 
		model.addAttribute("list", list);
		return "modules/metering/meteringtype/sbMeteringTypeList";
	}

	@RequiresPermissions("metering:meteringtype:sbMeteringType:view")
	@RequestMapping(value = "form")
	public String form(SbMeteringType sbMeteringType, Model model) {
		if (sbMeteringType.getParent()!=null && StringUtils.isNotBlank(sbMeteringType.getParent().getId())){
			sbMeteringType.setParent(sbMeteringTypeService.get(sbMeteringType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sbMeteringType.getId())){
				SbMeteringType sbMeteringTypeChild = new SbMeteringType();
				sbMeteringTypeChild.setParent(new SbMeteringType(sbMeteringType.getParent().getId()));
				List<SbMeteringType> list = sbMeteringTypeService.findList(sbMeteringType); 
				if (list.size() > 0){
					sbMeteringType.setSort(list.get(list.size()-1).getSort());
					if (sbMeteringType.getSort() != null){
						sbMeteringType.setSort(sbMeteringType.getSort() + 30);
					}
				}
			}
		}
		if (sbMeteringType.getSort() == null){
			sbMeteringType.setSort(30);
		}
		model.addAttribute("sbMeteringType", sbMeteringType);
		return "modules/metering/meteringtype/sbMeteringTypeForm";
	}

	@RequiresPermissions("metering:meteringtype:sbMeteringType:edit")
	@RequestMapping(value = "save")
	public String save(SbMeteringType sbMeteringType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMeteringType)){
			return form(sbMeteringType, model);
		}
		sbMeteringTypeService.save(sbMeteringType);
		addMessage(redirectAttributes, "保存计量设备类型成功");
		return "redirect:"+Global.getAdminPath()+"/metering/meteringtype/sbMeteringType/?repage";
	}
	
	@RequiresPermissions("metering:meteringtype:sbMeteringType:edit")
	@RequestMapping(value = "delete")
	public String delete(SbMeteringType sbMeteringType, RedirectAttributes redirectAttributes) {
		sbMeteringTypeService.delete(sbMeteringType);
		addMessage(redirectAttributes, "删除计量设备类型成功");
		return "redirect:"+Global.getAdminPath()+"/metering/meteringtype/sbMeteringType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SbMeteringType> list = sbMeteringTypeService.findList(new SbMeteringType());
		for (int i=0; i<list.size(); i++){
			SbMeteringType e = list.get(i);
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