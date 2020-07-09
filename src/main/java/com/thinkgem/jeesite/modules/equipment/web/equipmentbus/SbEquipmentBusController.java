/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.web.equipmentbus;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.thinkgem.jeesite.modules.borrow.service.sbborrow.SbBorrowService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.equipmentbus.SbEquipmentBus;
import com.thinkgem.jeesite.modules.equipment.entity.equipmenttype.SbEquipmentType;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;
import com.thinkgem.jeesite.modules.equipment.service.equipment.SbEquipmentService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentbus.SbEquipmentBusService;
import com.thinkgem.jeesite.modules.equipment.service.equipmentfunction.SbFunctionTypeService;
import com.thinkgem.jeesite.modules.equipment.service.equipmenttype.SbEquipmentTypeService;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.sbdeploy.entity.SbDeploy;
import com.thinkgem.jeesite.modules.sbdeploy.service.SbDeployService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 仪器设备配置Controller
 * @author suntao
 * @version 2019-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/equipmentbus/sbEquipmentBus")
public class SbEquipmentBusController extends BaseController {

	@Autowired
	private SbEquipmentBusService sbEquipmentBusService;
	
	@Autowired
	private SbEquipmentTypeService sbEquipmentTypeService;
	
	@Autowired
	private SbFunctionTypeService sbFunctionTypeService;
	
	@Autowired
	private TsResourceBusService tsResourceBusService;
	
	@Autowired
	private SbEquipmentService sbEquipmentService;
	
	@Autowired
	private SbDeployService sbDeployService ;
	
	@Autowired
	private SbBorrowService sbBorrowService;
	
