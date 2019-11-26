package model;

public enum Note {
    A(0, "A"),
    AISS(1, "A#"),
    AISSISS(2, "Ax"),
    BESSESS(0, "Bbb"),
    BESS(1, "Bb"),
    B(2, "B"),
    BISS(3, "B#"),
    BISSISS(4, "Bx"),
    CESSESS(1, "Cbb"),
    CESS(2, "Cb"),
    C(3, "C"),
    CISS(4, "C#"),
    CISSISS(5, "Cx"),
    DESSESS(3, "Dbb"),
    DESS(4, "Db"),
    D(5, "D"),
    DISS(6, "D#"),
    DISSISS(7, "Dx"),
    ESSESS(5, "Ebb"),
    ESS(6, "Eb"),
    E(7, "E"),
    EISS(8, "E#"),
    EISSISS(9, "Ex"),
    FESSESS(6, "Fbb"),
    FESS(7, "Fb"),
    F(8, "F"),
    FISS(9, "F#"),
    FISSISS(10, "Fx"),
    GESSESS(8, "Gbb"),
    GESS(9, "Gb"),
    G(10, "G"),
    GISS(11, "G#"),
    GISSISS(0, "Gx"),
    ASSASS(10, "Abb"),
    ASS(11, "Ab");

    private int numVal;
    private String desciption;
    private Note sharp;
    private Note flat;

    Note(int numVal, String description){
        this.numVal = numVal;
        this.desciption = description;
    }

    static{
        A.sharp = AISS;
        B.sharp = BISS;
        C.sharp = CISS;
        D.sharp = DISS;
        E.sharp = EISS;
        F.sharp = FISS;
        G.sharp = GISS;
        AISS.flat = A;
        BISS.flat = B;
        CISS.flat = C;
        DISS.flat = D;
        EISS.flat = E;
        FISS.flat = F;
        GISS.flat = G;
        A.flat = ASS;
        B.flat = BESS;
        C.flat = CESS;
        D.flat = DESS;
        E.flat = ESS;
        F.flat = FESS;
        G.flat = GESS;
        ASS.sharp = A;
        BESS.sharp = B;
        CESS.sharp = C;
        DESS.sharp = D;
        ESS.sharp = E;
        FESS.sharp = F;
        GESS.sharp = G;

        AISS.sharp = AISSISS;
        BISS.sharp = BISSISS;
        CISS.sharp = CISSISS;
        DISS.sharp = DISSISS;
        EISS.sharp = EISSISS;
        FISS.sharp = FISSISS;
        GISS.sharp = GISSISS;
        AISSISS.flat = AISS;
        BISSISS.flat = BISS;
        CISSISS.flat = CISS;
        DISSISS.flat = DISS;
        EISSISS.flat = EISS;
        FISSISS.flat = FISS;
        GISSISS.flat = GISS;
        ASS.flat = ASSASS;
        BESS.flat = BESSESS;
        CESS.flat = CESSESS;
        DESS.flat = DESSESS;
        ESS.flat = ESSESS;
        FESS.flat = FESSESS;
        GESS.flat = GESSESS;
        ASSASS.sharp = ASS;
        BESSESS.sharp = BESS;
        CESSESS.sharp = CESS;
        DESSESS.sharp = DESS;
        ESSESS.sharp = ESS;
        FESSESS.sharp = FESS;
        GESSESS.sharp = GESS;
    }

    public int getNumVal(){
        return this.numVal;
    }

    public String getDesciption(){
        return this.desciption;
    }

    public Note getSharp(){
        return sharp;
    }

    public Note getFlat(){
        return flat;
    }

    @Override
    public String toString(){
        return String.format("%s", this.getDesciption());
    }
}
