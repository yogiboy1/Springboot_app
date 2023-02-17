package com.yohan.myfirstapp.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderRepository extends JpaRepository<Sender,Long>{
Optional<Sender> findByDirectAndInstitution(String direct,String institution);
Optional<Sender> findByDirect(String direct);
Optional<Sender> findByEmail(String email);
}
