package com.example.__ewdm_back.comment;

import com.example.__ewdm_back.comment.dto.CommentListResponse;
import com.example.__ewdm_back.comment.dto.CommentPostRequest;
import com.example.__ewdm_back.comment.dto.CommentPostResponse;
import com.example.__ewdm_back.comment.dto.CommentPutRequest;
import com.example.__ewdm_back.guestbook.Guestbook;
import com.example.__ewdm_back.guestbook.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    GuestbookRepository guestbookRepository;

    public ArrayList<CommentListResponse> getComments(Integer postId) {
        ArrayList<CommentListResponse> commentListResponses = new ArrayList<>();
        ArrayList<Comment> comments = commentRepository.findAllByPostId(postId);
        for (Comment comment : comments)
            commentListResponses.add(new CommentListResponse(
                    comment.getCommentId(),
                    comment.getBody(),
                    comment.getNickname(),
                    comment.getCreatedAt()
            ));
        return commentListResponses;
    }

    public CommentPostResponse postComment(Integer postId, CommentPostRequest commentPostRequest) {
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        Guestbook target = guestbookRepository.findById(postId).orElse(null);
        if (target==null) return null;
        Comment comment = new Comment(
                null,
                target,
                commentPostRequest.getBody(),
                commentPostRequest.getNickname(),
                commentPostRequest.getPassword(),
                dateTimeFormatter.format(localTime)
        );
        if (comment==null) return null;
        Comment saved = commentRepository.save(comment);
        CommentPostResponse commentPostResponse = new CommentPostResponse(saved.getCommentId());
        return commentPostResponse;
    }

    public Comment updateComment(Integer postId, Integer commentId, CommentPutRequest commentPutRequest) {
        Comment target = commentRepository.findById(commentId).orElse(null);
        if (target==null) return null;
        if (!target.getGuestbook().getPostId().equals(postId)) return null;

        target.setNickname(commentPutRequest.getNickname());
        target.setBody(commentPutRequest.getBody());
        target.setPassword(commentPutRequest.getPassword());
        Comment updated = commentRepository.save(target);
        return updated;
    }

    public Comment deleteComment(Integer postId, Integer commentId) {
        Comment deleted = commentRepository.findById(commentId).orElse(null);
        if (deleted==null) return null;
        if (!deleted.getGuestbook().getPostId().equals(postId)) return null;
        commentRepository.deleteById(commentId);
        return deleted;
    }
}
