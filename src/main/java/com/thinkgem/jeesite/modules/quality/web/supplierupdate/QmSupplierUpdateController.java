/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.quality.web.supplierupdate;

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
import com.thinkgem.jeesite.modules.quality.entity.supplierupdate.QmSupplierUpdate;
import com.thinkgem.jeesite.modules.quality.service.qmsupplier.QmSupplierService;
import com.thinkgem.jeesite.modules.quality.service.supplierupdate.QmSupplierUpdateService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 供应商信息变更Controller
 * @author suntao
 * @version 2020-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/supplierupdate/qmSupplierUpdate")
public class QmSupplierUpdateController extends BaseController {

	@Autowired
	private QmSupplierUpdateService qmSupplierUpdateService;
	
	@Autowired
	private QmSupplierService qmSupplierService;
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	@ModelAttribute
	public QmSupplierUpdate get(@RequestParam(required=false) String id) {
		QmSupplierUpdate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmSupplierUpdateService.get(id);
		}
		if (entity == null){
			entity = new QmSupplierUpdate();
		}
		return entity;
	}
	
	@RequiresPermissions("quality:supplierupdate:qmSupplierUpdate:view")
	@RequestMapping(value = {"list", ""})
	public String list(QmSupplierUpdate qmSupplierUpdate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmSupplierUpdate> page = qmSupplierUpdateService.findPage(new Page<QmSupplierUpdate>(request, response), qmSupplierUpdate); 
		model.addAttribute("page", page);
		return "modules/quality/supplierupdate/qmSupplierUpdateList";
	}

	@RequiresPermissions("quality:supplierupdate:qmSupplierUpdate:view")
	@RequestMapping(value = "form")
	public String form(QmSupplierUpdate qmSupplierUpdate, Model model) {
		if(qmSupplierUpdate.getIsNewRecord()){
			qmSupplierUpdate.setApplicant(UserUtils.getUser().getName());
		}
		model.addAttribute("qmSupplierUpdate", qmSupplierUpdate);
		return "modules/quality/supplierupdate/qmSupplierUpdateForm";
	}

	@RequiresPermissions("quality:supplierupdate:qmSupplierUpdate:edit")
	@RequestMapping(value = "save")
	public String save(QmSupplierUpdate qmSupplierUpdate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qmSupplierUpdate)){
			return form(qmSupplierUpdate, model);
		}
		if("1".equals(qmSupplierUpdate.getState())){
			BpsManage bps = new BpsManage();
			String[] userid = {qmSupplierUpdate.getField3()} ;
			String[] type ={"emp"};
	
	
			long pid = 0;
			try {
				
				if("D".equals(qmSupplierUpdate.getType())){
					pid = bps.startBPS("供应商信息删除", "com.primeton.bps.qmSupplierDelete");
				}else{
					pid = bps.startBPS("供应商信息变更", "com.primeton.bps.qmSupplierUpdate");
				}
				//设计工艺
			//	bps.setFlowRelativeData(pid, "YZwfps", userid, type);
				if("D".equals(qmSupplierUpdate.getType())){
					bps.setFlowRelativeData(pid, "wfpsXH", userid, type);
				}
				
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
				
				
				WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
				bps.finishWorkItem(workitem);
				qmSupplierUpdate.setProcessinstid(pid+"");
			} catch (WFServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WFReasonableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		qmSupplierUpdateService.save(qmSupplierUpdate);
		addMessage(redirectAttributes, "保存供应商信息变更成功");
		return "redirect:"+Global.getAdminPath()+"/quality/supplierupdate/qmSupplierUpdate/?repage";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "flow")
	public String folw(WFWorkItem workItem, Model model) {
		QmSupplierUpdate qmSupplierUpdate = qmSupplierUpdateService.getByProcessInstID(workItem.getProcessInstID());
		qmSupplierUpdate.setWorkItem(workItem);
		qmSupplierUpdate.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("qmSupplierUpdate", qmSupplierUpdate);	
		return "modules/quality/supplierupdate/qmSupplierUpdateAudit";
	}
	
	@RequestMapping(value = "submit")
	public String submit(QmSupplierUpdate qmSupplierUpdate, Model model, RedirectAttributes redirectAttributes) {
	
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(qmSupplierUpdate.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(qmSupplierUpdate.getField2());
		examine.setExamineExamineisagree(qmSupplierUpdate.getField1());
		examine.setExamineSyzs(qmSupplierUpdate.getField1());
		examine.setExamineJd(qmSupplierUpdate.getWorkItem().getActivityDefID());
		examine.setExamineVersion(qmSupplierUpdate.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(qmSupplierUpdate.getWorkItem().getWorkItemName());
		examineService.save(examine);
		
		//判断领导意见
		
		try {
			bps.setRelativeData(qmSupplierUpdate.getWorkItem().getProcessInstID(), "isAgree", qmSupplierUpdate.getField1());
		} catch (WFServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if("D".equals(qmSupplierUpdate.getType()) && "manualActivity3".equals(qmSupplierUpdate.getWorkItem().getActivityDefID()) ){
			QmSupplier qmSupplier =qmSupplierService.get(qmSupplierUpdate.getSupplierId());
			qmSupplierService.delete(qmSupplier);
		}
		
		qmSupplierUpdate.setField1("");
		qmSupplierUpdate.setField2("");
		qmSupplierUpdateService.save(qmSupplierUpdate);
		try {
		
			
			bps.finishWorkItem(qmSupplierUpdate.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	
	@RequiresPermissions("quality:supplierupdate:qmSupplierUpdate:edit")
	@RequestMapping(value = "delete")
	public String delete(QmSupplierUpdate qmSupplierUpdate, RedirectAttributes redirectAttributes) {
		qmSupplierUpdateService.delete(qmSupplierUpdate);
		addMessage(redirectAttributes, "删除供应商信息变更成功");
		return "redirect:"+Global.getAdminPath()+"/quality/supplierupdate/qmSupplierUpdate/?repage";
	}

}