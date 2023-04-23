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
import sideproject.talkcoding.model.dto.post.PostDto;

@Entity(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class PostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIndex;

    @Column(length = 1000)
    @NotNull
    private String title;

    @Column(length = 1000)
    @NotNull
    private String description;

    @CreationTimestamp //INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.
    @Column
    private LocalDateTime postRegDate;

    @Column(length = 1000)
    @NotNull
    private String postSido;

    @Column(length = 1000)
    @NotNull
    private String postGugun;

    @Column(length = 1000)
    @NotNull
    private String postDong;

    @Column(length = 1000)
    private String postLanguage; 
    
    public PostDto toDto() {
        PostDto postDto = PostDto.builder()
        .title(title)
        .description(description)
        .postRegDate(postRegDate)
        .postSido(postSido)
        .postGugun(postGugun)
        .postDong(postDong)
        .postLanguage(postLanguage)
        .build();

        return postDto;
    }
}
