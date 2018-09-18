package web.controller.synchdata;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.service.InvoiceInterface;

@Controller
@RequestMapping({"/synchdataWeb"})
public class SynchdataController {
	
	private String defaultUser= "liulin";
	
	private final String SUCCESS = "S";
	
	private final String FAULT = "F";
	
	@Autowired
	private InvoiceInterface invoiceService;
	
	@RequestMapping(value="/invoice",method=RequestMethod.GET)
	public String synchInvoice()
	{
		return "/synchdata/invoice";
	}

	@ResponseBody
	@RequestMapping(value="/invoice/getDataList",method=RequestMethod.POST)
	public Map<String, Object> getDataList(HttpServletRequest request)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String plant = request.getParameter("plant");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			result.put("data", invoiceService.updateInvoiceInfoFromNc(plant, startDate, endDate, defaultUser));
			result.put("status", SUCCESS);
			result.put("msg", "");
		} catch (Exception e) {
			result.put("status", FAULT);
			result.put("msg", e.getMessage());
		}
		return result;
	}

}
