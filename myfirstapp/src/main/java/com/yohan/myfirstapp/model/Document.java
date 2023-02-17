package com.yohan.myfirstapp.model;

import org.hibernate.annotations.GenericGenerator;

import com.enumfields.Criteria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Entity(name = "DOCUMENT")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document{
	    @Id
	    @GeneratedValue(strategy = GenerationType.UUID,generator = "uuid")
	    @GenericGenerator(name = "uuid", strategy = "uuid2")
	    @Column(name = "DOC_ID")
	    private String id;
	    @Enumerated(EnumType.STRING)
	    @Column(name = "CRITERIA")
	    private Criteria urgency;
	    @Column(name = "DOC_NAME")
	    private String docname;
	    @Column(name = "DOC_TYPE")
	    private String fileType;
	    @Column(name = "FILE")
	    @Lob
	    private byte[] file;
	    
	}
