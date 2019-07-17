package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.UserFeedbackRepository;
import com.cict.offical.network.entity.UserFeedback;

@Service
public class UserFeedbackService {
	    @Autowired
	    UserFeedbackRepository userFeedbackRepository;
	    
		public Page<UserFeedback> getAllUserFeedback(Specification<UserFeedback> spec, Pageable pageable) {
			return userFeedbackRepository.findAll(spec,pageable);
		}
		
		public UserFeedback getUserFeedback(int id) {
			return userFeedbackRepository.findOne(id);
		}
		
		public UserFeedback saveUserFeedback(UserFeedback userFeedback) {
			return userFeedbackRepository.save(userFeedback);
		}
		
		public void deleteUserFeedback(Integer id) {
			userFeedbackRepository.delete(id);
		}

		public void deleteUserFeedbackBatch(List<Integer> ids) {
			userFeedbackRepository.deleteUserFeedbackByIdIn(ids);
		}
		
	}

	