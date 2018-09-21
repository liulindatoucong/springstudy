package web.service;

import java.util.List;

import web.webservice.domain.PackData;

public interface PackInfoInterface {
	
	/**
	 * 获取没有发送到BTS的电池包信息
	 * @author liulin
	 * @date 2018年9月21日 下午4:22:55
	 * @Description: TODO
	 * @param plant
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public List<PackData> getNoSendBtsPackFromWeb(String plant, List<String> items) throws Exception;
}
