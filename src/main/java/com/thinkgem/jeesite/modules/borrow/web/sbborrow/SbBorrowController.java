/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.web.sbborrow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.business.entity.agreement.SwAgreement;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.metering.SbMeteringChild;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 设备借用Controller
 * @author suntao
 * @version 2019-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/sbborrow/sbBorrow")
public class SbBorrowController extends BaseController {

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
	public SbBorrow get(@RequestParam(required=false) String id) {
		SbBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbBorrowService.get(id);
		}
		if (entity == null){
			entity = new SbBorrow();
		}
		return entity;
	}
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbBorrow sbBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbBorrow> page = sbBorrowService.findPage(new Page<SbBorrow>(request, response), sbBorrow); 
		model.addAttribute("page", page);
		return "modules/borrow/sbborrow/sbBorrowList";
	}
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:view")
	@RequestMapping(value = "childList")
	public String childList(SbBorrowChild sbBorrowChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SbBorrowChild> page = sbBorrowService.findChildPage(new Page<SbBorrowChild>(request, response), sbBorrowChild); 
		model.addAttribute("page", page);
		return "modules/borrow/sbborrow/sbBorrowChildList";
	}
	
	
	 @RequestMapping(value = "export", method=RequestMethod.POST)
	    public String exportFile(SbBorrowChild sbBorrowChild, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			
			try {
	            String fileName = "设备借用信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            Page<SbBorrowChild> page = sbBorrowService.findChildPage(new Page<SbBorrowChild>(), sbBorrowChild) ;
	    		new ExportExcel("设备借用信息", SbBorrowChild.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出设备借用信息失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/borrow/sbborrow/sbBorrow/childList?repage";
	    }
	
	
	
	@RequestMapping(value = "getdyInfo")
    @ResponseBody
	public void getdyInfo(SbBorrowChild sbBorrowChild, RedirectAttributes redirectAttributes,HttpServletResponse response) {
       System.out.println(sbBorrowChild.getEquipmentName()+".....");
        List<SbBorrowChild> list =sbBorrowService.findChildList(sbBorrowChild);
       
		
		renderString(response, JsonMapper.toJsonString(list),"text/html");
	}
	
	

	@RequestMapping(value = "childView")
	public String childView(SbBorrowChild sbBorrowChild, Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("sbBorrowChild", sbBorrowService.getSbBorrowChild(sbBorrowChild));
		return "modules/borrow/sbborrow/sbBorrowChildView";
	}
	
	
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:view")
	@RequestMapping(value = "MyBorrow")
	public String MyBorrow(SbBorrowChild sbBorrowChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		sbBorrowChild.setUser(UserUtils.getUser());
		Page<SbBorrowChild> page = sbBorrowService.findChildPage(new Page<SbBorrowChild>(request, response), sbBorrowChild); 
		model.addAttribute("page", page);
		return "modules/borrow/sbborrow/MyBorrow";
	}
	

	@RequiresPermissions("borrow:sbborrow:sbBorrow:view")
	@RequestMapping(value = "form")
	public String form(SbBorrow sbBorrow, Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		model.addAttribute("sbBorrow", sbBorrow);
		if(sbBorrow.getId()==null || "".equals(sbBorrow.getId())){
			SbBorrowChild child = new SbBorrowChild();
			SbEquipment equipment = new SbEquipment();
			List<SbBorrowChild> sbBorrowChildList = new ArrayList<SbBorrowChild>();
			sbBorrow.setUser(UserUtils.getUser());
			sbBorrow.setOffice(UserUtils.getUser().getOffice());
			SbCache sbCache = new SbCache();
            sbCache.setUserid(UserUtils.getUser().getId());
			sbCache.setType("1");
			List<SbCache> list = sbBorrowService.findCacheList(sbCache);
			for (SbCache temp : list) {
				equipment = sbEquipmentService.get(temp.getSbId());
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
				sbBorrowChildList.add(child);
				child = new SbBorrowChild();
				
			}
			sbBorrow.setSbBorrowChildList(sbBorrowChildList);
		}
		return "modules/borrow/sbborrow/sbBorrowForm";
	}
	


	@RequestMapping(value = "sbBorrowChildForm")
	public String sbBorrowChildForm(SbBorrowChild sbBorrowChild, Model model) {
		sbBorrowChild = sbBorrowService.getChild(sbBorrowChild.getField1());
		model.addAttribute("sbBorrowChild", sbBorrowChild);
		return "/modules/borrow/sbborrowchild/sbBorrowChildForm";
	}
	
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:view")
	@RequestMapping(value = "view")
	public String view(SbBorrow sbBorrow, Model model) { 
		model.addAttribute("sbBorrow", sbBorrow);
		return "modules/borrow/sbborrow/sbBorrowView";
	}
	
	
	
	@RequestMapping(value = "flow")
	public String flow(WFWorkItem workItem, Model model) {
		SbBorrow sbBorrow = sbBorrowService.getByProcessInstID(workItem.getProcessInstID());
		sbBorrow.setWorkItem(workItem);
		sbBorrow.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("sbBorrow", sbBorrow);
		if("manualActivity1".equals(workItem.getActivityDefID())){
			return "modules/borrow/sbborrow/sbBorrowEdit";
		}
		if("manualActivity4".equals(workItem.getActivityDefID()) || "manualActivity5".equals(workItem.getActivityDefID())){
			return "modules/borrow/sbborrow/sbBorrowFlow2";
		}
		return "modules/borrow/sbborrow/sbBorrowFlow";
	
	}
	
	
	@RequestMapping(value = "saveBorrowInfo")
	public String saveBorrowInfo(SbBorrowChild sbBorrowChild, Model model, RedirectAttributes redirectAttributes) {
		SbEquipment sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
		sbEquipment.setBstate("3");
		sbBorrowChild.setBorrowState("3");
		sbBorrowService.insertChild(sbBorrowChild);
		sbEquipmentService.save(sbEquipment);
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/?repage";
	}

	@RequiresPermissions("borrow:sbborrow:sbBorrow:edit")
	@RequestMapping(value = "save")
	public String save(SbBorrow sbBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbBorrow)){
			return form(sbBorrow, model);
		}
		SbEquipment sbEquipment = new SbEquipment();
		List<SbBorrowChild> sbBorrowChildList = sbBorrow.getSbBorrowChildList();
		//判断是否有设备已经被借出
		for (SbBorrowChild sbBorrowChild : sbBorrowChildList) {
			sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
			if(!"1".equals(sbEquipment.getBstate())){
				addMessage(redirectAttributes, "设备"+sbEquipment.getSbcode()+",已经被借出！");
				return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/form?repage";
			}
		}
		
		BpsManage bps = new BpsManage();
		long pid = 0;
		if (sbBorrow.getProcessinstid() == null || sbBorrow.getProcessinstid() == 0) {
			

			
			String[] userid ;
			String[] type ;
			
			try {
				pid = bps.startBPS("设备借用申请", "com.primeton.bps.equipmentBorrowing");
				
				List<User> list = systemService.findUserByOfficeIdAndRole(UserUtils.getUser().getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "BMwfps", userid, type);
				
				list = systemService.findUserByOfficeCodeAndRole("zjb", "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "ZJBwfps", userid, type);
				
				list = systemService.findUserByRole("yqsbgly");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsgl", userid, type);
				
				sbBorrow.setProcessinstid(pid);
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
		//更改设备状态，情况购物车，修改设备状态
		double value = 0 ;
		for (SbBorrowChild sbBorrowChild : sbBorrowChildList) {
			sbBorrowChild.setBorrowState("2");
			sbBorrowChild.setStatrDate(new Date());
			sbBorrowChild.setEndDate(sbBorrow.getBorrowTo());
			sbBorrowChild.setIsGood("1");
			sbBorrowChild.setEndIsGood("1");
			sbBorrowChild.setUser(UserUtils.getUser());
			sbBorrowChild.setOffice(UserUtils.getUser().getOffice());
			sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
			sbEquipment.setBstate("2");
			sbEquipmentService.save(sbEquipment);
			
			value = getMax(value,sbEquipment.getUnitprice());
		}

		Map<String, String> map = new HashMap<String,String>();
		map.put("userid", UserUtils.getUser().getId());
		map.put("type", "1");
		sbBorrowService.deleteByuserId(map);
		try {
			bps.setRelativeData(pid, "price", value);
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sbBorrow.setSbBorrowChildList(sbBorrowChildList);

		sbBorrow.setState("1");
		sbBorrowService.save(sbBorrow);
		addMessage(redirectAttributes, "设备借用申请提交成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/?repage";
	}
	
	
	@RequestMapping(value = "saveChild")
	public String saveChild(SbBorrowChild sbBorrowChild, Model model, RedirectAttributes redirectAttributes) {
		SbBorrowChild temp = sbBorrowService.getChild(sbBorrowChild.getField1());
		temp.setUser(sbBorrowChild.getUser());
		temp.setOffice(sbBorrowChild.getOffice());
		temp.setStatrDate(sbBorrowChild.getStatrDate());
		temp.setEndDate(sbBorrowChild.getEndDate());
		sbBorrowService.saveChild(temp);
		addMessage(redirectAttributes, "设备借用保存成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/childList/?repage";
		
	}
	
	public double getMax(double value,double temp){
		if(value<temp){
			value = temp ;
		}
		
		return value ;
	}
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:edit")
	@RequestMapping(value = "submit")
	public String submit(SbBorrow sbBorrow, Model model, RedirectAttributes redirectAttributes) {
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(sbBorrow.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(sbBorrow.getField1());
		examine.setExamineExamineisagree(sbBorrow.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(sbBorrow.getWorkItem().getWorkItemType());
		examine.setExamineJd(sbBorrow.getWorkItem().getActivityDefID());
		examine.setExamineVersion(sbBorrow.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(sbBorrow.getWorkItem().getWorkItemName());
		
		try {
			bps.setRelativeData(sbBorrow.getWorkItem().getProcessInstID(), "yesno", sbBorrow.getWorkItem().getWorkItemType());
		} catch (WFServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
		if("manualActivity4".equals(sbBorrow.getWorkItem().getActivityDefID())){
			SbEquipment sbEquipment = new SbEquipment();
			List<SbBorrowChild> sbBorrowChildList = sbBorrow.getSbBorrowChildList();
			for (SbBorrowChild sbBorrowChild : sbBorrowChildList) {
				sbBorrowChild.setBorrowState("3");
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("3");
				sbEquipmentService.save(sbEquipment);
			}
			sbBorrow.setSbBorrowChildList(sbBorrowChildList);
		}
		if("manualActivity15".equals(sbBorrow.getWorkItem().getActivityDefID())){
			SbEquipment sbEquipment = new SbEquipment();
			List<SbBorrowChild> sbBorrowChildList = sbBorrow.getSbBorrowChildList();
			for (SbBorrowChild sbBorrowChild : sbBorrowChildList) {
				sbBorrowChild.setBorrowState("3");
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("3");
				sbEquipmentService.save(sbEquipment);
			}
			sbBorrow.setSbBorrowChildList(sbBorrowChildList);
		}
		if("manualActivity5".equals(sbBorrow.getWorkItem().getActivityDefID())){
			sbBorrow.setState("3");
		}
		if(sbBorrow.getWorkItem().getWorkItemType().equals("N")){
       	 SbEquipment sbEquipment = new SbEquipment();
			List<SbBorrowChild> sbBorrowChildList = sbBorrow.getSbBorrowChildList();
			for (SbBorrowChild sbBorrowChild : sbBorrowChildList) {
				sbBorrowChild.setBorrowState("8");
				sbEquipment = sbEquipmentService.get(sbBorrowChild.getEquipmentId());
				sbEquipment.setBstate("1");
				sbEquipmentService.save(sbEquipment);
			}
			sbBorrow.setSbBorrowChildList(sbBorrowChildList);
			sbBorrow.setState("4");
		}
		examineService.save(examine);
		sbBorrowService.save(sbBorrow);
		try {
			bps.finishWorkItem(sbBorrow.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequiresPermissions("archives:application:edit")
	@RequestMapping(value = "updateState")
    @ResponseBody
	public void updateState(SbBorrowChild sbBorrowChild, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SbBorrowChild sbBorrowChild2 = sbBorrowService.getChild(sbBorrowChild.getField4());
		SbEquipment sbEquipment = sbEquipmentService.get(sbBorrowChild2.getEquipmentId());
		if(sbEquipment==null){
			sbEquipment = new  SbEquipment();
		}
		if("ff".equals(sbBorrowChild.getField5())){
			sbBorrowChild2.setBorrowState("3");
			sbEquipment.setBstate("3");
		}else if("hs".equals(sbBorrowChild.getField5())){
			sbBorrowChild2.setBorrowState("5");
			sbBorrowChild2.setEndIsGood(sbBorrowChild.getEndIsGood());
			sbBorrowChild2.setEndDate(new Date());
			sbBorrowChild2.setEndReason(sbBorrowChild.getEndReason());
			sbEquipment.setBstate("1");
		}
		sbBorrowService.updateChild(sbBorrowChild2);
		if(sbEquipment.getId()!=null && ! "".equals(sbEquipment.getId())){
			sbEquipmentService.save(sbEquipment);
		}
		
		addMessage(redirectAttributes, "操作成功");
		renderString(response, JsonMapper.toJsonString(""),"text/html");
	}
	
	@RequiresPermissions("borrow:sbborrow:sbBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(SbBorrow sbBorrow, RedirectAttributes redirectAttributes) {
		sbBorrowService.delete(sbBorrow);
		addMessage(redirectAttributes, "删除设备借用成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/sbborrow/sbBorrow/?repage";
	}
	
	
	@RequestMapping(value = "deleteSbBorrow")
	 @ResponseBody
	 public void deleteSbBorrow(String sbId,String gly ,HttpServletResponse response){
		if(sbId!=null && !"".equals(sbId)){
			sbBorrowService.deleteBysbId(sbId);
			if("y".equals(gly)){
				SbEquipment sbEquipment = sbEquipmentService.get(sbId);
				sbEquipment.setBstate("1");
				sbEquipmentService.save(sbEquipment);
			}
		}
		
		
		
		
	}
	 @RequestMapping(value = "getSbBorrow")
	    @ResponseBody
	    public void getSbBorrow(String sbId,HttpServletResponse response){
		 SbCache sbCache = new SbCache();
		 User user = UserUtils.getUser();
		 sbCache.setSbId(sbId);
		 sbCache.setUserid(user.getId());
		 sbCache.setType("1");
		 List list = sbBorrowService.findCacheList(sbCache);
		 if(sbId!=null && !"".equals(sbId) && list.size()==0){
			 sbBorrowService.saveCache(sbCache);
			 
			
		 }
		 sbCache.setSbId("");
		 list = sbBorrowService.findCacheList(sbCache);
	        renderString(response, JsonMapper.toJsonString(list),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 
	 @RequestMapping(value = "getSbMetering")
	    @ResponseBody
	    public void getSbMetering(String sbId,HttpServletResponse response){
		 SbCache sbCache = new SbCache();
		 User user = UserUtils.getUser();
		 sbCache.setSbId(sbId);
		 sbCache.setUserid(user.getId());
		 sbCache.setType("2");
		 List list = sbBorrowService.findCacheList(sbCache);
		 if(sbId!=null && !"".equals(sbId) && list.size()==0){
			 sbBorrowService.saveCache(sbCache);
			 
			
		 }
		 sbCache.setSbId("");
		 list = sbBorrowService.findCacheList(sbCache);
	        renderString(response, JsonMapper.toJsonString(list),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 @RequestMapping(value = "getSbProduct")
	    @ResponseBody
	    public void getSbProduct(String sbId,HttpServletResponse response){
		 SbCache sbCache = new SbCache();
		 User user = UserUtils.getUser();
		 sbCache.setSbId(sbId);
		 sbCache.setUserid(user.getId());
		 sbCache.setType("3");
		 List list = sbBorrowService.findCacheList(sbCache);
		 if(sbId!=null && !"".equals(sbId) && list.size()==0){
			 sbBorrowService.saveCache(sbCache);
			 
			
		 }
		 sbCache.setSbId("");
		 list = sbBorrowService.findCacheList(sbCache);
	        renderString(response, JsonMapper.toJsonString(list),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 
	 @RequestMapping(value = "getSbRename")
	    @ResponseBody
	    public void getSbRename(String sbId,HttpServletResponse response){
		 SbCache sbCache = new SbCache();
		 User user = UserUtils.getUser();
		 sbCache.setSbId(sbId);
		 sbCache.setUserid(user.getId());
		 sbCache.setType("4");
		 List list = sbBorrowService.findCacheList(sbCache);
		 if(sbId!=null && !"".equals(sbId) && list.size()==0){
			 sbBorrowService.saveCache(sbCache);
			 
			
		 }
		 sbCache.setSbId("");
		 list = sbBorrowService.findCacheList(sbCache);
	        renderString(response, JsonMapper.toJsonString(list),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 
	 
	 @RequestMapping(value = "btnRenew")
		public String btnRenew(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
			
	  
	        
			for(int i=0 ; i<genTable.getColumnList().size();i++){

				
				
			}
			addMessage(redirectAttributes, "归档成功");
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+genTable.getName();
		}

}