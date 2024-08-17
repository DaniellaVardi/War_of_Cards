package com.example.war_of_cards.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Player;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseService {

    private DatabaseReference ref;

    public DatabaseService(String name) {
        this.ref = FirebaseDatabase.getInstance().getReference(name);
    }

    public void save(Object obj, String id) {
        ref.child(id).setValue(obj)
                .addOnSuccessListener(aVoid -> Log.d("Player", "Data saved successfully"))
                .addOnFailureListener(e -> Log.d("Player", "Failed to save data: " + e.getMessage()));
    }

    public void updatePlayer(String uid, final DataLoadCallback callback) {
        fetchData(ref.child(uid), snapshot -> {
            Player player = snapshot.getValue(Player.class);
            if (player != null) {
                callback.onDataLoaded(player);
            } else {
                callback.onDataLoadFailed("User data is null");
            }
        }, error -> {
            Log.e("DatabaseService", "Error loading user data: ", error.toException());
            callback.onDataLoadFailed(error.getMessage());
        });
    }

    private void fetchData(@NonNull DatabaseReference reference, DataLoadAction onDataLoaded, ErrorAction onError) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onDataLoaded.onData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onError.onError(error);
            }
        });
    }

    public void loadPlayer(FirebaseUser currentUser) {
        String userId = currentUser.getUid();
        ref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = snapshot.getValue(Player.class);
                if (player != null) {
                    player.setUid(userId); // Set the UID to ensure the player object is properly identified
                    Player.setInstance(player); // Set the loaded player as the current instance
                    Log.d("Player", "Loaded player: " + player.toString());
                } else {
                    Log.d("Player", "Player data is null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("ERROR", "can't load player");
            }
        });
    }

    public interface DataLoadCallback {
        void onDataLoaded(Player player);
        void onDataLoadFailed(String error);
    }

    private interface DataLoadAction {
        void onData(DataSnapshot snapshot);
    }

    private interface ErrorAction {
        void onError(DatabaseError error);
    }
}
