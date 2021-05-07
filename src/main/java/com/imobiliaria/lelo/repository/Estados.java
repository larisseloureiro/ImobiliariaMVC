package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.imobiliaria.lelo.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {
	public List<Estado> findByStateContaining(String state);
	
}
