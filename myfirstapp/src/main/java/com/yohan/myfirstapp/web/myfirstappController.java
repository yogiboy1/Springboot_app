package com.yohan.myfirstapp.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yohan.myfirstapp.NotFoundException;
import com.yohan.myfirstapp.model.DocumentRespository;
import com.yohan.myfirstapp.model.MailcontentRepository;
import com.yohan.myfirstapp.model.Sender;
import com.yohan.myfirstapp.model.SenderRepository;
import com.yohan.myfirstapp.model.User;

@Controller
@RequestMapping(value = "/select")
public class myfirstappController {
	private final SenderRepository senderRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	public myfirstappController(SenderRepository senderRepository) {
		super();
		this.senderRepository = senderRepository;
	}


	@GetMapping("/login")
	public String login(Model model) {
		Sender sender = new Sender();
		model.addAttribute(sender);
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Sender> hellopntity(@ModelAttribute("sender") Sender sender){
		if(sender.getEmail()==null) {
			Optional<Sender> senOptional = senderRepository.findByDirectAndInstitution(sender.getDirect() , sender.getInstitution());
			if(senOptional.isEmpty()) {
				throw new NotFoundException("Cannot find user with given direct/institution please recheck");
			}
			if(senOptional.get().getPassword().equals(sender.getPassword())) {
				sender=senOptional.get();
			}
		}
		else {
			Optional<Sender> checkOptional = senderRepository.findByEmail(sender.getEmail());
			sender.setPassword(passwordEncoder.encode(sender.getPassword()));
			if(checkOptional.isPresent()) {
				throw new DataIntegrityViolationException("Account with that email already exists , Please use different email or TRY TO LOGIN!!");
			}
			senderRepository.save(sender);}
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Location", "/myfirstappServlet");    
		return new ResponseEntity<>(sender,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/sender")
	@ResponseBody
	public List<Sender> getall(){
		List<Sender> list= senderRepository.findAll();
	    list.forEach(System.out::println);
		return list;
	}
	
	@GetMapping("/sender/{id}")
	@ResponseBody
	public EntityModel<Sender> reteivesender(@PathVariable(name = "id") long id){
		Optional<Sender> sender = senderRepository.findById(id);
		if(sender.isEmpty())
			throw new NotFoundException("No such user with id: "+id);

		EntityModel<Sender> entityModel = EntityModel.of(sender.get());
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getall());
		entityModel.add(linkBuilder.withRel("all-senders"));
		return entityModel;
		
	}
	
}
