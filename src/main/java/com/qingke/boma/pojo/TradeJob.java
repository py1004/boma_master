package com.qingke.boma.pojo;

/**
 * 
 * @Description ��ҵ���λ���м�����ڹ�����ҵ�µĸ�λ���Ƿ���ҳ��չʾ
 */
public class TradeJob {

	private Integer id;
	// ��ҵ
	private Trade trade;
	// ��λ
	private Job job;
	// �Ƿ��ڳɹ�������չʾ
	private String isShow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}
