package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.war_of_cards.Model.Player;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private TextView profileName;
    private TextView profileEmail;
    private TextView profileCoins;
    private Player player;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileCoins = findViewById(R.id.profile_coins);
        backButton = findViewById(R.id.back_BTN);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Players").child(uid);

        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                player = task.getResult().getValue(Player.class);
                Log.d("player", player.toString());
                if (player != null) {
                    updateProfileUI();
                }
            }
        });

        // Set up the return to menu button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Main Menu Activity
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });
    }


    private void updateProfileUI() {
        profileName.setText(player.getName());
        profileEmail.setText(player.getEmail());
        profileCoins.setText(String.valueOf(player.getCoins()));
        // Update UI with the player's cards as needed
    }
}
