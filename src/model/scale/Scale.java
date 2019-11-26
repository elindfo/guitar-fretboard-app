package model.scale;

import model.Note;

import java.util.List;

public interface Scale {

    List<Note> getNotes();
    Note getKey();
    String getScaleName();
}
