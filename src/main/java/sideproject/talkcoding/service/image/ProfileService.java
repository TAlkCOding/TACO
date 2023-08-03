package sideproject.talkcoding.service.image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sideproject.talkcoding.file.FileStore;
import sideproject.talkcoding.model.entity.image.ProfileEntity;
import sideproject.talkcoding.model.entity.post.PostEntity;
import sideproject.talkcoding.model.entity.post.ReplyEntity;
import sideproject.talkcoding.repository.PostRepository;
import sideproject.talkcoding.repository.ProfileRepository;
import sideproject.talkcoding.repository.ReplyRepository;

@Service
public class ProfileService {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    // 회원가입시 프로필 엔티티를 생성
    public Optional<ProfileEntity> saveProfile(MultipartFile originFileName, Long userIndex) throws IOException {
        Optional<ProfileEntity> storeFile = fileStore.storeFile(originFileName, userIndex);
        
        List<PostEntity> userPost = postRepository.findByUserIndex(userIndex);
        List<ReplyEntity> userReply = replyRepository.findByReplyUserIndex(userIndex);

        for(PostEntity post : userPost){
            post.setStoreFileName(storeFile.get().getStoreFileName());

            postRepository.save(post);
        }

        for(ReplyEntity reply : userReply){
            reply.setStoreFileName(storeFile.get().getStoreFileName());

            replyRepository.save(reply);
        }
        return storeFile;
    }

    public Optional<ProfileEntity> findProfileEntity(Long userIndex){
        Optional<ProfileEntity> profile = profileRepository.findByUserIndex(userIndex);

        return profile;
    }
}
