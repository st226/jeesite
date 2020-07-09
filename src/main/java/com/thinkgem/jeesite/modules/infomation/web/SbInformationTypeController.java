/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.web;

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
import com.thinkgem.jeesite.modules.infomation.entity.SbInformationType;
import com.thinkgem.jeesite.modules.infomation.service.SbInformationTypeService;

/**
 * 信息化设备Controller
 * @author suntao
 * @version 2020-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/infomation/sbInformationType")
public class SbInformationTypeController extends BaseController {

	@Autowired
	private SbInformationTypeService sbInformationTypeService;
	
	@ModelAttribute
	public SbInformationType get(@RequestParam(required=false) String id) {
		SbInformationType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbInformationTypeService.get(id);
		}
		if (entity == null){
			entity = new SbInformationType();
		}
		return entity;
	}
	
	@RequiresPermissions("infomation:sbInformationType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbInformationType sbInformationType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SbInformationType> list = sbInformationTypeService.findList(sbInformationType); 
		model.addAttribute("list", list);
		return "modules/infomation/sbInformationTypeList";
	}

	@RequiresPermissions("infomation:sbInformationType:view")
	@RequestMapping(value = "form")
	public String form(SbInformationType sbInformationType, Model model) {
		if (sbInformationType.getParent()!=null && StringUtils.isNotBlank(sbInformationType.getParent().getId())){
			sbInformationType.setParent(sbInformationTypeService.get(sbInformationType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sbInformationType.getId())){
				SbInformationType sbInformationTypeChild = new SbInformationType();
				sbInformationTypeChild.setParent(new SbInformationType(sbInformationType.getParent().getId()));
				List<SbInformationType> list = sbInformationTypeService.findList(sbInformationType); 
				if (list.size() > 0){
					sbInformationType.setSort(list.get(list.size()-1).getSort());
					if (sbInformationType.getSort() != null){
						sbInformationType.setSort(sbInformationType.getSort() + 30);
					}
				}
			}
		}
		if (sbInformationType.getSort() == null){
			sbInformationType.setSort(30);
		}
		model.addAttribute("sbInformationType", sbInformationType);
		return "modules/infomation/sbInformationTypeForm";
	}

	@RequiresPermissions("infomation:sbInformationType:edit")
	@RequestMapping(value = "save")
	public String save(SbInformationType sbInformationType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbInformationType)){
			return form(sbInformationType, model);
		}
		sbInformationTypeService.save(sbInformationType);
		addMessage(redirectAttributes, "保存信息化设备类型成功");
		return "redirect:"+Global.getAdminPath()+"/infomation/sbInformationType/?repage";
	}
	
	@RequiresPermissions("infomation:sbInformationType:edit")
	@RequestMapping(value = "delete")
	public String delete(SbInformationType sbInformationType, RedirectAttributes redirectAttributes) {
		sbInformationTypeService.delete(sbInformationType);
		addMessage(redirectAttributes, "删除信息化设备类型成功");
		return "redirect:"+Global.getAdminPath()+"/infomation/sbInformationType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SbInformationType> list = sbInformationTypeService.findList(new SbInformationType());
		for (int i=0; i<list.size(); i++){
			SbInformationType e = list.get(i);
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