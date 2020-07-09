package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.LoveeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoveeDto {
    private Long id;
    private Long user;
    private Long board;

    public LoveeEntity toEntity() {
        LoveeEntity loveeEntity = LoveeEntity.builder()
                .id(id)
                .user(user)
                .board(board)
                .build();
        return loveeEntity;
    }

    @Builder
    public LoveeDto(Long id, Long user,Long board) {
        this.id = id;
        this.user = user;
        this.board =board;
    }
}
