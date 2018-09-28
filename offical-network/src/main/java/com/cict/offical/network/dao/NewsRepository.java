package com.cict.offical.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
}
