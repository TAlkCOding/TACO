package sideproject.talkcoding.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 아이디 찾기 (이름과 전화번호로)
    Optional<UserEntity> findByUserNameAndUserPhoneNumber(String userName, String userPhoneNumber);

    // 비밀번호 찾기 (아이디와 이름으로)
    Optional<UserEntity> findByUserIdAndUserName(String userId, String userName);

    // 로그인 기능
    Optional<UserEntity> findByUserIdAndUserPassword(String userId, String userPassword);

    // 아이디 중복 시 찾기
    Optional<UserEntity> findByUserId(String userId);

    // 닉네임 중복 시 찾기
    Optional<UserEntity> findByUserNickName(String userNickName);
}
