package com.cict.offical.network.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Recruit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String postName;//职位名称
	private String base;//工作地点
	private String workExperience;//工作经验
	private String education;//学历
	private Integer num;//招聘人数	
	private String keyWords;//职位亮点
	private String description;	//职位描述
	private String requirements;//任职要求
	private String priorities;	//优先条件	
	//@Temporal(TemporalType.DATE)
	private Date saveDate;//保存日期
	//@Temporal(TemporalType.DATE)
	private Date releaseDate;//发布日期
	private Integer state;	//状态   （待发布：0，已发布：1，已下线：2）
	
	@Transient
	private List<String> keyWordsList;//职位亮点    多选框
	
	public Recruit() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}


	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getPriorities() {
		return priorities;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  
	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<String> getKeyWordsList() {
		return keyWordsList;
	}

	public void setKeyWordsList(List<String> keyWordsList) {
		this.keyWordsList = keyWordsList;
	}

}
