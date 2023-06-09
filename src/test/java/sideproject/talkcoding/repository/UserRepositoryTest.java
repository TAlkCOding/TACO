package sideproject.talkcoding.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import sideproject.talkcoding.model.entity.user.UserEntity;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 레파지토리isNotNull(){
        Assertions.assertThat(userRepository).isNotNull();
    }

    // 회원가입 - 아이디 중복
    @Test
    @Transactional
    void signUp_duplicateId(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        UserEntity user2 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("5678")
        .userName("윤형우")
        .userNickName("닉네임2")
        .build();
        
        userRepository.save(user1);

        //given
        String user2Id = user2.getUserId();
        Optional<UserEntity> dupId = userRepository.findByUserId(user2Id);

        if(dupId.isEmpty()){
            userRepository.save(user2);
            log.info("create Id!!");
        }
        else{
            log.info("duplicate ID!!");
        }
        //then

        Assertions.assertThat(userRepository.findById(2L)).isEmpty();
    }

    // 회원가입 - 닉네임 중복
    @Test
    @Transactional
    void signUp_duplicateNickName(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("같은 닉네임")
        .userPhoneNumber("01012345678")
        .build();

        UserEntity user2 = UserEntity.builder()
        .userId("0104yhw")
        .userPassword("5678")
        .userName("윤현웅")
        .userNickName("같은 닉네임")
        .userPhoneNumber("01012345678")
        .build();
        
        userRepository.save(user1);

        //given
        String user2NickName = user2.getUserNickName();
        Optional<UserEntity> dupId = userRepository.findByUserNickName(user2NickName);

        if(dupId.isEmpty()){
            userRepository.save(user2);
            log.info("create Id!!");
        }
        else{
            log.info("duplicate NickName!!");
        }

        //then
        Assertions.assertThat(userRepository.findById(2L)).isEmpty();
    }

    // 회원가입 - 성공
    @Test
    @Transactional
    void signUp_Success(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .build();

        UserEntity user2 = UserEntity.builder()
        .userId("0104yhw")
        .userPassword("5678")
        .userName("윤현웅")
        .userNickName("닉네임2")
        .build();
        
        userRepository.save(user1);

        //given
        String user2Id = user2.getUserId();
        String user2NickName = user2.getUserNickName();

        Optional<UserEntity> dupUserId = userRepository.findByUserId(user2Id);
        Optional<UserEntity> dupUserNickName = userRepository.findByUserNickName(user2NickName);

        if(dupUserId.isEmpty() && dupUserNickName.isEmpty()){
            log.info("signup clear!");
            userRepository.save(user2);
        }


        //then
        List<UserEntity> list = userRepository.findAll();
        Assertions.assertThat(list.size()).isEqualTo(2);

    }

    // 아이디 찾기 (이름과 전화번호로)
    @Test
    @Transactional
    void findUserId(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .userPhoneNumber("01062635367")
        .build();

        userRepository.save(user1);

        //given
        String user1Name = user1.getUserName();
        String user1PhoneNumber = user1.getUserPhoneNumber();

        Optional<UserEntity> findId = userRepository.findByUserNameAndUserPhoneNumber(user1Name, user1PhoneNumber);
        
        String findUserId = findId.get().getUserId();
        //then
        Assertions.assertThat(user1.getUserId()).isEqualTo(findUserId);
    }

    // 비밀번호 찾기 및 변경 (아이디와 이름으로)
    @Test
    @Transactional
    void findUserPassword(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .userPhoneNumber("01062635367")
        .build();

        UserEntity user = userRepository.save(user1);

        //given
        String userId = user1.getUserId();
        String userName = user1.getUserName();

        Optional<UserEntity> findPassword = userRepository.findByUserIdAndUserName(userId, userName);
        
        String changePassword = "5678";

        findPassword.map(p -> {
            findPassword.get().setUserPassword(changePassword);

            return p;
        })
            .map(p -> userRepository.save(p));

        
        //then
        assertThat(user.getUserId()).isEqualTo(user1.getUserId());
        assertThat(user.getUserName()).isEqualTo(user1.getUserName());
        assertThat(user.getUserNickName()).isEqualTo(user1.getUserNickName());
        assertThat(user.getUserPhoneNumber()).isEqualTo(user1.getUserPhoneNumber());
        assertThat(user.getUserPassword()).isEqualTo(changePassword);

    }

    // 회원정보 보기
    @Test
    @Transactional
    void getUserInfo() {
        // when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .userPhoneNumber("01062635367")
        .build();

        UserEntity user = userRepository.save(user1);

        //given
        Optional<UserEntity> expected = userRepository.findById(1L);
        
        //then
        assertThat(1L).isEqualTo(expected.get().getUserIndex());
        assertThat(user.getUserId()).isEqualTo(expected.get().getUserId());
        assertThat(user.getUserName()).isEqualTo(expected.get().getUserName());
        assertThat(user.getUserNickName()).isEqualTo(expected.get().getUserNickName());
        assertThat(user.getUserPhoneNumber()).isEqualTo(expected.get().getUserPhoneNumber());
    }

    // 회원정보 수정
    @Test
    @Transactional
    void updateUserInfo(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .userPhoneNumber("01062635367")
        .build();

        UserEntity user = userRepository.save(user1);
        //given
        Optional<UserEntity> expected = userRepository.findById(1L);

        expected.map(p -> {
            expected.get().setUserName("윤하연");
            expected.get().setUserNickName("윤공주");
            expected.get().setUserPhoneNumber("01092595367");
        
            return p;
        })
        .map(p -> userRepository.save(p));

        //then
        assertThat(1L).isEqualTo(expected.get().getUserIndex());
        assertThat(user.getUserId()).isEqualTo(expected.get().getUserId());
        assertThat(user.getUserPassword()).isEqualTo(expected.get().getUserPassword());
        assertThat(expected.get().getUserName()).isEqualTo("윤하연");
        assertThat(expected.get().getUserNickName()).isEqualTo("윤공주");
        assertThat(expected.get().getUserPhoneNumber()).isEqualTo("01092595367");

    }

    // 회원 탈퇴
    @Test
    @Transactional
    void deleteUserInfo(){
        //when
        UserEntity user1 = UserEntity.builder()
        .userId("yhw0104")
        .userPassword("1234")
        .userName("윤현우")
        .userNickName("닉네임1")
        .userPhoneNumber("01062635367")
        .build();

        userRepository.save(user1);
        //given
        userRepository.deleteById(1L);

        //then
        assertThat(userRepository.findById(1L)).isEmpty();
    }
}
