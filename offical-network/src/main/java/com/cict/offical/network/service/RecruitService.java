package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.RecruitRepository;
import com.cict.offical.network.entity.Recruit;
import com.cict.offical.network.utils.Result;

@Service
public class RecruitService {
	    @Autowired
	    RecruitRepository recruitRepository;
	   
		public Result<List<Recruit>> getAllRecruit() {
			return Result.returnResult(recruitRepository.findAll());
		}
		public Result<String> addRecruit(Recruit recruit) {
			recruitRepository.save(recruit);
			return Result.returnResult();
		}
		public Result<String> updateRecruit(Recruit recruit) {
			recruitRepository.save(recruit);
			return Result.returnResult();
		}
		public Result<String> deleteRecruit(Integer id) {
			recruitRepository.delete(id);
			return Result.returnResult();
		}
	}

	