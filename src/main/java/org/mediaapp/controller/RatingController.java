package org.mediaapp.controller;

import org.mediaapp.model.Rating;
import org.mediaapp.model.User;
import org.mediaapp.model.MediaItem;
import org.mediaapp.repository.RatingRepository;
import org.mediaapp.repository.UserRepository;
import org.mediaapp.repository.MediaItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MediaItemRepository mediaItemRepository;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @PostMapping
    public Rating createRating(@RequestParam Long userId,
                               @RequestParam Long mediaItemId,
                               @RequestBody Rating rating) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található: " + userId));
        MediaItem item = mediaItemRepository.findById(mediaItemId)
                .orElseThrow(() -> new RuntimeException("Médiaelem nem található: " + mediaItemId));

        rating.setUser(user);
        rating.setMediaItem(item);
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @GetMapping("/{id}")
    public Rating getRatingById(@PathVariable Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nincs ilyen értékelés: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingRepository.deleteById(id);
    }
}
