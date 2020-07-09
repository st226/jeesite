/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.qmsupplier;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eos.workflow.data.WFWorkItem;
import com.google.common.collect.Lists;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplierAdmittance;
import com.thinkgem.jeesite.modules.quality.entity.qmsuppliertemp.QmSupplierAdmittanceTemplate;
import com.thinkgem.jeesite.modules.quality.service.qmsupplier.QmSupplierService;
import com.thinkgem.jeesite.modules.quality.service.qmsuppliertemp.QmSupplierAdmittanceTemplateService;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 供应商管理Controller
 * @author suntao
 * @version 2020-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/qmsupplier/qmSupplier")
public class QmSupplierController extends BaseController {

	@Autowired
	private QmSupplierService qmSupplierService;
	@Autowired
	private QmSupplierAdmittanceTemplateService qmSupplierAdmittanceTemplateService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	@ModelAttribute
	public QmSupplier get(@RequestParam(required=false) String id) {
		QmSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmSupplierService.get(id);
		}
		if (entity == null){
			entity = new QmSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:qmsupplier:qmSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmSupplier qmSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmSupplier> page = qmSupplierService.findPage(new Page<QmSupplier>(request, response), qmSupplier); 
		model.addAttribute("page", page);
		return "modules/quality/qmsupplier/qmSupplierList";
	}
	
	
	@RequestMapping(value = "query")
	public String query(QmSupplier qmSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmSupplier> page = qmSupplierService.findPage(new Page<QmSupplier>(request, response), qmSupplier); 
		model.addAttribute("page", page);
		return "modules/quality/qmsupplier/querySupplier";
	}

	@RequiresPermissions("quality:qmsupplier:qmSupplier:view")
	@RequestMapping(value = "form")
	public String form(QmSupplier qmSupplier, Model model) {
		if(qmSupplier.getQmSupplierAdmittanceList()==null || qmSupplier.getQmSupplierAdmittanceList().size()==0){
			List<QmSupplierAdmittanceTemplate> list = qmSupplierAdmittanceTemplateService.findList(new QmSupplierAdmittanceTemplate()) ;
			List<QmSupplierAdmittance> QmSupplierAdmittanceList = new ArrayList<QmSupplierAdmittance>();
			QmSupplierAdmittance qmSupplierAdmittance = new QmSupplierAdmittance();
			for (QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate : list) {
				qmSupplierAdmittance.setAdmittanceIndex(qmSupplierAdmittanceTemplate.getQmIndex());
				qmSupplierAdmittance.setName(qmSupplierAdmittanceTemplate.getName());
				qmSupplierAdmittance.setRemarks(qmSupplierAdmittanceTemplate.getRemarks());
				QmSupplierAdmittanceList.add(qmSupplierAdmittance);
				qmSupplierAdmittance = new QmSupplierAdmittance();
			}
			
			qmSupplier.setQmSupplierAdmittanceList(QmSupplierAdmittanceList);
		}
		model.addAttribute("qmSupplier", qmSupplier);
		return "modules/quality/qmsupplier/qmSupplierForm";
	}
	
	@RequiresPermissions("quality:qmsupplier:qmSupplier:view")
	@RequestMapping(value = "applyA")
	public String applyA(QmSupplier qmSupplier, Model model) {
		if(qmSupplier.getIsNewRecord()){
			qmSupplier.setApplicantName(UserUtils.getUser().getName());
			qmSupplier.setCreateDate(new Date());
			List<QmSupplierAdmittanceTemplate> list = qmSupplierAdmittanceTemplateService.findList(new QmSupplierAdmittanceTemplate()) ;
			List<QmSupplierAdmittance> QmSupplierAdmittanceList = new ArrayList<QmSupplierAdmittance>();
			QmSupplierAdmittance qmSupplierAdmittance = new QmSupplierAdmittance();
			for (QmSupplierAdmittanceTemplate qmSupplierAdmittanceTemplate : list) {
				qmSupplierAdmittance.setAdmittanceIndex(qmSupplierAdmittanceTemplate.getQmIndex());
				qmSupplierAdmittance.setName(qmSupplierAdmittanceTemplate.getName());
				qmSupplierAdmittance.setRemarks(qmSupplierAdmittanceTemplate.getRemarks());
				QmSupplierAdmittanceList.add(qmSupplierAdmittance);
				qmSupplierAdmittance = new QmSupplierAdmittance();
			}
			
			qmSupplier.setQmSupplierAdmittanceList(QmSupplierAdmittanceList);
		}
		
		model.addAttribute("qmSupplier", qmSupplier);
		return "modules/quality/qmsupplier/qmSupplierApplyA";
	}
	
	@RequiresPermissions("quality:qmsupplier:qmSupplier:view")
	@RequestMapping(value = "applyB")
	public String applyB(QmSupplier qmSupplier, Model model) {
		if(qmSupplier.getIsNewRecord()){
			qmSupplier.setApplicantName(UserUtils.getUser().getName());
		}
		model.addAttribute("qmSupplier", qmSupplier);
		return "modules/quality/qmsupplier/qmSupplierApplyB";
	}


