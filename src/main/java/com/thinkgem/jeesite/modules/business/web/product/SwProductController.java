/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.product;

import java.text.SimpleDateFormat;
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
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.receive.SwReceiveEquipment;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.equipment.entity.equipment.SbEquipment;
import com.thinkgem.jeesite.modules.equipment.entity.sbcache.SbCache;
import com.thinkgem.jeesite.modules.resource.entity.DataMap;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resourcebus.entity.TsResourceBus;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import net.sf.ehcache.search.aggregator.Count;

/**
 * 采购设备清单Controller
 * @author suntao
 * @version 2020-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/business/product/swProduct")
public class SwProductController extends BaseController {

	@Autowired
	private SwProductService swProductService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@ModelAttribute
	public SwProduct get(@RequestParam(required=false) String id) {
		SwProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swProductService.get(id);
		}
		if (entity == null){
			entity = new SwProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("business:product:swProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwProduct swProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		swProduct.setZrUserId(UserUtils.getUser().getId());
		Page<SwProduct> page = swProductService.findPage(new Page<SwProduct>(request, response), swProduct);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
		model.addAttribute("time",df.format(new Date()).substring(0, 7));
		
		model.addAttribute("page", page);
		return "modules/business/product/swProductList";
	}

	@RequiresPermissions("business:product:swProduct:view")
	@RequestMapping(value = "form")
	public String form(SwProduct swProduct, Model model) {
		if(swProduct.getIsNewRecord()){
			swProduct.setProjectType("2") ;
			swProduct.setType("1") ;
		}
		model.addAttribute("swProduct", swProduct);
		return "modules/business/product/swProductForm";
	}
	
	@RequiresPermissions("business:product:swProduct:view")
	@RequestMapping(value = "formPl")
	public String formPl(SwProduct swProduct, Model model) {
		model.addAttribute("swProduct", swProduct);
		return "modules/business/product/swProductFormPl";
	}

	@RequiresPermissions("business:product:swProduct:edit")
	@RequestMapping(value = "save")
	public String save(SwProduct swProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swProduct)){
			return form(swProduct, model);
		}
		if(swProduct.getState()==null){
			swProduct.setState("0");
		}
		swProductService.save(swProduct);
		if(swProduct.getOrderId()!=null && !"".equals(swProduct.getOrderId())){
			SwOrder swOrder = swOrderService.get(swProduct.getOrderId());
			SwProduct temp = new SwProduct();

			temp.setOrderId(swOrder.getId());
			List<SwProduct> list =  swProductService.findList(temp);
			
			double count = 0 ; 
			for (SwProduct swProduct2 : list) {	
				count = count +swProduct2.getTotalPrice() ;
			}
			swOrder.setAmount(count*10000);
			swOrder.setAmountYs(count*10000);
			swOrderService.save(swOrder);
		}
		
		addMessage(redirectAttributes, "保存采购设备清单成功");
		return "redirect:"+Global.getAdminPath()+"/business/product/swProduct/?repage";
	}
	
	@RequestMapping(value = "saveSwProduct")
	public String saveSwProduct(SwProduct swProduct,String ids ,HttpServletResponse response) {
		String id[] = ids.split(",");
		for (String idstring : id) {
			SwProduct temp = swProductService.get(idstring);
			if(swProduct.getProductName()!=null &&!"".equals(swProduct.getProductName()))
				temp.setProductName(swProduct.getProductName()) ;
			if(swProduct.getProductType()!=null &&!"".equals(swProduct.getProductType()))
				temp.setProductType(swProduct.getProductType());
			if(swProduct.getProductMade()!=null &&!"".equals(swProduct.getProductMade()))
				temp.setProductMade(swProduct.getProductMade());
			if(swProduct.getUnitPrice()!=null &&!"".equals(swProduct.getUnitPrice()))
				temp.setUnitPrice(swProduct.getUnitPrice());
			
			if(swProduct.getProductAmount()!=null &&!"".equals(swProduct.getProductAmount()))
				temp.setProductAmount(swProduct.getProductAmount());
			if(swProduct.getTotalPrice()!=null &&!"".equals(swProduct.getTotalPrice()))
				temp.setTotalPrice(swProduct.getTotalPrice());
			if(swProduct.getReason()!=null &&!"".equals(swProduct.getReason()))
				temp.setReason(swProduct.getReason());
			if(swProduct.getOffice()!=null)
				temp.setOffice(swProduct.getOffice());
			if(swProduct.getOfficeName()!=null &&!"".equals(swProduct.getOfficeName()))
				temp.setOfficeName(swProduct.getOfficeName());
			
			if(swProduct.getUser()!=null)
				temp.setUser(swProduct.getUser());
			if(swProduct.getUserName()!=null &&!"".equals(swProduct.getUserName()))
				temp.setUserName(swProduct.getUserName());
			if(swProduct.getProductUse()!=null &&!"".equals(swProduct.getProductUse()))
				temp.setProductUse(swProduct.getProductUse());
			if(swProduct.getProductDate()!=null)
				temp.setProductDate(swProduct.getProductDate());
			if(swProduct.getSbType()!=null &&!"".equals(swProduct.getSbType()))
				temp.setSbType(swProduct.getSbType());
			if(swProduct.getSbTypeName()!=null &&!"".equals(swProduct.getSbTypeName()))
				temp.setSbTypeName(swProduct.getSbTypeName());
			if(swProduct.getZrUserId()!=null &&!"".equals(swProduct.getZrUserId()))
				temp.setZrUserId(swProduct.getZrUserId());
			if(swProduct.getZrUserName()!=null &&!"".equals(swProduct.getZrUserName()))
				temp.setZrUserName(swProduct.getZrUserName());
			if(swProduct.getProjectType()!=null &&!"".equals(swProduct.getProjectType()))
				temp.setProjectType(swProduct.getProjectType());
			
			swProductService.save(temp);
			
		}
		
		
		 return  renderString(response, JsonMapper.toJsonString("sucess"),"text/html");
	}
	
	@RequiresPermissions("business:product:swProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(SwProduct swProduct, RedirectAttributes redirectAttributes) {
		swProductService.delete(swProduct);
		addMessage(redirectAttributes, "删除采购设备清单成功");
		return "redirect:"+Global.getAdminPath()+"/business/product/swProduct/?repage";
	}
	

    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "采购导入模板.xlsx";
            List<TsResource> list = Lists.newArrayList();
    		new ExportExcel("采购数据", SwProduct.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/business/product/swProduct/list?repage";
    }
    

    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SwProduct swProduct, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
            String fileName = "采购设备信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<SwProduct> page = swProductService.findPage(new Page<SwProduct>(), swProduct) ;
    		new ExportExcel("采购设备信息", SwProduct.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出采购失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/business/product/swProduct/list?repage";
    }
    
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SwProduct> list = ei.getDataList(SwProduct.class);
			for (SwProduct swProduct : list){
				swProduct.setProjectType("1");
				swProductService.save(swProduct);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条采购，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条采购"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入采购失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/business/product/swProduct/list?repage";
    }
    
    
    @RequestMapping(value = "getProduct")
    @ResponseBody
    public void getProduct(String orderId,HttpServletResponse response){
	 SwProduct swProduct = new SwProduct();
	 swProduct.setOrderId(orderId);
	 List list = swProductService.findList(swProduct);
	 

     renderString(response, JsonMapper.toJsonString(list),"text/html");
    
    }

}