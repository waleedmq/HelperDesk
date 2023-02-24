package com.fdmgroup.helperdesk.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.Trainee;
import com.fdmgroup.helperdesk.repo.HelperRepo;
import com.fdmgroup.helperdesk.repo.TraineeRepo;

import jakarta.websocket.Session;

@Service
public class TraineeDetailsService implements UserDetailsService{

	@Autowired
	private TraineeRepo traineeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Trainee> traineeOptional = traineeRepo.findByUsername(username);
				
				Trainee trainee = traineeOptional.orElseThrow(() -> new UsernameNotFoundException("Helper not found"));
				
				return new TraineePrincipal(trainee);
		
	}
}