	@RequiresPermissions("quality:qmsupplier:qmSupplier:edit")
	@RequestMapping(value = "save")
	public String save(QmSupplier qmSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmSupplier)){
			return form(qmSupplier, model);
		}
		qmSupplierService.save(qmSupplier);
		addMessage(redirectAttributes, "保存供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmsupplier/qmSupplier/?repage";
	}
	
	@RequiresPermissions("quality:qmsupplier:qmSupplier:edit")
	@RequestMapping(value = "saveApply")
	public String saveApply(QmSupplier qmSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmSupplier)){
			return form(qmSupplier, model);
		}
		
		if("1".equals(qmSupplier.getState())){
			BpsManage bps = new BpsManage();
			String[] userid = {qmSupplier.getField3()} ;
		//	String[] userid2 = {qmSupplier.getField5()} ;
			String[] type ={"emp"};
	
	
			long pid = 0;
			try {
				pid = bps.startBPS("供应商新增申请", "com.primeton.bps.qmSupplierApply");
				//设计工艺
				bps.setFlowRelativeData(pid, "YZwfps", userid, type);
		//		bps.setFlowRelativeData(pid, "ZGwfps", userid2, type);
				//部门领导
				List<User> list = systemService.findUserByOfficeIdAndRole(UserUtils.getUser().getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "BMwfps", userid, type);
				//质技部领导
				list = systemService.findUserByOfficeCodeAndRole("zjb", "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "ZGwfps", userid, type);
				
				WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
				bps.finishWorkItem(workitem);
				qmSupplier.setProcessinstid(pid+"");
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WFReasonableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addMessage(redirectAttributes, "供应商申请提交成功");
			
			qmSupplierService.save(qmSupplier);
			return "redirect:"+Global.getAdminPath()+"/quality/qmsupplier/qmSupplier";
		}else{
			addMessage(redirectAttributes, "保存供应商成功");
			qmSupplierService.save(qmSupplier);
			return "redirect:"+Global.getAdminPath()+"/quality/qmsupplier/qmSupplier/applyA?id="+qmSupplier.getId();
		}
		
		
		
		
	}
	
	
	@RequestMapping(value = "submit")
	public String submit(QmSupplier qmSupplier, Model model, RedirectAttributes redirectAttributes) {
	
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(qmSupplier.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(qmSupplier.getField7());
		examine.setExamineExamineisagree(qmSupplier.getField5());
		examine.setExamineSyzs(qmSupplier.getWorkItem().getWorkItemType());
		examine.setExamineJd(qmSupplier.getWorkItem().getActivityDefID());
		examine.setExamineVersion(qmSupplier.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(qmSupplier.getWorkItem().getWorkItemName());
		examineService.save(examine);
		
		//判断领导意见
		
		try {
			bps.setRelativeData(qmSupplier.getWorkItem().getProcessInstID(), "yesNo", qmSupplier.getField1());
		} catch (WFServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//接收判断
        if("manualActivity2".equals(qmSupplier.getWorkItem().getActivityDefID())){
        	qmSupplier.setState("2");
      
        	
        	if("1".equals(qmSupplier.getField10())){
        		String[] userid = {qmSupplier.getField8()} ;
        		String[] type ={"emp"};
        		try {
        			bps.setRelativeData(qmSupplier.getWorkItem().getProcessInstID(), "isNotify", qmSupplier.getField10());
					bps.setFlowRelativeData(qmSupplier.getWorkItem().getProcessInstID(), "GYSwfps", userid, type);
				} catch (WFServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
    		
        	
		}
        qmSupplierService.save(qmSupplier);
		try {
		
			
			bps.finishWorkItem(qmSupplier.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "flow")
	public String folw(WFWorkItem workItem, Model model) {

		QmSupplier qmSupplier = qmSupplierService.getByProcessInstID(workItem.getProcessInstID());
		qmSupplier.setWorkItem(workItem);
		qmSupplier.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("qmSupplier", qmSupplier);
		
		return "modules/quality/qmsupplier/qmSupplierApplyAudit";
	}
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "flowZG")
	public String flowZG(WFWorkItem workItem, Model model) {

		QmSupplier qmSupplier = qmSupplierService.getByProcessInstID(workItem.getProcessInstID());
		qmSupplier.setWorkItem(workItem);
		qmSupplier.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("qmSupplier", qmSupplier);
		
		return "modules/quality/qmsupplier/qmSupplierApplyAuditZG";
	}
	
	
	
	@RequiresPermissions("quality:qmsupplier:qmSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(QmSupplier qmSupplier, RedirectAttributes redirectAttributes) {
		qmSupplierService.delete(qmSupplier);
		addMessage(redirectAttributes, "删除供应商管理成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmsupplier/qmSupplier/?repage";
	}
	
	@RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "供应商导入模板.xlsx";
            List<TsResource> list = Lists.newArrayList();
    		new ExportExcel("供应商数据", QmSupplier.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/quality/qmsupplier/qmSupplier/list?repage";
    }
    

    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(QmSupplier qmSupplier, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
            String fileName = "供应商信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List list  = qmSupplierService.findList(qmSupplier) ;
    		new ExportExcel("供应商信息", QmSupplier.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出采购失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/quality/qmsupplier/qmSupplier/list?repage";
    }
    
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<QmSupplier> list = ei.getDataList(QmSupplier.class);
			for (QmSupplier qmSupplier : list){
				qmSupplierService.save(qmSupplier);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条供应商，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条供应商"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入供应商失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/quality/qmsupplier/qmSupplier/list?repage";
    }

}