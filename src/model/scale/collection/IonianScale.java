package model.scale.collection;

import model.Note;
import model.scale.AbstractScale;
import model.scale.Scale;
import model.scale.ScaleCodeStep;

import java.util.List;

public class IonianScale extends AbstractScale {

    public static final List<Note> keySignatures = MajorScale.keySignatures;

    public static final List<ScaleCodeStep> scaleSteps = MajorScale.scaleSteps;

    private static final String SCALE_NAME = "Ionian Scale";

    private Note keySignature;

    private List<Note> scaleNotes;

    public IonianScale(Note keySignature) {
        scaleNotes = (new MajorScale(keySignature)).getNotes();
        this.keySignature = keySignature;
    }

    @Override
    public List<Note> getNotes() {
        return scaleNotes;
    }

    @Override
    public Note getKey() {
        return keySignature;
    }

    @Override
    public String getScaleName(){
        return SCALE_NAME;
    }
}
