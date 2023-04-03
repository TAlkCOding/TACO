package sideproject.talkcoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
