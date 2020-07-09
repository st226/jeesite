/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.qmresource;

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
import com.thinkgem.jeesite.modules.infomation.entity.infomation.SbInformation;
import com.thinkgem.jeesite.modules.quality.entity.qmresource.QmResource;
import com.thinkgem.jeesite.modules.quality.service.qmresource.QmResourceService;

/**
 * 技术文件管理Controller
 * @author suntao
 * @version 2020-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/qmresource/qmResource")
public class QmResourceController extends BaseController {

	@Autowired
	private QmResourceService qmResourceService;
	
	@ModelAttribute
	public QmResource get(@RequestParam(required=false) String id) {
		QmResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmResourceService.get(id);
		}
		if (entity == null){
			entity = new QmResource();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:qmresource:qmResource:view")
	@RequestMapping(value = {"index"})
	public String index(QmResource qmResource, Model model) {
		return "modules/quality/qmresource/qmResourceIndex";
	}
	
	@RequiresPermissions("quality:qmresource:qmResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmResource qmResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmResource> page = qmResourceService.findPage(new Page<QmResource>(request, response), qmResource); 
		model.addAttribute("page", page);
		return "modules/quality/qmresource/qmResourceList";
	}

	@RequiresPermissions("quality:qmresource:qmResource:view")
	@RequestMapping(value = "form")
	public String form(QmResource qmResource, Model model) {
		model.addAttribute("qmResource", qmResource);
		return "modules/quality/qmresource/qmResourceForm";
	}

	@RequiresPermissions("quality:qmresource:qmResource:edit")
	@RequestMapping(value = "save")
	public String save(QmResource qmResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmResource)){
			return form(qmResource, model);
		}
		qmResourceService.save(qmResource);
		addMessage(redirectAttributes, "保存技术文件管理成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmresource/qmResource/?repage";
	}
	
	@RequiresPermissions("quality:qmresource:qmResource:edit")
	@RequestMapping(value = "delete")
	public String delete(QmResource qmResource, RedirectAttributes redirectAttributes) {
		qmResourceService.delete(qmResource);
		addMessage(redirectAttributes, "删除技术文件管理成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmresource/qmResource/?repage";
	}

}