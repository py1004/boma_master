package com.qingke.boma.service;

import java.util.List;

import com.qingke.boma.pojo.Job;
import com.qingke.boma.pojo.TradeJob;

public interface TradeJobService extends BasicService<TradeJob> {

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

	// ���ҷǸ���ҵ�ĸ�λ
	public List<Job> getOtherJobs(Integer id);

	// ��������isShow
	public List<TradeJob> getIsShow();
}
