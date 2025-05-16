package org.mediaapp.service;

import org.mediaapp.model.MediaItem;
import org.mediaapp.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {
    private final MediaRepository repository;

    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    public List<MediaItem> getAll() {
        return repository.findAll();
    }

    public Optional<MediaItem> getById(Long id) {
        return repository.findById(id);
    }

    public MediaItem create(MediaItem media) {
        return repository.save(media);
    }

    public MediaItem update(Long id, MediaItem updated) {
        return repository.findById(id).map(media -> {
            media.setTitle(updated.getTitle());
            media.setType(updated.getType());
            return repository.save(media);
        }).orElseThrow(() -> new RuntimeException("Media not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
