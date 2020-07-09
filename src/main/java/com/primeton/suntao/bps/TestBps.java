package com.primeton.suntao.bps;


import java.util.*;  

import com.eos.workflow.api.BPSServiceClientFactory;  
import com.eos.workflow.api.IBPSServiceClient;  
import com.eos.workflow.api.IWFWorkItemManager;  
import com.eos.workflow.data.WFWorkItem;  
import com.primeton.workflow.api.WFReasonableException;  
import com.primeton.workflow.api.WFServiceException;  
  
public class TestBps {  
  
    public static void main(String[] args) throws WFServiceException, WFReasonableException {  
          System.out.println(">>>>");
        BPSServiceClientFactory.getLoginManager().setCurrentUser("1", "系统管理员");  
        System.out.println(">>>>1");
        IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();  
        System.out.println(">>>>2");
        IWFWorkItemManager workItemManager = client.getWorkItemManager();  
        System.out.println(">>>>3");
          
        //创建流程  
        long  id =client.getProcessInstManager().  
                createAndStartProcessInstance("com.primeton.htgl.model.testBPS",  
                                              "Process A","Process A");             
        System.out.println("流程实例ID为："+id);  
          
      
          
  
    }  
  
}  
