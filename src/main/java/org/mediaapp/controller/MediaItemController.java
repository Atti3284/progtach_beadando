package org.mediaapp.controller;

import org.mediaapp.model.MediaItem;
import org.mediaapp.service.MediaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaItemController {
    private final MediaService mediaService;

    public MediaController (MediaService mediaService){
        this.mediaService = mediaService;
    }
    @GetMapping
    public List<MediaItem> GetAllMedia(){
        return mediaService.getAll();
    }
    @PostMapping
    public MediaItem createMedia(@RequestBody MediaItem mediaItem){
        return mediaService.create(mediaItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable Long id){
        mediaService.delete(id);
    }
}
