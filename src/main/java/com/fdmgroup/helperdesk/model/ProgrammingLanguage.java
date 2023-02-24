package com.fdmgroup.helperdesk.model;

import org.hibernate.boot.jaxb.mapping.marshall.LockModeTypeMarshalling;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Programming_Languages")

public class ProgrammingLanguage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long languageId;
	
	@Column(unique = true)
	private String name;
	
	public ProgrammingLanguage() {
		super();
	}
	
	public ProgrammingLanguage(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	
}
