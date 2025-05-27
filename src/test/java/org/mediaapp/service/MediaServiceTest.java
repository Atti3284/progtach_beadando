package org.mediaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mediaapp.model.Media;
import org.mediaapp.repository.MediaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MediaServiceTest {
    private MediaRepository mediaRepository;
    private MediaService mediaService;

    @BeforeEach
    void setUp(){
        mediaRepository = mock(MediaRepository.class);
        mediaService = new MediaService(mediaRepository);
    }

    @Test
    void testGetAll(){
        Media media1 = new Media();
        Media media2 = new Media();
        when(mediaRepository.findAll()).thenReturn(Arrays.asList(media1, media2));

        List<Media> all = mediaService.getAll();
        assertEquals(2, all.size());
        verify(mediaRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        Media media = new Media();
        when(mediaRepository.findById(1L)).thenReturn(Optional.of(media));

        Media result = mediaService.getById(1L);
        assertNotNull(result);
        verify(mediaRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(mediaRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            mediaService.getById(99L);
        });

        String expectedMessage = "Media not found with ID: 99";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testCreate() {
        Media media = new Media();
        when(mediaRepository.save(media)).thenReturn(media);

        Media result = mediaService.create(media);
        assertEquals(media, result);
        verify(mediaRepository, times(1)).save(media);
    }

    @Test
    void testUpdate() {
        Media existing = new Media();
        existing.setTitle("Old Title");
        when(mediaRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(mediaRepository.save(any(Media.class))).thenReturn(existing);

        Media update = new Media();
        update.setTitle("New Title");

        Media result = mediaService.update(1L, update);
        assertEquals("New Title", result.getTitle());
        verify(mediaRepository, times(1)).save(existing);
    }

    @Test
    void testDelete() {
        doNothing().when(mediaRepository).deleteById(1L);
        mediaService.delete(1L);
        verify(mediaRepository, times(1)).deleteById(1L);
    }
}
