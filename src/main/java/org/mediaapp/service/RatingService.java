package org.mediaapp.service;

import org.mediaapp.model.Rating;
import org.mediaapp.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public List<Rating> getAll() {
        return repository.findAll();
    }

    public Optional<Rating> getById(Long id) {
        return repository.findById(id);
    }

    public Rating create(Rating rating) {
        return repository.save(rating);
    }

    public Rating update(Long id, Rating updated) {
        return repository.findById(id).map(rating -> {
            rating.setScore(updated.getScore());
            rating.setComment(updated.getComment());
            return repository.save(rating);
        }).orElseThrow(() -> new RuntimeException("Rating not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Rating> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Rating> getByMediaItemId(Long mediaItemId) {
        return repository.findByMediaItemId(mediaItemId);
    }
}
