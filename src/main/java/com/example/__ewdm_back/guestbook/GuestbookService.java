package com.example.__ewdm_back.guestbook;

import com.example.__ewdm_back.guestbook.dto.GuestBookListResponse;
import com.example.__ewdm_back.guestbook.dto.GuestBookPostRequest;
import com.example.__ewdm_back.guestbook.dto.GuestbookResponse;
import com.example.__ewdm_back.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class GuestbookService {
    @Autowired
    GuestbookRepository guestbookRepository;
    @Autowired
    ImageRepository imageRepository;

    public ArrayList<GuestBookListResponse> getGuestbookList() {
        ArrayList<GuestBookListResponse> guestBookListResponses = new ArrayList<>();
        ArrayList<Guestbook> guestbooks = guestbookRepository.findAll();
        for (Guestbook guestbook: guestbooks)
            guestBookListResponses.add(new GuestBookListResponse(
                    guestbook.getPostId(),
                    guestbook.getNickname(),
                    guestbook.getTitle(),
                    guestbook.getBody(),
                    (ArrayList<String>) imageRepository.findLinksByGuestbook(guestbook.getPostId()),
                    guestbook.getCreatedAt()
            ));
        return guestBookListResponses;
    }

    public GuestbookResponse getGuestbook(Integer postId) {
        Guestbook guestbook = guestbookRepository.findById(postId).orElse(null);
        if (guestbook==null) return null;
        GuestbookResponse guestbookResponse = new GuestbookResponse(
                guestbook.getPostId(),
                guestbook.getNickname(),
                guestbook.getTitle(),
                guestbook.getBody(),
                (ArrayList<String>) imageRepository.findLinksByGuestbook(guestbook.getPostId()),
                guestbook.getCreatedAt()
        );
        return guestbookResponse;
    }

    public Guestbook postGuestbook(GuestBookPostRequest guestBookPostRequest) {
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        Guestbook guestbook = new Guestbook(
                null,
                guestBookPostRequest.getTitle(),
                guestBookPostRequest.getBody(),
                guestBookPostRequest.getNickname(),
                guestBookPostRequest.getPassword(),
                dateTimeFormatter.format(localTime)
        );
        Guestbook saved = guestbookRepository.save(guestbook);
        return saved;
    }
}
