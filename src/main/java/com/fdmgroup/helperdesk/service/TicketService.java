package com.fdmgroup.helperdesk.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.ProgrammingLanguage;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;
import com.fdmgroup.helperdesk.repo.HelperRepo;
import com.fdmgroup.helperdesk.repo.PLanguageRepo;
import com.fdmgroup.helperdesk.repo.TicketRepo;
import com.fdmgroup.helperdesk.repo.TraineeRepo;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private HelperRepo helperRepo;

	public List<Ticket> getAllTickets(){
		return ticketRepo.findAll();
	}
	
	public void registerNewTicket(Ticket ticket) {
		//Optional<Helper> helper = helperRepo.findByUsername("helper1");
		
		long pLanguageId = ticket.getpLanguage().getLanguageId();
		List<Helper> availableHelpers= helperRepo.getHelpersbypLanguage(pLanguageId);
		HashMap<Helper, Integer> helperTickets = new HashMap<>();
		
		for(Helper helper: availableHelpers) {
			helperTickets.put(helper, helper.getAssignedTickets().size());
		}
		
		int minNum = Collections.min(helperTickets.values());
		
		Helper selectedHelper = null;
	    
		for(Helper helper: helperTickets.keySet()) {
			if(helperTickets.get(helper)==minNum) {
				selectedHelper = helper;
			}
		}
		
		ticket.setHelper(selectedHelper);
		ticketRepo.save(ticket);
	}

	public List<Ticket> getOpenTraineeTickets(Trainee trainee) {
		return ticketRepo.getOpenTraineeTicketsById(trainee.getId());
	}
	
	public List<Ticket> getOpenHelperTickets(Helper helper) {
		return ticketRepo.getOpenHelperTicketsById(helper.getId());
	}

	public void closeTicketbyId(long ticketId) {
		ticketRepo.closeTicketbyId(ticketId);
	}
	
}
