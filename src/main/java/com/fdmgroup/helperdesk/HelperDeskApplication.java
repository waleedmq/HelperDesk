package com.fdmgroup.helperdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.support.Repositories;

import com.fdmgroup.helperdesk.model.ProgrammingLanguage;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;
import com.fdmgroup.helperdesk.repo.TicketRepo;

@SpringBootApplication
public class HelperDeskApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(HelperDeskApplication.class, args);

	}

}
