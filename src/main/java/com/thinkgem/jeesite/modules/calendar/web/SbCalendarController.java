/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.calendar.entity.SbCalendar;
import com.thinkgem.jeesite.modules.calendar.service.SbCalendarService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.task.entity.TsTask;

/**
 * 日程信息Controller
 * @author suntao
 * @version 2020-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/calendar/sbCalendar")
public class SbCalendarController extends BaseController {

	@Autowired
	private SbCalendarService sbCalendarService;
	
	@ModelAttribute
	public SbCalendar get(@RequestParam(required=false) String id) {
		SbCalendar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbCalendarService.get(id);
		}
		if (entity == null){
			entity = new SbCalendar();
		}
		return entity;
	}
	
	@RequiresPermissions("calendar:sbCalendar:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbCalendar sbCalendar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbCalendar> page = sbCalendarService.findPage(new Page<SbCalendar>(request, response), sbCalendar); 
		model.addAttribute("page", page);
		return "modules/calendar/sbCalendarList";
	}

	@RequiresPermissions("calendar:sbCalendar:view")
	@RequestMapping(value = "form")
	public String form(SbCalendar sbCalendar, Model model) {
		sbCalendar.setUser(UserUtils.getUser());
		sbCalendar.setUserName(UserUtils.getUser().getName());
		model.addAttribute("sbCalendar", sbCalendar);
		return "modules/calendar/sbCalendarForm";
	}

	@RequiresPermissions("calendar:sbCalendar:edit")
	@RequestMapping(value = "save")
	public String save(SbCalendar sbCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbCalendar)){
			return form(sbCalendar, model);
		}
		sbCalendarService.save(sbCalendar);
		addMessage(redirectAttributes, "保存日程信息成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/sbCalendar/?repage";
	}
	
	@RequiresPermissions("calendar:sbCalendar:edit")
	@RequestMapping(value = "delete")
	public String delete(SbCalendar sbCalendar, RedirectAttributes redirectAttributes) {
		sbCalendarService.delete(sbCalendar);
		addMessage(redirectAttributes, "删除日程信息成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/sbCalendar/?repage";
	}
	
	//日程
		@RequestMapping(value = {"calendar"})
		public String calendar(TsTask tsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
			
			return "modules/sys/homePage/calendar";
		}
		
		@RequestMapping(value = {"calendarInfo"})
		public String calendarInfo(TsTask tsTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		
			return "modules/sys/homePage/calendarInfo";
		}
		
		@RequestMapping(value = "getCalendarInfo")
	    @ResponseBody
	    public void getCalendarInfo(String busTableType,HttpServletResponse response){
		    
			SbCalendar sbCalendar =new SbCalendar();
			sbCalendar.setUser(UserUtils.getUser());
			List<SbCalendar> list = sbCalendarService.findList(sbCalendar);
	

	        renderString(response, JsonMapper.toJsonString(list),"text/html");

	    }

}