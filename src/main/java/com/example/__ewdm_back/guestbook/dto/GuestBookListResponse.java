package com.example.__ewdm_back.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookListResponse {
    private Integer postId;
    private String nickname;
    private String title;
}
