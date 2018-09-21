package web.webservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;
import org.xml.sax.InputSource;

/**
 * WebService工具类
 * @author liulin
 * @date 2018年9月20日 上午11:29:36
 * @Description: TODO
 */
public class WebServiceUtils {
	/**
	 * 将返回的流对象转为需要的字符串
	 * @author liulin
	 * @date 2018年9月14日 下午4:58:14
	 * @Description: TODO
	 * @param in
	 * @return
	 * @throws IOException
	 */
	protected String readInfoFromInputStream(InputStream in) throws IOException
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
	 * 解析返回结果
	 * @author liulin
	 * @date 2018年9月15日 上午8:54:15
	 * @Description: TODO
	 * @param xmlResult
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
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
       
        JSONObject jo = new JSONObject(body);
        return jo;
	}
	
	
	/**
     * 
     * @Description: 解析xml并返回根节点
     * @param xmlStr
     * @return
     * @throws Exception
     */
	protected Element getXmlElementNote(String xmlStr) throws JDOMException, IOException {
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
}
