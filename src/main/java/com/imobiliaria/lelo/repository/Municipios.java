package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.lelo.model.Municipio;


public interface Municipios extends JpaRepository<Municipio, Long> {
	public List<Municipio> findBynomeMunicipioContaining(String nomeMunicipio);
	public List<Municipio> findByEstado_Codigo(Long codigo);
}
