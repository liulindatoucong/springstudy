package web.webservice.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
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
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import web.webservice.service.WebServiceInterface;

public class NCInvoiceWebService implements WebServiceInterface {

	private final String DISPATCHBILLSERVICEURL = "http://10.10.180.21:58888/uapws/service/IsdExt";
	
	private final String HOST = "10.10.180.21";
	
	private final int PORT = 58888;
	
	private final String SERVICE_CODE = "DispatchBillService";
	
	private final String USERNAME = "sd";
	
	private final String PASSWORD = "sd";
	
	@Override
	public String getResponseInfo(Map<String, String> paramMap) {
		
		return null;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException, JDOMException
	{
//		Map<String, String> hashMap = new HashMap<String, String>();
//		hashMap.put("SITE", "1");
//		hashMap.put("STARTDATE", "2018-03-01");
//		hashMap.put("ENDDATE", "2018-09-30");
//		new NCInvoiceWebService().createHttpComponent(hashMap);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header><ns0:Urc xmlns:ns0=\"http://ws.uap.nc/lang\"><ns0:datasource>design</ns0:datasource><ns0:userCode>#UAP#</ns0:userCode><ns0:langCode>simpchn</ns0:langCode></ns0:Urc></soap:Header><soap:Body><ns1:executeResponse xmlns:ns1=\"http://itf.nc.sd/IsdExt\"><return>");
		sb.append("{\"data\":[{\"billtype\":null,\"unit\":\"个\",\"billrownostatus\":0,\"num\":210,\"meterialspec\":\"EV-7688190-4p1s(旧模组）\",\"site\":\"1\",\"outtype\":\"普通\",\"customername\":\"TOJO MOTORS CORPORATION\",\"customercode\":\"320019\",\"outdept\":\"国际贸易部\",\"billcode\":\"DN2018030100000001\",\"billdate\":\"2018-03-01 09:25:20\",\"meterialname\":\"7688190-4p1s-旧模组\",\"meterialcode\":\"103010056\",\"billstatus\":4,\"salebillcode\":\"WLD20180005-1\",\"rowno\":\"10\",\"outemployee\":\"曾暑文\"}]");
		sb.append("</return></ns1:executeResponse></soap:Body></soap:Envelope>");
		
		JSONObject jo = new NCInvoiceWebService().analysisXml(sb.toString());
	}
	
	/**
	 * 创建HTTP连接
	 * @author liulin
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @date 2018年9月14日 下午3:32:41
	 * @Description: TODO
	 */
	public void createHttpComponent(Map<String, String> paramMap) throws ClientProtocolException, IOException
	{
		HttpPost httpPost = new HttpPost(DISPATCHBILLSERVICEURL);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(HOST, PORT), new UsernamePasswordCredentials("sd", "sd"));
		CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
		httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
		StringEntity se = new StringEntity(createStringRequest(paramMap));
		httpPost.setEntity(se);
		
		CloseableHttpResponse response  = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();	
		String result = readInfoFromInputStream(entity.getContent());

		
	}
	
	public JSONObject analysisXml(String xmlResult) throws JDOMException, IOException
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
       Element data1 = bodyEt.getChild("ns1:executeResponse");
       Element data2 = data1.getChild("return");
       
       JSONObject jo = new JSONObject(data2.toString());
       return jo;
	}
	
	
    /**
     * 
     * @Description: 解析xml并返回根节点
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public Element getXmlElementNote(String xmlStr) throws JDOMException, IOException {
        // 创建一个新的字符串
        StringReader read = new StringReader(xmlStr);
        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        Element root = null;
        try {
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            // 取的根元素
            root = doc.getRootElement();
            // System.out.println(root.getContent());
        } catch (JDOMException jDomE) {
            throw jDomE;
        } catch (IOException iOE) {
            throw iOE;
        } finally {
            read.close();
        }
        return root;
    }

	
	
	/**
	 * 将返回的流对象转为需要的字符串
	 * @author liulin
	 * @date 2018年9月14日 下午4:58:14
	 * @Description: TODO
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private String readInfoFromInputStream(InputStream in) throws IOException
	{
		final int bufferSize = 1024;
		final char[] buffer = new char[bufferSize];
		final StringBuffer out = new StringBuffer();
		Reader reader = new InputStreamReader(in, "UTF-8");
		for (; ; ) {
		    int rsz = reader.read(buffer, 0, buffer.length);
		    if (rsz < 0)
		        break;
		    out.append(buffer, 0, rsz);
		}
		return out.toString();
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
