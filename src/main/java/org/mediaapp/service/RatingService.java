package org.mediaapp.service;

import org.mediaapp.model.Media;
import org.mediaapp.model.Rating;
import org.mediaapp.repository.MediaRepository;
import org.mediaapp.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final MediaRepository mediaRepository;

    public RatingService(RatingRepository rating0Repository,
                         MediaRepository media0Repository) {
        this.ratingRepository = rating0Repository;
        this.mediaRepository = media0Repository;
    }

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public Rating getById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new
                        RuntimeException("Rating not found with id "
                        + id));
    }

    public Rating create(Rating rating) {
        if (rating.getMedia() == null || rating.getMedia().getId() == null) {
            throw new IllegalArgumentException("A 'media' és 'id' kötelező!");
        }
        Long mediaId = rating.getMedia().getId();
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() ->
                        new RuntimeException("Media not found with id "
                        + mediaId));
        rating.setMedia(media);
        return ratingRepository.save(rating);
    }

    public Rating update(Long id, Rating ratingDetails) {
        Rating rating = getById(id);
        rating.setScore(ratingDetails.getScore());
        rating.setComment(ratingDetails.getComment());
        Long mediaId = ratingDetails.getMedia().getId();
        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() ->
                        new RuntimeException("Media not found with id "
                        + mediaId));
        rating.setMedia(media);
        return ratingRepository.save(rating);
    }

    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }
}
