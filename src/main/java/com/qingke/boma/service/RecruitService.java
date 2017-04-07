package com.qingke.boma.service;

import java.util.List;

import com.qingke.boma.pojo.Page;
import com.qingke.boma.pojo.Recruit;

public interface RecruitService extends BasicService<Recruit> {

	// ����������Ƹ��Ϣ
	public List<Recruit> getAllRecruits();

	// ����id����ɾ����Ƹ��Ϣ
	public int deleteRecruit(Recruit recruit);

	// ����id������Ƹ��Ϣ
	public Recruit getRecruitById(Integer id);

	// ����������ʾ����Ƹ��Ϣ
	public List<Recruit> getIsShowRecruits(Page page);

}
