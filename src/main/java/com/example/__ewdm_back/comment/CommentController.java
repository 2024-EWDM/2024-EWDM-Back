package com.example.__ewdm_back.comment;

import com.example.__ewdm_back.comment.dto.CommentListResponse;
import com.example.__ewdm_back.comment.dto.CommentPostRequest;
import com.example.__ewdm_back.comment.dto.CommentPostResponse;
import com.example.__ewdm_back.comment.dto.CommentPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/guestbook/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable("postId") Integer postId) {
        ArrayList<CommentListResponse> responses = commentService.getComments(postId);
        return (responses != null)?
                ResponseEntity.status(HttpStatus.OK).body(responses):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글을 불러오는 데 실패했습니다.");
    }

    @PostMapping("/guestbook/{postId}/comment")
    public ResponseEntity<?> postComment(@PathVariable("postId") Integer postId, @RequestBody CommentPostRequest commentPostRequest) {
        if (commentPostRequest.getBody()==null || commentPostRequest.getNickname()==null || commentPostRequest.getPassword()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("특정 내용이 없습니다.");
        CommentPostResponse response = commentService.postComment(postId, commentPostRequest);
        return (response != null)?
                ResponseEntity.status(HttpStatus.OK).body(response):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 작성에 실패했습니다.");
    }

    @PutMapping("/guestbook/{postId}/{commentId}/update")
    public ResponseEntity<?> updateComment(@PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId, @RequestBody CommentPutRequest commentPutRequest) {
        if (commentPutRequest.getBody()==null || commentPutRequest.getNickname()==null || commentPutRequest.getPassword()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("특정 내용이 없습니다.");
        Comment comment = commentService.updateComment(postId, commentId, commentPutRequest);
        return (comment != null)?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정에 실패했습니다.");
    }

    @DeleteMapping("/guestbook/{postId}/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId) {
        Comment deleted = commentService.deleteComment(postId, commentId);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
    }
}
