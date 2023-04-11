package sideproject.talkcoding.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.service.post.PostService;

@Controller
public class PostController {
    

    @Autowired
    private PostService postService;


    // 게시글 작성
    @PostMapping("/save")
    public String save(@ModelAttribute("post") PostDto postDto){
        postService.save(postDto);
        return "redirect:/post_detail";
    }

    // 게시글 상세
    @GetMapping("/post/{postId}")
    public String read(@PathVariable("postId") Long postId, Model model) {
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);

        return "post_detail";
    }

    // 게시글 수정
    

    // 게시글 삭제
    @DeleteMapping("/post/delete/{postId}")
    public String delete(@PathVariable("postId") Long postId){
        postService.delete(postId);

        return "/";
    }

}
