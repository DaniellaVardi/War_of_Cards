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
import java.util.List;

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

    public void updatePlayer(String uid, final DataLoadCallback callback ){
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
        if(Player.getInstancePlayer() == null){
            Player.init(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), "Player");
            Player.init("","","","AI");

        }
        Player player = Player.getInstancePlayer();
        ref.child(player.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<HashMap<String, Object>> cardsData = (ArrayList<HashMap<String, Object>>) snapshot.child("cards").getValue();
                ArrayList<Card> cards = new ArrayList<>();

                for (HashMap<String, Object> cardMap : cardsData) {
                    Card card = new Card();
                    card.setImageResource(((Number) cardMap.get("imageResource")).intValue());
                    card.setName((String) cardMap.get("name"));
                    card.setPower(((Number) cardMap.get("power")).intValue());
                    card.setSelected((Boolean) cardMap.get("selected"));
                    card.setValue(((Number) cardMap.get("value")).intValue());
                    cards.add(card);
                }

                Player.setCards(cards);
                System.out.println("Player: " + Player.getInstancePlayer().toString());
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
