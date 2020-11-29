package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class MediaPlay {
    MediaPlayer mediaPlayer;

    public MediaPlay(){

    }

    public void playMusic() {
        String musicFile = "src/main/java/sound/Frogger Main Song Theme (loop).mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void playChangeLevelMusic() {
        String musicFile = "src/main/java/sound/change.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }

    public void playGameOverMusic() {
        String musicFile = "src/main/java/sound/gameover.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }
}

