/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.approver.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eos.workflow.data.WFWorkItem;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.approver.entity.TsApprover;
import com.thinkgem.jeesite.modules.approver.service.TsApproverService;
import com.thinkgem.jeesite.modules.archives.entity.Application;
import com.thinkgem.jeesite.modules.examine.entity.Examine;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.entity.OaNotifyRecord;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 现行文件申请Controller
 * @author suntao
 * @version 2019-01-27
 */
@Controller
@RequestMapping(value = "${adminPath}/approver/tsApprover")
public class TsApproverController extends BaseController {

	@Autowired
	private TsApproverService tsApproverService;
	
	
	@Autowired
	private OaNotifyService oaNotifyService;
	
	
	@Autowired
	private WfworkitemService wfworkitemService;
	
	@Autowired
	private ExamineService examineService ;
	
	@Autowired
	private SystemService systemService ;
	
	
	@ModelAttribute
	public TsApprover get(@RequestParam(required=false) String id) {
		TsApprover entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsApproverService.get(id);
		}
		if (entity == null){
			entity = new TsApprover();
		}
		return entity;
	}
	
	@RequiresPermissions("approver:tsApprover:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsApprover tsApprover, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		tsApprover.setApproverId(user.getId());
		Page<TsApprover> page = tsApproverService.findPage(new Page<TsApprover>(request, response), tsApprover); 
		model.addAttribute("page", page);
		return "modules/approver/tsApproverList";
	}

	@RequiresPermissions("approver:tsApprover:view")
	@RequestMapping(value = "form")
	public String form(TsApprover tsApprover, Model model) {
		model.addAttribute("tsApprover", tsApprover);
		return "modules/approver/tsApproverForm";
	}

	@RequiresPermissions("approver:tsApprover:edit")
	@RequestMapping(value = "save")
	public String save(TsApprover tsApprover, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsApprover)){
			return form(tsApprover, model);
		}
		
		BpsManage bps = new BpsManage();
		String[] userid ;
		String[] type ;
		String[] userids = null ;
		String[] types  = null ;
		long pid = 0;
		try {
			pid = bps.startBPS("现行电子文件申请", "com.primeton.bps.approver");
			
		
			
			List<User> list = systemService.findUserByRole("xhwh");
			userid = (String[]) bps.getUserbyRoleAndOffice(list).get("userId");
			type = (String[]) bps.getUserbyRoleAndOffice(list).get("type");
			bps.setFlowRelativeData(pid, "wfps", userid, type);
			
			WFWorkItem workitem = wfworkitemService.getByProcessInstID(pid);
			bps.finishWorkItem(workitem);
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WFReasonableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		tsApproverService.save(tsApprover);
		addMessage(redirectAttributes, "保存现行文件申请成功");
		return "redirect:"+Global.getAdminPath()+"/approver/tsApprover/?repage";
	}
	
	@RequiresPermissions("approver:tsApprover:edit")
	@RequestMapping(value = "delete")
	public String delete(TsApprover tsApprover, RedirectAttributes redirectAttributes) {
		tsApproverService.delete(tsApprover);
		addMessage(redirectAttributes, "删除现行文件申请成功");
		return "redirect:"+Global.getAdminPath()+"/approver/tsApprover/?repage";
	}
	

	@RequestMapping(value = "flow")
	public String folw(WFWorkItem workItem, Model model) {
		TsApprover tsApprover = tsApproverService.getByProcessInstID(workItem.getProcessInstID());
		tsApprover.setWorkItem(workItem);
		tsApprover.setExamineList(examineService.getByProcessInstID(workItem.getProcessInstID()));
		model.addAttribute("tsApprover", tsApprover);
		if(workItem.getActivityDefID()==null){
			return "modules/approver/approverAuditView";
		}
		if("manualActivity2".equals(workItem.getActivityDefID())){
			return "modules/approver/approverAudit2";
		}
		return "modules/approver/approverAudit";
	}
	
	@RequestMapping(value = "submit")
	public String submit(TsApprover tsApprover, Model model, RedirectAttributes redirectAttributes) {
		
		BpsManage bps = new BpsManage();
		Examine examine = new Examine();
		examine.setProcessinstid(tsApprover.getWorkItem().getProcessInstID());
		examine.setExaminePerson(UserUtils.getUser().getName());
		examine.setExamineDate(new Date());
		examine.setExamineExplain(tsApprover.getUsername());
		examine.setExamineExamineisagree(tsApprover.getWorkItem().getWorkItemType());
		examine.setExamineSyzs(tsApprover.getWorkItem().getWorkItemType());
		examine.setExamineJd(tsApprover.getWorkItem().getActivityDefID());
		examine.setExamineVersion(tsApprover.getWorkItem().getWorkItemID());
		examine.setExamineExamineisagree(tsApprover.getWorkItem().getWorkItemName());
		examineService.save(examine);
		if("N".equals(tsApprover.getWorkItem().getWorkItemType())){
			tsApprover.setFile(null);
			tsApproverService.save(tsApprover);
		}
		
		
		try {

			bps.finishWorkItem(tsApprover.getWorkItem());
		} catch (WFServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "提交成功");

		return "redirect:"+Global.getAdminPath()+"/act/task/todo/";
	}
	
	@RequestMapping(value = "approver")
	public String approver(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
		//tsResourceBusService.saveBusTableData(genTable);

		String flag = genTable.getComments();
         TsApprover tsApprover = new TsApprover();
		for(int i=0 ; i<genTable.getColumnList().size();i++){
			List<OaNotifyRecord> list = new ArrayList<OaNotifyRecord>();
			tsApprover.setId(genTable.getColumnList().get(i).getId());
			tsApprover = tsApproverService.get(genTable.getColumnList().get(i).getId()) ;
			if("T".equals(flag)){
				if("1".equals(genTable.getColumnList().get(i).getIsList())){
					tsApproverService.save(tsApprover);
					tsApprover.setState("0");
					tsApproverService.save(tsApprover);
				}				
				
			}else{
				
			
			
			
			if("1".equals(genTable.getColumnList().get(i).getIsList())){
				tsApprover.setState("2");
				OaNotify oaNotify = new OaNotify();
				oaNotify.setType("1");
				oaNotify.setTitle("现行文件下载");
				oaNotify.setContent(tsApprover.getTypeName()+"——"+tsApprover.getName());
				oaNotify.setFiles(tsApprover.getFile());
				oaNotify.setStatus("1");
				User user = UserUtils.getUser();
				oaNotify.setCreateBy(user);
				oaNotify.setCreateDate(new Date());
				oaNotify.setUpdateBy(user);
				oaNotify.setUpdateDate(new Date());
				oaNotify.setDelFlag("0");
				
				OaNotifyRecord oaNotifyRecord = new OaNotifyRecord();
				oaNotifyRecord.setOaNotify(oaNotify);
				user = UserUtils.get(tsApprover.getApplicantId());
				oaNotifyRecord.setId(UUID.randomUUID().toString());
				oaNotifyRecord.setUser(user);
				oaNotifyRecord.setReadFlag("0");
				
				list.add(oaNotifyRecord);
				oaNotify.setOaNotifyRecordList(list);
				oaNotifyService.save(oaNotify);
				tsApproverService.save(tsApprover);
			}
			
		}
		}
		addMessage(redirectAttributes, "审核成功");
		
		return "redirect:"+Global.getAdminPath()+"/approver/tsApprover/?repage";
	}
	
	
	

}