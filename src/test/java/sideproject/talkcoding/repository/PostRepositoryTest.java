package sideproject.talkcoding.repository;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void savePost() {
        //when
        PostEntity post = PostEntity.builder()
                .title("first title")
                .description("first description")
                .build();
              
        //given
        PostEntity result = postRepository.save(post);

        //then
        assertThat(post.getId()).isEqualTo(result.getId());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getDescription()).isEqualTo(result.getDescription());
    }
}
