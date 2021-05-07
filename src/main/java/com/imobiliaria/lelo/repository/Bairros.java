package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.lelo.model.Bairro;

public interface Bairros extends JpaRepository<Bairro, Long> {
	public List<Bairro> findBynomeBairroContaining(String nomeBairro);

}
