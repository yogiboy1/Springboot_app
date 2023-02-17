package com.yohan.myfirstapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yohan.myfirstapp.model.SenderRepository;

@Service
public class mydetailservice implements UserDetailsService{
	
	@Autowired
	SenderRepository senderRepository;
	
	SenderDetails sender = null;
	
	@Override
	public UserDetails loadUserByUsername(String direct) throws UsernameNotFoundException {
		sender.setSender(senderRepository.findByDirect(direct).get());
		if(sender.getSender()==null){
			throw new UsernameNotFoundException("Cant find");
		}
		return sender;
	}

}
