package org.mediaapp.service;

import org.mediaapp.model.MediaItem;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MediaService {
    private final List<MediaItem> mediaItems = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<MediaItem> getAll(){
        return mediaItems;
    }

    public Optional<MediaItem> getById(Long id){
        return mediaItems.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public MediaItem create(MediaItem mediaItem){
        mediaItem.setId(idGenerator.incrementAndGet());
        mediaItem.add(mediaItem);
        return mediaItem;
    }

    public boolean delete(Long id){
        return mediaItems.removeIf(m -> m.getId().equals(id));
    }
}
