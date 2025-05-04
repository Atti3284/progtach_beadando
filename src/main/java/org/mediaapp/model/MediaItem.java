package org.mediaapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private MediaType type;
    private String genre;
    private Integer year;
    private String coverUrl;
    @ManyToOne
    @JoinColumn(name = "added_by_id")
    private User addedBy;
    @OneToMany(mappedBy = "mediaItem", cascade = CascadeType.ALL)
    private List<Rating> ratings;
}
