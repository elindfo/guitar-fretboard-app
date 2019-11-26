package model;

public enum NoteModifier {
    FLAT("b"),
    SHARP("#"),
    NATURAL("n");

    private final String character;

    private NoteModifier(String character){
        this.character = character;
    }

    @Override
    public String toString(){
        return String.format("%s", character);
    }
}
