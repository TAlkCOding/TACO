package sideproject.talkcoding.service.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sideproject.talkcoding.model.dto.post.ReplyDto;
import sideproject.talkcoding.model.entity.post.ReplyEntity;

@SpringBootTest
@Slf4j
public class ReplyServiceTest {
    
    @Autowired
    private ReplyService replyService;
    
    @Test
    @Transactional
    void testSave() {
        //given
        ReplyDto reply = ReplyDto.builder()
            .replyDescription("first reply")
            .build();

        Long userIndex = 1L;
        Long postIndex = 1L;

        //when
        ReplyEntity expected = replyService.save(reply, userIndex, postIndex);
        log.info(expected.getReplyIndex().toString());

        //then
        assertThat(expected.getReplyDescription()).isEqualTo(reply.getReplyDescription());
        assertThat(expected.getReplyPostIndex()).isEqualTo(postIndex);
        assertThat(expected.getReplyUserIndex()).isEqualTo(userIndex);
    }

    @Test
    @Transactional
    void testRead() {
        //given
        ReplyDto reply1 = ReplyDto.builder()
        .replyDescription("first reply")
        .build();

        ReplyDto reply2 = ReplyDto.builder()
        .replyDescription("second reply")
        .build();

        Long userIndex1 = 1L;
        Long userIndex2 = 2L;
        Long postIndex = 1L;

        //when
        replyService.save(reply1, userIndex1, postIndex);
        replyService.save(reply2, userIndex2, postIndex);

        List<ReplyEntity> replyList = replyService.read(postIndex);

        //then
        assertThat(replyList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void testEdit() {
        //given
        ReplyDto reply1 = ReplyDto.builder()
        .replyDescription("first reply")
        .build();

        Long userIndex1 = 1L;
        Long postIndex = 1L;

        ReplyEntity reply = replyService.save(reply1, userIndex1, postIndex);

        //when
        ReplyDto reply2 = ReplyDto.builder()
        .replyDescription("second reply")
        .build();

        Optional<ReplyEntity> expected = replyService.edit(reply.getReplyIndex(), reply2);
    
        //then
        assertThat(expected.get().getReplyDescription()).isEqualTo(reply2.getReplyDescription());
        assertThat(expected.get().getReplyIndex()).isEqualTo(reply.getReplyIndex());
        assertThat(expected.get().getReplyPostIndex()).isEqualTo(reply.getReplyPostIndex());
        
    }

    
    @Test
    @Transactional
    void testDelete() {
        //given
        ReplyDto reply1 = ReplyDto.builder()
        .replyDescription("first reply")
        .build();

        Long userIndex1 = 1L;
        Long postIndex = 1L;

        ReplyEntity reply = replyService.save(reply1, userIndex1, postIndex);

        //when
        replyService.delete(reply.getReplyIndex());

        //then
        List<ReplyEntity> read = replyService.read(1L);
        
        assertThat(read).isEmpty();
    }
}
