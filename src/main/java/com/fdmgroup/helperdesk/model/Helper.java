package com.fdmgroup.helperdesk.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Helpers")

public class Helper extends User {
	
	@OneToMany(mappedBy = "helper")
	private List<Ticket> assignedTickets = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "Helper_PLanguages",
	joinColumns = @JoinColumn(name = "FK_Helper_Id"),
	inverseJoinColumns = @JoinColumn(name = "FK_PLanguage_Id"))
	private List<ProgrammingLanguage> languages = new ArrayList<>();
	

	public Helper() {
		super();
	}
	
	public Helper(String username) {
		super(username);
	}

	public List<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public void setAssignedTickets(List<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}
	
	public List<ProgrammingLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(List<ProgrammingLanguage> languages) {
		this.languages = languages;
	}
	
	public void addTicket(Ticket t) {
		assignedTickets.add(t);
	}
	
	public void removeTicket(Ticket t) {
		assignedTickets.remove(t);
	}
	
	public void addLanguage(ProgrammingLanguage pl) {
		languages.add(pl);
	}
	
}
