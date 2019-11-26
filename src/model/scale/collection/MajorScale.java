package model.scale.collection;

import model.Note;
import model.scale.AbstractScale;
import model.scale.InvalidKeySignatureException;
import model.scale.Scale;
import model.scale.ScaleCodeStep;

import java.util.*;

public class MajorScale extends AbstractScale{

    public static final List<Note> keySignatures = Arrays.asList(
            Note.C,
            Note.G,
            Note.D,
            Note.A,
            Note.E,
            Note.B,
            Note.FISS,
            Note.CISS,
            Note.F,
            Note.BESS,
            Note.ESS,
            Note.ASS,
            Note.DESS,
            Note.GESS,
            Note.CESS
    );

    public static final List<ScaleCodeStep> scaleSteps = Arrays.asList(
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.HALF,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.WHOLE,
            ScaleCodeStep.HALF
    );

    private static final Map<Note, List<Note>> majorScales = Map.ofEntries(
            Map.entry(Note.C, Arrays.asList(Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B)),
            Map.entry(Note.G, Arrays.asList(Note.G, Note.A, Note.B, Note.C, Note.D, Note.E, Note.FISS)),
            Map.entry(Note.D, Arrays.asList(Note.D, Note.E, Note.FISS, Note.G, Note.A, Note.B, Note.CISS)),
            Map.entry(Note.A, Arrays.asList(Note.A, Note.B, Note.CISS, Note.D, Note.E, Note.FISS, Note.GISS)),
            Map.entry(Note.E, Arrays.asList(Note.E, Note.FISS, Note.GISS, Note.A, Note.B, Note.CISS, Note.DISS)),
            Map.entry(Note.B, Arrays.asList(Note.B, Note.CISS, Note.DISS, Note.E, Note.FISS, Note.GISS, Note.AISS)),
            Map.entry(Note.FISS, Arrays.asList(Note.FISS, Note.GISS, Note.AISS, Note.B, Note.CISS, Note.DISS, Note.EISS)),
            Map.entry(Note.CISS, Arrays.asList(Note.CISS, Note.DISS, Note.EISS, Note.FISS, Note.GISS, Note.AISS, Note.BISS)),
            Map.entry(Note.F, Arrays.asList(Note.F, Note.G, Note.A, Note.BESS, Note.C, Note.D, Note.E)),
            Map.entry(Note.BESS, Arrays.asList(Note.BESS, Note.C, Note.D, Note.ESS, Note.F, Note.G, Note.A)),
            Map.entry(Note.ESS, Arrays.asList(Note.ESS, Note.F, Note.G, Note.ASS, Note.BESS, Note.G, Note.D)),
            Map.entry(Note.ASS, Arrays.asList(Note.ASS, Note.BESS, Note.C, Note.DESS, Note.ESS, Note.F, Note.G)),
            Map.entry(Note.DESS, Arrays.asList(Note.DESS, Note.ESS, Note.F, Note.GESS, Note.ASS, Note.BESS, Note.C)),
            Map.entry(Note.GESS, Arrays.asList(Note.GESS, Note.ASS, Note.BESS, Note.CESS, Note.DESS, Note.ESS, Note.F)),
            Map.entry(Note.CESS, Arrays.asList(Note.CESS, Note.DESS, Note.ESS, Note.FESS, Note.GESS, Note.ASS, Note.BESS))
    );

    private Note keySignature;

    private List<Note> scaleNotes;

    private static final String SCALE_NAME = "Major Scale";

    public MajorScale(Note keySignature){
        if(keySignatures.contains(keySignature)){
            this.keySignature = keySignature;
            scaleNotes = majorScales.get(keySignature);
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
