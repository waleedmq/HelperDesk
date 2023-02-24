package com.fdmgroup.helperdesk.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.helperdesk.model.Helper;

import ch.qos.logback.core.testUtil.TeeOutputStream;

public class HelperPrincipal implements UserDetails{

	private Helper helper;
	
	public HelperPrincipal(Helper helper) {
		super();
		this.helper = helper;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));

	}

	@Override
	public String getPassword() {
		return helper.getPassword();
	}

	@Override
	public String getUsername() {
		return helper.getUsername();
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
