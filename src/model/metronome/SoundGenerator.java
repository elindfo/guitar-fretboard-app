package model.metronome;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class SoundGenerator {

    private static MidiChannel midiChannel;
    private static boolean initialized = false;

    private SoundGenerator(){}

    public static void init(){
        try {
            midiChannel = initMidiChannel(117);
            initialized = true;
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInitialized(){
        return initialized;
    }

    public static void play() {
        midiChannel.allNotesOff();
        midiChannel.allSoundOff();
        midiChannel.noteOn(90, 127);
    }

    public synchronized static void stop(){
        midiChannel.allNotesOff();
    }

    private static MidiChannel initMidiChannel(int instrument) throws MidiUnavailableException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel channel = synth.getChannels()[0];
        // MIDI instruments are traditionally numbered from 1,
        // but the javax.midi API numbers them from 0
        channel.programChange(instrument - 1);
        //channel.setChannelPressure(5);  // optional vibrato
        return channel;
    }
}
