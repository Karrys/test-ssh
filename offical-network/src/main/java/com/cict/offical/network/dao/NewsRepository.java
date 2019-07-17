package com.cict.offical.network.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.News;
import com.cict.offical.network.entity.Recruit;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
	Page<News> findAll(Pageable pageable);
}
