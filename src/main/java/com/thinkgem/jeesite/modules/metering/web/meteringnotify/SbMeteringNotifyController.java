/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.metering.web.meteringnotify;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.eos.workflow.data.WFWorkItem;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.metering.entity.meteringnotify.SbMeteringNotify;
import com.thinkgem.jeesite.modules.metering.service.meteringnotify.SbMeteringNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 测量数据追踪Controller
 * @author suntao
 * @version 2020-05-20
 */
@Controller
@RequestMapping(value = "${adminPath}/metering/meteringnotify/sbMeteringNotify")
public class SbMeteringNotifyController extends BaseController {

	@Autowired
	private SbMeteringNotifyService sbMeteringNotifyService;
	
	@Autowired
	private SbEquipmentService sbEquipmentService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SbBorrowService sbBorrowService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	
	@ModelAttribute
	public SbMeteringNotify get(@RequestParam(required=false) String id) {
		SbMeteringNotify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbMeteringNotifyService.get(id);
		}
		if (entity == null){
			entity = new SbMeteringNotify();
		}
		return entity;
	}
	
	@RequiresPermissions("metering:meteringnotify:sbMeteringNotify:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbMeteringNotify sbMeteringNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbMeteringNotify> page = sbMeteringNotifyService.findPage(new Page<SbMeteringNotify>(request, response), sbMeteringNotify); 
		model.addAttribute("page", page);
		return "modules/metering/meteringnotify/sbMeteringNotifyList";
	}

	@RequiresPermissions("metering:meteringnotify:sbMeteringNotify:view")
	@RequestMapping(value = "form")
	public String form(SbMeteringNotify sbMeteringNotify, Model model) {
		if(sbMeteringNotify.getIsNewRecord()){
			if(sbMeteringNotify.getEquipmentId()!=null && !"".equals(sbMeteringNotify.getEquipmentId())){
				SbEquipment sbEquipment = sbEquipmentService.get(sbMeteringNotify.getEquipmentId());
				if(sbEquipment.getUsepeoplename().equals("仪器库房")){
					
					sbMeteringNotify.setEquipmentName(sbEquipment.getName());
					sbMeteringNotify.setEquipmentCode(sbEquipment.getSbcode());
					sbMeteringNotify.setCreateOffice(UserUtils.getUser().getOffice().getName());
					sbMeteringNotify.setCreateDate(new Date());
					SbBorrowChild sbBorrowChild = new SbBorrowChild();
					sbBorrowChild.setEquipmentId(sbMeteringNotify.getEquipmentId());
					List<SbBorrowChild> equipmentList = sbBorrowService.findChildList(sbBorrowChild);
					String officeId="";
					String officeName="";
					Office office = new Office();
					for (SbBorrowChild sbBorrowChild2 : equipmentList) {
					
						if("".equals(officeId)){
							officeId = sbBorrowChild2.getOffice().getId();
							officeName = officeService.get(officeId).getName();
						}else{
							officeId = officeId +","+sbBorrowChild2.getOffice().getId();
							officeName = officeName+"," +officeService.get(sbBorrowChild2.getOffice().getId()).getName();
						}
					}
					office.setId(officeId);
					sbMeteringNotify.setOffice(office);
					sbMeteringNotify.setOfficeName(officeName);
					
					
				}else{
					if(sbEquipment.getTeam()!=null && !"".equals(sbEquipment.getTeam())){
						sbMeteringNotify.setOffice(officeService.get(sbEquipment.getTeam()));
					}
					sbMeteringNotify.setOfficeName(sbEquipment.getTeamname());
					sbMeteringNotify.setEquipmentName(sbEquipment.getName());
					sbMeteringNotify.setEquipmentCode(sbEquipment.getSbcode());
					sbMeteringNotify.setCreateOffice(UserUtils.getUser().getOffice().getName());
					sbMeteringNotify.setCreateDate(new Date());
				}
			}
		}
		model.addAttribute("sbMeteringNotify", sbMeteringNotify);
		return "modules/metering/meteringnotify/sbMeteringNotifyForm";
	}
	

	@RequestMapping(value = "flow")
	public String flow(WFWorkItem workItem, Model model) {
		SbMeteringNotify sbMeteringNotify = new SbMeteringNotify();
		sbMeteringNotify = sbMeteringNotifyService.getByProcessInstID(workItem.getProcessInstID());
		sbMeteringNotify.setUname(UserUtils.getUser().getName());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());
		sbMeteringNotify.setField1(currentDate);
		sbMeteringNotify.setWorkItem(workItem);
		sbMeteringNotify.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbMeteringNotify", sbMeteringNotify);
		if("manualActivity1".equals(workItem.getActivityDefID())){
			return "modules/metering/meteringnotify/sbMeteringNotifyAudit";
		}
		
		return "modules/metering/meteringnotify/sbMeteringNotifyAuditLeader";
	}

	@RequiresPermissions("metering:meteringnotify:sbMeteringNotify:edit")
	@RequestMapping(value = "save")
	public String save(SbMeteringNotify sbMeteringNotify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMeteringNotify)){
			return form(sbMeteringNotify, model);
		}
		sbMeteringNotifyService.save(sbMeteringNotify);
		addMessage(redirectAttributes, "保存测量数据追踪成功");
		return "redirect:"+Global.getAdminPath()+"/metering/meteringnotify/sbMeteringNotify/?repage";
	}
	
	
	@RequestMapping(value = "submit")
	public String submit(SbMeteringNotify sbMeteringNotify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMeteringNotify)){
			return form(sbMeteringNotify, model);
		}
		BpsManage bps = new BpsManage();
		sbMeteringNotifyService.save(sbMeteringNotify);
		try {
			bps.finishWorkItem(sbMeteringNotify.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequestMapping(value = "submitLeader")
	public String submitLeader(SbMeteringNotify sbMeteringNotify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMeteringNotify)){
			return form(sbMeteringNotify, model);
		}
		BpsManage bps = new BpsManage();
		
		Examine examine = new Examine();
		examine.setProcessinstid(sbMeteringNotify.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbMeteringNotify.getField2());
		examine.setExamineExamineisagree(sbMeteringNotify.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbMeteringNotify.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbMeteringNotify.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbMeteringNotify.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbMeteringNotify.getWorkItem().getWorkItemName());
		examineService.save(examine);
		
		sbMeteringNotifyService.save(sbMeteringNotify);
		try {
			bps.setRelativeData(sbMeteringNotify.getWorkItem().getProcessInstID(), "yesOrno", sbMeteringNotify.getWorkItem().getWorkItemType());
			bps.finishWorkItem(sbMeteringNotify.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	
	@RequestMapping(value = "saveNotify")
    @ResponseBody
    public void saveNotify(SbMeteringNotify sbMeteringNotify ,HttpServletResponse response){
		
		
		if (sbMeteringNotify == null || sbMeteringNotify.getProcessInstID()==0) {
			
			BpsManage bps = new BpsManage();
			String[] userid ;
			String[] type ;
			String[] userids = null ;
			String[] types  = null ;
	
			long pid =  0;
			try {
				pid = bps.startBPS("计量跟踪单", "com.primeton.bps.sbMeteringNotify");
				
				List<User> list = systemService.findUserByOfficeIdAndRole(sbMeteringNotify.getOffice().getId(), "bmsby");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsDD", userid, type);
				
				
				list = systemService.findUserByOfficeIdAndRole(sbMeteringNotify.getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsBM", userid, type);
				
				
				sbMeteringNotify.setProcessInstID(pid);
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
		
		
		sbMeteringNotifyService.save(sbMeteringNotify);
        renderString(response, JsonMapper.toJsonString("sucess"),"text/html");

    }
	
	@RequiresPermissions("metering:meteringnotify:sbMeteringNotify:edit")
	@RequestMapping(value = "delete")
	public String delete(SbMeteringNotify sbMeteringNotify, RedirectAttributes redirectAttributes) {
		sbMeteringNotifyService.delete(sbMeteringNotify);
		addMessage(redirectAttributes, "删除测量数据追踪成功");
		return "redirect:"+Global.getAdminPath()+"/metering/meteringnotify/sbMeteringNotify/?repage";
	}

}