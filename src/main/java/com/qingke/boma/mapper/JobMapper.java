package com.qingke.boma.mapper;

import java.util.List;

import com.qingke.boma.pojo.Job;

public interface JobMapper extends BomaMapper<Job> {

	// ͨ����λid���Ҹ�λ
	public Job getJobById(Integer id);
	// �������и�λ
	public List<Job> getAllJobs();

}
