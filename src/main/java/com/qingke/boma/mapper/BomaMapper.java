package com.qingke.boma.mapper;

public interface BomaMapper <T> {
	
	//�޸�ĳһ����(�߼�ɾ�����߼��޸�)
	int update(T t);
	
	//����ĳһ����
	int insert(T t);
	
}
