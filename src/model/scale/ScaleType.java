package model.scale;

public enum ScaleType {
    MAJOR("Major"),
    IONIAN("Ionian"),
    DORIAN("Dorian");

    final String description;

    ScaleType(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return description;
    }
}
