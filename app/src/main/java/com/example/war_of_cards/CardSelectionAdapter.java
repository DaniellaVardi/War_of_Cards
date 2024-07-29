package com.example.war_of_cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Model.Card;

import java.util.List;

public class CardSelectionAdapter extends RecyclerView.Adapter<CardSelectionAdapter.CardViewHolder> {

    private Context context;
    private List<Card> cardList;
    private List<Card> selectedCards;

    public CardSelectionAdapter(Context context, List<Card> cardList, List<Card> selectedCards) {
        this.context = context;
        this.cardList = cardList;
        this.selectedCards = selectedCards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_selection, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardImage.setImageResource(card.getImageResource());
        holder.checkBox.setChecked(selectedCards.contains(card));
        holder.itemView.setOnClickListener(v -> {
            if (selectedCards.contains(card)) {
                selectedCards.remove(card);
                holder.checkBox.setChecked(false);
            } else {
                if (selectedCards.size() < 3) {
                    selectedCards.add(card);
                    holder.checkBox.setChecked(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        CheckBox checkBox;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.item_card_image);
            checkBox = itemView.findViewById(R.id.item_card_checkbox);
        }
    }
}
