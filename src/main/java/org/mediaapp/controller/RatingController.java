package org.mediaapp.controller;

import org.mediaapp.model.Rating;
import org.mediaapp.service.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.create(rating);
    }

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getByUserId(@PathVariable Long userId) {
        return ratingService.getByUserId(userId);
    }

    @GetMapping("/media/{mediaItemId}")
    public List<Rating> getByMediaItemId(@PathVariable Long mediaItemId) {
        return ratingService.getByMediaItemId(mediaItemId);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.delete(id);
    }
}
