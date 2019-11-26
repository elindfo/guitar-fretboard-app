package model.scale.collection;

import model.Note;
import model.scale.AbstractScale;
import model.scale.InvalidKeySignatureException;
import model.scale.ScaleCodeStep;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DorianScale extends AbstractScale {

    public static final List<Note> keySignatures = Arrays.asList(
            Note.D,
            Note.G,
            Note.C,
            Note.F,
            Note.BESS,
            Note.ESS,
            Note.ASS,
            Note.DESS,
            Note.DISS,
            Note.GISS,
            Note.CISS,
            Note.FISS,
            Note.B,
            Note.E,
            Note.A
    );

    public static final List<ScaleCodeStep> scaleSteps = Arrays.asList(
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.HALF,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.HALF,
            ScaleCodeStep.WHOLE
    );

    private static final Map<Note, List<Note>> dorianScales = Map.ofEntries(
            Map.entry(Note.D, Arrays.asList(Note.D, Note.E, Note.F, Note.G, Note.A, Note.B, Note.C)),
            Map.entry(Note.G, Arrays.asList(Note.G, Note.A, Note.BESS, Note.C, Note.D, Note.E, Note.F)),
            Map.entry(Note.C, Arrays.asList(Note.C, Note.D, Note.ESS, Note.F, Note.G, Note.A, Note.BESS)),
            Map.entry(Note.F, Arrays.asList(Note.F, Note.G, Note.ASS, Note.BESS, Note.C, Note.D, Note.ESS)),
            Map.entry(Note.BESS, Arrays.asList(Note.BESS, Note.C, Note.DESS, Note.ESS, Note.F, Note.G, Note.ASS)),
            Map.entry(Note.ESS, Arrays.asList(Note.ESS, Note.F, Note.GESS, Note.ASS, Note.BESS, Note.C, Note.DESS)),
            Map.entry(Note.ASS, Arrays.asList(Note.ASS, Note.BESS, Note.CESS, Note.DESS, Note.ESS, Note.F, Note.GESS)),
            Map.entry(Note.DESS, Arrays.asList(Note.DESS, Note.ESS, Note.FESS, Note.GESS, Note.ASS, Note.BESS, Note.CESS)),
            Map.entry(Note.DISS, Arrays.asList(Note.DISS, Note.EISS, Note.FISS, Note.GISS, Note.AISS, Note.BISS, Note.CISS)),
            Map.entry(Note.GISS, Arrays.asList(Note.GISS, Note.AISS, Note.B, Note.CISS, Note.DISS, Note.EISS, Note.FISS)),
            Map.entry(Note.CISS, Arrays.asList(Note.CISS, Note.DISS, Note.E, Note.FISS, Note.G, Note.AISS, Note.BISS)),
            Map.entry(Note.FISS, Arrays.asList(Note.FISS, Note.GISS, Note.A, Note.B, Note.CISS, Note.DISS, Note.E)),
            Map.entry(Note.B, Arrays.asList(Note.B, Note.CISS, Note.D, Note.E, Note.FISS, Note.GISS, Note.A)),
            Map.entry(Note.E, Arrays.asList(Note.E, Note.FISS, Note.G, Note.A, Note.B, Note.CISS, Note.D)),
            Map.entry(Note.A, Arrays.asList(Note.A, Note.B, Note.C, Note.D, Note.E, Note.FISS, Note.G))
    );

    private Note keySignature;

    private List<Note> scaleNotes;

    private static final String SCALE_NAME = "Dorian Scale";

    public DorianScale(Note keySignature){
        if(keySignatures.contains(keySignature)){
            this.keySignature = keySignature;
            scaleNotes = dorianScales.get(keySignature);
        }
        else{
            throw new InvalidKeySignatureException(keySignature.toString() + " is not a valid key signature");
        }
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
    public String getScaleName() {
        return SCALE_NAME;
    }
}
