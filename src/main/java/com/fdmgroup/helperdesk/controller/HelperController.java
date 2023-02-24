package com.fdmgroup.helperdesk.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.fdmgroup.helperdesk.service.HelperService;
import com.fdmgroup.helperdesk.service.PLanguageService;
import com.fdmgroup.helperdesk.service.TicketService;
import com.fdmgroup.helperdesk.service.TraineeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("helper")
public class HelperController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private HelperService helperService;
	
	@Autowired
	private PLanguageService pLanguageService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static Logger logger = LogManager.getLogger();

	@GetMapping("/login")
    public String gethelperLogin() {
        return "helper/login";
    }
	
	
	@GetMapping("/home")
	public String getHelperHome(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", username);
		logger.info("Helper " +username+" logged in.");
		
		Helper helper = helperService.getHelperFromUsername(username).get();
		session.setAttribute("helper", helper);
		
		//open trainee tickets
		List<Ticket> openHelperTickets = ticketService.getOpenHelperTickets(helper);
		model.addAttribute("tickets", openHelperTickets);
		return "helper/helperHome";
	}
	
	@GetMapping("/close/{ticketId}")
	public String closeTicket(@PathVariable("ticketId") long ticketId) {
		ticketService.closeTicketbyId(ticketId);
		logger.info("Ticket with Id Number "+ticketId+" closed" );
		return "redirect:/helper/home";
	}
	


	

}
