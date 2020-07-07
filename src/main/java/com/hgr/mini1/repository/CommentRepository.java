package com.hgr.mini1.repository;


import com.hgr.mini1.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE board SET hit = hit+1 WHERE id=?1", nativeQuery = true)
//    public void increaseHit(Long id);
//
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE board SET recommend = recommend+1 WHERE id=?1", nativeQuery = true)
//    public void increaseRecommend(Long id);

}