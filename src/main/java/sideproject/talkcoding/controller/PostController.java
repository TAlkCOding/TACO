package sideproject.talkcoding.controller;

import java.util.List;
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
import sideproject.talkcoding.model.entity.post.ReplyEntity;
import sideproject.talkcoding.service.post.PostService;
import sideproject.talkcoding.service.post.ReplyService;

@Controller
public class PostController {
    

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    // 글쓰기 페이지 들어가기
    @GetMapping("/post")
    public String post(HttpSession session){
        // Long userIndex = (Long) session.getAttribute("userIndex");

        return "post";
    }

    // 내 작성글 페이지 들어가기
    @GetMapping("/post/my")
    public String myPost(HttpSession session, Model model){
        Long userIndex = (Long) session.getAttribute("userIndex");

        List<PostEntity> myPost = postService.readMyPost(userIndex);
        model.addAttribute("mypost", myPost);

        return "mypost";
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
        
        // 게시글 model
        Optional<PostEntity> post = postService.read(postId);
        model.addAttribute("post", post);

        // 댓글 model
        List<ReplyEntity> replyList = replyService.read(postId);
        model.addAttribute("reply", replyList);
        
        
        // 세션을 가져와서 만약 게시글, 댓글 userIndex와 같다면
        // 1을 출력해 수정 및 삭제 버튼 활성화 
        // 로그인 했지만, 다르면 0 
        // 로그인하지 않았다면, 2 출력
        // 수정 필요
        Integer checkedUserIndexForPost = 0;
        if(userIndex != null){
            if(userIndex == post.get().getUserIndex()){
                // 게시글, 댓글, 세션이 같으면
                checkedUserIndexForPost = 1;
                model.addAttribute("checkIndexForPost", checkedUserIndexForPost);
            } else if(userIndex != post.get().getUserIndex()){
                checkedUserIndexForPost = 2;
                model.addAttribute("checkIndexForPost", checkedUserIndexForPost);
            } else {
                model.addAttribute("checkIndexForPost", checkedUserIndexForPost);
            }
        }
        
        Integer checkedUserIndexForReply = 0;
        if(userIndex != null){
            for(ReplyEntity reply : replyList){
                if(userIndex == reply.getReplyUserIndex()){
                    checkedUserIndexForReply = 1;
                    model.addAttribute("checkIndexForReply", checkedUserIndexForReply);
                } else if(userIndex != reply.getReplyUserIndex()){
                    checkedUserIndexForReply = 2;
                    model.addAttribute("checkIndexForReply", checkedUserIndexForReply);
                } else {
                    model.addAttribute("checkIndexForReply", checkedUserIndexForReply);
                }
            }
        }
        
        
        return new ResponseEntity<>(checkedUserIndexForPost, HttpStatus.OK);
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
