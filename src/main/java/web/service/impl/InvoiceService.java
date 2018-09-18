package web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		JSONObject data = ncWebInterface.getResponseInfo(plant, startDate, endDate);
		List<BtsErpDelivery> resultList = new ArrayList<BtsErpDelivery>();
		if(SUCCESS.equals(data.getString("status")))
		{
			JSONArray jsonArray = data.getJSONArray("data");
			List<BtsErpDelivery> existsDeliverys = new ArrayList<BtsErpDelivery>();
			List<BtsErpDelivery> deleteDeliverys = new ArrayList<BtsErpDelivery>();
			splitDeliverys(jsonArray, existsDeliverys, deleteDeliverys, user);
			addDeliverys2DataBase(existsDeliverys);
			updateBtsErpDeliveryStatus2delete(deleteDeliverys);
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
	private void updateBtsErpDeliveryStatus2delete(List<BtsErpDelivery> deleteDeliverys) {
		erpDeliveryMapper.updateBtsErpDeliveryStatus2delete(deleteDeliverys);
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
			String billstatus = jo.getString("billstatus");
			String billrownostatus = jo.getString("billrownostatus");
			String billcode = jo.getString("billcode");
			String rowno = jo.getString("rowno");
			String site = jo.getString("site");
			BtsErpDelivery delivery = new BtsErpDelivery();
			delivery.setVbillcode(billcode);
			delivery.setVbillrowno(rowno);
			delivery.setPlant(site);
			String meterialcode = jo.getString("meterialcode");
			String meterialspec = jo.getString("meterialspec");
			String outemployee = jo.getString("outemployee");
			String salebillcode = jo.getString("salebillcode");
			String num = jo.getString("num");
			String unit = jo.getString("unit");
			String meterialname = jo.getString("meterialname");
			String billtype = jo.getString("billtype");
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
				delivery.setStatus(NEW);
				deleteDeliverys.add(delivery);
			}
			else
			{
				delivery.setStatus(DELETE);
				deleteDeliverys.add(delivery);
			}
		}
	}
	

}
