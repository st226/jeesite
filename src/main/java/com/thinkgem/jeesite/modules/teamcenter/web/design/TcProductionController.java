/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teamcenter.web.design;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.thinkgem.jeesite.modules.quality.entity.qmresource.QmResource;
import com.thinkgem.jeesite.modules.teamcenter.entity.design.TcProduction;
import com.thinkgem.jeesite.modules.teamcenter.entity.production.TcProductionType;
import com.thinkgem.jeesite.modules.teamcenter.service.design.TcProductionService;
import com.thinkgem.jeesite.modules.teamcenter.service.production.TcProductionTypeService;

/**
 * 生产图纸Controller
 * @author suntao
 * @version 2020-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/teamcenter/design/tcProduction")
public class TcProductionController extends BaseController {

	@Autowired
	private TcProductionService tcProductionService;
	@Autowired
	private TcProductionTypeService tcProductionTypeService ;
	
	@ModelAttribute
	public TcProduction get(@RequestParam(required=false) String id) {
		TcProduction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tcProductionService.get(id);
		}
		if (entity == null){
			entity = new TcProduction();
		}
		return entity;
	}
	
	@RequiresPermissions("teamcenter:design:tcProduction:view")
	@RequestMapping(value = {"index"})
	public String index(TcProduction tcProduction, Model model) {
		return "modules/teamcenter/design/tcProductionIndex";
	}
	
	@RequiresPermissions("teamcenter:design:tcProduction:view")
	@RequestMapping(value = {"list", ""})
	public String list(TcProduction tcProduction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TcProduction> page = tcProductionService.findPage(new Page<TcProduction>(request, response), tcProduction); 
		model.addAttribute("typeId", tcProduction.getTypeId());
		model.addAttribute("page", page);
		return "modules/teamcenter/design/tcProductionList";
	}

	@RequiresPermissions("teamcenter:design:tcProduction:view")
	@RequestMapping(value = "form")
	public String form(TcProduction tcProduction, Model model) {
		if(tcProduction.getIsNewRecord()){
			if(tcProduction.getTypeId()!=null && !"".equals(tcProduction.getTypeId())){
				TcProductionType tcProductionType = tcProductionTypeService.get(tcProduction.getTypeId());
				tcProduction.setName(tcProductionType.getName());
				tcProduction.setCode(tcProductionType.getCode());
				tcProduction.setDrawingNo(tcProductionType.getDrawingNo());
				tcProduction.setEdition(tcProductionType.getEdition());
			}
		}
		model.addAttribute("tcProduction", tcProduction);
		return "modules/teamcenter/design/tcProductionForm";
	}

	@RequiresPermissions("teamcenter:design:tcProduction:edit")
	@RequestMapping(value = "save")
	public String save(TcProduction tcProduction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tcProduction)){
			return form(tcProduction, model);
		}
		if(tcProduction.getIsNewRecord()){
			tcProduction.setEdition("001");
			tcProductionService.save(tcProduction);
		}else{
			String edition = tcProduction.getEdition();
			int a = Integer.parseInt(edition);
			tcProduction.setEdition(getCode(a));
			
			TcProduction newtcProduction = new TcProduction();
			newtcProduction.setName(tcProduction.getName());
			newtcProduction.setTypeId(tcProduction.getTypeId());
			newtcProduction.setDrawingNo(tcProduction.getDrawingNo());
			newtcProduction.setCode(tcProduction.getCode());
			newtcProduction.setEdition(tcProduction.getEdition());
			newtcProduction.setStage(tcProduction.getStage());
			newtcProduction.setFile(tcProduction.getFile());
			newtcProduction.setFilepdf(tcProduction.getFilepdf());
			tcProductionService.save(newtcProduction);
			tcProductionService.delete(tcProduction);
		}
		
		addMessage(redirectAttributes, "保存生产图纸成功");
		return "redirect:"+Global.getAdminPath()+"/teamcenter/design/tcProduction/?repage";
	}
	
	public String getCode(int maxc){
		
		
		
	      String codex = "" ;
			int nowc = maxc + 1;
			int len = String.valueOf(nowc).length();
			int siz = 3 - len;
			if (siz == 0)
				codex = codex + String.valueOf(nowc);
			if (siz == 1)
				codex = codex + "0" + String.valueOf(nowc);
			if (siz == 2)
				codex = codex + "00" + String.valueOf(nowc);
			if (siz == 3)
				codex = codex + "000" + String.valueOf(nowc);
		
			
		
		return codex ;	
	}
	
	@RequiresPermissions("teamcenter:design:tcProduction:edit")
	@RequestMapping(value = "delete")
	public String delete(TcProduction tcProduction, RedirectAttributes redirectAttributes) {
		tcProductionService.delete(tcProduction);
		addMessage(redirectAttributes, "删除生产图纸成功");
		return "redirect:"+Global.getAdminPath()+"/teamcenter/design/tcProduction/?repage";
	}

}