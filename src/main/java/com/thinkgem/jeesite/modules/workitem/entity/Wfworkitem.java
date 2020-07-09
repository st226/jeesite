/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.workitem.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工作项维护Entity
 * @author suntao
 * @version 2019-11-12
 */
public class Wfworkitem extends DataEntity<Wfworkitem> {
	
	private static final long serialVersionUID = 1L;
	private String workitemid;		// workitemid
	private String workitemname;		// workitemname
	private String workitemtype;		// workitemtype
	private String workitemdesc;		// workitemdesc
	private String currentstate;		// currentstate
	private String participant;		// participant
	private String partiname;		// partiname
	private String priority;		// priority
	private String istimeout;		// istimeout
	private String limitnum;		// limitnum
	private String limitnumdesc;		// limitnumdesc
	private Date createtime;		// createtime
	private Date starttime;		// starttime
	private Date endtime;		// endtime
	private Date finaltime;		// finaltime
	private Date remindtime;		// remindtime
	private String actionurl;		// actionurl
	private String processinstid;		// processinstid
	private String activityinstid;		// activityinstid
	private String stateslist;		// stateslist
	private String timeoutnum;		// timeoutnum
	private String timeoutnumdesc;		// timeoutnumdesc
	private String processinstname;		// processinstname
	private String activityinstname;		// activityinstname
	private String processdefid;		// processdefid
	private String processdefname;		// processdefname
	private String processchname;		// processchname
	private String activitydefid;		// activitydefid
	private String assistant;		// assistant
	private String assistantname;		// assistantname
	private String bizstate;		// bizstate
	private String allowagent;		// allowagent
	private String rootprocinstid;		// rootprocinstid
	private String actionmask;		// actionmask
	private String urltype;		// urltype
	private String dealresult;		// dealresult
	private String dealopinion;		// dealopinion
	private String extend1;		// extend1
	private String extend2;		// extend2
	private String extend3;		// extend3
	private String extend4;		// extend4
	private String extend5;		// extend5
	private String extend6;		// extend6
	private String extend7;		// extend7
	private String cataloguuid;		// cataloguuid
	private String catalogname;		// catalogname
	private String tenantId;		// tenant_id
	private String pressnum;		// pressnum
	
	public Wfworkitem() {
		super();
	}

	public Wfworkitem(String id){
		super(id);
	}

	public String getWorkitemid() {
		return workitemid;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}
	
	@Length(min=0, max=256, message="workitemname长度必须介于 0 和 256 之间")
	public String getWorkitemname() {
		return workitemname;
	}

	public void setWorkitemname(String workitemname) {
		this.workitemname = workitemname;
	}
	
	@Length(min=0, max=20, message="workitemtype长度必须介于 0 和 20 之间")
	public String getWorkitemtype() {
		return workitemtype;
	}

	public void setWorkitemtype(String workitemtype) {
		this.workitemtype = workitemtype;
	}
	
	@Length(min=0, max=512, message="workitemdesc长度必须介于 0 和 512 之间")
	public String getWorkitemdesc() {
		return workitemdesc;
	}

	public void setWorkitemdesc(String workitemdesc) {
		this.workitemdesc = workitemdesc;
	}
	
	public String getCurrentstate() {
		return currentstate;
	}

	public void setCurrentstate(String currentstate) {
		this.currentstate = currentstate;
	}
	
	@Length(min=0, max=512, message="participant长度必须介于 0 和 512 之间")
	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
	
	@Length(min=0, max=512, message="partiname长度必须介于 0 和 512 之间")
	public String getPartiname() {
		return partiname;
	}

