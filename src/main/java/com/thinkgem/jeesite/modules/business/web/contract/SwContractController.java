/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.business.web.contract;

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
import com.thinkgem.jeesite.modules.business.entity.contract.SwContract;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiate;
import com.thinkgem.jeesite.modules.business.entity.negotiate.SwNegotiateSupplier;
import com.thinkgem.jeesite.modules.business.entity.order.SwOrder;
import com.thinkgem.jeesite.modules.business.service.contract.SwContractService;
import com.thinkgem.jeesite.modules.business.service.negotiate.SwNegotiateService;
import com.thinkgem.jeesite.modules.business.service.order.SwOrderService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 合同审批Controller
 * @author suntao
 * @version 2020-03-20
 */
@Controller
@RequestMapping(value = "${adminPath}/business/contract/swContract")
public class SwContractController extends BaseController {

	@Autowired
	private SwContractService swContractService;
	
	@Autowired
	private SwOrderService swOrderService ;
	
	@Autowired
	private SwNegotiateService swNegotiateService;
	
	@ModelAttribute
	public SwContract get(@RequestParam(required=false) String id) {
		SwContract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = swContractService.get(id);
		}
		if (entity == null){
			entity = new SwContract();
		}
		return entity;
	}
	
	@RequiresPermissions("business:contract:swContract:view")
	@RequestMapping(value = {"list", ""})
	public String list(SwContract swContract, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SwContract> page = swContractService.findPage(new Page<SwContract>(request, response), swContract); 
		model.addAttribute("page", page);
		return "modules/business/contract/swContractList";
	}

	@RequiresPermissions("business:contract:swContract:view")
	@RequestMapping(value = "form")
	public String form(SwContract swContract, Model model) {
		
		if(swContract.getId()==null || "".equals(swContract.getId())){
			swContract.setState("0");
			if(swContract.getOrderId()!=null && !"".equals(swContract.getOrderId())){
				SwOrder swOrder = swOrderService.get(swContract.getOrderId()) ;
				if(swOrder.getField3Id()!=null && !"".equals(swOrder.getField3Id())){
					SwNegotiate swNegotiate = swNegotiateService.get(swOrder.getField3Id());
					swContract.setContractName(swNegotiate.getProjectName());
					swContract.setContractPrice(swNegotiate.getNegotiatePrice());
					if(swNegotiate.getSwNegotiateSupplierList().size()==1){
						SwNegotiateSupplier swNegotiateSupplier = swNegotiate.getSwNegotiateSupplierList().get(0);
						swContract.setSupplierName(swNegotiateSupplier.getSupplierName());
						swContract.setSupplierUser(swNegotiateSupplier.getSupplierUser());
						swContract.setSupplierPhone(swNegotiateSupplier.getPhone());
						swContract.setSupplierId(swNegotiateSupplier.getSupplierId());
						
					}
					
				}
				
				
			}
			swContract.setContractUser(UserUtils.getUser().getName());
			swContract.setContractPhone(UserUtils.getUser().getMobile());
		}
		
		
		model.addAttribute("swContract", swContract);
		return "modules/business/contract/swContractForm";
	}
	
	@RequestMapping(value = "view")
	public String view(SwContract swContract, Model model) {
		model.addAttribute("swContract", swContract);
		return "modules/business/contract/swContractView";
	}

	@RequiresPermissions("business:contract:swContract:edit")
	@RequestMapping(value = "save")
	public String save(SwContract swContract, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, swContract)){
			return form(swContract, model);
		}
		
		
		if(swContract.getContractCode()==null ||"".equals(swContract.getContractCode())){
			String code = swOrderService.getGyCode("", 3, false, false, "", "sw_contract", "field2");
			swContract.setField2(code);
			String codes[] = code.split("-");
			if(swContract.getContractType().equals("JG13")){
				swContract.setContractCode(swContract.getContractType()+codes[0]+"PC"+codes[1]);
			}else{
				swContract.setContractCode(swContract.getContractType()+codes[0]+"05"+codes[1]);
			}
				
			
		}
		
		swContractService.save(swContract);
		SwOrder swOrder = swOrderService.get(swContract.getOrderId());
		swOrder.setField4Id(swContract.getId());
		
		if("1".equals(swContract.getState())){
			swOrder.setField4State("3");
			swOrder.setField4Text("<font color='green'>合同申请完成</font><i style='color: green;' class='icon-ok-sign'></i>");
		    swOrder.setField5State("2");
			
		}
		
		
		swOrderService.save(swOrder);
		
		
		addMessage(redirectAttributes, "保存合同成功");
          if("1".equals(swContract.getState())){
			
			return "redirect:"+Global.getAdminPath()+"/business/order/swOrder/?repage";
		}else{
			return "redirect:"+Global.getAdminPath()+"/business/contract/swContract/form?id="+swContract.getId();
		}

	}
	
	@RequiresPermissions("business:contract:swContract:edit")
	@RequestMapping(value = "delete")
	public String delete(SwContract swContract, RedirectAttributes redirectAttributes) {
		swContractService.delete(swContract);
		addMessage(redirectAttributes, "删除合同成功");
		return "redirect:"+Global.getAdminPath()+"/business/contract/swContract/?repage";
	}

}