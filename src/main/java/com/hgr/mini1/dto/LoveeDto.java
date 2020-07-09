package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.LoveeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoveeDto {
    private Long id;
    private String user;
    private String board;

    public LoveeEntity toEntity() {
        LoveeEntity loveeEntity = LoveeEntity.builder()
                .id(id)
                .user(user)
                .board(board)
                .build();
        return loveeEntity;
    }

    @Builder
    public LoveeDto(Long id, String user,String board) {
        this.id = id;
        this.user = user;
        this.board =board;
    }
}
