package com.cict.offical.network.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.Recruit;



public interface RecruitRepository extends JpaRepository<Recruit, Integer>{		
		
    Page<Recruit> findAll(Specification<Recruit> spec,Pageable pageable);
	
}
