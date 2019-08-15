package com.subhadeep.easynotes.repository;

import com.subhadeep.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Note that, we have annotated the interface with @Repository annotation.
// This tells Spring to bootstrap the repository during component scan.
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
