package sideproject.talkcoding.service.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.repository.PostRepository;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;
    
    // @Test
    // void 레파지토리isNotNull(){
    //     Assertions.assertThat(postRepository).isNotNull();
    // }

    // 게시글 저장
    @Test
    @Transactional
    void savePost() {
        //when
        PostDto post = PostDto.builder()
                .title("first title")
                .description("first description")
                .postDong("상현동")
                .postSido("용인시")
                .postGugun("수지구")
                .build();
              
        //given
        PostEntity result = postService.save(post);

        Optional<PostEntity> expect = postRepository.findById(1L);
        
        //then
        assertThat(expect.get().getId()).isEqualTo(result.getId());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getDescription()).isEqualTo(result.getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.getPostDong());

    }

    // 게시글 리스트
    @Test
    @Transactional
    void postList(){
        //when
        PostDto post1= PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .build();

        PostDto post2 = PostDto.builder()
        .title("second title")
        .description("second description")
        .postSido("서울특별시")
        .postGugun("강남구")
        .postDong("서초동")
        .build();

        //given
        postService.save(post1);
        postService.save(post2);   

        //List<PostEntity> list = postService.readAll();
        List<PostEntity> list = postRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);



    }

    // 게시글 상세보기 읽기
    @Test
    @Transactional
    void findPost() {
        //when
        PostDto post = PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .build();

        //given
        postService.save(post);
        Optional<PostEntity> result = postService.read(1L);

        //then
        assertThat(post.getTitle()).isEqualTo(result.get().getTitle());
        assertThat(post.getDescription()).isEqualTo(result.get().getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.get().getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.get().getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.get().getPostDong());
    }

    // 게시글 수정


    // 게시글 삭제
    @Test
    @Transactional
    void deletePost() {
        //when
        PostDto post = PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .build();

        //given
        postService.save(post);
        postRepository.deleteById(1L);

        //then
        assertThat(postRepository.findById(1L)).isEmpty();
    }

}
