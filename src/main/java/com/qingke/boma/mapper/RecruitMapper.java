package com.qingke.boma.mapper;

import java.util.List;

import com.qingke.boma.pojo.Page;
import com.qingke.boma.pojo.Recruit;

public interface RecruitMapper extends BomaMapper<Recruit> {

	// ����������ʾ����Ƹ��Ϣ
	public List<Recruit> getAllRecruits();

	// ����idɾ����Ƹ��Ϣ(��ɾ��)
	public int deleteRecruit(Recruit recruit);
	 
	// ����id������Ƹ��Ϣ
	public Recruit getRecruitById(Integer id);
	
	// ��ҳ�������ʾ����Ƹ��Ϣ
	public List<Recruit> getIsShowRecruits(Page page);

}
