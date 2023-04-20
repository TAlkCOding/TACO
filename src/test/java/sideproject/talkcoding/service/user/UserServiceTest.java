package sideproject.talkcoding.service.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.talkcoding.model.dto.user.UserDto;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 서비스isNotNull(){
        Assertions.assertThat(userService).isNotNull();
    }

    // 회원가입 - 아이디 중복 체크 버튼
    @Test
    @Transactional
    void signUp_duplicateId(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        UserEntity user1Entity = user1.toEntity();

        userRepository.save(user1Entity);

        //given
        UserDto user2 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("5678")
        .userName("윤형우")
        .userNickName("닉네임2")
        .build();

        int result = userService.checkDuplicateId(user2.getUserId());
        //then
        assertThat(result).isEqualTo(1);

    }

    // 회원가입 - 닉네임 중복 체크 버튼
    @Test
    @Transactional
    void signUp_duplicateNickName(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("같은 닉네임")
        .build();

        UserEntity user1Entity = user1.toEntity();
        userRepository.save(user1Entity);
        
        //given
        UserDto user2 = UserDto.builder()
        .userId("0104yhw")
        .userPassword("5678")
        .userName("윤형우")
        .userNickName("같은 닉네임")
        .build();

        int result = userService.checkDuplicateNickName(user2.getUserNickName());

        //then
        assertThat(result).isEqualTo(1);
    }

    // 회원가입 - 성공
    @Test
    @Transactional
    void signUp_Success(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        UserEntity user1Entity = user1.toEntity();
        userRepository.save(user1Entity);
        
        //given
        UserDto user2 = UserDto.builder()
        .userId("0104yhw")
        .userPassword("5678")
        .userName("윤형우")
        .userNickName("닉네임2")
        .build();

        int checkId = userService.checkDuplicateId(user2.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user2.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user2);
            }
        }
        //then
        assertThat(userRepository.findAll().size()).isEqualTo(2);
    }

    // 아이디 찾기 (이름과 전화번호)
    @Test
    @Transactional
    void findUserId(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }
        //given

        // 아이디 찾기 페이지에서 들어온 이름과 전화번호 값
        String user1Name = user1.getUserName();
        String user1PhoneNumber = user1.getUserPhoneNumber();

        String expected = userService.findId(user1Name, user1PhoneNumber);
        
        //then
        assertThat(user1.getUserId()).isEqualTo(expected);
    }

    // 비밀번호 찾기 (아이디와 이름)
    @Test
    @Transactional
    void findUserPassword(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }

        //given
        String user1Id = user1.getUserId();
        String user1Name = user1.getUserName();

        Optional<UserEntity> expected = userService.findPassword(user1Id, user1Name);

        //then
        assertThat(expected.get().getUserIndex()).isEqualTo(1L);
        assertThat(user1.getUserId()).isEqualTo(expected.get().getUserId());
        assertThat(user1.getUserName()).isEqualTo(expected.get().getUserName());
        assertThat(user1.getUserNickName()).isEqualTo(expected.get().getUserNickName());
        assertThat(user1.getUserPassword()).isEqualTo(expected.get().getUserPassword());
        assertThat(user1.getUserPhoneNumber()).isEqualTo(expected.get().getUserPhoneNumber());
    }

    // 비밀번호 변경
    @Test
    @Transactional
    void changeUserPassword(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }

        //given
        String user1Id = user1.getUserId();
        String user1Name = user1.getUserName();

        Optional<UserEntity> findInfo = userService.findPassword(user1Id, user1Name);

        String changePassword = "5678";

        Optional<UserEntity> expected = userService.changePassword(findInfo, changePassword);
    
        //then
        assertThat(findInfo.get().getUserId()).isEqualTo(expected.get().getUserId());
        assertThat(findInfo.get().getUserName()).isEqualTo(expected.get().getUserName());
        assertThat(findInfo.get().getUserNickName()).isEqualTo(expected.get().getUserNickName());
        assertThat(findInfo.get().getUserPassword()).isEqualTo(changePassword);
        assertThat(findInfo.get().getUserPhoneNumber()).isEqualTo(expected.get().getUserPhoneNumber());
    }

    // 회원정보 보기
    @Test
    @Transactional
    void getUserInfo() {
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }

        Optional<UserEntity> user = userRepository.findById(1L);

        //given
        Optional<UserEntity> expected = userService.findUserInfo(1L);

        //then
        assertThat(user.get().getUserId()).isEqualTo(expected.get().getUserId());
        assertThat(user.get().getUserName()).isEqualTo(expected.get().getUserName());
        assertThat(user.get().getUserNickName()).isEqualTo(expected.get().getUserNickName());
        assertThat(user.get().getUserPhoneNumber()).isEqualTo(expected.get().getUserPhoneNumber());
    }

    // 회원정보 수정
    @Test
    @Transactional
    void updateUserInfo(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }

        Optional<UserEntity> user = userRepository.findById(1L);

        //given
        userService.changeUserInfo(user);
        
        //then
        assertThat(user.get().getUserName()).isEqualTo("윤하연");
        assertThat(user.get().getUserNickName()).isEqualTo("윤공주");
        assertThat(user.get().getUserPhoneNumber()).isEqualTo("01093195367");
    }

    // 회원 탈퇴
    @Test
    @Transactional
    void deleteUserInfo(){
        //when
        UserDto user1 = UserDto.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        // 아이디 닉네임 중복 검사
        int checkId = userService.checkDuplicateId(user1.getUserId());
        int checkNickName = userService.checkDuplicateNickName(user1.getUserNickName());
        
        if(checkId == 0){
            if(checkNickName == 0){
                userService.save(user1);
            }
        }

        //given
        userService.deleteUserInfo(1L);

        List<UserEntity> expected = userRepository.findAll();
        //then
        assertThat(expected.size()).isEqualTo(0);
    }
}
