/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.purchase;

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
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.quality.entity.purchase.QmSupplierPurchase;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.quality.service.purchase.QmSupplierPurchaseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 合格供方目录外采购申请表Controller
 * @author suntao
 * @version 2020-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/purchase/qmSupplierPurchase")
public class QmSupplierPurchaseController extends BaseController {

	@Autowired
	private QmSupplierPurchaseService qmSupplierPurchaseService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	@ModelAttribute
	public QmSupplierPurchase get(@RequestParam(required=false) String id) {
		QmSupplierPurchase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmSupplierPurchaseService.get(id);
		}
		if (entity == null){
			entity = new QmSupplierPurchase();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:purchase:qmSupplierPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmSupplierPurchase qmSupplierPurchase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmSupplierPurchase> page = qmSupplierPurchaseService.findPage(new Page<QmSupplierPurchase>(request, response), qmSupplierPurchase); 
		model.addAttribute("page", page);
		return "modules/quality/purchase/qmSupplierPurchaseList";
	}

	@RequiresPermissions("quality:purchase:qmSupplierPurchase:view")
	@RequestMapping(value = "form")
	public String form(QmSupplierPurchase qmSupplierPurchase, Model model, RedirectAttributes redirectAttributes) {
		if(qmSupplierPurchase.getIsNewRecord()){
			qmSupplierPurchase.setApplicant(UserUtils.getUser().getName());
		}
		if(qmSupplierPurchase.getState()!=null && !"".equals(qmSupplierPurchase.getState())){
			addMessage(redirectAttributes, "已经提交流程不允许修改");

			return "redirect:"+Global.getAdminPath()+"/quality/purchase/qmSupplierPurchase/?repage";
		}
		model.addAttribute("qmSupplierPurchase", qmSupplierPurchase);
		return "modules/quality/purchase/qmSupplierPurchaseForm";
	}

	@RequiresPermissions("quality:purchase:qmSupplierPurchase:edit")
	@RequestMapping(value = "save")
	public String save(QmSupplierPurchase qmSupplierPurchase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmSupplierPurchase)){
			return form(qmSupplierPurchase, model, redirectAttributes);
		}
		if("1".equals(qmSupplierPurchase.getState())){
			BpsManage bps = new BpsManage();
			String[] userid = {""} ;
			String[] type ={""};
	
	
			long pid = 0;
			try {
				pid = bps.startBPS("供方外采购申请", "com.primeton.bps.qmSupplierPurchase");
				//设计工艺
			//	bps.setFlowRelativeData(pid, "YZwfps", userid, type);
				
				//部门领导
				List<User> list = systemService.findUserByOfficeIdAndRole(UserUtils.getUser().getOffice().getId(), "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsBM", userid, type);
				//质技部领导
				list = systemService.findUserByOfficeCodeAndRole("zjb", "bmld");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsZJ", userid, type);
				//总经理
				list = systemService.findUserByRole("zjl");
				userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
				type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
				bps.setFlowRelativeData(pid, "wfpsZJL", userid, type);
				
				WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
				bps.finishWorkItem(workitem);
				qmSupplierPurchase.setProcessinstid(pid+"");
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WFReasonableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		qmSupplierPurchaseService.save(qmSupplierPurchase);
		addMessage(redirectAttributes, "保存合格供方目录外采购申请表成功");
		return "redirect:"+Global.getAdminPath()+"/quality/purchase/qmSupplierPurchase/?repage";
	}
	
	@RequiresPermissions("quality:purchase:qmSupplierPurchase:edit")
	@RequestMapping(value = "delete")
	public String delete(QmSupplierPurchase qmSupplierPurchase, RedirectAttributes redirectAttributes) {
		qmSupplierPurchaseService.delete(qmSupplierPurchase);
		addMessage(redirectAttributes, "删除合格供方目录外采购申请表成功");
		return "redirect:"+Global.getAdminPath()+"/quality/purchase/qmSupplierPurchase/?repage";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "flow")
	public String folw(WFWorkItem workItem, Model model) {
		QmSupplierPurchase qmSupplierPurchase = qmSupplierPurchaseService.getByProcessInstID(workItem.getProcessInstID());
		qmSupplierPurchase.setWorkItem(workItem);
		qmSupplierPurchase.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("qmSupplierPurchase", qmSupplierPurchase);	
		return "modules/quality/purchase/qmSupplierPurchaseAudit";
	}
	
	@RequestMapping(value = "submit")
	public String submit(QmSupplierPurchase qmSupplierPurchase, Model model, RedirectAttributes redirectAttributes) {
	
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(qmSupplierPurchase.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(qmSupplierPurchase.getField2());
		examine.setExamineExamineisagree(qmSupplierPurchase.getField1());
		examine.setExamineSyzs(qmSupplierPurchase.getField1());
		examine.setExamineJd(qmSupplierPurchase.getWorkItem().getActivityDefID());
		examine.setExamineVersion(qmSupplierPurchase.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(qmSupplierPurchase.getWorkItem().getWorkItemName());
		examineService.save(examine);
		
		//判断领导意见
		
		try {
			bps.setRelativeData(qmSupplierPurchase.getWorkItem().getProcessInstID(), "isAgree", qmSupplierPurchase.getField1());
		} catch (WFServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//接收判断
		if("manualActivity2".equals(qmSupplierPurchase.getWorkItem().getActivityDefID())){
			String[] userid = {qmSupplierPurchase.getField3()} ;
			String[] type ={"emp"};
			try {
				bps.setFlowRelativeData(qmSupplierPurchase.getWorkItem().getProcessInstID(), "wfpsXH", userid, type);
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if("manualActivity4".equals(qmSupplierPurchase.getWorkItem().getActivityDefID()) &&"1".equals(qmSupplierPurchase.getField1()) ){
			qmSupplierPurchase.setState("2");
			
		}
		qmSupplierPurchase.setField1("");
		qmSupplierPurchase.setField2("");
		qmSupplierPurchaseService.save(qmSupplierPurchase);
		try {
		
			
			bps.finishWorkItem(qmSupplierPurchase.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	

}