package web.webservice.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.jdom2.JDOMException;
import org.json.JSONObject;

public interface WebServiceInterface {
	
	/**
	 * 获取返回json对象结果
	 * @author liulin
	 * @date 2018年9月17日 上午9:52:04
	 * @Description: TODO
	 * @param plant
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public JSONObject getResponseInfo(String plant, String startDate, String endDate) throws ClientProtocolException, IOException, JDOMException;
}
