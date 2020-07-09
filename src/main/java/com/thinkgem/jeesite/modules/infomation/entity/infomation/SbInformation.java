/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infomation.entity.infomation;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 信息化设备Entity
 * @author suntao
 * @version 2020-01-16
 */
public class SbInformation extends DataEntity<SbInformation> {
	
	private static final long serialVersionUID = 1L;
	private String sbType;		// 设备类别
	private String sbTypeName;		// sb_type_name
	private String sbcode;		// 编号
	private String name;		// 名称
	private String type;		// 型号
	private String sbSecret;		// 密级
	private String usepeople;		// 责任人
	private String usepeoplename;		// 责任人名称
	private String starttime;		// 启用日期
	private String useinfo;		// 使用情况
	private String uset;		// 用途
	private String team;		// 所属部门
	private String teamname;		// 所属名称
	private String local;		// 放置地点
	private String serialNumber;		// 磁盘序列号
	private String sbIpaddress;		// IP地址
	private String sbSystemTime;		// 操作系统安装时间
	private String sbMacaddress;		// Mac地址
	private String sbNumber;		// 设备序列号
	private String jsjName;		// 计算机名称
	private String sbBrand;		// 品牌
	private String sbCapacity;		// 容量
	private String sbScrapTime;		// 报废时间
	private String sbEndTime;		// 销毁时间
	private String sbBuyTime;		// 购置时间
	private String made;		// 生产厂家
	private String sysType;		// 类型
	private String sbSystemVersion;		// 软件版本
	private String state;		// 状态
	private String field1;		// field1
	private String field2;		// field2
	private String field3;		// field3
	private String field4;		// field4
	private String field5;		// field5
	private String field6;		// field6
	private String field7;		// field7
	private String field8;		// field8
	private String field9;		// field9
	private String field10;		// field10
	private String field11;		// field11
	private String field12;		// field12
	private String field13;		// field13
	private String field14;		// field14
	private String field15;		// field15
	private String field16;		// field16
	private String field17;		// field17
	private String field18;		// field18
	private String field19;		// field19
	private String field20;		// field20
	private String bstate;		// bstate
	
	public SbInformation() {
		super();
	}

	public SbInformation(String id){
		super(id);
	}

	@Length(min=0, max=200, message="设备类别长度必须介于 0 和 200 之间")
	public String getSbType() {
		return sbType;
	}

	public void setSbType(String sbType) {
		this.sbType = sbType;
	}
	
	@Length(min=0, max=200, message="sb_type_name长度必须介于 0 和 200 之间")
	@ExcelField(title="设备类型", align=2, sort=10,value="sbTypeName")
	public String getSbTypeName() {
		return sbTypeName;
	}

	public void setSbTypeName(String sbTypeName) {
		this.sbTypeName = sbTypeName;
	}
	
	@Length(min=0, max=200, message="编号长度必须介于 0 和 200 之间")
	@ExcelField(title="编号", align=2, sort=20,value="sbcode")
	public String getSbcode() {
		return sbcode;
	}

	public void setSbcode(String sbcode) {
		this.sbcode = sbcode;
	}
	
	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	@ExcelField(title="名称", align=2, sort=30,value="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	@ExcelField(title="型号", align=2, sort=40,value="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=10, message="密级长度必须介于 0 和 10 之间")
	@ExcelField(title="密级", align=2, sort=50,value="sbSecret",dictType="MODEL_CLASS")
	public String getSbSecret() {
		return sbSecret;
	}

	public void setSbSecret(String sbSecret) {
		this.sbSecret = sbSecret;
	}
	
	@Length(min=0, max=200, message="责任人长度必须介于 0 和 200 之间")
	public String getUsepeople() {
		return usepeople;
	}

	public void setUsepeople(String usepeople) {
		this.usepeople = usepeople;
	}
	
	@Length(min=0, max=200, message="责任人名称长度必须介于 0 和 200 之间")
	@ExcelField(title="责任人名称", align=2, sort=60,value="usepeoplename")
	public String getUsepeoplename() {
		return usepeoplename;
	}

	public void setUsepeoplename(String usepeoplename) {
		this.usepeoplename = usepeoplename;
	}
	
