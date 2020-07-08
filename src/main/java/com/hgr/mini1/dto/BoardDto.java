package com.hgr.mini1.dto;


import com.hgr.mini1.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private int hit;
    private int recommend;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .hit(hit)
                .recommend(recommend)
                .build();
        return boardEntity;
    }


    @Builder
    public BoardDto(Long id, String title, String content, String author, int hit,int recommend, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.recommend = recommend;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}
/* toEntity()
dto에서 필요한 부분을 빌더패턴을 통해 entity로 만듬
필요한 Entity는 이런식으로 추가
dto는 Controller <-> Service <-> Repository 간에 필요한 데이터를 캡슐화한 데이터 전달 객체
 Service에서 Repository 메서드를 호출할 때, Entity를 전달한 이유는 JpaRepository에
정의된 함수들은 미리 정의되어 있기 때문. 그래서 Entity를 전달할 수 밖에 없었는데, 요점은 각 계층에서
필요한 객체전달은 Entity 객체가 아닌 dto 객체를 통해 주고받는 것이 좋음*/

