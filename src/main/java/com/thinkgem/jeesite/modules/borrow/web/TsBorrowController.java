/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.borrow.web;


import java.util.Date;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.borrow.entity.TsBorrow;
import com.thinkgem.jeesite.modules.borrow.entity.sbborrow.SbBorrowChild;
import com.thinkgem.jeesite.modules.borrow.service.TsBorrowService;
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.equipment.entity.TsEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.service.TsEquipmentService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentbus.SbEquipmentBusService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resourcebook.service.TsResourceBookService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.task.entity.TsTask;
import com.thinkgem.jeesite.modules.task.service.TsTaskService;

/**
 * 资源流通Controller
 * @author suntao
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/tsBorrow")
public class TsBorrowController extends BaseController {
	
	@Autowired
	private TsTaskService tsTaskService;

	@Autowired
	private TsBorrowService tsBorrowService;
	
	@Autowired
	private TsEquipmentService tsEquipmentService;
	
	@Autowired
	private TsResourceBusService tsResourceBusService;
	
	@Autowired
	private TsResourceTypeService tsResourceTypeService;
	
	@Autowired
	private SbEquipmentBusService sbEquipmentBusService;
	
	@Autowired
	private SbBorrowService sbBorrowService;
	
	@ModelAttribute
	public TsBorrow get(@RequestParam(required=false) String id) {
		TsBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsBorrowService.get(id);
		}
		if (entity == null){
			entity = new TsBorrow();
		}
		return entity;
	}
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsBorrow tsBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(tsBorrow==null || tsBorrow.getUser()==null){
			tsBorrow = new TsBorrow();
			User user = new User();
			tsBorrow.setUser(user);
			System.out.println(tsBorrow.getUser().getId());
		}
		Page<TsBorrow> page = tsBorrowService.findPage(new Page<TsBorrow>(request, response), tsBorrow); 
		model.addAttribute("page", page);
		return "modules/borrow/tsBorrowList";
	}
	
	@RequiresPermissions("archives:application:view")
	@RequestMapping(value = "borrowInfo")
	public String modelFiles(SbBorrowChild sbBorrowChild, Model model) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Map<String,String> map = new HashMap();
		map.put("key_code", sbBorrowChild.getEquipmentId());
		map = tsBorrowService.findListMap(map);
		
		sbBorrowChild.setUser(UserUtils.get(sbBorrowChild.getUser().getId()));
		sbBorrowChild.setOffice(UserUtils.get(sbBorrowChild.getUser().getId()).getOffice());
		sbBorrowChild.setEquipmentName(map.get("name"));
		sbBorrowChild.setEquipmentType(map.get("type"));
		sbBorrowChild.setEquipmentCccode(map.get("cccode"));
		sbBorrowChild.setEquipmentId(map.get("id"));
		sbBorrowChild.setEquipmentSbcode(map.get("sbcode"));
		sbBorrowChild.setEquipmentType(map.get("type"));
		if("1".equals(map.get("ismeasurement"))){
			sbBorrowChild.setIsmeasurement("是");
		}else{
			sbBorrowChild.setIsmeasurement("否");
		}
		if(map.get("measurement")!=null && !"".equals(map.get("measurement"))){
			try {
				sbBorrowChild.setMeasurement( format.parse(map.get("measurement")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		model.addAttribute("sbBorrowChild", sbBorrowChild);
		return "modules/borrow/sbborrow/borrowInfo";
	}
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"borrow", ""})
	public String borrow(TsBorrow tsBorrow, Model model) {
		
		List<TsBorrow> tsBorrows = null ;
		if(tsBorrow.getUserCode()!=null && !"".equals(tsBorrow.getUserCode())){
			User user = UserUtils.getByNo(tsBorrow.getUserCode());
			
			DataMap dataMap = new DataMap() ;
			TsResourceBus tsResourceBus = new TsResourceBus() ;
			tsResourceBus.setIsList("1");
			tsResourceBus.setBusType(tsBorrow.getUserCode());
			String userName = tsResourceTypeService.get(tsBorrow.getUserCode()).getName();
			tsBorrow.setRemarks(userName);
			List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
			String code = "" ;
			int var = 0 ;
			int busType = 0 ;
			for(int i = 0 ;i<list.size() ; i++){
				tsResourceBus = list.get(i) ;
	            if("local".equals(tsResourceBus.getColumnName())){	
				}else{
					if("".equals(code)){
						code += tsResourceBus.getColumnName();
					}else{
						code += ","+tsResourceBus.getColumnName();
					}
					
				}
				if("id".equals(tsResourceBus.getColumnName())){
					
					var = i ;
				}
	            if("busType".equals(tsResourceBus.getColumnName())){
					
	            	busType = i ;
				}
			}
			if(code != null && !"".equals(code)){
				
				list.remove(var) ;
			
			model.addAttribute("busType", tsBorrow.getUserCode());
			dataMap.put("code", code) ;
			dataMap.put("busType", tsResourceBus.getBusType()) ;
			

			List<Map> list2 = tsResourceBusService.findMaps(dataMap);
			model.addAttribute("page", list2);
			
			model.addAttribute("TsResourceBus", list);
			}	
		}
		model.addAttribute("tsBorrows", tsBorrows);
		model.addAttribute("tsBorrow", tsBorrow);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/tsBorrowIndex";
	}
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"sbBorrow", ""})
	public String sbBorrow(TsBorrow tsBorrow, Model model) {
		SbBorrowChild sbBorrowChild = new SbBorrowChild();
		List<TsBorrow> tsBorrows = null ;
		if(tsBorrow.getUserCode()!=null && !"".equals(tsBorrow.getUserCode())){
			User user = UserUtils.get(tsBorrow.getUserCode());
			tsBorrow.setUser(user);
			sbBorrowChild.setUser(user);
			tsBorrow.setOffice(user.getOffice());
			tsBorrows = tsBorrowService.findList(tsBorrow);
		}
		sbBorrowChild.setBorrowState("3");
		List<SbBorrowChild> sbBorrowChilds = sbBorrowService.findChildList(sbBorrowChild);
		model.addAttribute("sbBorrowChilds", sbBorrowChilds);
		model.addAttribute("tsBorrows", tsBorrows);
		model.addAttribute("tsBorrow", tsBorrow);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/sbBorrowIndex";
	}
	

	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"borrowSJ", ""})
	public String borrowSJ(TsBorrow tsBorrow, Model model,RedirectAttributes redirectAttributes) {
		List<TsBorrow> tsBorrows = null ;
		if(tsBorrow.getUserCode()!=null && !"".equals(tsBorrow.getUserCode())){
			User user = UserUtils.getByNo(tsBorrow.getUserCode());
			DataMap dataMap = new DataMap() ;
			
			
			TsResourceBus tsResourceBus = new TsResourceBus() ;
			tsResourceBus.setIsList("1");
			tsResourceBus.setBusType(tsBorrow.getUserCode());
			List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
			String code = "bus_type" ;
			int var = 0 ;
			int busType = 0 ;
			for(int i = 0 ;i<list.size() ; i++){
				tsResourceBus = list.get(i) ;
	            if("local".equals(tsResourceBus.getColumnName())){
					
				}else{
					
						code += ","+tsResourceBus.getColumnName();
					
				}
				if("id".equals(tsResourceBus.getColumnName())){
					var = i ;
				}
	            if("busType".equals(tsResourceBus.getColumnName())){
	            	busType = i ;
				}
			}
		
			if(code != null && !"".equals(code)){	
			list.remove(var) ;
			model.addAttribute("busType", tsBorrow.getUserCode());
			dataMap.put("code", code) ;
			dataMap.put("busType", tsResourceBus.getBusType()) ;
			List<Map> list2 = tsResourceBusService.findMaps(dataMap);
			Map map = new HashMap();
			
			TsTask task = new TsTask();
			for(int i = 0 ; i<list2.size() ;i++){
				map = list2.get(i);
				System.out.println(map.get("bus_type")+"....");
				task.setEquipmentid(null == map.get("id") ? null : map.get("id").toString());
				task.setOfficeid(null == map.get("department") ? null :map.get("department").toString());
				task.setUserid(null == map.get("personnel") ? null :map.get("personnel").toString());
				task.setLasttime(null == map.get("inspection") ? null :map.get("inspection").toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                task.setAcquisitiontime(formatter.format(new Date()));
                task.setEquipmentname(null == map.get("name") ? null :map.get("name").toString());
                task.setState("1");
                task.setBusType(null == map.get("bus_type") ? null :map.get("bus_type").toString());
				
                tsTaskService.save(task);
                task = new TsTask();
			}
			model.addAttribute("page", list2);
			model.addAttribute("TsResourceBus", list);
			}
		}
		
	
		tsBorrow.setUserCode(null);
		model.addAttribute("tsBorrow", tsBorrow);
		addMessage(redirectAttributes, "采集任务发起成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/borrow/?repage";
	
	}
	
	
	
	@RequestMapping(value = "save2")
	public String save2(TsEquipment tsEquipment, Model model, RedirectAttributes redirectAttributes) {
	//	if (!beanValidator(model, tsResource)){
	//		return form(tsResource, model);
	//	}
		tsEquipmentService.save(tsEquipment);
		addMessage(redirectAttributes, "保存资源成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/task?busType="+tsEquipment.getBusType();
	}
	
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"task", ""})
	public String task(TsTask tsTask, Model model) {
		List<TsBorrow> tsBorrows = null ;
		if(tsTask.getBusType()!=null && !"".equals(tsTask.getBusType())){
			DataMap dataMap = new DataMap() ;
			TsResourceBus tsResourceBus = new TsResourceBus() ;
			tsResourceBus.setIsList("1");
			tsResourceBus.setBusType(tsTask.getBusType());
			List<TsResourceBus> list = tsResourceBusService.getBuszd(tsResourceBus) ;
			String code = "" ;
			int var = 0 ;
			int busType = 0 ;
			for(int i = 0 ;i<list.size() ; i++){
				tsResourceBus = list.get(i) ;
	            if("local".equals(tsResourceBus.getColumnName())){	
				}else{
					if("".equals(code)){
						code += tsResourceBus.getColumnName();
					}else{
						code += ","+tsResourceBus.getColumnName();
					}
					
				}
				if("id".equals(tsResourceBus.getColumnName())){
					
					var = i ;
				}
	            if("busType".equals(tsResourceBus.getColumnName())){
					
	            	busType = i ;
				}
			}
			System.out.println(code);
			if(code != null && !"".equals(code)){
				
				list.remove(var) ;
			
			model.addAttribute("busType", tsTask.getBusType());
			dataMap.put("code", code) ;
			dataMap.put("busType", tsResourceBus.getBusType()) ;
			dataMap.put("equipmentid", tsTask.getEquipmentid()) ;
			dataMap.put("taskid", tsTask.getId()) ;

			List<Map> list2 = tsResourceBusService.findMapszd(dataMap);
			model.addAttribute("page", list2);
			model.addAttribute("TsResourceBus", list);
			}	
		}
		model.addAttribute("tsBorrows", tsBorrows);
		model.addAttribute("equipmentid", tsTask.getEquipmentid());
		model.addAttribute("taskid", tsTask.getId());
		model.addAttribute("tsTask", tsTask);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/tsTaskIndex";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "form2")
	public String form2(TsEquipment tsEquipment2, Model model) {
		System.out.println(tsEquipment2.getBusType());
		//查询实体资源信息
		TsEquipment tsEquipment = new TsEquipment();
		model.addAttribute("busType", tsEquipment2.getBusType());
		tsEquipment.setId(tsEquipment2.getId());
		if(tsEquipment2.getId()!= null && !"".equals(tsEquipment2.getId())){
			tsEquipment = tsEquipmentService.get(tsEquipment2.getId()) ;
		}
		
		TsResourceBus tsResourceBus = new TsResourceBus();
		//查询页面信息
		tsResourceBus.setIsEdit("1");
		tsResourceBus.setBusType(tsEquipment2.getBusType());
		List<TsResourceBus> list = tsResourceBusService.getBuszd(tsResourceBus) ;
		String codeString = "" ;
		Map map = new HashMap() ;
		String value = "" ;
		try {
			 map = objectToMap(tsEquipment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ;i<list.size() ; i++){
			tsResourceBus = list.get(i) ;
			if( null == map.get(tsResourceBus.getTableName())){
				value = "";
			}else{
				value = (String) map.get(tsResourceBus.getTableName()) ;
			}
			codeString = getCodeHtml(codeString, value, tsResourceBus);
		}
		tsEquipment.setTaskid(tsEquipment2.getTaskid());
		tsEquipment.setEquipmentid(tsEquipment2.getEquipmentid());
		tsEquipment.setBusType(tsEquipment2.getBusType());
		model.addAttribute("codeString", codeString);
		model.addAttribute("tsEquipment", tsEquipment);
		return "/modules/borrow/tsEquipmentForm";
	}
	
	 public  Map<String, Object> objectToMap(Object obj) throws Exception {    
         if(obj == null)  
            return null;      
  
        Map<String, Object> map = new HashMap<String, Object>();   
  
       BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
         for (PropertyDescriptor property : propertyDescriptors) {    
           String key = property.getName();    
            if (key.compareToIgnoreCase("class") == 0) {   
                continue;  
            }  
           Method getter = property.getReadMethod();  
             Object value = getter!=null ? getter.invoke(obj) : null;  
           map.put(key, value);  
       }    
   
     return map;  
   }    
	
	public String getCodeHtml(String codeString,String  value,TsResourceBus tsResourceBus){
		
		 switch (tsResourceBus.getShowType()) {
		 case "input":
			 codeString += " <div class='control-group'>"+
		                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
		                " <div class='controls'>"+
		                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
		                "htmlEscape='false' type='text' maxlength='50' value='"+value+"' class='input-xlarge '/>"+
		                " </div>"+
		                " </div>  " ; 
			break;
		case "dateselect":
			codeString += " <div class='control-group'>"+                              
	                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
	                
			" <div class='controls'>"+
			" <input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' type='text' maxlength='50' "
					+ "class='input-medium Wdate'"+
			" 	value='"+value+"' onclick= 'WdatePicker({isShowClear:false});'/>"+"  </div>  </div>  " ; 
			break;
			
		case "select":
			codeString += " <div class='control-group'>"+                              
	                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
	                
			" <div class='controls'>"+
			   "<select id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' class='input-xlarge '>  "+
			getSelect(tsResourceBus.getDictType(),value) + "</select>"
					+ "</div> " +
					 " </div>  " ; 
			break;
		
			
		default:
			codeString += " <div class='control-group'>"+
	                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
	                " <div class='controls'>"+
	                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
	                "htmlEscape='false' type='text' maxlength='50' value='"+value+"' class='input-xlarge '/>"+
	                " </div>"+
	                " </div>  " ;
			break;
		}
		 return codeString ;
		
		
		
		
		 
		
	}
	
	public String getSelect(String type , String value){
		String code = "<option value='' selected='selected'></option>" ;
		if(value !=null && !"".equals(value)){
			code = "<option value=''></option>" ;
		}
		List<Dict> list = DictUtils.getDictList(type) ;
		for (Dict dict : list) {    
			if(dict.getValue().equals(value)){
				code += "<option value='"+dict.getValue()+"' selected='selected'>"+dict.getLabel()+"</option>" ;
			}else{
				code += "<option value='"+dict.getValue()+"'>"+dict.getLabel()+"</option>" ;
			}
		}
		
		return code ;
		
	}
	
	
	
	
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"borrowMy", ""})
	public String borrowMy(TsBorrow tsBorrow, Model model) {
		
		User user = UserUtils.getUser();
		user = UserUtils.get(UserUtils.getUser().getId());
		
		List<TsBorrow> tsBorrows = null ;
		tsBorrow.setUser(user);
	    tsBorrow.setOffice(user.getOffice());
		tsBorrows = tsBorrowService.findList(tsBorrow);
		
		tsBorrow.setUserCode(user.getNo());
		model.addAttribute("tsBorrows", tsBorrows);
		model.addAttribute("tsBorrow", tsBorrow);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/tsBorrowMy";
	}
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"borrowQ", ""})
	public String borrowQ(TsBorrow tsBorrow, Model model) {
		SbBorrowChild sbBorrowChild = new SbBorrowChild();
		
		if(tsBorrow.getUserCode()!=null && !"".equals(tsBorrow.getUserCode())){
			System.out.println(tsBorrow.getUserCode());
			User user = UserUtils.get(tsBorrow.getUserCode());
			tsBorrow.setUser(user);
			sbBorrowChild.setUser(user);
			tsBorrow.setOffice(user.getOffice());
		}
		
		Map<String,String> map = new HashMap<String,String>();
		System.out.println(tsBorrow.getTsId());
		map.put("key_code", tsBorrow.getTsId());
		map = tsBorrowService.findListMap(map);
		tsBorrow.setBusType("(未找到相关设备)");
		if(map!=null && map.get("name")!=null){
			tsBorrow.setBorrowState(map.get("bstate"));
			String bstate = map.get("bstate") ;
			tsBorrow.setBusType("(当前设备不可借用)");
			if("1".equals(bstate)){
				tsBorrow.setBusType("(可借用)");
			}
			tsBorrow.setTsName(map.get("name"));
		}
		sbBorrowChild.setBorrowState("3");
		List<SbBorrowChild> sbBorrowChilds = sbBorrowService.findChildList(sbBorrowChild);
        model.addAttribute("sbBorrowChilds", sbBorrowChilds);
		model.addAttribute("map", map);
		model.addAttribute("tsBorrow", tsBorrow);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/sbBorrowIndex";
	}
	
	
	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = {"borrowJ", ""})
	public String borrowJ(TsBorrow tsBorrow, Model model) {
		TsBorrow temp = new TsBorrow();
		SbBorrowChild sbBorrowChild = new SbBorrowChild();
		User user = null ;
		if(tsBorrow.getUserCode()!=null && !"".equals(tsBorrow.getUserCode())){
			user = UserUtils.get(tsBorrow.getUserCode());
			tsBorrow.setUser(user);
			tsBorrow.setOffice(user.getOffice());
			temp.setUser(user);
			temp.setUserCode(user.getNo());
			temp.setOffice(user.getOffice());
			sbBorrowChild.setUser(user);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("key_code", tsBorrow.getTsId());
		map = tsBorrowService.findListMap(map);
		SbBorrowChild sbBorrowChildx = new SbBorrowChild();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		if(map!=null && map.get("name")!=null){
			sbBorrowChildx.setEquipmentId(map.get("id"));
			sbBorrowChildx.setEquipmentName(map.get("name"));
			sbBorrowChildx.setEquipmentType(map.get("type"));
			sbBorrowChildx.setEquipmentSbcode(map.get("sbcode"));
			sbBorrowChildx.setEquipmentCccode(map.get("cccode"));

			if("1".equals(map.get("field5"))){
				sbBorrowChildx.setIsmeasurement("是");
			}else{
				sbBorrowChildx.setIsmeasurement("否");
			}
			if(map.get("field6")!=null && !"".equals(map.get("field6"))){
				try {
					sbBorrowChildx.setMeasurement( format.parse(map.get("field6")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sbBorrowChildx.setBorrowState("3");
			sbBorrowChildx.setCreateBy(UserUtils.getUser());
			sbBorrowChildx.setCreateDate(new Date());
			sbBorrowChildx.setStatrDate(new Date());
			sbBorrowChildx.setEndDate(temp.getUpdateDate());
			sbBorrowChildx.setIsGood(temp.getDelFlag());
			sbBorrowChildx.setReason(temp.getRemarks());
			sbBorrowChildx.setUser(temp.getUser());
			sbBorrowChildx.setOffice(temp.getOffice());
			sbBorrowChildx.setEndIsGood("1");
			
			sbBorrowService.insertChild(sbBorrowChildx);
			SbEquipment sbEquipment = new SbEquipment();
			sbEquipment.setBstate("3");
			sbEquipment.setId(map.get("id"));
			sbEquipmentBusService.updateBstate(sbEquipment);
			
		}
		
		sbBorrowChild.setBorrowState("3");
		List<SbBorrowChild> sbBorrowChilds = sbBorrowService.findChildList(sbBorrowChild);
        model.addAttribute("sbBorrowChilds", sbBorrowChilds);

		List<TsBorrow> tsBorrows = tsBorrowService.findList(tsBorrow);
		model.addAttribute("tsBorrows", tsBorrows);
		model.addAttribute("tsBorrow", tsBorrow);
		model.addAttribute("config", GenUtils.getConfig());
		return "modules/borrow/sbBorrowIndex";
	}

	@RequiresPermissions("borrow:tsBorrow:view")
	@RequestMapping(value = "form")
	public String form(TsBorrow tsBorrow, Model model) {
		model.addAttribute("tsBorrow", tsBorrow);
		return "modules/borrow/tsBorrowForm";
	}

	@RequiresPermissions("borrow:tsBorrow:edit")
	@RequestMapping(value = "save")
	public String save(TsBorrow tsBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsBorrow)){
			return form(tsBorrow, model);
		}
		tsBorrowService.save(tsBorrow);
		addMessage(redirectAttributes, "保存资源流通成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/?repage";
	}
	
	@RequiresPermissions("borrow:tsBorrow:edit")
	@RequestMapping(value = "renew")
	public String renew(TsBorrow tsBorrow, Model model,RedirectAttributes redirectAttributes) {
		TsBorrow temp = tsBorrowService.get(tsBorrow.getId());
		int frequency = temp.getFrequency() ;
		if(frequency<2){
			temp.setFrequency(frequency+1);
			temp.setBorrowState("2");
			tsBorrowService.save(temp);
			addMessage(redirectAttributes, "续借设备成功");
		}else{
			addMessage(redirectAttributes, "已超出续借次数");
		}
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/sbBorrow?userCode="+tsBorrow.getUserCode();
	}
	
	@RequiresPermissions("borrow:tsBorrow:edit")
	@RequestMapping(value = "renewzj")
	public String renewzj(TsBorrow tsBorrow, Model model,RedirectAttributes redirectAttributes) {
		TsBorrow temp = tsBorrowService.get(tsBorrow.getId());
		int frequency = temp.getFrequency() ;
		if(frequency<2){
			temp.setFrequency(frequency+1);
			temp.setBorrowState("2");
			tsBorrowService.save(temp);
			addMessage(redirectAttributes, "续借设备成功");
		}else{
			addMessage(redirectAttributes, "已超出续借次数");
		}
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/borrowMy?userCode="+tsBorrow.getUserCode();
	}
	
	@RequiresPermissions("borrow:tsBorrow:edit")
	@RequestMapping(value = "revert")
	public String revert(TsBorrow tsBorrow, Model model, RedirectAttributes redirectAttributes) {
		TsBorrow temp = tsBorrowService.get(tsBorrow.getId());
	    temp.setBorrowState("3");
		tsBorrowService.save(temp);
		SbEquipment sbEquipment = new SbEquipment();
		sbEquipment.setBstate("1");
		sbEquipment.setId(tsBorrow.getTsId());
		sbEquipmentBusService.updateBstate(sbEquipment);
		
		
		
		addMessage(redirectAttributes, "归还设备成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/sbBorrow?userCode="+tsBorrow.getUserCode();
	}
	
	@RequiresPermissions("borrow:tsBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(TsBorrow tsBorrow, RedirectAttributes redirectAttributes) {
		tsBorrowService.delete(tsBorrow);
		addMessage(redirectAttributes, "删除资源流通成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tsBorrow/?repage";
	}

}