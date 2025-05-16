package org.mediaapp.model;

import lombok.Data;

@Data
public class Rating {
    private Long id;
    private Long userId;
    private Long mediaItemId;
    private int score;
    private String comment;
}
