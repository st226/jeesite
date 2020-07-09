/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Area;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 图书馆资源管理Entity
 * @author suntao
 * @version 2018-12-26
 */
public class TsResource extends DataEntity<TsResource> {
	
	private static final long serialVersionUID = 1L;
	private String busType;		// 资源类型
	private String local;		// 资源位置
	private String modelid;		// 型号ID
	private String name;		// 资源名称
	private String format;		// 规格
	private String personnel;		// 操作人员
	private String entrytime;		// 录入时间
	private String personcharge;		// 责任人
	private String state;		// 资源状态
	private String inspection;		// 生成日期
	private String power;		// 研制状态
	private String number;		// 资源编号
	private String weight;		// 研制阶段
	private String length;		// 年份
	private String width;		// 月份
	private String department;		// 编制部门
	private String files;		// 附件
	private User user;		// 用户id
	private Office office;		// 部门ID
	private Area area;		// 区域ID
	private String userName;		// 填写人
	private String officeName;		// 部门名称
	private String areaName;		// 区域名称
	private String remark1;		// remark1
	private String remark2;		// remark2
	private String remark3;		// remark3
	private String remark4;		// remark4
	private String remark5;		// remark5
	private String remark6;		// remark6
	private String remark7;		// remark7
	private String remark8;		// remark8
	private String remark9;		// remark9
	private String remark10;		// remark10
	private String remark11;		// remark11
	private String remark12;		// remark12
	private String remark13;		// remark13
	private String remark14;		// remark14
	private String remark15;		// remark15
	private String remark16;		// remark16
	private String remark17;		// remark17
	private String remark18;		// remark18
	private String remark19;		// remark19
	private String remark20;		// remark20
	private String remark21;		// remark21
	private String remark22;		// remark22
	private String remark23;		// remark23
	private String remark24;		// remark24
	private String remark25;		// remark25
	private String remark26;		// remark26
	private String remark27;		// remark27
	private String remark28;		// remark28
	private String remark29;		// remark29
	private String remark30;		// remark30
	private String remark31;		// remark31
	private String remark32;		// remark32
	private String remark33;		// remark33
	private String remark34;		// remark34
	private String remark35;		// remark35
	private String remark36;		// remark36
	private String remark37;		// remark37
	private String remark38;		// remark38
	private String remark39;		// remark39
	private String remark40;		// remark40
	private String remark41;		// remark41
	private String remark42;		// remark42
	private String remark43;		// remark43
	private String remark44;		// remark44
	private String remark45;		// remark45
	private String remark46;		// remark46
	private String remark47;		// remark47
	private String remark48;		// remark48
	private String remark49;		// remark49
	private String remark50;		// remark50
	
	public TsResource() {
		super();
	}

	public TsResource(String id){
		super(id);
	}

