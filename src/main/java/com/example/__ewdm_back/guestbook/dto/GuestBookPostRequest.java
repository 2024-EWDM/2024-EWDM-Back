package com.example.__ewdm_back.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookPostRequest {
    private String title;
    private String body;
    private String nickname;
}
