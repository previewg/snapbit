package com.hgr.mini1.repository;


import com.hgr.mini1.domain.entity.LoveeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoveRepository extends JpaRepository<LoveeEntity, Long> {


}