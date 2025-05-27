package org.mediaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mediaapp.model.Media;
import org.mediaapp.model.Rating;
import org.mediaapp.repository.MediaRepository;
import org.mediaapp.repository.RatingRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class RatingServiceTest {

    private RatingRepository ratingRepository;
    private MediaRepository mediaRepository;
    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingRepository = mock(RatingRepository.class);
        mediaRepository = mock(MediaRepository.class);
        ratingService = new RatingService(ratingRepository, mediaRepository);
    }

    @Test
    void testGetAll() {
        Rating rating1 = mock(Rating.class);
        Rating rating2 = mock(Rating.class);
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<Rating> all = ratingService.getAll();
        assertEquals(2, all.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        Rating rating = mock(Rating.class);
        when(ratingRepository.findById(1L)).thenReturn(Optional.of(rating));

        Rating result = ratingService.getById(1L);
        assertNotNull(result);
        verify(ratingRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(ratingRepository.findById(100L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> ratingService.getById(100L));
        String expectedMessage = "Rating not found with id 100";
        assertTrue(exception.getMessage().contains("Rating not found with id"));
    }

    @Test
    void testCreate() {
        Rating rating = mock(Rating.class);
        Media media = mock(Media.class);
        when(media.getId()).thenReturn(1L);
        when(rating.getMedia()).thenReturn(media);

        when(mediaRepository.findById(1L)).thenReturn(Optional.of(media));
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.create(rating);
        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void testUpdate() {
        Rating existing = mock(Rating.class);
        when(ratingRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(ratingRepository.save(existing)).thenReturn(existing);

        Rating updates = mock(Rating.class);
        Media updateMedia = mock(Media.class);
        when(updateMedia.getId()).thenReturn(2L);
        when(updates.getMedia()).thenReturn(updateMedia);
        when(updates.getScore()).thenReturn(5);
        when(updates.getComment()).thenReturn("Updated");

        when(mediaRepository.findById(2L)).thenReturn(Optional.of(updateMedia));

        Rating result = ratingService.update(1L, updates);

        verify(existing, times(1)).setScore(5);
        verify(existing, times(1)).setComment("Updated");
        verify(existing, times(1)).setMedia(updateMedia);
        verify(ratingRepository, times(1)).save(existing);
        assertEquals(existing, result);
    }

    @Test
    void testDelete() {
        doNothing().when(ratingRepository).deleteById(1L);
        ratingService.delete(1L);
        verify(ratingRepository, times(1)).deleteById(1L);
    }
}