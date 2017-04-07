package com.qingke.boma.mapper;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Manager;

public interface ManagerMapper extends BomaMapper<Manager> {

	// ͨ����˾id��ȡ��������Ϣ
	public Manager getManagerByCompanyId(Integer id);

	// ��ȡ���и�������Ϣ
	public List<Manager> getAllManagers();

	// ����id��ȡ������
	public Manager getManagerById(Integer id) throws Exception;

	// ��ȡ�����ǿڱ�ӡ֤�ĸ�����(��ҳ)
	public List<Manager> getAllManagersByPage(Map<String, Integer> mapPage);
	// ��ȡ�����ǿڱ�ӡ֤�ĸ���������
	public Integer countIsPraise();
	
	// ��ȡ���в��ǿڱ�ӡ֤�ĸ�����(��ҳ)
	public List<Manager> getAllManagerNotByPage(Map<String, Integer> mapPage);
	// ��ȡ���в��ǿڱ�ӡ֤�ĸ���������
	public Integer countNotPraise();
	
	// ���ݹ�˾IDɾ��������
	// public int deleteManagersByCompanyId(Integer id) throws Exception;
}
