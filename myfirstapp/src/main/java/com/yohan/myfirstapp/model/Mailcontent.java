package com.yohan.myfirstapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MAIL")
//@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mailcontent{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAIL_ID")
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDER_ID")
	@JsonIgnore
	private Sender sender;
	/*@OneToMany()
	@JoinColumn(name = "DOC_ID")
	//@Builder.Default
	private List<Document> attachment = new ArrayList<>();*/
	@Column(name = "BODY")
	private String body;
	@Column(name ="CONSUMER")
	private String to;
	@Column(name= "SENDTIME")
	private Date date;
	
	
}
