package sideproject.talkcoding.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sideproject.talkcoding.model.dto.post.ReplyDto;
import sideproject.talkcoding.model.entity.post.ReplyEntity;
import sideproject.talkcoding.service.post.ReplyService;

@Controller
public class ReplyController {
    
    @Autowired
    private ReplyService replyService;

    // 댓글 쓰기
    // thymeleaf로 해당 게시글 상세정보 가져올때 url 추가 하면 됨
    @PostMapping("/reply/save/{postId}")
    public String save(@PathVariable("postId") Long postIndex,  ReplyDto replyDto, HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");

        ReplyEntity reply = replyService.save(replyDto, userIndex, postIndex);

        return "redirect:/";
    }

    // 댓글 수정
    // 댓글 수정은 ajax로 하면 좋겠음
    @PostMapping ("/reply/edit/{replyId}")
    public ResponseEntity<Optional<ReplyEntity>> edit(@PathVariable("replyId") Long replyIndex, ReplyDto replyDto){
        
        Optional<ReplyEntity> reply = replyService.edit(replyIndex, replyDto);

        return new ResponseEntity<Optional<ReplyEntity>>(reply, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/reply/delete/{replyIndex}")
    public ResponseEntity<String> delete(@PathVariable("replyIndex") Long replyIndex){
        
        replyService.delete(replyIndex);
        
        return new ResponseEntity<String>("delete successfully", HttpStatus.OK);
    }

}
