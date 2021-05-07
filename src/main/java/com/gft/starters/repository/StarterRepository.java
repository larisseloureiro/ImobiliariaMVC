package com.gft.starters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.starters.model.Starter;

public interface StarterRepository extends JpaRepository<Starter, Long> {

}
