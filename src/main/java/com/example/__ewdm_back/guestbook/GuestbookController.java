package com.example.__ewdm_back.guestbook;

import com.example.__ewdm_back.guestbook.dto.GuestBookListResponse;
import com.example.__ewdm_back.guestbook.dto.GuestBookPostRequest;
import com.example.__ewdm_back.guestbook.dto.GuestbookResponse;
import com.example.__ewdm_back.image.Image;
import com.example.__ewdm_back.image.S3Service;
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
    @Autowired
    private S3Service s3Service;

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

    @PostMapping(value = "/guestbook/post", consumes = {"multipart/form-data"})
    public ResponseEntity<?> postGuestbook(@RequestPart(name="data") GuestBookPostRequest guestBookPostRequest, @RequestPart(name="file") List<MultipartFile> multipartFilelist) {
        List<Image> images;
        Guestbook guestbook = guestbookService.postGuestbook(guestBookPostRequest);
        if (guestbook == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 생성에 실패했습니다.");

        if (multipartFilelist != null) {
            images = s3Service.postGuestbook(guestbook, multipartFilelist);
            if (images == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사진 등록에 실패했습니다.");
        }

        // 여기서부터 작업 더해야 함
        return null;
    }
}
