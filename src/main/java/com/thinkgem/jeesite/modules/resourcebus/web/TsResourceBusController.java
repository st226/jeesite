/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcebus.web;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eos.workflow.data.WFWorkItem;
import com.google.common.collect.Lists;
import com.primeton.bps.common.BpsManage;
import com.primeton.workflow.api.WFReasonableException;
import com.primeton.workflow.api.WFServiceException;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.task.SynchronousFileTask;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.approver.entity.TsApprover;
import com.thinkgem.jeesite.modules.approver.service.TsApproverService;
import com.thinkgem.jeesite.modules.borrow.entity.TsBorrow;
import com.thinkgem.jeesite.modules.examine.service.ExamineService;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.infomation.entity.infomation.SbInformation;
import com.thinkgem.jeesite.modules.maxtable.entity.TsMaxTable;
import com.thinkgem.jeesite.modules.maxtable.service.TsMaxTableService;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resource.service.TsResourceService;
import com.thinkgem.jeesite.modules.resourcebook.entity.TsResourceBook;
import com.thinkgem.jeesite.modules.resourcebook.service.TsResourceBookService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.workitem.service.WfworkitemService;

/**
 * 著录项管理Controller
 * @author suntao
 * @version 2018-01-15
 */
@Controller
@RequestMapping(value = "${adminPath}/resourcebus/tsResourceBus")
public class TsResourceBusController extends BaseController {

	@Autowired
	private TsResourceBusService tsResourceBusService;
	
	@Autowired
	private TsResourceService tsResourceService;
	
	@Autowired
	private TsMaxTableService tsMaxTableService ;
	
	@Autowired
	private TsResourceTypeService tsResourceTypeService ;
	
	@Autowired
	private TsResourceBookService tsResourceBookService ;
	
	@Autowired
	private TsApproverService tsApproverService ;
	
	@Autowired
	private WfworkitemService wfworkitemService;

	static boolean isOver = true;
	
