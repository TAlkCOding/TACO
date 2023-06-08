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
    void savePostService() {
        //when
        PostDto post = PostDto.builder()
                .title("first title")
                .description("first description")
                .postDong("상현동")
                .postSido("용인시")
                .postGugun("수지구")
                .build();
              
        Long userIndex = 1L;
        //given
        PostEntity result = postService.save(post, userIndex);

        Optional<PostEntity> expect = postRepository.findById(1L);
        
        //then
        assertThat(expect.get().getPostIndex()).isEqualTo(result.getPostIndex());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getDescription()).isEqualTo(result.getDescription());
        assertThat(post.getPostSido()).isEqualTo(result.getPostSido());
        assertThat(post.getPostGugun()).isEqualTo(result.getPostGugun());
        assertThat(post.getPostDong()).isEqualTo(result.getPostDong());

    }

    // 게시글 리스트
    @Test
    @Transactional
    void postListService(){
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

        Long userIndex1 = 1L;
        Long userIndex2 = 2L;
        //given
        postService.save(post1, userIndex1);
        postService.save(post2, userIndex2);   

        
        List<PostEntity> list = postRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);



    }

    // 게시글 상세보기 읽기
    @Test
    @Transactional
    void findPostService() {
        //when
        PostDto post = PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .build();

        Long userIndex = 1L;
        //given
        postService.save(post, userIndex);
        Optional<PostEntity> result = postService.read(1L);

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
    void updatePostService() {
        //when
        PostDto post = PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage1("java")
        .build();

        Long userIndex = 1L;
        postService.save(post, userIndex);
        Optional<PostEntity> entityPost = postService.read(1L);
        //given

        PostDto postDto = PostDto.builder()
        .title("edit title")
        .description("edit description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .postLanguage1("C++")
        .build();

        Optional<PostEntity> result = postService.edit(1L, postDto);
        
        //then

        assertThat(entityPost.get().getPostIndex()).isEqualTo(1L);
        assertThat(entityPost.get().getTitle()).isEqualTo(result.get().getTitle());
        assertThat(entityPost.get().getDescription()).isEqualTo(result.get().getDescription());
        assertThat(entityPost.get().getPostSido()).isEqualTo(result.get().getPostSido());
        assertThat(entityPost.get().getPostGugun()).isEqualTo(result.get().getPostGugun());
        assertThat(entityPost.get().getPostDong()).isEqualTo(result.get().getPostDong());
        assertThat(entityPost.get().getPostLanguage1()).isEqualTo(result.get().getPostLanguage1());
    }

    // 게시글 삭제
    @Test
    @Transactional
    void deletePostService() {
        //when
        PostDto post = PostDto.builder()
        .title("first title")
        .description("first description")
        .postSido("용인시")
        .postGugun("수지구")
        .postDong("상현동")
        .build();

        Long userIndex = 1L;
        //given
        postService.save(post, userIndex);
        postRepository.deleteById(1L);

        //then
        assertThat(postRepository.findById(1L)).isEmpty();
    }

}
