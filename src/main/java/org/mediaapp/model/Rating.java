package org.mediaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score; // pl. 1-10 vagy 1-5 csillag
    private String comment;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    public Rating() { }

    public Rating(int score, String comment, Media media) {
        this.score = score;
        this.comment = comment;
        this.media = media;
    }

    // Getterek és setterek
    public Long getId() {
        return id;
    }

    public void setId(Long ratingid) {
        this.id = ratingid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int ratingscore) {
        this.score = ratingscore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String ratingcomment) {
        this.comment = ratingcomment;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media ratingmedia) {
        this.media = ratingmedia;
    }
}
