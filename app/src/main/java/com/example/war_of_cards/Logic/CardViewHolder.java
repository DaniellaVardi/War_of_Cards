package com.example.war_of_cards.Logic;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.R;

public class CardViewHolder extends RecyclerView.ViewHolder {
    ImageView cardImage;
    TextView cardName;
    TextView cardValue;

    public CardViewHolder(View itemView) {
        super(itemView);
        cardImage = itemView.findViewById(R.id.item_card_IMG_image);
        cardName = itemView.findViewById(R.id.item_card_LBL_name);
        cardValue = itemView.findViewById(R.id.item_card_LBL_value);
    }
}