	@ModelAttribute
	public TsResourceBus get(@RequestParam(required=false) String id) {
		TsResourceBus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsResourceBusService.get(id);
			
		}
		if (entity == null){
			entity = new TsResourceBus();
		}
		return entity;
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"index"})
	public String index(TsResourceBus tsResourceBus, Model model) {
		model.addAttribute("bj", tsResourceBus.getColumnId());
		return "modules/resourcebus/tsResourceBusIndex";
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"indexBM"})
	public String indexBM(TsResourceBus tsResourceBus, Model model) {
		return "modules/resourcebus/tsResourceBusIndexBM";
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"indexGR"})
	public String indexGR(TsResourceBus tsResourceBus, Model model) {
		return "modules/resourcebus/tsResourceBusIndexGR";
	}
	
	@RequestMapping(value = {"xxwj"})
	public String indexHome(TsResourceBus tsResourceBus, Model model) {
		return "modules/resourcebus/indexHome";
	}
	
	@RequestMapping(value = {"gdzc"})
	public String eslydsj(TsResourceBus tsResourceBus, Model model) {
		return "modules/resourcebus/eslydsj";
	}
	
	@RequestMapping(value = {"xxsb"})
	public String screen(TsResourceBus tsResourceBus, Model model) {
		return "modules/resourcebus/screen";
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"indexCX"})
	public String indexCX(TsResourceBus tsResourceBus, Model model) {
		model.addAttribute("bj", tsResourceBus.getColumnId());
		return "modules/resourcebus/tsResourceBusIndexCX";
	}
	
	@RequestMapping(value = {"census"})
	public String census(TsResourceBus tsResourceBus, Model model) {
		List list = tsResourceBusService.queryCensus(new HashMap<>());
		model.addAttribute("list", list);
		return "modules/resourcebus/census";
	}
	@RequestMapping(value = {"censust"})
	public String censust(TsResourceBus tsResourceBus, Model model) {
		List list = tsResourceBusService.queryCensus(new HashMap<>());
		model.addAttribute("list", list);
		return "modules/resourcebus/censust";
	}
	
	 @RequestMapping(value = "geCensustData")
	    @ResponseBody
	    public void geCensustData(String busTableType,HttpServletResponse response){
		    
	        List dataList= tsResourceBusService.queryCensus(new HashMap<>());
	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");

	    }
	 
	 @RequestMapping(value = "gesbData")
	    @ResponseBody
	    public void gesbData(String busTableType,HttpServletResponse response){
		    
	        List dataList= tsResourceBusService.gesbData(new HashMap<>());
	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");

	    }
	
	
	@RequestMapping(value = {"census2"})
	public String census2(TsResourceBus tsResourceBus, Model model) {
		List list = tsResourceBusService.queryCensus2(new HashMap<>());
		model.addAttribute("list", list);
		return "modules/resourcebus/census2";
	}
	
	@RequestMapping(value = {"censust2"})
	public String censust2(TsResourceBus tsResourceBus, Model model) {
		List list = tsResourceBusService.queryCensus2(new HashMap<>());
		model.addAttribute("list", list);
		return "modules/resourcebus/censust2";
	}
	
	
	 @RequestMapping(value = "geCensustData2")
	    @ResponseBody
	    public void geCensustData2(String busTableType,HttpServletResponse response){
		    
	        List dataList= tsResourceBusService.queryCensus2(new HashMap<>());
	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");

	    }
	 
	 
	 @RequestMapping(value = "geChartData")
	    @ResponseBody
	    public void geChartData(String busTableType,HttpServletResponse response){
		    
	        List dataList= tsResourceBusService.queryCensus2(new HashMap<>());
	        List dataLista= tsResourceBusService.queryCensusa(new HashMap<>());
	        List dataListb= tsResourceBusService.queryCensusb(new HashMap<>());
	        List dataListc= tsResourceBusService.queryCensusc(new HashMap<>());
	        List dataListxh= tsResourceBusService.queryCensus(new HashMap<>());
	        List list = new ArrayList();
	        list.add(dataList);
	        list.add(dataLista);
	        list.add(dataListb);
	        list.add(dataListc);
	        list.add(dataListxh);
	        renderString(response, JsonMapper.toJsonString(list),"text/html");

	    }
	

	@RequestMapping(value = {"list", ""})
	public String list(TsResource tsResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("bj", tsResource.getRemark48());
		TsResourceBus tsResourceBus = new TsResourceBus();
		tsResourceBus.setBusType(tsResource.getBusType());
		tsResourceBus.setTableComments(tsResource.getRemark49());
		String flag = tsResourceBus.getTableComments() ;
		DataMap dataMap = new DataMap() ;
		dataMap.put("name", tsResource.getName()) ;
		dataMap.put("state", tsResource.getState()) ;
		dataMap.put("weight", tsResource.getWeight()) ;
		dataMap.put("personnel", tsResource.getPersonnel()) ;
		dataMap.put("format", tsResource.getFormat()) ;
		dataMap.put("power", tsResource.getPower()) ;
		dataMap.put("department", tsResource.getDepartment()) ;
		dataMap.put("busType", tsResourceBus.getBusType()) ;
		model.addAttribute("busType", tsResourceBus.getBusType());
		tsResourceBus.setIsList("1");
		
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		if(null ==tsResourceBus.getBusType() || "".equals(tsResourceBus.getBusType())){
			TsResourceType tsResourceType = new TsResourceType();
			tsResourceType.setBj(tsResource.getRemark48());
			TsResourceType tsResourceTypep = new TsResourceType();
			tsResourceTypep.setId("0");
			tsResourceType.setParent(tsResourceTypep);
			List<TsResourceType> listType = tsResourceTypeService.findList(tsResourceType);
			if(listType!=null && listType.size()>0){
				tsResourceBus.setBusType(listType.get(0).getId());
			}else{
				tsResourceBus.setBusType("5455a96b7eb1467c831477384a820b1b");
			}
			
			 list = tsResourceBusService.getBus(tsResourceBus) ;
			 dataMap.put("busType", tsResourceBus.getBusType()) ;
			 tsResourceBus.setBusType(null);
		}
		String code = "(select c.`name` from ts_collection c where c.id=a.`local`) as local" ;
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
		code = code +",user_name as userName,create_date as createDate,office_name as officeName,state as state1" ;
		dataMap.put("code", code) ;
		
		
		
		if("BM".equals(flag)){
			dataMap.put("office_id", UserUtils.getOfficeList().get(0).getId());
		}
		if("GR".equals(flag)){
			dataMap.put("user_id", UserUtils.getUser().getId());
			dataMap.put("user_name", UserUtils.getUser().getName());
			
		}
		
		

		Page<Map> page = tsResourceBusService.findMapPage(new Page<Map>(request, response), dataMap); 
		model.addAttribute("page", page);
		}
		
		if(list!=null && list.size()!=0){
			
			list.remove(var) ;
			list.remove(busType) ;
		}else{
			TsResourceBus temp=  new TsResourceBus();
			temp.setColumnName("name");
			temp.setColumnComments("题名");
			list.add(temp);
		}
		
		model.addAttribute("TsResourceBus", list);
		
		if("GR".equals(flag)){
			return "modules/resourcebus/tsResourceBusListGR";
			
		}else if("CX".equals(flag)){
			
			return "modules/resourcebus/tsResourceBusListCX";
		}else{
			
			return "modules/resourcebus/tsResourceBusList";
			
		}
		
	}
	
	@RequestMapping(value = {"listQuery", ""})
	public String listQuery(TsResourceBus tsResourceBus, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("name", tsResourceBus.getColumnName()) ;
		model.addAttribute("busType", tsResourceBus.getBusType());
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String code = "(select c.`name` from ts_collection c where c.id=a.`local`) as local" ;
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
		
		dataMap.put("code", code) ;
		dataMap.put("busType", tsResourceBus.getBusType()) ;

		Page<Map> page = tsResourceBusService.findMapPage(new Page<Map>(request, response), dataMap); 
		model.addAttribute("page", page);
		}
		list.remove(var) ;
		list.remove(busType) ;
		model.addAttribute("TsResourceBus", list);
		return "modules/resourcebus/tsResourceBusQueryList";
	}
	
	
	
	@RequestMapping(value = "savezt")
	public String savezt(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
		//tsResourceBusService.saveBusTableData(genTable);
         System.out.println(genTable.getName());
         TsResource tsResource = new TsResource();
		for(int i=0 ; i<genTable.getColumnList().size();i++){
			tsResource.setId(genTable.getColumnList().get(i).getId());
			if("1".equals(genTable.getColumnList().get(i).getIsList())){
				tsResource.setState("3");
				tsResourceBusService.updateState(tsResource);
			}
			tsResource = new TsResource();
			
		}
		addMessage(redirectAttributes, "归档成功");
		return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+genTable.getName();
	}
	
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"local", ""})
	public String local(TsResourceBus tsResourceBus, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("name", tsResourceBus.getColumnName()) ;
		dataMap.put("local", tsResourceBus.getLocal()) ;
		dataMap.put("busType", tsResourceBus.getBusType()) ;
		dataMap.put("borrow_state", "2") ;
		Page<Map> page = tsResourceBusService.findLocalMapPage(new Page<Map>(request, response), dataMap);
		model.addAttribute("page", page);
		model.addAttribute("busType", tsResourceBus.getBusType());
		model.addAttribute("local", tsResourceBus.getLocal());
		return "modules/resourcebus/tsResourceBusLocal";
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:view")
	@RequestMapping(value = {"localSJ", ""})
	public String localSJ(TsResourceBus tsResourceBus, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("name", tsResourceBus.getColumnName()) ;
		dataMap.put("busType", tsResourceBus.getBusType()) ;
		dataMap.put("borrow_state", "1") ;
		Page<Map> page = tsResourceBusService.findLocalMapPage(new Page<Map>(request, response), dataMap);
		model.addAttribute("busType", tsResourceBus.getBusType());
		model.addAttribute("local", tsResourceBus.getLocal());
		model.addAttribute("page", page);
		return "modules/resourcebus/tsResourceBusLocalSelect";
	}
	
	
	
	
	

	@RequestMapping(value = "form")
	public String form(TsResourceBus tsResourceBus, Model model, RedirectAttributes redirectAttributes) {
		//查询实体资源信息
		TsResource tsResource = new TsResource();
		if(tsResourceBus.getId()!=null &&!"".equals(tsResourceBus.getId())){
			tsResource.setId(tsResourceBus.getId());
			tsResource = tsResourceService.get(tsResourceBus.getId()) ;
			tsResourceBus.setBusType(tsResource.getBusType());
		}
		
		tsResourceBus.setBusType(tsResourceBus.getBusType());
		
		tsResource.setBusType(tsResourceBus.getBusType());
		String tableComments = tsResourceBus.getTableComments() ;
		model.addAttribute("busType", tsResourceBus.getBusType());
		tsResource.setId(tsResourceBus.getId());
		if(tsResourceBus.getId()!= null && !"".equals(tsResourceBus.getId())  ){
			tsResource = tsResourceService.get(tsResourceBus.getId()) ;
			if(!"GLY".equals(tableComments))
			if("2".equals(tsResource.getState()) || "3".equals(tsResource.getState())){
				addMessage(redirectAttributes, "已审核文档不允许修改");
				return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType()+"&remark49=GR";
			}
		}
		//查询页面信息
		tsResourceBus.setIsEdit("1");
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String codeString = "" ;
		Map map = new HashMap() ;
		String value = "" ;
		try {
			 map = objectToMap(tsResource);
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
		TsResourceType type = tsResourceTypeService.get(tsResource.getBusType());
		if(type !=null)
		tsResource.setRemark20(type.getName());
		model.addAttribute("codeString", codeString);
		model.addAttribute("tsResource", tsResource);
		return "modules/resourcebus/tsResourceBusForm";
	}
	
	
	@RequestMapping(value = "view")
	public String view(TsResourceBus tsResourceBus, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("======================");
		//查询实体资源信息
		TsResource tsResource = new TsResource();
		tsResource.setBusType(tsResourceBus.getBusType());
		model.addAttribute("busType", tsResourceBus.getBusType());
		tsResource.setId(tsResourceBus.getId());
		if(tsResourceBus.getId()!= null && !"".equals(tsResourceBus.getId())){
			tsResource = tsResourceService.get(tsResourceBus.getId()) ;
			
		}
		//查询页面信息
		tsResourceBus.setIsEdit("1");
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String codeString = "" ;
		Map map = new HashMap() ;
		String value = "" ;
		try {
			 map = objectToMap(tsResource);
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
		TsResourceType type = tsResourceTypeService.get(tsResource.getBusType());
		if(type !=null)
		tsResource.setRemark20(type.getName());
		model.addAttribute("codeString", codeString);
		model.addAttribute("tsResource", tsResource);
		return "modules/resourcebus/tsResourceBusView";
	}
	

	@RequestMapping(value = "formjg")
	public String formjg(TsBorrow tsBorrow, Model model) {
		//查询实体资源信息
		TsResourceBus tsResourceBus = new TsResourceBus();
		tsResourceBus.setBusType(tsBorrow.getUserCode());
		TsResource tsResource = new TsResource();
		tsResource.setBusType(tsResourceBus.getBusType());
		model.addAttribute("busType", tsResourceBus.getBusType());
		tsResource.setId(tsResourceBus.getId());
		if(tsResourceBus.getId()!= null && !"".equals(tsResourceBus.getId())){
			tsResource = tsResourceService.get(tsResourceBus.getId()) ;
		}
		//查询页面信息
		tsResourceBus.setIsEdit("1");
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String codeString = "" ;
		Map map = new HashMap() ;
		String value = "" ;
		try {
			 map = objectToMap(tsResource);
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
		TsResourceType type = tsResourceTypeService.get(tsResource.getBusType());
		if(type !=null)
		tsResource.setRemark20(type.getName());
		model.addAttribute("codeString", codeString);
		model.addAttribute("tsResource", tsResource);
		return "modules/resourcebus/tsResourceBusForm";
	}
	
	
	
	public String getCodeHtml(String codeString,String  value,TsResourceBus tsResourceBus){
	
		 switch (tsResourceBus.getShowType()) {
		 case "input":
			 codeString += " <div class='control-group'>"+
		                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
		                " <div class='controls'>"+
		                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
		                "htmlEscape='false' type='text' maxlength='100' value='"+value+"' class='required '/>"+
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
	                "htmlEscape='false' type='text' maxlength='100' value='"+value+"' class='input-xlarge '/>"+
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

	@RequestMapping(value = "save")
	public String save(TsResource tsResource, Model model, RedirectAttributes redirectAttributes) {
	//	if (!beanValidator(model, tsResource)){
	//		return form(tsResource, model);
	//	}

		if(tsResource.getState()==null || "".equals(tsResource.getState())){
			tsResource.setState("1");
		}
		
		if("".equals(tsResource.getId()) || null == tsResource.getId())
		{
		tsResource.setUser(UserUtils.getUser());
		tsResource.setUserName(UserUtils.getUser().getName());
		tsResource.setOffice(UserUtils.getUser().getOffice());
		tsResource.setOfficeName(UserUtils.getUser().getOffice().getName());
		}else{
			TsResource temp = new TsResource();
			temp = tsResourceService.get(tsResource.getId());
			tsResource.setUser(temp.getUser());
			tsResource.setUserName(temp.getUserName());
			tsResource.setOffice(temp.getOffice());
			tsResource.setOfficeName(temp.getOfficeName());
		}
		tsResourceService.save(tsResource);
		addMessage(redirectAttributes, "保存资源成功");
		return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType()+"&remark49=GR";
	}
	
	
	
	@RequiresPermissions("resourcebus:tsResourceBus:edit")
	@RequestMapping(value = "upDown")
	public String upDown(TsResource tsResource, Model model, RedirectAttributes redirectAttributes) {
	//	if (!beanValidator(model, tsResource)){
	//		return form(tsResource, model);
	//	}
		TsResourceBook tBook = tsResourceBookService.get(tsResource.getId());
		tBook.setLocal(null);
		tBook.setBorrowState("1");
		addMessage(redirectAttributes, "下架成功");
		
		tsResourceBookService.save(tBook);
		
		return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/local?busType="+tsResource.getBusType()+"&local="+tsResource.getLocal();
	}
	
	@RequiresPermissions("resourcebus:tsResourceBus:edit")
	@RequestMapping(value = "delete")
	public String delete(TsResource tsResource, RedirectAttributes redirectAttributes) {
		String remark1 =tsResource.getRemark1() ;
		tsResource = tsResourceService.get(tsResource.getId()) ;
		System.out.println(remark1+"----");
		if(!"GLY".equals(remark1))
		if ("2".equals(tsResource.getState()) || "3".equals(tsResource.getState())){
			addMessage(redirectAttributes, "已审核文档不允许删除");
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType()+"&remark49=GR";
		}
		tsResourceService.delete(tsResource);
		addMessage(redirectAttributes, "删除资源成功");
		if("GLY".equals(remark1)){
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType();
		}
		return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType()+"&remark49=GR";
	}
	
	 @RequestMapping(value = "getSelectColumnData")
	    @ResponseBody
	    public void getSelectColumnData(String busTableType,HttpServletResponse response){
		    TsResourceBus tsResourceBus = new  TsResourceBus();
		    tsResourceBus.setBusType(busTableType);
	        List<TsResourceBus> dataList= tsResourceBusService.findList(tsResourceBus);
	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 
	 @RequestMapping(value = "getHtmlData")
	    @ResponseBody
	    public void getHtmlData(String busTableType,String id,HttpServletResponse response){
		    TsResource tsResource = new TsResource();
			tsResource.setBusType(busTableType);
		
			tsResource.setId(id);
			
			
			if(id!= null && !"".equals(id)){
				tsResource = tsResourceService.get(id) ;
			}
			TsResourceBus tsResourceBus = new TsResourceBus();
			//查询页面信息
			tsResourceBus.setIsEdit("1");
			List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
			
			String codeString = "" ;
			Map map = new HashMap() ;
			String value = "" ;
			try {
				 map = objectToMap(tsResource);
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
		
			
	        renderString(response, codeString,"text/html");
	       /* try {
	            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/

	    }
	 
	 
	 @RequiresPermissions("resourcebus:tsResourceBus:view")
	    @RequestMapping(value = "import/template")
	    public String importFileTemplate(TsResourceBus tsResourceBus,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		    tsResourceBus.setIsEdit("1");
			List<TsResourceBus> listBus = tsResourceBusService.getBus(tsResourceBus) ;
			List<String> listString = new ArrayList<String>() ;
			
			for (TsResourceBus property : listBus) {    
				listString.add(property.getColumnComments());      
			}
			
			
			try {
	            String fileName = "资源信息导入模板.xlsx";
	            System.out.println(fileName);
	    		List<TsResource> list = Lists.newArrayList();
	    		list.add(new TsResource());
	    		new ExportExcel("资源信息导入模板", listString).setDataList(list).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/resourcebus/tsResourceBus/list?busType="+tsResourceBus.getBusType();
	    }
	 
	 
	 /**
		 * 导入采购资源
		 * @param file
		 * @param redirectAttributes
		 * @return
		 */
		@RequiresPermissions("resourcebus:tsResourceBus:edit")
	    @RequestMapping(value = "import", method=RequestMethod.POST)
	    public String importFile(TsResourceBus tsResourceBus,MultipartFile file, RedirectAttributes redirectAttributes) {
			Map map = new HashMap<>();
			try {
				
				List<TsResourceBus> listBus = tsResourceBusService.getBusst(tsResourceBus) ;
				for (TsResourceBus property : listBus) {  
					map.put(property.getColumnName(), "1");
				
					  
				}
				
				int successNum = 0;
				int failureNum = 0;
				StringBuilder failureMsg = new StringBuilder();
				ImportExcel ei = new ImportExcel(file, 1, 0);
	
				List<TsResource> list = ei.getSbDataList(map,TsResource.class);
				for (TsResource tsResource : list){
				
					try{
						if (tsResource != null){
							tsResource.setBusType(tsResourceBus.getBusType());
							tsResource.setOffice(UserUtils.getUser().getOffice());
							tsResource.setUser(UserUtils.getUser());
							tsResource.setOfficeName(UserUtils.getUser().getOffice().getName());
							tsResource.setUserName(UserUtils.getUser().getName());
							tsResource.setState("1");
						//	tsResource.setBmState("1");
							tsResourceService.save(tsResource);
							successNum++;
						}else{
							failureMsg.append("<br/>资源名 "+tsResource.getName()+" 已存在; ");
							failureNum++;
						}
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>资源名 "+tsResource.getName()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>资源名 "+tsResource.getName()+" 导入失败："+ex.getMessage());
					}
				}
				if (failureNum>0){
					failureMsg.insert(0, "，失败 "+failureNum+" 条采购资源，导入信息如下：");
				}
				addMessage(redirectAttributes, "已成功导入 "+successNum+" 条待采购"+failureMsg);
			} catch (Exception e) {
				addMessage(redirectAttributes, "导入采购资失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/resourcebus/tsResourceBus/list?busType="+tsResourceBus.getBusType();
	    }
		
		@RequiresPermissions("resourcebus:tsResourceBus:edit")
	    @RequestMapping(value = "btnSubject", method=RequestMethod.POST)
	    public String btnSubject(TsResourceBus tsResourceBus, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			TsResource tsResourcetmp = new TsResource();
			tsResourcetmp.setBusType(tsResourceBus.getBusType());
			//tsResourcetmp.setBmState("1");
			TsResourceType tsResourceType= tsResourceTypeService.get(tsResourceBus.getBusType()) ;
			String bus_type = tsResourceType.getRemarks() ;
			
			List<TsResource> tsResourceList =  tsResourceBusService.getTsResourceList(tsResourcetmp) ;
			for (TsResource tsResource : tsResourceList){
				bmTsResource(tsResource,bus_type);
			}
			
			addMessage(redirectAttributes, "资源编目成功"+ tsResourceList.size());
			return "redirect:" + adminPath + "/resourcebus/tsResourceBus/list?busType="+tsResourceBus.getBusType();
	    }
		
		//资源编目
		public TsResource bmTsResource(TsResource tsResource ,String bus_type ){
			
			//索书号编目
			int serialNumber = 1 ;
			String bus_code = tsMaxTableService.findMaxCode("ts_resource", "key_code", bus_type) ;
			if(bus_code!=null && !"".equals(bus_code)){
				bus_code = bus_code.substring(bus_code.length()-5) ;
				serialNumber = Integer.parseInt(bus_code);
				serialNumber = serialNumber+1 ;
			}
			bus_code = String.format("%05d", serialNumber);
			bus_code = bus_type +"-" + bus_code ;
			
			//设置索书号及资源号
			//tsResource.setKeyCode(bus_code);
		//	tsResource.setBmState("2");
			tsResourceService.save(tsResource);
			bmTsResourceBook(tsResource, bus_type, bus_code);
            return null ;
			
		}
		//资源册编目
		public void bmTsResourceBook(TsResource tsResource ,String bus_type,String key_code ){
			TsResourceBook tsResourceBook = new TsResourceBook();
			tsResourceBook.setResourceId(tsResource.getId());
			tsResourceBookService.delete(tsResourceBook);
			
			String b_code ="";
			
			
			int number =  Integer.parseInt(tsResource.getNumber()) ;
			
			TsMaxTable tsMaxTable =  tsMaxTableService.get("1");
			int serialNumber = tsMaxTable.getCode() ;
			
			
			for(int i =0 ;i<number ;i++){
				tsResourceBook = new TsResourceBook();
				b_code = String.format("%02d", i+1);
				tsResourceBook.setResourceId(tsResource.getId());
				tsResourceBook.setKeyCode(key_code+"-"+b_code);
				tsResourceBook.setBookCode(b_code);
				
				b_code = String.format("%07d", serialNumber+i+1);
				tsResourceBook.setResourceCode(bus_type.split("-")[0]+"-"+b_code);
				tsResourceBook.setBookState("1");
				tsResourceBook.setBorrowState("1");
				tsResourceBookService.save(tsResourceBook);
				tsMaxTable.setCode(serialNumber+i+1);
			}
			tsMaxTableService.save(tsMaxTable);
	
		}
		
		/**
		 * 导出清点册
		 * @param user
		 * @param request
		 * @param response
		 * @param redirectAttributes
		 * @return
		 */
		
	
	    @RequestMapping(value = "export", method=RequestMethod.POST)
	    public String exportFile(TsResourceBus tsResourceBus, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			
	    	
	    	try {
	    		DataMap dataMap = new DataMap() ;
				dataMap.put("busType", tsResourceBus.getBusType()) ;
				dataMap.put("borrow_state", "2") ;
	            String fileName = "清点册"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            TsResource tsResource = new TsResource();
	            tsResource.setBusType(tsResourceBus.getBusType());
	            Page<TsResource> page = tsResourceService.findPage(new Page<TsResource>(request, response,-1), tsResource) ;
	            System.out.println(page.getList().size());
	            System.out.println(page.getList().size());
	    		new ExportExcel("清点册", TsResource.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出清点册失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/modules/resourcebus/tsResourceBusLocal?repage";
	    }
	    
	    
	    @RequiresPermissions("resourcebus:tsResourceBus:view")
		@RequestMapping(value = {"approval", ""})
		public String approval(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
	    	User user = UserUtils.getUser();
	    	tsResourceType.setManager(user.getId());
			List<TsResourceType> list = tsResourceTypeService.getTsResourceTypeBy(tsResourceType) ;
			List<TsResource> data = new ArrayList<TsResource>() ;
			
			for (TsResourceType temp : list) {
				tsResourceType = new TsResourceType();
				tsResourceType.setParentIds(temp.getId());
				tsResourceType.setRemarks("1");
				List<TsResource> list1 = tsResourceTypeService.getTsResource(tsResourceType);
				for (TsResource tsResource : list1) {
					data.add(tsResource) ;
				}
			}
			
			model.addAttribute("list", data);
			return "modules/resourcetype/tsResourceApproval";
		}
	    
	    
		@RequestMapping(value = {"leaderApproval", ""})
		public String leaderApproval(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
	    	User user = UserUtils.getUser();
	    	
	    	tsResourceType.setManager(user.getId());
			List<TsResourceType> list = tsResourceTypeService.getTsResourceTypeBy(tsResourceType) ;
			List<TsResource> data = new ArrayList<TsResource>() ;
			tsResourceType.setModel("1");
			tsResourceType.setName(user.getOffice().getId());
			tsResourceType.setId(user.getOffice().getName());
			data = tsResourceTypeService.getLdResource(tsResourceType);
			
			model.addAttribute("list", data);
			return "modules/resourcetype/leaderApproval";
		}
	    
	    
	    @RequestMapping(value = "savegd")
		public String savegd(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
			//tsResourceBusService.saveBusTableData(genTable);
	         TsResource tsResource = new TsResource();
			for(int i=0 ; i<genTable.getColumnList().size();i++){
				tsResource.setId(genTable.getColumnList().get(i).getId());
				if("1".equals(genTable.getColumnList().get(i).getIsList())){
					if("T".equals(genTable.getComments())){
						
						tsResource.setState("0");
					}else{
						tsResource.setState("2");
					}
					
					tsResourceBusService.updateState(tsResource);
				}
				tsResource = new TsResource();
			
				
			}
			addMessage(redirectAttributes, "审核成功");
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/leaderApproval";
		}
	    
	    
	    @RequiresPermissions("resourcebus:tsResourceBus:view")
		@RequestMapping(value = {"approval2", ""})
		public String approval2(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
	    	User user = UserUtils.getUser();
	    	tsResourceType.setManager(user.getId());
			List<TsResourceType> list = tsResourceTypeService.getTsResourceTypeBy(tsResourceType) ;
			List<TsResource> data = new ArrayList<TsResource>() ;
			tsResourceType.setParentIds("");
			tsResourceType.setRemarks("2");
			List<TsResource> list1 = tsResourceTypeService.getTsResource(tsResourceType);
			
			
			model.addAttribute("list", list1);
			return "modules/resourcetype/tsResourceApproval2";
		}
	    
	    
	    @RequestMapping(value = "savegd2")
		public String savegd2(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
			//tsResourceBusService.saveBusTableData(genTable);
	    	System.out.println(genTable.getComments());
	         TsResource tsResource = new TsResource();
			for(int i=0 ; i<genTable.getColumnList().size();i++){
				tsResource.setId(genTable.getColumnList().get(i).getId());
				if("1".equals(genTable.getColumnList().get(i).getIsList())){
					if("T".equals(genTable.getComments())){
						
						tsResource.setState("0");
					}else{
						tsResource.setState("3");
					}
					
					tsResourceBusService.updateState(tsResource);
				}
				tsResource = new TsResource();
				
				
			}
			addMessage(redirectAttributes, "归档成功");
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/approval2";
		}
	    
	    
	    @RequestMapping(value = "info")
		public String info(TsResource tsResource, Model model, RedirectAttributes redirectAttributes) {

			
			
			logger.info("-------执行synchronousFileTask开始------->" + DateUtils.getDateTime());
			if(isOver){
				isOver = false;
				logger.info("-------本次开始执行------->" + DateUtils.getDateTime());
				WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
				ServletContext servletContext = webApplicationContext.getServletContext(); 
				String projectPath=servletContext.getRealPath("/").replace("\\", "/");
				createDirAndCopyFile(projectPath);
				logger.info("-------本次执行完成------->" + DateUtils.getDateTime());
				isOver = true;
			}else{
				//上次的还没执行完成
				logger.info("-------等待上一次执行完成------->" + DateUtils.getDateTime());
			}
			logger.info("-------执行synchronousFileTask结束------->" + DateUtils.getDateTime());
			
			
			
			
		
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+tsResource.getBusType()+"&remark49=GR";
		}
	    
	    
	    public boolean createDirAndCopyFile(String projectPath){
			//获取数据
			logger.info("-------获取数据------->" + DateUtils.getDateTime());
			System.out.println("1");
			List<TsResource> tempList = tsResourceService.findAllList(new TsResource());
			System.out.println(tempList);
			System.out.print("tempList.size()=["+tempList.size());
			//这里是调用  
			List<TsResource> lists = tsResourceService.findAllListNoParam();
//			List<TsResource> list = tsRsService.findAllList2();
			System.out.print("lists.size()=["+lists.size());
			for (TsResource list:lists) {
				//获取存储的相对路径
				String files = list.getFiles();
				//获取存储的树结构路径
				String busType = list.getBusType();
				//获取树结构路径对象
				TsResourceType rsType = tsResourceTypeService.get(busType);
				if(null==rsType){
					continue;
				}
				String FileStoreRootPath = com.thinkgem.jeesite.common.config.User.getConfig("FileStoreRootPath");
				if(StringUtils.isBlank(FileStoreRootPath)){
					//获取默认的根节点路径
					FileStoreRootPath = com.thinkgem.jeesite.common.config.User.getConfig("DefaultFileStoreRootPath");
					FileStoreRootPath = System.getenv(FileStoreRootPath);
					if(StringUtils.isBlank(FileStoreRootPath)){
						FileStoreRootPath = "C:\\";
					}
				}
				try {
					if(!(new File(FileStoreRootPath).exists())){
						FileUtils.forceMkdir(new File(FileStoreRootPath));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//获取树结构路径对象的父ID
				String pid = rsType.getParentId();
				//获取树结构路径对象的父ID
				String pids = rsType.getParentIds();
				//获取树结构路径对象的名称
				String name = rsType.getName();
				//创建目录
				//创建子级目录
				System.out.print("    files=["+files);
				System.out.print("    name=["+name);
				System.out.print("    pids=["+pids);
				StringBuffer temp_path = new StringBuffer() ;
				temp_path.append(FileStoreRootPath);
//				rsType = tsRsTypeService.get(pid);
//				temp_path.append(File.separator);
//				temp_path.append(name);
//				System.out.print("    请求的URL地址：=【"+Servlets.getRequest().getContextPath());
				String[] ppaths = pids.split(",");
				try {
					for (int i = 0; i < ppaths.length; i++) {
						if(i==0){
							continue;
						}
						rsType = tsResourceTypeService.get(ppaths[i]);
						if(null!=rsType){
							temp_path.append(File.separator);
							temp_path.append(rsType.getName());
						}
					}
					temp_path.append(File.separator);
					temp_path.append(name);
					if(!(new File(temp_path.toString()).exists())){
						FileUtils.forceMkdir(new File(temp_path.toString()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				//获取文件
				String file_all_path = files.replace("|/jeesite", projectPath);
				System.out.println("file_all_path=["+file_all_path);
				 try {
						file_all_path = URLDecoder.decode(file_all_path, "UTF-8");
						System.out.println(file_all_path);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} // 设置编码字符集为utf-8
				File temp_file = new File(file_all_path);
//				fileName = temp_file.getName()
		        String fName = file_all_path.trim();  
		        String fileName = fName.substring(fName.lastIndexOf("/")+1);  
		        //或者  
		        System.out.println("fileName = " + fileName);  
				temp_path.append(File.separator);
				temp_path.append(fileName);
				String newFile_all_path = temp_path.toString();
	           
				if(temp_file.exists()){
					
					FileUtils.copyFileCover(file_all_path, newFile_all_path,true);
					//设置对象属性
					list.setRemark50("SAVED");
					tsResourceService.save(list);
				}
				
			}
			
			return true;
		}
	    
	    public String typeName = null ;
	    
	    public String getTypeName (String busTypeName ,String busType){
	    	
	    	TsResourceType tsResourceType = tsResourceTypeService.get(busType);
	    	
	    	
	    	if("".equals(busTypeName)){
	    		busTypeName = tsResourceType.getName() ;
	    		
	    	}else{
	    		
	    		busTypeName = tsResourceType.getName()+"——" +busTypeName ;
	    	}
	    	
	    	
	    	if((null != tsResourceType.getManager() && !"".equals(tsResourceType.getManager())) || "0".equals(tsResourceType.getParentId())){
	    		typeName = busTypeName ;
	    		
	    	}else{
	    		getTypeName(busTypeName,tsResourceType.getParentId());
	    	}
	    	
	    	System.out.println(busTypeName+"-->");
	    	
	    	return busTypeName ;
	    	
	    	
	    	
	    	
	    }
	    
	   public String manager =null;
	    
       public String getManager (String busType){
	    	
	    	TsResourceType tsResourceType = tsResourceTypeService.get(busType);

	    	if((null != tsResourceType.getManager() && !"".equals(tsResourceType.getManager())) || "0".equals(tsResourceType.getParentId())){
	    		
	    		manager = tsResourceType.getManager() ;
	    	}else{
	    		getManager(tsResourceType.getParentId());
	    	}
	    	
	    	return tsResourceType.getManager() ;
	    	
	    	
	    	
	    	
	    }
	    
	    
	 
		@RequestMapping(value = "approver")
		public String approver(TsResource tsResource, RedirectAttributes redirectAttributes) {
			String busType = tsResource.getBusType();
			tsResource = tsResourceService.get(tsResource.getId()) ;
			
			TsApprover tsApprover = new TsApprover();
			User user = UserUtils.getUser();
			Office office = UserUtils.getOfficeList().get(0);
			tsApprover.setName(tsResource.getName());
			tsApprover.setFile(tsResource.getFiles());
			
			//tsApprover.setUserid(tsResource.getUser().getId());
			
			//tsApprover.setUsername(tsResource.getUserName());
			//tsApprover.setOfficeid(tsResource.getOffice().getId());
			//tsApprover.setOfficename(tsResource.getOfficeName());
			tsApprover.setTypeName(tsResource.getBusType());
			tsApprover.setApplicantId(user.getId());
			tsApprover.setApplicantName(user.getName());
			tsApprover.setApplicantDeptId(office.getId());
			tsApprover.setApplicantDeptName(office.getName());
			tsApprover.setApplicantDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			tsApprover.setState("1");
			tsApprover.setApproverId("1");
			tsApprover.setApproverName("2");
			tsApprover.setResourceId(tsResource.getId());
			
			
			 getTypeName("",tsResource.getBusType());
			 getManager(tsResource.getBusType());

			 
			
			tsApprover.setTypeName(typeName);
			tsApprover.setApproverId(manager);
			if(manager !=null && !"".equals(manager)){
				
				
				

				BpsManage bps = new BpsManage();
				String[] userid  = manager.split(",");
				String[] type = new String[userid.length];
				for(int i=0 ;i<type.length;i++){
					type[i]="emp";
				}
				long pid = 0;
				try {
					pid = bps.startBPS("现行电子文件申请", "com.primeton.bps.approver");
	
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
				
				
				
				
				tsApprover.setProcessinstid(pid);
				
				
				tsApproverService.save(tsApprover);
				addMessage(redirectAttributes, "申请成功");
			}else{
				
				addMessage(redirectAttributes, "申请失败，无型号负责人");
			}
			
			
			
	
			return "redirect:"+Global.getAdminPath()+"/resourcebus/tsResourceBus/list?busType="+busType+"&remark49=CX";
		}
		
		
		

}