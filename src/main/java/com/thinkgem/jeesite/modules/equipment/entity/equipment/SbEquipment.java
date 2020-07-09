/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.equipment.entity.equipment;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 仪器设备Entity
 * @author suntao
 * @version 2019-12-07
 */
public class SbEquipment extends DataEntity<SbEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String sbType;		// 设备类别
	private String sbTypeName;		// 设备类别
	private String fsType;		// 资源类别
	private String fsTypeName;		// 资源类别
	private String name;		// 名称
	private String fundingsource;		// 资金来源
	private String cccode;		// 出厂编号
	private String sbcode;		// 设备编号
	private String zccode;		// 资产编号
	private String kind;		// 设备种类
	private String type;		// 型号
	private String state;		// 设备状态
	private String buyteam;		// 购买名称
	private String buyteamname;		// 购买部门名称
	private String team;		// 使用部门
	private String teamname;		// 部门名称
	private String usepeople;		// 使用人
	private String usepeoplename;		// 使用人名称
	private String made;		// 生产厂家
	private String outtime;		// 出厂日期
	private String buytime;		// 购买日期
	private double price;		// 总价
	private double unitprice;		// 单价
	private String starttime;		// 启用日期
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
	private String bstate;		// field20
	
	public SbEquipment() {
		super();
	}

	public SbEquipment(String id){
		super(id);
	}
    
	@Length(min=0, max=200, message="资产编号长度必须介于 0 和 200 之间")
	@ExcelField(title="资产编号", align=2, sort=10,value="zccode")
	public String getZccode() {
		return zccode;
	}

	public void setZccode(String zccode) {
		this.zccode = zccode;
	}
	
	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	@ExcelField(title="名称", align=2, sort=20,value="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="原有编号长度必须介于 0 和 200 之间")
	@ExcelField(title="原有编号", align=2, sort=30,value="cccode")
	public String getCccode() {
		return cccode;
	}

	public void setCccode(String cccode) {
		this.cccode = cccode;
	}

	@Length(min=0, max=200, message="设备编号长度必须介于 0 和 200 之间")
	@ExcelField(title="设备编号", align=2, sort=40,value="sbcode")
	public String getSbcode() {
		return sbcode;
	}

	public void setSbcode(String sbcode) {
		this.sbcode = sbcode;
	}
	
	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	@ExcelField(title="规格型号", align=2, sort=50,value="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="生产厂家长度必须介于 0 和 200 之间")
	@ExcelField(title="生产厂家", align=2, sort=60,value="made")
	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}
	
	@Length(min=0, max=200, message="field1长度必须介于 0 和 200 之间")
	@ExcelField(title="出厂编号", align=2, sort=70,value="field1")
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	@Length(min=0, max=200, message="field2长度必须介于 0 和 200 之间")
	@ExcelField(title="数量", align=2, sort=80,value="field2")
	public String getField2() {
		return field2;
	}
	
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	
	@ExcelField(title="单价", align=2, sort=85,value="unitprice")
	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
	

	
	@ExcelField(title="资产原值（元）", align=2, sort=90,value="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Length(min=0, max=200, message="部门名称长度必须介于 0 和 200 之间")
	@ExcelField(title="责任部门", align=2, sort=100,value="teamname")
	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	
	@Length(min=0, max=200, message="使用人名称长度必须介于 0 和 200 之间")
	@ExcelField(title="责任人", align=2, sort=110,value="usepeoplename")
	public String getUsepeoplename() {
		return usepeoplename;
	}

	public void setUsepeoplename(String usepeoplename) {
		this.usepeoplename = usepeoplename;
	}
	
	@Length(min=0, max=200, message="field3长度必须介于 0 和 200 之间")
	@ExcelField(title="原有责任人", align=2, sort=120,value="field3")
	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	@Length(min=0, max=200, message="field4长度必须介于 0 和 200 之间")
	@ExcelField(title="保管地点", align=2, sort=130,value="field4")
	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	@Length(min=0, max=200, message="field5长度必须介于 0 和 200 之间")
	@ExcelField(title="是否计量", align=2, sort=140,value="field5", dictType="is_leaf")
	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	@Length(min=0, max=200, message="field6长度必须介于 0 和 200 之间")
	@ExcelField(title="有效日期", align=2, sort=150,value="field6")
	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}
	
	@Length(min=0, max=100, message="设备状态长度必须介于 0 和 100 之间")
	@ExcelField(title="设备状态", align=2, sort=160,value="state", dictType="sb_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

	@Length(min=0, max=200, message="field7长度必须介于 0 和 200 之间")
	@ExcelField(title="使用率", align=2, sort=170,value="field7")
	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}
	
	@Length(min=0, max=200, message="field8长度必须介于 0 和 200 之间")
	@ExcelField(title="型号代号", align=2, sort=180,value="field8")
	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}
	
	@Length(min=0, max=200, message="资金来源长度必须介于 0 和 200 之间")
	@ExcelField(title="资金来源", align=2, sort=190,value="fundingsource")
	public String getFundingsource() {
		return fundingsource;
	}

	public void setFundingsource(String fundingsource) {
		this.fundingsource = fundingsource;
	}
	
	@Length(min=0, max=200, message="field9长度必须介于 0 和 200 之间")
	@ExcelField(title="备注", align=2, sort=200,value="field9")
	public String getField9() {
		return field9;
	}
	
	@ExcelField(title="设备类别", align=2, sort=190,value="sbTypeName")
	public String getSbTypeName() {
		return sbTypeName;
	}

	public void setSbTypeName(String sbTypeName) {
		this.sbTypeName = sbTypeName;
	}
	
	@ExcelField(title="资金类别", align=2, sort=190,value="fsTypeName")
	public String getFsTypeName() {
		return fsTypeName;
	}

	public void setFsTypeName(String fsTypeName) {
		this.fsTypeName = fsTypeName;
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
	
	
	
	
	@Length(min=0, max=200, message="设备类别长度必须介于 0 和 200 之间")
	public String getSbType() {
		return sbType;
	}

	public void setSbType(String sbType) {
		this.sbType = sbType;
	}
	
	@Length(min=0, max=200, message="资源类别长度必须介于 0 和 200 之间")
	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}
	
	
	
	
	
	
	
	

	
	@Length(min=0, max=200, message="设备种类长度必须介于 0 和 200 之间")

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	
	
	
	@Length(min=0, max=200, message="购买名称长度必须介于 0 和 200 之间")

	public String getBuyteam() {
		return buyteam;
	}

	public void setBuyteam(String buyteam) {
		this.buyteam = buyteam;
	}
	
	@Length(min=0, max=200, message="购买部门名称长度必须介于 0 和 200 之间")

	public String getBuyteamname() {
		return buyteamname;
	}

	public void setBuyteamname(String buyteamname) {
		this.buyteamname = buyteamname;
	}
	
	@Length(min=0, max=200, message="使用部门长度必须介于 0 和 200 之间")

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	
	@Length(min=0, max=200, message="使用人长度必须介于 0 和 200 之间")

	public String getUsepeople() {
		return usepeople;
	}

	public void setUsepeople(String usepeople) {
		this.usepeople = usepeople;
	}
	
	
	
	
	
	@Length(min=0, max=200, message="出厂日期长度必须介于 0 和 200 之间")

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	
	@Length(min=0, max=200, message="购买日期长度必须介于 0 和 200 之间")

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	
	
	@Length(min=0, max=200, message="启用日期长度必须介于 0 和 200 之间")
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
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

	public String getBstate() {
		return bstate;
	}

	public void setBstate(String bstate) {
		this.bstate = bstate;
	}

	

	
	
}