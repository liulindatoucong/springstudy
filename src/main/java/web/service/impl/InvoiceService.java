package web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import web.mybatis.domain.BtsErpDelivery;
import web.mybatis.mapper.BtsErpDeliveryMapper;
import web.service.InvoiceInterface;
import web.webservice.service.WebServiceInterface;

@Component
public class InvoiceService implements InvoiceInterface {

	@Autowired
	@Qualifier("ncinvoicewebservice")
	private WebServiceInterface ncWebInterface;
	
	@Autowired
	private BtsErpDeliveryMapper erpDeliveryMapper;
	/**
	 * 发货单删除，单据号行项目删除
	 */
	private final String NCDELETE = "1";
	
	private final String NEW = "10";
	
	private final String DELETE = "30";
	
	private final String DATEFORMAT = "yyyy-MM-dd";
	
	private final String SUCCESS = "S";
	
	@Transactional
	@Override
	public List<BtsErpDelivery> updateInvoiceInfoFromNc(String plant, String startDate, String endDate, String user) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("SITE", plant);
		param.put("STARTDATE", startDate);
		param.put("ENDDATE", endDate);
		JSONObject data = ncWebInterface.getResponseInfo(param);
		List<BtsErpDelivery> resultList = new ArrayList<BtsErpDelivery>();
		if(SUCCESS.equals(data.getString("status")))
		{
			JSONArray jsonArray = data.getJSONArray("data");
			List<BtsErpDelivery> existsDeliverys = new ArrayList<BtsErpDelivery>();
			List<BtsErpDelivery> deleteDeliverys = new ArrayList<BtsErpDelivery>();
			splitDeliverys(jsonArray, existsDeliverys, deleteDeliverys, user);
			if(existsDeliverys.size()!=0)
			{
				addDeliverys2DataBase(existsDeliverys);
			}
			if(deleteDeliverys.size()!=0)
			{
				deleteDeliverys = updateBtsErpDeliveryStatus2delete(deleteDeliverys);
			}
			resultList.addAll(existsDeliverys);
			resultList.addAll(deleteDeliverys);
		}
		else
		{
			throw new Exception(data.getString("msg"));
		}
		return resultList;

	}
	
	/**
	 * 新增NC发货单到数据库
	 * @author liulin
	 * @date 2018年9月17日 下午5:35:48
	 * @Description: TODO
	 * @param existsDeliverys
	 */
	private void addDeliverys2DataBase(List<BtsErpDelivery> existsDeliverys)
	{
		List<String> existsInDatabaseHandles = erpDeliveryMapper.getExistsHandleInSystem(existsDeliverys);
		List<BtsErpDelivery> existsInDatabase = new ArrayList<BtsErpDelivery>();
		for(BtsErpDelivery existOne:existsDeliverys)
		{
			String handle = existOne.getPlant()+existOne.getVbillcode()+existOne.getVbillrowno();
			if(existsInDatabaseHandles.contains(handle))
			{
				existsInDatabase.add(existOne);
			}
		}
		existsDeliverys.removeAll(existsInDatabase);
		erpDeliveryMapper.insertBtsErpDelivery(existsDeliverys);
	}
	
	/**
	 * 更新发货单数据为删除状态
	 * @author liulin
	 * @date 2018年9月17日 下午5:39:05
	 * @Description: TODO
	 * @param deleteDeliverys
	 */
	private List<BtsErpDelivery> updateBtsErpDeliveryStatus2delete(List<BtsErpDelivery> deleteDeliverys) {
		List<String> deletesInDatabaseHandles = erpDeliveryMapper.getExistsHandleInSystem(deleteDeliverys);
		List<BtsErpDelivery> deleteExistsInDatabase = new ArrayList<BtsErpDelivery>();
		for(BtsErpDelivery deleteExistOne:deleteDeliverys)
		{
			String handle = deleteExistOne.getPlant()+deleteExistOne.getVbillcode()+deleteExistOne.getVbillrowno();
			if(deletesInDatabaseHandles.contains(handle))
			{
				deleteExistsInDatabase.add(deleteExistOne);
			}
		}
		if(deleteExistsInDatabase.size() > 0)
		{
			erpDeliveryMapper.updateBtsErpDeliveryStatus2delete(deleteExistsInDatabase);
		}
		return deleteExistsInDatabase;

	}
	
	/**
	 * 将发货单分成删除和存在两类
	 * @author liulin
	 * @date 2018年9月17日 上午11:08:50
	 * @Description: TODO
	 * @param dataArray
	 * @param existsDeliverys
	 * @param deleteDeliverys
	 * @throws ParseException 
	 */
	private void splitDeliverys(JSONArray dataArray, List<BtsErpDelivery> existsDeliverys, List<BtsErpDelivery> deleteDeliverys, String user) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		for(Object oneDelivery:dataArray)
		{
			JSONObject jo = (JSONObject)oneDelivery;
			String billstatus = String.valueOf(jo.getInt("billstatus"));
			String billrownostatus = String.valueOf(jo.getInt("billrownostatus"));
			String billcode = jo.getString("billcode");
			String rowno = jo.getString("rowno");
			String site = jo.getString("site");
			BtsErpDelivery delivery = new BtsErpDelivery();
			delivery.setVbillcode(billcode);
			delivery.setVbillrowno(rowno);
			delivery.setPlant(site);
			String meterialcode = jo.getString("meterialcode");
			String meterialspec = String.valueOf(jo.get("meterialspec"));
			String outemployee = jo.getString("outemployee");
			String salebillcode = jo.getString("salebillcode");
			String num = String.valueOf(jo.getInt("num"));
			String unit = jo.getString("unit");
			String meterialname = jo.getString("meterialname");
			String billtype = String.valueOf(jo.get("billtype"));
			String billdate = jo.getString("billdate");
			String customercode = jo.getString("customercode");
			String customername = jo.getString("customername");
			String outtype = jo.getString("outtype");
			String outdept = jo.getString("outdept");
			delivery.setBilltypename(billtype);
			delivery.setCreatetime(new Date());
			delivery.setCreateuser(user);
			delivery.setCuscode(customercode);
			delivery.setCusdescription(customername);
			delivery.setMatdescription(meterialname);
			delivery.setMaterial(meterialcode);
			delivery.setMatspec(meterialspec);
			delivery.setVbilldate(sdf.parse(billdate));
			delivery.setVbillddname(outdept);
			delivery.setVbilldnum(num);
			delivery.setVbilldpname(outemployee);
			delivery.setVbilldtype(outtype);
			delivery.setVbillduname(unit);
			delivery.setVbillrowno(rowno);
			delivery.setVbillvdef2(salebillcode);
			if(NCDELETE.equals(billstatus)||NCDELETE.equals(billrownostatus))
			{
				delivery.setStatus(DELETE);
				deleteDeliverys.add(delivery);
			}
			else
			{
				delivery.setStatus(NEW);
				existsDeliverys.add(delivery);
			}
		}
	}

	@Override
	public String getDeliverysByPlantAndStr(String plant, String deliveryStr) throws Exception {
		StringBuffer sb = new StringBuffer();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("plant", plant);
		paramMap.put("delivery", deliveryStr);
		List<BtsErpDelivery> deliverys =  erpDeliveryMapper.getDeliverysByPlantAndStr(paramMap);
		sb.append("[");
		for(int i = 0; i < deliverys.size(); i ++)
		{
			String vbillcode = deliverys.get(i).getVbillcode();
			String rowNum = deliverys.get(i).getVbillrowno();
			String materail = deliverys.get(i).getMaterial();
			String materailDesc = deliverys.get(i).getMatdescription();
			String cusCode = deliverys.get(i).getCuscode();
			String cusDesc = deliverys.get(i).getCusdescription();
			String vbilldnum =  deliverys.get(i).getVbilldnum();
			String deliverynum = deliverys.get(i).getDeliverynum();
			sb.append("{\"id\":").append((i+1)).append(",")
			.append("\"vbillcode\":\"").append(vbillcode).append("\",")
			.append("\"rowNum\":\"").append(rowNum).append("\",")
			.append("\"materail\":\"").append(materail).append("\",")
			.append("\"materailDesc\":\"").append(materailDesc).append("\",")
			.append("\"cusCode\":\"").append(cusCode).append("\",")
			.append("\"cusDesc\":\"").append(cusDesc).append("\",")
			.append("\"vbilldnum\":\"").append(vbilldnum == null?"":vbilldnum).append("\",")
			.append("\"deliverynum\":\"").append(deliverynum == null?"":deliverynum).append("\"},");
			if(i == deliverys.size() - 1)
			{
				sb.deleteCharAt(sb.length()-1);
			}
		}
		sb.append("]");
		return sb.toString();
	}
	

}
