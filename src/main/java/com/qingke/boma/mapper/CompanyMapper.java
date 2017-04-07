package com.qingke.boma.mapper;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Company;
import com.qingke.boma.pojo.Trade;

public interface CompanyMapper extends BomaMapper<Company> {

	// ��ȡ���гɹ�������˾
	public List<Company> getCaseCompanies();

	// ����id��ȡ��˾
	public Company getCompanyById(Integer id);

	// ��ȡ����ĳ��ҵ�ĳɹ������Ĺ�˾
	public List<Company> getCaseCompanyByTrade(Trade trade);
	
	//��ȡĳ��ҵ�ǳɹ�������˾
	public List<Company> getUnCaseCompanyByTrade(Trade trade);

	// ���ݹ�˾���ƻ�ȡ��˾
	public Company getCompanyByName(String name);

	// ��ȡ���й�˾(��)
	public List<Company> getCompanies();
	
	//����IDɾ����˾
	///public int deleteCompanyById(Integer id);
	
	//��ȡ���й�˾(��ҳ)
	public List<Company> getCompaniesByPage(Map<String, Integer> mapPage);
	
	//��ȡ��˾��Ϣ��������
	public Integer countAllCompanies();
}
