package com.imobiliaria.lelo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imobiliaria.lelo.model.Imovel;



public interface Imoveis extends JpaRepository<Imovel, Long> {
	
}
