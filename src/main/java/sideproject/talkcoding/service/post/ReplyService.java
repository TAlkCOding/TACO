package sideproject.talkcoding.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sideproject.talkcoding.model.dto.post.ReplyDto;
import sideproject.talkcoding.model.entity.post.ReplyEntity;
import sideproject.talkcoding.repository.ReplyRepository;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    // 댓글 저장
    public ReplyEntity save(ReplyDto replyDto, Long userIndex, Long postIndex) {
        ReplyEntity replyEntity = replyDto.toEntity(userIndex, postIndex);

        replyRepository.save(replyEntity);

        return replyEntity;
    }

    // 게시글 읽어올 때 해당 게시글 댓글 불러오기
    public List<ReplyEntity> read(Long postIndex) {
        List<ReplyEntity> replyList = replyRepository.findByPostIndex(postIndex);

        return replyList;
    }

    public void delete(Long replyIndex) {

        replyRepository.deleteById(replyIndex);
    }
    
}
