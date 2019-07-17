package com.cict.offical.network.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cict.offical.network.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	Page<Article> findAll(Specification<Article> spec,Pageable pageable);	
	
	//删除时，直接可以使用delete(id),依据id来删除一条数据，但是当想使用deleteByName(String name)时，需要添加@Transactional注解，才能使用。
	@Transactional
    void deleteArticleByIdIn(List<Integer> ids);	
	
    @Modifying
    @Transactional
    @Query("delete from Article s where s.id in (?1)")
    void deleteBatch(List<Integer> ids);

    
}
