package com.qingke.boma.service;

public interface BasicService <T>{
	//���ʵ����Ϣ
	int insert(T t);
	//����ʵ����Ϣ
	int update(T t);
}
