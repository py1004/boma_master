package com.qingke.boma.pojo;

/**
 * 
 * @time ����4:03:24
 * @Description ��Ƹ
 */
public class Recruit {
	private Integer id;
	//��˾���
	private String description;
	// ��ƸҪ��
	private String requirement;
	// ��н
	private String salary;
	// ������ַ
	private String address;
	// ��Ƹ����
	private String title;
	// ��Ƹ��˾
	private Company company;
	//�Ƿ���ʾ
	private String isShow;

	public Recruit() {
		super();
		isShow = "y";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}
