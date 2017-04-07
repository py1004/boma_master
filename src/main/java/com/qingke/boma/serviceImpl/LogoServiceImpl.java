package com.qingke.boma.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingke.boma.mapper.LogoMapper;
import com.qingke.boma.pojo.Logo;
import com.qingke.boma.service.LogoService;

@Service(value = "logoServiceImpl")
@Transactional
public class LogoServiceImpl implements LogoService {
	@Autowired
	private LogoMapper logoMapper;

	// ����logo
	@Override
	public int insert(Logo t) {
		return logoMapper.insert(t);
	}

	@Override
	public int update(Logo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	// ���ݹ�˾ID���Ҷ�Ӧ��ͼƬ
	// delete by wongya
}
