package com.fdmgroup.helperdesk.service;

import java.util.List;
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
public class HelperService {
	
	@Autowired
	private HelperRepo helperRepo;

	
	public Optional<Helper> getHelperFromUsername(String username) {
		Optional<Helper> helperOptional = helperRepo.findByUsername(username);
		return helperOptional;
	}

}
