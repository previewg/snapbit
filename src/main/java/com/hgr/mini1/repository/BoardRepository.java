package com.hgr.mini1.repository;


import com.hgr.mini1.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findByTitleContaining(String keyword);

    @Transactional
    @Modifying
    @Query(value = "UPDATE board SET hit = hit+1 WHERE board_id=?1", nativeQuery = true)
    public void increaseHit(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE board SET recommend = recommend+1 WHERE board_id=?1", nativeQuery = true)
    public void increaseRecommend(Long id);

    @Transactional
    public BoardEntity findByIdAndAuthor(Long id,String author);

}