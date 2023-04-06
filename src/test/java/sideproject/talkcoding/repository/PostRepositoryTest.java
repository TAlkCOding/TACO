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

import sideproject.talkcoding.model.entity.post.PostEntity;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    
    // @Test
    // void 레파지토리isNotNull(){
    //     Assertions.assertThat(postRepository).isNotNull();
    // }

    // 게시글 저장
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
        .build();

        PostEntity post2 = PostEntity.builder()
        .title("second title")
        .description("second description")
        .postSido("서울특별시")
        .postGugun("강남구")
        .postDong("서초동")
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
    }

    // 게시글 수정
    @Test
    @Transactional
    void updatePost() {
        
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
        .build();

        //given
        postRepository.save(post);
        postRepository.deleteById(1L);

        //then
        assertThat(postRepository.findById(1L)).isEmpty();
    }

}
