package sideproject.talkcoding.controller.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.service.post.PostService;

@Controller
public class PageController {

    @Autowired
    private PostService postService;
    
    // 메인페이지 게시글 리스트
    @GetMapping("/")
    public String postList(Model model){
        model.addAttribute("posts", postService.readAll());

        //게시글 최신순으로 정렬

        return "main";
    }

    // 주소 설정 select box 값 가져와서 주소 별 게시글 가져오기 - ajax로 select box 데이터 가져오기
    
    // 시/도 select box만 설정했을 경우
    @GetMapping("/{postSido}")
    public ResponseEntity<List<PostEntity>> searchSido(@PathVariable("postSido") String postSido, Model model){
        List<PostEntity> sidoList = postService.findSido(postSido);
        model.addAttribute("sidoList", sidoList);
        
        return new ResponseEntity<>(sidoList, HttpStatus.OK);
    }

    // 시/도, 구/군 select box 설정
    @GetMapping("/{postSido}/{postGugun}")
    public ResponseEntity<List<PostEntity>> searchGugun(@PathVariable("postSido") String postSido,
                                                            @PathVariable("postGugun") String postGugun,
                                                            Model model){    
        List<PostEntity> gugunList = postService.findGugun(postSido, postGugun);
        model.addAttribute("gugunList", gugunList);
    
        return new ResponseEntity<>(gugunList, HttpStatus.OK);
    }

    @GetMapping("/{postSido}/{postGugun}/{postDong}")
    public ResponseEntity<List<PostEntity>> searchDong(@PathVariable("postSido") String postSido,
                                                            @PathVariable("postGugun") String postGugun,
                                                            @PathVariable("postDong") String postDong,
                                                            Model model){    
        List<PostEntity> dongList = postService.findDong(postSido, postGugun, postDong);
        model.addAttribute("gugunList", dongList);
    
        return new ResponseEntity<>(dongList, HttpStatus.OK);
    }

    // 제목, 게시글 검색 기능
}
