package com.qingke.boma.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingke.boma.mapper.ManagerMapper;
import com.qingke.boma.pojo.Manager;
import com.qingke.boma.service.ManagerService;

@Service(value = "managerServiceImpl")
@Transactional
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerMapper managerMapper;

	// ��Ӹ�����
	@Override
	public int insert(Manager t) {
		return managerMapper.insert(t);
	}

	// ���¸�����
	@Override
	public int update(Manager t) {
		return managerMapper.update(t);
	}

	@Override
	public Manager getManagerByCompanyId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	// ������и�����
	@Override
	public List<Manager> getAllManagers() {
		// ��ȡ���и�����
		List<Manager> managers = managerMapper.getAllManagers();
		return managers;
	}

	// ����Id��ȡ������
	public Manager getManagerById(Integer id) throws Exception {
		return managerMapper.getManagerById(id);
	}
	
	//��ȡ�����ǿڱ�ӡ֤�ĸ�������Ϣ(��ҳ)
	@Override
	public List<Manager> getAllManagersByPage(Map<String, Integer> mapPage) {
		return managerMapper.getAllManagersByPage(mapPage);
	}
	
	//��ȡ�����ǿڱ�ӡ֤���ܸ���
	@Override
	public Integer countIsPraise() {
		return managerMapper.countIsPraise();
	}

	//��ȡ���в��ǿڱ�ӡ֤�ĸ�������Ϣ
	@Override
	public List<Manager> getAllManagerNotByPage(Map<String, Integer> mapPage) {
		return managerMapper.getAllManagerNotByPage(mapPage);
	}
	
	//��ȡ���ǿڱ�ӡ֤���ܸ���
	@Override
	public Integer countNotPraise() {
		// TODO Auto-generated method stub
		return managerMapper.countNotPraise();
	}
}
