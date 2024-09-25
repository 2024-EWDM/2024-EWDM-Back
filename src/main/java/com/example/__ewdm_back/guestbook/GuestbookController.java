package com.example.__ewdm_back.guestbook;

import com.example.__ewdm_back.guestbook.dto.GuestBookListResponse;
import com.example.__ewdm_back.guestbook.dto.GuestBookPostRequest;
import com.example.__ewdm_back.guestbook.dto.GuestBookPostResponse;
import com.example.__ewdm_back.guestbook.dto.GuestbookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestbookController {
    @Autowired
    private GuestbookService guestbookService;

    @GetMapping("/")
    public ResponseEntity<?> healthcheck() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/guestbook")
    public ResponseEntity<?> getGuestbookList() {
        ArrayList<GuestBookListResponse> responses = guestbookService.getGuestbookList();
        return (responses != null)?
                ResponseEntity.status(HttpStatus.OK).body(responses):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글을 불러오는 데 실패했습니다.");
    }

    @GetMapping("/guestbook/{postId}")
    public ResponseEntity<?> getGuestbook(@PathVariable Integer postId) {
        GuestbookResponse response = guestbookService.getGuestbook(postId);
        return (response != null)?
                ResponseEntity.status(HttpStatus.OK).body(response):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글을 불러오는 데 실패했습니다.");
    }

    @PostMapping("/guestbook/post")
    public ResponseEntity<?> postGuestbook(@RequestBody GuestBookPostRequest guestBookPostRequest) {
        // 1. 값 검증
        if (guestBookPostRequest.getTitle()==null
                || guestBookPostRequest.getBody()==null
                || guestBookPostRequest.getNickname()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("특정 값이 없습니다.");
        // 2. 게시글 생성
        Guestbook guestbook = guestbookService.postGuestbook(guestBookPostRequest);
        if (guestbook == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 생성에 실패했습니다.");
        else return ResponseEntity.status(HttpStatus.OK).body(new GuestBookPostResponse(guestbook.getPostId()));
    }
}
