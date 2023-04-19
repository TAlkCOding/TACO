package sideproject.talkcoding.model.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.dto.user.UserDto;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIndex;

    // 사용자 아이디
    @Column
    @NotNull
    private String userId;

    @Column
    @NotNull
    private String userPassword;

    @Column
    @NotNull
    private String userName;

    @Column
    private String userNickName;

    @Column
    private String userPhoneNumber;

    public UserDto toDto(){
        UserDto userDto = UserDto.builder()
        .userId(userId)
        .userPassword(userPassword)
        .userName(userName)
        .userNickName(userNickName)
        .userPhoneNumber(userPhoneNumber)
        .build();

        return userDto;
    }
}
