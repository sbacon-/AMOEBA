package dev.chancho.amoeba.utilities;

import javax.sound.sampled.*;
import java.io.IOException;

public class Aria {
    public Track[] tracks;
    public AudioInputStream ais = null, fallback = null;
    public Clip mus = null, fall = null;
    public boolean mute = false;
    public Aria(){
        tracks = new Track[]{
                new Track("splash", false),
                new Track("hello", true)};
        play(tracks[0].trackName);
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
                String msg = String.format("Exception loading res/sound/%s.mp3:\n %s",trackName,e.getMessage());
                System.out.println(msg);
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
                        Track.class.getResource("res/sound/" + trackName + ".aiff"));
            } catch (UnsupportedAudioFileException | IOException e) {
                String msg = String.format("Exception loading res/sound/%s.mp3:\n %s",trackName,e.getMessage());
                System.out.println(msg);

            }
        }
    }
}

