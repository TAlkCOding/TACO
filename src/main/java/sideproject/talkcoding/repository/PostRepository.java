package sideproject.talkcoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    
}
