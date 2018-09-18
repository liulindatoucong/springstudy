package web.service;

import java.util.List;

import web.mybatis.domain.BtsErpDelivery;

/**
 * 用来处理NC ERP的发货单数据的服务
 * @author liulin
 * @date 2018年9月17日 上午9:36:41
 * @Description: TODO
 */
public interface InvoiceInterface {
	
	/**
	 * 更新NC ERP的发货单数据
	 * @author liulin
	 * @date 2018年9月17日 上午9:37:08
	 * @Description: TODO
	 * @throws Exception
	 */
	public List<BtsErpDelivery> updateInvoiceInfoFromNc(String plant, String startDate, String endDate, String user) throws Exception;

}
