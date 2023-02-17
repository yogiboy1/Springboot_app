package com.yohan.myfirstapp.service;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.enumfields.Criteria;
import com.yohan.myfirstapp.FileStorageException;
import com.yohan.myfirstapp.NotFoundException;
import com.yohan.myfirstapp.model.Document;
import com.yohan.myfirstapp.model.DocumentRespository;

import lombok.Setter;

@Service
public class FileService {
	
	@Autowired
	private DocumentRespository documentRespository;
	
	public Document newDocument(MultipartFile multipartFile,Criteria criteria) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Document dbFile = Document.builder().docname(fileName).urgency(criteria).fileType(multipartFile.getContentType()).file(multipartFile.getBytes()).build();

            return documentRespository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	
	 public Document geDocument(String fileId) {
	        return documentRespository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found with id " + fileId));
	    }
	}

