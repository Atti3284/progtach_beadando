package org.mediaapp.controller;

import org.mediaapp.model.Rating;
import org.mediaapp.service.RatingService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService service) {
        this.ratingService = service;
    }

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    public Rating getRatingById(@PathVariable Long id) {
        return ratingService.getById(id);
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.create(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(
            @PathVariable Long id, @RequestBody Rating rating) {
        return ratingService.update(id, rating);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.delete(id);
    }
}
