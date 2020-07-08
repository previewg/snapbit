package com.hgr.mini1;

import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.domain.entity.CommentEntity;
import com.hgr.mini1.repository.BoardRepository;
import com.hgr.mini1.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@SpringBootTest
class Mini1ApplicationTests {
    @Autowired
    CommentRepository cr;

    @Autowired
    BoardRepository br;

    @Test
    public void test() {
    }
}


