package sideproject.talkcoding.model.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.entity.post.ReplyEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    
    private Long replyIndex;

    private String replyDescription;

    private LocalDateTime replyRegDate;

    private Long replyUserIndex;

    private Long replyPostIndex;


    public ReplyEntity toEntity(Long replyUserIndex, Long replyPostIndex){
        ReplyEntity replyEntity = ReplyEntity.builder()
        .replyDescription(replyDescription)
        .replyRegDate(replyRegDate)
        .replyUserIndex(replyUserIndex)
        .replyPostIndex(replyPostIndex)
        .build();

        return replyEntity;
    }
}
