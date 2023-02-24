package com.fdmgroup.helperdesk.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Tickets")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketId;
	
	//@ManyToOne(cascade = {CascadeType.ALL})
	@ManyToOne
	@JoinColumn(name = "FK_Helper_Id")
	private Helper helper;
	
	//@ManyToOne(cascade = {CascadeType.ALL})
	@ManyToOne
	@JoinColumn(name = "FK_Trainee_Id")
	private Trainee trainee;
	

	//@ManyToOne(cascade = {CascadeType.ALL})
	@ManyToOne
	@JoinColumn(name = "FK_Programming_Language")
	private ProgrammingLanguage pLanguage;
	
	@Column
	private String description;
	
	@Column
	private String status; //open or close
	
	@Column
	private LocalDate dateCreated;
	
	public Ticket() {
		super();
		status = "Open";
		dateCreated = LocalDate.now();
	}
	
	public Ticket(Trainee trainee, ProgrammingLanguage pLanguage, String description) {
		super();
		this.trainee = trainee;
		this.pLanguage = pLanguage;
		this.description = description;
		status = "Open";
		dateCreated = LocalDate.now();
	}

	public long getTicketId() {
		return ticketId;
	}

	
	public Helper getHelper() {
		return helper;
	}
	
	public void setHelper(Helper helper) {
		this.helper = helper;
	}
	
	public Trainee getTrainee() {
		return trainee;
	}
	
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	
	public ProgrammingLanguage getpLanguage() {
		return pLanguage;
	}
	
	public void setpLanguage(ProgrammingLanguage pLanguage) {
		this.pLanguage = pLanguage;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	
	
	
	
}
