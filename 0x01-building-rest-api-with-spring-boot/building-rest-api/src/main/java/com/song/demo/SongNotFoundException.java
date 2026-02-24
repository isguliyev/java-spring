package com.song.demo;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long id) {
        super(String.format("Song not found. No song exists with id: %d", id));
    }
}