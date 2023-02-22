package com.yohan.myfirstapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yohan.myfirstapp.model.Sender;
import com.yohan.myfirstapp.model.SenderRepository;

@Service
public class mydetailservice implements UserDetailsService{
	
	@Autowired
	SenderRepository senderRepository;
	
	SenderDetails sender = new SenderDetails();
	
	@Autowired
	myuserservice myuserservice;
	
	@Override
	public UserDetails loadUserByUsername(String direct) throws UsernameNotFoundException {
		System.out.println("this is the direct address"+ direct);
		Optional<Sender> senderop = myuserservice.getdirect(direct);
		if(senderop.isEmpty()){
			throw new UsernameNotFoundException("Cant find");
		}
		sender.setSender(senderop.get());
		return sender;
	}

}
