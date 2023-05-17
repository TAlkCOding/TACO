package sideproject.talkcoding.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.entity.user.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private Long userIndex;

    private String userId;

    private String userPassword;

    private String userName;

    private String userNickName;

    private String userPhoneNumber;

    public UserEntity toEntity(){
        UserEntity userEntity = UserEntity.builder()
        .userId(userId)
        .userPassword(userPassword)
        .userName(userName)
        .userNickName(userNickName)
        .userPhoneNumber(userPhoneNumber)
        .build();

        return userEntity;
    }
}
