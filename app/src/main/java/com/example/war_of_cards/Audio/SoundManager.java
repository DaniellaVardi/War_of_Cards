package com.example.war_of_cards.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.war_of_cards.R;

public class SoundManager {

    public static final int SOUND_GAME_START = R.raw.mortal_comet;
    public static final int SOUND_ROUND_1 = R.raw.round_one;
    public static final int SOUND_ROUND_2 = R.raw.round_two;
    public static final int SOUND_ROUND_3 = R.raw.round_three;
    public static final int SOUND_LOSE = R.raw.lose_soud;
    public static final int SOUND_VICTORY = R.raw.victory_sound;

    private Context context;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerShort;

    public SoundManager(Context context) {
        this.context = context;
    }

    public void playLooping(int soundId, float lVol, float rVol) {
        releaseMediaPlayer(); // Release the previous instance if any
        mediaPlayer = MediaPlayer.create(context, soundId);
        mediaPlayer.setVolume(lVol, rVol);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void playSound(int soundId,float lVol, float rVol) {
        releaseMediaPlayerShort(); // Release the previous instance if any
        mediaPlayerShort = MediaPlayer.create(context, soundId);
        mediaPlayerShort.setVolume(lVol, rVol);
        mediaPlayerShort.setLooping(false);
        mediaPlayerShort.start();

        // Release the media player once the sound has finished playing
        mediaPlayerShort.setOnCompletionListener(mp -> releaseMediaPlayerShort());
    }

    public void stopLooping() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void release() {
        releaseMediaPlayer();
        releaseMediaPlayerShort();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void releaseMediaPlayerShort() {
        if (mediaPlayerShort != null) {
            mediaPlayerShort.release();
            mediaPlayerShort = null;
        }
    }
}