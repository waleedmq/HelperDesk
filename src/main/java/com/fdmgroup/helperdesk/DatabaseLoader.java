package com.fdmgroup.helperdesk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.ProgrammingLanguage;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;
import com.fdmgroup.helperdesk.repo.HelperRepo;
import com.fdmgroup.helperdesk.repo.PLanguageRepo;
import com.fdmgroup.helperdesk.repo.TicketRepo;
import com.fdmgroup.helperdesk.repo.TraineeRepo;

@Configuration
public class DatabaseLoader {
	
	@Autowired
	private HelperRepo helperRepo;
	
	@Autowired
	private PLanguageRepo pLanguageRepo;
	
	@Autowired
	private TraineeRepo traineeRepo;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public DatabaseLoader(HelperRepo helperRepo, PLanguageRepo pLanguageRepo, TraineeRepo traineeRepo, TicketRepo ticketRepo) {
		this.helperRepo = helperRepo;
		this.pLanguageRepo = pLanguageRepo;
		this.traineeRepo = traineeRepo;
		this.ticketRepo = ticketRepo;
	}
	
	@Bean
	public CommandLineRunner intializeDB() {
		return args -> {
			Helper helper1 = new Helper("helper1");
			Helper helper2 = new Helper("helper2");
			Helper helper3 = new Helper("helper3");
			
			helper1.setPassword(bCryptPasswordEncoder.encode("fdmgroup1"));
			helper2.setPassword(bCryptPasswordEncoder.encode("fdmgroup2"));
			helper3.setPassword(bCryptPasswordEncoder.encode("fdmgroup3"));

			ProgrammingLanguage javaSE = new ProgrammingLanguage("Java SE");
			ProgrammingLanguage sql = new ProgrammingLanguage("SQL");
			ProgrammingLanguage unix = new ProgrammingLanguage("UNIX");
			ProgrammingLanguage javaEE = new ProgrammingLanguage("Java EE");
			ProgrammingLanguage cplusplus = new ProgrammingLanguage("C++");
			
			helper1.addLanguage(javaEE);
			helper1.addLanguage(javaSE);
			
			helper2.addLanguage(sql);
			helper2.addLanguage(unix);
			helper2.addLanguage(cplusplus);
			
			helper3.addLanguage(javaSE);
			helper3.addLanguage(sql);
			
			Trainee trainee1 = new Trainee("john");
			Trainee trainee2 = new Trainee("alice");
			Trainee trainee3 = new Trainee("nicole");
			Trainee trainee4 = new Trainee("zack");
			Trainee trainee5 = new Trainee("jack");

			trainee1.setPassword(bCryptPasswordEncoder.encode("john12"));
			trainee2.setPassword(bCryptPasswordEncoder.encode("alice12"));
			trainee3.setPassword(bCryptPasswordEncoder.encode("nicole12"));
			trainee4.setPassword(bCryptPasswordEncoder.encode("zack12"));
			trainee5.setPassword(bCryptPasswordEncoder.encode("jack12"));

			//c++ 1 javase 2 javaee 1 unix 1 sql 2 
			
			Ticket ticket1 = new Ticket(trainee5, cplusplus, "have problem with cpluis");
			ticket1.setHelper(helper3);
			
			pLanguageRepo.saveAll(List.of(javaSE,cplusplus,sql,unix,javaEE));
			helperRepo.saveAll(List.of(helper1,helper2,helper3));
			traineeRepo.saveAll(List.of(trainee1,trainee2,trainee3,trainee4,trainee5));
			ticketRepo.save(ticket1);
			
			System.out.println("Databases intialized");
		};
	}
	
}
