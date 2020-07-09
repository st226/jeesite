/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.resource;

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
import com.thinkgem.jeesite.modules.quality.entity.resource.QmResourceType;
import com.thinkgem.jeesite.modules.quality.service.resource.QmResourceTypeService;

/**
 * 技术文件模板Controller
 * @author suntao
 * @version 2020-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/resource/qmResourceType")
public class QmResourceTypeController extends BaseController {

	@Autowired
	private QmResourceTypeService qmResourceTypeService;
	
	@ModelAttribute
	public QmResourceType get(@RequestParam(required=false) String id) {
		QmResourceType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmResourceTypeService.get(id);
		}
		if (entity == null){
			entity = new QmResourceType();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:resource:qmResourceType:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmResourceType qmResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<QmResourceType> list = qmResourceTypeService.findList(qmResourceType); 
		model.addAttribute("list", list);
		return "modules/quality/resource/qmResourceTypeList";
	}

	@RequiresPermissions("quality:resource:qmResourceType:view")
	@RequestMapping(value = "form")
	public String form(QmResourceType qmResourceType, Model model) {
		if (qmResourceType.getParent()!=null && StringUtils.isNotBlank(qmResourceType.getParent().getId())){
			qmResourceType.setParent(qmResourceTypeService.get(qmResourceType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(qmResourceType.getId())){
				QmResourceType qmResourceTypeChild = new QmResourceType();
				qmResourceTypeChild.setParent(new QmResourceType(qmResourceType.getParent().getId()));
				List<QmResourceType> list = qmResourceTypeService.findList(qmResourceType); 
				if (list.size() > 0){
					qmResourceType.setSort(list.get(list.size()-1).getSort());
					if (qmResourceType.getSort() != null){
						qmResourceType.setSort(qmResourceType.getSort() + 30);
					}
				}
			}
		}
		if (qmResourceType.getSort() == null){
			qmResourceType.setSort(30);
		}
		model.addAttribute("qmResourceType", qmResourceType);
		return "modules/quality/resource/qmResourceTypeForm";
	}

	@RequiresPermissions("quality:resource:qmResourceType:edit")
	@RequestMapping(value = "save")
	public String save(QmResourceType qmResourceType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmResourceType)){
			return form(qmResourceType, model);
		}
		qmResourceTypeService.save(qmResourceType);
		addMessage(redirectAttributes, "保存技术文件模板成功");
		return "redirect:"+Global.getAdminPath()+"/quality/resource/qmResourceType/?repage";
	}
	
	@RequiresPermissions("quality:resource:qmResourceType:edit")
	@RequestMapping(value = "delete")
	public String delete(QmResourceType qmResourceType, RedirectAttributes redirectAttributes) {
		qmResourceTypeService.delete(qmResourceType);
		addMessage(redirectAttributes, "删除技术文件模板成功");
		return "redirect:"+Global.getAdminPath()+"/quality/resource/qmResourceType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<QmResourceType> list = qmResourceTypeService.findList(new QmResourceType());
		for (int i=0; i<list.size(); i++){
			QmResourceType e = list.get(i);
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