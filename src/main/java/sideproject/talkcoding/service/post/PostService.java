package sideproject.talkcoding.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public PostEntity save(PostDto postDto) {
        PostEntity postEntity = postDto.toEntity();
        PostEntity saved = postRepository.save(postEntity);

        return saved;
    }
    
}
