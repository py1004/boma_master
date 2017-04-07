package com.qingke.boma.mapper;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Trade;

public interface TradeMapper extends BomaMapper<Trade> {

	// ����������ҵ
	public List<Trade> getAllTrades();

	// ������ҵ���Ʋ�����ҵ
	public Trade getTradeByName(Trade trade);

	// ������ҵid������ҵ
	public Trade getTradeById(Integer id);

	// limitselect
	public List<Trade> limitSelect(Map<String , Integer> map);
}
