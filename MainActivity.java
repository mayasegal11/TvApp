package com.example.tvcharactersapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<character> characterList = new ArrayList<>();
    private CharacterAdapter adapter;
    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        editSearch = findViewById(R.id.editSearch);

        characterList.add(new character("SpongeBob", "Lives in a pineapple under the sea", R.drawable.spongebob));
        characterList.add(new character("Patrick", "SpongeBob's best friend", R.drawable.patrick));
        characterList.add(new character("Squidward", "Grumpy neighbor", R.drawable.squidward));
        characterList.add(new character("Mr. Krabs", "Owner of Krusty Krab", R.drawable.krabs));
        characterList.add(new character("Sandy", "Squirrel from Texas", R.drawable.sandy));
        characterList.add(new character("Plankton", "Wants the secret formula", R.drawable.plankton));
        characterList.add(new character("Gary", "Meow!", R.drawable.gary));
        characterList.add(new character("Pearl", "Mr. Krabs' daughter", R.drawable.pearel));
        characterList.add(new character("Larry", "The Lobster", R.drawable.larry));
        characterList.add(new character("Mrs. Puff", "Boat school teacher", R.drawable.puff));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CharacterAdapter(this, characterList);
        recyclerView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filterList(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
