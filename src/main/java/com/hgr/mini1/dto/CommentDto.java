package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;

    public CommentEntity toEntity() {
        CommentEntity commentEntity = CommentEntity.builder()
                .id(id)
                .comment(comment)
                .build();
        return commentEntity;
    }

    @Builder
    public CommentDto(Long id,String comment) {
        this.id=id;
        this.comment = comment;
    }


}
