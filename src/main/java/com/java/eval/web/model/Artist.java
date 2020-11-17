package com.java.eval.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {

    // Attributs
    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArtistId")
    public Integer id;


    public String name;

    // Constructeurs

    public Artist() {
    }

    public Artist(List<Album> albums, String name) {
        this.albums = albums;
        this.name = name;
    }

    // Getter & Setter

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(albums, artist.albums) &&
                Objects.equals(id, artist.id) &&
                Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", name='" + name + '\'' +
                '}';
    }
}
