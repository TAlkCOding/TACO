package sideproject.talkcoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    
}
