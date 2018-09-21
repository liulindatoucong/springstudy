package web.webservice.serviceimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.json.JSONObject;	
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import web.webservice.service.WebServiceInterface;
import web.webservice.service.WebServiceUtils;

/**
 * 
 * @author liulin
 * @date 2018年9月20日 下午1:43:29
 * @Description: TODO
 */
@Component
@Qualifier("meserpoutlinewebservice")
public class MESErpOutlineWebService extends WebServiceUtils implements WebServiceInterface {

	private final String ERPOUTLINESERVICEURL = "http://10.10.156.11:50000/sapdevwebservice/ExecutingServiceService";
	
	private final String HOST = "10.10.156.11";
	
	private final int PORT = 50000;
	
	private final String SERVICE_CODE = "GetNeedTransferSfcOfPackService";
	
	private final String USERNAME = "liulin";
	
	private final String PASSWORD = "sap12345";

	@Override
	public JSONObject getResponseInfo(Map<String, String> param)
			throws ClientProtocolException, IOException, JDOMException {
		JSONObject result = createHttpService(param);
		return result;
	}

	
	/**
	 * 创建HTTP连接并实行服务
	 * @author liulin
	 * @date 2018年9月15日 上午8:58:58
	 * @Description: TODO
	 * @param paramMap
	 * @return 解析结果
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public JSONObject createHttpService(Map<String, String> paramMap) throws ClientProtocolException, IOException, JDOMException
	{
		HttpPost httpPost = new HttpPost(ERPOUTLINESERVICEURL);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(HOST, PORT), new UsernamePasswordCredentials(USERNAME, PASSWORD));
		CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
		httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
		StringEntity se = new StringEntity(createStringRequest(paramMap));
		httpPost.setEntity(se);
		CloseableHttpResponse response  = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();	
		String resultStr = readInfoFromInputStream(entity.getContent());
		JSONObject returnJson = analysisXml(resultStr);
		return returnJson;
	}
	
	
	/**
	 * 解析返回结果
	 * @author liulin
	 * @date 2018年9月15日 上午8:54:15
	 * @Description: TODO
	 * @param xmlResult
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@Override
	protected JSONObject analysisXml(String xmlResult) throws JDOMException, IOException
	{
		// 解析获取所有子节点
       
	    List<Element> envelopeChildNote = getXmlElementNote(xmlResult).getChildren();
		Element bodyEt = null;
	    for (int i = 0; i < envelopeChildNote.size(); i++) {
	        bodyEt = (Element) envelopeChildNote.get(i);// 循环依次得到子元素
	        if (bodyEt.getName().equals("Body")) {
	            break;
	        }
	    }
        String body = bodyEt.getValue();
        //去除掉其它数据，只留下data数据
        body = body.substring(body.indexOf("{"), body.length());
        JSONObject jo = new JSONObject(body);
        return jo;
	}
	

	/**
	 * 创建HTTP请求字符串
	 * @author liulin
	 * @date 2018年9月14日 下午3:32:58
	 * @Description: TODO
	 * @return
	 */
	private String createStringRequest(Map<String, String> paramMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sb.append("<SOAP-ENV:Body>");
		sb.append("<yq1:execute xmlns:yq1=\"http://base.ws.sapdev.com/\">");
		sb.append("<pRequest>");
		sb.append(createKeyRequestByParam(paramMap));
		sb.append("</pRequest>");
		sb.append("</yq1:execute>");
		sb.append("</SOAP-ENV:Body>");
		sb.append("</SOAP-ENV:Envelope>");
		return sb.toString();
	}
	
	/**
	 * 拼接关键的信息
	 * @author liulin
	 * @date 2018年9月14日 下午4:12:50
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	private String createKeyRequestByParam(Map<String, String> paramMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<site>").append(paramMap.get("SITE")).append("</site>")
		.append("<data>").append(paramMap.get("ITEMS")).append("</data>")
		.append("<serviceCode>").append(SERVICE_CODE).append("</serviceCode>");
		return sb.toString();
	}

}
