package com.java.eval.web.repository;

import com.java.eval.web.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Album findByTitle(String title);

    List<Album> findByArtistId(Integer id);
}
