package sideproject.talkcoding.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sideproject.talkcoding.repository.PostRepository;
import sideproject.talkcoding.service.post.PostService;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    
    // 메인페이지 게시글 리스트
    @GetMapping("/")
    public String postList(Model model){
        model.addAttribute("posts", postService.readAll());

        //게시글 최신순으로 정렬

        return "main";
    }
}
