package com.qingke.boma.mapper;

import java.util.List;

import com.qingke.boma.pojo.Admin;

public interface AdminMapper extends BomaMapper<Admin>{

	// �����û������ҹ���Ա
	public Admin getAdminByUsername(String username);

	// �������й���Ա
	public List<Admin> getAllAdmin();

}
