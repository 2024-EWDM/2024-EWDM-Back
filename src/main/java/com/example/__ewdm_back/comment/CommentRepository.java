package com.example.__ewdm_back.comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.guestbook.postId = :postId")
    ArrayList<Comment> findAllByPostId(@Param("postId") Integer postId);
}
