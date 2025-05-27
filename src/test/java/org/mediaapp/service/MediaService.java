package org.mediaapp.service;

import org.mediaapp.model.Media;
import org.mediaapp.repository.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class MediaServiceTest {

    private MediaService mediaService;
    private MediaRepository mediaRepository;

    @BeforeEach
    void setUp() {
        mediaRepository = mock(MediaRepository.class);
        mediaService = new MediaService(mediaRepository);
    }

    @Test
    void testCreateMedia() {
        Media media = new Media();
        media.setTitle("Test Movie");
        media.setType("film");

        when(mediaRepository.save(media)).thenReturn(media);

        Media saved = mediaService.createMedia(media);
        assertEquals("Test Movie", saved.getTitle());
        verify(mediaRepository, times(1)).save(media);
    }
}
