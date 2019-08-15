package com.subhadeep.easynotes.controller;

import com.subhadeep.easynotes.exception.ResourceNotFoundException;
import com.subhadeep.easynotes.model.Note;
import com.subhadeep.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping(value="/notes", method = RequestMethod.GET)
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    @PostMapping("/notes/create")
    public Note createNode(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @RequestMapping(value="/notes/{id}", method = RequestMethod.GET)
    public Note getNoteById(@PathVariable(value="id") Long noteId) {
        return noteRepository.getOne(noteId);
    }

    @PutMapping("/notes/{id}")
    public Note updateNoteById(@PathVariable(value="id") Long noteId, @Valid @RequestBody Note note) {
        Note tempNote = noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        tempNote.setTitle(note.getTitle());
        tempNote.setContent(note.getContent());
        return noteRepository.save(tempNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value="id") Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Note", "id", noteId));
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}
