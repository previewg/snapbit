package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.CommentEntity;
import lombok.*;


@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private String nickname;

    public CommentEntity toEntity() {
        CommentEntity commentEntity = CommentEntity.builder()
                .id(id)
                .comment(comment)
                .nickname(nickname)
                .build();
        return commentEntity;
    }

    @Builder
    public CommentDto(Long id,String comment,String nickname) {
        this.id=id;
        this.comment = comment;
        this.nickname = nickname;
    }


}
