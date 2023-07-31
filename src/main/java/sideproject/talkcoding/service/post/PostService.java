package sideproject.talkcoding.service.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sideproject.talkcoding.model.dto.post.PostDto;
import sideproject.talkcoding.model.entity.image.ProfileEntity;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.repository.PostRepository;
import sideproject.talkcoding.repository.ProfileRepository;
import sideproject.talkcoding.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    // 게시글 저장
    public PostEntity save(PostDto postDto, Long userIndex) {
        Optional<UserEntity> user = userRepository.findById(userIndex);
        Optional<ProfileEntity> userProfile = profileRepository.findById(userIndex);
        if(userProfile.isPresent()){
            PostEntity postEntity = postDto.toEntity(userIndex, user.get().getUserNickName(), userProfile.get().getStoreFileName());
            PostEntity saved = postRepository.save(postEntity);

            return saved;
        }
        else{
            PostEntity postEntity = postDto.toEntity(userIndex, user.get().getUserNickName());
            PostEntity saved = postRepository.save(postEntity);

            return saved;
        }
    }

    // 게시글 상세
	public Optional<PostEntity> read(Long postId) {
        Optional<PostEntity> post = postRepository.findById(postId);

        return post;
	}

    // 게시글 리스트 가져오기
    // 페이징 처리
    public Page<PostEntity> readAll(Pageable pageable){
        Page<PostEntity> list = postRepository.findAll(pageable);

        return list;
    }

    // 내 게시글들 리스트 가져오기
    public List<PostEntity> readMyPost(Long userIndex) {
        List<PostEntity> myPost = postRepository.findByUserIndex(userIndex);
    
        return myPost;
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
            post.get().setPostLanguage1(postDto.getPostLanguage1());
            post.get().setPostLanguage2(postDto.getPostLanguage2());
            post.get().setPostLanguage3(postDto.getPostLanguage3());
            
            return p;
        })

            .map(p -> postRepository.save(p));

    }
    
    // 게시글 삭제
	public void delete(Long postId) {
        postRepository.deleteById(postId);
	}

    // 시도 select box 찾기
    public Page<PostEntity> findSido(String postSido, Pageable pageable) {
        Page<PostEntity> sidoList = postRepository.findByPostSidoContaining(postSido, pageable);

        return sidoList;
    }

    // 시/도, 구/군 select box 찾기
    public Page<PostEntity> findGugun(String postSido, String postGugun, Pageable pageable) {
        Page<PostEntity> gugunList = postRepository.findByPostSidoContainingAndPostGugunContaining(postSido, postGugun, pageable);
        
        return gugunList;
    }

    // 시/도, 구/군, 동 select box 찾기
    public Page<PostEntity> findDong(String postSido, String postGugun, String postDong, Pageable pageable) {
        Page<PostEntity> dongList = postRepository.findByPostSidoContainingAndPostGugunContainingAndPostDongContaining(postSido, postGugun, postDong, pageable);
        
        return dongList;
    }

    // 게시글 검색
    public Page<PostEntity> search(String keyword, Pageable pageable) {

        Page<PostEntity> search = postRepository.findByTitleContaining(keyword, pageable);

        return search;
    }   
}