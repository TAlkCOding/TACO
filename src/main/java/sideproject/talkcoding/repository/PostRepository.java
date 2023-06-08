package sideproject.talkcoding.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.talkcoding.model.entity.post.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    
    // 시/군/동에 따른 게시글 검색
    List<PostEntity> findByPostSido(String postSido);

    List<PostEntity> findByPostSidoAndPostGugun(String postSido, String postGugun);

    List<PostEntity> findByPostSidoAndPostGugunAndPostDong(String postSido, String postGugun, String postDong);

    // 내 게시글 목록 가져오기
    List<PostEntity> findByUserIndex(Long userIndex);

    // 게시글 제목 검색 기능
    List<PostEntity> findByTitleContaining(String keyword);

    Page<PostEntity> findByPostSidoContaining(String postSido, Pageable pagealbe);

    Page<PostEntity> findByPostSidoContainingAndPostGugunContaining(String postSido, String postGugun, Pageable pageable);

    Page<PostEntity> findByPostSidoContainingAndPostGugunContainingAndPostDongContaining(String postSido, String postGugun, String postDong, Pageable pageable);
}
