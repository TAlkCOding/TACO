package sideproject.talkcoding.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNull;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.service.post.PostService;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;
    
    // @Test
    // void 레파지토리isNotNull(){
    //     Assertions.assertThat(postRepository).isNotNull();
    // }

    // 게시글 저장

    // 깃허브 세팅 주석
    @Test
    @Transactional
    void savePost() {
        //when
        PostEntity post = PostEntity.builder()
                .title("first title")
                .description("first description")
                .postDong("상현동")
                .postSido("용인시")
                .postGugun("수지구")
                .postLanguage("java")
                .build();
              
        //given
        PostEntity result = postRepository.save(post);
        
        //then
        assertThat(post.getId()).isEqualTo(result.getId());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getDescription()).isEqualTo(result.getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.getPostDong());
        assertThat(post.getPostLanguage()).isEqualTo(result.getPostLanguage());

    }

    // 게시글 리스트
    @Test
    @Transactional
    void postList(){
        //when
        PostEntity post1= PostEntity.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage("java")
        .build();

        PostEntity post2 = PostEntity.builder()
        .title("second title")
        .description("second description")
        .postSido("서울특별시")
        .postGugun("강남구")
        .postDong("서초동")
        .postLanguage("java")
        .build();

        //given
        postRepository.save(post1);
        postRepository.save(post2);

        List<PostEntity> list = postRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);



    }

    // 게시글 상세보기 읽기
    @Test
    @Transactional
    void findPost() {
        //when
        PostEntity post = PostEntity.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage("java")
        .build();

        //given
        postRepository.save(post);
        Optional<PostEntity> result = postRepository.findById(1L);

        //then
        assertThat(post.getTitle()).isEqualTo(result.get().getTitle());
        assertThat(post.getDescription()).isEqualTo(result.get().getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.get().getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.get().getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.get().getPostDong());
        assertThat(post.getPostLanguage()).isEqualTo(result.get().getPostLanguage());
    }

    // 게시글 수정
    @Test
    @Transactional
    void updatePost() {
        //when
        PostEntity post = PostEntity.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage("java")
        .build();

        PostEntity entityPost = postRepository.save(post);
        //given

        PostEntity post1 = PostEntity.builder()
        .title("edit title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage("java")
        .build();

        PostDto dtoPost = entityPost.toDto();

        dtoPost.setTitle(post1.getTitle());
        dtoPost.setDescription(post1.getDescription());
        dtoPost.setPostSido(post1.getPostSido());
        dtoPost.setPostGugun(post1.getPostGugun());
        dtoPost.setPostDong(post1.getPostDong());
        dtoPost.setPostLanguage(post1.getPostLanguage());

        PostEntity postEntity =dtoPost.toEntity();
        //then
    }


    // 게시글 삭제
    @Test
    @Transactional
    void deletePost() {
        //when
        PostEntity post = PostEntity.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage("java")
        .build();

        //given
        postRepository.save(post);
        postRepository.deleteById(1L);

        //then
        assertThat(postRepository.findById(1L)).isEmpty();
    }

}
