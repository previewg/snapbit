package com.hgr.mini1.repository;


import com.hgr.mini1.domain.entity.LoveeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface LoveeRepository extends JpaRepository<LoveeEntity, Long> {

    @Transactional
    public List<LoveeEntity> findAllByBoard(Long id);

    @Transactional
    public LoveeEntity findByBoardAndUser(Long board_id,Long user_id);

}