	@ModelAttribute
	public SbEquipmentBus get(@RequestParam(required=false) String id) {
		SbEquipmentBus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sbEquipmentBusService.get(id);
		}
		if (entity == null){
			entity = new SbEquipmentBus();
		}
		return entity;
	}
	
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"index"})
	public String index(TsResourceBus tsResourceBus, Model model) {
		return "modules/equipment/equipmentbus/sbEquipmentBusIndex";
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"list", ""})
	public String list(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("field20", sbEquipment.getField20()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("sbcode", sbEquipment.getSbcode()) ;
		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", sbEquipment.getState()) ;
		kmap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("field20", sbEquipment.getField20()) ;
		kmap.put("teamname", sbEquipment.getTeamname()) ;
		kmap.put("sbcode", sbEquipment.getSbcode()) ;
		TsResourceBus tsResourceBus = new TsResourceBus();
		
		
		if(null != sbEquipment.getFsType() && !"".equals(sbEquipment.getFsType())){
			tsResourceBus.setBusType(sbEquipment.getFsType());
		}else{
			tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		}
		
		SbDeploy sbDeploy = new SbDeploy();
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy );
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = null ;
		if(sbDeploy!=null && sbDeploy.getBusType()!=null && !"".equals(sbDeploy.getBusType()) ){
			TsResourceBus temp = new TsResourceBus();
			String[] ids = sbDeploy.getBusType().split(",");
			 list = tsResourceBusService.getBusById(ids) ;
		}else{
			 list = tsResourceBusService.getBus(tsResourceBus) ;
		}
		
		
		String code = "" ;
		
		for(int i = 0 ;i<list.size() ; i++){
			
			tsResourceBus = list.get(i) ;
			if("treeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"_name");
			}
			if("officeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"name");
			}
			if("".equals(code)){
				code =tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
			
			
		}
		
		dataMap.put("code", code) ;
		
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("code", code) ;
		
		kmap.put("name", sbEquipment.getName()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		model.addAttribute("fsType", sbEquipment.getFsType());
	
		model.addAttribute("TsResourceBus", list);
		return "modules/equipment/equipmentbus/sbEquipmentBusList";
	}
	
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"simpleList"})
	public String simpleList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;

		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", sbEquipment.getState()) ;
		kmap.put("field5", sbEquipment.getField5()) ;
		kmap.put("teamname", sbEquipment.getTeamname()) ;
		
		
		
		TsResourceBus tsResourceBus = new TsResourceBus();
		if(null != sbEquipment.getFsType() && !"".equals(sbEquipment.getFsType())){
			tsResourceBus.setBusType(sbEquipment.getFsType());
		}else{
			tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		}
		
		SbDeploy sbDeploy = new SbDeploy();
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy );
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = null ;
		if(sbDeploy!=null && sbDeploy.getBusType()!=null && !"".equals(sbDeploy.getBusType()) ){
			TsResourceBus temp = new TsResourceBus();
			String[] ids = sbDeploy.getBusType().split(",");
			 list = tsResourceBusService.getBusById(ids) ;
		}else{
			 list = tsResourceBusService.getBus(tsResourceBus) ;
		}
		
		
		String code = "" ;
		
		for(int i = 0 ;i<list.size() ; i++){
			
			tsResourceBus = list.get(i) ;
			if("treeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"_name");
			}
			if("officeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"name");
			}
			if("".equals(code)){
				code =tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
			
			
		}
		
		dataMap.put("code", code) ;
		
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("code", code) ;
		
		kmap.put("name", sbEquipment.getName()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		model.addAttribute("fsType", sbEquipment.getFsType());
	
		model.addAttribute("TsResourceBus", list);
		return "modules/equipment/equipmentbus/sbEquipmentBusSimpleList";
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"seoList"})
	public String seoList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("field4", sbEquipment.getField4()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("sbcode", sbEquipment.getSbcode()) ;
		dataMap.put("type", sbEquipment.getType()) ;
		dataMap.put("field1", sbEquipment.getField1()) ;
		dataMap.put("team", sbEquipment.getTeam()) ;
		dataMap.put("usepeoplename", sbEquipment.getUsepeoplename()) ;
		dataMap.put("sbType", sbEquipment.getSbType()) ;
		dataMap.put("price", sbEquipment.getPrice()) ;
		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", sbEquipment.getState()) ;
		kmap.put("field5", sbEquipment.getField5()) ;
		kmap.put("field4", sbEquipment.getField4()) ;
		kmap.put("teamname", sbEquipment.getTeamname()) ;
		kmap.put("sbcode", sbEquipment.getSbcode()) ;
		kmap.put("type", sbEquipment.getType()) ;
		kmap.put("field1", sbEquipment.getField1()) ;
		kmap.put("team", sbEquipment.getTeam()) ;
		kmap.put("usepeoplename", sbEquipment.getUsepeoplename()) ;
		kmap.put("sbType", sbEquipment.getSbType()) ;
		kmap.put("price", sbEquipment.getPrice()) ;
		TsResourceBus tsResourceBus = new TsResourceBus();
		
		if(null != sbEquipment.getFsType() && !"".equals(sbEquipment.getFsType())){
			tsResourceBus.setBusType(sbEquipment.getFsType());
		}else{
			tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		}
		
		SbDeploy sbDeploy = new SbDeploy();
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy );
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = null ;
		if(sbDeploy!=null && sbDeploy.getBusType()!=null && !"".equals(sbDeploy.getBusType()) ){
			TsResourceBus temp = new TsResourceBus();
			String[] ids = sbDeploy.getBusType().split(",");
			 list = tsResourceBusService.getBusById(ids) ;
		}else{
			 list = tsResourceBusService.getBus(tsResourceBus) ;
		}
		
		
		String code = "" ;
		
		for(int i = 0 ;i<list.size() ; i++){
			
			tsResourceBus = list.get(i) ;
			if("treeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"_name");
			}
			if("officeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"name");
			}
			if("".equals(code)){
				code =tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
			
			
		}
		
		dataMap.put("code", code) ;
		
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("code", code) ;
		
		kmap.put("name", sbEquipment.getName()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		model.addAttribute("fsType", sbEquipment.getFsType());
	
		model.addAttribute("TsResourceBus", list);
		return "modules/equipment/equipmentbus/sbEquipmentBusSeoList";
	}
	
	
	//部门台账查询
	@RequestMapping(value = {"listTeam"})
	public String listTeam(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("teamname", UserUtils.getUser().getOffice().getName()) ;
		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", sbEquipment.getState()) ;
		kmap.put("field5", sbEquipment.getField5()) ;
		kmap.put("teamname", UserUtils.getUser().getOffice().getName()) ;
		TsResourceBus tsResourceBus = new TsResourceBus();
		
		if(null != sbEquipment.getFsType() && !"".equals(sbEquipment.getFsType())){
			tsResourceBus.setBusType(sbEquipment.getFsType());
		}else{
			tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		}
		
		SbDeploy sbDeploy = new SbDeploy();
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy );
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = null ;
		if(sbDeploy!=null && sbDeploy.getBusType()!=null && !"".equals(sbDeploy.getBusType()) ){
			TsResourceBus temp = new TsResourceBus();
			String[] ids = sbDeploy.getBusType().split(",");
			 list = tsResourceBusService.getBusById(ids) ;
		}else{
			 list = tsResourceBusService.getBus(tsResourceBus) ;
		}
		
		
		String code = "" ;
		
		for(int i = 0 ;i<list.size() ; i++){
			
			tsResourceBus = list.get(i) ;
			if("treeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"_name");
			}
			if("officeselect".equals(tsResourceBus.getShowType())){
				list.get(i).setColumnName(tsResourceBus.getColumnName()+"name");
			}
			if("".equals(code)){
				code =tsResourceBus.getColumnName();
			}else{
				code += ","+tsResourceBus.getColumnName();
			}
			
			
		}
		
		dataMap.put("code", code) ;
		
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("code", code) ;
		
		kmap.put("name", sbEquipment.getName()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		model.addAttribute("fsType", sbEquipment.getFsType());
	
		model.addAttribute("TsResourceBus", list);
		return "modules/equipment/equipmentbus/sbEquipmentBusTeamList";
	}
	
	
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"selectEquipment"})
	public String selectEquipment(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", sbEquipment.getState()) ;
		kmap.put("field5", sbEquipment.getField5()) ;
		kmap.put("teamname", sbEquipment.getTeamname()) ;
		TsResourceBus tsResourceBus = new TsResourceBus();
		
		if(null != sbEquipment.getFsType() && !"".equals(sbEquipment.getFsType())){
			tsResourceBus.setBusType(sbEquipment.getFsType());
		}else{
			tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
		}
		
		SbDeploy sbDeploy = new SbDeploy();
		sbDeploy.setUser(UserUtils.getUser());
		sbDeploy = sbDeployService.getByUserId(sbDeploy );
		tsResourceBus.setIsList("1");
		List<TsResourceBus> list = null ;
		if(sbDeploy!=null && sbDeploy.getBusType()!=null && !"".equals(sbDeploy.getBusType()) ){
			TsResourceBus temp = new TsResourceBus();
			String[] ids = sbDeploy.getBusType().split(",");
			 list = tsResourceBusService.getBusById(ids) ;
		}else{
			 list = tsResourceBusService.getBus(tsResourceBus) ;
		}
		
		
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
		
		dataMap.put("code", code) ;
		
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("code", code) ;
		
		kmap.put("name", sbEquipment.getName()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		model.addAttribute("fsType", sbEquipment.getFsType());
	
		model.addAttribute("TsResourceBus", list);
		return "modules/equipment/equipmentbus/selectEquipment";
	}
	
	
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"borrowList"})
	public String borrowList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", "1") ;
		dataMap.put("field5", sbEquipment.getField5()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("usepeoplename", "仪器库房") ;
		Map kmap = new HashMap();
		kmap.put("fsType", sbEquipment.getFsType()) ;
		kmap.put("state", "1") ;
		kmap.put("field5", sbEquipment.getField5()) ;
		kmap.put("teamname", sbEquipment.getTeamname()) ;
		kmap.put("usepeoplename", "仪器库房") ;
		String code = "" ;
		dataMap.put("name", sbEquipment.getName()) ;
		kmap.put("name", sbEquipment.getName()) ;
		dataMap.put("bstate", sbEquipment.getBstate()) ;
		kmap.put("bstate", sbEquipment.getBstate()) ;
		Page<Map> page = sbEquipmentBusService.findMapPage(new Page<Map>(request, response), dataMap,kmap); 
		model.addAttribute("page", page);
		return "modules/equipment/equipmentbus/sbEquipmentBorrowList";
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"meteringList"})
	public String meteringList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", "1") ;
		dataMap.put("field15", sbEquipment.getField15()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("usepeoplename", sbEquipment.getUsepeoplename()) ;
		String code = "" ;
		dataMap.put("name", sbEquipment.getName()) ;
		dataMap.put("bstate", sbEquipment.getBstate()) ;
		Page<Map> page = sbEquipmentBusService.findMeteringPage(new Page<Map>(request, response), dataMap);
		model.addAttribute("page", page);
		model.addAttribute("field15", sbEquipment.getField15());
		return "modules/equipment/equipmentbus/sbEquipmentMeteringList";
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"meteringMonthList"})
	public String meteringMonthList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", "1") ;
		dataMap.put("field15", sbEquipment.getField15()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("usepeoplename", sbEquipment.getUsepeoplename()) ;
		String code = "" ;
		dataMap.put("name", sbEquipment.getName()) ;
		dataMap.put("bstate", sbEquipment.getBstate()) ;
		
		dataMap.put("endInDate", getNextDate(2));
		Page<Map> page = sbEquipmentBusService.findMeteringYMPage(new Page<Map>(request, response), dataMap);
		model.addAttribute("page", page);
		model.addAttribute("field15", sbEquipment.getField15());
		return "modules/equipment/equipmentbus/sbEquipmentMeteringMonthList";
	}
	
	public String getNextDate(int time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
		cal.add(Calendar.MONTH, time);
		cal.set(Calendar.DAY_OF_MONTH,
		cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		System.out.println("当月时间上月1号-->"+sdf.format(cal.getTime()));
		return sdf.format(cal.getTime());
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = {"meteringYearList"})
	public String meteringYearList(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		DataMap dataMap = new DataMap() ;
		dataMap.put("fsType", sbEquipment.getFsType()) ;
		dataMap.put("state", sbEquipment.getState()) ;
		dataMap.put("field5", "1") ;
		dataMap.put("field15", sbEquipment.getField15()) ;
		dataMap.put("teamname", sbEquipment.getTeamname()) ;
		dataMap.put("usepeoplename", sbEquipment.getUsepeoplename()) ;
		String code = "" ;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String currentDate = df.format(new Date());//系统当前时间
		String year = currentDate.split("-")[0];// 年
		int yearInt = Integer.parseInt(year);
		yearInt = yearInt+1 ;
		
		dataMap.put("beginInDate",yearInt+"-01-01");
		dataMap.put("endInDate", (yearInt+1)+"01-01");
		dataMap.put("name", sbEquipment.getName()) ;
		dataMap.put("bstate", sbEquipment.getBstate()) ;
		Page<Map> page = sbEquipmentBusService.findMeteringYMPage(new Page<Map>(request, response), dataMap);
		model.addAttribute("page", page);
		model.addAttribute("field15", sbEquipment.getField15());
		return "modules/equipment/equipmentbus/sbEquipmentMeteringYearList";
	}

	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = "form")
	public String form(SbEquipmentBus sbEquipmentBus, Model model) {
		flag=0;
		//查询实体资源信息
				SbEquipment sbEquipment = new SbEquipment();
				
				
				TsResourceBus tsResourceBus = new TsResourceBus();
				tsResourceBus.setBusType(sbEquipmentBus.getBusType());
				
				
				if(sbEquipmentBus.getId()!=null && !"".equals(sbEquipmentBus.getId())){
					sbEquipment.setId(sbEquipmentBus.getId());
					sbEquipment = sbEquipmentService.get(sbEquipmentBus.getId()) ;
					tsResourceBus.setBusType(sbEquipment.getFsType());
				}
				
				

				sbEquipment.setFsType(sbEquipmentBus.getBusType());
				String tableComments = sbEquipmentBus.getTableComments() ;
				model.addAttribute("fsType", sbEquipmentBus.getBusType());
				sbEquipment.setId(sbEquipmentBus.getId());
				if(sbEquipmentBus.getId()!= null && !"".equals(sbEquipmentBus.getId())  ){
					sbEquipment = sbEquipmentService.get(sbEquipmentBus.getId()) ;
				
				}
				//查询页面信息
				tsResourceBus.setIsEdit("1");
				if(null == tsResourceBus.getBusType() || "".equals(tsResourceBus.getBusType())){
			
					tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
				}
				List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
				String codeString = "<fieldset><table class='table-form'><tr>" ;
				Map map = new HashMap() ;
				Object value = "" ;
				String valueName="";
				try {
					 map = objectToMap(sbEquipment);
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
					if(tsResourceBus.getTableName().equals("team")){
						valueName = (String) map.get(tsResourceBus.getTableName()+"name") ;
					}
					codeString = getCodeHtml(codeString, value, valueName,tsResourceBus);
				}
				SbEquipmentType type = sbEquipmentTypeService.get(sbEquipment.getFsType());
				codeString+="</tr></table></fieldset>";
				model.addAttribute("codeString", codeString);
				if(sbEquipment.getId()==null || "".equals(sbEquipment.getId())){
					sbEquipment.setField2("1");
				}
				model.addAttribute("sbEquipment", sbEquipment);
				return "modules/equipment/equipmentbus/sbEquipmentBusForm";
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	@RequestMapping(value = "formClone")
	public String formClone(SbEquipmentBus sbEquipmentBus, Model model) {
		flag=0;
		//查询实体资源信息
				SbEquipment sbEquipment = new SbEquipment();
				
				
				TsResourceBus tsResourceBus = new TsResourceBus();
				tsResourceBus.setBusType(sbEquipmentBus.getBusType());
				
				
				if(sbEquipmentBus.getId()!=null && !"".equals(sbEquipmentBus.getId())){
					sbEquipment.setId(sbEquipmentBus.getId());
					sbEquipment = sbEquipmentService.get(sbEquipmentBus.getId()) ;
					tsResourceBus.setBusType(sbEquipment.getFsType());
				}
				
				

				sbEquipment.setFsType(sbEquipmentBus.getBusType());
				String tableComments = sbEquipmentBus.getTableComments() ;
				model.addAttribute("fsType", sbEquipmentBus.getBusType());
				sbEquipment.setId(sbEquipmentBus.getId());
				if(sbEquipmentBus.getId()!= null && !"".equals(sbEquipmentBus.getId())  ){
					sbEquipment = sbEquipmentService.get(sbEquipmentBus.getId()) ;
				
				}
				//查询页面信息
				tsResourceBus.setIsEdit("1");
				if(null == tsResourceBus.getBusType() || "".equals(tsResourceBus.getBusType())){
			
					tsResourceBus.setBusType("0161de2d17334cbf93be8073beb5fe5f");
				}
				List<TsResourceBus> list = tsResourceBusService.getBus(tsResourceBus) ;
				String codeString = "<fieldset><table class='table-form'><tr>" ;
				Map map = new HashMap() ;
				Object value = "" ;
				String valueName="";
				try {
					 map = objectToMap(sbEquipment);
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
					if(tsResourceBus.getTableName().equals("team")){
						valueName = (String) map.get(tsResourceBus.getTableName()+"name") ;
					}
					codeString = getCodeHtml(codeString, value, valueName,tsResourceBus);
				}
				SbEquipmentType type = sbEquipmentTypeService.get(sbEquipment.getFsType());
				codeString+="</tr></table></fieldset>";
				model.addAttribute("codeString", codeString);
				if(sbEquipment.getId()==null || "".equals(sbEquipment.getId())){
					sbEquipment.setField2("1");
				}
				sbEquipment.setId(null);
				sbEquipment.setIsNewRecord(true);
				model.addAttribute("sbEquipment", sbEquipment);
				return "modules/equipment/equipmentbus/sbEquipmentBusForm";
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
	int flag =0;
	public String getCodeHtml(String codeString,Object  value,String valueName ,TsResourceBus tsResourceBus){
	     flag++;
		if(valueName==null){
			valueName="";
		}
		 switch (tsResourceBus.getShowType()) {
		 case "input":
			 codeString += "<td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
		                " <td>"+
		                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
		                "htmlEscape='false' type='text' maxlength='50' value='"+value+"' />"; 
			break;
		case "dateselect":
			codeString += "<td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
	                " <td>"+
			" <input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' type='text' maxlength='50' "
					+ "class='input-medium Wdate'"+
			" 	value='"+value+"' onclick= 'WdatePicker({isShowClear:false});'/>  " ; 
			break;
			
		case "select":
			codeString += "<td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
	                " <td>"+
			   "<select id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' class='input-xlarge '>  "+
			getSelect(tsResourceBus.getDictType(),value) + "</select>"; 
			break;

		case "treeselect":
			codeString += " <td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
		                " <td>"+
			"<input id='"+tsResourceBus.getTableName()+"_Id' name='"+tsResourceBus.getTableName()+"' class='required' type='hidden' value='"+value+"'/>"+
			"<input id='"+tsResourceBus.getTableName()+"_Name' name='"+tsResourceBus.getTableName()+"Name' readonly='readonly' value='"+valueName+"' type='text' data-msg-required=''"+
			"class='required' style=''/><a id='"+tsResourceBus.getTableName()+"_Button' href='javascript:' class='btn  ' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>&nbsp;&nbsp;"; 
			break;
			
		case "officeselect":
			codeString += " <td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
		                " <td>"+
			"<input id='"+tsResourceBus.getTableName()+"_Id' name='"+tsResourceBus.getTableName()+"' class='required' type='hidden' value='"+value+"'/>"+
			"<input id='"+tsResourceBus.getTableName()+"_Name' name='"+tsResourceBus.getTableName()+"name' readonly='readonly' value='"+valueName+"' type='text' data-msg-required=''"+
			"class='required' style=''/><a id='"+tsResourceBus.getTableName()+"_Button' href='javascript:' class='btn  ' style=''>&nbsp;<i class='icon-search'></i>&nbsp;</a>&nbsp;&nbsp;"; 
			break;
			
		case "hidden":
			 codeString += "<form:hidden path='"+tsResourceBus.getTableName()+"'/>";
			 flag--;
			break;
			
		default:
			codeString += "<td class='tit'>"+tsResourceBus.getColumnComments()+"：</td>"+
		                " <td>"+
	                "<input id='"+tsResourceBus.getTableName()+"' name='"+tsResourceBus.getTableName()+"' " +
	                "htmlEscape='false' type='text' maxlength='50' value='"+value+"' class='input-xlarge '/>"
	               ;
			break;
		}
		 if("1".equals(tsResourceBus.getIsNull())){
			 codeString+="<span class='help-inline'><font color='red'>*</font> </span>" ;
		 }
		 codeString+="</td>";
		 if(flag%2==0){
			 codeString+="</tr><tr>";
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

	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:edit")
	@RequestMapping(value = "save")
	public String save(SbEquipment sbEquipment, Model model, RedirectAttributes redirectAttributes) {
		SbEquipmentBus sbEquipmentBus = new SbEquipmentBus();
		sbEquipmentBus.setBusType(sbEquipment.getFsType());
		if (!beanValidator(model, sbEquipment)){
			return form(sbEquipmentBus, model);
		}
			
		if((sbEquipment.getSbcode()==null||"".equals(sbEquipment.getSbcode()))&&(sbEquipment.getSbType()!=null&&!"".equals(sbEquipment.getSbType()))){
			String type = sbFunctionTypeService.get(sbEquipment.getSbType()).getCode();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String currentDate = df.format(new Date());//系统当前时间
			String year = currentDate.split("-")[0];// 年
			String code = sbEquipmentBusService.getCode("sb_equipment","sbcode",type+year);
			sbEquipment.setSbcode(getCode(type,code));
		}
		if(sbEquipment.getBstate()==null || "".equals(sbEquipment.getBstate())){
			sbEquipment.setBstate("1");
		}
		sbEquipmentService.save(sbEquipment);
		addMessage(redirectAttributes, "保存仪器设备配置成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmentbus/sbEquipmentBus?sbcode="+sbEquipment.getSbcode();
	}
	
	@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:edit")
	@RequestMapping(value = "delete")
	public String delete(SbEquipment sbEquipment, RedirectAttributes redirectAttributes) {
		sbEquipmentService.delete(sbEquipment);
		addMessage(redirectAttributes, "删除仪器设备配置成功");
		return "redirect:"+Global.getAdminPath()+"/equipment/equipmentbus/sbEquipmentBus?fsType="+sbEquipment.getFsType();
	}
	
	 @RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:view")
	    @RequestMapping(value = "import/template")
	    public String importFileTemplate(TsResourceBus tsResourceBus,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		    tsResourceBus.setIsEdit("1");
			List<TsResourceBus> listBus = tsResourceBusService.getBus(tsResourceBus) ;
			List<String> listString = new ArrayList<String>() ;
			
			for (TsResourceBus property : listBus) {    
				listString.add(property.getColumnComments());      
			}
			
			
			try {
	            String fileName = "仪器设备导入模板.xlsx";
	            System.out.println(fileName);
	    		List<TsResource> list = Lists.newArrayList();
	    		list.add(new TsResource());
	    		new ExportExcel("仪器设备数据", listString).setDataList(list).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/equipment/equipmentbus/sbEquipmentBus?fsType="+tsResourceBus.getBusType();
	    }
	 
	 /**
		 * 导入采购资源
		 * @param file
		 * @param redirectAttributes
		 * @return
		 */
		@RequiresPermissions("equipment:equipmentbus:sbEquipmentBus:edit")
	    @RequestMapping(value = "import", method=RequestMethod.POST)
	    public String importFile(TsResourceBus tsResourceBus,MultipartFile file, RedirectAttributes redirectAttributes) {
			Map map = new HashMap<>();
			try {
				List<TsResourceBus> listBus = tsResourceBusService.getBus(tsResourceBus) ;
				for (TsResourceBus property : listBus) {  
					map.put(property.getColumnName(), "1");
					  
				}

				int successNum = 0;
				int failureNum = 0;
				StringBuilder failureMsg = new StringBuilder();
				ImportExcel ei = new ImportExcel(file, 1, 0);
				List<SbEquipment> list = ei.getSbDataList(map,SbEquipment.class);
				
				
				
				for (SbEquipment sbEquipment : list){
				
					try{
						if (sbEquipment != null){
							sbEquipment.setFsType(tsResourceBus.getBusType());
							sbEquipmentService.save(sbEquipment);
							successNum++;
						}else{
							failureMsg.append("<br/>资源名 "+sbEquipment.getName()+" 已存在; ");
							failureNum++;
						}
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>资源名 "+sbEquipment.getName()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>资源名 "+sbEquipment.getName()+" 导入失败："+ex.getMessage());
					}
				}
				if (failureNum>0){
					failureMsg.insert(0, "，失败 "+failureNum+" 条资源，导入信息如下：");
				}
				addMessage(redirectAttributes, "已成功导入 "+successNum+" 条待"+failureMsg);
			} catch (Exception e) {
				addMessage(redirectAttributes, "导入资失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/equipment/equipmentbus/sbEquipmentBus?fsType="+tsResourceBus.getBusType();
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
	    public String exportFile(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			DataMap dataMap = new DataMap() ;
			
			dataMap.put("fsType", sbEquipment.getFsType()) ;
			dataMap.put("state", sbEquipment.getState()) ;
			dataMap.put("field5", sbEquipment.getField5()) ;
			dataMap.put("teamname", sbEquipment.getTeamname()) ;
			try {
	            String fileName = "仪器设备信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            Page<SbEquipment> page = sbEquipmentService.findPage(new Page<SbEquipment>(), sbEquipment) ;
	    		new ExportExcel("仪器设备信息", SbEquipment.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出设备失败！失败信息："+e.getMessage());
			}
			return "redirect:" + adminPath + "/equipment/equipmentbus/sbEquipmentBus/list?repage";
	    }
	    
	    @RequestMapping(value = "exportExcel", method=RequestMethod.POST)
	    public String exportExcel(SbEquipment sbEquipment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
			List<String> list = new ArrayList<String>();
			List<List> list2 = new ArrayList<List>();
			List<SbEquipment> sbEquipmentList= sbEquipmentService.exportList(UserUtils.getUser().getId());
			for (SbEquipment sbEquipment2 : sbEquipmentList) {
				list.add(sbEquipment2.getName());
				list.add(sbEquipment2.getSbcode());
				list.add(sbEquipment2.getTeamname());
				list.add(sbEquipment2.getUsepeoplename());
				list.add(sbEquipment2.getStarttime());
				list2.add(list);
				list = new ArrayList<String>();
				
			}
			
			list.add("设备名称");
			list.add("设备编号");
			list.add("责任部门");
			list.add("责任人");
			list.add("登记日期");
			try {
	            String fileName = "仪器设备信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	    		new ExportExcel("仪器设备信息", list).setDataListString(list2).write(response, fileName).dispose();
	    		Map<String, String> map = new HashMap<String,String>();
				map.put("userid", UserUtils.getUser().getId());
				map.put("type", "4");
				sbBorrowService.deleteByuserId(map);
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出设备失败！失败信息："+e.getMessage());
			}

			
			
			return "redirect:" + adminPath + "/equipment/equipmentbus/sbEquipmentBus/list?repage";
	    }
		
		@RequestMapping(value = "saveEquipment")
		public String saveEquipment(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
			//tsResourceBusService.saveBusTableData(genTable);
	         System.out.println(genTable.getName());
	         System.out.println(genTable.getComments());
	         System.out.println(genTable.getClassName());
	         SbEquipment sbEquipment = new SbEquipment();
			for(int i=0 ; i<genTable.getColumnList().size();i++){
				sbEquipment.setId(genTable.getColumnList().get(i).getId());
				if("1".equals(genTable.getColumnList().get(i).getIsList())){
					sbEquipment.setSbType(genTable.getComments());
					sbEquipment.setSbTypeName(genTable.getClassName());
					sbEquipmentBusService.updateEquipment(sbEquipment);
				}
				sbEquipment = new SbEquipment();
				
			}
			addMessage(redirectAttributes, "修改成功");
			return "redirect:"+Global.getAdminPath()+"/equipment/equipmentbus/sbEquipmentBus/list?fsType="+genTable.getName();
		}
		
		@RequestMapping(value = "geChartData")
	    @ResponseBody
	    public void geChartData(String busTableType,HttpServletResponse response){
		    
	        List dataList= sbEquipmentBusService.queryEquipmentInfo(new HashMap<>());
	        List stateList= sbEquipmentBusService.queryStateInfo(new HashMap<>());
	        List OfficeList= sbEquipmentBusService.queryOfficeInfo(new HashMap<>());
	        List fsTypeList= sbEquipmentBusService.queryFsTypeInfo(new HashMap<>());
	        List sbTypeList= sbEquipmentBusService.querySbTypeInfo(new HashMap<>());
	        List rateList= sbEquipmentBusService.queryRateInfo(new HashMap<>());

	        List list = new ArrayList();
	        list.add(dataList);
	        list.add(stateList);
	        list.add(OfficeList);
	        list.add(fsTypeList);
	        list.add(sbTypeList);
	        list.add(rateList);

	        renderString(response, JsonMapper.toJsonString(list),"text/html");

	    }
		
		@RequestMapping(value = "getIndexChart")
	    @ResponseBody
	    public void getIndexChart(String busTableType,HttpServletResponse response){
		    
	        List dataList= sbEquipmentBusService.queryIndexInfo(new HashMap<>());
	        

	        renderString(response, JsonMapper.toJsonString(dataList),"text/html");

	    }
		
		public String getCode(String type,String code){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String currentDate = df.format(new Date());//系统当前时间
			

			String year = currentDate.split("-")[0];// 年
			
			String codex =type+year+"-";
			if(code!=null){
				String[] maxCodes = code.split("-");
				String maxCode = maxCodes[maxCodes.length - 1];
				int maxc = Integer.parseInt(maxCode);
				int nowc = maxc + 1;
				int len = String.valueOf(nowc).length();
				int siz = 4 - len;
				if (siz == 0)
					codex = codex + String.valueOf(nowc);
				if (siz == 1)
					codex = codex + "0" + String.valueOf(nowc);
				if (siz == 2)
					codex = codex + "00" + String.valueOf(nowc);
				if (siz == 3)
					codex = codex + "000" + String.valueOf(nowc);
			
				
			}else{
				
				codex = codex+"0001";
			}
			
			return codex ;	
		}
		
		@RequestMapping(value = "updateCode")
	    @ResponseBody
		public void updateState(String model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
			List<SbEquipment> list = sbEquipmentService.findList(new SbEquipment());
			System.out.println(list.size());
			for (SbEquipment sbEquipment : list) {
				if((sbEquipment.getSbcode()==null||"".equals(sbEquipment.getSbcode()))&&(sbEquipment.getSbType()!=null&&!"".equals(sbEquipment.getSbType()))){
					String type = sbFunctionTypeService.get(sbEquipment.getSbType()).getCode();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
					String currentDate = df.format(new Date());//系统当前时间
					String year = currentDate.split("-")[0];// 年
					String code = sbEquipmentBusService.getCode("sb_equipment","sbcode",type+year);
					sbEquipment.setSbcode(getCode(type,code));
					sbEquipmentService.save(sbEquipment);
				}
			}
			renderString(response, JsonMapper.toJsonString(""),"text/html");
		}
		
		@RequestMapping(value = "updateMetering")
		public String updateMetering(String[] ids,String field5s[], String[] field12s, String[] field11s, String[] field13s, String[] field14s,String[] field6s, RedirectAttributes redirectAttributes) {
			
	    	for (int i = 0; i < ids.length; i++) {
	    		SbEquipment sbEquipment = sbEquipmentService.get(ids[i]);
	    		sbEquipment.setField12(field12s[i]);
	    		sbEquipment.setField11(field11s[i]);
	    		sbEquipment.setField13(field13s[i]);
	    		sbEquipment.setField14(field14s[i]);
	    		sbEquipment.setField6(field6s[i]);
	    		sbEquipment.setField5(field5s[i]);
	    		if(sbEquipment.getId()!=null){
	    			sbEquipmentService.save(sbEquipment);
	    		}
	    	}
	    	addMessage(redirectAttributes, "保存计量信息成功!");
			return "redirect:" + adminPath + "/equipment/equipmentbus/sbEquipmentBus/meteringList";
		}
		


}