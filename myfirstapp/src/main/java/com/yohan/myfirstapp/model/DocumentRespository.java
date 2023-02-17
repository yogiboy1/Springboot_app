package com.yohan.myfirstapp.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRespository extends JpaRepository<Document, String> {
}
