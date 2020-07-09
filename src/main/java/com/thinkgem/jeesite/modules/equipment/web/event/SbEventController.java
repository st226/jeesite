/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eos.workflow.data.WFWorkItem;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.event.SbEvent;
import com.thinkgem.jeesite.modules.equipment.entity.event.SbEventChild;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.equipment.service.event.SbEventService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 借用事件Controller
 * @author suntao
 * @version 2020-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/event/sbEvent")
public class SbEventController extends BaseController {

	@Autowired
	private SbEventService sbEventService;
	
	@Autowired
	private SbBorrowService sbBorrowService;
	
	@Autowired
	private SbEquipmentService sbEquipmentService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	@ModelAttribute
	public SbEvent get(@RequestParam(required=false) String id) {
		SbEvent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEventService.get(id);
		}
		if (entity == null){
			entity = new SbEvent();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEvent sbEvent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbEvent> page = sbEventService.findPage(new Page<SbEvent>(request, response), sbEvent); 
		model.addAttribute("page", page);
		return "modules/equipment/event/sbEventList";
	}

	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = "form")
	public String form(SbEvent sbEvent, Model model) {
		model.addAttribute("sbEvent", sbEvent);
		return "modules/equipment/event/sbEventForm";
	}
	
	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = "renew")
	public String modelFiles(String ids , Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		SbEvent sbEvent = new SbEvent();
		sbEvent.setUser(UserUtils.getUser());
		sbEvent.setOffice(UserUtils.getUser().getOffice());
		SbEventChild child = new SbEventChild();
		SbEquipment equipment = new SbEquipment();
		SbBorrowChild borrowChild = new SbBorrowChild();
		List<SbEventChild> sbEventChildList = new ArrayList<SbEventChild>();
		System.out.println(ids);
		String id[] = ids.split(",");
		for (String temp : id) {
			borrowChild = sbBorrowService.getChild(temp);
			equipment = sbEquipmentService.get(borrowChild.getEquipmentId());
			child.setField1(temp);
			child.setEquipmentName(equipment.getName());
			child.setEquipmentType(equipment.getType());
			child.setEquipmentCccode(equipment.getCccode());
			child.setEquipmentSbcode(equipment.getSbcode());
			if("1".equals(equipment.getField5())){
				child.setIsmeasurement("是");
			}else{
				child.setIsmeasurement("否");
			}
			
			child.setEquipmentId(equipment.getId());
			if(equipment.getField6()!=null && !"".equals(equipment.getField6())){
				try {
					child.setMeasurement( format.parse(equipment.getField6()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sbEventChildList.add(child);
			child = new SbEventChild();
		}
		sbEvent.setSbEventChildList(sbEventChildList);
		model.addAttribute("sbEvent", sbEvent);
		return "modules/equipment/event/sbEventForm";
	}
	
	
	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = "lose")
	public String lose(String ids , Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		SbEvent sbEvent = new SbEvent();
		sbEvent.setUser(UserUtils.getUser());
		sbEvent.setOffice(UserUtils.getUser().getOffice());
		SbEventChild child = new SbEventChild();
		SbEquipment equipment = new SbEquipment();
		SbBorrowChild borrowChild = new SbBorrowChild();
		List<SbEventChild> sbEventChildList = new ArrayList<SbEventChild>();
		System.out.println(ids);
		String id[] = ids.split(",");
		for (String temp : id) {
			borrowChild = sbBorrowService.getChild(temp);
			equipment = sbEquipmentService.get(borrowChild.getEquipmentId());
			child.setField1(temp);
			child.setEquipmentName(equipment.getName());
			child.setEquipmentType(equipment.getType());
			child.setEquipmentCccode(equipment.getCccode());
			child.setEquipmentSbcode(equipment.getSbcode());
			if("1".equals(equipment.getField5())){
				child.setIsmeasurement("是");
			}else{
				child.setIsmeasurement("否");
			}
			
			child.setEquipmentId(equipment.getId());
			if(equipment.getField6()!=null && !"".equals(equipment.getField6())){
				try {
					child.setMeasurement( format.parse(equipment.getField6()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sbEventChildList.add(child);
			child = new SbEventChild();
		}
		sbEvent.setSbEventChildList(sbEventChildList);
		model.addAttribute("sbEvent", sbEvent);
		return "modules/equipment/event/sbLoseForm";
	}
	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = "damage")
	public String damage(String ids , Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		SbEvent sbEvent = new SbEvent();
		sbEvent.setUser(UserUtils.getUser());
		sbEvent.setOffice(UserUtils.getUser().getOffice());
		SbEventChild child = new SbEventChild();
		SbEquipment equipment = new SbEquipment();
		SbBorrowChild borrowChild = new SbBorrowChild();
		List<SbEventChild> sbEventChildList = new ArrayList<SbEventChild>();
		System.out.println(ids);
		String id[] = ids.split(",");
		for (String temp : id) {
			borrowChild = sbBorrowService.getChild(temp);
			equipment = sbEquipmentService.get(borrowChild.getEquipmentId());
			child.setField1(temp);
			child.setEquipmentName(equipment.getName());
			child.setEquipmentType(equipment.getType());
			child.setEquipmentCccode(equipment.getCccode());
			child.setEquipmentSbcode(equipment.getSbcode());
			if("1".equals(equipment.getField5())){
				child.setIsmeasurement("是");
			}else{
				child.setIsmeasurement("否");
			}
			
			child.setEquipmentId(equipment.getId());
			if(equipment.getField6()!=null && !"".equals(equipment.getField6())){
				try {
					child.setMeasurement( format.parse(equipment.getField6()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sbEventChildList.add(child);
			child = new SbEventChild();
		}
		sbEvent.setSbEventChildList(sbEventChildList);
		model.addAttribute("sbEvent", sbEvent);
		return "modules/equipment/event/sbDamageForm";
	}
	
	@RequiresPermissions("equipment:event:sbEvent:view")
	@RequestMapping(value = "lending")
	public String lending(String ids , Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		SbEvent sbEvent = new SbEvent();
		
		SbEventChild child = new SbEventChild();
		SbEquipment equipment = new SbEquipment();
		SbBorrowChild borrowChild = new SbBorrowChild();
		List<SbEventChild> sbEventChildList = new ArrayList<SbEventChild>();
		System.out.println(ids);
		String id[] = ids.split(",");
		for (String temp : id) {
			borrowChild = sbBorrowService.getChild(temp);
			equipment = sbEquipmentService.get(borrowChild.getEquipmentId());
			child.setField1(temp);
			child.setEquipmentName(equipment.getName());
			child.setEquipmentType(equipment.getType());
			child.setEquipmentCccode(equipment.getCccode());
			child.setEquipmentSbcode(equipment.getSbcode());
			if("1".equals(equipment.getField5())){
				child.setIsmeasurement("是");
			}else{
				child.setIsmeasurement("否");
			}
			
			child.setEquipmentId(equipment.getId());
			if(equipment.getField6()!=null && !"".equals(equipment.getField6())){
				try {
					child.setMeasurement( format.parse(equipment.getField6()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sbEventChildList.add(child);
			child = new SbEventChild();
		}
		sbEvent.setSbEventChildList(sbEventChildList);
		model.addAttribute("sbEvent", sbEvent);
		return "modules/equipment/event/sbLendingForm";
	}
	
	@RequestMapping(value = "renewAudit")
	public String renewAudit(WFWorkItem workItem, Model model) {
		SbEvent sbEvent = sbEventService.getByProcessInstID(workItem.getProcessInstID());
		sbEvent.setWorkItem(workItem);
		sbEvent.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbEvent", sbEvent);
		if(workItem.getActivityDefID()==null || "".equals(workItem.getActivityDefID())){
			return "modules/equipment/event/renewView";
		}
		return "modules/equipment/event/renewAudit";
	
	}
	
	@RequestMapping(value = "lendingAudit")
	public String lendingAudit(WFWorkItem workItem, Model model) {
		SbEvent sbEvent = sbEventService.getByProcessInstID(workItem.getProcessInstID());
		sbEvent.setWorkItem(workItem);
		sbEvent.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbEvent", sbEvent);
		if(workItem.getActivityDefID()==null || "".equals(workItem.getActivityDefID())){
			return "modules/equipment/event/lendingView";
		}
		return "modules/equipment/event/lendingAudit";
	
	}
	
	@RequestMapping(value = "loseAudit")
	public String loseAudit(WFWorkItem workItem, Model model) {
		SbEvent sbEvent = sbEventService.getByProcessInstID(workItem.getProcessInstID());
		sbEvent.setWorkItem(workItem);
		sbEvent.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbEvent", sbEvent);
		if(workItem.getActivityDefID()==null || "".equals(workItem.getActivityDefID())){
			return "modules/equipment/event/loseView";
		}
		return "modules/equipment/event/loseAudit";
	
	}

	@RequiresPermissions("equipment:event:sbEvent:edit")
	@RequestMapping(value = "save")
	public String save(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEvent)){
			return form(sbEvent, model);
		}
		BpsManage bps = new BpsManage();
		long pid = 0;
		String[] userid ;
		String[] type ;
		if (sbEvent.getProcessinstid() == null || sbEvent.getProcessinstid() == 0) {
			try {
				pid = bps.startBPS("设备续借申请", "com.primeton.bps.Renew");
				List<User> list  = systemService.findUserByRole("yqsbgly");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsgl", userid, type);
				
				sbEvent.setProcessinstid(pid);
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
		sbEventService.save(sbEvent);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/MyBorrow/?repage";
	}
	
	@RequiresPermissions("equipment:event:sbEvent:edit")
	@RequestMapping(value = "saveLending")
	public String saveLending(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEvent)){
			return form(sbEvent, model);
		}
		BpsManage bps = new BpsManage();
		long pid = 0;
	//	String[] userid ;
	//	String[] type ;
		System.out.println(sbEvent.getProcessinstid());
		if (sbEvent.getProcessinstid() == null || sbEvent.getProcessinstid() == 0) {
			try {
				pid = bps.startBPS("设备转借申请", "com.primeton.bps.Lending");
				String[] userid = {sbEvent.getUser().getId()};
				String[] type = {"emp"};
				bps.setFlowRelativeData(pid, "wfps", userid, type);
				
				sbEvent.setProcessinstid(pid);
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
		sbEventService.save(sbEvent);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/MyBorrow/?repage";
	}
	
	@RequiresPermissions("equipment:event:sbEvent:edit")
	@RequestMapping(value = "saveLose")
	public String saveLose(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbEvent)){
			return form(sbEvent, model);
		}
		BpsManage bps = new BpsManage();
		long pid = 0;
		String[] userid ;
		String[] type ;
		System.out.println(sbEvent.getProcessinstid());
		if (sbEvent.getProcessinstid() == null || sbEvent.getProcessinstid() == 0) {
			try {
				pid = bps.startBPS("设备丢失（损坏）", "com.primeton.bps.Lose");
				List<User> list = systemService.findUserByOfficeIdAndRole(UserUtils.getUser().getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpBM", userid, type);
				
				list = systemService.findUserByRole("yqsbgly");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsgl", userid, type);
				
				sbEvent.setProcessinstid(pid);
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
		sbEventService.save(sbEvent);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/MyBorrow/?repage";
	}
	
	@RequestMapping(value = "submit")
	public String submit(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(sbEvent.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbEvent.getField1());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbEvent.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbEvent.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemName());
		
		if("Y".equals(sbEvent.getWorkItem().getWorkItemType())){
			SbEquipment sbEquipment = new SbEquipment();
			SbBorrowChild sbBorrowChild = new SbBorrowChild();
			List<SbEventChild> sbEventChildList = sbEvent.getSbEventChildList();
			for (SbEventChild sbEventChild : sbEventChildList) {
				sbBorrowChild = sbBorrowService.getChild(sbEventChild.getField1());
				
				sbBorrowChild.setBorrowState("4");
				sbBorrowChild.setEndDate(sbEvent.getEndDate());
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("4");
				sbBorrowService.saveChild(sbBorrowChild);
				sbEquipmentService.save(sbEquipment);
			}
		
		}
		
		examineService.save(examine);

		try {
			bps.finishWorkItem(sbEvent.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequestMapping(value = "submitLending")
	public String submitLending(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(sbEvent.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbEvent.getField1());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbEvent.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbEvent.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemName());
		
		if("Y".equals(sbEvent.getWorkItem().getWorkItemType())){
			SbEquipment sbEquipment = new SbEquipment();
			SbBorrowChild sbBorrowChild = new SbBorrowChild();
			SbBorrowChild sbBorrowChildx = new SbBorrowChild();
			List<SbEventChild> sbEventChildList = sbEvent.getSbEventChildList();
			for (SbEventChild sbEventChild : sbEventChildList) {
				sbBorrowChild = sbBorrowService.getChild(sbEventChild.getField1());
				sbBorrowChild.setBorrowState("6");
				sbBorrowChild.setEndDate(sbEvent.getEndDate());
				sbBorrowChildx.setBorrowId(sbBorrowChild.getBorrowId());
				sbBorrowChildx.setEquipmentId(sbBorrowChild.getEquipmentId());
				sbBorrowChildx.setEquipmentName(sbBorrowChild.getEquipmentName());
				sbBorrowChildx.setEquipmentType(sbBorrowChild.getEquipmentType());
				sbBorrowChildx.setEquipmentSbcode(sbBorrowChild.getEquipmentSbcode());
				sbBorrowChildx.setEquipmentCccode(sbBorrowChild.getEquipmentCccode());
				sbBorrowChildx.setMeasurement(sbBorrowChild.getMeasurement());
				sbBorrowChildx.setIsmeasurement(sbBorrowChild.getIsmeasurement());
				sbBorrowChildx.setBorrowState("3");
				sbBorrowChildx.setCreateBy(UserUtils.getUser());
				sbBorrowChildx.setCreateDate(new Date());
				sbBorrowChildx.setStatrDate(new Date());
				sbBorrowChildx.setEndDate(sbEvent.getEndDate());
				sbBorrowChildx.setIsGood(sbBorrowChild.getIsGood());
				sbBorrowChildx.setReason(sbBorrowChild.getReason());
				sbBorrowChildx.setUser(sbEvent.getUser());
				sbBorrowChildx.setOffice(sbEvent.getOffice());
				sbBorrowChildx.setEndIsGood("1");
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("3");
				sbBorrowService.saveChild(sbBorrowChild);
				sbBorrowService.insertChild(sbBorrowChildx);
				sbEquipmentService.save(sbEquipment);
			}
		
		}
		
		examineService.save(examine);

		try {
			bps.finishWorkItem(sbEvent.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequestMapping(value = "submitLose")
	public String submitLose(SbEvent sbEvent, Model model, RedirectAttributes redirectAttributes) {
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(sbEvent.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbEvent.getField1());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbEvent.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbEvent.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbEvent.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbEvent.getWorkItem().getWorkItemName());
		
		if("manualActivity1".equals(sbEvent.getWorkItem().getActivityDefID())){
			SbEquipment sbEquipment = new SbEquipment();
			SbBorrowChild sbBorrowChild = new SbBorrowChild();
			List<SbEventChild> sbEventChildList = sbEvent.getSbEventChildList();
			for (SbEventChild sbEventChild : sbEventChildList) {
				sbBorrowChild = sbBorrowService.getChild(sbEventChild.getField1());
				
				sbBorrowChild.setBorrowState("9");
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("9");
				sbBorrowService.saveChild(sbBorrowChild);
				sbEquipmentService.save(sbEquipment);
			}
		
		}
		
		examineService.save(examine);

		try {
			bps.finishWorkItem(sbEvent.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequiresPermissions("equipment:event:sbEvent:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEvent sbEvent, RedirectAttributes redirectAttributes) {
		sbEventService.delete(sbEvent);
		addMessage(redirectAttributes, "删除借用事件成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/event/sbEvent/?repage";
	}

}