package com.fdmgroup.helperdesk.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.repo.HelperRepo;

import jakarta.websocket.Session;

@Service
public class HelperDetailsService implements UserDetailsService{

	@Autowired
	private HelperRepo helperRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Helper> helperOptional = helperRepo.findByUsername(username);
				
				Helper helper = helperOptional.orElseThrow(() -> new UsernameNotFoundException("Helper not found"));
				
				return new HelperPrincipal(helper);
		
	}
}
