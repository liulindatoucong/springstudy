package web.mybatis.mapper;

import java.util.List;

import web.mybatis.domain.BtsErpDelivery;

public interface BtsErpDeliveryMapper 
{
	/**
	 * 判断发货单是否在数据库中已经存在
	 * @author liulin
	 * @date 2018年9月17日 下午4:33:38
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	public List<String> getExistsHandleInSystem(List<BtsErpDelivery> btsErpDelivery);
	
	/**
	 * 增加发货单数据
	 * @author liulin
	 * @date 2018年9月17日 下午4:41:28
	 * @Description: TODO
	 * @param btsErpDelivery
	 */
	public void insertBtsErpDelivery(List<BtsErpDelivery> btsErpDeliverys);
	
	/**
	 * 更新发货单数据为删除状态
	 * @author liulin
	 * @date 2018年9月17日 下午5:10:34
	 * @Description: TODO
	 * @param btsErpDeliverys
	 */
	public void updateBtsErpDeliveryStatus2delete(List<BtsErpDelivery> btsErpDeliverys);

}
