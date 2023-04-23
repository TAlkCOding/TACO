package sideproject.talkcoding.service.post;

import java.util.List;
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

    // 게시글 리스트 가져오기
    public List<PostEntity> readAll(){
        List<PostEntity> list = postRepository.findAll();

        return list;
    }

    // 게시글 수정
    public Optional<PostEntity> edit(Long postId, PostDto postDto) {
        Optional<PostEntity> post = postRepository.findById(postId);

       
        return post.map(p -> {
            post.get().setTitle(postDto.getTitle());
            post.get().setDescription(postDto.getDescription());
            post.get().setPostSido(postDto.getPostSido());
            post.get().setPostGugun(postDto.getPostGugun());
            post.get().setPostDong(postDto.getPostDong());
            post.get().setPostLanguage(postDto.getPostLanguage());
            
            return p;
        })

            .map(p -> postRepository.save(p));

    }
    
    // 게시글 삭제
	public void delete(Long postId) {
        postRepository.deleteById(postId);
	}

    // 시도 select box 찾기
    public List<PostEntity> findSido(String postSido) {
        List<PostEntity> sidoList = postRepository.findByPostSido(postSido);

        return sidoList;
    }

    // 시/도, 구/군 select box 찾기
    public List<PostEntity> findGugun(String postSido, String postGugun) {
        List<PostEntity> gugunList = postRepository.findByPostSidoAndPostGugun(postSido, postGugun);
        
        return gugunList;
    }

    // 시/도, 구/군, 동 select box 찾기
    public List<PostEntity> findDong(String postSido, String postGugun, String postDong) {
        List<PostEntity> dongList = postRepository.findByPostSidoAndPostGugunAndPostDong(postSido, postGugun, postDong);
        
        return dongList;
    }

}
