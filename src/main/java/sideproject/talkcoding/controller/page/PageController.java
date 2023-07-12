package sideproject.talkcoding.controller.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.service.post.PostService;

@Controller
public class PageController {

    @Autowired
    private PostService postService;
    
    // 메인페이지 게시글 리스트
    @GetMapping("/")
    public String postList(Model model, @PageableDefault(page = 0,size = 20,sort = "postIndex",direction = Sort.Direction.DESC) Pageable pageable){
        // 로그인 세션 환경 설정

        Page<PostEntity> readAll = postService.readAll(pageable);

        // 게시글 최신순으로 정렬
        // 페이징 처리
        int nowPage = readAll.getPageable().getPageNumber()+1; //pageable이 갖고 있는 페이지는 0부터 시작하기 때문에
        int startPage = Math.max(nowPage -4,1);
        int endPage = Math.min(nowPage +5,readAll.getTotalPages());


        model.addAttribute("posts", readAll);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        

        return "main";
    }

    // 주소 설정 select box 값 가져와서 주소 별 게시글 가져오기 - ajax로 select box 데이터 가져오기
    
    // 시/도 select box만 설정했을 경우
    @GetMapping("/address/{postSido}")
    public ResponseEntity<Page<PostEntity>> searchSido(@PathVariable("postSido") String postSido,
                                                            @PageableDefault(page = 0,size = 20,sort = "postIndex",direction = Sort.Direction.DESC) Pageable pageable,
                                                            Model model){
        Page<PostEntity> sidoList = postService.findSido(postSido, pageable);
        
        // 페이징 처리
        int nowPage = sidoList.getPageable().getPageNumber()+1; //pageable이 갖고 있는 페이지는 0부터 시작하기 때문에
        int startPage = Math.max(nowPage -4,1);
        int endPage = Math.min(nowPage +5,sidoList.getTotalPages());

        model.addAttribute("sidoList", sidoList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        
        return new ResponseEntity<>(sidoList, HttpStatus.OK);
    }

    // 시/도, 구/군 select box 설정
    @GetMapping("/address/{postSido}/{postGugun}")
    public ResponseEntity<Page<PostEntity>> searchGugun(@PathVariable("postSido") String postSido,
                                                            @PathVariable("postGugun") String postGugun,
                                                            @PageableDefault(page = 0,size = 20,sort = "postIndex",direction = Sort.Direction.DESC) Pageable pageable,
                                                            Model model){    
        Page<PostEntity> gugunList = postService.findGugun(postSido, postGugun, pageable);

        // 페이징 처리
        int nowPage = gugunList.getPageable().getPageNumber()+1; //pageable이 갖고 있는 페이지는 0부터 시작하기 때문에
        int startPage = Math.max(nowPage -4,1);
        int endPage = Math.min(nowPage +5,gugunList.getTotalPages());

        model.addAttribute("gugunList", gugunList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

    
        return new ResponseEntity<>(gugunList, HttpStatus.OK);
    }

    // 시/도, 구/군, 동 select box 설정
    @GetMapping("/address/{postSido}/{postGugun}/{postDong}")
    public ResponseEntity<Page<PostEntity>> searchDong(@PathVariable("postSido") String postSido,
                                                            @PathVariable("postGugun") String postGugun,
                                                            @PathVariable("postDong") String postDong,
                                                            @PageableDefault(page = 0,size = 20,sort = "postIndex",direction = Sort.Direction.DESC) Pageable pageable,
                                                            Model model){    
        Page<PostEntity> dongList = postService.findDong(postSido, postGugun, postDong, pageable);

        // 페이징 처리
        int nowPage = dongList.getPageable().getPageNumber()+1; //pageable이 갖고 있는 페이지는 0부터 시작하기 때문에
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage +5, dongList.getTotalPages());

        model.addAttribute("gugunList", dongList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
    
        return new ResponseEntity<>(dongList, HttpStatus.OK);
    }

    // 제목, 게시글 검색 기능
    // GetMapping 이므로 url 뒤에 ?keyword = ... 이런 형식으로 들어가야 됨
    // PostMapping 에서의 RequestParam과 다름
    @GetMapping("/post/search")
    public ResponseEntity<List<PostEntity>> search(@RequestParam("keyword") String keyword, Model model){
        List<PostEntity> searchList = postService.search(keyword);
        model.addAttribute("searchList", searchList);

        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }
}