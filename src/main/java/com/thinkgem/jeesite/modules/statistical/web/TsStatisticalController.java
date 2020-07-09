/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.statistical.web;

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
import com.thinkgem.jeesite.modules.statistical.entity.TsStatistical;
import com.thinkgem.jeesite.modules.statistical.service.TsStatisticalService;

/**
 * 统计分析Controller
 * @author suntao
 * @version 2018-02-04
 */
@Controller
@RequestMapping(value = "${adminPath}/statistical/tsStatistical")
public class TsStatisticalController extends BaseController {

	@Autowired
	private TsStatisticalService tsStatisticalService;
	
	@ModelAttribute
	public TsStatistical get(@RequestParam(required=false) String id) {
		TsStatistical entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsStatisticalService.get(id);
		}
		if (entity == null){
			entity = new TsStatistical();
		}
		return entity;
	}
	
	@RequiresPermissions("statistical:tsStatistical:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsStatistical> page = tsStatisticalService.findPage(new Page<TsStatistical>(request, response), tsStatistical); 
		model.addAttribute("page", page);
		return "modules/statistical/tsStatisticalList";
	}
	
	@RequestMapping(value = {"libraryZs", ""})
	public String libraryZs(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsLibraryZs";
	}
	
	@RequestMapping(value = {"libraryCar", ""})
	public String libraryCar(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsCar";
	}
	@RequestMapping(value = {"libraryCar2", ""})
	public String libraryCar2(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsCar2";
	}
	
	@RequestMapping(value = {"libraryCar3", ""})
	public String libraryCar3(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsCar3";
	}
	@RequestMapping(value = {"libraryCar4", ""})
	public String libraryCar4(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsCar4";
	}
	@RequestMapping(value = {"libraryCar5", ""})
	public String libraryCar5(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsCar5";
	}
	
	@RequestMapping(value = {"libraryWz", ""})
	public String libraryWz(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsLibraryWz";
	}
	
	
	
	@RequestMapping(value = {"library", ""})
	public String library(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsLibraryCount";
	}
	
	@RequestMapping(value = {"accounted", ""})
	public String accounted(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsAccounted";
	}
	
	@RequestMapping(value = {"accounted2", ""})
	public String accounted2(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsAccounted2";
	}
	
	@RequestMapping(value = {"borrowing", ""})
	public String borrowing(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsBorrowing";
	}
	
	@RequestMapping(value = {"index", ""})
	public String index(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsTjdx";
	}
	

	@RequestMapping(value = {"tsTest", ""})
	public String tsTest(TsStatistical tsStatistical, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/statistical/tsTest";
	}

	@RequiresPermissions("statistical:tsStatistical:view")
	@RequestMapping(value = "form")
	public String form(TsStatistical tsStatistical, Model model) {
		model.addAttribute("tsStatistical", tsStatistical);
		return "modules/statistical/tsStatisticalForm";
	}

	@RequiresPermissions("statistical:tsStatistical:edit")
	@RequestMapping(value = "save")
	public String save(TsStatistical tsStatistical, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsStatistical)){
			return form(tsStatistical, model);
		}
		tsStatisticalService.save(tsStatistical);
		addMessage(redirectAttributes, "保存统计分析成功");
		return "redirect:"+Global.getAdminPath()+"/statistical/tsStatistical/?repage";
	}
	
	@RequiresPermissions("statistical:tsStatistical:edit")
	@RequestMapping(value = "delete")
	public String delete(TsStatistical tsStatistical, RedirectAttributes redirectAttributes) {
		tsStatisticalService.delete(tsStatistical);
		addMessage(redirectAttributes, "删除统计分析成功");
		return "redirect:"+Global.getAdminPath()+"/statistical/tsStatistical/?repage";
	}

}