package com.hgr.mini1.service;

import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.dto.BoardDto;
import com.hgr.mini1.dto.CommentDto;
import com.hgr.mini1.repository.BoardRepository;
import com.hgr.mini1.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;



    /*검색기능*/
    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;
        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        return boardDtoList;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .hit(boardEntity.getHit())
                .recommend(boardEntity.getHit())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .hit(boardEntity.getHit())
                .recommend(boardEntity.getRecommend())
                .createdDate(boardEntity.getCreatedDate())
                .build();
        return boardDTO;
    }

    public void increseHit (Long id) {
        boardRepository.increaseHit(id);
    }

    public void increaseRecommend (Long id) {
        boardRepository.increaseRecommend(id);
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }


    private static final int BLOCK_PAGE_NUM_COUNT = 10;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT =10;       // 한 페이지에 존재하는 게시글 수

    /*페이징*/
    @Transactional
    public List<BoardDto> getBoardlist(Integer pageNum) {
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum - 1,
                PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC,
                        "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            BoardDto boardDTO = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .author(boardEntity.getAuthor())
                    .hit(boardEntity.getHit())
                    .recommend(boardEntity.getRecommend())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();
            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];


        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        System.out.println(totalLastPageNum);
        System.out.println(blockLastPageNum);


        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 10) ? 1 : curPageNum - 10;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    @Transactional
    public void saveComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.getCommentEntityList().add(commentDto.toEntity());
    }




}
