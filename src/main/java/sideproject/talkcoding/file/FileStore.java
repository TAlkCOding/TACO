package sideproject.talkcoding.file;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import sideproject.talkcoding.model.entity.image.ProfileEntity;
import sideproject.talkcoding.repository.ProfileRepository;

@Component
public class FileStore {

    @Autowired
    private ProfileRepository profileRepository;
    
    // 파일 경로
    @Value("${file.dir}")
    private String fileDir;

    // 파일 경로 구성
    public String getFullPath(String storeFileName) {
        return fileDir + storeFileName;
    }

    // 프로필을 저장 -  이미 유저인덱스로 찾아 엔티티가 있을 경우 수정 / 없으면 저장
    public Optional<ProfileEntity> storeFile(MultipartFile multipartFile, Long userIndex) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        
        String originFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);
        
        try{
            multipartFile.transferTo(new File(getFullPath(storeFileName)));
        }catch(Exception e){
            System.out.println("can't upload file");
        }

        ProfileEntity entity = ProfileEntity.builder()
                                    .originFileName(originFileName)
                                    .storeFileName(storeFileName)
                                    .userIndex(userIndex)
                                    .build();

        Optional<ProfileEntity> profileEntity = profileRepository.findByUserIndex(userIndex);
        
        // 만약 유저 인덱스를 가진 프로필엔티티가 있으면 해당 프로필 엔티티를 수정
        if(!profileEntity.isEmpty()){
            profileEntity.map(p -> {
                profileEntity.get().setOriginFileName(originFileName);
                profileEntity.get().setStoreFileName(storeFileName);
                profileEntity.get().setUserIndex(userIndex);

                return p;
            })
            .map(p -> profileRepository.save(p));

            return profileEntity;
        }
        // 없다면 프로필 엔티티를 생성해서 저장
        else{
            profileRepository.save(entity);
            Optional<ProfileEntity> find =profileRepository.findById(userIndex);
            return find;
        }
    }
    
    // storeFile 이름 구성
    private String createStoreFileName(String originFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originFileName);
        String storeFileName = uuid + "." + ext;

        return storeFileName;
    }

    // 확장자 추출
    private String extractExt(String originFileName) {
        int idx = originFileName.lastIndexOf(".");
        String ext = originFileName.substring(idx + 1);
        return ext;
    }
}
