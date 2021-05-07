package com.imobiliaria.lelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.imobiliaria.lelo.model.Quarto;

public interface Quartos extends JpaRepository<Quarto, Long> {
public List<Quarto> findByQntdContaining(String qntdQuarto);
}
