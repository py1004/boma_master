package com.qingke.boma.service;

import java.util.List;
import java.util.Map;

import com.qingke.boma.pojo.Trade;

public interface CaseService {
	// �õ���������ʾ����ҵ
	public List<Map> getCaseMap() throws Exception;

	// limitselect
	public List<Map> getSelectMap(Map<String, Integer> map) throws Exception;
}