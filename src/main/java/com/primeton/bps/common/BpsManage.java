package com.primeton.bps.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFProcessInstManager;
import com.eos.workflow.api.IWFRelativeDataManager;
import com.eos.workflow.api.IWFWorkItemManager;
import com.eos.workflow.api.IWFWorklistQueryManager;
import com.eos.workflow.data.WFWorkItem;
import com.eos.workflow.helper.BPSDataFactory;
import com.eos.workflow.omservice.WFParticipant;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;



public class BpsManage {

	public IBPSServiceClient client;
	public IWFProcessInstManager mng;
	public IWFWorkItemManager workItemManager;
	public IWFWorklistQueryManager workListMng;
	public IWFRelativeDataManager relativeDataManagerService;
	

	public long startBPS(String name, String processName) throws WFServiceException, WFReasonableException {
		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(), UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		// 创建流程
		long id = client.getProcessInstManager().createAndStartProcessInstance(processName, name, name);

		System.out.println("流程实例ID为：" + id);

		return id;

	}
	
	public Map getUserbyRoleAndOffice(List<User> list){		
		Map map = new HashMap();
		System.out.println(list);
		if(list.size()>0){
		String[] userId = new String[list.size()]; 
		String[] type = new String[list.size()]; 
		for (int i=0 ;i<list.size();i++) {
			userId[i] = list.get(i).getId();
			type[i] = "emp";
		}
		map.put("userId", userId);
		map.put("type", type);
		return map ;
		}else{
			return null ;
		}
		
	}
	
	
	
	public void setRelativeData(long processInstID, String name, Object value)
			throws WFServiceException {
		
		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(),UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		relativeDataManagerService = client.getRelativeDataManager();
		relativeDataManagerService.setRelativeData(processInstID, name, value);// list是相关数据中定义的数组
		
	}

	public void setFlowRelativeData(long processInstID, String name, String[] ids, String[] typeCodes)
			throws WFServiceException {
		System.out.println(1);
		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(), UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		relativeDataManagerService = client.getRelativeDataManager();
		System.out.println(2);
		if (null != ids && null != typeCodes && typeCodes.length == typeCodes.length) {
			System.out.println(3);
			WFParticipant[] wfps = new WFParticipant[ids.length];
			for (int i = 0; i < ids.length; i++) {
				wfps[i] = new WFParticipant(ids[i], typeCodes[i]);
			}
			System.out.println(processInstID);
			relativeDataManagerService.setRelativeData(processInstID, name, wfps);// list是相关数据中定义的数组
		} else {
			new WFServiceException("参与者ID或者参与者的类型参数无效！");
		}
	}

	
	public  List<WFWorkItem> getUserTasks4SDO(String personID, String permission, String scope)
			throws WFServiceException {
		List<WFWorkItem> tasklist = null;
		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(), UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorklistQueryManager workListMng = client.getWorklistQueryManager(); // 获取工作列表查询器接口

		DataObject workItemFilter = BPSDataFactory.createWFSDOCriteria(WFWorkItem.class);
		DataObject bizEntityFilter = BPSDataFactory.createWFSDOCriteria(WFWorkItem.class);

		// DataObject pageCond = BPSDataFactory.createSDOPageCond();
		DataObject pageCond = DataFactory.INSTANCE.create("com.eos.foundation", "PageCond");

		pageCond.set("begin", 0);
		pageCond.set("length", 10);
		pageCond.set("isCount", true);
		workItemFilter.set("_entity", "com.eos.workflow.data.WFWorkItem");
		// workItemFilter.set("/_and[1]/_expr[1]/workItemID", 822); // 822
		// 为工作项ID workItemID
		// bizEntityFilter.set("_entity", "com.eos.workflow.example.test");
		// //业务过滤条件

		// DataObject[]
		// results=workListMng.queryPersonBizEntities4SDO(personID,permission,scope,
		// bizEntityFilter, workItemFilter,"processinstid",pageCond);
		// //分布式多数据源（结合业务表的工作任务查询，查询指定用户领取和待执行的工作任务.）
		// tasklist = BPSDataConvertor.convertToUserObjectList(results,
		// com.eos.workflow.data.WFWorkItem.class);

		List<WFWorkItem> workItems = workListMng.queryPersonWorkItems(personID, permission, scope, null); // 查询指定人员的待办工作项列表

		//DataObject[] workItemByWorkItems = workListMng.queryPersonWorkItems4SDO(personID, permission, scope,
		//		workItemFilter, pageCond); // 查询指定人员的待办工作项列表(根据返回和工作项过滤条件)
		//tasklist = BPSDataConvertor.convertToUserObjectList(workItemByWorkItems,
		//		com.eos.workflow.data.WFWorkItem.class);

		//List<WFWorkItem> finishedWorkItems = workListMng.queryPersonFinishedWorkItems(personID, scope, true, null); // 返回指定人员已完成的任务列表
		System.out.println(workItems.size());
		return workItems;
	}
	
	/**
	 * 批量提交待办任务工作项
	 * @param workitems
	 * @throws WFServiceException 
	 */
	public void finishWorkItems(List<WFWorkItem> workitems) throws WFServiceException{
		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(), UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		try {
			if(null!=workitems&&workitems.size()!=0){
				workItemManager = client.getWorkItemManager();
				long[] ids = new long[workitems.size()];
				for(WFWorkItem workitem : workitems){
					ids[workitems.indexOf(workitem)] = workitem.getWorkItemID();
				}
				workItemManager.finishWorkItemBatch(ids, false);
			}
		} catch (Exception e) {
			
		}
	}
	
	public  void finishWorkItem(WFWorkItem workitem) throws WFServiceException{

		BPSServiceClientFactory.getLoginManager().setCurrentUser(UserUtils.getUser().getId(), UserUtils.getUser().getName());
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		try {
			if(null!=workitem){
				IWFWorkItemManager	workItemManager = client.getWorkItemManager();
				
				workItemManager.finishWorkItem(workitem.getWorkItemID(), false);
			}
		} catch (Exception e) {
			
		}
	}

}
