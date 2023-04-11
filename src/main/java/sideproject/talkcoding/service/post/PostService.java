package sideproject.talkcoding.service.post;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 저장
    public PostEntity save(PostDto postDto) {
        PostEntity postEntity = postDto.toEntity();
        PostEntity saved = postRepository.save(postEntity);

        return saved;
    }

    // 게시글 상세
	public Optional<PostEntity> read(Long postId) {
        Optional<PostEntity> post = postRepository.findById(postId);

        return post;
	}

	public void delete(Long postId) {
        postRepository.deleteById(postId);
	}
    
}
