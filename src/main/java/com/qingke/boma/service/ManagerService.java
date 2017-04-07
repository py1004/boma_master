package com.qingke.boma.service;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Manager;

public interface ManagerService extends BasicService<Manager> {

	// ͨ����˾id��ȡ��������Ϣ
	public Manager getManagerByCompanyId(Integer id);

	// ��ȡ���и�������Ϣ
	public List<Manager> getAllManagers();
	
	//����Id��ȡ������
	public Manager getManagerById(Integer id) throws Exception;
	
	//��ȡ�����ǿڱ�ӡ֤�ĸ�����(��ҳ)
	public List<Manager> getAllManagersByPage(Map<String, Integer> mapPage);
	//��ȡ�����ǿڱ�ӡ֤�ĸ���������
	public Integer countIsPraise();
	
	//��ȡ���в��ǿڱ�ӡ֤�ĸ�����(��ҳ)
	public List<Manager> getAllManagerNotByPage(Map<String, Integer> mapPage);
	//��ȡ���в��ǿڱ����ߵĸ���������
	public Integer countNotPraise();
}
