package sideproject.talkcoding.model.entity.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.dto.image.ProfileDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class ProfileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileIndex;

    @Column
    private Long userIndex;

    @Column
    private String originFileName;

    @Column
    private String storeFileName;

    public ProfileDto toDto(){
        ProfileDto profileDto = ProfileDto.builder()
        .userIndex(userIndex)
        .originFileName(originFileName)
        .storeFileName(storeFileName)
        .build();

        return profileDto;
    }

}