	public void setPartiname(String partiname) {
		this.partiname = partiname;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Length(min=0, max=1, message="istimeout长度必须介于 0 和 1 之间")
	public String getIstimeout() {
		return istimeout;
	}

	public void setIstimeout(String istimeout) {
		this.istimeout = istimeout;
	}
	
	public String getLimitnum() {
		return limitnum;
	}

	public void setLimitnum(String limitnum) {
		this.limitnum = limitnum;
	}
	
	@Length(min=0, max=512, message="limitnumdesc长度必须介于 0 和 512 之间")
	public String getLimitnumdesc() {
		return limitnumdesc;
	}

	public void setLimitnumdesc(String limitnumdesc) {
		this.limitnumdesc = limitnumdesc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFinaltime() {
		return finaltime;
	}

	public void setFinaltime(Date finaltime) {
		this.finaltime = finaltime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRemindtime() {
		return remindtime;
	}

	public void setRemindtime(Date remindtime) {
		this.remindtime = remindtime;
	}
	
	@Length(min=0, max=256, message="actionurl长度必须介于 0 和 256 之间")
	public String getActionurl() {
		return actionurl;
	}

	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	
	public String getActivityinstid() {
		return activityinstid;
	}

	public void setActivityinstid(String activityinstid) {
		this.activityinstid = activityinstid;
	}
	
	@Length(min=0, max=256, message="stateslist长度必须介于 0 和 256 之间")
	public String getStateslist() {
		return stateslist;
	}

	public void setStateslist(String stateslist) {
		this.stateslist = stateslist;
	}
	
	public String getTimeoutnum() {
		return timeoutnum;
	}

	public void setTimeoutnum(String timeoutnum) {
		this.timeoutnum = timeoutnum;
	}
	
	@Length(min=0, max=512, message="timeoutnumdesc长度必须介于 0 和 512 之间")
	public String getTimeoutnumdesc() {
		return timeoutnumdesc;
	}

	public void setTimeoutnumdesc(String timeoutnumdesc) {
		this.timeoutnumdesc = timeoutnumdesc;
	}
	
	@Length(min=0, max=256, message="processinstname长度必须介于 0 和 256 之间")
	public String getProcessinstname() {
		return processinstname;
	}

	public void setProcessinstname(String processinstname) {
		this.processinstname = processinstname;
	}
	
	@Length(min=0, max=256, message="activityinstname长度必须介于 0 和 256 之间")
	public String getActivityinstname() {
		return activityinstname;
	}

	public void setActivityinstname(String activityinstname) {
		this.activityinstname = activityinstname;
	}
	
	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}
	
	@Length(min=0, max=256, message="processdefname长度必须介于 0 和 256 之间")
	public String getProcessdefname() {
		return processdefname;
	}

	public void setProcessdefname(String processdefname) {
		this.processdefname = processdefname;
	}
	
	@Length(min=0, max=256, message="processchname长度必须介于 0 和 256 之间")
	public String getProcesschname() {
		return processchname;
	}

	public void setProcesschname(String processchname) {
		this.processchname = processchname;
	}
	
	@Length(min=0, max=64, message="activitydefid长度必须介于 0 和 64 之间")
	public String getActivitydefid() {
		return activitydefid;
	}

	public void setActivitydefid(String activitydefid) {
		this.activitydefid = activitydefid;
	}
	
	@Length(min=0, max=64, message="assistant长度必须介于 0 和 64 之间")
	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	
	@Length(min=0, max=64, message="assistantname长度必须介于 0 和 64 之间")
	public String getAssistantname() {
		return assistantname;
	}

	public void setAssistantname(String assistantname) {
		this.assistantname = assistantname;
	}
	
	public String getBizstate() {
		return bizstate;
	}

	public void setBizstate(String bizstate) {
		this.bizstate = bizstate;
	}
	
	@Length(min=0, max=10, message="allowagent长度必须介于 0 和 10 之间")
	public String getAllowagent() {
		return allowagent;
	}

	public void setAllowagent(String allowagent) {
		this.allowagent = allowagent;
	}
	
	public String getRootprocinstid() {
		return rootprocinstid;
	}

	public void setRootprocinstid(String rootprocinstid) {
		this.rootprocinstid = rootprocinstid;
	}
	
	@Length(min=0, max=64, message="actionmask长度必须介于 0 和 64 之间")
	public String getActionmask() {
		return actionmask;
	}

	public void setActionmask(String actionmask) {
		this.actionmask = actionmask;
	}
	
	@Length(min=0, max=20, message="urltype长度必须介于 0 和 20 之间")
	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}
	
	@Length(min=0, max=512, message="dealresult长度必须介于 0 和 512 之间")
	public String getDealresult() {
		return dealresult;
	}

	public void setDealresult(String dealresult) {
		this.dealresult = dealresult;
	}
	
	@Length(min=0, max=256, message="dealopinion长度必须介于 0 和 256 之间")
	public String getDealopinion() {
		return dealopinion;
	}

	public void setDealopinion(String dealopinion) {
		this.dealopinion = dealopinion;
	}
	
	@Length(min=0, max=64, message="extend1长度必须介于 0 和 64 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=64, message="extend2长度必须介于 0 和 64 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=64, message="extend3长度必须介于 0 和 64 之间")
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	
	@Length(min=0, max=64, message="extend4长度必须介于 0 和 64 之间")
	public String getExtend4() {
		return extend4;
	}

	public void setExtend4(String extend4) {
		this.extend4 = extend4;
	}
	
	@Length(min=0, max=64, message="extend5长度必须介于 0 和 64 之间")
	public String getExtend5() {
		return extend5;
	}

	public void setExtend5(String extend5) {
		this.extend5 = extend5;
	}
	
	@Length(min=0, max=64, message="extend6长度必须介于 0 和 64 之间")
	public String getExtend6() {
		return extend6;
	}

	public void setExtend6(String extend6) {
		this.extend6 = extend6;
	}
	
	public String getExtend7() {
		return extend7;
	}

	public void setExtend7(String extend7) {
		this.extend7 = extend7;
	}
	
	@Length(min=0, max=32, message="cataloguuid长度必须介于 0 和 32 之间")
	public String getCataloguuid() {
		return cataloguuid;
	}

	public void setCataloguuid(String cataloguuid) {
		this.cataloguuid = cataloguuid;
	}
	
	@Length(min=0, max=64, message="catalogname长度必须介于 0 和 64 之间")
	public String getCatalogname() {
		return catalogname;
	}

	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
	
	@Length(min=0, max=128, message="tenant_id长度必须介于 0 和 128 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getPressnum() {
		return pressnum;
	}

	public void setPressnum(String pressnum) {
		this.pressnum = pressnum;
	}
	
}