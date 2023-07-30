package sideproject.talkcoding.model.entity.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp //INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.
    @Column
    private LocalDateTime replyRegDate;

    @Column
    @NotNull
    private Long replyUserIndex;

    @Column
    @NotNull
    private String userNickName;

    @Column
    @NotNull
    private Long replyPostIndex;
    

    public ReplyDto toDto(){
        ReplyDto replyDto = ReplyDto.builder()
        .replyDescription(replyDescription)
        .replyRegDate(replyRegDate)
        .replyUserIndex(replyUserIndex)
        .replyPostIndex(replyPostIndex)
        .userNickName(userNickName)
        .build();

        return replyDto;
    }
}
