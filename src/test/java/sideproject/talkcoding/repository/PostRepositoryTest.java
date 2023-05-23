package sideproject.talkcoding.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.talkcoding.model.dto.post.PostDto;
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
                .postLanguage1("java")
                .build();
              
        //given
        PostEntity result = postRepository.save(post);
        
        //then
        assertThat(post.getPostIndex()).isEqualTo(result.getPostIndex());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getDescription()).isEqualTo(result.getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.getPostDong());
        assertThat(post.getPostLanguage1()).isEqualTo(result.getPostLanguage1());

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
        .postLanguage1("java")
        .build();

        PostEntity post2 = PostEntity.builder()
        .title("second title")
        .description("second description")
        .postSido("서울특별시")
        .postGugun("강남구")
        .postDong("서초동")
        .postLanguage1("java")
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
        .postLanguage1("java")
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
        assertThat(post.getPostLanguage1()).isEqualTo(result.get().getPostLanguage1());
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
        .postLanguage1("java")
        .build();

        postRepository.save(post);
        Optional<PostEntity> entityPost = postRepository.findById(1L);
        //given

        PostDto postDto = PostDto.builder()
        .title("edit title")
        .description("edit description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage1("C++")
        .build();

        entityPost.map(p ->{
            entityPost.get().setTitle(postDto.getTitle());
            entityPost.get().setDescription(postDto.getDescription());
            entityPost.get().setPostSido(postDto.getPostSido());
            entityPost.get().setPostGugun(postDto.getPostGugun());
            entityPost.get().setPostDong(postDto.getPostDong());
            entityPost.get().setPostLanguage1(postDto.getPostLanguage1());
            
            return p;
        })

            .map(p -> postRepository.save(p));
        
        //then

        assertThat(entityPost.get().getPostIndex()).isEqualTo(1L);
        assertThat(entityPost.get().getTitle()).isEqualTo(postDto.getTitle());
        assertThat(entityPost.get().getDescription()).isEqualTo(postDto.getDescription());
        assertThat(entityPost.get().getPostSido()).isEqualTo(postDto.getPostSido());
        assertThat(entityPost.get().getPostGugun()).isEqualTo(postDto.getPostGugun());
        assertThat(entityPost.get().getPostDong()).isEqualTo(postDto.getPostDong());
        assertThat(entityPost.get().getPostLanguage1()).isEqualTo(postDto.getPostLanguage1());
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
        .postLanguage1("java")
        .build();

        //given
        postRepository.save(post);
        postRepository.deleteById(1L);

        //then
        assertThat(postRepository.findById(1L)).isEmpty();
    }

}
