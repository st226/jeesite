/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.suntao_test.web;

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
import com.thinkgem.jeesite.modules.suntao_test.entity.SuntaoTest;
import com.thinkgem.jeesite.modules.suntao_test.service.SuntaoTestService;

/**
 * 测试主子表Controller
 * @author suntao
 * @version 2020-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/suntao_test/suntaoTest")
public class SuntaoTestController extends BaseController {

	@Autowired
	private SuntaoTestService suntaoTestService;
	
	@ModelAttribute
	public SuntaoTest get(@RequestParam(required=false) String id) {
		SuntaoTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = suntaoTestService.get(id);
		}
		if (entity == null){
			entity = new SuntaoTest();
		}
		return entity;
	}
	
	@RequiresPermissions("suntao_test:suntaoTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(SuntaoTest suntaoTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SuntaoTest> page = suntaoTestService.findPage(new Page<SuntaoTest>(request, response), suntaoTest); 
		model.addAttribute("page", page);
		return "modules/suntao_test/suntaoTestList";
	}

	@RequiresPermissions("suntao_test:suntaoTest:view")
	@RequestMapping(value = "form")
	public String form(SuntaoTest suntaoTest, Model model) {
		model.addAttribute("suntaoTest", suntaoTest);
		return "modules/suntao_test/suntaoTestForm";
	}

	@RequiresPermissions("suntao_test:suntaoTest:edit")
	@RequestMapping(value = "save")
	public String save(SuntaoTest suntaoTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, suntaoTest)){
			return form(suntaoTest, model);
		}
		suntaoTestService.save(suntaoTest);
		addMessage(redirectAttributes, "保存测试主子表成功");
		return "redirect:"+Global.getAdminPath()+"/suntao_test/suntaoTest/?repage";
	}
	
	@RequiresPermissions("suntao_test:suntaoTest:edit")
	@RequestMapping(value = "delete")
	public String delete(SuntaoTest suntaoTest, RedirectAttributes redirectAttributes) {
		suntaoTestService.delete(suntaoTest);
		addMessage(redirectAttributes, "删除测试主子表成功");
		return "redirect:"+Global.getAdminPath()+"/suntao_test/suntaoTest/?repage";
	}

}