/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.reimbursement;

import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.entity.pay.SwPay;
import com.thinkgem.jeesite.modules.business.entity.reimbursement.SwReimbursement;
import com.thinkgem.jeesite.modules.business.service.agreement.SwAgreementService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.business.service.pay.SwPayService;
import com.thinkgem.jeesite.modules.business.service.reimbursement.SwReimbursementService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 报销单Controller
 * @author suntao
 * @version 2020-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/business/reimbursement/swReimbursement")
public class SwReimbursementController extends BaseController {

	@Autowired
	private SwReimbursementService swReimbursementService;
	
	@Autowired
	private SwPayService swPayService;
	
	@Autowired
	private SwAgreementService swAgreementService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@ModelAttribute
	public SwReimbursement get(@RequestParam(required=false) String id) {
		SwReimbursement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swReimbursementService.get(id);
		}
		if (entity == null){
			entity = new SwReimbursement();
		}
		return entity;
	}
	
	@RequiresPermissions("business:reimbursement:swReimbursement:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwReimbursement swReimbursement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwReimbursement> page = swReimbursementService.findPage(new Page<SwReimbursement>(request, response), swReimbursement); 
		model.addAttribute("page", page);
		return "modules/business/reimbursement/swReimbursementList";
	}

	@RequiresPermissions("business:reimbursement:swReimbursement:view")
	@RequestMapping(value = "form")
	public String form(SwReimbursement swReimbursement, Model model) {
		
		if(swReimbursement.getIsNewRecord()){
			SwPay swPayTemp  = new SwPay();
			swPayTemp.setOrderId(swReimbursement.getOrderId());
			SwOrder swOrder = swOrderService.get(swReimbursement.getOrderId());
			swReimbursement.setProjectName(swOrder.getName());
			swReimbursement.setProjectCode(swOrder.getCode());
			List<SwPay> swPayList = swPayService.findList(swPayTemp);
			if(swPayList!=null && swPayList.size()>0){
				SwPay swPay = swPayList.get(0);
				swReimbursement.setSupplierId(swPay.getSupplierId());
				swReimbursement.setSupplierName(UserUtils.getUser().getOffice().getName());
				
				swReimbursement.setAmount(swPay.getContratePrice());
			}
		}
		
		model.addAttribute("swReimbursement", swReimbursement);
		return "modules/business/reimbursement/swReimbursementForm";
	}
	
	@RequiresPermissions("business:reimbursement:swReimbursement:view")
	@RequestMapping(value = "view")
	public String view(SwReimbursement swReimbursement, Model model) {
		model.addAttribute("swReimbursement", swReimbursement);
		return "modules/business/reimbursement/swReimbursementView";
	}

	@RequiresPermissions("business:reimbursement:swReimbursement:edit")
	@RequestMapping(value = "save")
	public String save(SwReimbursement swReimbursement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swReimbursement)){
			return form(swReimbursement, model);
		}
		swReimbursementService.save(swReimbursement);
		
		SwOrder swOrder = swOrderService.get(swReimbursement.getOrderId());
		swOrder.setField10Id(swReimbursement.getId());
		
		if("1".equals(swReimbursement.getState())){
			swOrder.setField10State("3");
			swOrder.setField10DateEnd(new Date());
			swOrder.setField10Text("<font color='green'>报销</font><i style='color: green;' class='icon-ok-sign'></i>");
		    swOrder.setState("11");
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		addMessage(redirectAttributes, "保存报销单成功");

		 if("1".equals(swReimbursement.getState())){
				
				return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
			}else{
				return "redirect:"+Global.getAdminPath()+"/business/reimbursement/swReimbursement/form?id="+swReimbursement.getId();
			}
	}
	
	@RequiresPermissions("business:reimbursement:swReimbursement:edit")
	@RequestMapping(value = "delete")
	public String delete(SwReimbursement swReimbursement, RedirectAttributes redirectAttributes) {
		swReimbursementService.delete(swReimbursement);
		addMessage(redirectAttributes, "删除报销单成功");
		return "redirect:"+Global.getAdminPath()+"/business/reimbursement/swReimbursement/?repage";
	}

}