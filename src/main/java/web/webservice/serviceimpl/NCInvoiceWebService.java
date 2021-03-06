package web.webservice.serviceimpl;

import java.io.IOException;
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
import org.jdom2.JDOMException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import web.webservice.service.WebServiceInterface;
import web.webservice.service.WebServiceUtils;

/**
 * 获取NC的电池包发货单信息
 * @author liulin
 * @date 2018年9月15日 上午9:14:47
 * @Description: TODO
 */
@Component
@Qualifier("ncinvoicewebservice")
public class NCInvoiceWebService extends WebServiceUtils implements WebServiceInterface {

	private final String DISPATCHBILLSERVICEURL = "http://10.10.180.21:58888/uapws/service/IsdExt";
	
	private final String HOST = "10.10.180.21";
	
	private final int PORT = 58888;
	
	private final String SERVICE_CODE = "DispatchBillService";
	
	private final String USERNAME = "sd";
	
	private final String PASSWORD = "sd";
	
	@Override
	public JSONObject getResponseInfo(Map<String, String> param) throws ClientProtocolException, IOException, JDOMException {
		JSONObject result = createHttpService(param);
		return result;
	}
	
//	public static void main(String[] args) throws ClientProtocolException, IOException, JDOMException {
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("SITE", "1");
//		param.put("STARTDATE", "2018-08-01");
//		param.put("ENDDATE", "2018-08-02");
//		JSONObject result = new NCInvoiceWebService().createHttpService(param);
//		System.out.println(result.toString());
//	}
	
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
		HttpPost httpPost = new HttpPost(DISPATCHBILLSERVICEURL);
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
	 * 创建HTTP请求字符串
	 * @author liulin
	 * @date 2018年9月14日 下午3:32:58
	 * @Description: TODO
	 * @return
	 */
	private String createStringRequest(Map<String, String> paramMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:isd=\"http://itf.nc.sd/IsdExt\">");
		sb.append("<soapenv:Header>");
		sb.append("<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soapenv:mustUnderstand=\"1\">");
		sb.append("<wsse:UsernameToken>");
		sb.append("<wsse:Username>");
		sb.append(USERNAME);
		sb.append("</wsse:Username>");
		sb.append("<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">");
		sb.append(PASSWORD);
        sb.append("</wsse:Password>");
        sb.append("</wsse:UsernameToken>");
        sb.append("</wsse:Security>");
        sb.append("</soapenv:Header>");
		sb.append("<soapenv:Body>");
		sb.append("<isd:execute>");
		sb.append("<string>").append(createKeyRequestByParam(paramMap)).append("</string>");
		sb.append("</isd:execute>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
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
		sb.append("{").append("'serviceCode':'").append(SERVICE_CODE).append("',")
		.append("'data':{'SITE':'").append(paramMap.get("SITE")).append("',")
		.append("'STARTDATE':'").append(paramMap.get("STARTDATE")).append("',")
		.append("'ENDDATE':").append(paramMap.get("ENDDATE")).append("'}}");
		return sb.toString();
	}

}
