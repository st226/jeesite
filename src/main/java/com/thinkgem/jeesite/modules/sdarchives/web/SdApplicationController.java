/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sdarchives.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMeteringChild;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdApplication;
import com.thinkgem.jeesite.modules.sdarchives.entity.SdModel;
import com.thinkgem.jeesite.modules.sdarchives.service.SdApplicationService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 三单维护Controller
 * @author suntao
 * @version 2019-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sdarchives/sdApplication")
public class SdApplicationController extends BaseController {

	@Autowired
	private SdApplicationService sdApplicationService;
	
	@Autowired
	private TsResourceTypeService tsResourceTypeService;
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@ModelAttribute
	public SdApplication get(@RequestParam(required=false) String id) {
		SdApplication entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sdApplicationService.get(id);
		}
		if (entity == null){
			entity = new SdApplication();
		}
		return entity;
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"plList"})
	public String plList(SdApplication sdApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdApplication.setSdType("pl");
		Page<SdApplication> page = sdApplicationService.findPage(new Page<SdApplication>(request, response), sdApplication); 
		model.addAttribute("page", page);
		return "modules/sdarchives/sdApplicationList";
	}
	
	@RequestMapping(value = {"updateModel"})
	public String updateModel(SdModel sdmodel, HttpServletRequest request, HttpServletResponse response, Model model) {
		SdModel temp = new SdModel();
		temp.setId(sdmodel.getBjjd());
		SdModel sdmodelx = sdApplicationService.getModel(temp);
		model.addAttribute("sdmodel", sdmodelx);
		return "modules/sdarchives/updateModel";
	}
	
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"plModelList"})
	public String plModelList(SdModel sdModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdModel.setSdType("pl");
		Page<SdModel> page = sdApplicationService.findModelPage(new Page<SdModel>(request, response), sdModel); 
		model.addAttribute("page", page);
		return "modules/sdarchives/plModelList";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"ggModelList"})
	public String ggModelList(SdModel sdModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdModel.setSdType("gg");
		Page<SdModel> page = sdApplicationService.findModelPage(new Page<SdModel>(request, response), sdModel); 
		model.addAttribute("page", page);
		return "modules/sdarchives/ggModelList";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"cdzModelList"})
	public String cdzModelList(SdModel sdModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdModel.setSdType("cdz");
		Page<SdModel> page = sdApplicationService.findModelPage(new Page<SdModel>(request, response), sdModel); 
		model.addAttribute("page", page);
		return "modules/sdarchives/cdzModelList";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"cdzList"})
	public String cdzList(SdApplication sdApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdApplication.setSdType("cdz");
		Page<SdApplication> page = sdApplicationService.findPage(new Page<SdApplication>(request, response), sdApplication); 
		model.addAttribute("page", page);
		return "modules/sdarchives/cdzApplicationList";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = {"ggList"})
	public String ggList(SdApplication sdApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		sdApplication.setSdType("gg");
		Page<SdApplication> page = sdApplicationService.findPage(new Page<SdApplication>(request, response), sdApplication); 
		model.addAttribute("page", page);
		return "modules/sdarchives/ggApplicationList";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = {"sdModels"})
	public String sdModels(SdModel sdModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SdModel> page = sdApplicationService.findModelPage(new Page<SdModel>(request, response), sdModel); 
		model.addAttribute("page", page);
		return "modules/sdarchives/sdModels";
	}

	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = "form")
	public String form(SdApplication sdApplication, Model model) {
		if(sdApplication.getId()==null || "".equals(sdApplication.getId())){
			sdApplication.setUser(UserUtils.getUser());
			sdApplication.setOffice(UserUtils.getUser().getOffice());
			sdApplication.setApllyDate(new Date());
		}
		model.addAttribute("sdApplication", sdApplication);
		return "modules/sdarchives/sdApplicationForm";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = "cdzform")
	public String cdzform(SdApplication sdApplication, Model model) {
		if(sdApplication.getId()==null || "".equals(sdApplication.getId())){
			sdApplication.setUser(UserUtils.getUser());
			sdApplication.setOffice(UserUtils.getUser().getOffice());
			sdApplication.setApllyDate(new Date());
		}
		model.addAttribute("sdApplication", sdApplication);
		return "modules/sdarchives/cdzApplicationForm";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:view")
	@RequestMapping(value = "ggform")
	public String ggform(SdApplication sdApplication, Model model) {
		if(sdApplication.getId()==null || "".equals(sdApplication.getId())){
			sdApplication.setUser(UserUtils.getUser());
			sdApplication.setOffice(UserUtils.getUser().getOffice());
			sdApplication.setApllyDate(new Date());
		}
		model.addAttribute("sdApplication", sdApplication);
		return "modules/sdarchives/ggApplicationForm";
	}

	@RequiresPermissions("sdarchives:sdApplication:edit")
	@RequestMapping(value = "save")
	public String save(SdApplication sdApplication, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sdApplication)){
			return form(sdApplication, model);
		}
		List<SdModel> models = sdApplication.getSdModelList();
		if (sdApplication.getProcessinstid() == null || sdApplication.getProcessinstid() == 0) {
			BpsManage bps = new BpsManage();
			String[] userid ;
			String[] type ;
			String[] userids = null ;
			String[] types  = null ;
			
			long pid = 0;
			try {
				pid = bps.startBPS("三单发放", "com.primeton.bps.provide");
				
		
				for (int i=0 ;i<sdApplication.getSdModelList().size();i++) {
					String[] officeids = models.get(i).getOffice().getId().split(",") ;
					for (String officeid : officeids) {
						List<User> list = systemService.findUserByOfficeIdAndRole(officeid, "bmzly");
						if(list!= null &&list.size()>0){
							userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
							type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
							userids = (String[]) ArrayUtils.addAll(userid, userids);
							types = (String[]) ArrayUtils.addAll(type, types);
							models.get(i).setAtlasStatus("2");
						}else{
							models.get(i).setAtlasStatus("1");
							
						}
						
						bps.setFlowRelativeData(pid, "wfps", userids, types);
						WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
						bps.finishWorkItem(workitem);
						
					}
					
					
				}
	
				sdApplication.setProcessinstid(pid);
		
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WFReasonableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i=0 ;i<sdApplication.getSdModelList().size();i++) {

			if(models.get(i).getModelId()!=null && !"".equals(models.get(i).getModelId()))
			models.get(i).setModelName(tsResourceTypeService.get(models.get(i).getModelId()).getName());
		
		}
		sdApplication.setSdModelList(models);
		sdApplicationService.save(sdApplication);
		addMessage(redirectAttributes, "保存三单维护成功");
		if("cdz".equals(sdApplication.getSdType())){
			return "redirect:"+Global.getAdminPath()+"/sdarchives/sdApplication/cdzList?repage";
		}
        if("gg".equals(sdApplication.getSdType())){
        	return "redirect:"+Global.getAdminPath()+"/sdarchives/sdApplication/ggList?repage";
		}
		return "redirect:"+Global.getAdminPath()+"/sdarchives/sdApplication/plList?repage";
	}
	
	@RequestMapping(value = "saveModel")
	public void saveModel(SdModel sdModel , Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SdModel temp = new SdModel();
		temp.setId(sdModel.getBjjd());
		SdModel sdmodelx = sdApplicationService.getModel(temp);
		sdmodelx.setFiles(sdModel.getFiles());
		sdApplicationService.saveModel(sdmodelx);
		
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "sdflow")
	public String sdfolw(WFWorkItem workItem, Model model) {
		
		SdApplication sdApplication = sdApplicationService.getByProcessInstID(workItem.getProcessInstID());
		sdApplication.setWorkItem(workItem);
		model.addAttribute("sdApplication", sdApplication);
		
		if("gg".equals(sdApplication.getSdType())){
			return "modules/sdarchives/sdggApplicationAudit";
		}
		if("cdz".equals(sdApplication.getSdType())){
			return "modules/sdarchives/sdcdzApplicationAudit";
		}


	return "modules/sdarchives/sdApplicationAudit";
	}

     
	
	
	@RequestMapping(value = "submit")
	public String submit(Application application, Model model, RedirectAttributes redirectAttributes) {
		
		BpsManage bps = new BpsManage();
		try {
			
			bps.finishWorkItem(application.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "三单接收成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequiresPermissions("sdarchives:sdApplication:edit")
	@RequestMapping(value = "delete")
	public String delete(SdApplication sdApplication, RedirectAttributes redirectAttributes) {
		sdApplicationService.delete(sdApplication);
		addMessage(redirectAttributes, "删除三单维护成功");
		return "redirect:"+Global.getAdminPath()+"/sdarchives/sdApplication/plList?repage";
	}
	
	
	
	@RequestMapping(value = "deleteModel")
	public String deleteModel(SdModel sdModel, RedirectAttributes redirectAttributes) {
		sdModel.setId(sdModel.getOn());
		sdApplicationService.deleteModel(sdModel);
		addMessage(redirectAttributes, "删除三单维护成功");
		return "redirect:"+Global.getAdminPath()+"/sdarchives/sdApplication/"+sdModel.getModelName();
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "updateState")
    @ResponseBody
	public void updateState(SdModel sdModel, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SdModel model2 = new SdModel();
		model2.setId(sdModel.getImageNo());
		model2 = sdApplicationService.getModel(model2) ;
		model2.setAtlasStatus(sdModel.getAtlasStatus());
		sdApplicationService.saveModel(model2);
		addMessage(redirectAttributes, "销毁成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "updateFiles")
    @ResponseBody
	public void updateFiles(SdModel sdmodel, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SdModel model2 = new SdModel();
		model2.setId(sdmodel.getImageNo());
		model2 = sdApplicationService.getModel(model2);
		System.out.println(sdmodel.getFiles());
		model2.setFiles(sdmodel.getFiles());
		sdApplicationService.saveModel(model2);
		addMessage(redirectAttributes, "销毁成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "modelFiles")
	public String modelFiles(SdModel sdmodel , Model model) {
		
		model.addAttribute("sdmodel", sdmodel);
		return "modules/sdarchives/sdmodelFiles";
	}
	
	

}