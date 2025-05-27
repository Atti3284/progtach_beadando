package org.mediaapp.service;

import org.mediaapp.model.Rating;
import org.mediaapp.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository repository) {
        this.ratingRepository = repository;
    }

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public Rating getById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("Rating not found with id " + id));
    }

    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating update(Long id, Rating ratingDetails) {
        Rating rating = getById(id);
        rating.setScore(ratingDetails.getScore());
        rating.setComment(ratingDetails.getComment());
        rating.setMedia(ratingDetails.getMedia());
        return ratingRepository.save(rating);
    }

    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }
}
