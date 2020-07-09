package com.hgr.mini1.controller;


import com.hgr.mini1.dto.BoardDto;
import com.hgr.mini1.dto.CommentDto;
import com.hgr.mini1.dto.LoveeDto;
import com.hgr.mini1.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    HttpSession session;




    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no,Model model) {
        BoardDto boardDto = boardService.getPost(no);
        List<CommentDto> commentDtoList = boardService.commentList(no);
        List<LoveeDto> loveeDtoList = boardService.loveeList(no);

        model.addAttribute("loveeDtoList",loveeDtoList);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("commentDtoList",commentDtoList);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);
        return "redirect:/board";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board";
    }

    @GetMapping("/post/like/{no}")
    public String recommend(@PathVariable("no") Long no){
        boardService.increaseRecommend(no);
        return "redirect:/post/{no}";
    }

    /* 게시글 목록*/
    @GetMapping("/board")
    public String list(Model model,
                       @RequestParam(value="page",
    defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        List<BoardDto> boardList;
        boardList = boardService.getBoardlist(pageNum,keyword);

        Map<String,Object> map = boardService.getPageList(pageNum);
        Integer[] pageList =(Integer[]) map.get("pageList");
        int totalLastPageNum = (int)map.get("totalLastPageNum");

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("nowPage",pageNum );
        model.addAttribute("totalLastPageNum",totalLastPageNum );
        return "board";
    }
    
    @GetMapping("/board/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/board";
    }

    @PostMapping("/board/comment")
    public String saveComment(CommentDto commentDto,long board_id){
        boardService.saveComment(commentDto,board_id);
        return "redirect:/post/" + board_id;
    }

    @GetMapping("/board/click")
    public String increaseHit(long board_id,String author){
        boardService.increseHit(board_id,author);
        return("redirect:/post/"+board_id);
    }

    @PostMapping("/board/like")
    public String SaveboardLike(LoveeDto loveeDto){
        boardService.SaveboardLike(loveeDto);
        return "redirect:/post/" + loveeDto.getBoard();
    }

}