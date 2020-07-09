/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.special;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.product.SwProduct;
import com.thinkgem.jeesite.modules.business.entity.special.SwSpecial;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.product.SwProductService;
import com.thinkgem.jeesite.modules.business.service.special.SwSpecialService;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import Aspose.Words.Document;

/**
 * 特殊项目申请表Controller
 * @author suntao
 * @version 2020-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/business/special/swSpecial")
public class SwSpecialController extends BaseController {

	@Autowired
	private SwSpecialService swSpecialService;
	
	@Autowired
	private SwOrderService swOrderService;
	
	@Autowired
	private SwProductService swProductService;
	
	@ModelAttribute
	public SwSpecial get(@RequestParam(required=false) String id) {
		SwSpecial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swSpecialService.get(id);
		}
		if (entity == null){
			entity = new SwSpecial();
		}
		return entity;
	}
	
	@RequiresPermissions("business:special:swSpecial:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwSpecial swSpecial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwSpecial> page = swSpecialService.findPage(new Page<SwSpecial>(request, response), swSpecial); 
		model.addAttribute("page", page);
		return "modules/business/special/swSpecialList";
	}

	@RequiresPermissions("business:special:swSpecial:view")
	@RequestMapping(value = "form")
	public String form(SwSpecial swSpecial, Model model) {
		String reason = "" ;
		if(swSpecial.getId()==null || "".equals(swSpecial.getId())){
			swSpecial.setApplicationDate(new Date());
			swSpecial.setState("0");
			Office office = null ;
			if(swSpecial.getOrderId()!=null && !"".equals(swSpecial.getOrderId())){
				 SwOrder swOrder = swOrderService.get(swSpecial.getOrderId());
				 
				  swSpecial.setBudget(swOrder.getAmountYs().toString());
				  swSpecial.setProjectName(swOrder.getName());
				  SwProduct temp = new SwProduct();
				  temp.setOrderId(swSpecial.getOrderId());
				  List<SwProduct> swProductList = swProductService.findList(temp) ;
				  for (SwProduct swProduct : swProductList) {
					  if("".equals(reason)){
						  reason = swProduct.getReason();
					  }else{
						  reason = ";"+ swProduct.getReason();
					  }
					  if(swProduct.getOffice()!=null)
					  office = swProduct.getOffice();
				}
				  
				  swSpecial.setReason(reason);  
				  swSpecial.setOffice(office);
				  if(office!=null){
					  swSpecial.setOfficeName(office.getName());
				  }
				  
			}
			 
		}
		model.addAttribute("amountYs", swSpecial.getBudget());
		model.addAttribute("swSpecial", swSpecial);
		return "modules/business/special/swSpecialForm";
	}
	

	@RequestMapping(value = "view")
	public String view(SwSpecial swSpecial, Model model) {
		model.addAttribute("swSpecial", swSpecial);
		return "modules/business/special/swSpecialView";
	}
	
	@RequestMapping(value = "look")
	public String look(SwSpecial swSpecial, Model model) {
		model.addAttribute("swSpecial", swSpecial);
		return "modules/business/special/swSpecialLook";
	}
	
	 @RequestMapping(value = "exportSwSpecial")
	    @ResponseBody
	    public void exportSwSpecial(String content ,HttpServletResponse response,HttpServletRequest request){
		  String path = System.getProperty("user.dir");
		  
		 path = request.getSession().getServletContext().getRealPath("/");
		 path = path +"userfiles\\download\\" ;
		 

		  try {
		
		    
		    // 检查目录是否存在
		    File fileDir = new File(path);
		    if (fileDir.exists()) {
		     
		     // 生成临时文件名称
		     String fileName = UUID.randomUUID().toString().replaceAll("-", "")+".doc";
		     String x = StringEscapeUtils.unescapeHtml(content);
		    
		     byte b[] = x.getBytes();
		     ByteArrayInputStream bais = new ByteArrayInputStream(b);
		     POIFSFileSystem poifs = new POIFSFileSystem();
		     DirectoryEntry directory = poifs.getRoot();
		     DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		     System.out.println(path+ fileName);
		     FileOutputStream ostream = new FileOutputStream(path+ fileName);
		     poifs.writeFilesystem(ostream);
		     bais.close();
		     ostream.close();
			
		     renderString(response, JsonMapper.toJsonString(fileName),"text/html");
		     
		    }
		

		  } catch (IOException e) {
		   e.printStackTrace();
		  }


		 
	 }

	@RequiresPermissions("business:special:swSpecial:edit")
	@RequestMapping(value = "save")
	public String save(SwSpecial swSpecial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swSpecial)){
			return form(swSpecial, model);
		}
		
		if(swSpecial.getCode()==null || "".equals(swSpecial.getCode())){
			String code = swOrderService.getGyCode("", 3, false, false, "", "sw_special", "field1");
			swSpecial.setField1(code);
			String[] codes = code.split("-") ;
			swSpecial.setCode("JGTS-"+codes[0]+codes[1]+"-GX");
		}
		
		swSpecialService.save(swSpecial);
		SwOrder swOrder = swOrderService.get(swSpecial.getOrderId());
		swOrder.setField1Id(swSpecial.getId());
		
		
		if("1".equals(swSpecial.getState())){
			swOrder.setField1State("3");
			swOrder.setField1DateEnd(new Date());
			swOrder.setField1Text("<font color='green'>特殊项目申请</font><i style='color: green;' class='icon-ok-sign'></i>");
			if("2".equals(swOrder.getType())){
				swOrder.setField1State("3");
				swOrder.setField5State("2");
			}else{
				if(swOrder.getAmountYs()<20000 || "1".equals(swOrder.getType())){
					swOrder.setField3State("2");
				}else{
					swOrder.setField2State("2");
				}
			}
			
		}
		swOrderService.save(swOrder);

		addMessage(redirectAttributes, "保存特殊项目成功");
	
		 if("1".equals(swSpecial.getState())){
				
				return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?identification="+swOrder.getIdentification();
			}else{
				return "redirect:"+Global.getAdminPath()+"/business/special/swSpecial/form?id="+swSpecial.getId();
			}
		
	}
	
	@RequiresPermissions("business:special:swSpecial:edit")
	@RequestMapping(value = "delete")
	public String delete(SwSpecial swSpecial, RedirectAttributes redirectAttributes) {
		swSpecialService.delete(swSpecial);
		addMessage(redirectAttributes, "删除特殊项目成功");
		return "redirect:"+Global.getAdminPath()+"/business/special/swSpecial/?repage";
	}

}