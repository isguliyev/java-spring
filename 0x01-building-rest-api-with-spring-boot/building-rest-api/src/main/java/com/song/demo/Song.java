package com.song.demo;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String artist;
    private String album;
    private LocalDate releaseDate;

    public Song() {}

    public Song(
        String name,
        String artist,
        String album,
        LocalDate releaseDate
    ) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Song)) {
            return false;
        }

        Song song = (Song) object;

        return this.id != null && this.id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return String.format(
            "%s [id=%d, name=%s, artist=%s, album=%s, releaseDate=%s]",
            this.getClass().getSimpleName(),
            this.id,
            this.name,
            this.artist,
            this.album,
            this.releaseDate
        );
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}