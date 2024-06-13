package com.example.__ewdm_back.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostRequest {
    private String body;
    private String nickname;
    private String password;
}
