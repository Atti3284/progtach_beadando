package org.mediaapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class MediaItem {

    private Long id;
    private String title;
    private String type;
    private String genre;
    private int releaseYear;
}
