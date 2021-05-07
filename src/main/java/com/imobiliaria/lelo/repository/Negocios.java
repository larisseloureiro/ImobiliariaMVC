package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.lelo.model.Negocio;

public interface Negocios extends JpaRepository<Negocio, Long> {
	public List<Negocio> findByTipoContaining(String tipoNegocio);
}
