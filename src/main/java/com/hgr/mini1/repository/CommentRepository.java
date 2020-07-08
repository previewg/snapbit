package com.hgr.mini1.repository;


import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


}