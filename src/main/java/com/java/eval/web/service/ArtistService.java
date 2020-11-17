package com.java.eval.web.service;

import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    public Artist findById(Integer id){
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()) {
            // Gestion 404
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé !");
        }
        return artist.get();
    }

    public List<Artist> findByNameLike(String name){
        List<Artist> artistList = artistRepository.findByNameLike(name);
        return artistList;
    }

    public Page<Artist> listArtists(Integer page, Integer size, String sortProperty, String sortDirection){
        if (page < 0){
            throw new IllegalArgumentException("La page doit être un entier positif ou nul !");
        }
        if (size <= 0 || size >50){
            throw new IllegalArgumentException("La taille doit être un entier positif compris entre 1 et 50 !");
        }
        if(!"ASC".equalsIgnoreCase(sortDirection) && !"DESC".equalsIgnoreCase((sortDirection))){
            throw new IllegalArgumentException("Le sens de tri doit valoir ASC ou DESC");
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    public Artist addArtist(Artist artist)
    {
        if (artistRepository.findByName(artist.getName()) != null)
        {
            throw new EntityExistsException("Un artiste avec le même nom existe déjà !");
        }
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Artist artist) {
        if (artistRepository.findById(artist.getId()).isEmpty())
        {
            throw new EntityNotFoundException("L'artiste que vous essayer de modifier n'existe pas");
        }
        return artistRepository.save(artist);
    }

    public void deleteArtist(Integer id) {
        if (artistRepository.findById(id).isEmpty())
        {
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas !");
        }
        List<Album> albumList = albumRepository.findByArtistId(id);
        for (Album album : albumList) {
            albumRepository.deleteById(album.getId());
        }
        artistRepository.deleteById(id);

    }
}
