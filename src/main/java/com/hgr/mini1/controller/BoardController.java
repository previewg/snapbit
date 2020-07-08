package com.hgr.mini1.controller;


import com.hgr.mini1.dto.BoardDto;
import com.hgr.mini1.dto.CommentDto;
import com.hgr.mini1.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /*검색기능*/
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);
        return "board";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        boardService.increseHit(no);
        BoardDto boardDTO = boardService.getPost(no);
        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
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
    defaultValue = "1") Integer pageNum ) {
        List<BoardDto> boardList;
        boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList;
        pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
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
    public String saveComment(CommentDto commentDto,@RequestParam long board_id){
        boardService.saveComment(commentDto);
        return "redirect:/post/" + board_id;
    }
}