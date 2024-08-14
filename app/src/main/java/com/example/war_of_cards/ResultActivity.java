package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.war_of_cards.Audio.SoundManager;
import com.example.war_of_cards.Model.Game;

public class ResultActivity extends AppCompatActivity {

    private SoundManager soundManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        soundManager = new SoundManager(this);


        int player1Score = getIntent().getIntExtra("player1Score", 0);
        int player2Score = getIntent().getIntExtra("player2Score", 0);

        TextView resultTextView = findViewById(R.id.result_text_view);
        Button playAgainButton = findViewById(R.id.result_btn_play_again);
        Button mainMenuButton = findViewById(R.id.result_btn_main_menu);

        if (player1Score > player2Score) {
            resultTextView.setText("VICTORY !");
            soundManager.playSound(SoundManager.SOUND_VICTORY);
        } else if (player2Score > player1Score) {
            resultTextView.setText("YOU LOSE !");
            soundManager.playSound(SoundManager.SOUND_LOSE);
        } else {
            resultTextView.setText("The game is a draw!");
        }

        playAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, GameSetupActivity.class);
            intent.putExtra("mode", getIntent().getStringExtra("mode"));
            startActivity(intent);
        });

        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundManager.release();
    }
}
