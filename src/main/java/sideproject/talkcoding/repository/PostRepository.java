package sideproject.talkcoding.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{

    // 프로필 수정 시 자신이 쓴 모든 댓글, 게시글 프로필 바꿀 때 쓸 메서드

    // 내 게시글 목록 가져오기
    List<PostEntity> findByUserIndex(Long userIndex);

    // 게시글 제목 검색 기능
    Page<PostEntity> findByTitleContaining(String keyword, Pageable pagealbe);

    // 시/군/동에 따른 게시글들 목록 페이지네이션
    Page<PostEntity> findByPostSidoContaining(String postSido, Pageable pagealbe);

    Page<PostEntity> findByPostSidoContainingAndPostGugunContaining(String postSido, String postGugun, Pageable pageable);

    Page<PostEntity> findByPostSidoContainingAndPostGugunContainingAndPostDongContaining(String postSido, String postGugun, String postDong, Pageable pageable);
}
