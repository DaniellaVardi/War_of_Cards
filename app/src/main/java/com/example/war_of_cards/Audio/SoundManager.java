package com.example.war_of_cards.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

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
    private SoundPool soundPool;
    private int soundGameStart;
    private int soundRound1;
    private int soundRound2;
    private int soundRound3;
    private int soundLose;
    private int soundVictory;


    public SoundManager(Context context) {
        this.context = context;

        soundPool = new SoundPool.Builder().setMaxStreams(6).build();

        soundGameStart = soundPool.load(context, SOUND_GAME_START, 1);
        soundRound1 = soundPool.load(context, SOUND_ROUND_1, 1);
        soundRound2 = soundPool.load(context, SOUND_ROUND_2, 1);
        soundRound3 = soundPool.load(context, SOUND_ROUND_3, 1);
        soundLose = soundPool.load(context, SOUND_LOSE, 1);
        soundVictory = soundPool.load(context, SOUND_VICTORY, 1);

        mediaPlayer = MediaPlayer.create(context, SOUND_GAME_START);
    }

    public void playLooping(int soundId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, soundId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void playSound(int soundId) {
        soundPool.play(soundId, 3f, 3f, 1, 0, 1);
    }

    public void stopLooping() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        stopLooping();
    }
}
