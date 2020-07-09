/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.metering;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceive;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMetering;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMeteringChild;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentbus.SbEquipmentBusService;
import com.thinkgem.jeesite.modules.equipment.service.metering.SbMeteringService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 计量管理Controller
 * @author suntao
 * @version 2020-01-01
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/metering/sbMetering")
public class SbMeteringController extends BaseController {

	@Autowired
	private SbMeteringService sbMeteringService;
	
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
	public SbMetering get(@RequestParam(required=false) String id) {
		SbMetering entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbMeteringService.get(id);
		}
		if (entity == null){
			entity = new SbMetering();
		}
		return entity;
	}
	
	@RequiresPermissions("equipment:metering:sbMetering:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbMetering sbMetering, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbMetering> page = sbMeteringService.findPage(new Page<SbMetering>(request, response), sbMetering); 
		model.addAttribute("page", page);
		return "modules/equipment/metering/sbMeteringList";
	}
	
	@RequestMapping(value = "formEquipment")
	public String formEquipment(SbEquipment sbEquipment, Model model) {
		if(sbEquipment.getField1()!=null && !"".equals(sbEquipment.getField1())){
			sbEquipment = sbEquipmentService.get(sbEquipment.getField1());
		}else{
			
			sbEquipment.setField5("1");
			sbEquipment.setField12("1");
			sbEquipment.setField11("1");
			sbEquipment.setField17("1");
		}
		
		model.addAttribute("sbEquipment", sbEquipment);
		return "modules/equipment/metering/sbEquipmentForm";
		
		
	}

	@RequiresPermissions("equipment:metering:sbMetering:view")
	@RequestMapping(value = "form")
	public String form(SbMetering sbMetering, Model model) {
		model.addAttribute("sbMetering", sbMetering);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
		if(sbMetering.getId()==null || "".equals(sbMetering.getId())){
			sbMetering.setCreateBy(UserUtils.getUser());
			sbMetering.setCreateDate(new Date());
			sbMetering.setField2( format.format(new Date()));
			SbMeteringChild child = new SbMeteringChild();
			SbEquipment equipment = new SbEquipment();
			List<SbMeteringChild> sbMeteringChildList = new ArrayList<SbMeteringChild>();
			SbCache sbCache = new SbCache();
            sbCache.setUserid(UserUtils.getUser().getId());
			sbCache.setType("2");
			List<SbCache> list = sbBorrowService.findCacheList(sbCache);
			for (SbCache temp : list) {
				equipment = sbEquipmentService.get(temp.getSbId());
				child.setEquipmentName(equipment.getName());
				child.setEquipmentType(equipment.getType());
				child.setEquipmentCccode(equipment.getCccode());
				child.setEquipmentSbcode(equipment.getSbcode());
				child.setDepartment(equipment.getTeamname());
				child.setDepartmentid(equipment.getTeam());
				child.setPerson(equipment.getUsepeoplename());
				child.setMeteringType(equipment.getField12());
				child.setMeteringTime(equipment.getField11());
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
				sbMeteringChildList.add(child);
				child = new SbMeteringChild();
				
			}
			sbMetering.setSbMeteringChildList(sbMeteringChildList);
		}
		
		return "modules/equipment/metering/sbMeteringForm";
	}
	
	//月度
	@RequestMapping(value = "formMonth")
	public String formMonth(SbMetering sbMetering, Model model) {
		model.addAttribute("sbMetering", sbMetering);
		

		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
		if(sbMetering.getId()==null || "".equals(sbMetering.getId())){
			sbMetering.setCreateBy(UserUtils.getUser());
			sbMetering.setCreateDate(new Date());
			sbMetering.setField2( format.format(new Date()));
			SbMeteringChild child = new SbMeteringChild();
			SbEquipment equipment = new SbEquipment();
			List<SbMeteringChild> sbMeteringChildList = new ArrayList<SbMeteringChild>();
		
			//查询月度设备
			SbEquipment sbEquipment  = new SbEquipment();
			sbEquipment.setField6(getNextDate(2));
			List<SbEquipment> list = sbEquipmentService.findList(sbEquipment);
			for (SbEquipment temp : list) {
				equipment = temp;
				child.setEquipmentName(equipment.getName());
				child.setEquipmentType(equipment.getType());
				child.setEquipmentCccode(equipment.getCccode());
				child.setEquipmentSbcode(equipment.getSbcode());
				child.setDepartment(equipment.getTeamname());
				child.setDepartmentid(equipment.getTeam());
				child.setPerson(equipment.getUsepeoplename());
				child.setMeteringType(equipment.getField12());
				child.setMeteringTime(equipment.getField11());
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
				sbMeteringChildList.add(child);
				child = new SbMeteringChild();
				
			}
			sbMetering.setSbMeteringChildList(sbMeteringChildList);
		}
		
		return "modules/equipment/metering/sbMeteringForm";
	}
	
	public String getNextDate(int time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
		cal.add(Calendar.MONTH, time);
		cal.set(Calendar.DAY_OF_MONTH,
		cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		System.out.println("当月时间上月1号-->"+sdf.format(cal.getTime()));
		return sdf.format(cal.getTime());
	}

	@RequiresPermissions("equipment:metering:sbMetering:edit")
	@RequestMapping(value = "save")
	public String save(SbMetering sbMetering, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbMetering)){
			return form(sbMetering, model);
		}
		
		BpsManage bps = new BpsManage();
		long pid = 0;
		String[] userid ;
		String[] type ;
		String[] userids = null ;
		String[] types  = null ;
		Map<String,String> map = new HashMap<String,String>();
		if (sbMetering.getProcessinstid() == null || sbMetering.getProcessinstid() == 0) {
			try {
				
				List<SbMeteringChild>  sbMeteringChildList = sbMetering.getSbMeteringChildList() ;
				for (int i=0 ;i<sbMeteringChildList.size();i++) {
					List<User> list = systemService.findUserByOfficeIdAndRole(sbMeteringChildList.get(i).getDepartmentid(), "bmsby");
					if(list!= null &&list.size()>0){
						if(map.get("id-"+sbMeteringChildList.get(i).getDepartmentid())==null){
						    userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
						    type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
							userids = (String[]) ArrayUtils.addAll(userid, userids);
							types = (String[]) ArrayUtils.addAll(type, types);
							map.put("id-"+sbMeteringChildList.get(i).getDepartmentid(), "1");
						}
						
					}else{
						if(sbMeteringChildList.get(i).getDepartment()!=null){
						addMessage(redirectAttributes, "部门"+sbMeteringChildList.get(i).getDepartment()+",无设备管理员！");
						return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/form?repage";
						}
					}
				}
				pid = bps.startBPS("设备计量", "com.primeton.bps.metering");
				sbMetering.setProcessinstid(pid);
				bps.setFlowRelativeData(pid, "wfps", userids, types);
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
		sbMetering.setTotle(sbMetering.getSbMeteringChildList().size());
		sbMetering.setField3(UserUtils.getUser().getName());
		sbMeteringService.save(sbMetering);
		map = new HashMap<String,String>();
		map.put("userid", UserUtils.getUser().getId());
		map.put("type", "2");
		sbBorrowService.deleteByuserId(map);
		addMessage(redirectAttributes, "计量申请成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/?repage";
	}
	
	@RequiresPermissions("equipment:metering:sbMetering:edit")
	@RequestMapping(value = "delete")
	public String delete(SbMetering sbMetering, RedirectAttributes redirectAttributes) {
		sbMeteringService.delete(sbMetering);
		addMessage(redirectAttributes, "删除计量管理成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/?repage";
	}
	
	
	@RequestMapping(value = "flow")
	public String flow(WFWorkItem workItem, Model model) {
		SbMetering sbMetering = sbMeteringService.getByProcessInstID(workItem.getProcessInstID());
		
		if("manualActivity1".equals(workItem.getActivityDefID())){
		    List<SbMeteringChild> list = sbMetering.getSbMeteringChildList();
		    List<SbMeteringChild> listx =  new ArrayList<SbMeteringChild>();
		    for (SbMeteringChild sbMeteringChild : list) {
		    	if(sbMeteringChild.getDepartmentid().equals(UserUtils.getUser().getOffice().getId())){
		    		listx.add(sbMeteringChild);
		    	}
				
			}
		    sbMetering.setSbMeteringChildList(listx);
		}
		
		sbMetering.setWorkItem(workItem);
		sbMetering.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbMetering", sbMetering);
		if("manualActivity1".equals(workItem.getActivityDefID())){
			return "modules/equipment/metering/sbMeteringAudit";
		}
		if("manualActivity2".equals(workItem.getActivityDefID())){
			return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/sbMeteringChildList?meteringId="+sbMetering.getId();
		}
		return "modules/equipment/metering/sbMeteringAudit";
	
	}
	
	
	@RequestMapping(value = "metering")
	public String metering(WFWorkItem workItem, Model model) {
		return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/sbMeteringChildEmpList?field1="+workItem.getProcessInstID();
	
	}

	@RequiresPermissions("equipment:metering:sbMetering:view")
	@RequestMapping(value = {"sbMeteringChildList"})
	public String sbMeteringChildList(SbMeteringChild sbMeteringChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbMeteringChild> page = sbMeteringService.findSbMeteringChildPage(new Page<SbMeteringChild>(request, response), sbMeteringChild); 
		model.addAttribute("page", page);
		model.addAttribute("meteringId", sbMeteringChild.getMeteringId());
		return "modules/equipment/metering/sbMeteringChildList";
	}
	
	@RequiresPermissions("equipment:metering:sbMetering:view")
	@RequestMapping(value = {"sbMeteringChildEmpList"})
	public String sbMeteringChildEmpList(SbMeteringChild sbMeteringChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbMeteringChild> page = sbMeteringService.findSbMeteringChildPage(new Page<SbMeteringChild>(request, response), sbMeteringChild); 
		model.addAttribute("page", page);
		model.addAttribute("field1", sbMeteringChild.getField1());
		return "modules/equipment/metering/sbMeteringChildEmpList";
	}
	
	@RequestMapping(value = "submit")
	public String submit(SbMetering sbMetering, Model model, RedirectAttributes redirectAttributes) {
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(sbMetering.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbMetering.getField1());
		examine.setExamineExamineisagree(sbMetering.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbMetering.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbMetering.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbMetering.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbMetering.getWorkItem().getWorkItemName());
		
		
		if("manualActivity1".equals(sbMetering.getWorkItem().getActivityDefID())){
			SbEquipment sbEquipment = new SbEquipment();
			List<SbMeteringChild> sbMeteringChildList = sbMetering.getSbMeteringChildList();
			List<SbMeteringChild> sbMeteringChildListx = new ArrayList<SbMeteringChild>();;
			for (SbMeteringChild sbMeteringChild : sbMeteringChildList) {
	           if("Y".equals(sbMetering.getWorkItem().getWorkItemType())){
				sbEquipment = sbEquipmentService.get(sbMeteringChild.getEquipmentId());
				sbEquipment.setBstate("10");
				sbEquipmentService.save(sbEquipment);
	           }
				if(sbMeteringChild.getReason()!=null){
					sbMeteringChildListx.add(sbMeteringChild);
				}
			}
			sbMetering.setSbMeteringChildList(sbMeteringChildListx);
		}
		
		sbMeteringService.save(sbMetering);
		
		
		try {
			if("Y".equals(sbMetering.getWorkItem().getWorkItemType())){
				SbMeteringChild sbMeteringChild = new SbMeteringChild();
				sbMeteringChild.setMeteringId(sbMetering);
				sbMeteringChild.setIsGood("1");
				List<SbMeteringChild> list= sbMeteringService.getMeteringChildList(sbMeteringChild);
				
				sbMetering.setSureTotle(list.size());
				examineService.save(examine);
			    bps.finishWorkItem(sbMetering.getWorkItem());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sbMeteringService.save(sbMetering);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	
	
	@RequestMapping(value = "updateMetering")
	public String updateMetering(SbMeteringChild sbMeteringChild , Model model) {
		sbMeteringChild = sbMeteringService.getSbMeteringChild(sbMeteringChild.getField1());
		sbMeteringChild.setInspectionDate(new Date());
		sbMeteringChild.setMeteringState("1");
		model.addAttribute("sbMeteringChild", sbMeteringChild);
		return "modules/equipment/metering/updateMeteringFrom";
	}
	
	@RequestMapping(value = "saveMetering")
	public void saveMetering(SbMeteringChild sbMeteringChild , Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SbMeteringChild temp = new SbMeteringChild();
		temp.setInspectionDate(sbMeteringChild.getInspectionDate());
		temp.setMeteringState(sbMeteringChild.getMeteringState());
		temp.setEffectiveDate(sbMeteringChild.getEffectiveDate());
		temp.setEnclosure(sbMeteringChild.getEnclosure());
		
		
		sbMeteringChild = sbMeteringService.getSbMeteringChild(sbMeteringChild.getField1());
		sbMeteringChild.setInspectionDate(temp.getInspectionDate());
		sbMeteringChild.setMeteringState(temp.getMeteringState());
		sbMeteringChild.setEffectiveDate(temp.getEffectiveDate());
		sbMeteringChild.setEnclosure(temp.getEnclosure());
		sbMeteringService.saveSbMeteringChild(sbMeteringChild);
		
		SbEquipment sbEquipment = sbEquipmentService.get(sbMeteringChild.getEquipmentId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sbEquipment.setField6(sdf.format(sbMeteringChild.getEffectiveDate()));
		sbEquipment.setField14(sdf.format(sbMeteringChild.getInspectionDate()));
		sbEquipment.setField13(sdf.format(sbMeteringChild.getInspectionDate()));
		sbEquipmentService.save(sbEquipment);
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequestMapping(value = "sbMeteringCoordination")
	public String sbMeteringCoordination(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
		//tsResourceBusService.saveBusTableData(genTable);
		String meteringId = "" ;
		
		BpsManage bps = new BpsManage();
		long pid = 0;
		String[] userids = {genTable.getComments()};
		String[] types  = {"emp"};
		
		try {
			pid = bps.startBPS("设备计量", "com.primeton.bps.meteringProvide");
			bps.setFlowRelativeData(pid, "wfps", userids, types);
			WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
			bps.finishWorkItem(workitem);
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WFReasonableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
        
        SbMeteringChild sbMeteringChild = new SbMeteringChild();
		for(int i=0 ; i<genTable.getColumnList().size();i++){
			if("1".equals(genTable.getColumnList().get(i).getIsList())){
				sbMeteringChild = sbMeteringService.getSbMeteringChild(genTable.getColumnList().get(i).getId());
				meteringId=sbMeteringChild.getMeteringId().getId();
				sbMeteringChild.setUser(UserUtils.get(genTable.getComments()));
				sbMeteringChild.setMeteringState("0");
				sbMeteringChild.setField1(pid+"");
				sbMeteringService.saveSbMeteringChild(sbMeteringChild);
			}
		
			
		}
		addMessage(redirectAttributes, "推送成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/metering/sbMetering/sbMeteringChildList?meteringId="+meteringId;
	}
	
	
	@RequestMapping(value = "getdyInfo")
    @ResponseBody
	public void getdyInfo(String ids, RedirectAttributes redirectAttributes,HttpServletResponse response) {
        String id[] = ids.split(",");
        SbMeteringChild sbMeteringChild = new SbMeteringChild();
        List<SbMeteringChild> list = new ArrayList<SbMeteringChild>();
        for(int i=0;i<id.length;i++){
        	sbMeteringChild = sbMeteringService.getSbMeteringChild(id[i]);
        	list.add(sbMeteringChild);
        	
        }
		
		renderString(response, JsonMapper.toJsonString(list),"text/html");
	}
	
	@RequestMapping(value = "submitMetering")
	public String submitMetering(Long pid , Model model) {
		System.out.println(pid+"-----======");
		BpsManage bps = new BpsManage();
		WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
		System.out.println(workitem.getWorkItemID()+"-----======");
		try {
			bps.finishWorkItem(workitem);
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequestMapping(value = "submitMeteringgly")
	public String submitMeteringgly(String meteringId , Model model) {
		SbMetering metering = sbMeteringService.get(meteringId);
		BpsManage bps = new BpsManage();
		WFWorkItem workitem = wfworkitemService.getByProcessInstID(metering.getProcessinstid());
		try {
			bps.finishWorkItem(workitem);
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	
	
	@RequestMapping(value = "saveEquipment")
	public String saveEquipment(SbEquipment sbEquipment,String ids ,HttpServletResponse response) {
		String id[] = ids.split(",");
		for (String idstring : id) {
			SbEquipment temp = sbEquipmentService.get(idstring);
			
			if(sbEquipment.getField15()!=null && !"".equals(sbEquipment.getField15()))
			   temp.setField15(sbEquipment.getField15());
			if(sbEquipment.getField5()!=null && !"".equals(sbEquipment.getField5()))
				temp.setField5(sbEquipment.getField5());
			if(sbEquipment.getField12()!=null && !"".equals(sbEquipment.getField12()))
				temp.setField12(sbEquipment.getField12());
			if(sbEquipment.getField11()!=null && !"".equals(sbEquipment.getField11()))
				temp.setField11(sbEquipment.getField11());
			if(sbEquipment.getField13()!=null && !"".equals(sbEquipment.getField13()))
				temp.setField13(sbEquipment.getField13());
			if(sbEquipment.getField14()!=null && !"".equals(sbEquipment.getField14()))
				temp.setField14(sbEquipment.getField14());
			if(sbEquipment.getField6()!=null && !"".equals(sbEquipment.getField6()))
				temp.setField6(sbEquipment.getField6());
			if(sbEquipment.getField16()!=null && !"".equals(sbEquipment.getField16()))
				temp.setField16(sbEquipment.getField16());
			if(sbEquipment.getField17()!=null && !"".equals(sbEquipment.getField17()))
				temp.setField17(sbEquipment.getField17());
			

			
			
			sbEquipmentService.save(temp);
			
		}
		
		
		 return  renderString(response, JsonMapper.toJsonString("sucess"),"text/html");
	}
	
	

}