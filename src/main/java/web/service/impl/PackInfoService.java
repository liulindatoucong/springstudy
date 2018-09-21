package web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import web.service.PackInfoInterface;
import web.webservice.domain.PackData;
import web.webservice.service.WebServiceInterface;

@Component
public class PackInfoService implements PackInfoInterface {

	@Autowired
	@Qualifier("meserpoutlinewebservice")
	private WebServiceInterface webWebInterface;
	
	@Override
	public List<PackData> getNoSendBtsPackFromWeb(String plant, List<String> items) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("SITE", plant);
		paramMap.put("ITEMS", createItems(items));
		JSONObject resultJo = webWebInterface.getResponseInfo(paramMap);
		JSONArray joArray = resultJo.getJSONArray("SFC_LIST");
		List<PackData> packDataList = new ArrayList<PackData>();
		for(Object obj:joArray)
		{
			JSONObject jsonObj = (JSONObject)obj;
			PackData packData = new PackData();
			packData.setOrderNum(jsonObj.getString("SHOP_ORDER"));
			packData.setBarCode(jsonObj.getString("SFC"));
			packData.setItemCode(jsonObj.getString("ITEM"));
			packData.setItemDesc(jsonObj.getString("ITEM_DESC"));
			packData.setVersion(jsonObj.getString("ITEM_TYPE"));
			packData.setPackTheoryNum(jsonObj.getString("PACK_REQ_QTY"));
			packData.setPackActualNum(jsonObj.getString("PACK_ASSY_QTY"));
			packData.setModuleTheoryNum(jsonObj.getString("MODULE_REQ_QTY"));
			packData.setModuleActualNum(jsonObj.getString("MODULE_ASSY_QTY"));
			packData.setBatteryTheoryNum(jsonObj.getString("CELL_REQ_QTY"));
			packData.setBatteryActualNum(jsonObj.getString("CELL_ASSY_QTY"));
			packDataList.add(packData);
		}
		return packDataList;
		
	}
	
	
	/**
	 * 
	 * @author liulin
	 * @date 2018年9月21日 下午2:56:59
	 * @Description: TODO
	 * @param items
	 * @return
	 */
	private String createItems(List<String> items)
	{
		StringBuffer sb = new StringBuffer("{\"ITEM_LIST\":[");
		for(int i = 0; i < items.size(); i ++)
		{
			sb.append("{\"ITEM\":\"").append(items.get(i)).append("\"},");
			if(i == items.size() - 1)
			{
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		sb.append("]}");
		return sb.toString();
	}

}
