package model.metronome;

public interface Metronome {

    void start();
    void stop();
    boolean isRunning();
    void setTempo(int beatsPerMinute);
}
