package com.qingke.boma.service;

import java.util.List;

import com.qingke.boma.pojo.Trade;

public interface TradeService extends BasicService<Trade> {

	// ����������ҵ
	public List<Trade> getAllTrades();

	// ������ҵ���Ʋ�����ҵ
	public Trade getTradeByName(Trade trade);
	
	//������ҵid������ҵ
	public Trade getTradeById(Integer id);

}
