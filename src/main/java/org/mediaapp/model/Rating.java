package org.mediaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
