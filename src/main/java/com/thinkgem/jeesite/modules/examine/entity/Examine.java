/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.examine.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 审批意见Entity
 * @author suntao
 * @version 2019-11-13
 */
public class Examine extends DataEntity<Examine> {
	
	private static final long serialVersionUID = 1L;
	private String examineExplain;		// examine_explain
	private String examineExamineisagree;		// examine_examineisagree
	private String examinePerson;		// examine_person
	private Date examineDate;		// examine_date
	private String examineJd;		// examine_jd
	private String examineSyzs;		// examine_syzs
	private Long examineDtzcs;		// examine_dtzcs
	private Long examineDtzzs;		// examine_dtzzs
	private Long examineLtzfs;		// examine_ltzfs
	private Long examineLtzzs;		// examine_ltzzs
	private Long processinstid;		// processinstid
	private Long examineVersion;		// examine_version
	
	public Examine() {
		super();
	}

	public Examine(String id){
		super(id);
	}

	@Length(min=0, max=255, message="examine_explain长度必须介于 0 和 255 之间")
	public String getExamineExplain() {
		return examineExplain;
	}

	public void setExamineExplain(String examineExplain) {
		this.examineExplain = examineExplain;
	}
	
	@Length(min=0, max=255, message="examine_examineisagree长度必须介于 0 和 255 之间")
	public String getExamineExamineisagree() {
		return examineExamineisagree;
	}

	public void setExamineExamineisagree(String examineExamineisagree) {
		this.examineExamineisagree = examineExamineisagree;
	}
	
	@Length(min=0, max=255, message="examine_person长度必须介于 0 和 255 之间")
	public String getExaminePerson() {
		return examinePerson;
	}

	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamineDate() {
		return examineDate;
	}

	public void setExamineDate(Date examineDate) {
		this.examineDate = examineDate;
	}
	
	@Length(min=0, max=55, message="examine_jd长度必须介于 0 和 55 之间")
	public String getExamineJd() {
		return examineJd;
	}

	public void setExamineJd(String examineJd) {
		this.examineJd = examineJd;
	}
	
	public String getExamineSyzs() {
		return examineSyzs;
	}

	public void setExamineSyzs(String examineSyzs) {
		this.examineSyzs = examineSyzs;
	}
	
	public Long getExamineDtzcs() {
		return examineDtzcs;
	}

	public void setExamineDtzcs(Long examineDtzcs) {
		this.examineDtzcs = examineDtzcs;
	}
	
	public Long getExamineDtzzs() {
		return examineDtzzs;
	}

	public void setExamineDtzzs(Long examineDtzzs) {
		this.examineDtzzs = examineDtzzs;
	}
	
	public Long getExamineLtzfs() {
		return examineLtzfs;
	}

	public void setExamineLtzfs(Long examineLtzfs) {
		this.examineLtzfs = examineLtzfs;
	}
	
	public Long getExamineLtzzs() {
		return examineLtzzs;
	}

	public void setExamineLtzzs(Long examineLtzzs) {
		this.examineLtzzs = examineLtzzs;
	}
	
	public Long getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	
	public Long getExamineVersion() {
		return examineVersion;
	}

	public void setExamineVersion(Long examineVersion) {
		this.examineVersion = examineVersion;
	}
	
}