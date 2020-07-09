/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resourcetype.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.borrow.entity.TsBorrow;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPayYs;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.service.GenTableService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcebus.service.TsResourceBusService;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;

/**
 * 著录项管理Controller
 * @author suntao
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/resourcetype/tsResourceType")
public class TsResourceTypeController extends BaseController {

	@Autowired
	private TsResourceTypeService tsResourceTypeService;
	
	@Autowired
	private TsResourceTypeService tsResourceBusService;
	
	@Autowired
	private GenTableService genTableService;
	
	@Autowired
	private DictService dictService;
	 
	@ModelAttribute
	public TsResourceType get(@RequestParam(required=false) String id) {
		TsResourceType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsResourceTypeService.get(id);
		}
		if (entity == null){
			entity = new TsResourceType();
		}
		return entity;
	}
	
	@RequiresPermissions("resourcetype:tsResourceType:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TsResourceType> list = tsResourceTypeService.findList(tsResourceType); 
		model.addAttribute("list", list);
		return "modules/resourcetype/tsResourceTypeList";
	}
	
	@RequiresPermissions("resourcetype:tsResourceType:view")
	@RequestMapping(value = {"query"})
	public String query(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TsResourceType> list = tsResourceTypeService.queryList(tsResourceType); 
		model.addAttribute("list", list);
		return "modules/resourcetype/queryList";
	}
	
	
	@RequestMapping(value = "queryExport", method=RequestMethod.POST)
    public String queryExport(TsResourceType tsResourceType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
            String fileName = "上传信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<TsResourceType> list = tsResourceTypeService.queryList(tsResourceType); 
    		
    		new ExportExcel("上传信息", TsResourceType.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出统计失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/resourcetype/tsResourceType/query?repage";
    }

	@RequiresPermissions("resourcetype:tsResourceType:view")
	@RequestMapping(value = "form")
	public String form(TsResourceType tsResourceType, Model model) {
		if (tsResourceType.getParent()!=null && StringUtils.isNotBlank(tsResourceType.getParent().getId())){
			tsResourceType.setParent(tsResourceTypeService.get(tsResourceType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tsResourceType.getId())){
				TsResourceType tsResourceTypeChild = new TsResourceType();
				tsResourceTypeChild.setParent(new TsResourceType(tsResourceType.getParent().getId()));
				List<TsResourceType> list = tsResourceTypeService.findList(tsResourceType); 
				if (list.size() > 0){
					tsResourceType.setSort(list.get(list.size()-1).getSort());
					if (tsResourceType.getSort() != null){
						tsResourceType.setSort(tsResourceType.getSort() + 30);
					}
				}
			}
		}
		if (tsResourceType.getSort() == null){
			tsResourceType.setSort(30);
		}
		model.addAttribute("tsResourceType", tsResourceType);
		return "modules/resourcetype/tsResourceTypeForm";
	}
	
	
	@RequestMapping(value = "deploy")
	public String deploy(TsResourceBus tsResourceBus,  HttpServletRequest request, HttpServletResponse response, Model model) {
		GenTable genTable = new GenTable() ; 
		
		genTable.setName("ts_resource");
		genTable = genTableService.getByName("ts_resource");
		model.addAttribute("genTable", genTable);
		model.addAttribute("busTableType",tsResourceBus.getId());
        model.addAttribute("config", GenUtils.getConfig());
		return "modules/resourcebus/tsResourceDeploy";
	}
	
	
	@RequestMapping(value = "busTableSave")
    //@ResponseBody
    public String busTableSave(GenTable genTable, RedirectAttributes redirectAttributes) {
		tsResourceBusService.saveBusTableData(genTable);
		addMessage(redirectAttributes, "型号配置成功");
		return "redirect:"+Global.getAdminPath()+"/resourcetype/tsResourceType/?repage";
    }
	
	


	@RequiresPermissions("resourcetype:tsResourceType:edit")
	@RequestMapping(value = "save")
	public String save(TsResourceType tsResourceType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsResourceType)){
			return form(tsResourceType, model);
		}
		String Model = 	tsResourceType.getModel() ;
		
		tsResourceType.setModel(null);
		String id = tsResourceType.getId() ;
		
		
		
		
		//不存模型号
		tsResourceTypeService.save(tsResourceType);
       if(id==null || "".equals((id))){
    	   tsResourceTypeService.saveGemCoumn(tsResourceType.getId());
			
		}
		
		if(Model!=null)
		saveType(Model,tsResourceType.getId());
		addMessage(redirectAttributes, "保存型号成功");
		return "redirect:"+Global.getAdminPath()+"/resourcetype/tsResourceType/?repage";
	}
	
	
	public void saveType(String ParentId,String xParentId){
		TsResourceType tsResourceTypep = new TsResourceType();
		TsResourceType tsResourceType = new TsResourceType();
		TsResourceType xtsResourceTypep = new TsResourceType();
		tsResourceTypep.setId(ParentId);
		xtsResourceTypep.setId(xParentId);
		tsResourceType.setParent(tsResourceTypep);
		List<TsResourceType> list = tsResourceTypeService.getTsResourceType(tsResourceType) ;
		if(list.size()>0 ){
			for (TsResourceType temp : list) {
				TsResourceType xtsResourceType = new TsResourceType();
				xtsResourceType.setName(temp.getName());
				xtsResourceType.setParent(xtsResourceTypep);
				xtsResourceType.setSort(temp.getSort());
				tsResourceTypeService.save(xtsResourceType);
				//配置属性
				tsResourceTypeService.saveGemCoumn(xtsResourceType.getId());
				saveType(temp.getId(),xtsResourceType.getId());
			}
		}	
	}
	
	@RequiresPermissions("resourcetype:tsResourceType:edit")
	@RequestMapping(value = "delete")
	public String delete(TsResourceType tsResourceType, RedirectAttributes redirectAttributes) {
		tsResourceTypeService.delete(tsResourceType);
		
		addMessage(redirectAttributes, "删除型号管理成功");
		return "redirect:"+Global.getAdminPath()+"/resourcetype/tsResourceType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,String bj , HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		TsResourceType tsResourceType = new TsResourceType();
		tsResourceType.setBj(bj);
		List<TsResourceType> list = tsResourceTypeService.findList(tsResourceType);
		for (int i=0; i<list.size(); i++){
			TsResourceType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	

	
}