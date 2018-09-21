package web.controller.upload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.service.InvoiceInterface;
import web.service.PackInfoInterface;
import web.service.comm.ResultInfo;

@Controller
@RequestMapping({"/uploadInfo"})
public class UploadController {
	
	@Autowired
	private InvoiceInterface invoiceService;
	
	@Autowired
	private PackInfoInterface packInfoService;

	@RequestMapping(value="/invoiceupload",method=RequestMethod.GET)
	public String invoiceUpload()
	{
		return "/upload/invoiceuploadpage";
	}
	
	@ResponseBody
	@RequestMapping(value="/getdeliverystable",method=RequestMethod.POST)
	public Map<String, Object> getDeliverysTable(HttpServletRequest request)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String plant = request.getParameter("plant");
			plant = "1";
			String delivery = request.getParameter("delivery");
			result.put("data", invoiceService.getDeliverysByPlantAndStr(plant, delivery));
			result.put("status", ResultInfo.SUCCESS.getValue());
			result.put("msg", "");
		} catch (Exception e) {
			result.put("status", ResultInfo.FAIL.getValue());
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getNoSendBtsPack",method=RequestMethod.POST)
	public Map<String, Object> getNoSendBtsPack(HttpServletRequest request)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<String> items = new ArrayList<String>();
			String plant = request.getParameter("plant");
			String itemCode = request.getParameter("itemCode");
			plant = "3002";
			itemCode = "103010360";
			items.add(itemCode);
			result.put("data", packInfoService.getNoSendBtsPackFromWeb(plant, items));
			result.put("status", ResultInfo.SUCCESS.getValue());
			result.put("msg", "");
		} catch (Exception e) {
			result.put("status", ResultInfo.FAIL.getValue());
			result.put("msg", e.getMessage());
		}
		return result;
	}
}