	@Length(min=1, max=100, message="资源类型长度必须介于 1 和 100 之间")
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@Length(min=0, max=50, message="资源位置长度必须介于 0 和 50 之间")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=1, max=50, message="型号ID长度必须介于 1 和 50 之间")
	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	
	@Length(min=0, max=100, message="资源名称长度必须介于 0 和 100 之间")
	@ExcelField(title="题名", align=2, sort=20,value="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="规格长度必须介于 0 和 50 之间")
	@ExcelField(title="图号/档号", align=2, sort=10,value="format")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Length(min=0, max=50, message="操作人员长度必须介于 0 和 50 之间")
	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
	
	@Length(min=0, max=50, message="录入时间长度必须介于 0 和 50 之间")
	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}
	
	@Length(min=1, max=50, message="责任人长度必须介于 1 和 50 之间")
	public String getPersoncharge() {
		return personcharge;
	}

	public void setPersoncharge(String personcharge) {
		this.personcharge = personcharge;
	}
	
	@Length(min=0, max=50, message="资源状态长度必须介于 0 和 50 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=50, message="生成日期长度必须介于 0 和 50 之间")
	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	
	@Length(min=0, max=50, message="研制状态长度必须介于 0 和 50 之间")
	@ExcelField(title="阶段", align=2, sort=30,value="power")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	@Length(min=0, max=11, message="资源编号长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=50, message="研制阶段长度必须介于 0 和 50 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=50, message="年份长度必须介于 0 和 50 之间")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	@Length(min=0, max=50, message="月份长度必须介于 0 和 50 之间")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Length(min=0, max=50, message="编制部门长度必须介于 0 和 50 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=500, message="附件长度必须介于 0 和 500 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=128, message="填写人长度必须介于 0 和 128 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=128, message="部门名称长度必须介于 0 和 128 之间")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@Length(min=0, max=128, message="区域名称长度必须介于 0 和 128 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=50, message="remark1长度必须介于 0 和 50 之间")
	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	@Length(min=0, max=50, message="remark2长度必须介于 0 和 50 之间")
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	@Length(min=0, max=50, message="remark3长度必须介于 0 和 50 之间")
	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	
	@Length(min=0, max=50, message="remark4长度必须介于 0 和 50 之间")
	@ExcelField(title="份数", align=2, sort=70,value="remark4")
	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	
	@Length(min=0, max=50, message="remark5长度必须介于 0 和 50 之间")
	@ExcelField(title="页数", align=2, sort=80,value="remark5")
	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}
	
	@Length(min=0, max=50, message="remark6长度必须介于 0 和 50 之间")
	@ExcelField(title="归档人", align=2, sort=90,value="remark6")
	public String getRemark6() {
		return remark6;
	}

	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}
	
	@Length(min=0, max=50, message="remark7长度必须介于 0 和 50 之间")
	@ExcelField(title="归档日期", align=2, sort=100,value="remark7")
	public String getRemark7() {
		return remark7;
	}

	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}
	
	@Length(min=0, max=50, message="remark8长度必须介于 0 和 50 之间")
	@ExcelField(title="保管期限", align=2, sort=110,value="remark8")
	public String getRemark8() {
		return remark8;
	}

	public void setRemark8(String remark8) {
		this.remark8 = remark8;
	}
	
	@Length(min=0, max=50, message="remark9长度必须介于 0 和 50 之间")
	@ExcelField(title="序号", align=2, sort=1,value="remark9")
	public String getRemark9() {
		return remark9;
	}

	public void setRemark9(String remark9) {
		this.remark9 = remark9;
	}
	
	@Length(min=0, max=50, message="remark10长度必须介于 0 和 50 之间")
	public String getRemark10() {
		return remark10;
	}

	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}
	
	@Length(min=0, max=50, message="remark11长度必须介于 0 和 50 之间")
	@ExcelField(title="文件编号", align=2, sort=40,value="remark11")
	public String getRemark11() {
		return remark11;
	}

	public void setRemark11(String remark11) {
		this.remark11 = remark11;
	}
	
	@Length(min=0, max=50, message="remark12长度必须介于 0 和 50 之间")
	@ExcelField(title="发文日期", align=2, sort=50,value="remark12")
	public String getRemark12() {
		return remark12;
	}

	public void setRemark12(String remark12) {
		this.remark12 = remark12;
	}
	
	@Length(min=0, max=50, message="remark13长度必须介于 0 和 50 之间")
	@ExcelField(title="密级", align=2, sort=60,value="remark13")
	public String getRemark13() {
		return remark13;
	}

	public void setRemark13(String remark13) {
		this.remark13 = remark13;
	}
	
	@Length(min=0, max=50, message="remark14长度必须介于 0 和 50 之间")
	public String getRemark14() {
		return remark14;
	}

	public void setRemark14(String remark14) {
		this.remark14 = remark14;
	}
	
	@Length(min=0, max=50, message="remark15长度必须介于 0 和 50 之间")
	public String getRemark15() {
		return remark15;
	}

	public void setRemark15(String remark15) {
		this.remark15 = remark15;
	}
	
	@Length(min=0, max=50, message="remark16长度必须介于 0 和 50 之间")
	public String getRemark16() {
		return remark16;
	}

	public void setRemark16(String remark16) {
		this.remark16 = remark16;
	}
	
	@Length(min=0, max=50, message="remark17长度必须介于 0 和 50 之间")
	public String getRemark17() {
		return remark17;
	}

	public void setRemark17(String remark17) {
		this.remark17 = remark17;
	}
	
	@Length(min=0, max=50, message="remark18长度必须介于 0 和 50 之间")
	public String getRemark18() {
		return remark18;
	}

	public void setRemark18(String remark18) {
		this.remark18 = remark18;
	}
	
	@Length(min=0, max=50, message="remark19长度必须介于 0 和 50 之间")
	public String getRemark19() {
		return remark19;
	}

	public void setRemark19(String remark19) {
		this.remark19 = remark19;
	}
	
	@Length(min=0, max=50, message="remark20长度必须介于 0 和 50 之间")
	public String getRemark20() {
		return remark20;
	}

	public void setRemark20(String remark20) {
		this.remark20 = remark20;
	}
	
	@Length(min=0, max=50, message="remark21长度必须介于 0 和 50 之间")
	public String getRemark21() {
		return remark21;
	}

	public void setRemark21(String remark21) {
		this.remark21 = remark21;
	}
	
	@Length(min=0, max=50, message="remark22长度必须介于 0 和 50 之间")
	public String getRemark22() {
		return remark22;
	}

	public void setRemark22(String remark22) {
		this.remark22 = remark22;
	}
	
	@Length(min=0, max=50, message="remark23长度必须介于 0 和 50 之间")
	public String getRemark23() {
		return remark23;
	}

	public void setRemark23(String remark23) {
		this.remark23 = remark23;
	}
	
	@Length(min=0, max=50, message="remark24长度必须介于 0 和 50 之间")
	public String getRemark24() {
		return remark24;
	}

	public void setRemark24(String remark24) {
		this.remark24 = remark24;
	}
	
	@Length(min=0, max=50, message="remark25长度必须介于 0 和 50 之间")
	public String getRemark25() {
		return remark25;
	}

	public void setRemark25(String remark25) {
		this.remark25 = remark25;
	}
	
	@Length(min=0, max=50, message="remark26长度必须介于 0 和 50 之间")
	public String getRemark26() {
		return remark26;
	}

	public void setRemark26(String remark26) {
		this.remark26 = remark26;
	}
	
	@Length(min=0, max=50, message="remark27长度必须介于 0 和 50 之间")
	public String getRemark27() {
		return remark27;
	}

	public void setRemark27(String remark27) {
		this.remark27 = remark27;
	}
	
	@Length(min=0, max=50, message="remark28长度必须介于 0 和 50 之间")
	public String getRemark28() {
		return remark28;
	}

	public void setRemark28(String remark28) {
		this.remark28 = remark28;
	}
	
	@Length(min=0, max=50, message="remark29长度必须介于 0 和 50 之间")
	public String getRemark29() {
		return remark29;
	}

	public void setRemark29(String remark29) {
		this.remark29 = remark29;
	}
	
	@Length(min=0, max=50, message="remark30长度必须介于 0 和 50 之间")
	public String getRemark30() {
		return remark30;
	}

	public void setRemark30(String remark30) {
		this.remark30 = remark30;
	}
	
	@Length(min=0, max=50, message="remark31长度必须介于 0 和 50 之间")
	public String getRemark31() {
		return remark31;
	}

	public void setRemark31(String remark31) {
		this.remark31 = remark31;
	}
	
	@Length(min=0, max=50, message="remark32长度必须介于 0 和 50 之间")
	public String getRemark32() {
		return remark32;
	}

	public void setRemark32(String remark32) {
		this.remark32 = remark32;
	}
	
	@Length(min=0, max=50, message="remark33长度必须介于 0 和 50 之间")
	public String getRemark33() {
		return remark33;
	}

	public void setRemark33(String remark33) {
		this.remark33 = remark33;
	}
	
	@Length(min=0, max=50, message="remark34长度必须介于 0 和 50 之间")
	public String getRemark34() {
		return remark34;
	}

	public void setRemark34(String remark34) {
		this.remark34 = remark34;
	}
	
	@Length(min=0, max=50, message="remark35长度必须介于 0 和 50 之间")
	public String getRemark35() {
		return remark35;
	}

	public void setRemark35(String remark35) {
		this.remark35 = remark35;
	}
	
	@Length(min=0, max=50, message="remark36长度必须介于 0 和 50 之间")
	public String getRemark36() {
		return remark36;
	}

	public void setRemark36(String remark36) {
		this.remark36 = remark36;
	}
	
	@Length(min=0, max=50, message="remark37长度必须介于 0 和 50 之间")
	public String getRemark37() {
		return remark37;
	}

	public void setRemark37(String remark37) {
		this.remark37 = remark37;
	}
	
	@Length(min=0, max=50, message="remark38长度必须介于 0 和 50 之间")
	public String getRemark38() {
		return remark38;
	}

	public void setRemark38(String remark38) {
		this.remark38 = remark38;
	}
	
	@Length(min=0, max=50, message="remark39长度必须介于 0 和 50 之间")
	public String getRemark39() {
		return remark39;
	}

	public void setRemark39(String remark39) {
		this.remark39 = remark39;
	}
	
	@Length(min=0, max=50, message="remark40长度必须介于 0 和 50 之间")
	public String getRemark40() {
		return remark40;
	}

	public void setRemark40(String remark40) {
		this.remark40 = remark40;
	}
	
	@Length(min=0, max=50, message="remark41长度必须介于 0 和 50 之间")
	public String getRemark41() {
		return remark41;
	}

	public void setRemark41(String remark41) {
		this.remark41 = remark41;
	}
	
	@Length(min=0, max=50, message="remark42长度必须介于 0 和 50 之间")
	public String getRemark42() {
		return remark42;
	}

	public void setRemark42(String remark42) {
		this.remark42 = remark42;
	}
	
	@Length(min=0, max=50, message="remark43长度必须介于 0 和 50 之间")
	public String getRemark43() {
		return remark43;
	}

	public void setRemark43(String remark43) {
		this.remark43 = remark43;
	}
	
	@Length(min=0, max=50, message="remark44长度必须介于 0 和 50 之间")
	public String getRemark44() {
		return remark44;
	}

	public void setRemark44(String remark44) {
		this.remark44 = remark44;
	}
	
	@Length(min=0, max=50, message="remark45长度必须介于 0 和 50 之间")
	public String getRemark45() {
		return remark45;
	}

	public void setRemark45(String remark45) {
		this.remark45 = remark45;
	}
	
	@Length(min=0, max=50, message="remark46长度必须介于 0 和 50 之间")
	public String getRemark46() {
		return remark46;
	}

	public void setRemark46(String remark46) {
		this.remark46 = remark46;
	}
	
	@Length(min=0, max=50, message="remark47长度必须介于 0 和 50 之间")
	public String getRemark47() {
		return remark47;
	}

	public void setRemark47(String remark47) {
		this.remark47 = remark47;
	}
	
	@Length(min=0, max=50, message="remark48长度必须介于 0 和 50 之间")
	public String getRemark48() {
		return remark48;
	}

	public void setRemark48(String remark48) {
		this.remark48 = remark48;
	}
	
	@Length(min=0, max=50, message="remark49长度必须介于 0 和 50 之间")
	public String getRemark49() {
		return remark49;
	}

	public void setRemark49(String remark49) {
		this.remark49 = remark49;
	}
	
	@Length(min=0, max=50, message="remark50长度必须介于 0 和 50 之间")
	public String getRemark50() {
		return remark50;
	}

	public void setRemark50(String remark50) {
		this.remark50 = remark50;
	}
	
}