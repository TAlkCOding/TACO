package sideproject.talkcoding.model.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.entity.image.ProfileEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    
    private Long profileIndex;

    private Long userIndex;

    private String originFileName;

    private String storeFileName;

    public ProfileEntity toEntity(){
        ProfileEntity profileEntity = ProfileEntity.builder()
        .userIndex(userIndex)
        .originFileName(originFileName)
        .storeFileName(storeFileName)
        .build();

        return profileEntity;
    }
}
