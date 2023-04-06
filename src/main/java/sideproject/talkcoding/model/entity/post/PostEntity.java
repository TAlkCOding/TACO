package sideproject.talkcoding.model.entity.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class PostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @CreationTimestamp //INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.
    @Column
    private LocalDateTime postRegDate;

    @Column
    private String postSido;

    @Column
    private String postGugun;

    @Column
    private String postDong;

    
}