	@Length(min=0, max=200, message="启用日期长度必须介于 0 和 200 之间")
	@ExcelField(title="启用日期", align=2, sort=70,value="starttime")
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	@Length(min=0, max=200, message="使用情况长度必须介于 0 和 200 之间")
	@ExcelField(title="使用情况", align=2, sort=80,value="useinfo",dictType="useinfo")
	public String getUseinfo() {
		return useinfo;
	}

	public void setUseinfo(String useinfo) {
		this.useinfo = useinfo;
	}
	
	@Length(min=0, max=200, message="用途长度必须介于 0 和 200 之间")
	@ExcelField(title="用途", align=2, sort=90,value="uset")
	public String getUset() {
		return uset;
	}

	public void setUset(String uset) {
		this.uset = uset;
	}
	
	@Length(min=0, max=200, message="所属部门长度必须介于 0 和 200 之间")
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	@Length(min=0, max=200, message="所属名称长度必须介于 0 和 200 之间")
	@ExcelField(title="所属部门", align=2, sort=100,value="teamname")
	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	
	@Length(min=0, max=200, message="放置地点长度必须介于 0 和 200 之间")
	@ExcelField(title="放置地点", align=2, sort=110,value="local")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	@Length(min=0, max=200, message="磁盘序列号长度必须介于 0 和 200 之间")
	@ExcelField(title="磁盘序列号", align=2, sort=120,value="serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=0, max=200, message="IP地址长度必须介于 0 和 200 之间")
	@ExcelField(title="IP地址", align=2, sort=130,value="sbIpaddress")
	public String getSbIpaddress() {
		return sbIpaddress;
	}

	public void setSbIpaddress(String sbIpaddress) {
		this.sbIpaddress = sbIpaddress;
	}
	
	@Length(min=0, max=200, message="操作系统安装时间长度必须介于 0 和 200 之间")
	@ExcelField(title="操作系统安装时间", align=2, sort=140,value="sbSystemTime")
	public String getSbSystemTime() {
		return sbSystemTime;
	}

	public void setSbSystemTime(String sbSystemTime) {
		this.sbSystemTime = sbSystemTime;
	}
	
	@Length(min=0, max=100, message="Mac地址长度必须介于 0 和 100 之间")
	@ExcelField(title="Mac地址", align=2, sort=150,value="sbMacaddress")
	public String getSbMacaddress() {
		return sbMacaddress;
	}

	public void setSbMacaddress(String sbMacaddress) {
		this.sbMacaddress = sbMacaddress;
	}
	
	@Length(min=0, max=200, message="设备序列号长度必须介于 0 和 200 之间")
	@ExcelField(title="设备序列号", align=2, sort=160,value="sbNumber")
	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}
	
	@Length(min=0, max=200, message="计算机名称长度必须介于 0 和 200 之间")
	@ExcelField(title="计算机名称", align=2, sort=170,value="jsjName")
	public String getJsjName() {
		return jsjName;
	}

	public void setJsjName(String jsjName) {
		this.jsjName = jsjName;
	}
	
	@Length(min=0, max=200, message="品牌长度必须介于 0 和 200 之间")
	@ExcelField(title="品牌", align=2, sort=180,value="sbBrand")
	public String getSbBrand() {
		return sbBrand;
	}

	public void setSbBrand(String sbBrand) {
		this.sbBrand = sbBrand;
	}
	
	@Length(min=0, max=200, message="容量长度必须介于 0 和 200 之间")
	@ExcelField(title="容量", align=2, sort=190,value="sbCapacity")
	public String getSbCapacity() {
		return sbCapacity;
	}

	public void setSbCapacity(String sbCapacity) {
		this.sbCapacity = sbCapacity;
	}
	
	@Length(min=0, max=200, message="报废时间长度必须介于 0 和 200 之间")
	@ExcelField(title="报废时间", align=2, sort=200,value="sbScrapTime")
	public String getSbScrapTime() {
		return sbScrapTime;
	}

	public void setSbScrapTime(String sbScrapTime) {
		this.sbScrapTime = sbScrapTime;
	}
	
	@Length(min=0, max=200, message="销毁时间长度必须介于 0 和 200 之间")
	@ExcelField(title="销毁时间", align=2, sort=210,value="sbEndTime")
	public String getSbEndTime() {
		return sbEndTime;
	}

	public void setSbEndTime(String sbEndTime) {
		this.sbEndTime = sbEndTime;
	}
	
