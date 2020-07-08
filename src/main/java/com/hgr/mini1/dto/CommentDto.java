package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.domain.entity.CommentEntity;
import com.hgr.mini1.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentEntity toEntity() {
        CommentEntity commentEntity = CommentEntity.builder()
                .id(id)
                .comment(comment)
                .build();
        return commentEntity;
    }

    @Builder
    public CommentDto(Long id,String comment, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id=id;
        this.comment = comment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}
