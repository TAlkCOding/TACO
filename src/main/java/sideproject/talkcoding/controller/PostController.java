package sideproject.talkcoding.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostEntity> save(@ModelAttribute("post") PostDto postDto){
        PostEntity post = postService.save(postDto);    // 변경해야함( 테스트위해서 변경 )
        // postService.save(postDto);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
        // return "redirect:/post_detail.html";
    }
 
    // 게시글 상세
    @GetMapping("/post/{postId}")
    public ResponseEntity<Optional<PostEntity>> read(@PathVariable(name = "postId") Long postId, Model model) {
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
        // return "post_detail";
    }

    // 게시글 수정 페이지 -> 게시글 수정 페이지에 수정하려는 게시글 들어가게 하는 페이지
    @GetMapping("/post/edit/{postId}")
    public ResponseEntity<Optional<PostEntity>> editPage(@PathVariable("postId") Long postId, Model model){
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // 게시글 수정
    @PostMapping("/post/edit/{postId}")
    public ResponseEntity<Optional<PostEntity>> edit(@PathVariable(name = "postId") Long postId, @ModelAttribute PostDto postDto){
        Optional<PostEntity> post = postService.edit(postId, postDto);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    

    // 게시글 삭제
    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity<String> delete(@PathVariable(name = "postId") Long postId){
        postService.delete(postId);

        return new ResponseEntity<>("delete success", HttpStatus.OK);
        //return "/";
    }

}
