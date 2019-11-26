package model.metronome;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class AppMetronome implements Metronome{

    private int beatsPerMinute;
    private boolean running;
    private Runnable r;
    private MidiChannel midiChannel;

    public AppMetronome(int beatsPerMinute){
        this.beatsPerMinute = beatsPerMinute;
        r = new MetronomeRunnable(beatsPerMinuteToMillis(beatsPerMinute));
        running = false;

    }

    @Override
    public void start() {
        if(!running){
            running = true;
            Thread thread = new Thread(r);
            thread.start();
        }
    }

    @Override
    public void stop() {
        if(running){
            running = false;
            ((MetronomeRunnable)r).stop();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setTempo(int beatsPerMinute) {
        this.beatsPerMinute = beatsPerMinute;
        ((MetronomeRunnable)r).setSleepTimeInMillis(beatsPerMinuteToMillis(beatsPerMinute));
    }

    private int beatsPerMinuteToMillis(int beatsPerMinute){
        return 60000 / beatsPerMinute;
    }

    class MetronomeRunnable implements Runnable{

        private int sleepTimeInMillis;
        private boolean running;

        MetronomeRunnable(int sleepTimeInMillis){
            this.sleepTimeInMillis = sleepTimeInMillis;
        }

        @Override
        public void run() {
            if(!SoundGenerator.isInitialized()){
                SoundGenerator.init();
            }
            //System.out.println("STARTED METRONOME RUNNABLE");
            running = true;
            long timeToSleep;
            long t1 = 0, t2 = 0, totalOffset = 0, offsetCounter = 0, currentSleepTime = 0;
            while(running){
                currentSleepTime = timeToSleep = sleepTimeInMillis * 1000000 - totalOffset; //totalOffset is extra time waited last iteration (negative value)
                //System.out.println("Time to sleep: " + timeToSleep);

                offsetCounter = System.nanoTime();
                try {
                    t1 = System.nanoTime();
                    Thread.sleep((long)(sleepTimeInMillis * 0.99)); //Thread sleep for 99% of wait time
                    t2 = System.nanoTime();
                    timeToSleep -= (t2 - t1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(timeToSleep > 0){ //Busy wait the rest
                    t1 = System.nanoTime();
                    t2 = System.nanoTime();
                    timeToSleep -= (t2 - t1);
                }

                if(running){
                    SoundGenerator.play();
                }
                totalOffset = (System.nanoTime() - offsetCounter) - currentSleepTime;
                //System.out.println("Total offset: " + totalOffset);
            }
        }

        public void stop(){
            running = false;
        }

        public void setSleepTimeInMillis(int sleepTimeInMillis){
            this.sleepTimeInMillis = sleepTimeInMillis;
        }
    }
}
