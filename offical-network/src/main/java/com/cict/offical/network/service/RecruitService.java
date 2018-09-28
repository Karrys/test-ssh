package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.RecruitRepository;
import com.cict.offical.network.entity.Recruit;

@Service
public class RecruitService {
	    @Autowired
	    RecruitRepository recruitRepository;
	   
		public List<Recruit> getAllRecruit() {
			return recruitRepository.findAll();
		}
		public Recruit addRecruit(Recruit recruit) {
			recruitRepository.save(recruit);
			return recruit;
		}
		public Recruit updateRecruit(Recruit recruit) {
			recruitRepository.save(recruit);
			return recruit;
		}
		public void deleteRecruit(Integer id) {
			recruitRepository.delete(id);
		}
	}

	