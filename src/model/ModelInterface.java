package model;


import model.metronome.AppMetronome;
import model.metronome.Metronome;

public class ModelInterface {

    private Metronome metronome;

    public ModelInterface(){
        metronome = new AppMetronome(250);
    }

    public Metronome getMetronome() {
        return metronome;
    }
}
