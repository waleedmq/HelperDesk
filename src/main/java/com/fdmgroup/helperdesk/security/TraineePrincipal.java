package com.fdmgroup.helperdesk.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.helperdesk.model.Helper;
import com.fdmgroup.helperdesk.model.Trainee;

import ch.qos.logback.core.testUtil.TeeOutputStream;

public class TraineePrincipal implements UserDetails{

	private Trainee trainee;
	
	public TraineePrincipal(Trainee trainee) {
		super();
		this.trainee = trainee;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));

	}

	@Override
	public String getPassword() {
		return trainee.getPassword();
	}

	@Override
	public String getUsername() {
		return trainee.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
