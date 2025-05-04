package org.mediaapp.controller;

import org.mediaapp.model.MediaItem;
import org.mediaapp.repository.MediaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaItemController {
    private final MediaItemRepository mediaItemRepository;
    @GetMapping
    public List<MediaItem> GetAllMediaItems(){
        return mediaItemRepository.findAll();
    }
    @PostMapping
    public MediaItem createMediaItem(@RequestBody MediaItem mediaItem){
        return mediaItemRepository.save(mediaItem);
    }

    @GetMapping("/{id}")
    public MediaItem getById(@PathVariable Long id){
        return mediaItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nincs ilyen médiaelem: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteMediaItem(@PathVariable Long id){
        mediaItemRepository.deleteById(id);
    }
}
