package com.example.guarderia.repository;

import com.example.guarderia.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client getClientByDocument(Integer document);




}
