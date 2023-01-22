package dev.chancho.amoeba.utilities;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class Aria {
    public Track[] tracks;
    public boolean mute = false;
    public Aria(){
        tracks = new Track[]{
                new Track("splash", false),
                new Track("hello", true)};
    }
    public void play(String trackName){
        if(mute)
            return;
        Track track = null;
        for(Track t : tracks) {
            if(t.trackName.equals(trackName)){
                track = t;
                break;
            }
        }
        assert track != null;
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(track.audioInput);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
                mute = true;
                System.out.printf("Exception loading res/sound/%s.mp3:\n %s",trackName,e.getMessage());
        }
    }

    public static class Track{
        public String trackName;
        public AudioInputStream audioInput;
        public boolean loop;
        public Track(String trackName, boolean loop){
            this.trackName = trackName;
            this.loop = loop;
            try {
                audioInput = AudioSystem.getAudioInputStream(
                        Objects.requireNonNull(Track.class.getResource("res/sound/" + trackName + ".aiff")));
            } catch (UnsupportedAudioFileException | IOException e) {
                System.out.printf("Exception loading res/sound/%s.mp3:\n %s",trackName,e.getMessage());
            }
        }
    }
}

