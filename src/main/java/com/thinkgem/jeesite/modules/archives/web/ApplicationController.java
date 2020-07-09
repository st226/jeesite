/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.archives.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eos.workflow.data.WFWorkItem;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.archives.service.ApplicationService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 现行文件管理Controller
 * @author suntao
 * @version 2019-11-10
 */
@Controller
@RequestMapping(value = "${adminPath}/archives/application")
public class ApplicationController extends BaseController {

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	@ModelAttribute
	public Application get(@RequestParam(required=false) String id) {
		Application entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = applicationService.get(id);
		}
		if (entity == null){
			entity = new Application();
		}
		return entity;
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = {"list", ""})
	public String list(Application application, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Application> page = applicationService.findPage(new Page<Application>(request, response), application); 
		model.addAttribute("page", page);
		return "modules/archives/applicationList";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = {"listModelss"})
	public String listModels(com.thinkgem.jeesite.modules.archives.entity.Model model2, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<com.thinkgem.jeesite.modules.archives.entity.Model> page = applicationService.findModelPage(new Page<com.thinkgem.jeesite.modules.archives.entity.Model>(request, response), model2); 
		model.addAttribute("page", page);
		return "modules/archives/modelList";
	}

	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "form")
	public String form(Application application, Model model) {
		Date date = new Date();
		if(application.getId()==null || "".equals(application.getId())){
			application.setUser(UserUtils.getUser());
			application.setOffice(UserUtils.getUser().getOffice());
			application.setApllyDate(date);
		}
		model.addAttribute("application", application);
		return "modules/archives/applicationForm";
	}
	

	@RequestMapping(value = "view")
	public String view(Application application, Model model) {
		
		model.addAttribute("application", application);
		return "modules/archives/applicationView";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "modelFiles")
	public String modelFiles(com.thinkgem.jeesite.modules.archives.entity.Model model2 , Model model) {
		
		model.addAttribute("model", model2);
		return "modules/archives/modelFiles";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "flow")
	public String folw(WFWorkItem workItem, Model model) {
		System.out.println(workItem.getActivityDefID());
		Application application = applicationService.getByProcessInstID(workItem.getProcessInstID());
		application.setWorkItem(workItem);
		application.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("application", application);
		if("manualActivity".equals(workItem.getActivityDefID())){
			return "modules/archives/applicationEdit";
		}
		return "modules/archives/applicationAudit";
	}
	

	@RequestMapping(value = "submit")
	public String submit(Application application, Model model, RedirectAttributes redirectAttributes) {
		String[] userid ;
		String[] type ;
		String[] userids = null ;
		String[] types  = null ;
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(application.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(application.getApplyTypename());
		examine.setExamineExamineisagree(application.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(application.getWorkItem().getWorkItemType());
		examine.setExamineJd(application.getWorkItem().getActivityDefID());
		examine.setExamineVersion(application.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(application.getWorkItem().getWorkItemName());
		examineService.save(examine);
		
		//分发判断
		if("manualActivity3".equals(application.getWorkItem().getActivityDefID())){
			Application application3 = applicationService.get(application.getId());
		    List<com.thinkgem.jeesite.modules.archives.entity.Model>  modelList = application3.getModelList() ;
			for (int i=0 ;i<modelList.size();i++) {
				List<User> list = systemService.findUserByOfficeIdAndRole(modelList.get(i).getOffice().getId(), "bmzly");
				if(list!= null &&list.size()>0){
					userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
					type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
					userids = (String[]) ArrayUtils.addAll(userid, userids);
					types = (String[]) ArrayUtils.addAll(type, types);
					modelList.get(i).setAtlasStatus("2");
				}else{
					modelList.get(i).setAtlasStatus("1");
				}
			}
			application3.setModelList(modelList);
			applicationService.save(application3);
			
			
		}
		
		//接收判断
        if("manualActivity4".equals(application.getWorkItem().getActivityDefID())){
        	Application application3 = applicationService.get(application.getId());
		    List<com.thinkgem.jeesite.modules.archives.entity.Model>  modelList = application3.getModelList() ;
			for (int i=0 ;i<modelList.size();i++) {
			    if(UserUtils.getUser().getOffice().getId().equals(modelList.get(i).getOffice().getId())){
			    	if("N".equals(application.getWorkItem().getWorkItemType())){
						
						modelList.get(i).setAtlasStatus("3");
					}else{
						modelList.get(i).setAtlasStatus("4");
					}
			    }
				
			}
			application3.setModelList(modelList);
			applicationService.save(application3);
			
		}
		try {
			bps.setFlowRelativeData(application.getWorkItem().getProcessInstID(), "wfps", userids, types);
			if(userids==null || userids.length==0){
				bps.setRelativeData(application.getWorkItem().getProcessInstID(), "yesNo2", "N");
			}
			bps.setRelativeData(application.getWorkItem().getProcessInstID(), "yesNo", application.getWorkItem().getWorkItemType());
			bps.finishWorkItem(application.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交现行文件成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "edit")
	public String edit(Application application, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, application)){
			return form(application, model);
		}
		BpsManage bps = new BpsManage();
		try {
			bps.finishWorkItem(application.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		applicationService.save(application);
		addMessage(redirectAttributes, "保存现行文件成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}

	public String getCode(String code){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		

		String year = currentDate.split("-")[0];// 年
		
		String codex =year+"-";
		if(code!=null){
			String[] maxCodes = code.split("-");
			String maxCode = maxCodes[maxCodes.length - 1];
			int maxc = Integer.parseInt(maxCode);
			int nowc = maxc + 1;
			int len = String.valueOf(nowc).length();
			int siz = 4 - len;
			if (siz == 0)
				codex = codex + String.valueOf(nowc);
			if (siz == 1)
				codex = codex + "0" + String.valueOf(nowc);
			if (siz == 2)
				codex = codex + "00" + String.valueOf(nowc);
			if (siz == 3)
				codex = codex + "000" + String.valueOf(nowc);
		
			
		}else{
			
			codex = codex+"0001";
		}
		
		return codex ;	
	}
	

	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "save")
	public String save(Application application, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, application)){
			return form(application, model);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		

		String year = currentDate.split("-")[0];// 年
		
		if (application.getProcessinstid() == null || application.getProcessinstid() == 0) {
			String code = applicationService.getCode(year);
			code = getCode(code);
			System.out.println(code+"---");
			BpsManage bps = new BpsManage();
			String[] userid ;
			String[] type ;
			String[] userids = null ;
			String[] types  = null ;
			List<com.thinkgem.jeesite.modules.archives.entity.Model> models = application.getModelList();
			long pid = 0;
			try {
				pid = bps.startBPS("现行文件申请", "com.primeton.bps.archives");
				
				List<User> list = systemService.findUserByOfficeIdAndRole(UserUtils.getUser().getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "BMwfps", userid, type);
				
				list = systemService.findUserByOfficeCodeAndRole("xmb", "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "XMwfps", userid, type);
				
				list = systemService.findUserByRole("xhwh");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "DAwfps", userid, type);
				
				
				
				
				
				
				application.setProcessinstid(pid);
				application.setCode(code);
				WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
				bps.finishWorkItem(workitem);
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WFReasonableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(application.getProcessinstid()+"MMMMMM");
		applicationService.save(application);
		addMessage(redirectAttributes, "保存现行文件成功");
		return "redirect:"+Global.getAdminPath()+"/archives/application/?repage";
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "delete")
	public String delete(Application application, RedirectAttributes redirectAttributes) {
		applicationService.delete(application);
		addMessage(redirectAttributes, "删除现行文件成功");
		return "redirect:"+Global.getAdminPath()+"/archives/application/?repage";
	}
	
	
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "updateState")
    @ResponseBody
	public void updateState(com.thinkgem.jeesite.modules.archives.entity.Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		com.thinkgem.jeesite.modules.archives.entity.Model model2 = new com.thinkgem.jeesite.modules.archives.entity.Model();
		model2.setId(model.getAtlasPid());
		model2 = applicationService.getModel(model2);
		model2.setAtlasStatus("5");
		applicationService.saveModel(model2);
		addMessage(redirectAttributes, "销毁成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "updateFiles")
    @ResponseBody
	public void updateFiles(com.thinkgem.jeesite.modules.archives.entity.Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		com.thinkgem.jeesite.modules.archives.entity.Model model2 = new com.thinkgem.jeesite.modules.archives.entity.Model();
		model2.setId(model.getAtlasPid());
		model2 = applicationService.getModel(model2);
		model2.setFiles(model.getFiles());
		applicationService.saveModel(model2);
		addMessage(redirectAttributes, "分发成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	
	
	

}