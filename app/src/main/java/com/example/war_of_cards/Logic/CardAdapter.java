package com.example.war_of_cards.Logic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<Card> cardList;
    private OnCardClickListener onCardClickListener;

    public CardAdapter(Context context, List<Card> cardList, OnCardClickListener onCardClickListener) {
        this.context = context;
        this.cardList = cardList;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_shop, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardName.setText(card.getName());
        holder.cardValue.setText(String.valueOf(card.getValue()));
        holder.cardImage.setImageResource(card.getImageResource());

        // Highlight selected cards
        if (card.isSelected()) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> onCardClickListener.onCardClick(card));
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView cardName;
        TextView cardValue;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.item_card_IMG_image);
            cardName = itemView.findViewById(R.id.item_card_LBL_name);
            cardValue = itemView.findViewById(R.id.item_card_LBL_value);
        }
    }

    public interface OnCardClickListener {
        void onCardClick(Card card);
    }
}
