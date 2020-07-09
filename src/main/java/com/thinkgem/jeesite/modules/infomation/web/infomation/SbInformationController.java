/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.web.infomation;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.business.entity.supplier.SwSupplier;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.infomation.entity.infomation.SbInformation;
import com.thinkgem.jeesite.modules.infomation.service.infomation.SbInformationService;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 信息化设备Controller
 * @author suntao
 * @version 2020-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/infomation/infomation/sbInformation")
public class SbInformationController extends BaseController {

	@Autowired
	private SbInformationService sbInformationService;
	
	@Autowired
	private TsResourceBusService tsResourceBusService;
	
	@ModelAttribute
	public SbInformation get(@RequestParam(required=false) String id) {
		SbInformation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbInformationService.get(id);
		}
		if (entity == null){
			entity = new SbInformation();
		}
		return entity;
	}
	
	@RequiresPermissions("infomation:infomation:sbInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbInformation sbInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		DataMap dataMap = new DataMap() ;
		dataMap.put("sbType", sbInformation.getSbType()) ;
		dataMap.put("type", sbInformation.getType()) ;
		dataMap.put("usepeoplename", sbInformation.getUsepeoplename()) ;
		dataMap.put("sbIpaddress", sbInformation.getSbIpaddress()) ;
		dataMap.put("sbMacaddress", sbInformation.getSbMacaddress()) ;
		dataMap.put("useinfo", sbInformation.getUseinfo()) ;
		dataMap.put("name", sbInformation.getName()) ;
		dataMap.put("sbSecret", sbInformation.getSbSecret()) ;


		TsResourceBus tsResourceBus = new TsResourceBus();
		if(null != sbInformation.getSbType() && !"".equals(sbInformation.getSbType())){
			tsResourceBus.setBusType(sbInformation.getSbType());
		}else{
			tsResourceBus.setBusType("9fb092fe22eb4b618a455af79d6b8806");
		}
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String code = "" ;
		
		for(int i = 0 ;i<list.size() ; i++){
			
			tsResourceBus = list.get(i) ;
			if("treeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"_name");
			}
			if("".equals(code)){
				code =tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
		}
		System.out.println(code);


		Page<Map> page = sbInformationService.findMapPage(new Page<Map>(request, response), dataMap); 
		model.addAttribute("sbType", sbInformation.getSbType());
		model.addAttribute("TsResourceBus", list);
		model.addAttribute("page", page);
		return "modules/infomation/infomation/sbInformationList";
	}
	
	
	
	@RequiresPermissions("infomation:infomation:sbInformation:view")
	@RequestMapping(value = {"index"})
	public String index(SbInformation sbInformation, Model model) {
		return "modules/infomation/infomation/sbInformationIndex";
	}

	@RequiresPermissions("infomation:infomation:sbInformation:view")
	@RequestMapping(value = "form")
	public String form(SbInformation sbInformation, Model model) {
		model.addAttribute("sbInformation", sbInformation);
		
		
		
		
		
		//查询实体资源信息
		if(sbInformation.getId()!=null && !"".equals(sbInformation.getId())){
			sbInformation = sbInformationService.get(sbInformation.getId()) ;
		}
		TsResourceBus tsResourceBus = new TsResourceBus();
		tsResourceBus.setBusType(sbInformation.getSbType());
		model.addAttribute("sbType", sbInformation.getSbType());
		//查询页面信息
		tsResourceBus.setIsEdit("1");
		if(null == tsResourceBus.getBusType() || "".equals(tsResourceBus.getBusType())){
	
			tsResourceBus.setBusType("9fb092fe22eb4b618a455af79d6b8806");
		}
		List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
		String codeString = "" ;
		Map map = new HashMap() ;
		Object value = "" ;
		String valueName="";
		try {
			 map = objectToMap(sbInformation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0 ;i<list.size() ; i++){
			tsResourceBus = list.get(i) ;
			if( null == map.get(tsResourceBus.getTableName())){
				value = "";
			}else{
				value =map.get(tsResourceBus.getTableName()) ;
			}
			valueName = (String) map.get(tsResourceBus.getTableName()+"Name") ;
			codeString = getCodeHtml(codeString, value, valueName,tsResourceBus);
		}

		model.addAttribute("codeString", codeString);
		model.addAttribute("sbInformation", sbInformation);

		System.out.println(codeString);
		
		return "modules/infomation/infomation/sbInformationForm";
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
	
	public String getCodeHtml(String codeString,Object  value,String valueName ,TsResourceBus tsResourceBus){
		if(valueName==null){
			valueName="";
		}
		 switch (tsResourceBus.getShowType()) {
		 case "input":
			 codeString += " <div class='control-group'>"+
		                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
		                " <div class='controls'>"+
		                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
		                "htmlEscape='false' type='text'  value='"+value+"' />"+
		                " </div>"+
		                " </div>  " ; 
			break;
		case "dateselect":
			codeString += " <div class='control-group'>"+                              
	                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
	                
			" <div class='controls'>"+
			" <input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' type='text'  "
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

		case "treeselect":
			codeString += " <div class='control-group'>"+                              
	                " <label class='control-label'>"+tsResourceBus.getColumnComments()+"：</label>"+
	                
			" <div class='controls'>"+
			"<input id='"+tsResourceBus.getTableName()+"_Id' name='"+tsResourceBus.getTableName()+"' class='required' type='hidden' value='"+value+"'/>"+
			"<input id='"+tsResourceBus.getTableName()+"_Name' name='"+tsResourceBus.getTableName()+"Name' readonly='readonly' value='"+valueName+"' type='text' data-msg-required=''"+
			"class='required' style=''/><a id='"+tsResourceBus.getTableName()+"_Button' href='javascript:' class='btn  ' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>&nbsp;&nbsp;"+
		"</div>"+
		
					"</div> " +
					 " </div>  " ; 
			break;
		case "hidden":
			 codeString += "<form:hidden path='"+tsResourceBus.getTableName()+"'/>";
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
	
	public String getSelect(String type , Object value){
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

	@RequiresPermissions("infomation:infomation:sbInformation:edit")
	@RequestMapping(value = "save")
	public String save(SbInformation sbInformation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sbInformation)){
			return form(sbInformation, model);
		}
		sbInformationService.save(sbInformation);
		addMessage(redirectAttributes, "保存信息化设备成功");
		return "redirect:"+Global.getAdminPath()+"/infomation/infomation/sbInformation/?sbType="+sbInformation.getSbType();
	}
	
	@RequiresPermissions("infomation:infomation:sbInformation:edit")
	@RequestMapping(value = "delete")
	public String delete(SbInformation sbInformation, RedirectAttributes redirectAttributes) {
		sbInformationService.delete(sbInformation);
		addMessage(redirectAttributes, "删除信息化设备成功");
		return "redirect:"+Global.getAdminPath()+"/infomation/infomation/sbInformation/?repage";
	}
	
	/**
	 * 导出设备数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SbInformation sbInformation, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("sbType", sbInformation.getSbType()) ;
		try {
            String fileName = "仪器设备信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SbInformation> page = sbInformationService.findPage(new Page<SbInformation>(), sbInformation) ;
    		new ExportExcel("仪器设备信息", SbInformation.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出设备信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	@RequiresPermissions("infomation:infomation:sbInformation:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(TsResourceBus tsResourceBus,HttpServletResponse response, RedirectAttributes redirectAttributes) {
	    tsResourceBus.setIsEdit("1");
		List<TsResourceBus> listBus = tsResourceBusService.getBus(tsResourceBus) ;
		List<String> listString = new ArrayList<String>() ;
		
		for (TsResourceBus property : listBus) {    
			listString.add(property.getColumnComments());      
		}
		
		
		try {
            String fileName = "信息设备导入模板.xlsx";
            System.out.println(fileName);
    		List<TsResource> list = Lists.newArrayList();
    		list.add(new TsResource());
    		new ExportExcel("信息设备数据", listString).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/infomation/infomation/sbInformation?sbType="+tsResourceBus.getBusType();
    }
 
 /**
	 * 导入采购资源
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("infomation:infomation:sbInformation:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(TsResourceBus tsResourceBus,MultipartFile file, RedirectAttributes redirectAttributes) {
		Map map = new HashMap<>();
		try {
			List<TsResourceBus> listBus = tsResourceBusService.getBusst(tsResourceBus) ;
			for (TsResourceBus property : listBus) {  
				map.put(property.getColumnName(), "1");
				System.out.println(property.getColumnName()+"___------");
				  
			}

			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SbInformation> list = ei.getSbDataList(map,SbInformation.class);
			
			
			
			for (SbInformation sbInformation : list){
			
				try{
					if (sbInformation != null){
						sbInformation.setSbType(tsResourceBus.getBusType());
						sbInformationService.save(sbInformation);
					//	sbEquipmentService.save(sbEquipment);
						successNum++;
					}else{
						failureMsg.append("<br/>资源名 "+sbInformation.getName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>资源名 "+sbInformation.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>资源名 "+sbInformation.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条资源，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条待"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入资失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/infomation/infomation/sbInformation?sbType="+tsResourceBus.getBusType();
    }
	
	@RequestMapping(value = "geChartData")
    @ResponseBody
    public void geChartData(String busTableType,HttpServletResponse response){
	    
        List dataList= sbInformationService.queryInformationInfo(new HashMap<>());
        List mjInfo= sbInformationService.querymjInfo(new HashMap<>());
        List teamnameInfo= sbInformationService.queryTeamnameInfo(new HashMap<>());
        List useInfo= sbInformationService.queryUseInfo(new HashMap<>());
        List typeInfo= sbInformationService.queryTypeInfo(new HashMap<>());

        List list = new ArrayList();
        list.add(dataList);
        list.add(mjInfo);
        list.add(teamnameInfo);
        list.add(useInfo);
        list.add(typeInfo);


        renderString(response, JsonMapper.toJsonString(list),"text/html");

    }
	@RequestMapping(value = "deleteType")
    @ResponseBody
	public void deleteType(String  sbType, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		SbInformation sbInformation = new SbInformation();
		sbInformation.setSbType(sbType);
        if(sbType!=null &&!"".equals(sbType)){
        	sbInformationService.deleteType(sbInformation);
        }

		renderString(response, JsonMapper.toJsonString(null),"text/html");
	}

}