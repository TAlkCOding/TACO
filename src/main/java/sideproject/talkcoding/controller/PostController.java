package sideproject.talkcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysql.cj.protocol.x.Ok;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.repository.PostRepository;
import sideproject.talkcoding.service.post.PostService;

@Controller
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;


    // 게시글 작성
    @PostMapping("/save")
    public String save(@ModelAttribute("post") PostDto postDto){
        postService.save(postDto);
        return null;
    }
}
