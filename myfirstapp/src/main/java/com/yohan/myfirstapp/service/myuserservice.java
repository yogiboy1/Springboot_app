package com.yohan.myfirstapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yohan.myfirstapp.model.Sender;
import com.yohan.myfirstapp.model.SenderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class myuserservice {

	private final SenderRepository senderRepository;
	
	
	public myuserservice(SenderRepository senderRepository) {
		super();
		this.senderRepository = senderRepository;
	}


	public Optional<Sender> getdirect (String direct) {
		Optional<Sender> hellOptional = senderRepository.findByDirect(direct);
		return hellOptional;
	}
	
}
