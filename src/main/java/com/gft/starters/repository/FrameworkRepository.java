package com.gft.starters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.starters.model.Framework;

public interface FrameworkRepository extends JpaRepository<Framework, Long>{
	
	public List<Framework> findByLinguagem_Id(Long id);
	
	public List<Framework> findByLinguagem_Nome(String nome);

}
