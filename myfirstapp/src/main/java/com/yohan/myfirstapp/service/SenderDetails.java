package com.yohan.myfirstapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yohan.myfirstapp.model.Sender;

public class SenderDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Sender sender;
	
	public SenderDetails() {}
	
	public Sender getSender() {
		return sender;
	}
	

	public void setSender(Sender sender) {
		this.sender = sender;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("DB_ADMIN"));
		return grantedAuthorityList;
	}
	
	public String getInstitution() {
		return sender.getInstitution();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return sender.getPassword();
	}

	@Override
	public String getUsername() {
		return sender.getDirect();
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
