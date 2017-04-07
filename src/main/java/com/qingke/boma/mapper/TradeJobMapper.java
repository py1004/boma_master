package com.qingke.boma.mapper;

import java.util.List;

import com.qingke.boma.pojo.TradeJob;

public interface TradeJobMapper extends BomaMapper<TradeJob> {

	// ����ĳ��ҵ�µ����и�λ
	public List<TradeJob> getTradeJobs(Integer id);

	// ����ĳ��ҵ�µ�����չʾ�ķ����λ
	public List<TradeJob> getIsShowTradeJobs(Integer id);

	// ����ĳ��ҵ�µ����в�չʾ�ķ����λ
	public List<TradeJob> getUnShowTradeJobs(Integer id);

	// ����id��tradejob
	public TradeJob getTradeJobById(Integer id);

	// ͨ����ҵid�͸�λid�ҵ�tradejob
	public TradeJob getTradeJobByTwoIds(Integer tId, Integer jId);
	
	//��������isShow
	public List<TradeJob> getIsShow();
}
