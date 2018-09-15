package web.webservice.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.jdom2.JDOMException;
import org.json.JSONObject;

public interface WebServiceInterface {
	
	/**
	 * 获取返回json对象结果
	 * @author liulin
	 * @date 2018年9月15日 上午9:13:42
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public JSONObject getResponseInfo(Map<String, String> paramMap) throws ClientProtocolException, IOException, JDOMException;
}
