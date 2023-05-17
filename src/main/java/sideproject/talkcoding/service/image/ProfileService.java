package sideproject.talkcoding.service.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sideproject.talkcoding.file.FileStore;
import sideproject.talkcoding.model.entity.image.ProfileEntity;

@Service
public class ProfileService {

    @Autowired
    private FileStore fileStore;

    // 회원가입시 프로필 엔티티를 생성
    public ProfileEntity saveProfile(MultipartFile originFileName, Long userIndex) throws IOException {
        fileStore.storeFile(originFileName, userIndex);
        return null;
    }
}
