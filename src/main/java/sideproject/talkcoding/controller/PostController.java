package sideproject.talkcoding.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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

    // 글쓰기 페이지 들어가기
    @GetMapping("/post")
    public String post(HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");

        return "post";
    }

    // 게시글 작성 / return 저장한 게시글 상세 페이지
    // 로그인이 되어있지 않으면 애초에 해당 url 설정을 해두지 않을 것이기 때문에 로그인 체크 안해도 됨
    @PostMapping("/post/save")
    public ResponseEntity<PostEntity> save(HttpSession session, @ModelAttribute("post") PostDto postDto){
        Long userIndex = (Long) session.getAttribute("userIndex");
        PostEntity post = postService.save(postDto, userIndex);    // 변경해야함( 테스트위해서 변경 )
        // postService.save(postDto, userIndex);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
        // return "redirect:/post_detail.html";
    }
 
    // 게시글 상세보기
    @GetMapping("/post/{postId}")
    public ResponseEntity<Integer> read(HttpSession session, @PathVariable(name = "postId") Long postId, Model model) {
        Long userIndex = (Long) session.getAttribute("userIndex");
        
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);
        
        
        // 세션을 가져와서 만약 게시글 userIndex와 같다면, 1을 출력해 수정 및 삭제 버튼 활성화
        Integer checkedUserIndex = 0;
        if(userIndex != null){
            if(userIndex == post.get().getUserIndex()){
                model.addAttribute("correct", "1");
                checkedUserIndex = 1;
            } else if(userIndex != post.get().getUserIndex()){
                model.addAttribute("incorrect", "0");
                checkedUserIndex = 2;
            }
        }
        
        return new ResponseEntity<>(checkedUserIndex, HttpStatus.OK);
        // return "post_detail";
    }

    // 게시글 수정 페이지 들어가기 -> 게시글 수정 페이지에 수정하려는 게시글 들어가게 하는 페이지 (게시글 작성 페이지에 데이터가 들어가있는 화면)
    @GetMapping("/post/edit/{postId}")
    public ResponseEntity<Optional<PostEntity>> editPage(@PathVariable("postId") Long postId, Model model){
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // 게시글 수정 (게시글 수정 페이지 들어가서 수정 버튼 클릭 시) / return 수정한 게시글 상세 페이지
    @PostMapping("/post/edit/{postId}")
    public ResponseEntity<Optional<PostEntity>> edit(@PathVariable(name = "postId") Long postId, @ModelAttribute PostDto postDto){
        Optional<PostEntity> post = postService.edit(postId, postDto);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    

    // 게시글 삭제  / return 메인 페이지
    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity<String> delete(@PathVariable(name = "postId") Long postId){
        postService.delete(postId);

        return new ResponseEntity<>("delete success", HttpStatus.OK);
        //return "/";
    }

}
