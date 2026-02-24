package com.song.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongRepository songRepository;
    private final SongModelAssembler songModelAssembler;

    public SongController(SongRepository songRepository, SongModelAssembler songModelAssembler) {
        this.songRepository = songRepository;
        this.songModelAssembler = songModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Song>> getAllSongs(Pageable pageable) {
        return CollectionModel.of(
            this.songModelAssembler.toCollectionModel(this.songRepository.findAll(pageable)),
            linkTo(methodOn(SongController.class).getAllSongs(pageable)).withSelfRel()
        );
    }

    @GetMapping(path = "/{id}")
    public EntityModel<Song> findSongById(@PathVariable Long id) {
        return this.songModelAssembler.toModel(
            this.songRepository.findById(id).orElseThrow(
                () -> new SongNotFoundException(id)
            )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Song> addSong(@RequestBody Song song) {
        return this.songModelAssembler.toModel(this.songRepository.save(song));
    }

    @PutMapping(path = "/{id}")
    public EntityModel<Song> updateSong(
        @RequestBody Song song,
        @PathVariable Long id
    ) {
        Song songToUpdate = songRepository.findById(id).orElseThrow(
            () -> new SongNotFoundException(id)
        );

        songToUpdate.setName(song.getName());
        songToUpdate.setArtist(song.getArtist());
        songToUpdate.setAlbum(song.getAlbum());
        songToUpdate.setReleaseDate(song.getReleaseDate());

        return this.songModelAssembler.toModel(this.songRepository.save(songToUpdate));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSongById(@PathVariable Long id) {
        if (!this.songRepository.existsById(id)) {
            throw new SongNotFoundException(id);
        }

        this.songRepository.deleteById(id);
    }
}