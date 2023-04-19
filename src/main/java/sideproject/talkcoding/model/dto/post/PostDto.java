package sideproject.talkcoding.model.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.talkcoding.model.entity.post.PostEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postIndex;

    private String title;

    private String description;

    private LocalDateTime postRegDate;

    private String postSido;

    private String postGugun;

    private String postDong;

    private String postLanguage;

    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
        .title(title)
        .description(description)
        .postRegDate(postRegDate)
        .postSido(postSido)
        .postGugun(postGugun)
        .postDong(postDong)
        .postLanguage(postLanguage)
        .build();

        return postEntity;
    }
}
