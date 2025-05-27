package org.mediaapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    public Rating(int scoreValue, String commentText, Media mediaObj) {
        this.score = scoreValue;
        this.comment = commentText;
        this.media = mediaObj;
    }
}
