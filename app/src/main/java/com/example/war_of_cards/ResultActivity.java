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
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        soundManager = new SoundManager(this);

        // Retrieve the game object passed from the previous activity
        game = (Game) getIntent().getSerializableExtra("game");

        TextView resultTextView = null;
        if (game != null) {
            int player1Score = game.getPlayer1Score();
            int player2Score = game.getPlayer2Score();

            resultTextView = findViewById(R.id.result_text_view);
            Button playAgainButton = findViewById(R.id.result_btn_play_again);
            Button mainMenuButton = findViewById(R.id.result_btn_main_menu);

            if (player1Score > player2Score) {
                resultTextView.setText("VICTORY !");
                soundManager.playSound(SoundManager.SOUND_VICTORY, 0.6f, 0.6f);
            } else if (player2Score > player1Score) {
                resultTextView.setText("YOU LOSE !");
                soundManager.playSound(SoundManager.SOUND_LOSE, 0.6f, 0.6f);
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
        } else {
            // Handle the case where game is null
            resultTextView.setText("Error: Game data is missing.");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundManager.release();
    }
}
