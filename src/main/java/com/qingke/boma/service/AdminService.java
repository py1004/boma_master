package com.qingke.boma.service;

import java.util.List;

import com.qingke.boma.pojo.Admin;

public interface AdminService extends BasicService<Admin>{
	
	//�����û������ҹ���Ա
	public Admin getAdminByUsername(String username);
	
	//�������й���Ա
	public List<Admin> getAllAdmin();

}
