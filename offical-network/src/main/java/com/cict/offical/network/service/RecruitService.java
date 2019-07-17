package com.cict.offical.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.RecruitRepository;
import com.cict.offical.network.entity.Recruit;

@Service
public class RecruitService {
	@Autowired
	RecruitRepository recruitRepository;

	public Page<Recruit> getAllRecruit(Specification<Recruit> spec, Pageable pageable) {
		return recruitRepository.findAll(spec, pageable);
	}

	public Recruit getRecruit(int id) {
		return recruitRepository.findOne(id);
	}

	public Recruit saveRecruit(Recruit recruit) {
		return recruitRepository.save(recruit);
	}

	public void deleteRecruit(Integer id) {
		recruitRepository.delete(id);
	}
}
