package sideproject.talkcoding.service.image;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sideproject.talkcoding.file.FileStore;
import sideproject.talkcoding.model.entity.image.ProfileEntity;
import sideproject.talkcoding.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private ProfileRepository profileRepository;

    // 회원가입시 프로필 엔티티를 생성
    public ProfileEntity saveProfile(MultipartFile originFileName, Long userIndex) throws IOException {
        fileStore.storeFile(originFileName, userIndex);
        return null;
    }

    public Optional<ProfileEntity> findProfileEntity(Long userIndex){
        Optional<ProfileEntity> profile = profileRepository.findByUserIndex(userIndex);

        return profile;
    }
}
