package com.qingke.boma.pojo;

import java.util.List;

/**
 * 
 * @time ����4:09:56
 * @Description ��ҵ, ���ڳɹ�����, һ����ҵ�ж����˾�������λ
 */
public class Trade{
	private Integer id;
	// ��ҵ����
	private String name;
	// ӵ�еĹ�˾
	private List<Company> companys;
	// ӵ�е�ְλ
	private List<Job> jobs;

	public Trade() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Company> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

}
