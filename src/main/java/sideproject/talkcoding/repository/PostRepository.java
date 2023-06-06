package sideproject.talkcoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    
    List<PostEntity> findByPostSido(String postSido);

    List<PostEntity> findByPostSidoAndPostGugun(String postSido, String postGugun);

    List<PostEntity> findByPostSidoAndPostGugunAndPostDong(String postSido, String postGugun, String postDong);

    List<PostEntity> findByUserIndex(Long userIndex);

    List<PostEntity> findByTitleContaining(String keyword);
}
