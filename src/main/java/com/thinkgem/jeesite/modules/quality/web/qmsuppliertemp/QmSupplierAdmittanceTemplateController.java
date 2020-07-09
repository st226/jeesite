/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.qmsuppliertemp;

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
import com.thinkgem.jeesite.modules.quality.entity.qmsuppliertemp.QmSupplierAdmittanceTemplate;
import com.thinkgem.jeesite.modules.quality.service.qmsuppliertemp.QmSupplierAdmittanceTemplateService;

/**
 * 供应商文件模板Controller
 * @author suntao
 * @version 2020-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate")
public class QmSupplierAdmittanceTemplateController extends BaseController {

	@Autowired
	private QmSupplierAdmittanceTemplateService qmSupplierAdmittanceTemplateService;
	
	@ModelAttribute
	public QmSupplierAdmittanceTemplate get(@RequestParam(required=false) String id) {
		QmSupplierAdmittanceTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmSupplierAdmittanceTemplateService.get(id);
		}
		if (entity == null){
			entity = new QmSupplierAdmittanceTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmSupplierAdmittanceTemplate> page = qmSupplierAdmittanceTemplateService.findPage(new Page<QmSupplierAdmittanceTemplate>(request, response), qmSupplierAdmittanceTemplate); 
		model.addAttribute("page", page);
		return "modules/quality/qmsuppliertemp/qmSupplierAdmittanceTemplateList";
	}

	@RequiresPermissions("quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:view")
	@RequestMapping(value = "form")
	public String form(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate, Model model) {
		model.addAttribute("qmSupplierAdmittanceTemplate", qmSupplierAdmittanceTemplate);
		return "modules/quality/qmsuppliertemp/qmSupplierAdmittanceTemplateForm";
	}

	@RequiresPermissions("quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:edit")
	@RequestMapping(value = "save")
	public String save(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmSupplierAdmittanceTemplate)){
			return form(qmSupplierAdmittanceTemplate, model);
		}
		qmSupplierAdmittanceTemplateService.save(qmSupplierAdmittanceTemplate);
		addMessage(redirectAttributes, "保存供应商文件模板成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/?repage";
	}
	
	@RequiresPermissions("quality:qmsuppliertemp:qmSupplierAdmittanceTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate, RedirectAttributes redirectAttributes) {
		qmSupplierAdmittanceTemplateService.delete(qmSupplierAdmittanceTemplate);
		addMessage(redirectAttributes, "删除供应商文件模板成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmsuppliertemp/qmSupplierAdmittanceTemplate/?repage";
	}

}