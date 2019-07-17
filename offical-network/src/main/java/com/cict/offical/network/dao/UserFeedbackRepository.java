package com.cict.offical.network.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cict.offical.network.entity.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {
	
	Page<UserFeedback> findAll(Specification<UserFeedback> spec,Pageable pageable);

	@Transactional
    void deleteUserFeedbackByIdIn(List<Integer> ids);	
	
}
