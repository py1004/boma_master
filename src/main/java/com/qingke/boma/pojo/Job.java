package com.qingke.boma.pojo;

/**
 * @time ����4:11:01
 * @Description ��λ
 */
public class Job {
	private Integer id;
	// ��λ����
	private String name;

	public Job() {
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Job) {
			if (((Job) obj).getName().equals(this.getName())) {
				return true;
			}
		}
		return false;
	}
}
