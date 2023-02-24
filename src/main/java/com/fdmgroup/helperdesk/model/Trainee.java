package com.fdmgroup.helperdesk.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Trainees")
public class Trainee extends User{
	
	@OneToMany(mappedBy = "trainee")
	private List<Ticket> openTickets = new ArrayList<>();
	
	public Trainee() {
		super();
	}
	
	public Trainee(String username) {
		super(username);
	}
	
	public List<Ticket> getOpenTickets() {
		return openTickets;
	}

	public void setOpenTickers(List<Ticket> assignedTickets) {
		this.openTickets = assignedTickets;
	}
	
	public void addTicket(Ticket t) {
		openTickets.add(t);
	}
	
	public void removeTicket(Ticket t) {
		openTickets.remove(t);
	}
}
