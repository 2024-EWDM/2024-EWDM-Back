package com.example.__ewdm_back.image;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
    @Query("SELECT i.link FROM Image i WHERE i.postId.postId = :postId")
    List<String> findLinksByGuestbook(@Param("postId") Integer postId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Image i WHERE i.postId.postId = :postId")
    void deleteByGuestbookId(@Param("postId") Integer postId);
}
