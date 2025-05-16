package org.mediaapp.repository;

import org.mediaapp.model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaItem, Long>{
}
