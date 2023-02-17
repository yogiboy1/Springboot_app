package com.yohan.myfirstapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "SENDER")
public class Sender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SENDER_ID")
	private long id;
	@Column(name = "SENDER_NAME",nullable = false,length = 20)
	private String name;
	@Column(name = "SENDER_SURNAME",nullable = false,length = 20)
	private String surname;
	@Column(name = "SENDER_EMAIL",nullable = false,length = 45)
	private String email;
	@Column(name = "SENDER_D_ADDRESS",unique = true,length = 45)
	private String direct;
	@Column(name = "SENDER_INS",nullable = false,length = 45)
	private String institution;
	@Column(name = "PASSWORD",nullable = false,length = 64)
	@JsonIgnore
	private String password;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "sender")
    private List<Mailcontent> mails = new ArrayList<>();
	
	public Sender() {}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDirect() {
		return direct;
	}


	public void setDirect(String direct) {
		this.direct = direct;
	}


	public String getInstitution() {
		return institution;
	}


	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Mailcontent> getMails() {
		return mails;
	}


	public void setMails(List<Mailcontent> mails) {
		this.mails = mails;
	}


	@Override
	public String toString() {
		return "Sender [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", direct="
				+ direct + ", institution=" + institution + ", password=" + password + ", mails=" + mails + "]";
	}
	
	
    
	
	
}
