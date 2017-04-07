package com.qingke.boma.service;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Company;
import com.qingke.boma.pojo.Trade;

public interface CompanyService extends BasicService<Company> {

	// ��ȡ���й�˾
	public List<Company> getAllCompanies();

	// ��ȡ���гɹ�������˾
	public List<Company> getCaseCompanies();

	// ����id��ȡ��˾
	public Company getCompanyById(Integer id);

	// ��ȡ����ĳ��ҵ�ĳɹ������Ĺ�˾
	public List<Company> getCaseCompanyByTrade(Trade trade);

	// ��ȡ����ĳ��ҵ�ĳɹ������Ĺ�˾
	public List<Company> getUnCaseCompanyByTrade(Trade trade);

	// ���ݹ�˾���ƻ�ȡ��˾
	public Company getCompanyByName(String name);
	
	//��ȡ���й�˾��ҳ
	public List<Company> getCompaniesByPage(Map<String, Integer> mapPage);
	
	//��ȡ��˾��Ϣ������
	public Integer countAllCompanies();
}
