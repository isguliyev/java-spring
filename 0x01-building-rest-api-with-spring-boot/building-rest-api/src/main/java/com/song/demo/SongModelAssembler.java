package com.song.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class SongModelAssembler implements RepresentationModelAssembler<Song, EntityModel<Song>> {
    @Override
    public EntityModel<Song> toModel(Song song) {
        return EntityModel.of(
            song,
            linkTo(methodOn(SongController.class).findSongById(song.getId())).withSelfRel(),
            linkTo(methodOn(SongController.class).getAllSongs(null)).withRel("songs")
        );
    }
}