package com.cict.offical.network.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SysMenu implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private int id;
    private int parentId;
    private String type;
    private String name;
    private String alias;
    private String icon;
    private String url;
    private int sort;    
    private String desc;  
    private String lastUpdatedUser;
    private Date lastUpadatedTime;
    private int state;
    @Transient
    private boolean isLeaf;

    public SysMenu() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	public Date getLastUpadatedTime() {
		return lastUpadatedTime;
	}

	public void setLastUpadatedTime(Date lastUpadatedTime) {
		this.lastUpadatedTime = lastUpadatedTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isLeaf() {
		return StringUtils.isBlank(this.url)?true:false;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
   
}