package com.qingke.boma.service;

import java.util.List;

import com.qingke.boma.pojo.Job;

public interface JobService extends BasicService<Job> {

	// ͨ����λid���Ҹ�λ
	public Job getJobById(Integer id);

	// �������и�λ
	public List<Job> getAllJobs();

	// ͨ�������Ҹ�λ
	public Job getJobByName(String name);
}
