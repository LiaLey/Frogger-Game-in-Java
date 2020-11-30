package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This class defines the background music that will be played throughout the game as well as some sound effects.
 */
public class MediaPlay {
    MediaPlayer mediaPlayer;

    /**
     * Constructor class of MediaPlay that creates and instance of MediaPlay
     */
    public MediaPlay(){

    }

    /**
     * This method plays the background music once it is called.
     */
    public void playMusic() {
        String musicFile = "src/main/resources/sound/Frogger Main Song Theme (loop).mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * This method plays the change level sound effect once it is called.
     * This method is called on the level changes.
     */
    public void playChangeLevelMusic() {
        String musicFile = "src/main/resources/sound/change.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }

    /**
     * This method plays the Game Over sound effect once it is called.
     * This method is called once the player runs out of lives and the game is over.
     */
    public void playGameOverMusic() {
        String musicFile = "src/main/resources/sound/gameover.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }

    /**
     * This method will stop the background music once it is called.
     */
    public void stopMusic() {
        mediaPlayer.stop();
    }
}