	@Length(min=0, max=200, message="购置时间长度必须介于 0 和 200 之间")
	@ExcelField(title="购置时间", align=2, sort=220,value="sbBuyTime")
	public String getSbBuyTime() {
		return sbBuyTime;
	}

	public void setSbBuyTime(String sbBuyTime) {
		this.sbBuyTime = sbBuyTime;
	}
	
	@Length(min=0, max=200, message="生产厂家长度必须介于 0 和 200 之间")
	@ExcelField(title="生产厂家", align=2, sort=230,value="made")
	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}
	
	@Length(min=0, max=200, message="类型长度必须介于 0 和 200 之间")
	@ExcelField(title="类型", align=2, sort=240,value="sysType")
	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	
	@Length(min=0, max=200, message="软件版本长度必须介于 0 和 200 之间")
	@ExcelField(title="软件版本", align=2, sort=250,value="sbSystemVersion")
	public String getSbSystemVersion() {
		return sbSystemVersion;
	}

	public void setSbSystemVersion(String sbSystemVersion) {
		this.sbSystemVersion = sbSystemVersion;
	}
	
	@Length(min=0, max=200, message="状态长度必须介于 0 和 200 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=200, message="field1长度必须介于 0 和 200 之间")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=200, message="field2长度必须介于 0 和 11 之间")
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	@Length(min=0, max=200, message="field3长度必须介于 0 和 200 之间")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=200, message="field4长度必须介于 0 和 200 之间")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=200, message="field5长度必须介于 0 和 200 之间")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	@Length(min=0, max=200, message="field6长度必须介于 0 和 200 之间")
	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}
	
	@Length(min=0, max=200, message="field7长度必须介于 0 和 200 之间")
	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}
	
	@Length(min=0, max=200, message="field8长度必须介于 0 和 200 之间")
	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}
	
	@Length(min=0, max=200, message="field9长度必须介于 0 和 200 之间")
	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}
	
	@Length(min=0, max=200, message="field10长度必须介于 0 和 200 之间")
	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}
	
	@Length(min=0, max=200, message="field11长度必须介于 0 和 200 之间")
	public String getField11() {
		return field11;
	}

	public void setField11(String field11) {
		this.field11 = field11;
	}
	
	@Length(min=0, max=200, message="field12长度必须介于 0 和 200 之间")
	public String getField12() {
		return field12;
	}

	public void setField12(String field12) {
		this.field12 = field12;
	}
	
	@Length(min=0, max=200, message="field13长度必须介于 0 和 200 之间")
	public String getField13() {
		return field13;
	}

	public void setField13(String field13) {
		this.field13 = field13;
	}
	
	@Length(min=0, max=200, message="field14长度必须介于 0 和 200 之间")
	public String getField14() {
		return field14;
	}

	public void setField14(String field14) {
		this.field14 = field14;
	}
	
	@Length(min=0, max=200, message="field15长度必须介于 0 和 200 之间")
	public String getField15() {
		return field15;
	}

	public void setField15(String field15) {
		this.field15 = field15;
	}
	
	@Length(min=0, max=200, message="field16长度必须介于 0 和 200 之间")
	public String getField16() {
		return field16;
	}

	public void setField16(String field16) {
		this.field16 = field16;
	}
	
	@Length(min=0, max=200, message="field17长度必须介于 0 和 200 之间")
	public String getField17() {
		return field17;
	}

	public void setField17(String field17) {
		this.field17 = field17;
	}
	
	@Length(min=0, max=200, message="field18长度必须介于 0 和 200 之间")
	public String getField18() {
		return field18;
	}

	public void setField18(String field18) {
		this.field18 = field18;
	}
	
	@Length(min=0, max=200, message="field19长度必须介于 0 和 200 之间")
	public String getField19() {
		return field19;
	}

	public void setField19(String field19) {
		this.field19 = field19;
	}
	
	@Length(min=0, max=200, message="field20长度必须介于 0 和 200 之间")
	public String getField20() {
		return field20;
	}

	public void setField20(String field20) {
		this.field20 = field20;
	}
	
	@Length(min=0, max=200, message="bstate长度必须介于 0 和 200 之间")
	public String getBstate() {
		return bstate;
	}

	public void setBstate(String bstate) {
		this.bstate = bstate;
	}
	
}