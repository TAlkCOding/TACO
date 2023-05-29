package sideproject.talkcoding.model.entity.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.dto.post.ReplyDto;

@Entity(name = "reply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class ReplyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyIndex;
    
    @Column
    @NotNull
    private String replyDescription;

    @Column
    private LocalDateTime replyRegDate;

    @Column
    private Long replyUserIndex;

    @Column
    private Long replyPostIndex;
    

    public ReplyDto toDto(){
        ReplyDto replyDto = ReplyDto.builder()
        .replyDescription(replyDescription)
        .replyRegDate(replyRegDate)
        .replyUserIndex(replyUserIndex)
        .replyPostIndex(replyPostIndex)
        .build();

        return replyDto;
    }
}
