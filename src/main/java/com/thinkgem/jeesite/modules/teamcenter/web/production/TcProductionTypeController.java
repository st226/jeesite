/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.web.production;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.quality.entity.qmsupplier.QmSupplier;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.teamcenter.entity.production.TcProductionType;
import com.thinkgem.jeesite.modules.teamcenter.service.production.TcProductionTypeService;

/**
 * 生产bom树Controller
 * @author suntao
 * @version 2020-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/teamcenter/production/tcProductionType")
public class TcProductionTypeController extends BaseController {

	@Autowired
	private TcProductionTypeService tcProductionTypeService;
	
	@ModelAttribute
	public TcProductionType get(@RequestParam(required=false) String id) {
		TcProductionType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcProductionTypeService.get(id);
		}
		if (entity == null){
			entity = new TcProductionType();
		}
		return entity;
	}
	
	@RequiresPermissions("teamcenter:production:tcProductionType:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcProductionType tcProductionType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TcProductionType> list = tcProductionTypeService.findList(tcProductionType); 
		model.addAttribute("list", list);
		return "modules/teamcenter/production/tcProductionTypeList";
	}

	@RequiresPermissions("teamcenter:production:tcProductionType:view")
	@RequestMapping(value = "form")
	public String form(TcProductionType tcProductionType, Model model) {
		if (tcProductionType.getParent()!=null && StringUtils.isNotBlank(tcProductionType.getParent().getId())){
			tcProductionType.setParent(tcProductionTypeService.get(tcProductionType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tcProductionType.getId())){
				TcProductionType tcProductionTypeChild = new TcProductionType();
				tcProductionTypeChild.setParent(new TcProductionType(tcProductionType.getParent().getId()));
				List<TcProductionType> list = tcProductionTypeService.findList(tcProductionType); 
				if (list.size() > 0){
					tcProductionType.setSort(list.get(list.size()-1).getSort());
					if (tcProductionType.getSort() != null){
						tcProductionType.setSort(tcProductionType.getSort() + 30);
					}
				}
			}
		}
		if (tcProductionType.getSort() == null){
			tcProductionType.setSort(30);
		}
		model.addAttribute("tcProductionType", tcProductionType);
		return "modules/teamcenter/production/tcProductionTypeForm";
	}

	@RequiresPermissions("teamcenter:production:tcProductionType:edit")
	@RequestMapping(value = "save")
	public String save(TcProductionType tcProductionType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcProductionType)){
			return form(tcProductionType, model);
		}
		tcProductionTypeService.save(tcProductionType);
		addMessage(redirectAttributes, "保存生产bom树成功");
		return "redirect:"+Global.getAdminPath()+"/teamcenter/production/tcProductionType/?repage";
	}
	
	@RequiresPermissions("teamcenter:production:tcProductionType:edit")
	@RequestMapping(value = "delete")
	public String delete(TcProductionType tcProductionType, RedirectAttributes redirectAttributes) {
		tcProductionTypeService.delete(tcProductionType);
		addMessage(redirectAttributes, "删除生产bom树成功");
		return "redirect:"+Global.getAdminPath()+"/teamcenter/production/tcProductionType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TcProductionType> list = tcProductionTypeService.findList(new TcProductionType());
		for (int i=0; i<list.size(); i++){
			TcProductionType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName()+"("+e.getDrawingNo()+")");
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "生产BOM导入模板.xlsx";
            List<TsResource> list = Lists.newArrayList();
    		new ExportExcel("生产BOM数据", TcProductionType.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/teamcenter/production/tcProductionType/list?repage";
    }
	
	@RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<TcProductionType> list = ei.getDataList(TcProductionType.class);
			TcProductionType temp = new TcProductionType();
			String line0 = "2374a1633f0f472094f7efebea3c099b" ;
			String line1 = "" ;
			String line2 = "" ;
			String line3 = "" ;
			String line4 = "" ;
			String line5 = "" ;
			String line6 = "" ;
			String flag = "" ;
			String name = "" ;
			String namey = "" ;
			String code = "" ;
			String drawingNo="";
			String stage = "" ;	
		    String edition = "" ;
		    String str[] = null ;
			for (TcProductionType tcProductionType : list){
				namey = tcProductionType.getName() ;
				name = tcProductionType.getName() ;
				temp.setCode(tcProductionType.getDrawingNo());
				str = tcProductionType.getStage().split("/");
				temp.setName(str[0]);
				temp.setDelFlag("0");
				temp.setSort(successNum);
				if(str.length>1){
					temp.setStage(str[1]);
				}
				if(str.length>3){
					temp.setDrawingNo(str[3]);
				}
				
				if("1".equals(name)){
					
					TcProductionType tcP = tcProductionTypeService.get(line0);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					tcProductionTypeService.save(temp);
					line1 = temp.getId() ;
					temp =  new TcProductionType();
				}
                if("2".equals(name)){
                	TcProductionType tcP = tcProductionTypeService.get(line1);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					str = tcProductionType.getStage().split("/");
					temp.setName(str[0]);
					if(str.length>2){
						temp.setStage(str[2]);
					}
					if(str.length>3){
						temp.setDrawingNo(str[3]);
					}
					
					temp.setDelFlag("0");
					temp.setSort(successNum);
					tcProductionTypeService.save(temp);
					line2 = temp.getId() ;
					temp =  new TcProductionType();
				}
                if("3".equals(name)){
                	TcProductionType tcP = tcProductionTypeService.get(line2);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					str = tcProductionType.getStage().split("/");
					temp.setName(str[0]);
					if(str.length>2){
						temp.setStage(str[2]);
					}
					if(str.length>3){
						temp.setDrawingNo(str[3]);
					}
					
					temp.setDelFlag("0");
					temp.setSort(successNum);
					tcProductionTypeService.save(temp);
					line3 = temp.getId() ;
					temp =  new TcProductionType();
	
                }
                if("4".equals(name)){
                	TcProductionType tcP = tcProductionTypeService.get(line3);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					str = tcProductionType.getStage().split("/");
					temp.setName(str[0]);
					if(str.length>2){
						temp.setStage(str[2]);
					}
					if(str.length>3){
						temp.setDrawingNo(str[3]);
					}
					
					temp.setDelFlag("0");
					temp.setSort(successNum);
					tcProductionTypeService.save(temp);
					line4 = temp.getId() ;
					temp =  new TcProductionType();
	
                }
               if("5".equals(name)){
            	   TcProductionType tcP = tcProductionTypeService.get(line4);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					str = tcProductionType.getStage().split("/");
					temp.setName(str[0]);
					if(str.length>2){
						temp.setStage(str[2]);
					}
					if(str.length>3){
						temp.setDrawingNo(str[3]);
					}
					
					temp.setDelFlag("0");
					temp.setSort(successNum);
					tcProductionTypeService.save(temp);
					line5 = temp.getId() ;
					temp =  new TcProductionType();
	
                }
               if("6".equals(name)){
            	   TcProductionType tcP = tcProductionTypeService.get(line5);
					temp.setParent(tcP);
					temp.setParentIds(tcP.getParentIds()+tcP.getId()+",");
					str = tcProductionType.getStage().split("/");
					temp.setName(str[0]);
					if(str.length>2){
						temp.setStage(str[2]);
					}
					if(str.length>3){
						temp.setDrawingNo(str[3]);
					}
					
					temp.setDelFlag("0");
					temp.setSort(successNum);
					tcProductionTypeService.save(temp);
					line6 = temp.getId() ;
					temp =  new TcProductionType();
            		
               }
				
				
				code = tcProductionType.getCode() ;
				drawingNo = tcProductionType.getDrawingNo() ;
				stage = tcProductionType.getStage();
				edition = tcProductionType.getEdition() ;
				System.out.println("TC"+name+code+drawingNo+stage+edition+">>");
				//tcProductionTypeService.save(tcProductionType);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条BOM数据，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条供应商"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入BOM数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/teamcenter/production/tcProductionType/list?repage";
    }
	
}