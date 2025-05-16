package org.mediaapp.service;

import org.mediaapp.model.Rating;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final List<Rating> ratings = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Rating create(Rating rating) {
        rating.setId(idGenerator.incrementAndGet());
        ratings.add(rating);
        return rating;
    }

    public List<Rating> getAll() {
        return ratings;
    }

    public List<Rating> getByUserId(Long userId) {
        return ratings.stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Rating> getByMediaItemId(Long mediaItemId) {
        return ratings.stream()
                .filter(r -> r.getMediaItemId().equals(mediaItemId))
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        return ratings.removeIf(r -> r.getId().equals(id));
    }
}
