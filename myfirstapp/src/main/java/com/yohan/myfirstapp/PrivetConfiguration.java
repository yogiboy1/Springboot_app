package com.yohan.myfirstapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class PrivetConfiguration {
	@Autowired
	private PrivetProperties privetProperties;
	
	@PostConstruct
	public void init() {
	System.out.println("Status set load: "+ privetProperties.getLoadTestData());
}}
