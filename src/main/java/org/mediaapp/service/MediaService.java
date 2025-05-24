package org.mediaapp.service;

import org.mediaapp.model.Media;
import org.mediaapp.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getAll() {
        return mediaRepository.findAll();
    }

    public Media getById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Media not found with ID: " + id));
    }

    public Media create(Media media) {
        return mediaRepository.save(media);
    }

    public Media update(Long id, Media updatedMedia) {
        Media existing = getById(id);
        existing.setTitle(updatedMedia.getTitle());
        existing.setType(updatedMedia.getType());
        existing.setYear(updatedMedia.getYear());
        return mediaRepository.save(existing);
    }

    public void delete(Long id) {
        mediaRepository.deleteById(id);
    }
}
