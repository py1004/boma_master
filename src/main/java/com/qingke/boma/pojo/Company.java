package com.qingke.boma.pojo;

/**
 * 
 * @time ����4:02:54
 * @Description ��˾
 */
public class Company {
	private Integer id;
	// ��˾��
	private String name;
	// ��˾���
	private String description;
	// ������ҵ
	private Trade trade;
	// �Ƿ��ǳɹ�����
	private String isCase;
	// �Ƿ�����ܹ�˾
	private Company parentCompany;
	// ��˾��Ӧ��log
	private Logo logo;

	public Company() {
		super();
		isCase = "n";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public String getIsCase() {
		return isCase;
	}

	public void setIsCase(String isCase) {
		this.isCase = isCase;
	}

	public Company getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(Company parentCompany) {
		this.parentCompany = parentCompany;
	}

	public Logo getLogo() {
		return logo;
	}

	public void setLogo(Logo logo) {
		this.logo = logo;
	}

}
