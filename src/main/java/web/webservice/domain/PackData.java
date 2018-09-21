package web.webservice.domain;

/**
 * MES系统电池包数据
 * @author liulin
 * @date 2018年9月21日 下午2:34:36
 * @Description: TODO
 */
public class PackData {
	//物料编码
	private String itemCode;
	//物料描述
	private String itemDesc;
	//物料版本
	private String version;
	//生产订单号
	private String orderNum;
	//条码号
	private String barCode;
	//电池包理论数量
	private String packTheoryNum;
	//电池包实际数量
	private String packActualNum;
	//模块码理论数量
	private String moduleTheoryNum;
	//模块码实际数量
	private String moduleActualNum;
	//电芯理论数量
	private String batteryTheoryNum;
	//电芯实际数量
	private String batteryActualNum;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getPackTheoryNum() {
		return packTheoryNum;
	}

	public void setPackTheoryNum(String packTheoryNum) {
		this.packTheoryNum = packTheoryNum;
	}

	public String getPackActualNum() {
		return packActualNum;
	}

	public void setPackActualNum(String packActualNum) {
		this.packActualNum = packActualNum;
	}

	public String getModuleTheoryNum() {
		return moduleTheoryNum;
	}

	public void setModuleTheoryNum(String moduleTheoryNum) {
		this.moduleTheoryNum = moduleTheoryNum;
	}

	public String getModuleActualNum() {
		return moduleActualNum;
	}

	public void setModuleActualNum(String moduleActualNum) {
		this.moduleActualNum = moduleActualNum;
	}

	public String getBatteryTheoryNum() {
		return batteryTheoryNum;
	}

	public void setBatteryTheoryNum(String batteryTheoryNum) {
		this.batteryTheoryNum = batteryTheoryNum;
	}

	public String getBatteryActualNum() {
		return batteryActualNum;
	}

	public void setBatteryActualNum(String batteryActualNum) {
		this.batteryActualNum = batteryActualNum;
	}
	
	
}
