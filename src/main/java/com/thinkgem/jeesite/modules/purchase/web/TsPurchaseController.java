/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purchase.web;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.order.entity.TsOrder;
import com.thinkgem.jeesite.modules.order.service.TsOrderService;
import com.thinkgem.jeesite.modules.purchase.entity.TsPurchase;
import com.thinkgem.jeesite.modules.purchase.service.TsPurchaseService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 征订信息维护Controller
 * @author suntao
 * @version 2018-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/tsPurchase")
public class TsPurchaseController extends BaseController {

	@Autowired
	private TsPurchaseService tsPurchaseService;
	
	@Autowired
	private TsOrderService tsOrderService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private DictService dictService;
	
	@ModelAttribute
	public TsPurchase get(@RequestParam(required=false) String id) {
		TsPurchase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsPurchaseService.get(id);
		}
		if (entity == null){
			entity = new TsPurchase();
		}
		return entity;
	}
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("purchase:tsPurchase:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "采购信息导入模板.xlsx";
    		List<TsPurchase> list = Lists.newArrayList(); 
    		list.add(new TsPurchase());
    		new ExportExcel("资源采购数据", TsPurchase.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/purchase/tsPurchase/list?repage";
    }
	
	@RequiresPermissions("purchase:tsPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsPurchase tsPurchase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsPurchase> page = tsPurchaseService.findPage(new Page<TsPurchase>(request, response), tsPurchase);
		TsOrder tsOrder = new TsOrder();
		tsOrder.setOrderState("1");
		model.addAttribute("tsOrder", tsOrderService.findList(tsOrder)); 
		model.addAttribute("page", page);
		return "modules/purchase/tsPurchaseList";
	}

	@RequiresPermissions("purchase:tsPurchase:view")
	@RequestMapping(value = "form")
	public String form(TsPurchase tsPurchase, Model model) {
		TsOrder tsOrder = new TsOrder();
		tsOrder.setOrderState("1");
		model.addAttribute("tsOrder", tsOrderService.findList(tsOrder));  
		model.addAttribute("tsPurchase", tsPurchase);
		return "modules/purchase/tsPurchaseForm";
	}

	@RequiresPermissions("purchase:tsPurchase:edit")
	@RequestMapping(value = "save")
	public String save(TsPurchase tsPurchase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsPurchase)){
			return form(tsPurchase, model);
		}
		tsPurchaseService.save(tsPurchase);
		addMessage(redirectAttributes, "保存征订信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/purchase/tsPurchase/?repage";
	}
	
	@RequiresPermissions("purchase:tsPurchase:edit")
	@RequestMapping(value = "delete")
	public String delete(TsPurchase tsPurchase, RedirectAttributes redirectAttributes) {
		tsPurchaseService.delete(tsPurchase);
		addMessage(redirectAttributes, "删除征订信息维护成功");
		return "redirect:"+Global.getAdminPath()+"/purchase/tsPurchase/?repage";
	}
	
	
	/**
	 * 导入采购资源
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("purchase:tsPurchase:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			User user = new User();
			Dict dict = new Dict();
			List<TsPurchase> list = ei.getDataList(TsPurchase.class);
			for (TsPurchase tsPurchase : list){
				user = new User() ;
				try{
					if (tsPurchase != null){
						user.setOffice(tsPurchase.getOffice());
						user.setName(tsPurchase.getUserName());
						List<User> listUser = systemService.findUserByOfficeIdAndName(user);
						if(listUser.size()>0){
							tsPurchase.setUser(listUser.get(0));
						}
						
						tsPurchase.setZyState("1");
						tsPurchase.setZyType(DictUtils.getDictValue(tsPurchase.getZyType(), "zy_type", "") );
						tsPurchase.setCgType(DictUtils.getDictValue(tsPurchase.getCgType(), "cg_type", ""));
						tsPurchaseService.save(tsPurchase);
						successNum++;
					}else{
						failureMsg.append("<br/>资源名 "+tsPurchase.getName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>资源名 "+tsPurchase.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>资源名 "+tsPurchase.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条采购资源，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条待采购"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入采购资失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/purchase/tsPurchase?repage";
    }

}