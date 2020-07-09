/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collection.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.collection.entity.TsCollection;
import com.thinkgem.jeesite.modules.collection.service.TsCollectionService;
import com.thinkgem.jeesite.modules.resourcebook.entity.TsResourceBook;
import com.thinkgem.jeesite.modules.resourcebook.service.TsResourceBookService;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;

/**
 * 馆藏信息Controller
 * @author suntao
 * @version 2018-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/collection/tsCollection")
public class TsCollectionController extends BaseController {

	@Autowired
	private TsCollectionService tsCollectionService ;
	
	@Autowired
	private TsResourceBookService tsResourceBookService ;
	
	@Autowired
	private TsResourceTypeService tsResourceTypeService ;
	
	@ModelAttribute
	public TsCollection get(@RequestParam(required=false) String id) {
		TsCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsCollectionService.get(id);
		}
		if (entity == null){
			entity = new TsCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("collection:tsCollection:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsCollection tsCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TsCollection> list = tsCollectionService.findList(tsCollection); 
		model.addAttribute("list", list);
		return "modules/collection/tsCollectionList";
	}
	
	
	@RequestMapping(value = "shelves")
    @ResponseBody
    public void shelves(String book_id,String local,String check ,HttpServletResponse response){
		System.out.println(book_id);
		System.out.println(local);
		System.out.println(check);
	    TsResourceBook tsResourceBook = tsResourceBookService.get(book_id);
	    if("checked".equals(check)){
	    	tsResourceBook.setLocal(local);
	    	tsResourceBook.setBorrowState("2");
	    	tsResourceBookService.save(tsResourceBook);
	    }else{
	    	tsResourceBook.setLocal(null);
	    	tsResourceBook.setBorrowState("1");
	    	tsResourceBookService.save(tsResourceBook);
	    }
        renderString(response, JsonMapper.toJsonString(null),"text/html");
       /* try {
            ResponseUtil.writeJson("getSelectColumnData",dataList,response);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
	
	
	
	@RequiresPermissions("collection:tsCollection:view")
	@RequestMapping(value = {"index"})
	public String index(TsCollection tsCollection, Model model) {
		return "modules/collection/tsCollectionIndex";
	}

	@RequiresPermissions("collection:tsCollection:view")
	@RequestMapping(value = "form")
	public String form(TsCollection tsCollection, Model model) {
		if (tsCollection.getParent()!=null && StringUtils.isNotBlank(tsCollection.getParent().getId())){
			tsCollection.setParent(tsCollectionService.get(tsCollection.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(tsCollection.getId())){
				TsCollection tsCollectionChild = new TsCollection();
				tsCollectionChild.setParent(new TsCollection(tsCollection.getParent().getId()));
				List<TsCollection> list = tsCollectionService.findList(tsCollection); 
				if (list.size() > 0){
					tsCollection.setSort(list.get(list.size()-1).getSort());
					if (tsCollection.getSort() != null){
						tsCollection.setSort(tsCollection.getSort() + 30);
					}
				}
			}
		}
		if (tsCollection.getSort() == null){
			tsCollection.setSort(30);
		}
		
		TsResourceType tsResourceType = tsResourceTypeService.get(tsCollection.getBusType());
		model.addAttribute("tsResourceType", tsResourceType);
		model.addAttribute("tsCollection", tsCollection);
		return "modules/collection/tsCollectionForm";
	}

	@RequiresPermissions("collection:tsCollection:edit")
	@RequestMapping(value = "save")
	public String save(TsCollection tsCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsCollection)){
			return form(tsCollection, model);
		}
		tsCollectionService.save(tsCollection);
		addMessage(redirectAttributes, "保存馆藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/collection/tsCollection/?repage";
	}
	
	@RequiresPermissions("collection:tsCollection:edit")
	@RequestMapping(value = "delete")
	public String delete(TsCollection tsCollection, RedirectAttributes redirectAttributes) {
		tsCollectionService.delete(tsCollection);
		addMessage(redirectAttributes, "删除馆藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/collection/tsCollection/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TsCollection> list = tsCollectionService.findList(new TsCollection());
		for (int i=0; i<list.size(); i++){
			TsCollection e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				map.put("busType", e.getBusType());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}