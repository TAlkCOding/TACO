package sideproject.talkcoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>{

    List<ReplyEntity> findByReplyPostIndex(Long replyPostIndex);
    
}
