package com.example.__ewdm_back.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuestBookPostRequest {
    private String title;
    private String body;
    private String nickname;
    private String password;
}
