package com.qingke.boma.pojo;

/**
 * @time ����4:02:21
 * @Description ������
 */
public class Manager {
	private Integer id;
	// ��˾��������
	private String name;
	// ���������
	private String showName;
	// �����˸�λ
	private String job;
	// ����������
	private String comment;
	// ������˾
	private Company company;
	// �Ƿ�����ʾ�ڱ�ӡ֤
	private String isPraise;

	public Manager() {
		super();
		isPraise = "n";
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

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(String isPraise) {
		this.isPraise = isPraise;
	}

}
