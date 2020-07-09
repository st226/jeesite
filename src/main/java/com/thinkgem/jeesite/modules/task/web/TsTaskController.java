/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.calendar.entity.SbCalendar;
import com.thinkgem.jeesite.modules.calendar.service.SbCalendarService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.task.entity.TsTask;
import com.thinkgem.jeesite.modules.task.service.TsTaskService;

/**
 * 采集任务管理Controller
 * @author suntao
 * @version 2018-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/task/tsTask")
public class TsTaskController extends BaseController {

	@Autowired
	private TsTaskService tsTaskService;
	
	@Autowired
	private SbCalendarService sbCalendarService;
	
	@ModelAttribute
	public TsTask get(@RequestParam(required=false) String id) {
		TsTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsTaskService.get(id);
		}
		if (entity == null){
			entity = new TsTask();
		}
		return entity;
	}
	
	@RequiresPermissions("task:tsTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsTask tsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		tsTask.setOfficeid(user.getOffice().getName());
		tsTask.setState("1");
		Page<TsTask> page = tsTaskService.findPage(new Page<TsTask>(request, response), tsTask); 
		model.addAttribute("page", page);
		return "modules/sys/homePage/indexHome";
	}
	
	
	
	@RequiresPermissions("task:tsTask:view")
	@RequestMapping(value = {"indexChart"})
	public String indexChart(TsTask tsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		tsTask.setOfficeid(user.getOffice().getName());
		tsTask.setState("1");
		Page<TsTask> page = tsTaskService.findPage(new Page<TsTask>(request, response), tsTask); 
		model.addAttribute("page", page);
		return "modules/sys/homePage/indexChart";
	}

	@RequiresPermissions("task:tsTask:view")
	@RequestMapping(value = "form")
	public String form(TsTask tsTask, Model model) {
		model.addAttribute("tsTask", tsTask);
		return "modules/task/tsTaskForm";
	}

	@RequiresPermissions("task:tsTask:edit")
	@RequestMapping(value = "save")
	public String save(TsTask tsTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsTask)){
			return form(tsTask, model);
		}
		tsTaskService.save(tsTask);
		addMessage(redirectAttributes, "保存采集任务管理成功");
		return "redirect:"+Global.getAdminPath()+"/task/tsTask/?repage";
	}
	
	@RequiresPermissions("task:tsTask:edit")
	@RequestMapping(value = "delete")
	public String delete(TsTask tsTask, RedirectAttributes redirectAttributes) {
		tsTask = tsTaskService.get(tsTask.getId());
		tsTask.setState("2");
		tsTaskService.save(tsTask);
		addMessage(redirectAttributes, "采集任务成功");
		return "redirect:"+Global.getAdminPath()+"/task/tsTask/?repage";
	}
	
	
	

}