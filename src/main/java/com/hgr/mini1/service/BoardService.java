package com.hgr.mini1.service;

import com.hgr.mini1.domain.entity.BoardEntity;
import com.hgr.mini1.domain.entity.CommentEntity;
import com.hgr.mini1.domain.entity.LoveeEntity;
import com.hgr.mini1.dto.BoardDto;
import com.hgr.mini1.dto.CommentDto;
import com.hgr.mini1.dto.LoveeDto;
import com.hgr.mini1.repository.BoardRepository;
import com.hgr.mini1.repository.CommentRepository;
import com.hgr.mini1.repository.LoveeRepository;
import com.hgr.mini1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private LoveeRepository loveeRepository;



    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .author(boardEntity.getAuthor())
                .hit(boardEntity.getHit())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }

    private CommentDto convertEntityToDto(CommentEntity commentEntity) {
        return CommentDto.builder()
                .id(commentEntity.getId())
                .comment(commentEntity.getComment())
                .nickname(commentEntity.getNickname())
                .build();
    }

    private LoveeDto convertEntityToDto(LoveeEntity loveeEntity) {
        return LoveeDto.builder()
                .id(loveeEntity.getId())
                .user(loveeEntity.getUser())
                .board(loveeEntity.getBoard())
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
                .createdDate(boardEntity.getCreatedDate())
                .build();
        return boardDTO;
    }

    public void increseHit (long board_id,String author) {
        if(boardRepository.findByIdAndAuthor(board_id,author) == null){
            boardRepository.increaseHit(board_id);
        }
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
    public List<BoardDto> getBoardlist(Integer pageNum, String keyword) {
        Pageable page = PageRequest.of(pageNum - 1,
                PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC,
                        "createdDate"));

        Page<BoardEntity> pageEntities = boardRepository.findByTitleContaining(keyword, page);
        List<BoardEntity> boardEntities = pageEntities.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;
        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Map<String, Object> getPageList(Integer curPageNum) {
        Map<String, Object> map = new HashMap<>();
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

        if(curPageNum >10) {
            curPageNum = (int) (curPageNum / 10) * 10 + 1;

            // 페이지 번호 할당
            for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
                pageList[idx] = val;
            }
        }else{
            for(int val = 1, idx = 0; val <=blockLastPageNum; val++, idx++){
                pageList[idx] =val;
            }
        }
        map.put("pageList",pageList);
        map.put("totalLastPageNum",totalLastPageNum);
        map.put("blockLastPageNum",blockLastPageNum);
        return map;
    }


    @Transactional
    public void saveComment(CommentDto commentDto,long board_id) {
        BoardEntity boardEntity = boardRepository.findById(board_id).get();
        boardEntity.getCommentEntityList().add(commentDto.toEntity());
    }


    @Transactional
    public List<CommentDto> commentList(Long id){
        List<CommentDto> CommentDtoList = new ArrayList<>();

        BoardEntity boardEntity = boardRepository.findById(id).get();
        List<CommentEntity> commentEntities = boardEntity.getCommentEntityList();
        for ( CommentEntity commentEntity :commentEntities) {
            CommentDtoList.add(convertEntityToDto(commentEntity));
            System.out.println(commentEntity);
        }

        System.out.println(CommentDtoList);
        return CommentDtoList;
    }

    @Transactional
    public List<LoveeDto> loveeList(Long id){
        List<LoveeDto> loveeDtoList = new ArrayList<>();
        List<LoveeEntity> loveeEntities = loveeRepository.findAllByBoard(id);
        System.out.println(loveeEntities);
        for (LoveeEntity loveEntity :loveeEntities) {
            System.out.println(loveEntity);
            loveeDtoList.add(convertEntityToDto(loveEntity));
        }
        return loveeDtoList;
    }

    @Transactional
    public void SaveboardLike(LoveeDto loveeDto){
        LoveeEntity loveeEntity = loveeRepository.findByBoardAndUser(loveeDto.getBoard(),loveeDto.getUser());
        if (loveeEntity != null){
            loveeRepository.deleteById(loveeEntity.getId());
        }else{
            loveeRepository.save(loveeDto.toEntity());
        }
    }





}
