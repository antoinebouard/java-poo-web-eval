package com.java.eval.web.service;

import com.java.eval.web.model.Album;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public Album addAlbum(Album album){
        if (albumRepository.findByTitle(album.getTitle()) != null) {
            throw new EntityExistsException("Un album avec le même nom existe déjà !");
        }
        if (artistRepository.findByName(album.getArtist().name) == null) {
            throw new EntityNotFoundException("L'artiste " + album.getArtist().name + " n'existe pas !");
        }
        return albumRepository.save(album);
    }

    public void deleteAlbum(Integer id) { albumRepository.deleteById(id);}
}
