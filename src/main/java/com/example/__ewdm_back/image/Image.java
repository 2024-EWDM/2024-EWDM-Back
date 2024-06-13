package com.example.__ewdm_back.image;

import com.example.__ewdm_back.guestbook.Guestbook;
import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Column
    private String link;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Guestbook postId;
}
