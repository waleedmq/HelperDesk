package com.fdmgroup.helperdesk.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.ProgrammingLanguage;
import com.fdmgroup.helperdesk.model.Ticket;
import com.fdmgroup.helperdesk.model.Trainee;
import com.fdmgroup.helperdesk.repo.HelperRepo;
import com.fdmgroup.helperdesk.repo.PLanguageRepo;
import com.fdmgroup.helperdesk.repo.TraineeRepo;
import com.fdmgroup.helperdesk.service.PLanguageService;
import com.fdmgroup.helperdesk.service.TicketService;
import com.fdmgroup.helperdesk.service.TraineeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("trainee")

public class TraineeController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private TraineeService traineeService;
	
	@Autowired
	private PLanguageService pLanguageService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static Logger logger = LogManager.getLogger();

	@GetMapping("/login")
    public String getTraineeLogin() {
        return "trainee/login";
    }
	
	@GetMapping("/home")
	public String getTraineHome(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", username);
		logger.info("Trainee " +username+ " logged in.");
		
		Trainee trainee = traineeService.getTraineeFromUsername(username).get();
		session.setAttribute("trainee", trainee);
		
		List<Ticket> openTraineeTickets = ticketService.getOpenTraineeTickets(trainee);
		model.addAttribute("tickets", openTraineeTickets);

		return "trainee/traineeHome";
	}
	
	
	@GetMapping("/createTicketPage")
	public String goToCreateTicketPage(Model model) {
		List <ProgrammingLanguage> pLanguages = pLanguageService.getAllPLanguages();
		model.addAttribute("allPLanguages", pLanguages);
				
		return "trainee/createTicket";
	}
	
	@PostMapping("/createTicket")
	public String registerNewTicket(Model model, HttpSession session, @RequestParam long languageId, @RequestParam String description) {
		
		Ticket ticket = new Ticket();
		ticket.setDescription(description);
		
		ticket.setTrainee((Trainee) session.getAttribute("trainee"));
		
		Optional<ProgrammingLanguage> pLanguagOptional = pLanguageService.findLanguagebyName(languageId);
		ticket.setpLanguage(pLanguagOptional.get());
		
		ticketService.registerNewTicket(ticket);
		
		logger.info("Ticket created with ID: " + ticket.getTicketId());
		
		return "redirect:/trainee/createTicketPage";
	}
}
