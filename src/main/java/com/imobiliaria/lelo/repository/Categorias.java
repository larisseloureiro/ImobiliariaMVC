package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.lelo.model.Categoria;

public interface Categorias extends JpaRepository<Categoria, Long> {
	public List<Categoria> findByTipoContaining(String tipo);
}
