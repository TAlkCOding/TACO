package sideproject.talkcoding.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired 
    private PostRepository postRepository;

    @Test
    void 멤버십레파지토리isNotNull(){
        Assertions.assertThat(postRepository).isNotNull();
    }    

}
