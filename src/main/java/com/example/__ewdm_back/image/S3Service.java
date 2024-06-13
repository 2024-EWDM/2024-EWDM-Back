package com.example.__ewdm_back.image;

import com.example.__ewdm_back.guestbook.Guestbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class S3Service {
    public List<Image> postGuestbook(Guestbook guestbook, List<MultipartFile> multipartFilelist) {

        return null;
    }

    public List<Image> updateGuestbook(Guestbook guestbook, List<MultipartFile> multipartFilelist) {

        return null;
    }
}
