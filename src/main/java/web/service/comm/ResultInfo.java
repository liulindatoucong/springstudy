package web.service.comm;

/**
 * 返回信息成功失败之类的信息
 * @author liulin
 * @date 2018年9月20日 下午4:12:17
 * @Description: TODO
 */
public enum ResultInfo {
	SUCCESS("S"), FAIL("F");
	private String value;
	
	private ResultInfo(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
