package com.example.tvcharactersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context context;
    private List<character> characterList;
    private List<character> fullList;

    public CharacterAdapter(Context context, List<character> characterList) {
        this.context = context;
        this.characterList = new ArrayList<>(characterList);
        this.fullList = new ArrayList<>(characterList); // לשמירה על הרשימה המקורית
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        character c = characterList.get(position);
        holder.txtName.setText(c.getName());
        holder.txtDescription.setText(c.getDescription());
        holder.imgCharacter.setImageResource(c.getImageResId());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, c.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void filterList(String text) {
        characterList.clear();
        if (text.isEmpty()) {
            characterList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (character c : fullList) {
                if (c.getName().toLowerCase().contains(text)) {
                    characterList.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCharacter;
        TextView txtName, txtDescription;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.imgCharacter);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
