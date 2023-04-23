package sideproject.talkcoding.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sideproject.talkcoding.model.dto.user.UserDto;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 아이디 중복 체크 버튼 기능 (파라미터 -> 비교할 회원 아이디)
    // 버튼 클릭시 ajax로 get방식으로 받아와서 중복 확인 리턴 값이 1이면 중복 0이면 사용가능 창 띄우기
	// ajax 처리시 반환값 다시 생각해보기
    public int checkDuplicateId(String userId) {
        boolean trueOrFalse = userRepository.existsByUserId(userId);
        if(trueOrFalse == true){
            System.out.println("duplicate ID!!");
            return 1;
        }
        else{
            System.out.println("possible ID!!");
            return 0;
        }
    }

    // 닉네임 중복 체크 버튼 기능 (파라미터 -> 비교할 닉네임)
    // 버튼 클릭시 ajax로 get방식으로 받아와서 중복 확인 리턴 값이 1이면 중복 0이면 사용가능 창 띄우기
	// ajax 처리시 반환값 다시 생각해보기
    public int checkDuplicateNickName(String userNickName) {
        boolean trueOrFalse = userRepository.existsByUserNickName(userNickName);
        if(trueOrFalse == true){
            System.out.println("duplicate NickName!!");
            return 1;
        }
        else{
            System.out.println("possible NickName!!");
            return 0;
        }
    }

    // 회원가입 (회원가입 성공)
    // 아이디, 닉네임 중복 체크 확인 후 저장 할 수 있도록 수정해야함
    public UserEntity save(UserDto userDto) {
        UserEntity entity = userDto.toEntity();
        userRepository.save(entity);

        return entity;
    }

    // 아이디 찾기 (이름과 전화번호)
	public String findId(String userName, String userPhoneNumber) {
		Optional<UserEntity> findId = userRepository.findByUserNameAndUserPhoneNumber(userName, userPhoneNumber);
        String id = findId.get().getUserId();

        return id;
	}

    // 비밀번호 찾기(아이디와 이름)
    // 비밀번호 찾기 페이지에서 필요한 데이터 입력 후
	public Optional<UserEntity> findPassword(String userId, String userName) {
		Optional<UserEntity> findPassword = userRepository.findByUserIdAndUserName(userId, userName);

        return findPassword;
	}

    // 비밀번호 변경
    // 비밀번호를 바꿀 회원정보를 찾은 후 변경하는 페이지때 사용
    // 파라미터 변경 생각해보기
	public Optional<UserEntity> changePassword(Optional<UserEntity> findInfo, String changePassword) {
        findInfo.map(p -> {
            findInfo.get().setUserPassword(changePassword);

            return p;
        })
            .map(p -> userRepository.save(p));

        return findInfo;
	}

    // 회원정보 보기
    public Optional<UserEntity> findUserInfo(Long userIndex) {
        Optional<UserEntity> findUser = userRepository.findById(userIndex);

        return findUser;
    }

    // 회원정보 수정
	public Optional<UserEntity> changeUserInfo(Optional<UserEntity> user) {
        user.map(p -> {
            user.get().setUserName("윤하연");
            user.get().setUserNickName("윤공주");
            user.get().setUserPhoneNumber("01093195367");
        
            return p;
        })
        .map(p -> userRepository.save(p));

        return user;

    }

    // 회원 탈퇴
    // 회원 탈퇴시 회원이 쓴 게시글, 댓글도 다 삭제
	public void deleteUserInfo(Long userIndex) {

        userRepository.deleteById(userIndex);
	}
    
    // 로그인 기능
    public Long login(String userId, String userPassword) {
        Optional<UserEntity> user = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        Long userIndex = user.get().getUserIndex();
        return userIndex;
    }
}
