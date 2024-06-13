package com.example.__ewdm_back.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookResponse {
    private Integer postId;
    private String nickname;
    private String title;
    private String body;
    private ArrayList<String> imageLinks;
    private String createdAt;
}
